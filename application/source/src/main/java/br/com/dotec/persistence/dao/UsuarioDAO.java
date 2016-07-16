package br.com.dotec.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.infra.interceptor.Credencial;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Usuario;

@Component
public class UsuarioDAO {

	private Session session;

	public UsuarioDAO(Session session) {
		this.session = session;
	}

	public void salva(Usuario objtect) {
		this.session.save(objtect);
	}

	public void remove(Usuario usuario) {

		this.session.delete(usuario);
	}

	public Usuario carrega(Long id) {
		return (Usuario) this.session.get(Usuario.class, id);

	}

	public Usuario carrega(Usuario usuario) {
		return (Usuario) session.createCriteria(Usuario.class).add(
				Restrictions.eq("email", usuario.getEmail())).add(
				Restrictions.eq("senha", usuario.getSenha())).uniqueResult();
	}

	public Usuario carrega(String email) {
		return (Usuario) session.createCriteria(Usuario.class).add(
				Restrictions.eq("email", email)).uniqueResult();
	}

	public Usuario carrega(Credencial credencial) {
		return (Usuario) session.createCriteria(Usuario.class).add(
				Restrictions.eq("email", credencial.getLogin())).add(
				Restrictions.eq("senha", credencial.getSenha())).uniqueResult();
	}

	public void merge(Usuario usuario) {
		this.session.merge(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> lista() {
		return this.session.createCriteria(Usuario.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> lista(Cliente cliente) {
		Query query = (Query) this.session
				.createQuery("select u from Usuario as u,Cliente as c where u.id = c.usuarios.id and c.id = :clienteId");
		query.setDouble("clienteId", cliente.getId());
		return (List<Usuario>) query.list();
	}

}
