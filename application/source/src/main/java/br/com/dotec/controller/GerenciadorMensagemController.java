package br.com.dotec.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.model.GerenciadorMensagem;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.persistence.dao.GerenciadorMensagemDAO;

@Resource
public class GerenciadorMensagemController {
	private final GerenciadorMensagemDAO gerenciadorMensagemDAO;
	private final Result result;
	private final Validator validator;
	
	public GerenciadorMensagemController(GerenciadorMensagemDAO gerenciadorMensagemDAO, Result result, Validator validator) {
		super();
		this.gerenciadorMensagemDAO = gerenciadorMensagemDAO;
		this.result = result;
		this.validator = validator;		
	}
	
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})		
	@Path(value = { "/admin/gerenciadormensagem/lista" }, priority = 1)
	public List<GerenciadorMensagem> lista() 
	{
		List<GerenciadorMensagem> gerenciadorMensagem = null;		
		try {
			gerenciadorMensagem = gerenciadorMensagemDAO.lista();			
			if(gerenciadorMensagem.isEmpty())
				result.include("notice", "Nenhum item encontrado!!");
			
		} catch (Exception e) {
			 result.include("erros", e.getMessage());
		}
		
		return gerenciadorMensagem; 		
	}
	
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})
	@Path(value={"/admin/gerenciadormensagem/formulario"}, priority=1)
	public GerenciadorMensagem formulario(GerenciadorMensagem gerenciadorMensagem) {		
		return gerenciadorMensagem;
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/gerenciadormensagem/edita"}, priority=1)
	public void edita(Long id) {			
		GerenciadorMensagem gerenciadorMensagem = gerenciadorMensagemDAO.carrega(id);		
		result.forwardTo(this).formulario(gerenciadorMensagem);		
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/gerenciadormensagem/altera"}, priority=1)
	public void altera(GerenciadorMensagem gerenciadorMensagem) {	
		validator.validate(gerenciadorMensagem);
		validator.onErrorUsePageOf(this).formulario(gerenciadorMensagem);
		gerenciadorMensagemDAO.atualiza(gerenciadorMensagem);
		result.redirectTo(this).lista();
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/gerenciadormensagem/adiciona"}, priority=1)
	public void adiciona(GerenciadorMensagem gerenciadorMensagem){
		validator.validate(gerenciadorMensagem);
		validator.onErrorUsePageOf(this).formulario(gerenciadorMensagem);
		gerenciadorMensagemDAO.salva(gerenciadorMensagem);
		result.redirectTo(this).lista();
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})
	@Path(value={"/admin/gerenciadormensagem/remove"}, priority=1)
	public void remove(Long id) {
		GerenciadorMensagem gerenciadorMensagem = gerenciadorMensagemDAO.carrega(id);		
		gerenciadorMensagemDAO.remove(gerenciadorMensagem);
		result.redirectTo(this).lista();
	}
}
