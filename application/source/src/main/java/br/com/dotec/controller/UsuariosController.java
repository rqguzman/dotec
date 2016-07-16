package br.com.dotec.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.interceptor.Credencial;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.Sexo;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.model.Usuario;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;
import br.com.dotec.persistence.dao.UsuarioDAO;

@Resource
public class UsuariosController {
	private final UsuarioDAO dao;
	private final ClienteDAO clienteDAO;
	private final Result result;
	private final Validator validator;
	private final UserInfo userInfo;

	private final MovimentacaoDao movimentacaoDao;

	public UsuariosController(UsuarioDAO dao, ClienteDAO clienteDAO,
			Result result, Validator validator, UserInfo userInfo,
			MovimentacaoDao movimentacaoDao) {
		super();
		this.dao = dao;
		this.clienteDAO = clienteDAO;
		this.result = result;
		this.validator = validator;
		this.userInfo = userInfo;
		this.movimentacaoDao = movimentacaoDao;
	}

	@Restrito( { TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/usuarios/lista" }, priority = 1)
	public List<Usuario> lista() {
		Usuario usuario = userInfo.getUser();
		List<Usuario> usuarios = null;
		if (usuario == null) {
			usuarios = dao.lista();
		} else if (usuario.getTipoDeUsuario() == TipoDeUsuario.ADMINISTRADOR) {
			usuarios = dao.lista();
		} else {
			usuarios = dao.lista(userInfo.getCliente());
		}
		return usuarios;
	}

	@Restrito( { TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/usuarios/formulario" }, priority = 1)
	public Usuario formulario(Usuario Usuario) {
		incluiListas();
		return Usuario;
	}

	@Restrito( { TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/usuarios/adiciona" }, priority = 1)
	public void adiciona(Usuario usuario) {
		incluiListas();
		validator.validate(usuario);
		validateSenha(usuario);
		validator.onErrorUsePageOf(this).formulario(usuario);

		if (userInfo.getUser() == null) {
			usuario.setTipoDeUsuario(TipoDeUsuario.ADMINISTRADOR);
			dao.salva(usuario);
		} else if (userInfo.getUser().getTipoDeUsuario() == TipoDeUsuario.ADMINISTRADOR) {
			usuario.setTipoDeUsuario(TipoDeUsuario.ADMINISTRADOR);
			dao.salva(usuario);
		} else {
			usuario.setTipoDeUsuario(TipoDeUsuario.SECUNDARIO);
			Cliente cliente = clienteDAO.carrega(userInfo.getCliente().getId());
			cliente.getUsuarios().add(usuario);
			clienteDAO.atualiza(cliente);
		}
		result.redirectTo(this).lista();
	}

	@Restrito
	@Path(value = { "/admin/usuarios/edita" }, priority = 1)
	public void edita(Long id) {
		Usuario usuario = dao.carrega(id);
		validateDireitosParaAlteracaoDeUsuario(userInfo.getUser(),usuario);
		result.forwardTo(this).formulario(usuario);
	}
	
	//@Restrito
	@Path(value = { "/admin/usuarios/trocaSenha" }, priority = 1)
	public void trocaSenha(String login,String senhaAtual,String novaSenha,String confirmaSenha) {
		Credencial credencial = new Credencial();
		credencial.setLogin(login);
		credencial.setSenha(senhaAtual);
		Usuario usuario = dao.carrega(credencial);
		
		if(usuario == null){
			throw new RuntimeException("Login ou Senha Inválidos");
		}
				
		if(!novaSenha.equals(confirmaSenha)){
			throw new RuntimeException("Confirmação de Senha inválida");
		}
		
		usuario.setSenha(novaSenha);
		dao.merge(usuario);
		result.use(Results.json()).withoutRoot().from("Senha alterada com sucesso").serialize();	
	}
	
	@Restrito
	@Path(value = { "/admin/usuarios/altera" }, priority = 1)
	public void altera(Usuario usuario) {
		incluiListas();
		validator.validate(usuario);
		validateDireitosParaAlteracaoDeUsuario(userInfo.getUser(),usuario);
		validateSenha(usuario);
		validator.onErrorUsePageOf(this).formulario(usuario);
		usuario.setTipoDeUsuario(dao.carrega(usuario.getId())
				.getTipoDeUsuario());

		dao.merge(usuario);
		
		if(userInfo.getUser().getTipoDeUsuario()==TipoDeUsuario.SECUNDARIO){
			result.redirectTo(ElementosController.class).lista();
		}else{
			result.redirectTo(this).lista();
		}
	}	

	@Restrito( { TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value = { "/admin/usuarios/remove" }, priority = 1)
	public void remove(Long id) 
	{
		Usuario usuario = dao.carrega(id);		
		List<Movimentacao> mov = movimentacaoDao.lista(usuario);
		System.out.println("yyyyyy " + mov.size() + " uuuuuu  " + usuario.getId());	
		if (mov.size() > 0) 
		{
			validator.add(new ValidationMessage("Usuário " + usuario.getNome()+", não pode ser excluído, pois existem movimentações no cadastro", "erro"));
			validator.onErrorRedirectTo(this).lista();
		}
		
		if(!usuario.getTipoDeUsuario().equals(TipoDeUsuario.ADMINISTRADOR))
			dao.remove(usuario);
		
		result.redirectTo(this).lista();
	}

	public void incluiListas() {
		result.include("sexoList", Sexo.values());
	}
	
	private void validateSenha(Usuario usuario){
		final boolean isSenhaNull = usuario.getSenha().equals("d41d8cd98f00b204e9800998ecf8427e") ? false : true;		
		validator.checking(new Validations() {
			{that(isSenhaNull, "senha", "validator.notEmpty");}
		});
	}
	
	private void validateDireitosParaAlteracaoDeUsuario(Usuario usuarioCorrente, Usuario usuarioQueSeraAlterado){
		
		final boolean acessoNegado =((!(usuarioCorrente.getId().equals(usuarioQueSeraAlterado.getId())) && usuarioCorrente.getTipoDeUsuario() == TipoDeUsuario.SECUNDARIO))?false:true;
				
		validator.checking(new Validations() {
			{that(acessoNegado, "usuário", "acesso negado para a alteração");}
		});
		validator.onErrorRedirectTo(ElementosController.class).lista();
			
	}
	
	

}
