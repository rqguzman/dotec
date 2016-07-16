package br.com.dotec.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Movimentacao {
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "{validator.notEmpty}")
	private TipoDeMovimentacao tipoDeMovimentacao;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "{validator.notEmpty}")
	private StatusDaMovimentacao statusDaMovimentacao;

	@NotNull(message = "{validator.notEmpty}")
	@ManyToOne(cascade = { CascadeType.ALL })
	private Usuario criadaPor;

	@NotNull(message = "{validator.notEmpty}")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime dataDeCriacao = DateTime.now();

	private Double valor;

	private boolean boletoGerado = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDeMovimentacao getTipoDeMovimentacao() {
		return tipoDeMovimentacao;
	}

	public void setTipoDeMovimentacao(TipoDeMovimentacao tipoDeMovimentacao) {
		this.tipoDeMovimentacao = tipoDeMovimentacao;
	}

	public StatusDaMovimentacao getStatusDaMovimentacao() {
		return statusDaMovimentacao;
	}

	public void setStatusDaMovimentacao(
			StatusDaMovimentacao statusDaMovimentacao) {
		this.statusDaMovimentacao = statusDaMovimentacao;
	}

	public Usuario getCriadaPor() {
		return criadaPor;
	}

	public void setCriadaPor(Usuario criadaPor) {
		this.criadaPor = criadaPor;
	}

	public DateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(DateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isBoletoGerado() {
		return boletoGerado;
	}

	public void setBoletoGerado(boolean boletoGerado) {
		this.boletoGerado = boletoGerado;
	}

}
