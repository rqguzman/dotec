package br.com.dotec.model;

public enum TipoDeMovimentacao {
	NOVA_CAIXA("Nova caixa"),
	SOLICITACAO("Solicitação"),
	DEVOLUCAO("Devolução"),
	NOVO_CLIENTE("Novo cliente");

	private final String nome;

	TipoDeMovimentacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
