package br.com.dotec.util.jobs;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.util.DotecException;
import br.com.dotec.util.HibernateUtil;
import br.com.dotec.util.manager.ManagerFactory;
import br.com.dotec.util.properties.ApplicationProperties;

@Component
@ApplicationScoped
public class TaskSendEmail implements ApplicationTask {

	static Logger logger = Logger.getLogger(TaskSendEmail.class);

	public TaskSendEmail(TaskScheduler scheduler) {
		this.schedule(scheduler);
	}

	public void schedule(TaskScheduler scheduler) {
		scheduler.schedule(this, new CronTrigger("0 0 12 * * ?"));
	}

	public void run() {
		logger.info("in run");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {																
			Transaction transaction = session.beginTransaction();
								
			DateTime d = DateTime.now();
			
			int diaAtual = d.getDayOfMonth();				
			int primeiroVencimento = Integer.parseInt(ApplicationProperties.getInstance().getBoletoDataVencimento10());
			int segundoVencimento = Integer.parseInt(ApplicationProperties.getInstance().getBoletoDataVencimento15());
			int terceiroVencimento = Integer.parseInt(ApplicationProperties.getInstance().getBoletoDataVencimento20());			
			int vencimento = 0;
			
			if (d.getDayOfMonth() == primeiroVencimento) 
				vencimento = 10;
			else if (d.getDayOfMonth() == segundoVencimento) 
				vencimento = 15;
			else if (d.getDayOfMonth() == terceiroVencimento)
				vencimento = 20;
								
			if(diaAtual == primeiroVencimento || diaAtual == segundoVencimento || diaAtual == terceiroVencimento){			
				List<Cliente> clientes = ManagerFactory.getManagerMoip().findCustomerWithDateGenerationBank(vencimento, session);
				
				for (Cliente cliente : clientes) 
				{														
					int differenceDay =  Days.daysBetween(cliente.getCriadoEm() , DateTime.now() ).getDays();
					
					if(differenceDay > 29)
					{
						List<Movimentacao> movs = ManagerFactory.getManagerMoip().findMovementOpenByCustomer(cliente, session) ;																						
						ManagerFactory.getManagerMoip().createBank(movs, cliente, 0 , session);						
					}								
				}
			}
			logger.info("end run");
			transaction.commit();					
		} 
		catch (DotecException e) {
			logger.error(e);				
		}
		finally{			
			session.close();			
		}
	}	
}