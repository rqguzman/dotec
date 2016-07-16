<%@ include file="/header.jsp" %>
							
			<c:forEach items="${contentList}" var="content"	varStatus ="status" end="3">															
				${content.content}															    
			</c:forEach>
<%@ include file="/footer.jsp"%>