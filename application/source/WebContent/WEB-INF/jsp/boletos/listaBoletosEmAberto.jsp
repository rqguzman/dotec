<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${empty boletoList}"></c:when>
	<c:otherwise> 
		<div class="boletosGerados">
			Você possui boletos em aberto: 
			<ul class="">
				<c:forEach items="${boletoList}" var="boleto" varStatus="rowCounter">			
					<li>
						<a href="<c:url value="/admin/boletos/lista"/>"> ${boleto.anoReferencia} | ${boleto.mesReferencia}</a>
					</li>							
				</c:forEach>
			</ul>
		</div>	
	</c:otherwise>
</c:choose>
