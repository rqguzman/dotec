package br.com.dotec.persistence.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.ErrorSystemDotec;

@Component
public class ErrorSystemDotecDAO 
{	
	private Session session;
		
	public ErrorSystemDotecDAO(Session session){
		this.session = session;
	}
	
	public void salva(ErrorSystemDotec dotec)
	{
		try
		{			
			Transaction transaction = session.beginTransaction();
			this.session.save(dotec);
			transaction.commit();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
