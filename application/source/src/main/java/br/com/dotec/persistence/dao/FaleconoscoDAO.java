package br.com.dotec.persistence.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Faleconosco;

@Component
public class FaleconoscoDAO {
	private Session session;
	
	public FaleconoscoDAO(Session session){
		this.session = session;		
	}
	
	public void save(Faleconosco faleConosco){
		this.session.save(faleConosco);
	}
	
}
