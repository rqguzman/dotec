package br.com.dotec.persistence.dao;

import java.util.List;
import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Administrador;

@Component
public class AdministradorDAO {

	private Session session;

	public AdministradorDAO(Session session) {
		this.session = session;
	}

	public void salva(Administrador objtect) {
		this.session.save(objtect);
	}

	public void remove(Administrador objtect) {
		this.session.delete(objtect);
	}

	public Administrador carrega(Long id) {
		return (Administrador) this.session.get(Administrador.class, id);

	}

	public void atualiza(Administrador objtect) {
		this.session.update(objtect);
	}

	public List<Administrador> lista() {
		return this.session.createCriteria(Administrador.class).list();
	}

}
