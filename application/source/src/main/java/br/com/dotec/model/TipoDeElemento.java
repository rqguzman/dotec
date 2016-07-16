package br.com.dotec.model;

public enum TipoDeElemento {
	PASTA("Pasta"),CAIXA("Caixa"),DOCUMENTO("Documento"), ENVELOPE("Envelope");

	private final String nome;

	TipoDeElemento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
