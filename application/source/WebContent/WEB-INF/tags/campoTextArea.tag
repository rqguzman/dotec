<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="classe" required="false" %>
<%@ attribute name="size" required="false" %>
<label for="${id}" class="hasTip required">${label}
<c:if test="${empty required || required}">
	<span class="star">&#160;*</span>
</c:if>
</label>
<textarea  id="${id}" name="${id}" 
<c:if test="${readOnly}">
	readonly="readonly"	
</c:if>
class="texto <c:if test='${readOnly}'> readOnly</c:if> ${classe}" size="${size}"
 >
 ${value}
 </textarea>