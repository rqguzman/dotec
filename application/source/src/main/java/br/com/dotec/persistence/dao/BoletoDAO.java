package br.com.dotec.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Boleto;
import br.com.dotec.model.Cliente;

@Component
public class BoletoDAO {
	private Session session;

	public BoletoDAO(Session session) {
		this.session = session;
	}

	public Boleto getByIdproprio(String idProprio) {
		Query query = (Query) this.session
				.createQuery("select b from Boleto as b where b.idProprio = :idProprio");
		query.setString("idProprio", idProprio);
		return (Boleto) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Boleto> lista(Cliente cliente, Boleto boleto) {		
		String strQuery = "select b from Boleto as b  " ;		
		String strCriteria = "";
		
		String strWhere = "";
		
		if(cliente !=null || boleto !=null)
			strWhere = " WHERE ";
										
		if(cliente != null)
			strCriteria +=" b.cliente = :cliente ";	
		
		if(boleto!=null){
		
			if (boleto.isPago())
				strCriteria += (strCriteria.equals("")) ?  " b.pago = 1 " :  " AND b.pago = 1 ";
			else
				strCriteria += (strCriteria.equals("")) ?  " b.pago = 0 " :  " AND b.pago = 0 ";
		
			if(boleto.getMesReferencia()!=0){
				strCriteria += (strCriteria.equals("")) ?  " b.mesReferencia = :mesReferencia " :  " AND b.mesReferencia = :mesReferencia ";			
			}
			
			if(boleto.getAnoReferencia()!=0){
				strCriteria += (strCriteria.equals("")) ?  " b.anoReferencia = :anoReferencia " :  " AND b.anoReferencia = :anoReferencia ";			
			}		
		}
		
		Query query = (Query) this.session.createQuery(strQuery + strWhere + strCriteria);

		if(cliente != null){
			query.setParameter("cliente", cliente);
		}
		
		if(boleto != null)
		{
			if(boleto.getMesReferencia()!=0)
				query.setParameter("mesReferencia", boleto.getMesReferencia());
			
			if(boleto.getAnoReferencia()!=0)
				query.setParameter("anoReferencia", boleto.getAnoReferencia());
			
		}
				
		return query.list();
	}
	
	public void salva(Boleto boleto) {
		this.session.save(boleto);
	}

	public void atualiza(Boleto boleto) {
		this.session.update(boleto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Boleto> listaBoletosEmAberto(Long id) {
		Query query = (Query) this.session
		.createQuery("select b from Boleto as b Where  b.pago=0 and b.cliente.id = :clienteId");
		query.setLong("clienteId", id);
			
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Boleto> listaBoletosByClienteID(Cliente cliente){
		
		this.session.createCriteria(Boleto.class).list();
		Query query = (Query) this.session
				.createQuery("select  b from Boleto as b  where b.cliente = :cliente");
		
		query.setParameter("cliente", cliente);
		return (List<Boleto>) query.list();
	}
}
