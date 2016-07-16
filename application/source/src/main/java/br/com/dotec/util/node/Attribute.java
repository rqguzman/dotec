package br.com.dotec.util.node;

import br.com.dotec.model.StatusDeLocalizacao;
import br.com.dotec.model.StatusDaMovimentacao;

public class Attribute {
	private Long id;
	private NodeType rel;
	private StatusDeLocalizacao statusDaCaixa;
	private StatusDaMovimentacao statusDaMovimentacao;
	private boolean elementoDescendenteComMovimentacaoPendente;
	private boolean elementoAscendenteComMovimentacaoPendente;
	
	public boolean isElementoDescendenteComMovimentacaoPendente() {
		return elementoDescendenteComMovimentacaoPendente;
	}

	public void setElementoDescendenteComMovimentacaoPendente(
			boolean elementoDescendenteComMovimentacaoPendente) {
		this.elementoDescendenteComMovimentacaoPendente = elementoDescendenteComMovimentacaoPendente;
	}

	public boolean isElementoAscendenteComMovimentacaoPendente() {
		return elementoAscendenteComMovimentacaoPendente;
	}

	public void setElementoAscendenteComMovimentacaoPendente(
			boolean elementoAscendenteComMovimentacaoPendente) {
		this.elementoAscendenteComMovimentacaoPendente = elementoAscendenteComMovimentacaoPendente;
	}

	public StatusDaMovimentacao getStatusDaMovimentacao() {
		return statusDaMovimentacao;
	}

	public void setStatusDaMovimentacao(
			StatusDaMovimentacao statusDaMovimentacao) {
		this.statusDaMovimentacao = statusDaMovimentacao;
	}

	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusDeLocalizacao getStatusDaCaixa() {
		return statusDaCaixa;
	}

	public void setStatusDaCaixa(StatusDeLocalizacao statusDaCaixa) {
		this.statusDaCaixa = statusDaCaixa;
	}

	public NodeType getRel() {
		return rel;
	}

	public void setRel(NodeType rel) {
		this.rel = rel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
