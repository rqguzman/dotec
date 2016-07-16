package br.com.dotec.controller;


import br.com.caelum.vraptor.Resource;
import br.com.dotec.infra.interceptor.Restrito;

@Resource
public class MainController {
	
	@Restrito
	public void main(){
		
	}
}
