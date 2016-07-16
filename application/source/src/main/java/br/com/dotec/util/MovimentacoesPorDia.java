package br.com.dotec.util;

import org.joda.time.DateTime;

import br.com.dotec.model.TipoDeMovimentacao;



public class MovimentacoesPorDia {	
	private DateTime dateTime;	
	private Long total;
	private TipoDeMovimentacao categoria;
		
	public MovimentacoesPorDia(DateTime dateTime, Long total, TipoDeMovimentacao categoria) {
		super();
		this.dateTime = dateTime;
		this.total = total;
		this.categoria = categoria;
	}
	
	public DateTime getDateTime() { return dateTime; }
	public void setDateTime(DateTime dateTime) { this.dateTime = dateTime; }
	public Long getTotal() { return total; }
	public void setTotal(Long total) { this.total = total; }
	public TipoDeMovimentacao getCategoria() { return categoria; }
	public void setCategoria(TipoDeMovimentacao categoria) { this.categoria = categoria; }	
	
}
