package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

import br.com.caelum.stella.bean.validation.CPF;



@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
public class ClientePessoaFisica extends Cliente {
	
	@NotEmpty(message = "{validator.notEmpty}")
	@CPF
	private String cpf;
	
	@NotEmpty(message = "{validator.notEmpty}")
	private String rg;
	
	@NotNull(message = "{validator.notEmpty}")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")  
	private LocalDate dataDeNascimento;
	
	@NotNull(message = "{validator.notEmpty}")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public String getDocumento() {
		return getCpf();
	}

	@Override
	public TipoDePessoa getTipoDePessoa() {
		return TipoDePessoa.PF;
	}

}
