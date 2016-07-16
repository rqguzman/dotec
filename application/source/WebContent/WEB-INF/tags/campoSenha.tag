<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="false" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="equalTo" required="false" %>

<c:if test="${empty name}">
	<c:set var="name" value="${id}"></c:set>
</c:if>
<label for="${id}">${label}
<c:if test="${empty required || required}">
	<span class="star">&#160;*</span>
</c:if>
</label>
<input type="password" id="${id}" name="${name}" class="password required" value="${value}"
<c:if test="${not empty equalTo}">
	equalTo="${equalTo}"
</c:if>
 minlength="6" maxlength="8"  />