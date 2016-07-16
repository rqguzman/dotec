package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class ErrorSystemDotec {

	@Id
	@GeneratedValue
	private Long id;

	private String message;

	private String classe;
	private String msgamigavel;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime date = DateTime.now();

	public ErrorSystemDotec(String msgamigavel) {
		this.msgamigavel = msgamigavel;
	}

	public ErrorSystemDotec(String msgamigavel, String message, String classe) {
		this.message = message;
		this.classe = classe;
		this.msgamigavel = msgamigavel;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getMsgamigavel() {
		return msgamigavel;
	}

	public void setMsgamigavel(String msgamigavel) {
		this.msgamigavel = msgamigavel;
	}

}
