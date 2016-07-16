package br.com.dotec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Endereco {
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "{validator.notEmpty}")
	private TipoDeLogradouro tipoDeLogradouro;
	
	@NotEmpty(message = "{validator.notEmpty}")
	private String nomeDoLogrado;
	
	@NotNull(message = "{validator.notEmpty}")
	private int numero;
	private String complemento;
	
	@NotEmpty(message = "{validator.notEmpty}")
	private String bairro;
	
	@NotEmpty(message = "{validator.notEmpty}")
	private String cep;
	
	@NotEmpty(message = "{validator.notEmpty}")
	private String municipio;
	
	@NotNull(message = "{validator.notEmpty}")
	@Enumerated(EnumType.STRING)
	private Uf uf;
	
	private boolean enderecoDeCobranca;
	
	private boolean enderecoDeEntrega;
	
	@Column(columnDefinition = "text null")
	private String referencia;
		
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDeLogradouro getTipoDeLogradouro() {
		return tipoDeLogradouro;
	}

	public void setTipoDeLogradouro(TipoDeLogradouro tipoDeLogradouro) {
		this.tipoDeLogradouro = tipoDeLogradouro;
	}

	public String getNomeDoLogrado() {
		return nomeDoLogrado;
	}

	public void setNomeDoLogrado(String nomeDoLogrado) {
		this.nomeDoLogrado = nomeDoLogrado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isEnderecoDeCobranca() {
		return enderecoDeCobranca;
	}

	public void setEnderecoDeCobranca(boolean enderecoDeCobranca) {
		this.enderecoDeCobranca = enderecoDeCobranca;
	}

	public boolean isEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(boolean enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

}
