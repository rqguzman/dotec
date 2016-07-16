package br.com.dotec.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Boleto {
	@Id @GeneratedValue private Long id;

	@NotNull(message = "{validator.notEmpty}") private int mesReferencia;

	@NotNull(message = "{validator.notEmpty}") private int anoReferencia;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime") private DateTime datavencimento;

	@NotNull(message = "{validator.notEmpty}") private double valor;

	private String Descricao;

	@NotNull(message = "{validator.notEmpty}") private String token;

	@Column(columnDefinition = "bit(1) default 0") private boolean pago;

	@NotNull(message = "{validator.notEmpty}") private String idProprio;

	@OneToMany(cascade = CascadeType.ALL) private List<BoletoItem> itens;

	private String codigoMoip;
	private int formaDePagamento;
	private int statusPagamento;
	private String tipoPagamento;
	
	@OneToOne(cascade = CascadeType.ALL) private Cliente cliente;
	
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public int getMesReferencia() {	return mesReferencia; }

	public void setMesReferencia(int mesReferencia) { this.mesReferencia = mesReferencia; }

	public int getAnoReferencia() { return anoReferencia; }

	public void setAnoReferencia(int anoReferencia) { this.anoReferencia = anoReferencia;}

	public DateTime getDatavencimento() { return datavencimento; }

	public void setDatavencimento(DateTime datavencimento) { this.datavencimento = datavencimento; }

	public double getValor() { return valor; }

	public void setValor(double valor) { this.valor = valor; }

	public String getDescricao() { return Descricao; }

	public void setDescricao(String descricao) { Descricao = descricao; }

	public String getToken() { return token; }

	public void setToken(String token) { this.token = token; }

	public boolean isPago() { return pago; }

	public void setPago(boolean pago) {	this.pago = pago;}

	public List<BoletoItem> getItens() { return itens;}

	public void setItens(List<BoletoItem> itens) {	this.itens = itens; }

	public String getIdProprio() {	return idProprio;	}

	public void setIdProprio(String idProprio) 
	{
		if (id==null)
		{
			DateTime d = DateTime.now();
			idProprio = String.valueOf(d.getYear()) + "" + String.valueOf(d.getMonthOfYear()) + ""	+ String.valueOf(d.getDayOfMonth()) + "" + String.valueOf(d.getHourOfDay()) + "" + String.valueOf(d.getMinuteOfHour()) + "" +String.valueOf( d.getSecondOfMinute())+""+String.valueOf(d.getMillis());					
		}
		
		this.idProprio = idProprio;
	}

	public String getCodigoMoip() { return codigoMoip;	}

	public void setCodigoMoip(String codigoMoip) { this.codigoMoip = codigoMoip; }

	public int getFormaDePagamento() { return formaDePagamento; }

	public void setFormaDePagamento(int formaDePagamento) {	this.formaDePagamento = formaDePagamento; }

	public int getStatusPagamento() { return statusPagamento; }

	public void setStatusPagamento(int statusPagamento) {this.statusPagamento = statusPagamento; }

	public String getTipoPagamento() {	return tipoPagamento;}

	public void setTipoPagamento(String tipoPagamento) {this.tipoPagamento = tipoPagamento; }

	public Cliente getCliente() {return cliente;}

	public void setCliente(Cliente cliente) {this.cliente = cliente;}

}
