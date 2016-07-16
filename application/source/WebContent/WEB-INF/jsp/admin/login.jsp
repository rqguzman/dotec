<%@ include file="/header-login.jsp" %>

	<div id="content-box">
		<div class="padding">
			<div class="clr"></div>
			<div id="element-box" class="login">
				<div class="t">
					<div class="t">
						<div class="t"></div>
					</div>
				</div>
				<div class="m wbg">
					<h1>Sistema DoTec Login</h1>					
					<div id="section-box">
						<div class="t">
							<div class="t">
								<div class="t"></div>
							</div>
						</div>
						<div class="m">
							<form id="login-form" action="<c:url value="/admin/autentica" />" method="post">
								<fieldset class="loginform">
									<label id="mod-login-username-lbl" for="mod-login-username">Usuário</label>
									<input name="credencial.login" id="mod-login-username" type="text" class="inputbox" size="15" />
									<label id="mod-login-password-lbl" for="mod-login-password">Senha</label>
									<input name="credencial.senha" id="mod-login-password" type="password" class="inputbox" size="15" />
									<br/><br/><br/><br/><br/><br/>		
									<div class="button-holder">
										<div class="button1">
											<div class="next">
												<a href="#" onclick="$('#login-form').submit();">Logar</a>
											</div>
										</div>
									</div>	
									<div class="clr"></div>
								</fieldset>	
							</form>	
							<div class="clr"></div>
						</div>
						<div class="b">
							<div class="b">
								<div class="b"></div>
							</div>
						</div>
					</div>		
					<p>Use login/senha para acessar a àrea administrativa.</p>
					<p><a href="<c:url value="../"></c:url>">Ir a página inicial.</a></p>
					<div id="lock"></div>
					<div class="clr"></div>
				</div>
				<div class="b">
					<div class="b">
						<div class="b"></div>
					</div>
				</div>
			</div>
							
			<noscript>
				Warning! JavaScript must be enabled for proper operation of the Administrator backend.			</noscript>
			<div class="clr"></div>
			
			<div id="errors">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error.category } - ${error.message }</li>
					</c:forEach>
				</ul>
			</div>
			<div class="clr"></div>
		</div>
	</div>
	

<%@ include file="/footer-admin.jsp" %>	


