<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="required" required="false" %>
<label for="${id}">${label}
<c:if test="${empty required || required}">
	<span class="fieldRequired">*</span>
</c:if>
</label>
<input type="text" id="${id}" name="${id}" class="real" value="${value}" />