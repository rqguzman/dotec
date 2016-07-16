<%@ include file="/header-admin.jsp"%>
<%@ include file="/menu-admin.jsp"%>
<script type="text/javascript" src="<c:url value="/client/js/clientesFormulario.js"/>"  ></script>
<style type="text/css">
	@import url("<c:url value="/client/css/movimentacoesFormulario.css"/>");
</style>
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
              		<li class="button" id="toolbar-save"> <a href="javascript:$('#formulario').submit()" onClick="" class="toolbar"> <span class="icon-32-save"> </span> Salvar &amp; Fechar </a> </li>              
              		<li class="button" id="toolbar-cancel"> <a href="<c:url value="lista" />" class="toolbar"> <span class="icon-32-cancel"> </span> Cancelar </a> </li>
              		<li class="divider"> </li>
              		<li class="button" id="toolbar-help"> <a href="#" onClick="popupWindow('http://help.joomla.org/proxy/index.php?option=com_help&amp;keyref=Help16:Components_Weblinks_Categories_Edit', 'Ajuda', 700, 500, 1)" rel="help" class="toolbar"> <span class="icon-32-help"> </span> Ajuda </a> </li>
            	</ul>
            	<div class="clr"></div>
          	</div>
			<div class="pagetitle icon-48-category-add icon-48-weblinks-category-add">
				<h2>
					Gerenciador de Movimentações: Editar Movimentação
				</h2>
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
			<form id="formulario" name="formulario" class="cmxform" action="altera" method="post">
				<input type="hidden" name="clientePessoaFisica.id" value="${clientePessoaFisica.id}" />
				<div class="width-60 fltlft">
					<fieldset class="adminform">
						<legend>Detalhes</legend>
						<ul class="adminformlist" id="ulTipoDeCliente">
							<li><dotec:campoRadio classe="radioTipoDeCliente"
									name="tipoDeCliente" label="Pessoa Física"
									id="radioPessoaFisica" value="pf"
									checked="${checkPessoaFisica}"
									readOnly="${readOnlyTipoDePessoa}"></dotec:campoRadio> <dotec:campoRadio
									classe="radioTipoDeCliente" name="tipoDeCliente"
									label="Pessoa Jurídica" id="radioPessoaJuridica" value="pj"
									checked="${checkPessoaJuridica}"
									readOnly="${readOnlyTipoDePessoa}"></dotec:campoRadio></li>
						</ul>
					</fieldset>
					<div class="clr"></div>
					<dotec:campoObrigatorio></dotec:campoObrigatorio>
				</div>
				<div class="width-40 fltrt">
					<div id="categories-sliders-" class="pane-sliders">
						<div style="display: none;">
							<div></div>
						</div>
					</div>
				</div>
				<div class="clr"></div>
				<input type="submit" />
			</form>
			<div class="clr"></div>
		</div>
		<div class="b">
			<div class="b">
				<div class="b"></div>
			</div>
		</div>
	</div>
	<noscript>Atenção! JavaScript deve estar habilitado para obom funcionamento do back-end do administrador.</noscript>
	<div class="clr"></div>
</div>
<div class="clr"></div>
<%@ include file="/footer-admin.jsp"%>