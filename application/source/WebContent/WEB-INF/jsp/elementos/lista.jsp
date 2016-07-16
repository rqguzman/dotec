<%@ include file="/header-admin.jsp" %> 
<%@ include file="/menu-admin.jsp" %>

<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jstree/jquery.jstree.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jstree/_lib/jquery.cookie.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jstree/_lib/jquery.hotkeys.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-tooltip/jquery.tooltip.min.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/js/elementosLista.js"/>"></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-puts/puts.js"/>"  ></script><script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery.form/jquery.form.js"/>" ></script>
<style type="text/css">
	@import url("<c:url value='/client/css/elementosLista.css'/>");
</style>
<style type="text/css">
	@import url("<c:url value='/client/frameworks/jquery/plugins/jquery-tooltip/jquery.tooltip.css'/>");
</style>
	
	
    		<div class="padding">
      			<div id="toolbar-box">
        			<div class="t">
          				<div class="t">
            				<div class="t"></div>
          				</div>
        			</div>
		 			<div class="m">
          				<ul id="submenu">          					<li><img class="btnRefresh" alt="refresh" title="Atualizar lista" src="<c:url value="/client/image/icons/refresh.png"/>" /></li>          					<li><button class="btnCriarCaixa">Nova Caixa</button></li>          	 				<li><button class="btnCriarPasta">Adicionar Pasta</button></li>          	 			</ul>
          				<div class="clr"></div>
        			</div>
        			<div class="b">
          				<div class="b">
            				<div class="b"></div>
          				</div>
        			</div>
      			</div>
      
       			<div id="element-box">
        			<div class="t">
          				<div class="t">
            				<div class="t"></div>
          				</div>
        			</div>
        			<div class="m">                           
            			<div class="clr"> </div>   		           
						
						<input type="text" class="search" value="" />																	
						<div id="tree" class="demo"></div>
						
						<div id="boxDocumentos"></div>
												<div id="dialog-form-rename">							<p class="validateTips">Todos os campos são obrigatórios</p>							<form>								<fieldset>									<label for="nome">Nome</label>									<input type="text" name="elementoNome" id="elementoNome" class="text ui-widget-content ui-corner-all" />									<label for="descricao">Descrição</label>									<input type="text" name="elementoDescricao" id="elementoDescricao" class="text ui-widget-content ui-corner-all" />									<input type="hidden" name="elementoId" id="elementoId" />															</fieldset>							</form>						</div>
						
						<div id="dialog-form-adiciona">
							<p class="validateTips">Todos os campos são obrigatórios</p>
							<form id="form-adiciona"  action="adicionaDocumento" method="POST" enctype="multipart/form-data">
								<fieldset>																	<label for="nome">Nome</label>																		<input type="text" name="caixa.nome" id="caixa.nome" class="text ui-widget-content ui-corner-all" />
									<label for="descricao">Descrição</label>
									<input type="text" name="caixa.descricao" id="caixa.descricao" class="text ui-widget-content ui-corner-all" />																		<input type="file" name="uploadedFile" class="uploadedFile" id="uploadedFile" />																		<label for="quantidade" class="quantidade">Quantidade</label>									<input type="text" name="quantidade" id="quantidade" class="text ui-widget-content ui-corner-all quantidade"  value="1"/>									
									<input type="hidden" name="elementoPaiId" id="elementoPaiId" />																		<div class="progress">        								<div class="bar">        									<div class="percent">0%</div >        								</div >								    </div>								    						    							
								</fieldset>
							</form>
						</div>																		
					</div>
 					<div class="b">
          				<div class="b">
            				<div class="b"></div>
          				</div>
        			</div>
       			</div>
       		</div>
       
<input type="hidden" id="addType" value="documento"/>
<input type="hidden" id="addController" value="adicionaDocumento"/>

<input type="hidden" id="boxImageURL" value='<c:url value="/client/image/icons/boxNode.png"/>' />
<input type="hidden" id="pathImageURL" value='<c:url value="/client/image/icons/pathNode.png"/>' />
<input type="hidden" id="documentImageURL" value='<c:url value="/client/image/icons/documentNode.png"/>' /><input type="hidden" id="envelopeImageURL" value='<c:url value="/client/image/icons/envelopeNode.png"/>' />
<input type="hidden" id="isHabilitado" value='${userInfo.cliente.habilitado}' /><input type="hidden" id="isGuardaTecnica" value='${userInfo.cliente.guardaTecnica}' />

<script type="text/javascript">


</script>

<style>
	#tree a { white-space:normal !important; height: auto; padding:1px 	2px; } 
	#tree li > ins { vertical-align:top; }
	#tree .jstree-hovered, #tree .jstree-clicked { border:0; }
</style>



<%@ include file="/footer-admin.jsp" %>	


