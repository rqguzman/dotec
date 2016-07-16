package br.com.dotec.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.interceptor.UserInfo;

@Resource
public class UploadController {
	private final UserInfo userInfo;
	private final Result result;
	private final Validator validator;

	public UploadController(UserInfo userInfo, Result result,Validator validator) {
		this.userInfo = userInfo;
		this.result = result;
		this.validator = validator;
	}

	// @Restrito
	public void upload(UploadedFile uploadedFile) {
		if (!uploadedFile.getContentType().toString().equals("application/pdf")) {
			throw new RuntimeException(
					"Só é permitido o upload de arquivos PDF");
		}
		
		userInfo.setUploadedFile(uploadedFile);
		result.use(Results.json()).withoutRoot().from(true).serialize();
	}

}
