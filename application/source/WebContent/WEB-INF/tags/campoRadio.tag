<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" required="false" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="classe" required="false" %>
<%@ attribute name="size" required="false" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="checked" required="false" %>

<c:if test="${empty name}">
	<c:set var="name" value="${id}"></c:set>
</c:if>
<label for="${id}">${label}</label>
<input type="radio" id="${id}" name="${name}" value="${value}"
<c:if test="${readOnly}">
	disabled="disabled"
</c:if>
<c:if test="${checked}">
	checked="checked"	
</c:if>
class="radio <c:if test='${readOnly}'> readOnly</c:if> ${classe}" 
 />