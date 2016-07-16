package br.com.dotec.model;

public enum StatusDaMovimentacao {
	PENDENTE("Pendente"),EM_ANDAMENTO("Em andamento"), EXECUTADA("Executada"), CANCELADA("Cancelada");

	private final String nome;

	StatusDaMovimentacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
