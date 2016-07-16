package br.com.dotec.model;

public enum TipoDePessoa {

	PF("Pessoa F�sica"), PJ("Pessoa Jur�dica");

	private final String nome;

	TipoDePessoa(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}

