package br.com.dotec.controller;

import java.util.List;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.dotec.model.TipoDeLogradouro;
import br.com.dotec.persistence.dao.TipoDeLogradouroDAO;

@Resource
public class TipoDeLogradourosController {
	private final TipoDeLogradouroDAO dao;
	private final Result result;
	private final Validator validator;
	
	public TipoDeLogradourosController(TipoDeLogradouroDAO dao, Result result,Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	public List<TipoDeLogradouro> lista(){
		List<TipoDeLogradouro> tiposDeLogradouro = dao.lista();
		return tiposDeLogradouro;
	}
	
	public TipoDeLogradouro formulario(TipoDeLogradouro tipoDeLogradouro){
		return tipoDeLogradouro;
	}
	
	public void adiciona(TipoDeLogradouro tipoDeLogradouro){
		validator.validate(tipoDeLogradouro);
		validator.onErrorUsePageOf(this).formulario(tipoDeLogradouro);
		dao.salva(tipoDeLogradouro);
		result.redirectTo(this).lista();
	}
	
	public void edita(Long id){
		TipoDeLogradouro tipoDeLogradouro= dao.carrega(id);
		result.forwardTo(this).formulario(tipoDeLogradouro) ;
	}
	
	public void altera(TipoDeLogradouro tipoDeLogradouro){
		validator.validate(tipoDeLogradouro);
		validator.onErrorUsePageOf(this).formulario(tipoDeLogradouro);
		dao.atualiza(tipoDeLogradouro);
		result.redirectTo(this).lista();
	}
	
	public void remove(Long id){
		TipoDeLogradouro tipoDeLogradouro = dao.carrega(id); 
		dao.remove(tipoDeLogradouro);
		result.redirectTo(this).lista();
	}
	
	
	
}

