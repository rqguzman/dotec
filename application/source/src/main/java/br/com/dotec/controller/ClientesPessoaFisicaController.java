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
import br.com.dotec.model.ClientePessoaFisica;
import br.com.dotec.model.Endereco;
import br.com.dotec.model.Sexo;
import br.com.dotec.model.TipoDeLogradouro;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.model.Uf;
import br.com.dotec.model.Usuario;
import br.com.dotec.persistence.dao.BoletoDAO;
import br.com.dotec.persistence.dao.ClientePessoaFisicaDAO;
import br.com.dotec.persistence.dao.EntregaCepDAO;
import br.com.dotec.util.DotecException;

@Resource
public class ClientesPessoaFisicaController {
	private final ClientePessoaFisicaDAO clienteDao;	
	private final Result result;
	private final Validator validator;
	private final UserInfo userInfo;
	private final Cache cache;
	private final EntregaCepDAO entregaDAO;
	
	
	static Logger logger = Logger.getLogger(ClientesPessoaFisicaController.class);
	
	

	public ClientesPessoaFisicaController(ClientePessoaFisicaDAO clienteDao, BoletoDAO boletoDao,
			Result result, Validator validator, UserInfo userInfo, Cache cache,
			EntregaCepDAO entregaDAO) {
		super();
		this.clienteDao = clienteDao;		
		this.result = result;
		this.validator = validator;
		this.userInfo = userInfo;
		this.cache = cache;
		this.entregaDAO = entregaDAO;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"admin/clientesPessoaFisica/lista"}, priority=1)
	public List<ClientePessoaFisica> lista() {
		List<ClientePessoaFisica> clientesPessoaFisica = clienteDao.lista();
		return clientesPessoaFisica;
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	public void formulario(ClientePessoaFisica clientePessoaFisica) {
		incluiListas();
		result.include("clientePessoaFisica", clientePessoaFisica);
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"admin/clientesPessoaFisica/adiciona"}, priority=1)
	public void adiciona(ClientePessoaFisica clientePessoaFisica) {
		incluiListas();
		clientePessoaFisica.setCriadaPor(userInfo.getUser());
		validator.validate(clientePessoaFisica);
		for (Endereco endereco : clientePessoaFisica.getEnderecos()) {
			validator.validate(endereco);
		}
		
		final boolean possuiEnderecoDeCobranca = clientePessoaFisica.possuiEnderecoDeCobranca();
		validator.checking(new Validations() {
			{
				that(possuiEnderecoDeCobranca,"Endereço","validator.notHaveBillingAddress");
			}
		});
		
		
		final boolean possuiEnderecoDeEntrega = clientePessoaFisica.possuiEnderecoDeEntrega();
		validator.checking(new Validations() {
			{
				that(possuiEnderecoDeEntrega,"Endereço","validator.notHaveDeliveryAddress");
			}
		});
		
		
		String tel = clientePessoaFisica.getTelefone().getNumero();
		final boolean isValidPhone = ((tel == null) || (tel.length() != 8) || (!tel.matches("[0-9]{8}?"))) ? false : true;
		validator.checking(new Validations() {
			{
				that(isValidPhone, "Telefone", "validator.invalidPhone");
			}
		});
				
		cepDeliveryValidator(clientePessoaFisica);
				
		clientePessoaFisica = preencheDadosDoUsuario(clientePessoaFisica);
		Usuario usuario =  clientePessoaFisica.getUsuarios().get(0);
		validator.validate(usuario);
		validateSenha(usuario);
		validator.onErrorUsePageOf(this).formulario(clientePessoaFisica);

		try {
			clienteDao.salva(clientePessoaFisica);
		} catch (DotecException e) {
			
			logger.error(e);
		}
		result.redirectTo(ClientesController.class).lista();
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	@Path(value={"admin/clientesPessoaFisica/edita"}, priority=1)
	public void edita(Long id) {
		ClientePessoaFisica clientePessoaFisica = clienteDao.carrega(id);
		result.forwardTo(this).formulario(clientePessoaFisica);
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value={"admin/clientesPessoaFisica/altera"}, priority=1)
	public void altera(ClientePessoaFisica clientePessoaFisica) {
		incluiListas();
		validator.validate(clientePessoaFisica);
		if(clientePessoaFisica.getEnderecos()!=null){
			for (Endereco endereco : clientePessoaFisica.getEnderecos()) {
				validator.validate(endereco);
			}
			
			final boolean possuiEnderecoDeCobranca = clientePessoaFisica.possuiEnderecoDeCobranca();
			validator.checking(new Validations() {
				{
					that(possuiEnderecoDeCobranca,"Endereço","validator.notHaveBillingAddress");
				}
			});
			
			final boolean possuiEnderecoDeEntrega = clientePessoaFisica.possuiEnderecoDeEntrega();
			validator.checking(new Validations() {
				{
					that(possuiEnderecoDeEntrega,"Endereço","validator.notHaveDeliveryAddress");
				}
			});
			
			cepDeliveryValidator(clientePessoaFisica);
		}
				
		validateSenha(clientePessoaFisica.getUsuarios().get(0));
				
		validator.onErrorUsePageOf(this).formulario(clientePessoaFisica);
		clientePessoaFisica = preencheDadosDoUsuario(clientePessoaFisica);
		validator.validate(clientePessoaFisica.getUsuarios().get(0));
		validator.onErrorUsePageOf(this).formulario(clientePessoaFisica);
		
		restoreCliente(clientePessoaFisica);
		cache.setInativo(clientePessoaFisica, clientePessoaFisica.isInativo());
		clienteDao.merge(clientePessoaFisica);
		
		if(userInfo.getUser().getTipoDeUsuario()==TipoDeUsuario.ADMINISTRADOR){
			result.redirectTo(ClientesController.class).lista();
		}else{
			result.redirectTo(ElementosController.class).lista();
		}
	}

	@Restrito({ TipoDeUsuario.ADMINISTRADOR })
	public void remove(Long id) {
		ClientePessoaFisica clientePessoaFisica = clienteDao.carrega(id);
		clienteDao.remove(clientePessoaFisica);
		result.redirectTo(this).lista();
	}

	public void incluiListas() {
		result.include("sexoList", Sexo.values());
		result.include("tipoDeCliente", "pf");
		result.include("ufs", Uf.values());
		result.include("tipoDeLogradouroList", TipoDeLogradouro.values());
	}

	private ClientePessoaFisica preencheDadosDoUsuario(
			ClientePessoaFisica clientePessoaFisica) {
		clientePessoaFisica.getUsuarios().get(0)
				.setNome(clientePessoaFisica.getNome());
		clientePessoaFisica.getUsuarios().get(0)
				.setEmail(clientePessoaFisica.getEmail());
		clientePessoaFisica.getUsuarios().get(0)
				.setCpf(clientePessoaFisica.getCpf());
		clientePessoaFisica.getUsuarios().get(0)
				.setRg(clientePessoaFisica.getRg());
		clientePessoaFisica.getUsuarios().get(0)
				.setDataDeNascimento(clientePessoaFisica.getDataDeNascimento());
		clientePessoaFisica.getUsuarios().get(0)
				.setSexo(clientePessoaFisica.getSexo());
		clientePessoaFisica.getUsuarios().get(0)
				.setTipoDeUsuario(TipoDeUsuario.PRIMARIO);
		return clientePessoaFisica;
	}
	
	
	private void cepDeliveryValidator(Cliente cliente){
		for (Endereco endereco : cliente.getEnderecos()) {
			if(endereco.isEnderecoDeCobranca()){
				String cep = endereco.getCep();
				String[] ceps = cep.split("-");
				cep = ceps[0]+ceps[1];
				
				
				final boolean cepEntregaValida = entregaDAO.carrega(Long.parseLong(cep)) != null ? true : false;		
				validator.checking(new Validations() {
					{that(cepEntregaValida, "Entrega", "validator.invalidDelivery");}
				});
			}
			
		}
	}
	
	private void restoreCliente(Cliente cliente){
		Cliente clienteAux = clienteDao.carrega(cliente.getId());
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