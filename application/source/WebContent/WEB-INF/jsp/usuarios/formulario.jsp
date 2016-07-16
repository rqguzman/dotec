<%@ include file="/header-admin.jsp" %>
<%@ include file="/menu-admin.jsp" %> <script type="text/javascript" src="<c:url value="/client/js/usuariosFormulario.js"/>"></script>		
    		<div class="padding">
      			<div id="toolbar-box">
        			<div class="t">
          				<div class="t">
            				<div class="t"></div>
          				</div>
        			</div>
        			<div class="m">
          				<div class="toolbar-list" id="toolbar">
            				<ul>              
              					<li class="button" id="toolbar-save"> <a href="javascript:$('#formularioDeUsuario').submit()" onClick="" class="toolbar"> <span class="icon-32-save"> </span> Salvar &amp; Fechar </a> </li>              
              					<li class="button" id="toolbar-cancel"> <a href="<c:url value="/admin/usuarios/lista" />" class="toolbar"> <span class="icon-32-cancel"> </span> Cancelar </a> </li>
              					<li class="divider"> </li>
              					<li class="button" id="toolbar-help"> <a href="#" onClick="popupWindow('http://help.joomla.org/proxy/index.php?option=com_help&amp;keyref=Help16:Components_Weblinks_Categories_Edit', 'Ajuda', 700, 500, 1)" rel="help" class="toolbar"> <span class="icon-32-help"> </span> Ajuda </a> </li>
            				</ul>
            				<div class="clr"></div>
          				</div>
          				<div class="pagetitle icon-48-category-add icon-48-weblinks-category-add">
            			<h2>Gerenciador de Usuários: <c:choose><c:when test='${usuario.id == null}'>Adicionar</c:when><c:otherwise>Editar</c:otherwise></c:choose> Usuário</h2>
          			</div>
          			<div class="clr"></div>
        		</div>
        		<div class="b">
          			<div class="b">
            			<div class="b"></div>
          			</div>
        		</div>
      		</div>
      		<div class="clr"></div>
      		<div id="element-box">
        	<div class="t">
          		<div class="t">
            		<div class="t"></div>
          		</div>
        	</div>
        	<div class="m">                               
         		<form id="formularioDeUsuario"  <c:choose><c:when test='${usuario.id == null}'>action="adiciona"</c:when><c:otherwise>action="altera"</c:otherwise></c:choose> method="post" >
					<div class="width-60 fltlft">
					<input type="hidden" name="usuario.id"  value="${usuario.id}" />								
					<fieldset class="adminform">
						<legend>Dados Pessoais</legend>
						<ul class="adminformlist"	>
							<li><dotec:campoTexto label="Nome:" id="usuario.nome" value="${usuario.nome}" classe="inputbox" size="30"></dotec:campoTexto></li>
							<li><dotec:campoCPF label="CPF:" id="usuario.cpf" name="usuario.cpf" value="${usuario.cpf}" classe="inputbox"></dotec:campoCPF></li>
							<li><dotec:campoRG label="RG:" id="usuario.rg" value="${usuario.rg}" classe="inputbox" ></dotec:campoRG></li>				
							<li>
								<joda:format value="${usuario.dataDeNascimento}" pattern="dd/MM/yyyy" var="dataDeNascimento"  />
								<dotec:campoData id="usuario.dataDeNascimento"  label="Data de Nascimento:" value="${dataDeNascimento}"></dotec:campoData>
							</li>
							<li>
								<label for="usuario.sexo" class="hasTip required">Sexo:<span class="star">&#160;*</span></label>
								<select name="usuario.sexo" id="usuario.sexo">
									<option value=""></option>
									<c:forEach items="${sexoList}" var="sexo">
										<option value="${sexo}" <c:if test="${usuario.sexo==sexo}">selected="selected"</c:if> >${sexo.nome}</option>
									</c:forEach>
								</select>
							</li>
						</ul>				
					</fieldset>
					<fieldset class="adminform">
						<legend>Dados Acesso</legend>
						<ul>
							<li><dotec:campoEmail label="E-mail:" id="usuario.email" value="${usuario.email}"></dotec:campoEmail></li>				
											
							<li><dotec:campoSenha label="Senha:" id="usuario.senha"></dotec:campoSenha></li>
						</ul>
						<br /><br /><br />
						<input type="submit" />
					</fieldset>										<dotec:formularioDeLoginModal formId="formularioDeUsuario"></dotec:formularioDeLoginModal>                                                                                                             
					<div class="clr"></div>
					<dotec:campoObrigatorio></dotec:campoObrigatorio>
					</div>
				</form>
            	</div>
	           	<div class="width-40 fltrt">
	             		<div id="categories-sliders-" class="pane-sliders">
	               		<div style="display:none;">
	                  		<div> 
	                  	</div>
	                </div>
              	</div>
              
            </div>
            <div class="clr"></div>
     	
        <div class="clr"></div>
   	</div>
	<div class="b">
	  <div class="b">
	    <div class="b"></div>
	  </div>
    </div>
   </div>
      <noscript>
      Atenção! JavaScript deve estar habilitado para o bom funcionamento do back-end do administrador.
      </noscript>
      <div class="clr"></div>
    



<%@ include file="/footer-admin.jsp" %>