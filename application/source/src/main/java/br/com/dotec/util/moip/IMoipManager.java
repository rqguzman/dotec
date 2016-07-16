package br.com.dotec.util.moip;

import java.util.List;

import org.hibernate.Session;

import br.com.dotec.model.Boleto;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;
import br.com.dotec.util.DotecException;

public interface IMoipManager {
	
	/**
	 * Gera boleto para o cadastro de clientes
	 * @param mov
	 * @param session
	 * @param cliente
	 * @param movimentacaoDao
	 * @param diaVencimento
	 * @return
	 * @throws DotecException
	 */
	public Boleto generateInvoice(List<Movimentacao> mov,  Session session,  Cliente cliente, MovimentacaoDao movimentacaoDao,  int diaVencimento) 	throws DotecException ;
	
	
}
