package br.com.dotec.controller;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Content;
import br.com.dotec.model.GerenciadorMensagem;
import br.com.dotec.model.Usuario;
import br.com.dotec.persistence.dao.ContentDAO;
import br.com.dotec.persistence.dao.UsuarioDAO;
import br.com.dotec.persistence.dao.GerenciadorMensagemDAO;
import br.com.dotec.util.DotecException;
import br.com.dotec.util.RandomAlphaNumeric;
import br.com.dotec.util.manager.ManagerFactory;
import br.com.dotec.util.properties.ApplicationProperties;

@Resource
public class IndexController {
	
	private final ContentDAO dao;
	private final GerenciadorMensagemDAO gerenciadorMensagemDAO;
	private final Result result;
	private final UserInfo userInfo;
	private final UsuarioDAO usuarioDAO;
	
	static Logger logger = Logger.getLogger(IndexController.class);
	
	public IndexController(ContentDAO dao, Result result,UserInfo userInfo, UsuarioDAO usuarioDAO, GerenciadorMensagemDAO gerenciadorMensagemDAO) {
		this.dao = dao;
		this.result = result;
		this.userInfo = userInfo;
		this.usuarioDAO = usuarioDAO;
		this.gerenciadorMensagemDAO = gerenciadorMensagemDAO;
	}
	@Publico
	@Path(value= {"/","/{page}.html"},priority=1)
	public List<Content> index(String page) {		
		if(page==null)
			return dao.listIsHomePage();		
		List<Content> content = (List<Content>) dao.get(page);					
				
		return content;
	}
	
	
	@Path(value= {"/logout"},priority=1)
	public void logout() {
		userInfo.logout();
		result.redirectTo(this).index(null);
	}

	@Publico
	@Path(value = { "/forgot" }, priority = 1)
	public void forgot(String emailUser) {
		Usuario usuario = usuarioDAO.carrega(emailUser);

		if (usuario == null) {
			String mensagem = "E-mail não encontrado em nosso banco de dados.";
			result.use(Results.json()).withoutRoot().from(mensagem).serialize();
		} else {
			String senha = RandomAlphaNumeric.randomString(8);
			usuario.setSenha(senha);	

			usuarioDAO.merge(usuario);
		
			sendEmailCadastro(usuario, senha);
			result.use(Results.json()).withoutRoot().from(
					"Usuário e senha enviado para o seu email.").serialize();

		}
	}
	
	private void sendEmailCadastro(Usuario usuario, String senha) 
	{
		GerenciadorMensagem mensagem = gerenciadorMensagemDAO.carregaByTitle("lembretesenha");
		HtmlEmail email = new HtmlEmail();
		
		String emailFrom = mensagem.getEmailFrom();
		String name = ApplicationProperties.getInstance().getMailName();

		try 
		{
			email.addTo(usuario.getEmail(), usuario.getNome());
			email.setFrom(emailFrom, name);
			String subject = mensagem.getSubject();
			email.setSubject(subject);
			
			String html = mensagem.getBody();
			html = html.replaceAll("%NOME%", usuario.getNome());
			html = html.replaceAll("%EMAIL%", usuario.getEmail());
			html = html.replaceAll("%LOGIN%", usuario.getEmail());
			html = html.replaceAll("%SENHA%", senha);
			
			email.setHtmlMsg(html);
			ManagerFactory.getSendMailManager().sendMail(email, html);

		} 
		catch (EmailException e) 
		{		
			logger.error(e);
		} 
		catch (DotecException e) 
		{			
			logger.error(e);
		}
		catch(Exception e)
		{
			logger.error(e);
		}
}

}
