<%@ include file="/header-admin2.jsp" %>
	<form  <c:choose><c:when test='${cauxa.id == null}'>action="adiciona"</c:when><c:otherwise>action="altera"</c:otherwise></c:choose> method="post" >
		<input type="hidden" name="caixa.id"  value="${caixa.id}" />
		<fieldset>
			<legend>Cliente</legend>
			<dotec:campoTexto label="Nome:" id="caixa.nome" value="${caixa.nome}"></dotec:campoTexto>
			<br /><br />
			<dotec:campoTexto label="Descricao:" id="caixa.descricao" value="${caixa.descricao}"></dotec:campoTexto>
			<br /><br />
			<button type="button" onclick="javascript:window.location='<c:url value="/caixas/lista"/>'">Voltar</button>	
			<button type="submit">Enviar</button>	
		</fieldset>
		<dotec:campoObrigatorio></dotec:campoObrigatorio> 
	</form>
<%@ include file="/footer-admin2.jsp" %>