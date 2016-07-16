package br.com.dotec.util.moip;

import java.util.List;

import org.hibernate.Session;

import br.com.dotec.model.Boleto;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.util.DotecException;

public interface IManagerMoip {
	
	/**
	 * 
	 * @param session
	 * @return
	 * @throws DotecException
	 */
	public List<Cliente> findCustomerWithDateGenerationBank(int diaVencimento, Session session) throws DotecException;
	
	/**
	 * 
	 * @param cliente
	 * @param session
	 * @return
	 * @throws DotecException
	 */
	public List<Movimentacao> findMovementOpenByCustomer(Cliente cliente, Session session) throws DotecException;
	
	/**
	 * 
	 * @param movs
	 * @param cliente
	 * @param diaVencimento
	 * @param session
	 * @return
	 * @throws DotecException
	 */
	public Boleto createBank(List<Movimentacao> movs, Cliente cliente, int diaVencimento, Session session) throws DotecException;
}
