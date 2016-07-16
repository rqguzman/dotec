package br.com.dotec.util.node;

import java.util.ArrayList;
import java.util.List;

import br.com.dotec.model.Elemento;
import br.com.dotec.model.TipoDeElemento;

public class Node {
	private Data data;
	private Attribute attr;
	private NodeState state;
	private List<Node> children;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public NodeState getState() {
		return state;
	}

	public void setState(NodeState state) {
		this.state = state;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

}
