package br.com.dotec.persistence.dao;
import java.util.List;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.TipoDeLogradouro;

@Component
public class TipoDeLogradouroDAO {
	
private Session session;
	
	public TipoDeLogradouroDAO(Session session){
		this.session = session;
	}
	
	public void salva(TipoDeLogradouro objtect){
		this.session.save(objtect);
	}
	
	public void remove(TipoDeLogradouro objtect){
		this.session.delete(objtect);
	}
	public TipoDeLogradouro carrega(Long id){
		return (TipoDeLogradouro)this.session.load(TipoDeLogradouro.class,id);
		
	}
	public void atualiza(TipoDeLogradouro objtect){
		this.session.update(objtect);
	}

	public List<TipoDeLogradouro> lista(){
		return this.session.createCriteria(TipoDeLogradouro.class).list();
	}

}
