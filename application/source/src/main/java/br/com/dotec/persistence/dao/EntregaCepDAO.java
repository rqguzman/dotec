package br.com.dotec.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Content;
import br.com.dotec.model.Entrega;
@Component
public class EntregaCepDAO {
	private Session session;

	public EntregaCepDAO(Session session) {
		this.session = session;
	}

	public Entrega carrega(Long cep){
		Query query = (Query) this.session
		.createQuery("select e from Entrega e Where e.cepFim  >= :cep and e.cepInicio <= :cep");
		query.setLong("cep", cep);
		return (Entrega) query.uniqueResult();
	}
	
	public void salva(Entrega entrega) {
		this.session.save(entrega);
	}

	public void remove(Entrega entrega) {
		this.session.delete(entrega);
	}
	
	public Entrega carrega(String entrega) {
		return (Entrega) this.session.createCriteria(Entrega.class).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Entrega> lista(Entrega entrega) {			
		Criteria entregas = this.session.createCriteria(Entrega.class);
		if(entrega != null && !entrega.getCidade().equals("[Digite a cidade]")){							
			if(entrega.getCidade()!=null)
				entregas.add(Restrictions.ilike("cidade", '%'+entrega.getCidade()+'%'));
		}		
		return entregas.addOrder(Order.asc("cepInicio")).list();		
	}
	
	
	public Entrega get(Long id)
	{
		return (Entrega)this.session.get(Entrega.class, id);	
	}
	
	public void atualiza(Entrega entrega)
	{
		this.session.update(entrega);
	}
	
	public void merge(Content entrega)
	{
		this.session.merge(entrega);
	}
}
