<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="classe" required="false" %>
<label for="${id}">${label}
<c:if test="${empty required || required}">
	<span class="star">&#160;*</span>
</c:if>
</label>
<input type="text" id="${id}" name="${id}" class="email<c:if test='${readOnly}'> readOnly</c:if> ${classe}" value="${value}"
<c:if test="${readOnly}">
	readonly="readonly"	
</c:if>
 />