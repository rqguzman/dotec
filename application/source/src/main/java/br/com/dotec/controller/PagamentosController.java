package br.com.dotec.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.model.Boleto;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.FormaPagamento;
import br.com.dotec.model.GerenciadorMensagem;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.StatusPagamento;
import br.com.dotec.persistence.dao.BoletoDAO;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.GerenciadorMensagemDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;
import br.com.dotec.util.DotecException;
import br.com.dotec.util.manager.ManagerFactory;
import br.com.dotec.util.properties.ApplicationProperties;

@Resource
public class PagamentosController {
	
	static Logger logger = Logger.getLogger(PagamentosController.class);
	
	
	private static String ID_TRANSACAO = "id_transacao";
	private static String VALOR = "valor";
	private static String STATUS_PAGAMENTO = "status_pagamento";
	private static String COD_MOIP = "cod_moip";
	private static String FORMA_PAGAMENTO = "forma_pagamento";
	private static String TIPO_PAGAMENTO = "tipo_pagamento";

	private final BoletoDAO boletoDAO;
	private final ClienteDAO clienteDAO;
	private final Result result;
	private final Validator validator;

	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final MovimentacaoDao movimentacaoDao;
	private final GerenciadorMensagemDAO gerenciadorMensagemDAO;

	public PagamentosController(HttpServletRequest request,
			HttpServletResponse response, BoletoDAO boletoDAO, Result result,
			Validator validator, MovimentacaoDao movimentacaoDao,
			ClienteDAO clienteDAO, GerenciadorMensagemDAO gerenciadorMensagemDAO) {
		this.request = request;
		this.response = response;
		this.boletoDAO = boletoDAO;
		this.result = result;
		this.validator = validator;
		this.clienteDAO = clienteDAO;
		this.movimentacaoDao = movimentacaoDao;
		this.gerenciadorMensagemDAO =gerenciadorMensagemDAO; 
	}
	
	/**	 
	  	1 - Autorizado -Pagamento autorizado pelo pagador, porém ainda não creditado para o recebedor em razão do floating
		2 - Iniciado - Pagamento foi iniciado, mas não existem garantias de que será finalizado
		3 - BoletoImpresso - Pagamento ainda não foi confirmado, porém boleto bancário foi impresso e pode ter sido pago (não existem garantias de que será pago)
		4 - Concluido - Pagamento foi concluído, dinheiro debitado do pagador e creditado para o recebedor
		5 - Cancelado - Pagamento foi cancelado por quem estava pagando
		6 - EmAnalise - Pagamento autorizado pelo pagador, mas está em análise e não tem garantias de que será autorizado
		7 - Estornado -Pagamento foi concluído, dinheiro creditado para o recebedor, porém estornado para o cartão de crédito do pagador
  		9 - Reembolsado - Pagamento foi concluído, dinheiro creditado para o recebedor, porém houve o reembolso para a Carteira Moip do pagador
	 * @throws DotecException
	 */
	@Publico	
	public void recebepagamento() throws DotecException {
		try {						
		
			logger.info("iniciando pagamento");
			
			printParameter();

			String idTransacao = (String) request.getParameter(ID_TRANSACAO);
			int valor = Integer.parseInt(request.getParameter(VALOR));
			int statusPagamento = Integer.parseInt(request
					.getParameter(STATUS_PAGAMENTO));
			String codMoip = (String) request.getParameter(COD_MOIP);
			int formaDePagamento = Integer.parseInt(request
					.getParameter(FORMA_PAGAMENTO));
			String tipoPagamento = (String) request
					.getParameter(TIPO_PAGAMENTO);

			logger.info("Call pagamento : idTransacao=" + idTransacao
					+ ";valor=" + valor + ";statusPagamento=" + statusPagamento
					+ "" + ";codMoip=" + codMoip + ";formaDePagamento="
					+ formaDePagamento + ";tipoPagamento=" + tipoPagamento);

			Boleto boleto = boletoDAO.getByIdproprio(idTransacao);
			
			if (boleto != null) {
				boleto.setFormaDePagamento(formaDePagamento);
				boleto.setTipoPagamento(tipoPagamento);
				boleto.setCodigoMoip(codMoip);

				if (statusPagamento == 4 || statusPagamento == 1 ) {
					boleto.setPago(true);
					Cliente cliente = clienteDAO
							.carregaPorIdProprio(idTransacao);
					if (cliente != null) {
						confirmaPagamentoAutoCadastro(cliente);

						Movimentacao movimentacao = movimentacaoDao
								.carregaByIdProprio(idTransacao);
						movimentacao
								.setStatusDaMovimentacao(StatusDaMovimentacao.EXECUTADA);
						movimentacaoDao.altera(movimentacao);
					}
				}
				if (boleto.getStatusPagamento() != statusPagamento) {
					boleto.setStatusPagamento(statusPagamento);
					boletoDAO.salva(boleto);

					sendEmailAtualizaStatus(boleto);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new DotecException(e);
		}
	}
/**
 * 
 * @param cliente
 * @throws EmailException
 * @throws DotecException
 */
	private void confirmaPagamentoAutoCadastro(Cliente cliente)
			throws EmailException, DotecException {
		cliente.setHabilitado(true);
		clienteDAO.atualiza(cliente);

		GerenciadorMensagem mensagem = gerenciadorMensagemDAO.carregaByTitle("confirmacaocadastro");
		
		HtmlEmail email = new HtmlEmail();
		String emailFrom = mensagem.getEmailFrom();
		String name = ApplicationProperties.getInstance().getMailName();
		email.setSubject(mensagem.getSubject());
		email.addTo(cliente.getEmail(), cliente.getNome());
		email.setFrom(emailFrom, name);
		
		String html = mensagem.getBody();
		html = html.replaceAll("%NOME%",  cliente.getNome());
		html = html.replaceAll("%EMAIL%", cliente.getEmail());
		html = html.replaceAll("%LOGIN%",  cliente.getUsuarios().get(0).getEmail());
					
		email.setHtmlMsg(html);
		ManagerFactory.getSendMailManager().sendMail(email, html);
	}

	/**
	 * 
	 * @param b
	 * @throws EmailException
	 * @throws DotecException
	 */
	private void sendEmailAtualizaStatus(Boleto b)
	throws EmailException, DotecException {
		
		GerenciadorMensagem mensagem =  gerenciadorMensagemDAO.carregaByTitle("atualizastatus");
		
		HtmlEmail email = new HtmlEmail();
		String emailFrom = ApplicationProperties.getInstance().getMailLogin();
		String name = ApplicationProperties.getInstance().getMailName();		
		String emailPagamento = mensagem.getEmailFrom();
		
		String html = mensagem.getBody();
		html = html.replaceAll("%EMAIL_PAGAMENTO%", emailPagamento);
		html = html.replaceAll("%IDPROPRIO%", b.getIdProprio());
		html = html.replaceAll("%ANO_REFERENCIA%", String.valueOf(b.getAnoReferencia()));
		html = html.replaceAll("%MES_REFERENCIA%", String.valueOf(b.getMesReferencia()));
		html = html.replaceAll("%VALOR%", String.valueOf(b.getValor()));
		html = html.replaceAll("%STATUS_PAGAMENTO%", StatusPagamento.getPagamento(b.getStatusPagamento()));
		html = html.replaceAll("%TIPO_PAGAMENTO%",b.getTipoPagamento());
		html = html.replaceAll("%FORMA_PAGAMENTO%",FormaPagamento.getFormaPagamento(b.getFormaDePagamento()));
																
		email.setSubject(mensagem.getSubject() + b.getIdProprio());
		email.addTo(emailPagamento);
		email.setFrom(emailFrom, name);			
		email.setHtmlMsg(html);
		ManagerFactory.getSendMailManager().sendMail(email, html);
	}
	
	/**
	 * 
	 */
	public void printParameter() {
		Enumeration enumeration = request.getParameterNames();
		// apenas imprime os parametros retornados pelo MoIP.
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			String param = request.getParameter(paramName);

			logger.info("paramName=" + paramName + ", value=" + param);
			System.out.println(paramName + ": " + param);
		}
	}

}
