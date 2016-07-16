package br.com.dotec.persistence.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Documento;

@Component
public class DocumentoDao {
private Session session;
	
	public DocumentoDao(Session session){
		this.session = session;		
	}
	
	public void salva(Documento objtect){
		this.session.save(objtect);
	}
	
	public void remove(Documento objtect){
		this.session.delete(objtect);
	}
		
	public void altera(Documento object){
		this.session.update(object);
	}
	
	public Documento carrega(Long id){
		return (Documento)this.session.load(Documento.class,id);	
	}

}
