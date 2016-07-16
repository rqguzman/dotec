package br.com.dotec.controller;

import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.TipoDeMovimentacao;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.persistence.dao.CaixaDAO;
import br.com.dotec.persistence.dao.ClienteDAO;

@Resource
public class CaixasController {
	private final CaixaDAO dao;
	private final ClienteDAO clienteDAO;
	private final UserInfo userInfo;
	private final Result result;
	private final Validator validator;

	public CaixasController(CaixaDAO dao, ClienteDAO clienteDAO,
			UserInfo userInfo, Result result, Validator validator) {
		super();
		this.dao = dao;
		this.clienteDAO = clienteDAO;
		this.userInfo = userInfo;
		this.result = result;
		this.validator = validator;
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public List<Caixa> lista() {
		//List<Caixa> caixas = clienteDAO.carrega(userInfo.getCliente().getId()).getCaixas();
		//return caixas;
		return null;
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void listaJson() {
		//List<Caixa> caixas = userInfo.getCliente().getCaixas();
		//result.use(Results.json()).from(caixas).serialize();
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void formulario(Caixa caixa) {
		result.include("caixa", caixa);
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void adiciona(Caixa caixa,Boolean ajax) {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setCriadaPor(userInfo.getUser());
		movimentacao.setStatusDaMovimentacao(StatusDaMovimentacao.PENDENTE);
		movimentacao.setTipoDeMovimentacao(TipoDeMovimentacao.NOVA_CAIXA);
		//caixa.setCriadaPor(userInfo.getUser());
		caixa.getMovimentacoes().add(movimentacao);
		validator.validate(caixa);
		validator.onErrorUsePageOf(this).formulario(caixa);
		Cliente cliente = clienteDAO.carrega(userInfo.getCliente().getId()) ;
		//cliente.getCaixas().add(caixa);
		clienteDAO.merge(cliente);
		result.redirectTo(this).lista();
	}
		
	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void adicionaAjax(Caixa caixa,Boolean ajax) {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setCriadaPor(userInfo.getUser());
		movimentacao.setStatusDaMovimentacao(StatusDaMovimentacao.PENDENTE);
		movimentacao.setTipoDeMovimentacao(TipoDeMovimentacao.NOVA_CAIXA);
		//caixa.setCriadaPor(userInfo.getUser());
		caixa.getMovimentacoes().add(movimentacao);
		validator.validate(caixa);
		validator.onErrorSendBadRequest();
		Cliente cliente = clienteDAO.carrega(userInfo.getCliente().getId()) ;
		//cliente.getCaixas().add(caixa);
		clienteDAO.merge(cliente);
		result.use(Results.json()).from(1,"success").serialize();
	}
	

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void edita(Long id) {
		Caixa caixa = dao.carrega(id);
		result.forwardTo(this).formulario(caixa);
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void altera(Caixa cliente) {
		validator.validate(cliente);
		validator.onErrorUsePageOf(this).formulario(cliente);
		dao.atualiza(cliente);
		result.redirectTo(this).lista();
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void remove(Long id) {
		Caixa caixa = dao.carrega(id);
		dao.remove(caixa);
		result.redirectTo(this).lista();
	}

}
