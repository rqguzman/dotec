<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="false" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<label for="${id}">${label}
<c:if test="${empty required || required}">
	<span class="star">*</span>
</c:if>
</label>
<c:if test="${empty name}">
	<c:set var="name" value="${id}"></c:set>
</c:if>
<input type="text" id="${id}" name="${name}" class="numero <c:if test='${readOnly}'> readOnly</c:if>"
<c:if test="${readOnly}">
	readonly="readonly"	
</c:if>
 value="${value}" />