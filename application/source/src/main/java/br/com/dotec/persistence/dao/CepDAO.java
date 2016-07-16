package br.com.dotec.persistence.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Cep;
@Component
public class CepDAO {
	private Session session;
	
	public CepDAO(Session session){
		this.session = session;		
	}
	
	
	public Cep get(String cep )
	{
		return  (Cep) this.session.createCriteria(Cep.class).add(Restrictions.eq("cep", cep)).uniqueResult();		
	}
	
	
}
