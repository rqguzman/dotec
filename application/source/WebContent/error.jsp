<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="dotec" %>				
<c:if test="${not empty errors}">
	<div id="errors"  style="color:red;padding:5px 10px !important">
		<ul>
			<c:forEach items="${errors}" var="error">
				<li>${error.category } - ${error.message }</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
