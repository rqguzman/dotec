package br.com.dotec.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.model.TabelaPreco;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.persistence.dao.TabelaDePrecoDAO;

@Resource
public class TabelaPrecoController {
	private final TabelaDePrecoDAO tabelaDePrecoDAO;
	private final Result result;
	private final Validator validator;
	
	public TabelaPrecoController(TabelaDePrecoDAO tabelaDePrecoDAO, Result result, Validator validator) {
		super();
		this.tabelaDePrecoDAO = tabelaDePrecoDAO;
		this.result = result;
		this.validator = validator;		
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})		
	@Path(value = { "/admin/tabelapreco/lista" }, priority = 1)
	public List<TabelaPreco> lista() 
	{
		List<TabelaPreco> tabelaPrecos = null;		
		try {
			tabelaPrecos = tabelaDePrecoDAO.lista();			
			if(tabelaPrecos.isEmpty())
				result.include("notice", "Nenhum item encontrado!!");
			
		} catch (Exception e) {
			 result.include("erros", e.getMessage());
		}
		
		return tabelaPrecos; 		
	}
	
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})
	@Path(value={"/admin/tabelapreco/formulario"}, priority=1)
	public TabelaPreco formulario(TabelaPreco tabelaPreco) {		
		return tabelaPreco;
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/tabelapreco/edita"}, priority=1)
	public void edita(Long id) {			
		TabelaPreco tabelaPreco = tabelaDePrecoDAO.carrega(id);		
		result.forwardTo(this).formulario(tabelaPreco);		
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/tabelapreco/altera"}, priority=1)
	public void altera(TabelaPreco tabelaPreco) {	
		validator.validate(tabelaPreco);
		validator.onErrorUsePageOf(this).formulario(tabelaPreco);
		tabelaDePrecoDAO.atualiza(tabelaPreco);
		result.redirectTo(this).lista();
	}
		
	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"/admin/tabelapreco/adiciona"}, priority=1)
	public void adiciona(TabelaPreco tabelaPreco)
	{
		validator.validate(tabelaPreco);
		validator.onErrorUsePageOf(this).formulario(tabelaPreco);
		tabelaDePrecoDAO.salva(tabelaPreco);
		result.redirectTo(this).lista();
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR})
	@Path(value={"/admin/tabelapreco/remove"}, priority=1)
	public void remove(Long id) {
		TabelaPreco tabelaPreco = tabelaDePrecoDAO.carrega(id);
		tabelaDePrecoDAO.remove(tabelaPreco);
		result.redirectTo(this).lista();
	}
}
