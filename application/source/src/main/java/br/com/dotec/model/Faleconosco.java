package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Faleconosco {
	@Id
	@GeneratedValue
	private Long id;
	
	private String protocolo;
	
	@NotNull(message = "{validator.notEmpty}")
	private String nome;
	
	@NotNull(message = "{validator.notEmpty}")
	private String email;
	
	@NotNull(message = "{validator.notEmpty}")
	private String telefone;
	
	@NotNull(message = "{validator.notEmpty}")
	@Type(type = "text")
	private String mensagem;
	
	@NotNull(message = "{validator.notEmpty}")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime dataEnvio = DateTime.now();

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public DateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(DateTime dataEnvio) {
		
		this.dataEnvio = dataEnvio;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

}
