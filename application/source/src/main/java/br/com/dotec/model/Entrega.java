package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Entrega {
	@Id
	@GeneratedValue
	private Long id;
	private String cidade;
	private Long cepInicio;
	private Long cepFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getCepInicio() {
		return cepInicio;
	}

	public void setCepInicio(Long cepInicio) {
		this.cepInicio = cepInicio;
	}

	public Long getCepFim() {
		return cepFim;
	}

	public void setCepFim(Long cepFim) {
		this.cepFim = cepFim;
	}

}
