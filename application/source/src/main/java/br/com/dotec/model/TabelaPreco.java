package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TabelaPreco {

	@Id @GeneratedValue	private Long id;
	@NotEmpty(message = "{validator.notEmpty}") private String title;		
	private Double price;	
	private int ordering;
	private String category;
	
	public Long getId() { return id;}

	public void setId(Long id) { this.id = id;	}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public Double getPrice() {return price;}

	public void setPrice(Double price) {this.price = price;}

	public int getOrdering() {return ordering;}

	public void setOrdering(int ordering) {	this.ordering = ordering;}

	public String getCategory() {return category;}

	public void setCategory(String category) {	this.category = category;}	
}