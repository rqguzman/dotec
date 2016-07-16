package br.com.dotec.model;

public enum StatusDeLocalizacao {
	CLIENTE("Cliente"),DOTEC("Dotec");
	
	private final String nome;

	StatusDeLocalizacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
