package br.com.dotec.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.Elemento;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.TipoDeElemento;
import br.com.dotec.model.TipoDeMovimentacao;

@Component
public class ElementoDAO {

	private Session session;

	public ElementoDAO(Session session) {
		this.session = session;
	}

	public void salva(Elemento objtect) {
		this.session.save(objtect);
	}

	public void remove(Elemento object) {
		this.session.delete(object);
	}

	public Elemento carrega(Long id) {
		return (Elemento) this.session.get(Elemento.class, id);
	}
	
	public Elemento carrega(Movimentacao movimentacao){
		Query query = (Query) this.session.createQuery("select e from Elemento as e Where movimentacoes.id = :movimentacaoId");
		query.setParameter("movimentacaoId",movimentacao.getId());
		Elemento elemento = (Elemento) query.setMaxResults(1).uniqueResult();
		return elemento;	
	}

	public void atualiza(Elemento object) {
		this.session.update(object);
	}

	public void merge(Elemento object) {
		this.session.merge(object);
	}

	@SuppressWarnings("unchecked")
	public List<Elemento> lista() {
		return this.session.createCriteria(Elemento.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Elemento> getElementosFilhos(Elemento elementoPai) {
		Query query = (Query) this.session
				.createQuery("select e from Elemento as e where e.elementoPai = :elementoPai");
		query.setParameter("elementoPai", elementoPai);
		return (List<Elemento>) query.list();
	}

	@SuppressWarnings("unchecked")
	public boolean possuiElementosFilhos(Elemento elementoPai) {
		Query query = (Query) this.session
				.createQuery("select e from Elemento as e where e.elementoPai = :elementoPai");
		query.setParameter("elementoPai", elementoPai);
		List<Elemento> elementosFilhos = (List<Elemento>) query.list();
		return elementosFilhos.size() > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Elemento> getElementosDescendentes(Elemento elemento) {
		List<Elemento> elementosDescendentes = new ArrayList<Elemento>();
		List<Elemento> elementosFilhos = getElementosFilhos(elemento);
		elementosDescendentes.addAll(elementosFilhos);
		for (Elemento elementoFilho : elementosFilhos) {
			elementosDescendentes
					.addAll(getElementosDescendentes(elementoFilho));
		}
		return elementosDescendentes;
	}
	
	@SuppressWarnings("unchecked")
	public boolean possuiElementoDescendenteComMovimentacaoPendente(Elemento elemento) {
		List<Elemento> elementosDescendentes = new ArrayList<Elemento>();
		elementosDescendentes = this.getElementosDescendentes(elemento);
		for (Elemento elementoDescendente : elementosDescendentes) {
			Movimentacao ultimaMovimentacao = this.getUltimaMovimentacao(elementoDescendente); 
			if(ultimaMovimentacao != null){
				if(ultimaMovimentacao.getStatusDaMovimentacao() == StatusDaMovimentacao.PENDENTE || ultimaMovimentacao.getStatusDaMovimentacao() == StatusDaMovimentacao.EM_ANDAMENTO){
					return true;
				}
			}
			
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean possuiElementoAscendenteComMovimentacaoPendente(Elemento elemento) {
		List<Elemento> elementosAscendentes = new ArrayList<Elemento>();
		elementosAscendentes = elemento.getElementosAscendentes();
		for (Elemento elementoAscendente : elementosAscendentes) {
			Movimentacao ultimaMovimentacao = this.getUltimaMovimentacao(elementoAscendente); 
			if(ultimaMovimentacao != null){
				if(ultimaMovimentacao.getStatusDaMovimentacao() == StatusDaMovimentacao.PENDENTE || ultimaMovimentacao.getStatusDaMovimentacao() == StatusDaMovimentacao.EM_ANDAMENTO){
					return true;
				}
			}
			
		}
		return false;
	}
	
	

	@SuppressWarnings("unchecked")
	public boolean possuiCaixaDescendente(Elemento elemento) {
		boolean possuiCaixaDescendente = false;
		List<Elemento> elementosFilhos = getElementosFilhos(elemento);
		for (Elemento elementoFilho : elementosFilhos) {
			if (elementoFilho.getTipoDeElemento() == TipoDeElemento.CAIXA) {
				possuiCaixaDescendente = true;
			}
			if (possuiCaixaDescendente(elementoFilho)) {
				possuiCaixaDescendente = true;
			}
			if (possuiCaixaDescendente) {
				break;
			}
		}
		return possuiCaixaDescendente;
	}
	
	
	public boolean possuiMovimentacaoDeDevolucaoPendente(Elemento elemento){
		Query query = (Query) this.session.createQuery("select m from Movimentacao as m, Elemento as e Where e.id =:elementoId and e.movimentacoes.id = m.id and m.statusDaMovimentacao=:statusDaMovimentacao and m.tipoDeMovimentacao=:tipoDeMovimentacao");
		query.setParameter("elementoId",elemento.getId());
		query.setParameter("tipoDeMovimentacao", TipoDeMovimentacao.DEVOLUCAO);
		query.setParameter("statusDaMovimentacao", StatusDaMovimentacao.PENDENTE);
		Movimentacao movimentacao = (Movimentacao) query.setMaxResults(1).uniqueResult();
		return movimentacao != null;
	}
	
	
	public Movimentacao getUltimaMovimentacao(Elemento elemento){
		Query query = (Query) this.session.createQuery("select m from Movimentacao as m, Elemento as e Where e.id =:elementoId and e.movimentacoes.id = m.id order by m.dataDeCriacao desc");
		query.setParameter("elementoId",elemento.getId());
		return (Movimentacao) query.setMaxResults(1).uniqueResult();
	}
	
	
	
	

}