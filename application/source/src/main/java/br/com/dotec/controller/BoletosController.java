package br.com.dotec.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.dotec.infra.interceptor.Publico;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Boleto;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.persistence.dao.BoletoDAO;
import br.com.dotec.persistence.dao.ClienteDAO;

@Resource
public class BoletosController {
	private final BoletoDAO boletoDAO;	
	private final Result result;
	private final ClienteDAO clienteDAO;
	private final UserInfo userInfo;
	
	public BoletosController(BoletoDAO boletoDAO,
			 Result result,
			 UserInfo userInfo,
			 ClienteDAO clienteDAO) {
		super();
		this.boletoDAO = boletoDAO;		
		this.clienteDAO = clienteDAO;
		this.result = result;	
		this.userInfo = userInfo;
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value={"/admin/boletos/lista"}, priority=1)
	public List<Boleto> lista(Boleto boleto,Integer pago) {											
		List<Cliente> clientes = clienteDAO.listaComBoleto();
				
		Cliente cliente = null;
		
		if(userInfo.getCliente()!=null)
			cliente = clienteDAO.carrega(userInfo.getCliente().getId());
		
		if(boleto!=null && pago != null){
			boleto.setPago(pago==1);
		}
				
		List<Boleto> boletos = boletoDAO.lista(cliente, boleto);
		
		result.include("clienteList", clientes);
		result.include("boleto", boleto);
		result.include("pago", pago);
		return boletos;
	}
	
	@Restrito({ TipoDeUsuario.ADMINISTRADOR, TipoDeUsuario.PRIMARIO })
	@Path(value={"/admin/boletos/listaBoletos"}, priority=1)
	public List<Boleto> listaBoletos() {				
		Cliente cliente = clienteDAO.carrega(userInfo.getUser());				
		List<Cliente> clientes = new ArrayList<Cliente>();		
		List<Boleto> boletos = boletoDAO.lista(cliente, null);
				
		result.include("clientes", clientes);
		
		return boletos;
	}
	
	@Publico
	public List<Boleto> listaBoletosEmAberto(){
		
		Cliente cliente = null;
		
		if(userInfo.getCliente() != null)
			cliente = userInfo.getCliente();
			
		List<Boleto> boletos = null;
		if(cliente != null)
			boletos = boletoDAO.listaBoletosEmAberto(cliente.getId());
		
		return boletos;
	}
}