package br.com.dotec.controller;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.model.Faleconosco;
import br.com.dotec.model.GerenciadorMensagem;
import br.com.dotec.persistence.dao.FaleconoscoDAO;
import br.com.dotec.persistence.dao.GerenciadorMensagemDAO;
import br.com.dotec.util.DotecException;
import br.com.dotec.util.manager.ManagerFactory;

@Resource
public class FaleconoscoController {
	private final FaleconoscoDAO dao;
	private final GerenciadorMensagemDAO gerenciadorMensagemDAO;
	private final Result result;
	private final Validator validator;
	
	static Logger logger = Logger.getLogger(FaleconoscoController.class);

	public FaleconoscoController(FaleconoscoDAO dao, Result result,
			Validator validator, GerenciadorMensagemDAO gerenciadorMensagemDAO) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.gerenciadorMensagemDAO = gerenciadorMensagemDAO;
	}

	@Publico
	@Post
	@Path( { "/faleconosco/", "/faleconosco" })
	public void add(Faleconosco faleconosco) {
		validator.validate(faleconosco);
		validator.onErrorUsePageOf(this).form(faleconosco);

		try {
			faleconosco.setProtocolo(generationProtocolo());
			
			dao.save(faleconosco);
			send(faleconosco);
			result.redirectTo(this).form(faleconosco);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Publico
	@Get
	@Path( { "/faleconosco/", "/faleconosco", "/faleconosco/*.html" })
	public Faleconosco form(Faleconosco faleConosco) {
		return faleConosco;
	}

	@Publico
	public void send(Faleconosco faleconosco) {
		try {
			
			GerenciadorMensagem mensagem = gerenciadorMensagemDAO.carregaByTitle("sendemail");
			
			HtmlEmail email = new HtmlEmail();
			email.setFrom(faleconosco.getEmail(), faleconosco.getNome());
			email.setSubject(mensagem.getSubject());
			email.addTo(mensagem.getEmailFrom());
			
			String html = mensagem.getBody();
			html = html.replaceAll("%NOME%", faleconosco.getNome());
			html = html.replaceAll("%EMAIL%", faleconosco.getEmail());
			html = html.replaceAll("%TELEFONE%", faleconosco.getTelefone());
			html = html.replaceAll("%MENSAGEM%", faleconosco.getMensagem());
			
			ManagerFactory.getSendMailManager().sendMail(email, html);

			htmlFrom(faleconosco);		

		} catch (EmailException e) {
			logger.error(e);
		} catch (DotecException e) {			
			logger.error(e);
		}

	}

	/**
	 * Método que envia um email para o cliente, informando o recebimento do
	 * fale conosco
	 * 
	 * @param faleconosco
	 * @param body
	 * @throws EmailException
	 * @throws DotecException
	 */
	public void htmlFrom(Faleconosco faleconosco)
			throws EmailException, DotecException {	
		GerenciadorMensagem mensagem = gerenciadorMensagemDAO.carregaByTitle("faleconosco");
			
		String html = mensagem.getBody();
		html = html.replaceAll("%NOME%", faleconosco.getNome());
		html = html.replaceAll("%EMAIL%", faleconosco.getEmail());		

		HtmlEmail email = new HtmlEmail();
		email.setFrom(mensagem.getEmailFrom());
		email.setSubject(mensagem.getSubject());
		email.addTo(faleconosco.getEmail(), faleconosco.getNome());

		ManagerFactory.getSendMailManager().sendMail(email, html);

	}
	
	private String generationProtocolo() {
		DateTime d = DateTime.now();
		String idProprio = d.getYear() + "" + d.getMonthOfYear() + ""
				+ d.getDayOfMonth() + "" + d.getHourOfDay() + ""
				+ d.getMinuteOfHour() + "" + d.getSecondOfMinute();

		return idProprio;
	}
}
