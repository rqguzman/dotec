<%@ include file="/header-admin.jsp" %> 
<%@ include file="/menu-admin.jsp" %>

<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jstree/jquery.jstree.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jstree/_lib/jquery.cookie.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jstree/_lib/jquery.hotkeys.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-tooltip/jquery.tooltip.min.js"/>"  ></script>
<script type="text/javascript" src="<c:url value="/client/js/elementosLista.js"/>"></script>
<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-puts/puts.js"/>"  ></script>
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
          				<ul id="submenu">
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
						
						
						<div id="dialog-form-adiciona">
							<p class="validateTips">Todos os campos s�o obrigat�rios</p>
							<form id="form-adiciona"  action="adicionaDocumento" method="POST" enctype="multipart/form-data">
								<fieldset>
									<label for="descricao">Descri��o</label>
									<input type="text" name="caixa.descricao" id="caixa.descricao" class="text ui-widget-content ui-corner-all" />
									<input type="hidden" name="elementoPaiId" id="elementoPaiId" />
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
<input type="hidden" id="documentImageURL" value='<c:url value="/client/image/icons/documentNode.png"/>' />


<script type="text/javascript">


</script>

<style>
	#tree a { white-space:normal !important; height: auto; padding:1px 	2px; } 
	#tree li > ins { vertical-align:top; }
	#tree .jstree-hovered, #tree .jstree-clicked { border:0; }
</style>



<%@ include file="/footer-admin.jsp" %>	

