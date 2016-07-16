package br.com.dotec.util.moip;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import br.com.dotec.model.Cliente;
import br.com.dotec.model.ClientePessoaFisica;
import br.com.dotec.model.ClientePessoaJuridica;
import br.com.dotec.model.Endereco;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.persistence.dao.BoletoDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;
import br.com.dotec.util.DotecException;
import br.com.dotec.util.XmlUtil;
import br.com.dotec.util.manager.ManagerFactory;
import br.com.dotec.util.properties.ApplicationProperties;

public class MoipManager implements IMoipManager{
	private  String moipUrl ;
	private  String moipToken;
	private  String moipKey;
	
	static Logger logger = Logger.getLogger(MoipManager.class);
	
	public Boleto generateInvoice(List<Movimentacao> mov,  Session session, Cliente cliente, MovimentacaoDao movimentacaoDao,  int diaVencimento)	throws DotecException 
	{								
		String tokenValidate = null;
		RequestEntity requestEntity;
		BoletoDAO boletoDAO = new BoletoDAO(session);
		DateTime dateTime 	= DateTime.now();
		
		this.moipUrl 	= ApplicationProperties.getInstance().getMoipUrl();	
		this.moipToken 	= ApplicationProperties.getInstance().getMoipToken();
		this.moipKey 	= ApplicationProperties.getInstance().getMoipKey();
				
		DecimalFormat df = setFormatCurrency();

		HttpClient client = new HttpClient();
		PostMethod post = getPostMethod();
		
		List<BoletoItem> boletoItems = new ArrayList<BoletoItem>();
		
		double valor = Double.parseDouble(df.format(getTotalBoleto(mov)).replace(",", "."));
		Boleto boleto = getNewBoleto(valor);
		
		
		try 
		{			
			String dataVencimento = dateTime.getYear() + "-" + dateTime.getMonthOfYear() + "-";
			
			if(diaVencimento==0)
				dataVencimento += cliente.getDiaDeVencimento();
			else
				dataVencimento += (diaVencimento + 3);

			String body = getXmlMoip(mov, cliente, df, boletoItems, valor, boleto, dataVencimento);		

			boleto.setItens(boletoItems);
			requestEntity = new StringRequestEntity(body,"application/x-www-formurlencoded", "UTF-8");
			post.setRequestEntity(requestEntity);
			logger.info(body);
			
			int status = client.executeMethod(post);
						
			if (status == 200) 
			{
				tokenValidate = post.getResponseBodyAsString().toString();
				logger.info("tokenValidate " + tokenValidate);

				SAXReader reader = new SAXReader();
				Document newNodeDocument = reader.read(new StringReader(tokenValidate));
				DOMWriter domWriter = new DOMWriter();
				org.w3c.dom.Document w3cDoc = domWriter.write(newNodeDocument);

				Node firstNode = w3cDoc.getFirstChild();
				String erro = XmlUtil.readNodeString("Resposta/Erro", firstNode);
				
				if (erro != null) {
					Node nodeErro = XmlUtil.selectSingleNode("Resposta/Erro", firstNode);
					String attrValue = XmlUtil.readAttributeString("Codigo", nodeErro);
	
					logger.error("Error ao gerar boleto : Codigo=" + attrValue	+ " Mensagem=" + erro);
					throw new DotecException(erro);
				} 
				else 
				{
					String tokenClient = null;
					tokenClient = XmlUtil.readNodeString("Resposta/Token",	firstNode);
					
					boleto.setToken(tokenClient);																												
					boleto.setCliente(cliente);
					boletoDAO.salva(boleto);
																													
					for (Movimentacao movimentacao : mov) 	{
						movimentacaoDao.salva(movimentacao);					
					}																										
					sendEmailCadastroBoleto(cliente, boleto, tokenClient);																
					
					return boleto;
				}
			}
		} catch (Exception e) 
		{
			logger.error(e.getMessage());
			throw new DotecException(e);
		} 
		finally 
		{											
						
			post.releaseConnection();						
		}		
		return boleto;
	}
	
	
	




	private String getXmlMoip(List<Movimentacao> mov, Cliente cliente,
			DecimalFormat df, List<BoletoItem> boletoItems, double valor,
			Boleto boleto, String dataVencimento) throws DotecException {
		String body = "<EnviarInstrucao>" 
			    + "<InstrucaoUnica>"
				+ "<Razao>Pagamento cadastro cliente : "+cliente.getNome()+"</Razao>"
				+ "<IdProprio>" + boleto.getIdProprio() + "</IdProprio>"
				+ "<Valores>" 
				+ "<Valor moeda=\"BRL\">" + valor + "</Valor>" 
				+ "</Valores>" 
				+ "<FormasPagamento>"
				+ "<FormaPagamento>BoletoBancario</FormaPagamento>"
				+ "<FormaPagamento>CartaoCredito</FormaPagamento>"
				+ "<FormaPagamento>DebitoBancario</FormaPagamento>"
				+ " </FormasPagamento>" 
				+ "<Boleto>"
				+ "<DiasExpiracao Tipo=\"Corridos\">5</DiasExpiracao>"
				+ "<DataVencimento>" + dataVencimento+ "T24:00:00.0-03:00</DataVencimento>" 
				+ "</Boleto>";

		body += "<Mensagens>";
		for (Movimentacao movimentacao : mov) {
			BoletoItem boletoItem = new BoletoItem();
			boletoItem.setId(movimentacao.getId());
			boletoItem.setMovimentacao(movimentacao);
			boletoItems.add(boletoItem);

			body += " <Mensagem>"
					+ movimentacao.getTipoDeMovimentacao().getNome()
					+ " - Preço: R$" + df.format(movimentacao.getValor())
					+ "</Mensagem>";

			movimentacao.setBoletoGerado(true);
		}
		body += "</Mensagens>" 
			+ "<Pagador>" 
			+ " <Nome>"	+ cliente.getNome() + "</Nome>" 
			+ " <Email>" + cliente.getEmail() + "</Email>";
		
		if (cliente.getTipoDePessoa().name().equals("PF"))
			body += " <Identidade>"
					+ ((ClientePessoaFisica) cliente).getRg()
					+ "</Identidade>";
		else
			body += " <Identidade>"
					+ ((ClientePessoaJuridica) cliente).getCnpj()
					+ "</Identidade>";

		List<Endereco> enderecos = cliente.getEnderecos();

		if (enderecos.size() == 0)
			throw new DotecException("Nenhum endereço encontrado para o cliente "+ cliente.getNome());
		body += " <EnderecoCobranca>" 
			    + " <Logradouro>" + enderecos.get(0).getNomeDoLogrado() + "</Logradouro>"
				+ " <Numero>" + enderecos.get(0).getNumero() + "</Numero>"
				+ " <Complemento>" + enderecos.get(0).getComplemento()+ "</Complemento>" 
				+ " <Bairro>" + enderecos.get(0).getBairro() + "</Bairro>" 
				+ " <Cidade>" + enderecos.get(0).getMunicipio() + "</Cidade>"
				+ " <Estado>" + enderecos.get(0).getUf() + "</Estado>"
				+ " <Pais>Brasil</Pais>" 
				+ " <CEP>" + enderecos.get(0).getCep() + "</CEP>" 
				+ " <TelefoneFixo>"+ cliente.getTelefone().getDdd()+ cliente.getTelefone().getNumero() + "</TelefoneFixo>"
				+ " </EnderecoCobranca>" 
				+ "</Pagador>"
				+ "</InstrucaoUnica>" 
				+ "</EnviarInstrucao>";
		return body;
	}
	
	
	
		
	private void sendEmailCadastroBoleto(Cliente cliente, Boleto boleto,
			String tokenClient) throws EmailException, DotecException 
	{
		HtmlEmail email = new HtmlEmail();										
		String name = ApplicationProperties.getInstance().getMailName();		
		String emailAtendimento = ApplicationProperties.getInstance().getEmailAtendimento();			
		String urlMoipBoleto = ApplicationProperties.getInstance().getMoipUrlBoleto() + tokenClient;
		String html = ApplicationProperties.getInstance().getMailMessageBodyBoletoGerado();
		String subject = ApplicationProperties.getInstance().getMailMessageSubjectBoletoGerado();
				
		email.setFrom(emailAtendimento, name);
		
		subject = subject.replace("%IDPROPRIO%", boleto.getIdProprio());
		email.setSubject(subject);
		
		String emailTo = cliente.getEmail();
		email.addTo(emailTo);
									
		html = html.replaceAll("%NOME%", cliente.getNome());
		html = html.replaceAll("%EMAIL%", cliente.getEmail());
		html = html.replaceAll("%URL%", urlMoipBoleto);
				
		ManagerFactory.getSendMailManager().sendMail(email, html);		
	}
	
	/**
	 * 
	 * @param valor
	 * @return
	 */
	private Boleto getNewBoleto(double valor) 
	{
		String idProprio = generationIdProprio();

		DateTime d = DateTime.now();
		Boleto boleto = new Boleto();
		boleto.setAnoReferencia(d.getYear());
		boleto.setDatavencimento(DateTime.now());
		boleto.setMesReferencia(d.getMonthOfYear());
		boleto.setValor(valor);
		boleto.setIdProprio(idProprio);

		return boleto;
	}
	
	private PostMethod getPostMethod() {
		PostMethod post = new PostMethod(moipUrl);
		String authHeader = moipToken + ":" + moipKey;
		String encoded = String.valueOf(org.apache.axis.encoding.Base64
				.encode(authHeader.getBytes()));
		post.setRequestHeader("Authorization", " Basic " + encoded);
		post.setDoAuthentication(true);

		return post;
	}
	
	private DecimalFormat setFormatCurrency() {
		String pattern = "###,###,###,###,###.00";
		NumberFormat nf = NumberFormat
				.getNumberInstance(new Locale("pt", "BR"));
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern(pattern);

		return df;
	}

	/**
	 * Gera um identificador proprio para o boleto
	 * 
	 * @return
	 */
	private String generationIdProprio() {
		DateTime d = DateTime.now();
		String idProprio = d.getYear() + "" + d.getMonthOfYear() + ""
				+ d.getDayOfMonth() + "" + d.getHourOfDay() + ""
				+ d.getMinuteOfHour() + "" + d.getSecondOfMinute();

		return idProprio;
	}

	/**
	 * 
	 * @param movs
	 * @return
	 */
	private double getTotalBoleto(List<Movimentacao> movs) {
		double total = 0.0;
		for (Movimentacao movimentacao : movs) {
			if (movimentacao.getValor() != null)
				total += movimentacao.getValor();
		}

		return total;
	}
}

