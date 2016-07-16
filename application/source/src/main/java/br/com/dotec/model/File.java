package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class File {
	@Id
	private String id;

	@NotNull(message = "{validator.notEmpty}")
	private String name;

	@NotNull(message = "{validator.notEmpty}")
	private String contentType;
	
	@NotNull(message = "{validator.notEmpty}")
	private String path;

	public File() {
		this.id = java.util.UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getPath(){
		
		return  path;		
	}
	
public String setPath(String path){
		
		return  this.path = path;		
	}

}
