package br.com.dotec.model;

public enum TipoDePessoa {

	PF("Pessoa Física"), PJ("Pessoa Jurídica");

	private final String nome;

	TipoDePessoa(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}

