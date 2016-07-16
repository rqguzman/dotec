package br.com.dotec.persistence.dao;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.infra.interceptor.Credencial;

@Component
public class CredencialDAO {
	
private Session session;
	
	public CredencialDAO(Session session){
		this.session = session;
	}
	
	public Credencial carrega(Credencial credencial) {
		return (Credencial) session.createCriteria(Credencial.class)
		.add(Restrictions.eq("email", credencial.getLogin()))
		.add(Restrictions.eq("senha", credencial.getSenha()))
		.uniqueResult();
	}
}






