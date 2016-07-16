package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Content {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "{validator.notEmpty}")
	private String title;

	@NotNull(message = "{validator.notEmpty}")
	@Type(type = "text")
	private String content;

	@NotNull(message = "{validator.notEmpty}")
	private int status;

	@NotNull(message = "{validator.notEmpty}")
	private int isHomePage;

	@NotNull(message = "{validator.notEmpty}")
	private String page;

	@NotNull(message = "{validator.notEmpty}")
	private int ordering;

	private String css;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsHomePage() {
		return isHomePage;
	}

	public void setIsHomePage(int isHomePage) {
		this.isHomePage = isHomePage;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	
	
}
