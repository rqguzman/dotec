<%@ include file="/header-admin.jsp" %>
<%@ include file="/menu-admin.jsp" %> 	
	<div class="padding">		<div id="toolbar-box">			<div class="t"><div class="t"><div class="t"></div></div></div>
   			<div class="m">   				<div class="toolbar-list" id="toolbar">       				<ul>                     					<li class="button" id="toolbar-save"> <a href="javascript:$('#formContent').submit()" onClick="" class="toolbar"> <span class="icon-32-save"> </span> Salvar &amp; Fechar </a> </li>                     					<li class="button" id="toolbar-cancel"> <a href="<c:url value="/admin/gerenciadormensagem/lista" />" class="toolbar"> <span class="icon-32-cancel"> </span> Cancelar </a> </li>       					<li class="divider"> </li>       					<li class="button" id="toolbar-help"> <a href="#" onClick="popupWindow('http://help.joomla.org/proxy/index.php?option=com_help&amp;keyref=Help16:Components_Weblinks_Categories_Edit', 'Ajuda', 700, 500, 1)" rel="help" class="toolbar"> <span class="icon-32-help"> </span> Ajuda </a> </li>       				</ul>       				<div class="clr"></div>   				</div>   				<div class="pagetitle icon-48-category-add icon-48-weblinks-category-add">         			<h2>Gerenciador de Pre�os: <c:choose><c:when test='${gerenciadorMensagem.id == null}'>Adicionar</c:when><c:otherwise>Editar</c:otherwise></c:choose> Pre�o</h2>       			</div>       			<div class="clr"></div>       		</div>       		<div class="b">      			<div class="b">          			<div class="b"></div>       			</div>       		</div>   		</div>
   		<div class="clr"></div>
   		<div id="element-box">	       	<div class="t">          		<div class="t">            		<div class="t"></div>
          		</div>
        	</div>
        	<div class="m">                               
         		<form id="formContent"  <c:choose><c:when test='${gerenciadorMensagem.id == null}'>action="adiciona"</c:when><c:otherwise>action="altera"</c:otherwise></c:choose> method="post" >
					<div class="width-60 fltlft">
						<input type="hidden" name="gerenciadorMensagem.id"  value="${gerenciadorMensagem.id}" />								
						<fieldset class="adminform">
							<legend>Informa��es</legend>
							<ul class="adminformlist">																																											<li><dotec:campoTexto readOnly="true" label="Titulo:" id="gerenciadorMensagem.title" value="${gerenciadorMensagem.title}" classe="inputbox" size="70"></dotec:campoTexto></li>							
							<li><dotec:campoTexto label="Assunto:" id="gerenciadorMensagem.subject" value="${gerenciadorMensagem.subject}" classe="inputbox" size="90"></dotec:campoTexto></li>
							<li><dotec:campoTexto label="E-mail de Envio:" id="gerenciadorMensagem.emailFrom"  value="${gerenciadorMensagem.emailFrom}" classe="inputbox email" size="70"></dotec:campoTexto></li>							
							<li>
								<label for="gerenciadorMensagem.body" class="hasTip required">Corpo do Email:<span class="star">&#160;*</span></label>
								<textarea id="gerenciadorMensagem.body" name="gerenciadorMensagem.body" cols="80" rows="20">${gerenciadorMensagem.body}</textarea>
							</li>
							<script type="text/javascript">
								//<![CDATA[
									CKEDITOR.replace( 'gerenciadorMensagem.body', {
										uiColor: 'none',
										skin : 'v2'						
									} );
								//]]>					
							</script>							
						</ul>
				    </fieldset>                                                                                                    
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
      Aten��o! JavaScript deve estar habilitado para o bom funcionamento do back-end do administrador.
      </noscript>
      <div class="clr"></div>    
<%@ include file="/footer-admin.jsp" %>