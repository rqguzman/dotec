package br.com.dotec.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Boleto;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.ClientePessoaFisica;
import br.com.dotec.model.ClientePessoaJuridica;
import br.com.dotec.model.Endereco;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.Sexo;
import br.com.dotec.model.TipoDeLogradouro;
import br.com.dotec.model.TipoDePessoa;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.model.Uf;
import br.com.dotec.persistence.dao.BoletoDAO;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.ClientePessoaFisicaDAO;
import br.com.dotec.persistence.dao.ClientePessoaJuridicaDAO;
import br.com.dotec.persistence.dao.EntregaCepDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;

@Resource
public class ClientesController {
	private final ClienteDAO clienteDAO;
	private final ClientePessoaFisicaDAO clientePessoaFisicaDAO;
	private final ClientePessoaJuridicaDAO clientePessJuridicaDAO;
	private final BoletoDAO boletoDAO;
	private final MovimentacaoDao movimentacaoDao;
	private final Result result;
	private final Validator validator;
	private final UserInfo userInfo;
	private final EntregaCepDAO entregaCepDAO;

	public ClientesController(ClienteDAO clienteDAO,
			ClientePessoaFisicaDAO clientePessoaFisicaDAO,
			ClientePessoaJuridicaDAO clientePessJuridicaDAO,
			BoletoDAO boletoDAO, MovimentacaoDao movimentacaoDao,
			Result result, Validator validator, UserInfo userInfo,
			EntregaCepDAO entregaCepDAO) {
		super();
		this.clienteDAO = clienteDAO;
		this.clientePessoaFisicaDAO = clientePessoaFisicaDAO;
		this.clientePessJuridicaDAO = clientePessJuridicaDAO;
		this.boletoDAO = boletoDAO;
		this.movimentacaoDao = movimentacaoDao;
		this.result = result;
		this.validator = validator;
		this.userInfo = userInfo;
		this.entregaCepDAO = entregaCepDAO;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/clientes/lista" }, priority = 1)
	public List<Cliente> lista() {
		List<Cliente> clientes = clienteDAO.lista();
		return clientes;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/clientes/formulario" }, priority = 1)
	public void formulario(Cliente cliente) {
		incluiListas();
		result.include("cliente", cliente);
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/clientes/adiciona" }, priority = 1)
	public void adiciona(Cliente cliente) {
		incluiListas();
		cliente.setCriadaPor(userInfo.getUser());
		validator.validate(cliente);
		validator.onErrorUsePageOf(this).formulario(cliente);
		clienteDAO.salva(cliente);
		result.redirectTo(ClientesController.class).lista();
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/clientes/edita" }, priority = 1)
	public void edita(Long id) {
		Cliente cliente = clienteDAO.carrega(id);
		if (cliente.getTipoDePessoa() == TipoDePessoa.PF) {
			ClientePessoaFisica clientePessoaFisica = clientePessoaFisicaDAO
					.carrega(id);
			if (clientePessoaFisica != null) {
				result.forwardTo(ClientesPessoaFisicaController.class)
						.formulario(clientePessoaFisica);
			}
		} else {
			ClientePessoaJuridica clientePessoaJuridica = clientePessJuridicaDAO
					.carrega(id);
			if (clientePessoaJuridica != null) {
				result.forwardTo(ClientesPessoaJuridicaController.class)
						.formulario(clientePessoaJuridica);
			}
		}
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/clientes/altera" }, priority = 1)
	public void altera(Cliente cliente) {
		incluiListas();
		validator.validate(cliente);

		validator.onErrorUsePageOf(this).formulario(cliente);
		clienteDAO.atualiza(cliente);
		result.redirectTo(this).lista();
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/clientes/remove" }, priority = 1)
	public void remove(Long id) {
		Cliente cliente = clienteDAO.carrega(id);

		List<Boleto> boletos = boletoDAO.listaBoletosByClienteID(cliente);

		System.out.println("Boletos cliente === "
				+ String.valueOf(boletos.size()));
		if (boletos.size() > 0) {
			validator
					.add(new ValidationMessage(
							"O cliente "
									+ cliente.getNome()
									+ ", não pode ser excluído, pois existem boletos no cadastro",
							"erro"));
			validator.onErrorRedirectTo(this).lista();
		}

		List<Movimentacao> mov = movimentacaoDao.lista(cliente);
		System.out.println("Movimentação cliente === "
				+ String.valueOf(mov.size()));
		if (mov.size() > 0) {
			validator
					.add(new ValidationMessage(
							"O usuário "
									+ cliente.getNome()
									+ ", não pode ser excluído, pois existem movimentações no cadastro",
							"erro"));
			validator.onErrorRedirectTo(this).lista();
		}

		clienteDAO.remove(cliente);
		result.redirectTo(this).lista();
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	public void incluiListas() {
		result.include("sexoList", Sexo.values());
		result.include("ufs", Uf.values());
		result.include("tipoDeLogradouroList", TipoDeLogradouro.values());
	}

	@Publico
	public void ajaxCEPDeliveryValidator(String cep) {
	try {
			String[] ceps = cep.split("-");
			cep = ceps[0] + ceps[1];
			String mensagem = "";
			final boolean cepEntregaValida = entregaCepDAO.carrega(Long.parseLong(cep)) != null ? true : false;
			if(cepEntregaValida){
				mensagem ="Cep dentro da nossa área de atuação!";
			}else{
				mensagem ="Infelizmente o seu CEP não está dentro da nossa área de atuação!";
			}
			result.use(Results.json()).withoutRoot().from(mensagem).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("Erro no processamento do Teste do CEP. Verifique o formato digitado XXXXX-XXX.").serialize();
		}
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/clientes/enable/" }, priority = 1)
	public void enable(Long id) {
		Cliente cliente = clienteDAO.carrega(id);
		cliente.setHabilitado(true);
		
		clienteDAO.atualiza(cliente);
		String message = "success";
		result.use(Results.json()).withoutRoot().from(message).serialize();
	}

}
