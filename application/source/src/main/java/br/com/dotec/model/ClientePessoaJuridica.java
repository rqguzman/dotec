package br.com.dotec.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.stella.bean.validation.CNPJ;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cnpj"))
public class ClientePessoaJuridica extends Cliente{
	
	@NotEmpty(message = "{validator.notEmpty}")
	@CNPJ
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String getDocumento() {
		return getCnpj();
	}

	@Override
	public TipoDePessoa getTipoDePessoa() {
		return TipoDePessoa.PJ;
	}

}
