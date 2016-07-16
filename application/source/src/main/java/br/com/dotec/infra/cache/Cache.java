package br.com.dotec.infra.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.dotec.model.Cliente;
import br.com.dotec.util.node.Node;

@Component
@ApplicationScoped
public class Cache {
	private Map<Long, List<Node>> nodes = new HashMap<Long, List<Node>>();
	private Map<Long, Boolean> inativo = new HashMap<Long, Boolean>();

	public List<Node> getNodes(Cliente cliente) {
		return nodes.get(cliente.getId());
	}

	public void setNodes(Cliente cliente, List<Node> nodes) {
		this.nodes.put(cliente.getId(), nodes);
	}

	public void limparCache(Cliente cliente) {
		this.nodes.remove(cliente.getId());
	}

	public boolean isInativo(Cliente cliente) {
		return inativo.get(cliente.getId());
	}

	public void setInativo(Cliente cliente, Boolean inativo) {
		this.inativo.put(cliente.getId(), inativo);
	}
	
	public void setInativo( Boolean inativo) {
		this.inativo.put(null, inativo);
	}

}
