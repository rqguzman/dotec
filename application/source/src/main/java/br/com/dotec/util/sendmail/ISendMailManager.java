package br.com.dotec.util.sendmail;

import org.apache.commons.mail.HtmlEmail;

import br.com.dotec.util.DotecException;

public interface ISendMailManager {
	
	public void sendMail(HtmlEmail email , String body) throws DotecException;
}
