package br.com.dotec.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.model.Content;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.persistence.dao.ContentDAO;

@Resource
public class ContentsController {	
	private final ContentDAO contentDAO;
	private final Result result;
	private final Validator validator;

	public ContentsController(ContentDAO contentDAO, Result result, Validator validator) {
		super();
		this.contentDAO = contentDAO;
		this.result = result;
		this.validator = validator;		
	}
	
	@Publico
	@Path(value= {"/pop/","/pop/{page}.html"},priority=1)
	public List<Content> index(String page) {		
		if(page==null)
			return contentDAO.listIsHomePage();		
		List<Content> content = (List<Content>) contentDAO.get(page);					
				
		return content;
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})
	@Path(value={"/admin/contents/lista"}, priority=1)
	public List<Content> lista(Content content) 
	{
		List<Content> contents = null;
		
		try {
			contents = contentDAO.lista(content);
			
			if(contents.isEmpty())
				result.include("notice", "Nenhum conteúdo encontrado!!");
			
		} catch (Exception e) {
			 result.include("erros", e.getMessage());
		}
		
		return contents; 
		
	}
	
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})
	@Path(value={"/admin/contents/formulario"}, priority=1)
	public Content formulario(Content content) {		
		return content;
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/contents/edita"}, priority=1)
	public void edita(Long id) {			
		Content content = contentDAO.carrega(id);		
		result.forwardTo(this).formulario(content);		
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/contents/altera"}, priority=1)
	public void altera(Content content) {	
		validator.validate(content);
		validator.onErrorUsePageOf(this).formulario(content);
		contentDAO.atualiza(content);
		result.redirectTo(this).lista(null);
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/contents/adiciona"}, priority=1)
	public void adiciona(Content content)
	{
		validator.validate(content);
		validator.onErrorUsePageOf(this).formulario(content);
		contentDAO.salva(content);
		result.redirectTo(ContentsController.class).lista(null);
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})
	@Path(value={"/admin/contents/remove"}, priority=1)
	public void remove(Long id) {
		Content content = contentDAO.carrega(id);
		contentDAO.remove(content);
		result.redirectTo(this).lista(null);
	}
}
