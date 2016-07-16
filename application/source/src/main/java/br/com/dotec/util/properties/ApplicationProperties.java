package br.com.dotec.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ApplicationProperties {
	private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
	
	private static Properties properties;

	public static ApplicationProperties instance;

	private ApplicationProperties() {
		super();
	}

	public static ApplicationProperties getInstance() {
		if (instance == null) {
			instance = new ApplicationProperties();
		}
		return instance;
	}
	
	
	private Properties getProperties() {
		if (properties == null) {
			try {
				properties = new Properties();

				String path = System.getProperty("dotec.properties");
				logger.debug("path de configuracao: " + path);

				if (path == null) {
					properties.load(getPackageInputStream());
				} else {
					logger.info("carregando configurações do ambiente: " + path);

					File file = new File(path);
					if (!file.exists()) {
						logger.error("arquivo de configuração não existe: "
								+ path);
					} else {
						properties.load(new FileInputStream(file));
					}
					
				}
				
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return properties;
	}
	
	private InputStream getPackageInputStream() {
		logger.info("carregando configurações do pacote");
		//logger.info("use -Dsitemap.properties=sitemap.properties -Dconfiguration.display=true -jar dist/r7-sitemaps_v0.0.1.jar");
		return ApplicationProperties.class.getClassLoader()
				.getResourceAsStream("dotec.properties");
	}
	
	private String getProperty(String property) {
		return getProperties().getProperty(property);
	}
	
	public String getMailSmtp() {
		return getProperty("mail.smtp");
	}
	
	public String getMailLogin() {
		return getProperty("mail.login");
	}
	
	public String getMailPassword() {
		return getProperty("mail.password");
	}
	
	public String getMailName() {
		return getProperty("mail.name");
	}
	
	
	
	public String getSiteUrl(){
		return getProperty("site.url");
	}
	
	public String getSubjectDefault(){
		return getProperty("mail.subject");
	}
	
	public String getEmailComercial(){
		return getProperty("mail.comercial");
	}
	public String getEmailFaleconosco(){
		return getProperty("mail.faleconosco");
	}
	public String getEmailPagamento(){
		return getProperty("mail.pagamento");
	}
	
	public String getEmailAtendimento(){
		return getProperty("mail.atendimento");
	}
	
	public String getMoipUrl(){
		return getProperty("moip.url");
	}
	public String getMoipToken(){
		return getProperty("moip.token");
	}
	
	public String getMoipKey(){
		return getProperty("moip.key");
	}
	
	public Double getValorCadastroCliente(){
		return Double.parseDouble(getProperty("valor.cadastro.cliente"));
	}
	
	/*public Double getValorCadastroNovaCaixa(){
		return Double.parseDouble(getProperty("valor.nova.caixa"));
	}*/
	
	public String getMoipUrlBoleto(){
		return getProperty("moip.url.boleto");
	}
	
	
	public String getMailMessageBodyCadastroCliente(){
		return getProperty("mail.message.body.cadastro.cliente");
	}
	public String getMailMessageBodyBoletoGerado(){
		return getProperty("mail.message.body.boleto.gerado");
	}
	public String getMailMessageSubjectBoletoGerado(){
		return getProperty("mail.message.subject.boleto.gerado");
	}
	
	public String getMailMessageSubjectCadastroCliente(){
		return getProperty("mail.message.subject.cadastro.cliente");
	}
	
	public String getMailMessageBodyFaleconosco(){
		return getProperty("mail.message.body.faleconosco");
	}
	public String getMailMessageSubjectFaleconosco(){
		return getProperty("mail.message.subject.faleconosco");
	}
	
	public String getMailMessageBodyFaleconoscoFrom(){
		return getProperty("mail.message.body.faleconosco.from");
	}
	
	public String getMailMessageBodyForgot(){
		return getProperty("mail.message.body.forgot");
	}
	
	public String getMailMessageSubjectForgot(){
		return getProperty("mail.message.subject.forgot");
	}
	
	public String getMailMessageBodyPagamentoAtualizaStatus(){
		return getProperty("mail.message.body.pagamento.atualizacao.status");
	}
	
	public String getMailMessageBodyPagamentoEfetuado(){
		return getProperty("mail.message.body.pagamento.efetuado");
	}
	public String getMailMessageSubjectPagamentoEfetuado(){
		return getProperty("mail.message.subject.pagamento.efetuado");
	}
	
	public String getBoletoDataVencimento10(){
		return getProperty("boleto.data.vencimento10");
	}
	
	public String getBoletoDataVencimento15(){
		return getProperty("boleto.data.vencimento15");
	}
	
	public String getBoletoDataVencimento20(){
		return getProperty("boleto.data.vencimento20");
	}
	
	
	public String getFileUploadMaxFileSize(){
		return getProperty("file.upload.maxfilesize");
	}
	
	public String getAwsS3AccessKey(){
		return getProperty("aws.s3.accesskey");
	}
	
	public String getAwsS3SecretKey(){
		return getProperty("aws.s3.secretkey");
	}
	
	
	
	
	
}
