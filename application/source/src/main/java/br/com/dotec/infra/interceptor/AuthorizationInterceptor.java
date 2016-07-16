/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.dotec.infra.interceptor;

import java.util.Arrays;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.dotec.controller.AdminController;
import br.com.dotec.model.Autenticavel;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.util.DotecException;

/**
 * Interceptor to check if the user is in the session.
 */
@Intercepts(after = AuthenticationInterceptor.class)
public class AuthorizationInterceptor implements Interceptor {
	private final UserInfo currentUser;
	private final Result result;

	static Logger logger = Logger.getLogger(AuthorizationInterceptor.class);

	public AuthorizationInterceptor(UserInfo currentUser, Result result) {
		this.currentUser = currentUser;
		this.result = result;

	}

	/**
	 * the easiest way to implement the accepts method is creating an annotation
	 */
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Restrito.class);
	}

	/**
	 * Intercepts the request and checks if there is a user logged in.
	 */
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		try {
			Restrito restrito = method.getMethod()
					.getAnnotation(Restrito.class);
			boolean possuiAcesso = false;
			TipoDeUsuario[] tiposDeUsuario = restrito.value();

			if (tiposDeUsuario.length == 0) {
				possuiAcesso = true;
			} else {

				for (TipoDeUsuario tipoDeUsuario : tiposDeUsuario) {
					if (tipoDeUsuario == currentUser.getUser()
							.getTipoDeUsuario())
						possuiAcesso = true;
				}

			}
			if (possuiAcesso) {
				stack.next(method, resourceInstance);
			} else {
				result.include("errors", Arrays.asList(new ValidationMessage(
						"Usuário não possui acesso ao recurso solicitado!",
						"Usuário")));
				result.redirectTo(AdminController.class).login();
			}
		} catch (Exception e) {
			try {
				throw new DotecException(e);
			} catch (DotecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.error(e);
			result.redirectTo(AdminController.class).login();
		}
	}
}