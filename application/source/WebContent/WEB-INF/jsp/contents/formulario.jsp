<%@ include file="/header-admin.jsp" %>
<%@ include file="/menu-admin.jsp" %> 	

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
              					<li class="button" id="toolbar-save"> <a href="javascript:$('#formContent').submit()" onClick="" class="toolbar"> <span class="icon-32-save"> </span> Salvar &amp; Fechar </a> </li>              
              					<li class="button" id="toolbar-cancel"> <a href="<c:url value="/admin/contents/lista" />" class="toolbar"> <span class="icon-32-cancel"> </span> Cancelar </a> </li>
              					<li class="divider"> </li>
              					<li class="button" id="toolbar-help"> <a href="#" onClick="popupWindow('http://help.joomla.org/proxy/index.php?option=com_help&amp;keyref=Help16:Components_Weblinks_Categories_Edit', 'Ajuda', 700, 500, 1)" rel="help" class="toolbar"> <span class="icon-32-help"> </span> Ajuda </a> </li>
            				</ul>
            				<div class="clr"></div>
          				</div>
          				<div class="pagetitle icon-48-category-add icon-48-weblinks-category-add">
            			<h2>Gerenciador de Conteúdos: <c:choose><c:when test='${content.id == null}'>Adicionar</c:when><c:otherwise>Editar</c:otherwise></c:choose> Usuário</h2>
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
         		<form id="formContent"  <c:choose><c:when test='${content.id == null}'>action="adiciona"</c:when><c:otherwise>action="altera"</c:otherwise></c:choose> method="post" >
					<div class="width-60 fltlft">
					<input type="hidden" name="content.id"  value="${content.id}" />								
					<fieldset class="adminform">
						<legend>Informações</legend>
						<ul class="adminformlist"	>
							<li><dotec:campoTexto label="Titulo:" id="content.title" value="${content.title}" classe="inputbox" size="70"></dotec:campoTexto></li>
							<li><dotec:campoTexto label="Nome da Página:" id="content.page" value="${content.page}" classe="inputbox" size="50"></dotec:campoTexto></li>
							<li>
								<label for="content.isHomePage" class="hasTip required">Página inicial ?:<span class="star">&#160;*</span></label>
								<select name="content.isHomePage" id="content.isHomePage">																		
									<option value="0" <c:if test="${content.isHomePage==0}">selected="selected"</c:if> >Não</option>									
									<option value="1" <c:if test="${content.isHomePage==1}">selected="selected"</c:if> >Sim</option>
								</select>
							</li>
							<li>
								<label for="content.status" class="hasTip required">Habilitado?:<span class="star">&#160;*</span></label>
								<select name="content.status" id="content.status">																		
									<option value="0" <c:if test="${content.status==0}">selected="selected"</c:if> >Não</option>									
									<option value="3" <c:if test="${content.status==3}">selected="selected"</c:if> >Sim</option>
								</select>
							</li>							<li><dotec:campoTexto label="Arquivo Css" id="content.css" value="${content.css}" classe="inputbox" size="30"></dotec:campoTexto></li>
							<li><dotec:campoNumeroInteiro label="Ordenação:" id="content.ordering" value="${content.ordering}" ></dotec:campoNumeroInteiro></li>
							<li>
								<label for="content.content" class="hasTip required">Conteúdo:<span class="star">&#160;*</span></label>
							<textarea id="content.content" name="content.content" cols="80" rows="10">${content.content}</textarea></li><script type="text/javascript">			//<![CDATA[								CKEDITOR.replace( 'content.content', {					uiColor: 'none',					skin : 'v2'										} );			//]]>			</script>						</ul>
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
      Atenção! JavaScript deve estar habilitado para o bom funcionamento do back-end do administrador.
      </noscript>
      <div class="clr"></div>
    



<%@ include file="/footer-admin.jsp" %>