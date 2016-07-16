package br.com.dotec.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.cache.Cache;
import br.com.dotec.infra.interceptor.Credencial;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Elemento;
import br.com.dotec.model.TipoDeElemento;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.model.Usuario;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.UsuarioDAO;

@Resource
public class AdminController {

	private final Result result;
	private final Validator validator;
	private final UsuarioDAO usuarioDAO;
	private final ClienteDAO clienteDAO;
	private final UserInfo userInfo;
	private final Cache cache;

	public AdminController(Result result, Validator validator,
			UsuarioDAO usuarioDAO, ClienteDAO clienteDAO, UserInfo userInfo,
			Cache cache) {
		super();
		this.result = result;
		this.validator = validator;
		this.usuarioDAO = usuarioDAO;
		this.clienteDAO = clienteDAO;
		this.userInfo = userInfo;
		this.cache = cache;
	}

	@Publico
	@Get
	public void login() {
	}

	@Publico
	@Post
	public void autentica(Credencial credencial) {
		// search for the user in the database
		final Usuario usuario = usuarioDAO.carrega(credencial);
		validator.checking(new Validations() {
			{
				that(usuario, is(notNullValue()), "email",
						"invalid_login_or_password");
			}
		});

		if (usuario != null) {
			if (usuario.getTipoDeUsuario() != TipoDeUsuario.ADMINISTRADOR) {
				final Cliente cliente = clienteDAO.carrega(usuario);

				if(cliente!=null)
				{
					userInfo.setCliente(cliente);				
					cache.setInativo(cliente, cliente.isInativo());
				}else{
					cache.setInativo( true);
					
				}
			}
		}

		validator.onErrorUsePageOf(this).login();

		// the login was valid, add user to session
		userInfo.login(usuario);

		if (usuario.getTipoDeUsuario() == TipoDeUsuario.ADMINISTRADOR) {
			result.redirectTo(MovimentacaosController.class).lista(null);
		} else {
			result.redirectTo(ElementosController.class).lista();
		}
	}

	@Restrito
	public void logout() {
		userInfo.logout();
		result.redirectTo(this).login();
	}
	
	@Restrito
	@Post
	public void confirmarSenha(Credencial credencial){
		// search for the user in the database
		final Usuario usuario = usuarioDAO.carrega(credencial);
		String message = "0"; 
		
		if(usuario != null){
			if(userInfo.getUser().getId().equals(usuario.getId())){
				message = "1"; 
			}
		}
		
		result.use(Results.json()).withoutRoot().from(message).serialize();		
	}
	
	
}
