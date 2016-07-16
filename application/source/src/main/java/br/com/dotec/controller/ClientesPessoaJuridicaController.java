package br.com.dotec.controller;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.dotec.infra.cache.Cache;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.ClientePessoaJuridica;
import br.com.dotec.model.Endereco;
import br.com.dotec.model.Sexo;
import br.com.dotec.model.TipoDeLogradouro;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.model.Uf;
import br.com.dotec.model.Usuario;
import br.com.dotec.persistence.dao.ClientePessoaJuridicaDAO;
import br.com.dotec.persistence.dao.EntregaCepDAO;

@Resource
public class ClientesPessoaJuridicaController {
	private final ClientePessoaJuridicaDAO dao;
	private final Result result;
	private final Validator validator;
	private final UserInfo userInfo;
	private final Cache cache;

	private final EntregaCepDAO entregaDAO;

	static Logger logger = Logger
			.getLogger(ClientesPessoaJuridicaController.class);

	public ClientesPessoaJuridicaController(ClientePessoaJuridicaDAO dao,
			Result result, Validator validator, UserInfo userInfo, Cache cache,
			EntregaCepDAO entregaDAO) {
		super();
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.userInfo = userInfo;
		this.cache = cache;
		this.entregaDAO = entregaDAO;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value = { "admin/clientesPessoaJuridica/lista" }, priority = 1)
	public List<ClientePessoaJuridica> lista() {
		List<ClientePessoaJuridica> clientesPessoaJuridica = dao.lista();
		return clientesPessoaJuridica;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value = { "admin/clientesPessoaJuridica/formulario" }, priority = 1)
	public ClientePessoaJuridica formulario(
			ClientePessoaJuridica clientePessoaJuridica) {
		incluiListas();
		return clientePessoaJuridica;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value = { "admin/clientesPessoaJuridica/adiciona" }, priority = 1)
	public void adiciona(ClientePessoaJuridica clientePessoaJuridica) {
		incluiListas();
		clientePessoaJuridica.setCriadaPor(userInfo.getUser());
		validator.validate(clientePessoaJuridica);
		if (clientePessoaJuridica.getEnderecos() != null) {
			for (Endereco endereco : clientePessoaJuridica.getEnderecos()) {
				validator.validate(endereco);
			}

			final boolean possuiEnderecoDeCobranca = clientePessoaJuridica
					.possuiEnderecoDeCobranca();
			validator.checking(new Validations() {
				{
					that(possuiEnderecoDeCobranca, "Endereço",
							"validator.notHaveBillingAddress");
				}
			});

			final boolean possuiEnderecoDeEntrega = clientePessoaJuridica
					.possuiEnderecoDeEntrega();
			validator.checking(new Validations() {
				{
					that(possuiEnderecoDeEntrega, "Endereço",
							"validator.notHaveDeliveryAddress");
				}
			});

			cepDeliveryValidator(clientePessoaJuridica);
		}

		String tel = clientePessoaJuridica.getTelefone().getNumero();
		final boolean isValidPhone = ((tel == null) || (tel.length() != 8) || (!tel
				.matches("[0-9]{8}?"))) ? false : true;
		validator.checking(new Validations() {
			{
				that(isValidPhone, "Telefone", "validator.invalidPhone");
			}
		});
		
		Usuario usuario= clientePessoaJuridica.getUsuarios().get(0);
		usuario.setTipoDeUsuario(TipoDeUsuario.PRIMARIO);
		validator.validate(usuario);
		validateSenha(usuario);
		validator.onErrorUsePageOf(this).formulario(clientePessoaJuridica);
		cache.setInativo(clientePessoaJuridica, clientePessoaJuridica.isInativo());
		dao.salva(clientePessoaJuridica);
		result.redirectTo(ClientesController.class).lista();
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value = { "admin/clientesPessoaJuridica/edita" }, priority = 1)
	public void edita(Long id) {
		ClientePessoaJuridica clientePessoaJuridica = dao.carrega(id);
		result.forwardTo(this).formulario(clientePessoaJuridica);
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "admin/clientesPessoaJuridica/altera" }, priority = 1)
	public void altera(ClientePessoaJuridica clientePessoaJuridica) {
		incluiListas();
		validator.validate(clientePessoaJuridica);
		for (Endereco endereco : clientePessoaJuridica.getEnderecos()) {
			validator.validate(endereco);
		}

		final boolean possuiEnderecoDeCobranca = clientePessoaJuridica
				.possuiEnderecoDeCobranca();
		validator.checking(new Validations() {
			{
				that(possuiEnderecoDeCobranca, "Endereço",
						"validator.notHaveBillingAddress");
			}
		});

		final boolean possuiEnderecoDeEntrega = clientePessoaJuridica
				.possuiEnderecoDeEntrega();
		validator.checking(new Validations() {
			{
				that(possuiEnderecoDeEntrega, "Endereço",
						"validator.notHaveDeliveryAddress");
			}
		});

		cepDeliveryValidator(clientePessoaJuridica);

		validateSenha(clientePessoaJuridica.getUsuarios().get(0));
		
		validator.onErrorUsePageOf(this).formulario(clientePessoaJuridica);
		clientePessoaJuridica.getUsuarios().get(0)
				.setTipoDeUsuario(TipoDeUsuario.PRIMARIO);
		validator.validate(clientePessoaJuridica.getUsuarios().get(0));
		validator.onErrorUsePageOf(this).formulario(clientePessoaJuridica);

		restoreCliente(clientePessoaJuridica);
		dao.merge(clientePessoaJuridica);

		if (userInfo.getUser().getTipoDeUsuario() == TipoDeUsuario.ADMINISTRADOR) {
			result.redirectTo(ClientesController.class).lista();
		} else {
			result.redirectTo(ElementosController.class).lista();
		}
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value = { "admin/clientesPessoaJuridica/remove" }, priority = 1)
	public void remove(Long id) {
		ClientePessoaJuridica clientePessoaJuridica = dao.carrega(id);
		dao.remove(clientePessoaJuridica);
		result.redirectTo(this).lista();
	}

	public void incluiListas() {
		result.include("sexoList", Sexo.values());
		result.include("tipoDeCliente", "pj");
		result.include("ufs", Uf.values());
		result.include("tipoDeLogradouroList", TipoDeLogradouro.values());
	}

	private void cepDeliveryValidator(Cliente cliente) {
		for (Endereco endereco : cliente.getEnderecos()) {
			if (endereco.isEnderecoDeEntrega()) {
				String cep = endereco.getCep();
				String[] ceps = cep.split("-");
				cep = ceps[0] + ceps[1];

				final boolean cepEntregaValida = entregaDAO.carrega(Long
						.parseLong(cep)) != null ? true : false;
				validator.checking(new Validations() {
					{
						that(cepEntregaValida, "Entrega",
								"validator.invalidDelivery");
					}
				});
			}

		}
	}

	private void restoreCliente(Cliente cliente) {
		Cliente clienteAux = dao.carrega(cliente.getId());
		cliente.setCriadoEm(clienteAux.getCriadoEm());
		cliente.setCriadaPor(clienteAux.getCriadaPor());
		cliente.setElementos(clienteAux.getElementos());		
		cliente.setUsuarios(clienteAux.getUsuarios());
		cliente.setHabilitado(clienteAux.isHabilitado());
		cliente.setContrato(clienteAux.isContrato());
		cliente.setIdProprio(clienteAux.getIdProprio());
	}
	
	private void validateSenha(Usuario usuario){
		final boolean isSenhaNull = usuario.getSenha().equals("d41d8cd98f00b204e9800998ecf8427e") ? false : true;		
		validator.checking(new Validations() {
			{that(isSenhaNull, "senha", "validator.notEmpty");}
		});
	}

}
