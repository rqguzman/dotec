package br.com.dotec.util.moip;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.w3c.dom.Node;

import br.com.dotec.model.Boleto;
import br.com.dotec.model.BoletoItem;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.ClientePessoaFisica;
import br.com.dotec.model.ClientePessoaJuridica;
import br.com.dotec.model.Endereco;
import br.com.dotec.model.GerenciadorMensagem;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.TipoDeMovimentacao;
import br.com.dotec.persistence.dao.BoletoDAO;
import br.com.dotec.persistence.dao.CaixaDAO;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.GerenciadorMensagemDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;
import br.com.dotec.persistence.dao.TabelaDePrecoDAO;
import br.com.dotec.util.DotecException;
import br.com.dotec.util.MovimentacoesPorDia;
import br.com.dotec.util.XmlUtil;
import br.com.dotec.util.manager.ManagerFactory;
import br.com.dotec.util.properties.ApplicationProperties;

public class ManagerMoip implements IManagerMoip{
	private  String moipUrl ;
	private  String moipToken;
	private  String moipKey;
	DecimalFormat df = setFormatCurrency();
	
	static Logger logger = Logger.getLogger(ManagerMoip.class);
	
	public List<Cliente> findCustomerWithDateGenerationBank(int diaVencimento,  Session session) throws DotecException{
		List<Cliente> clientes = new ArrayList<Cliente>();		
		try{													
			try{								
				ClienteDAO clienteDAO = new ClienteDAO(session);									
				clientes = clienteDAO.listaVencimento(new Long(diaVencimento));																					
			} 
			catch (Exception e){
				logger.error(e);
			}										
		}
		catch(Exception e){
			logger.error(e);
		}				
		if(clientes.isEmpty())		
			return Collections.emptyList();
		else
			return clientes;
	}
	
	
	public List<Movimentacao> findMovementOpenByCustomer(Cliente cliente, Session session) throws DotecException
	{		
		List<Movimentacao> movs = null;						
		try {			
			MovimentacaoDao movimentacaoDao = new MovimentacaoDao(session);	
			movs = movimentacaoDao.listaMovimentacaoSemBoleto(cliente.getId());
		} 
		catch (Exception e) {
			logger.error(e);
		}
				
		if(movs.isEmpty())
			return Collections.emptyList();
		else
			return movs;
	}
	
	public Boleto createBank(List<Movimentacao> movs, Cliente cliente, int diaVencimento, Session session) throws DotecException
	{				
		List<BoletoItem> boletoItems = new ArrayList<BoletoItem>();
		this.moipUrl 	= ApplicationProperties.getInstance().getMoipUrl();	
		this.moipToken 	= ApplicationProperties.getInstance().getMoipToken();
		this.moipKey 	= ApplicationProperties.getInstance().getMoipKey();					
		
		DateTime dateTime = DateTime.now();
		
		double totalBoleto = 0.0;
		
		try 
		{																												
			StringBuilder mensagemXml = new StringBuilder();																
			mensagemXml.append("<Mensagens>");						
			String dtApuracao1 = "";
			String dtApuracao2 = "";
			
			if(diaVencimento == 0)
			{
				dtApuracao1 = dateTime.getDayOfMonth()+1 + "/" + (dateTime.getMonthOfYear()-1) +"/"+dateTime.getYear();
				dtApuracao2 = formataData(dateTime);
				// TODO: Modificar esta data
				//dtApuracao1 = "11/04/2013";
				//dtApuracao2 = "10/05/2013";
				
				ClienteDAO clienteDAO = new ClienteDAO(session);
				boolean existeBoletoGerado = clienteDAO.verificaClienteComMovimentacaoBoleto(cliente);
				if(existeBoletoGerado)
				{
					mensagemXml.append("<Mensagem>Data de Apuração : " + dtApuracao1  + " à " + dtApuracao2+"</Mensagem>");			
				}
				else
				{					
					mensagemXml.append("<Mensagem>Data de Apuração : " + formataData(cliente.getCriadoEm()) + " à " +  dtApuracao2+"</Mensagem>");				
				}
			}
			//verifica se existe caixa para o respectivo cliente
			CaixaDAO caixaDAO  = new CaixaDAO(session);
			List<Caixa> caixa = caixaDAO.getCaixaByCliente(cliente);						
			
			if(caixa.size() > 0){								
				TabelaDePrecoDAO tabelaDePrecoDAO = new TabelaDePrecoDAO(session);
				double totalMensal = caixa.size() * tabelaDePrecoDAO.getValor("Guarda Mensal Por Caixa").getPrice();				
				totalBoleto += totalMensal;
				int intnovaCaixa = 0;
				for (Movimentacao mov : movs) {
					if(mov.getTipoDeMovimentacao().getNome().equals("Nova caixa"))
					{
						intnovaCaixa++;						
					}
				}
				
				mensagemXml.append("<Mensagem>");
				mensagemXml.append("Guarda mensal - " + (caixa.size()-intnovaCaixa) + " caixas - R$: " + Double.parseDouble(df.format(totalMensal).replace(",", "."))); 
				mensagemXml.append("</Mensagem>");
			}	
						
			for (Movimentacao mov : movs) {
				if(mov.getTipoDeMovimentacao().equals(TipoDeMovimentacao.NOVO_CLIENTE)){
					totalBoleto += mov.getValor();
					mensagemXml.append("<Mensagem>");
					mensagemXml.append("Cadastro - R$: " + Double.parseDouble(df.format(mov.getValor()).replace(",", ".")));
					mensagemXml.append("</Mensagem>");
				}
			}
			
			//se caixa e movimentações não existirem retornamos um objeto nullo
			if(caixa.isEmpty() && movs.isEmpty())
			{
				return null;				
			}
						
			totalBoleto = getMessageXml(cliente, session, mensagemXml, totalBoleto);			
			mensagemXml.append("</Mensagens>");																
			StringBuilder boletoXml =  new StringBuilder();					
			String dataVencimento = dateTime.getYear() + "-" + dateTime.getMonthOfYear() + "-";
			
			if(diaVencimento==0)
				dataVencimento += cliente.getDiaDeVencimento();
			else
				dataVencimento += (diaVencimento + 3);
			
			// TODO: Modificar esta data
			//dataVencimento="2013-05-10";
			
			totalBoleto = Double.parseDouble(df.format(totalBoleto).replace(",", "."));			
			Boleto boleto = createBoleto(totalBoleto);						
			boletoXml.append("<EnviarInstrucao>"); 
			boletoXml.append("<InstrucaoUnica>");
			boletoXml.append("<Razao>Pagador: " + cliente.getNome() + "</Razao>");
			boletoXml.append("<IdProprio>" + boleto.getIdProprio() + "</IdProprio>");
			boletoXml.append("<Valores>"); 
			boletoXml.append("<Valor moeda=\"BRL\">" + totalBoleto + "</Valor>"); 
			boletoXml.append("</Valores>"); 
			boletoXml.append("<FormasPagamento>");
			boletoXml.append("<FormaPagamento>BoletoBancario</FormaPagamento>");
			boletoXml.append("<FormaPagamento>CartaoCredito</FormaPagamento>");
			boletoXml.append("<FormaPagamento>DebitoBancario</FormaPagamento>");
			boletoXml.append(" </FormasPagamento>"); 
			boletoXml.append("<Boleto>");
				//boletoXml.append("<DiasExpiracao Tipo=\"Corridos\">5</DiasExpiracao>");
				boletoXml.append("<DataVencimento>" + dataVencimento + "</DataVencimento>"); 
			boletoXml.append("</Boleto>");
			boletoXml.append(mensagemXml.toString());						
			boletoXml.append("<Pagador>"); 
			boletoXml.append("<Nome>"	+ cliente.getNome() + "</Nome>"); 
			boletoXml.append("<Email>" + cliente.getEmail() + "</Email>");		
		
			if (cliente.getTipoDePessoa().name().equals("PF"))
				boletoXml.append("<Identidade>"	+ ((ClientePessoaFisica) cliente).getRg() + "</Identidade>");
			else
				boletoXml.append("<Identidade>"	+ ((ClientePessoaJuridica) cliente).getCnpj() + "</Identidade>");
			
			List<Endereco> enderecos = cliente.getEnderecos();
		
			if (enderecos.size() == 0)
				throw new DotecException("Nenhum endereço encontrado para o cliente "+ cliente.getNome());
		
			boletoXml.append("<EnderecoCobranca>"); 
			boletoXml.append("<Logradouro>" + enderecos.get(0).getNomeDoLogrado() + "</Logradouro>");
			boletoXml.append("<Numero>" + enderecos.get(0).getNumero() + "</Numero>");
			boletoXml.append("<Complemento>" + enderecos.get(0).getComplemento()+ "</Complemento>"); 
			boletoXml.append("<Bairro>" + enderecos.get(0).getBairro() + "</Bairro>"); 
			boletoXml.append("<Cidade>" + enderecos.get(0).getMunicipio() + "</Cidade>");
			boletoXml.append("<Estado>" + enderecos.get(0).getUf() + "</Estado>");
			boletoXml.append("<Pais>Brasil</Pais>"); 
			boletoXml.append("<CEP>" + enderecos.get(0).getCep() + "</CEP>"); 
			boletoXml.append("<TelefoneFixo>"+ cliente.getTelefone().getDdd()+ cliente.getTelefone().getNumero() + "</TelefoneFixo>");
			boletoXml.append("</EnderecoCobranca>"); 
			boletoXml.append("</Pagador>");
			boletoXml.append("</InstrucaoUnica>"); 
			boletoXml.append("</EnviarInstrucao>");
			
			logger.info(boletoXml.toString());
			System.err.println(boletoXml.toString());
			String tokenValidate = null;
			RequestEntity requestEntity;													
			
			PostMethod post = getPostMethod();													
			requestEntity = new StringRequestEntity(boletoXml.toString(),"application/x-www-formurlencoded", "UTF-8");
			
			//requestEntity = new StringRequestEntity(getBoletoFake(boleto.getIdProprio()).toString(),"application/x-www-formurlencoded", "UTF-8");
											
			for (Movimentacao movimentacao : movs) {									
				BoletoItem boletoItem = new BoletoItem();
				boletoItem.setId(movimentacao.getId());
				boletoItem.setMovimentacao(movimentacao);				
				boletoItems.add(boletoItem);		
				
				movimentacao.setBoletoGerado(true);
			}	
										
			post.setRequestEntity(requestEntity);
			
			HttpClient client = new HttpClient();
			int status = client.executeMethod(post);
			
			if (status == 200) 
			{
				tokenValidate = post.getResponseBodyAsString().toString();				
				SAXReader reader = new SAXReader();
				Document newNodeDocument = reader.read(new StringReader(tokenValidate));
				DOMWriter domWriter = new DOMWriter();
				org.w3c.dom.Document w3cDoc = domWriter.write(newNodeDocument);

				Node firstNode = w3cDoc.getFirstChild();
				String erro = XmlUtil.readNodeString("Resposta/Erro", firstNode);
				
				if (erro != null) 
				{
					Node nodeErro = XmlUtil.selectSingleNode("Resposta/Erro", firstNode);
					String attrValue = XmlUtil.readAttributeString("Codigo", nodeErro);
	
					logger.error("Error ao gerar boleto : Codigo=" + attrValue	+ " Mensagem=" + erro);
					throw new DotecException(erro);
				} 
				else 
				{
					MovimentacaoDao movimentacaoDao = new MovimentacaoDao(session);
					BoletoDAO boletoDAO = new BoletoDAO(session);
					
					String tokenClient = null;
					tokenClient = XmlUtil.readNodeString("Resposta/Token",	firstNode);
																																					
					for (Movimentacao movimentacao : movs) 	
					{
						movimentacaoDao.salva(movimentacao);					
					}				
					boleto.setToken(tokenClient);																												
					boleto.setCliente(cliente);
					boleto.setItens(boletoItems);
					boletoDAO.salva(boleto);
								
					return boleto;
				}
			}	
			else{
				logger.info("Erro na criação do boleto " + status);
			}
		}
		catch(Exception e){				
			logger.error(e);
			throw new DotecException(e);
		}
		
		return null;		
	}

	private double getMessageXml(Cliente cliente, Session session, StringBuilder mensagens, double totalBoleto) 
	{					
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(session);
		List<MovimentacoesPorDia> porDias = movimentacaoDao.listaMovimentacaoSemBoletoAgrupadoPorData(cliente.getId());			
		TabelaDePrecoDAO tabelaDePrecoDAO = new TabelaDePrecoDAO(session);
		
		for (MovimentacoesPorDia movimentacoesPorDia : porDias) {						
			int v = movimentacoesPorDia.getTotal().intValue();			
			double valor= 0.0 ;
			String descricao = null;
			
			if(movimentacoesPorDia.getCategoria().getNome().equals(TipoDeMovimentacao.SOLICITACAO.getNome())){				
				if(v==1){			
					descricao = "01 CAIXA";
					valor = tabelaDePrecoDAO.getValorFreteEntrega(descricao).getPrice();
				}else if (v >=2 && v<=5){
					descricao = "02 A 05 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteEntrega(descricao).getPrice();
				}else if(v >=6 && v<=10){
					descricao = "06 A 10 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteEntrega(descricao).getPrice();				
				}else if(v >=11 && v<=20){
					descricao = "11 A 20 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteEntrega(descricao).getPrice();
				}
				else if(v >=21 && v<=30){
					descricao = "21 A 30 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteEntrega(descricao).getPrice();
				}
				else if(v >=31 && v<=40){
					descricao = "31 A 40 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteEntrega(descricao).getPrice();
				}
				else if(v >=41 && v<=50){
					descricao = "41 A 50 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteEntrega(descricao).getPrice();
				}
				descricao+=" EM " + formataData(movimentacoesPorDia.getDateTime());
			}
			else if(movimentacoesPorDia.getCategoria().getNome().equals(TipoDeMovimentacao.DEVOLUCAO.getNome())){
				if(v==1){
					descricao = "01 CAIXA";
					valor = tabelaDePrecoDAO.getValorFreteRetirada(descricao).getPrice();						
				}else if (v >=2 && v<=5){
					descricao = "02 A 05 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteRetirada(descricao).getPrice();
				}else if(v >=6 && v<=10){
					descricao = "06 A 10 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteRetirada(descricao).getPrice();				
				}else if(v >=11 && v<=20){
					descricao = "11 A 20 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteRetirada(descricao).getPrice();
				}
				else if(v >=21 && v<=30){
					descricao = "21 A 30 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteRetirada(descricao).getPrice();
				}
				else if(v >=31 && v<=40){
					descricao = "31 A 40 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteRetirada(descricao).getPrice();
				}
				else if(v >=41 && v<=50){
					descricao = "41 A 50 CAIXAS";
					valor = tabelaDePrecoDAO.getValorFreteRetirada(descricao).getPrice();
				}				
				descricao+=" EM " + formataData(movimentacoesPorDia.getDateTime());								
			}
			else if (movimentacoesPorDia.getCategoria().getNome().equals(TipoDeMovimentacao.NOVA_CAIXA.getNome())){
				descricao = "Nova Caixa";
				valor = tabelaDePrecoDAO.getValor(descricao).getPrice();
			}
			else if(movimentacoesPorDia.getCategoria().getNome().equals(TipoDeMovimentacao.NOVO_CLIENTE.getNome())){
				descricao = "Cadastro";
				valor = tabelaDePrecoDAO.getValor(descricao).getPrice();
			}
			
			totalBoleto += valor;
			mensagens.append("<Mensagem>");
			mensagens.append("Frete de " + movimentacoesPorDia.getCategoria().getNome() + " - " );
			
			if(descricao != null)
				mensagens.append( descricao + " - " );
			
			mensagens.append( "R$: " + Double.parseDouble(df.format(valor).replace(",", ".")));			
			mensagens.append("</Mensagem>");
		}
		return totalBoleto;
	}
		
	private DecimalFormat setFormatCurrency() 
	{
		String pattern = "###,###,###,###,###.00";
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern(pattern);
		return df;
	}
	
	private PostMethod getPostMethod() 
	{
		PostMethod post = new PostMethod(moipUrl);
		String authHeader = moipToken + ":" + moipKey;
		String encoded = String.valueOf(org.apache.axis.encoding.Base64.encode(authHeader.getBytes()));
		post.setRequestHeader("Authorization", " Basic " + encoded);
		post.setDoAuthentication(true);
		return post;
	}
	
	private Boleto createBoleto(double valor) 
	{		
		DateTime d = DateTime.now();
		Boleto boleto = new Boleto();
		boleto.setAnoReferencia(d.getYear());
		boleto.setDatavencimento(d);
		boleto.setMesReferencia(d.getMonthOfYear());
		boleto.setValor(valor);
		boleto.setIdProprio(null);
		return boleto;
	}
	
	private void sendEmailCadastroBoleto(Cliente cliente, Boleto boleto, String tokenClient, Session session) throws EmailException, DotecException 
	{
		HtmlEmail email = new HtmlEmail();										
		String name = ApplicationProperties.getInstance().getMailName();		
		
		GerenciadorMensagemDAO gerenciadorMensagemDAO = new GerenciadorMensagemDAO(session);		
		GerenciadorMensagem mensagem = gerenciadorMensagemDAO.carregaByTitle("boletogerado"); 
		
		String urlMoipBoleto = ApplicationProperties.getInstance().getMoipUrlBoleto() + tokenClient;
		String html = mensagem.getBody();
		
		String emailFrom = mensagem.getEmailFrom();
		email.setFrom(emailFrom, name);
				
		String subject = mensagem.getSubject();							
		subject = subject.replace("%IDPROPRIO%", boleto.getIdProprio());
		email.setSubject(subject);
		
		String emailTo = cliente.getEmail();
		email.addTo(emailTo);
		email.addTo("jeolcavaco@hotmail.com");
		//email.addCc("jeolcavaco@hotmail.com");
									
		html = html.replaceAll("%NOME%", cliente.getNome());
		html = html.replaceAll("%EMAIL%", cliente.getEmail());
		html = html.replaceAll("%URL%", urlMoipBoleto);
				
		ManagerFactory.getSendMailManager().sendMail(email, html);		
	}	
	
	private String formataData(DateTime dateTime)
	{
		return dateTime.getDayOfMonth() + "/" + dateTime.getMonthOfYear() + "/" + dateTime.getYear();
	}
	
	
	private StringBuilder getBoletoFake(String idproprio){
		StringBuilder builder = new StringBuilder();
		
		builder.append("<EnviarInstrucao>");
		builder.append("<InstrucaoUnica>");
			builder.append("<Razao>Pagador: Vida Centro de Fertilidade LTDA</Razao>");
			builder.append("<IdProprio>"+idproprio+"</IdProprio>");
			builder.append("<Valores>	");
				builder.append("<Valor moeda=\"BRL\">213.10</Valor>");
			builder.append("</Valores>");
			builder.append("<FormasPagamento>");
				builder.append("<FormaPagamento>BoletoBancario</FormaPagamento>");
				builder.append("<FormaPagamento>CartaoCredito</FormaPagamento>");
				builder.append("<FormaPagamento>DebitoBancario</FormaPagamento> ");
			builder.append("</FormasPagamento>");
			builder.append("<Boleto>");
				//builder.append("<DiasExpiracao Tipo=\"Corridos\">5</DiasExpiracao>");
				builder.append("<DataVencimento>2013-5-20</DataVencimento>");
			builder.append("</Boleto>");
			builder.append("<Mensagens>");
				builder.append("<Mensagem>Data de Apuração : 11/4/2013 à 10/5/2013 **Guarda mensal 7 caixas - R$: 34.30  ** 5 Caixas novas  - R$: 24.50    **Frete de Devolução de caixa - 02 A 05 CAIXAS EM 18/4/2013 - R$: 60.00</Mensagem>");	
				builder.append("<Mensagem>Data de Apuração : 19/3/2013 à 10/4/2013 **7 Caixas novas - R$: 34.30  **Frete de Devolução de caixa - 02 A 05 CAIXAS EM 5/4/2013 - R$: 60.00</Mensagem>");														
			builder.append("</Mensagens>");
			builder.append("<Pagador>");
				builder.append("<Nome>Vida Centro de Fertilidade LTDA</Nome>");
				builder.append("<Email>mvdantas@hotmail.com</Email>");
				builder.append("<Identidade>10831663000101</Identidade>");
				builder.append("<EnderecoCobranca>");
					builder.append("<Logradouro>das Américas</Logradouro>");
					builder.append("<Numero>6205</Numero>");
					builder.append("<Complemento>305</Complemento>");
					builder.append("<Bairro>Barra da Tijuca</Bairro>");
					builder.append("<Cidade>Rio de Janeiro</Cidade>");
					builder.append("<Estado>RJ</Estado>");
					builder.append("<Pais>Brasil</Pais>");
					builder.append("<CEP>22793-080</CEP>");
					builder.append("<TelefoneFixo>2124842918</TelefoneFixo>");
				builder.append("</EnderecoCobranca>");
			builder.append("</Pagador>");
		builder.append("</InstrucaoUnica>");
		builder.append("</EnviarInstrucao>");
		
		System.out.println(builder.toString());
		return builder;
	}
}
