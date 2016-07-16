package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class GerenciadorMensagem {
	@Id @GeneratedValue	private Long id;
	@NotEmpty(message = "{validator.notEmpty}") private String title;	
	@NotEmpty(message = "{validator.notEmpty}") private String subject;
	@NotEmpty(message = "{validator.notEmpty}") @Type(type = "text") private String body;
	@NotEmpty(message = "{validator.notEmpty}") @Email private String emailFrom;
	
	
	public Long getId() { return id; }
	public void setId(Long id) {this.id = id;}
	public String getTitle() {	return title; }
	public void setTitle(String title) {this.title = title;}
	public String getSubject() {return subject;	}
	public void setSubject(String subject) {this.subject = subject;	}
	public String getBody() {return body;}
	public void setBody(String body) {	this.body = body;}
	public String getEmailFrom() {	return emailFrom;}
	public void setEmailFrom(String emailFrom) {this.emailFrom = emailFrom;}
		
}