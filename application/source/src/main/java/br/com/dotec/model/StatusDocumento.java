package br.com.dotec.model;

public enum StatusDocumento {

	SOLICITADA("Solicitada");
	

	private final String nome;

	StatusDocumento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
