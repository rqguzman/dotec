package br.com.dotec.util.sendmail;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import br.com.dotec.util.DotecException;
import br.com.dotec.util.properties.ApplicationProperties;

public class SendMailManager implements ISendMailManager
{
	static Logger logger =  Logger.getLogger(SendMailManager.class);
		
	public void sendMail(HtmlEmail email, String body) throws DotecException
	{		
		String smtp = (String)ApplicationProperties.getInstance().getMailSmtp();
		String login = (String)ApplicationProperties.getInstance().getMailLogin();
		String password = (String)ApplicationProperties.getInstance().getMailPassword();
						
		try 
		{			
			if(email.getSubject()==null){			
				String subject = (String)ApplicationProperties.getInstance().getSubjectDefault();
				email.setSubject(subject);
			}					
			email.setHostName(smtp);								
			email.setCharset("ISO-8859-1");			
			
			if(password != null)			
				email.setAuthenticator(new DefaultAuthenticator(login, password));
			
			logger.info(body);
			email.setHtmlMsg(createHTML(body));
			
			email.send();
			
		} 
		catch (EmailException e) 
		{
			logger.error(e);
			throw new DotecException("Erro ao enviar email", e);
		}
		catch (Exception e) 
		{
			logger.error(e);
			throw new DotecException("Erro ao enviar email", e);
		}
	}

	private String createHTML(String body) throws EmailException{
		
		StringBuffer buffer = new StringBuffer();
					
		buffer.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		buffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"pt\" lang=\"pt\" dir=\"ltr\">");
		buffer.append("<head>");
		buffer.append("<title>Dotec.com.br</title>");
		buffer.append("<meta http-equiv=\"Content-Language\" content=\"pt\" />");
		buffer.append("</head>");
		buffer.append("<body>");
		buffer.append(body.toString());
		
		
		buffer.append("</body>");
		buffer.append("</html>");
					
		return buffer.toString();
	}
}
