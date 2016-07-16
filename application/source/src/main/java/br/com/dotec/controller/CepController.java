package br.com.dotec.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Cep;
import br.com.dotec.persistence.dao.CepDAO;
import br.com.dotec.persistence.dao.EntregaCepDAO;

@Resource
public class CepController {

	private final CepDAO cepDAO;
	private final Result result;
	private final Validator validator;

	public CepController(CepDAO cepDAO, Result result, Validator validator) {
		super();
		this.cepDAO = cepDAO;
		this.result = result;
		this.validator = validator;
	}

	@Publico
	@Path(value = { "/cep/get/" }, priority = 1)
	public void get(String cep) {
		Cep cepObj = cepDAO.get(cep);
		result.use(Results.json()).from(cepObj).serialize();
	}

}
