package br.com.dotec.infra.interceptor;

import java.util.Arrays;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.dotec.controller.AdminController;

@Intercepts(before=AuthorizationInterceptor.class)
public class AuthenticationInterceptor implements Interceptor {
	private final UserInfo userInfo;
	private final Result result;
	

	public AuthenticationInterceptor(UserInfo info, Result result,
			Validator validator) {
		this.userInfo = info;
		this.result = result;

	}

	public boolean accepts(ResourceMethod method) {
		return !method.containsAnnotation(Publico.class);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		if (userInfo.getUser() == null) {
			result.include("errors", Arrays.asList(new ValidationMessage("Usuário não está logado!", "Usuário")));
			result.redirectTo(AdminController.class).login();
		} else {
			stack.next(method, resourceInstance);
		}
	}

}