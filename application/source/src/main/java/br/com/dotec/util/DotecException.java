package br.com.dotec.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.dotec.model.ErrorSystemDotec;
import br.com.dotec.persistence.dao.ErrorSystemDotecDAO;
import br.com.dotec.util.manager.ManagerFactory;
import br.com.dotec.util.properties.ApplicationProperties;

public class DotecException extends Exception {
	
	private static final long serialVersionUID = 1;

	private static Logger logger = Logger.getLogger(DotecException.class);	
	private String emailFrom = ApplicationProperties.getInstance().getMailLogin();	
	private String name = ApplicationProperties.getInstance().getMailName();
	public DotecException(String message) 
	{		
		super(message);	
		
		try {
			sendEmailError(message, null, emailFrom, name);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * 
	 * @param e
	 */
	public DotecException(Exception e)
	{
		super(e);		
		try {
			sendEmailError(null, e, emailFrom, name);
		} catch (EmailException e1) {
			logger.error(e);
		} catch (Exception e1) {
			logger.error(e);
		}
		
	}
	
	public DotecException(String message, Exception e) 
	{		
		super(message, e);
		
		logger.error(e);
	}
			
	@Override
	public String toString()
	{
		String className = getClass().getName();
		String message = getMessage();
		return (message != null) ? (className + ": " + message) : className;
	}	
	
	
	private void sendEmailError(String msgamigavel, Exception ex,
			String emailFrom, String name) throws EmailException,
			DotecException {
		HtmlEmail email = new HtmlEmail();
		email.setFrom(emailFrom, name);
		email.addTo("raphaelcabralnet@gmail.com", "Rapahel Cabral");
		email.addTo("jeolcavaco@hotmail.com", "Jorge Eduardo");
		
		email.setSubject("Dotec - Erro no Sistema");
		StringBuffer body = new StringBuffer();
		body.append("Error no sistema Dotec");
		body.append("<p>"+msgamigavel+"</p>");
		if(ex != null){
		
		body.append("<p>"+ex.getMessage()+"</p>");
		body.append("<p>"+ex.getStackTrace().toString()+"</p>");
		body.append("<p>"+ex.getClass().getName()+"</p>");
		}
		ManagerFactory.getSendMailManager().sendMail(email, body.toString());
	}	
}
