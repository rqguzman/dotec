package br.com.dotec.persistence.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Pasta;

@Component
public class PastaDAO {
private Session session;
	
	public PastaDAO(Session session){
		this.session = session;		
	}
	
	public void salva(Pasta objtect){
		this.session.save(objtect);
	}
	
	public void remove(Pasta objtect){
		this.session.delete(objtect);
	}
		
	public void altera(Pasta object){
		this.session.update(object);
	}
	
	public Pasta carrega(Long id){
		return (Pasta)this.session.load(Pasta.class,id);	
	}

}

