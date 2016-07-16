package br.com.dotec.persistence.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.ClientePessoaJuridica;

@Component
public class ClientePessoaJuridicaDAO {

	private Session session;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Caixa> caixas;

	public ClientePessoaJuridicaDAO(Session session) {
		this.session = session;
	}

	public void salva(ClientePessoaJuridica objtect) {
		this.session.save(objtect);
	}

	public void remove(ClientePessoaJuridica objtect) {
		this.session.delete(objtect);
	}

	public ClientePessoaJuridica carrega(Long id) {
		return (ClientePessoaJuridica) this.session.get(
				ClientePessoaJuridica.class, id);

	}

	public void atualiza(ClientePessoaJuridica objtect) {
		this.session.update(objtect);
	}
	
	public void merge(ClientePessoaJuridica objtect) {
		this.session.merge(objtect);
	}

	@SuppressWarnings("unchecked")
	public List<ClientePessoaJuridica> lista() {
		return this.session.createCriteria(ClientePessoaJuridica.class).list();
	}

	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}

}
