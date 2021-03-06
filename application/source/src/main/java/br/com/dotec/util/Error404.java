package br.com.dotec.util;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.resource.HttpMethod;
import br.com.caelum.vraptor.view.Results;
import br.com.caelum.vraptor.resource.DefaultResourceNotFoundHandler;

public class Error404 extends DefaultResourceNotFoundHandler {
	private final Router router;
	private final Result result;

	public Error404(Router router, Result result) {
		this.router = router;
		this.result = result;
	}

	@Override
	public void couldntFind(RequestInfo requestInfo) {
		try {
			String uri = requestInfo.getRequestedUri();
			if (uri.endsWith("/")) {

				tryMovePermanentlyTo(requestInfo, uri.substring(0,
						uri.length() - 1));
			} else {
				tryMovePermanentlyTo(requestInfo, uri + "/");
			}
		} catch (ResourceNotFoundException ex) {
			super.couldntFind(requestInfo);
		}
	}

	private void tryMovePermanentlyTo(RequestInfo requestInfo, String newUri) {
		router.parse(newUri, HttpMethod.of(requestInfo.getRequest()),
				requestInfo.getRequest());
		result.use(Results.http()).movedPermanentlyTo(newUri);
	}
}
