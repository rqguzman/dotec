package br.com.dotec.infra.interceptor;

import java.util.List;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Usuario;
import br.com.dotec.util.node.Node;

@Component
@SessionScoped
public class UserInfo {

    private Usuario user;
    private Cliente cliente;
    private List<Node> nodes;
    private String moipUrl;
    private UploadedFile uploadedFile;

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public Usuario getUser() {
        return user;
    }

    public void login(Usuario user) {
        this.user = user;
    }

    public void logout() {
        this.user = null;
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	public String getMoipUrl() {
		return moipUrl;
	}

	public void setMoipUrl(String moipUrl) {
		this.moipUrl = moipUrl;
	}

	public void limparCache(){
		this.setNodes(null);
		this.moipUrl = null;
	}
}