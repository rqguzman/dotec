package br.com.dotec.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.GerenciadorMensagem;
@Component
public class GerenciadorMensagemDAO {
	private Session session;
	
	static Logger logger = Logger.getLogger(GerenciadorMensagemDAO.class);
	
	public GerenciadorMensagemDAO(Session session){
		this.session = session;		
	}
			
	@SuppressWarnings("unchecked")
	public List<GerenciadorMensagem> lista(){		
		Criteria criteria = this.session.createCriteria(GerenciadorMensagem.class);				
		return criteria.list();
	}
	
	public void salva(GerenciadorMensagem gerenciadorMensagem){		
		this.session.save(gerenciadorMensagem);
	}
	
	public void remove(GerenciadorMensagem gerenciadorMensagem){		
		this.session.delete(gerenciadorMensagem);
	}
	
	public GerenciadorMensagem carrega(Long id){	
		return (GerenciadorMensagem)this.session.get(GerenciadorMensagem.class, id);	
	}
	
	public GerenciadorMensagem carregaByTitle(String string){	
		Criteria criteria = this.session.createCriteria(GerenciadorMensagem.class);
		criteria.add(Restrictions.eq("title", string));
		
		return  (GerenciadorMensagem) criteria.uniqueResult();	
	}
	
	public void atualiza(GerenciadorMensagem preco){		
		this.session.update(preco);
	}
	
	public void merge(GerenciadorMensagem preco){		
		this.session.merge(preco);
	}	
}
