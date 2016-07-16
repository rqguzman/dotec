package br.com.dotec.controller;

import java.util.List;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.dotec.model.Administrador;
import br.com.dotec.persistence.dao.AdministradorDAO;

@Resource
public class AdministradoresController {
	private final AdministradorDAO dao;
	private final Result result;
	private final Validator validator;

	public AdministradoresController(AdministradorDAO dao, Result result,
			Validator validator) {
		super();
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	public List<Administrador> lista() {
		List<Administrador> administradores = dao.lista();
		return administradores;
	}

	public Administrador novo(Administrador administrador) {
		return administrador;
	}

	public void adiciona(Administrador administrador) {
		validator.validate(administrador);
		validator.onErrorUsePageOf(this).novo(administrador);
		dao.salva(administrador);
		result.redirectTo(this).lista();
	}

	public Administrador edita(Long id) {
		Administrador administrador = dao.carrega(id);
		return administrador;
	}

	public void altera(Administrador administrador) {
		validator.validate(administrador);
		validator.onErrorUsePageOf(this).edita(administrador.getId());
		dao.atualiza(administrador);
		result.redirectTo(this).lista();
	}

	public void remove(Long id) {
		Administrador administrador = dao.carrega(id);
		dao.remove(administrador);
		result.redirectTo(this).lista();
	}

}
