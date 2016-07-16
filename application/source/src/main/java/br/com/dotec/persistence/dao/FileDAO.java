package br.com.dotec.persistence.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.File;

@Component
public class FileDAO {
private Session session;
	
	public FileDAO(Session session){
		this.session = session;		
	}
	
	public void salva(File objtect){
		this.session.save(objtect);
	}
	
	public void remove(File objtect){
		this.session.delete(objtect);
	}
		
	public void altera(File object){
		this.session.update(object);
	}
	
	public File carrega(String id){
		return (File)this.session.load(File.class,id);	
	}

}

