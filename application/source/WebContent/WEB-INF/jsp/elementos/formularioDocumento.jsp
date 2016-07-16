<%@ include file="/header-admin2.jsp" %>
	<form  <c:choose><c:when test='${cauxa.id == null}'>action="adicionaDocumento"</c:when><c:otherwise>action="alteraDocumento"</c:otherwise></c:choose> method="post" >
		<input type="hidden" name="caixa.id"  value="${caixa.id}" />
		<fieldset>
			<legend>Documento</legend>
			<dotec:campoTexto label="Nome:" id="documento.nome" value="${documento.nome}"></dotec:campoTexto>
			<br /><br />
			<dotec:campoTexto label="Descricao:" id="documento.descricao" value="${documento.descricao}"></dotec:campoTexto>
			<br /><br />
			<button type="button" onclick="javascript:window.location='<c:url value="/caixas/lista"/>'">Voltar</button>	
			<button type="submit">Enviar</button>	
		</fieldset>
		<dotec:campoObrigatorio></dotec:campoObrigatorio> 
	</form>
<%@ include file="/footer-admin2.jsp" %>