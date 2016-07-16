package br.com.dotec.model;

public enum TipoPagamento {

	DEBITO_BANCARIO("DebitoBancario")
	,FINANCIAMENTO_BANCARIO("FinanciamentoBancario")
	,BOLETO_BANCARIO("BoletoBancario")
	,CARTAO_CREDITO("CartaoDeCredito")
	,CARTAO_DEBITO("CartaoDeDebito")
	,CARTEIRA_MOIP("CarteiraMoIP")
	,NAODEFINIDA("NaoDefinida");
	
	@SuppressWarnings("unused")
	private final String nome;
	 
	TipoPagamento(String nome){
		
		this.nome = nome;
	}
}
