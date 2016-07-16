package br.com.dotec.infra.interceptor;

import java.util.Arrays;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.dotec.controller.AdminController;
import br.com.dotec.infra.cache.Cache;
import br.com.dotec.model.TipoDeUsuario;

//@Intercepts(before = AuthorizationInterceptor.class, after = AuthenticationInterceptor.class)
public class InactiveClientInterceptor implements Interceptor {
	private final UserInfo userInfo;
	private final Result result;
	private final Cache cache;

	public InactiveClientInterceptor(UserInfo userInfo, Result result,
			Cache cache) {
		super();
		this.userInfo = userInfo;
		this.result = result;
		this.cache = cache;
	}

	public boolean accepts(ResourceMethod method) {
		return !method.containsAnnotation(Publico.class);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {

		if (userInfo.getUser().getTipoDeUsuario() != TipoDeUsuario.ADMINISTRADOR
				&& cache.isInativo(userInfo.getCliente())) {
			result.include("errors", Arrays.asList(new ValidationMessage(
					"Cliente inativado!", "Cliente")));
			result.redirectTo(AdminController.class).login();
		} else {
			stack.next(method, resourceInstance);
		}

	}

}