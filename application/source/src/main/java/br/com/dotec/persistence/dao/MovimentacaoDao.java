package br.com.dotec.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.DateTime;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.TipoDeMovimentacao;
import br.com.dotec.model.Usuario;
import br.com.dotec.util.MovimentacoesPorDia;

@Component
public class MovimentacaoDao {
	private Session session;
	static Logger logger = Logger.getLogger(MovimentacaoDao.class);
	
	public MovimentacaoDao(Session session){
		this.session = session;		
	}
	
	public void salva(Movimentacao mov){		
		this.session.save(mov);		
	}
	
	public void remove(Movimentacao objtect){
		this.session.delete(objtect);
	}	
		
	@SuppressWarnings("unchecked")
	public List<Movimentacao> lista(Cliente cliente,Movimentacao movimentacao){
		String strQuery = "select m from Movimentacao as m, Elemento as e, Cliente as cl Where e.movimentacoes.id = m.id and cl.elementos.id = e.id  ";
		Long AllDates = -2208977612000L;
		DateTime last30days = DateTime.now().plusDays(-30);
		
		if(cliente!=null){
			strQuery += " and cl = :cliente ";
		}
		
		if(movimentacao!=null){
			if(movimentacao.getTipoDeMovimentacao() != null){
				strQuery += " and m.tipoDeMovimentacao = :tipoDeMovimentacao ";
			}
			if(movimentacao.getStatusDaMovimentacao() != null){
				strQuery += " and m.statusDaMovimentacao = :statusDaMovimentacao ";
			}
			if(movimentacao.getDataDeCriacao() != null && movimentacao.getDataDeCriacao().getMillis()!=AllDates){
				strQuery += " and m.dataDeCriacao > :dataDeCriacao ";
			}
		}else{
			strQuery += " and m.dataDeCriacao > :dataDeCriacao ";
		}
		
		strQuery += " order by m.id desc ";
				
		Query query = (Query) this.session.createQuery(strQuery);
		if(cliente!=null){
			query.setParameter("cliente", cliente);
		}
		if(movimentacao==null){
			query.setParameter("dataDeCriacao",last30days);
		}else{
			if(movimentacao.getTipoDeMovimentacao() != null){
				query.setParameter("tipoDeMovimentacao", movimentacao.getTipoDeMovimentacao());
			}
			if(movimentacao.getStatusDaMovimentacao() != null){
				query.setParameter("statusDaMovimentacao", movimentacao.getStatusDaMovimentacao());
			}
			if(movimentacao.getDataDeCriacao() != null && movimentacao.getDataDeCriacao().getMillis()!=AllDates){
				query.setParameter("dataDeCriacao", movimentacao.getDataDeCriacao());
			}
		}
		
		return query.list();		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Movimentacao> lista(Cliente cliente){
		String strQuery = "select m from Movimentacao as m, " +
				"Elemento as e, " +
				"Cliente as cl " +
				"Where e.movimentacoes.id = m.id " +
				"and cl.elementos.id = e.id  ";
		
		
		if(cliente!=null){
			strQuery += " and cl = :cliente ";
		}
							
		Query query = (Query) this.session.createQuery(strQuery);
		if(cliente!=null){
			query.setParameter("cliente", cliente);
		}
		
		strQuery += " order by m.id desc ";
		
		return query.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimentacao> lista(Usuario usuario){
		String strQuery = "select m from Movimentacao as m, " +
				"Elemento as e " +				
				"Where e.movimentacoes.id = m.id and m.criadaPor= :criadoPor";
										
		Query query = (Query) this.session.createQuery(strQuery);		
		query.setLong("criadoPor", usuario.getId());
		
		strQuery += " order by m.id desc ";
		
		return query.list();		
	}
		
	@SuppressWarnings("unchecked")
	public List<Movimentacao> lista(){
		Query query = (Query) this.session.createQuery("select m from Movimentacao");
		return query.list();		
	}
		
	@SuppressWarnings("unchecked")
	public List<Movimentacao> listaMovimentacaoSemBoleto(Long clienteId){
		
		String strQuery = " SELECT m " +
						  " FROM Movimentacao as m,  Elemento as e, Cliente as cl " + 
		      			  " WHERE e.movimentacoes.id = m.id " +
		      			  " AND cl.elementos.id = e.id " +
		      			  " AND cl.id = :clienteId " +
		      			  " AND m.boletoGerado = :boletoGerado" +
		      			  " AND m.statusDaMovimentacao = :statusDaMovimentacao " +
		      			  " ORDER BY m.dataDeCriacao asc";
		
		Query query = (Query) this.session.createQuery(strQuery);
		query.setLong("clienteId",clienteId);
		query.setInteger("boletoGerado", 0);
		query.setParameter("statusDaMovimentacao", StatusDaMovimentacao.EXECUTADA);
		
		return query.list();				
	}
	
	@SuppressWarnings("unchecked")
	public List<MovimentacoesPorDia> listaMovimentacaoSemBoletoAgrupadoPorData(Long clienteId){
						
		String strQuery = " SELECT new br.com.dotec.util.MovimentacoesPorDia(m.dataDeCriacao, count(m.dataDeCriacao) as total, m.tipoDeMovimentacao)" +
						  " FROM Movimentacao as m,  Elemento as e, Cliente as cl " + 
		     			  " WHERE e.movimentacoes.id = m.id " +
		     			  " AND cl.elementos.id = e.id " +
		     			  " AND cl.id = :clienteId " +
		     			  " AND m.boletoGerado = :boletoGerado" +
		     			  " AND m.statusDaMovimentacao = :statusDaMovimentacao " +
		     			  " AND m.tipoDeMovimentacao <>:tipoDeMovimentacao " +		     			  		     			 
		      			  " GROUP BY m.dataDeCriacao ";
		
		Query query = (Query) this.session.createQuery(strQuery);
		query.setLong("clienteId", clienteId);
		query.setInteger("boletoGerado", 0);
		query.setParameter("statusDaMovimentacao", StatusDaMovimentacao.EXECUTADA);
		query.setParameter("tipoDeMovimentacao", TipoDeMovimentacao.NOVA_CAIXA);
		
		return  query.list();				
	}
	
	
		
	public Movimentacao carrega(Long id){
		return (Movimentacao)this.session.load(Movimentacao.class,id);	
	}
	
	public Movimentacao carregaByIdProprio(String idProprio){
		Query query = (Query) this.session.createQuery("select c from Movimentacao as c, BoletoItem bi, Boleto b where c.id = bi.movimentacao.id and b.itens.id=bi.id and b.idProprio= :idProprio");
		query.setString("idProprio",idProprio);
		return (Movimentacao) query.uniqueResult();
	}
	
	public void altera(Movimentacao object){
		this.session.update(object);
	}		
}
