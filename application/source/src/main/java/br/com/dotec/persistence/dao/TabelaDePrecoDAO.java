package br.com.dotec.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Elemento;
import br.com.dotec.model.TabelaPreco;
@Component
public class TabelaDePrecoDAO {
	private Session session;
	
	static Logger logger = Logger.getLogger(TabelaDePrecoDAO.class);
	
	public TabelaDePrecoDAO(Session session){
		this.session = session;		
	}
		
	@SuppressWarnings("unchecked")
	public List<TabelaPreco> lista(){		
		Criteria criteria = this.session.createCriteria(TabelaPreco.class);				
		return criteria.list();
	}
	
	public void salva(TabelaPreco preco){		
		this.session.save(preco);
	}
	
	public void remove(TabelaPreco preco){		
		this.session.delete(preco);
	}
	
	public TabelaPreco carrega(Long id){	
		return (TabelaPreco)this.session.get(TabelaPreco.class, id);	
	}
	
	public void atualiza(TabelaPreco preco){		
		this.session.update(preco);
	}
	
	public void merge(TabelaPreco preco){		
		this.session.merge(preco);
	}
	
	
	
	public TabelaPreco getValorFreteEntrega(String name){		
		Query query = (Query) this.session
		.createQuery("select t from TabelaPreco as t where t.title = :title and t.category = :category");
		query.setParameter("title", name);
		query.setParameter("category", "Frete de Solicitação de Entrega de:");
	
		return  (TabelaPreco) query.uniqueResult();	
	}
	
	public TabelaPreco getValorFreteRetirada(String name){		
		Query query = (Query) this.session
		.createQuery("select t from TabelaPreco as t where t.title = :title and t.category = :category");
		query.setParameter("title", name);
		query.setParameter("category", "Frete de Solicitação de Retirada de:");
	
		return  (TabelaPreco) query.uniqueResult();	
	}
	
	public TabelaPreco getValor(String name){		
		Query query = (Query) this.session
		.createQuery("select t from TabelaPreco as t where t.title = :title ");
		query.setParameter("title", name);
		
	
		return  (TabelaPreco) query.uniqueResult();	
	}
}
