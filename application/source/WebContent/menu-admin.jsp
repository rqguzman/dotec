<div id="header-box">
	<div id="module-menu">
		<ul id="menu">
		   <li class="node">
				<a href="#">Site</a>
				<ul>
					<li><a class="icon-16-cpanel" href="<c:url value="/" />">Voltar ao Site</a></li>
					<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">
						<li class="separator"><span></span></li>		
						<li><a class="icon-16-config" href="<c:url value="/admin/contents/lista" />">Conteúdo</a></li>
						<li class="separator"><span></span></li>		
						<li><a class="icon-16-content" href="<c:url value="/admin/entrega/lista" />">Cep de Entrega</a></li>						<li class="separator"><span></span></li>								<li><a class="icon-16-maintenance" href="<c:url value="/admin/tabelapreco/lista" />">Tabela de Preços</a></li>						<li class="separator"><span></span></li>								<li><a class="icon-16-newcategory" href="<c:url value="/admin/gerenciadormensagem/lista" />">Gerenciador de Mensagens do Sistema</a></li>
					</c:if>
					<li class="separator"><span></span></li>
					<li><a class="icon-16-logout" href="<c:url value="/logout" />">Sair</a></li>
				</ul>
			</li>
			<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR' || userInfo.user.tipoDeUsuario=='PRIMARIO'}">	
				<li class="node">
					<a href="#">Usuários</a>
					<ul>
						<li>
							<a class="icon-16-user" href="<c:url value="/admin/usuarios/lista" /> ">Gerenciador de Usuários</a>
						</li>			
					</ul>
				</li>
			</c:if>
			<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">		
				<li class="node">
					<a href="#">Clientes</a>
					<ul>
						<li>
							<a class="icon-16-groups" 	href="<c:url value="/admin/clientes/lista" />">Gerenciador de Clientes</a>
						</li>
					</ul>
				</li>
			</c:if>
			<c:if test="${userInfo.user.tipoDeUsuario=='PRIMARIO' || userInfo.user.tipoDeUsuario=='SECUNDARIO'}">	
				<li class="node">
					<a href="#">Caixas</a>
					<ul>
						<li>
							<a href="<c:url value="/elementos/lista" />" >Minhas Caixas</a>
						</li>
					</ul>
				</li>							</c:if>
								<c:if test="${userInfo.user.tipoDeUsuario!='SECUNDARIO'}">	
				<li class="node">
					<a href="#">Financeiro</a>
					<ul>
						<li>
							<a class="icon-16-article"	href="<c:url value="/movimentacaos/lista" />" >Movimentações</a>						
						</li>
						<li>
							<a class="icon-16-article"	href="<c:url value="/admin/boletos/lista" />" >Boletos</a>						
						</li>							
					</ul>
				</li>							</c:if>								<c:if test="${userInfo.user.tipoDeUsuario=='PRIMARIO'}">									<li class="node">					<a href="#">Cadastro</a>					<ul>						<li>							<a href="<c:url value="/admin/clientes/edita?id=${userInfo.cliente.id}" />" >Minhas Informações Cadastrais</a>						</li>					</ul>				</li>
			</c:if>							<c:if test="${userInfo.user.tipoDeUsuario=='SECUNDARIO'}">									<li class="node">					<a href="#">Cadastro</a>					<ul>						<li>							<a href="<c:url value="/admin/usuarios/edita?id=${userInfo.user.id}" />" >Minhas Informações Cadastrais</a>						</li>					</ul>				</li>			</c:if>										<li class="node">				<a href='javascript:trocarSenha()'>Trocar Senha</a>			</li>										
		</ul>
 	</div>
  	<div id="module-status">   
  		
  		<span class="backloggedin-users">Olá ${userInfo.cliente.nome} - <%=br.com.dotec.util.SessionCounter.getActiveSessions() %> usuário na administração</span>
  		<span class="logout"><a href="<c:url value="/admin/logout" />">Sair</a></span> 
  	</div>
  	<div class="clr"></div>
</div>

<div id="content-box">
  <div class="border">
 
 
<%@ include file="/error.jsp" %>

<div id="boletosEmAberto"></div><dotec:formularioDeTrocaDeSenha></dotec:formularioDeTrocaDeSenha>

