package br.com.dotec.model;

public enum StatusDoEnvelope {
	CLIENTE("Cliente"),DOTEC("Dotec");
	
	private final String nome;

	StatusDoEnvelope(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
