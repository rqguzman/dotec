<%@ include file="/header-admin.jsp" %>
<%@ include file="/menu-admin.jsp" %> 	
	<div class="padding">
   			<div class="m">
   		<div class="clr"></div>
   		<div id="element-box">
          		</div>
        	</div>
        	<div class="m">                               
         		<form id="formContent"  <c:choose><c:when test='${tabelaPreco.id == null}'>action="adiciona"</c:when><c:otherwise>action="altera"</c:otherwise></c:choose> method="post" >
					<div class="width-60 fltlft">
						<input type="hidden" name="tabelaPreco.id"  value="${tabelaPreco.id}" />								
						<fieldset class="adminform">
							<legend>Informa��es</legend>
							<ul class="adminformlist">								
											<c:if test="${tabelaPreco.category=='Frete de Solicita��o de Entrega de:'}">selected="selected"</c:if> 										
										>Frete de Solicita��o de Entrega de:</option>											
										<c:if test="${tabelaPreco.category=='Frete de Solicita��o de Retirada de:'}">selected="selected"</c:if> 
										>Frete de Solicita��o de Retirada de:</option>
							<li><dotec:campoNumeroInteiro label="Ordena��o:" id="tabelaPreco.ordering" value="${tabelaPreco.ordering}" ></dotec:campoNumeroInteiro></li>
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