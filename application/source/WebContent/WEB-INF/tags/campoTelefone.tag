<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="idDDD" required="true" %>
<%@ attribute name="idTelefone" required="true" %>
<%@ attribute name="valueDDD" required="true" %>
<%@ attribute name="valueTelefone" required="true" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<label for="${id}">${label}
<c:if test="${empty required || required}">
	<span class="star">&#160;*</span>
</c:if>
</label>
<input type="text" id="${idDDD}" name="${idDDD}" class="ddd<c:if test='${readOnly}'> readOnly</c:if>" value="${valueDDD}" size="2" maxlength="2"
<c:if test="${readOnly}">
	readonly="readonly"	
</c:if>
 />
<input type="text" id="${idTelefone}" name="${idTelefone}" class="telefone<c:if test='${readOnly}'> readOnly</c:if>" value="${valueTelefone}" size="10" maxlength="8"
<c:if test="${readOnly}">
	readonly="readonly"	
</c:if>
 />