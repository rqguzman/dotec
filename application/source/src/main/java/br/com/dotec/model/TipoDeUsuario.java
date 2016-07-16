package br.com.dotec.model;

public enum TipoDeUsuario {

	ADMINISTRADOR("Administrador"), PRIMARIO("Prim�rio"), SECUNDARIO("Secund�rio");

	private final String nome;

	TipoDeUsuario(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
