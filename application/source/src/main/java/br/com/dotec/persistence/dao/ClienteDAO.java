package br.com.dotec.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Content;
import br.com.dotec.model.Usuario;

@Component
public class ClienteDAO {

	private Session session;

	public ClienteDAO(Session session) {
		this.session = session;
	}

	public void salva(Cliente cliente) {
		this.session.save(cliente);
	}

	public void remove(Cliente cliente) {
		this.session.delete(cliente);
	}

	public Cliente carrega(Long id) {
		return (Cliente) this.session.get(Cliente.class, id);
	}
	
	public Cliente carregaPorIdProprio(String idProprio){
		return (Cliente) session.createCriteria(Cliente.class)
		.add(Restrictions.eq("idProprio", idProprio))
		.uniqueResult();
	}

	public Cliente carrega(Usuario usuario) {
		Query query = (Query) this.session
				.createQuery("select c from Cliente as c where c.usuarios.id = :usuarioId");
		query.setDouble("usuarioId", usuario.getId());
		
//		return (Cliente) session.createCriteria(Cliente.class).add(Restrictions.eq("usuario", usuario)).uniqueResult();
return (Cliente) query.uniqueResult();
	}
	
	/**
	 * Carrega o cliente que estive com o status habilitado
	 * @param usuario
	 * @return
	 */
	public Cliente carregaClienteHabilitado(Usuario usuario) {
		Query query = (Query) this.session
				.createQuery("select c from Cliente as c where c.habilitado = 1 and c.usuarios.id = :usuarioId");
		query.setDouble("usuarioId", usuario.getId());
		return (Cliente) query.uniqueResult();
	}

	public void atualiza(Cliente cliente) {
		this.session.update(cliente);
	}

	public void merge(Cliente cliente) {
		this.session.merge(cliente);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> lista() {
		return this.session.createCriteria(Cliente.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listaComBoleto(){
		
		this.session.createCriteria(Cliente.class).list();
		Query query = (Query) this.session
				.createQuery("select c from Cliente as c, Boleto as b where c.id = b.cliente.id");
		
		return (List<Cliente>) query.list();
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Cliente> listaVencimento(Long dia) {
		this.session.createCriteria(Cliente.class).list();
		Query query = (Query) this.session
				.createQuery("select c from Cliente as c where c.diaDeVencimento = :diaVencimento");
		query.setParameter("diaVencimento", dia);
		return (List<Cliente>) query.list();
	}
	
	public boolean verificaClienteComMovimentacaoBoleto(Cliente cliente){
		String strQuery = "SELECT  cl " +
				"FROM Cliente as cl, " +
				"Caixa as c, " +
				"Movimentacao as m " +
				"WHERE c.movimentacoes.id = m.id " +
				"AND cl.elementos.id = c.id  " +
				"AND cl = :cliente " +
				"AND m.boletoGerado = 1";
											
		Query query = (Query) this.session.createQuery(strQuery);
		
		query.setParameter("cliente", cliente);				
		
		Cliente cliente2 = (Cliente) query.uniqueResult();
		if(cliente2 != null)		
			return true;
		else
			return false;
	}
}