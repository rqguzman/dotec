<%@ include file="/header-admin2.jsp" %>
<script type="text/javascript" src="<c:url value="/client/js/caixasLista.js"/>"></script>
<style type="text/css">
	@import url("<c:url value='/client/css/caixasLista.css'/>");
</style>

<fieldset>
	<legend>Minhas Caixas</legend>
	<input type="button" class="btnSolicitarCaixas" value="Solicitar mais caixas"> 
	<input type="button" class="btnSolicitarCaixas" value="Solicitar mais caixas">
</fieldset>


<div id="dialog-form-nova-caixa" title="Solicitar nova caixa">
	<p class="validateTips">Todos os campos são obrigatórios</p>
	<form>
		<fieldset>
			<label for="nome">Nome</label>
			<input type="text" name="caixa.nome" id="caixa.nome" class="text ui-widget-content ui-corner-all" />
			<label for="descricao">Descrição</label>
			<input type="text" name="caixa.descricao" id="caixa.descricao" class="text ui-widget-content ui-corner-all" />
		</fieldset>
	</form>
</div>
	




<%@ include file="/footer-admin2.jsp" %>