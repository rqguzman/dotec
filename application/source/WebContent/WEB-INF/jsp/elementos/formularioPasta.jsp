<%@ include file="/header-admin2.jsp" %>
	<form  <c:choose><c:when test='${cauxa.id == null}'>action="adicionaPasta"</c:when><c:otherwise>action="alteraPasta"</c:otherwise></c:choose> method="post" >
		<input type="hidden" name="pasta.id"  value="${pasta.id}" />
		<fieldset>
			<legend>Pasta</legend>
			<dotec:campoTexto label="Nome:" id="pasta.nome" value="${pasta.nome}"></dotec:campoTexto>
			<br /><br />
			<dotec:campoTexto label="Descricao:" id="pasta.descricao" value="${pasta.descricao}"></dotec:campoTexto>
			<br /><br />
			<button type="button" onclick="javascript:window.location='<c:url value="/caixas/lista"/>'">Voltar</button>	
			<button type="submit">Enviar</button>	
		</fieldset>
		<dotec:campoObrigatorio></dotec:campoObrigatorio> 
	</form>
<%@ include file="/footer-admin2.jsp" %>