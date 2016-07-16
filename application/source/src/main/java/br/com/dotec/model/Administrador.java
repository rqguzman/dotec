package br.com.dotec.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Administrador extends PessoaFisica implements Autenticavel {
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario credencial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario getCredencial() {
		return credencial;
	}

	public void setCredencial(Usuario credencial) {
		this.credencial = credencial;
	}

	public boolean autentica() {
		return false;
	}

}
