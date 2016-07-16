package br.com.dotec.persistence.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.TipoDeMovimentacao;

@Component
public class CaixaDAO {
	
private Session session;
	
	public CaixaDAO(Session session){
		this.session = session;
	}
	
	public void salva(Caixa objtect){
		this.session.save(objtect);
	}
	
	public void remove(Caixa object){
		this.session.delete(object);
	}
	
	public Caixa carrega(Long id){
		return (Caixa)this.session.get(Caixa.class, id);	
	}
	
	public Caixa carrega(Movimentacao movimentacao){
		Query query = (Query) this.session.createQuery("select c from Caixa as c Where movimentacoes.id = :movimentacaoId");
		query.setParameter("movimentacaoId",movimentacao.getId());
		Caixa caixa = (Caixa) query.setMaxResults(1).uniqueResult();
		return caixa;	
	}
	
	public void atualiza(Caixa object){
		this.session.update(object);
	}
	
	public void merge(Caixa object){
		this.session.merge(object);
	}

	@SuppressWarnings("unchecked")
	public List<Caixa> lista(){
		return this.session.createCriteria(Caixa.class).list();
	}
	
	
	public boolean possuiMovimentacaoDeDevolucaoPendente(Caixa caixa){
		Query query = (Query) this.session.createQuery("select m from Movimentacao as m, Caixa as c Where c.id =:caixaId and c.movimentacoes.id = m.id and m.statusDaMovimentacao=:statusDaMovimentacao and m.tipoDeMovimentacao=:tipoDeMovimentacao");
		query.setParameter("caixaId",caixa.getId());
		query.setParameter("tipoDeMovimentacao", TipoDeMovimentacao.DEVOLUCAO);
		query.setParameter("statusDaMovimentacao", StatusDaMovimentacao.PENDENTE);
		Movimentacao movimentacao = (Movimentacao) query.setMaxResults(1).uniqueResult();
		return movimentacao != null;
	}
	
	
	public Movimentacao getUltimaMovimentacao(Caixa caixa){
		Query query = (Query) this.session.createQuery("select m from Movimentacao as m, Caixa as c Where c.id =:caixaId and c.movimentacoes.id = m.id order by m.dataDeCriacao desc");
		query.setParameter("caixaId",caixa.getId());
		return (Movimentacao) query.setMaxResults(1).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Caixa> getCaixaByCliente(Cliente cliente){		
		String strQuery = " SELECT c " +
						  " FROM Caixa as c, Cliente as cl " + 
		      			  " WHERE cl.elementos.id = c.id " +
		      			  " AND cl.id = :clienteId order by c.criadoEm asc";
		
		Query query = (Query) this.session.createQuery(strQuery);
		query.setLong("clienteId",cliente.getId());
						
		return query.list();				
	}
}