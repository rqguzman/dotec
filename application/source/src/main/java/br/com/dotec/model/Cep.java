package br.com.dotec.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Cep {
	@NotNull(message = "{validator.notEmpty}")
	@OneToMany(cascade = { CascadeType.ALL })
	
	@Id
	@GeneratedValue
	private long id;
	private String cidade;
	private String logradouro;
	private String bairro;
	private String cep;	
	private String tp_logradouro;
	
	@Column(columnDefinition="bit(1) default 0")
	private boolean habilitado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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

	public String getTp_logradouro() {
		return tp_logradouro;
	}

	public void setTp_logradouro(String tpLogradouro) {
		tp_logradouro = tpLogradouro;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	
	
	
	
	
	

}
