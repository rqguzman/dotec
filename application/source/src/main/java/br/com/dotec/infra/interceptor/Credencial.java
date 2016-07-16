package br.com.dotec.infra.interceptor;

import br.com.dotec.util.Md5;

public class Credencial {
	private String login;
	private String senha;
	
	public boolean equals(Credencial credencial) {
		return this.login == credencial.getLogin() && this.senha == credencial.getSenha();
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Md5.md5(senha);
		;
	}

}
