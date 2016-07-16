package br.com.dotec.infra.file;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.dotec.util.properties.ApplicationProperties;

@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

    public long getSizeLimit() {
        return Integer.parseInt(ApplicationProperties.getInstance().getFileUploadMaxFileSize()) * 1024 * 1024; // 1 MB
    }

}