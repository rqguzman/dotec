package br.com.dotec.persistence.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.dotec.model.Endereco;

public class EnderecoDAO {
	
private Session session;
private Transaction transaction;
	
	public EnderecoDAO(Session session){
		this.session = session;
	}
	
	public void salva(Endereco p){
		transaction = this.session.beginTransaction();
		this.session.save(p);
		transaction.commit();
	}
	
	public void remove(Endereco p){
		this.session.delete(p);
		this.session.flush();
	}
	public Endereco procura(Long id){
		return (Endereco)this.session.load(Endereco.class,id);
	}
	public void atualiza(Endereco p){
		this.session.update(p);
		this.session.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> lista(){
		return this.session.createCriteria(Endereco.class).list();
	}


}
