package br.com.dotec.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.cache.Cache;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Elemento;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.StatusDeLocalizacao;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.TipoDeMovimentacao;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.persistence.dao.CaixaDAO;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.ElementoDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;

@Resource
public class MovimentacaosController {
	private final MovimentacaoDao dao;
	private final ClienteDAO clienteDAO;
	private final CaixaDAO caixaDAO;
	private final ElementoDAO elementoDAO;
	private final UserInfo userInfo;
	private final Cache cache;
	private final Result result;
	private final Validator validator;

	public MovimentacaosController(MovimentacaoDao dao, ClienteDAO clienteDAO,
			CaixaDAO caixaDAO, ElementoDAO elementoDAO, UserInfo userInfo,
			Cache cache, Result result, Validator validator) {
		super();
		this.dao = dao;
		this.clienteDAO = clienteDAO;
		this.caixaDAO = caixaDAO;
		this.elementoDAO = elementoDAO;
		this.userInfo = userInfo;
		this.cache = cache;
		this.result = result;
		this.validator = validator;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	public List<Movimentacao> lista(Movimentacao movimentacao) {
		Cliente cliente = clienteDAO.carrega(userInfo.getUser());
		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Elemento> elementos = new ArrayList<Elemento>();

		movimentacoes = dao.lista(cliente, movimentacao);

		for (Movimentacao movimentacaoAux : movimentacoes) {
			clientes.add(clienteDAO.carrega(movimentacaoAux.getCriadaPor()));
			elementos.add(elementoDAO.carrega(movimentacaoAux));
		}

		DateTime dateTime = DateTime.now();
		result.include("tipoDeMovimentacaoList", TipoDeMovimentacao.values());
		result.include("statusDaMovimentacaoList",
				StatusDaMovimentacao.values());
		result.include("movimentacao", movimentacao);
		result.include("las30Days", dateTime.plusDays(-30));
		result.include("las60Days", dateTime.plusDays(-60));
		result.include("las90Days", dateTime.plusDays(-90));
		result.include("clientes", clientes);
		result.include("elementos", elementos);

		return movimentacoes;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	public Movimentacao edita(Long id) {
		Movimentacao movimentacao = dao.carrega(id);
		return movimentacao;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	public void updateStatus(Movimentacao movimentacao) {
		StatusDaMovimentacao statusDaMovimentacao = movimentacao
				.getStatusDaMovimentacao();
		Movimentacao movimentacaoAux = dao.carrega(movimentacao.getId());
		movimentacaoAux.setStatusDaMovimentacao(statusDaMovimentacao);
		dao.altera(movimentacaoAux);

		if (statusDaMovimentacao == statusDaMovimentacao.EXECUTADA) {
			if (movimentacaoAux.getTipoDeMovimentacao() == TipoDeMovimentacao.NOVA_CAIXA
					|| movimentacaoAux.getTipoDeMovimentacao() == TipoDeMovimentacao.SOLICITACAO) {
				Elemento elemento = elementoDAO.carrega(movimentacaoAux);
				elemento.setStatusDeLocalizacao(StatusDeLocalizacao.CLIENTE);
				List<Elemento> elementosFilhos = elementoDAO.getElementosFilhos(elemento);
				for (Elemento elementoFilho : elementosFilhos) {
					elementoFilho.setStatusDeLocalizacao(StatusDeLocalizacao.CLIENTE);
					elementoDAO.atualiza(elementoFilho);
				}
				elementoDAO.atualiza(elemento);
			}

			if (movimentacaoAux.getTipoDeMovimentacao() == TipoDeMovimentacao.DEVOLUCAO) {
				Elemento elemento = elementoDAO.carrega(movimentacaoAux);
				elemento.setStatusDeLocalizacao(StatusDeLocalizacao.DOTEC);
				List<Elemento> elementosFilhos = elementoDAO.getElementosFilhos(elemento);
				for (Elemento elementoFilho : elementosFilhos) {
					elementoFilho.setStatusDeLocalizacao(StatusDeLocalizacao.DOTEC);
					elementoDAO.atualiza(elementoFilho);
				}
				elementoDAO.atualiza(elemento);
			}
		}
		Cliente cliente = clienteDAO.carrega(movimentacaoAux.getCriadaPor());
		cache.limparCache(cliente);
		result.use(Results.json()).from(1, "success").serialize();
	}

}
