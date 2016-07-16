package br.com.dotec.model;

public enum TipoDeMovimentacao {
	NOVA_CAIXA("Nova caixa"),
	SOLICITACAO("Solicita��o"),
	DEVOLUCAO("Devolu��o"),
	NOVO_CLIENTE("Novo cliente");

	private final String nome;

	TipoDeMovimentacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
