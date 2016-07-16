package br.com.dotec.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Elemento {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "{validator.notEmpty}")
	private String nome;
	private String descricao;
	@ManyToOne(cascade = { CascadeType.ALL })
	private Elemento elementoPai;
	@NotNull(message = "{validator.notEmpty}")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime criadoEm = DateTime.now();
	@NotNull(message = "{validator.notEmpty}")
	@ManyToOne(cascade = { CascadeType.MERGE })
	private Usuario criadoPor;
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
	private StatusDeLocalizacao statusDeLocalizacao = StatusDeLocalizacao.DOTEC;
	private boolean excluido;
	
	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public StatusDeLocalizacao getStatusDeLocalizacao() {
		return statusDeLocalizacao;
	}

	public void setStatusDeLocalizacao(StatusDeLocalizacao statusDeLocalizacao) {
		this.statusDeLocalizacao = statusDeLocalizacao;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Elemento getElementoPai() {
		return elementoPai;
	}

	public void setElementoPai(Elemento elementoPai) {
		this.elementoPai = elementoPai;
	}

	public DateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(DateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public abstract TipoDeElemento getTipoDeElemento();

	public boolean equals(Object object) {
		Elemento outroElemento = (Elemento) object;
		if (this.getId() == outroElemento.getId()) {
			return true;
		}
		return false;
	}
	
	public List<Elemento> getElementosAscendentes(){
		List<Elemento> elementosAscendentes = new ArrayList<Elemento>();
		Elemento elementoPai = this.getElementoPai();
		while(elementoPai != null){
			elementosAscendentes.add(elementoPai);
			elementoPai = elementoPai.getElementoPai();
		}
		return elementosAscendentes;
	}
	

}
