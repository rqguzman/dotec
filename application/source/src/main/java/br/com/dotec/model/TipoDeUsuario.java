package br.com.dotec.model;

public enum TipoDeUsuario {

	ADMINISTRADOR("Administrador"), PRIMARIO("Primário"), SECUNDARIO("Secundário");

	private final String nome;

	TipoDeUsuario(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
