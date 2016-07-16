package br.com.dotec.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Content;
@Component
public class ContentDAO {
	private Session session;
		
	static Logger logger = Logger.getLogger(ContentDAO.class);
	
	public ContentDAO(Session session){
		this.session = session;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Content> get(String page ){
		return  session.createCriteria(Content.class).add(Restrictions.eq("page", page)).list();					
	}
	
	@SuppressWarnings("unchecked")
	public List<Content> listIsHomePage(){
		return this.session.createCriteria(Content.class)
		.add(Restrictions.eq("isHomePage", 1))
		.add(Restrictions.eq("status", 3))
		.setMaxResults(3)
		.addOrder(Order.asc("ordering")).list(); 
	}
		
	@SuppressWarnings("unchecked")
	public List<Content> lista(Content content){		
		Criteria contents = this.session.createCriteria(Content.class);
		
		if(content != null && !content.getTitle().equals("[Digite o título]"))			
			contents.add(Restrictions.like("title", "%"+content.getTitle()+"%"));	
		
		return contents.list();
	}
	
	public void salva(Content content){
		logger.info("Save item: " + content.getTitle());
		this.session.save(content);
	}
	
	public void remove(Content content){
		logger.info("Delete item: " + content.getTitle());
		this.session.delete(content);
	}
	
	public Content carrega(Long id){
		logger.info("Get item: " + id);
		return (Content)this.session.get(Content.class, id);	
	}
	
	public void atualiza(Content content){
		logger.info("Update item: " + content.getTitle() );
		this.session.update(content);
	}
	
	public void merge(Content content){
		logger.info("Merge item: " + content.getTitle());
		this.session.merge(content);
	}
}