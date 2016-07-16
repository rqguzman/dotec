package br.com.dotec.persistence.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.Envelope;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.TipoDeMovimentacao;

@Component
public class EnvelopeDAO {
	private Session session;

	public EnvelopeDAO(Session session) {
		this.session = session;
	}

	public void salva(Envelope objtect) {
		this.session.save(objtect);
	}

	public void remove(Envelope objtect) {
		this.session.delete(objtect);
	}

	public void altera(Envelope object) {
		this.session.update(object);
	}
	
	public void merge(Envelope object){
		this.session.merge(object);
	}


	public Envelope carrega(Long id) {
		return (Envelope) this.session.load(Envelope.class, id);
	}
	
	public boolean possuiMovimentacaoDeDevolucaoPendente(Envelope envelope){
		Query query = (Query) this.session.createQuery("select m from Movimentacao as m, Envelope as e Where e.id =:envelopeId and c.movimentacoes.id = m.id and m.statusDaMovimentacao=:statusDaMovimentacao and m.tipoDeMovimentacao=:tipoDeMovimentacao");
		query.setParameter("envelopeId",envelope.getId());
		query.setParameter("tipoDeMovimentacao", TipoDeMovimentacao.DEVOLUCAO);
		query.setParameter("statusDaMovimentacao", StatusDaMovimentacao.PENDENTE);
		Movimentacao movimentacao = (Movimentacao) query.setMaxResults(1).uniqueResult();
		return movimentacao != null;
	}
	
	
	public Movimentacao getUltimaMovimentacao(Envelope envelope){
		Query query = (Query) this.session.createQuery("select m from Movimentacao as m, Envelope as e Where e.id =:envelopeId and c.movimentacoes.id = m.id order by m.dataDeCriacao desc");
		query.setParameter("envelopeId",envelope.getId());
		return (Movimentacao) query.setMaxResults(1).uniqueResult();
	}
	
	
}
