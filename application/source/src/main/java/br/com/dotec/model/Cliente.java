package br.com.dotec.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public abstract class Cliente {
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "{validator.notEmpty}")
	private String nome;

	@Email
	@NotEmpty(message = "{validator.notEmpty}")
	private String email;

	@NotNull(message = "{validator.notEmpty}")
	@OneToOne(cascade = { CascadeType.ALL })
	private Telefone telefone;

	@OneToMany(cascade = CascadeType.ALL)
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<Usuario> usuarios;

	@NotNull(message = "{validator.notEmpty}")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime criadoEm = DateTime.now();

	@ManyToOne(cascade = { CascadeType.MERGE })
	private Usuario criadaPor;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Elemento> elementos;

	@NotNull(message = "{validator.notEmpty}")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Endereco> enderecos;

	@NotNull(message = "{validator.notEmpty}")
	private Long diaDeVencimento;
	
	@Column(columnDefinition = "bit(1) default 0")
	private boolean guardaTecnica = false;

	@Column(columnDefinition = "bit(1) default 0")
	private boolean habilitado;

	@NotNull(message = "{validator.notEmpty}")
	@Column(columnDefinition = "bit(1) default 0")
	private boolean contrato;

	private String idProprio;
	
	private boolean inativo = false;

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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public DateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(DateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Usuario getCriadaPor() {
		return criadaPor;
	}

	public void setCriadaPor(Usuario criadaPor) {
		this.criadaPor = criadaPor;
	}

	public List<Elemento> getElementos() {
		return this.elementos;
	}
	
	
	public List<Elemento> getElementos(boolean incluirElementosRemovidos) {
		if(incluirElementosRemovidos){
			return this.elementos;
		}else{
			List<Elemento> elementos = new ArrayList<Elemento>();
			for (Elemento elemento : this.elementos) {
				if(!elemento.isExcluido()){
					elementos.add(elemento);
				}
			} 
			return elementos;
		}
	}

	public void setElementos(List<Elemento> elementos) {
		this.elementos = elementos;
	}

	public abstract String getDocumento();

	public abstract TipoDePessoa getTipoDePessoa();

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public boolean possuiEnderecoDeCobranca() {
		boolean possuiEnderecoDeCobranca = false;
		for (Endereco endereco : this.getEnderecos()) {
			if (endereco.isEnderecoDeCobranca()) {
				possuiEnderecoDeCobranca = true;
				break;
			}
		}
		return possuiEnderecoDeCobranca;
	}

	public boolean possuiEnderecoDeEntrega() {
		boolean possuiEnderecoDeEntrega = false;
		for (Endereco endereco : this.getEnderecos()) {
			if (endereco.isEnderecoDeEntrega()) {
				possuiEnderecoDeEntrega = true;
				break;
			}
		}
		return possuiEnderecoDeEntrega;
	}

	public Long getDiaDeVencimento() {
		return diaDeVencimento;
	}

	public void setDiaDeVencimento(Long diaDeVencimento) {
		this.diaDeVencimento = diaDeVencimento;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public boolean isContrato() {
		return contrato;
	}

	public void setContrato(boolean contrato) {
		this.contrato = contrato;
	}

	public String getIdProprio() {
		return idProprio;
	}

	public void setIdProprio(String idProprio) {
		this.idProprio = idProprio;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public boolean isGuardaTecnica() {
		return guardaTecnica;
	}

	public void setGuardaTecnica(boolean guardaTecnica) {
		this.guardaTecnica = guardaTecnica;
	}

	public boolean equals(Cliente cliente) {
		if (this.getId() == cliente.getId()) {
			return true;
		} else {
			return false;
		}
	}

}
