package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.dotec.util.Md5;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@SessionScoped
@Component
public class Usuario extends PessoaFisica {
	/**
	 * 
	 */

	@Id
	@GeneratedValue
	private Long id;
	
	
	
	@NotEmpty(message = "{validator.notEmpty}")
	private String senha;
	
	@NotEmpty(message = "{validator.notEmpty}")
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private TipoDeUsuario tipoDeUsuario;
	
	@Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
	private LocalDate criadoEm;

	public LocalDate getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDate criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Md5.md5(senha);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

}
