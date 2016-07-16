package br.com.dotec.util.manager;

import org.apache.log4j.Logger;

import br.com.dotec.util.moip.IManagerMoip;
import br.com.dotec.util.moip.ManagerMoip;
import br.com.dotec.util.sendmail.ISendMailManager;
import br.com.dotec.util.sendmail.SendMailManager;

public  abstract class ManagerFactory {
	
	static Logger logger = Logger.getLogger(ManagerFactory.class);
	
	private static ISendMailManager sendMailManager = new SendMailManager();
	
	private static IManagerMoip managerMoip = new ManagerMoip();
	
	
	public static ISendMailManager getSendMailManager(){
		return sendMailManager;
	}
	
	
	public static IManagerMoip getManagerMoip(){
		return managerMoip;
	}
}
