<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="lista" required="true" %>
<%@ attribute name="value" required="false" %>

<select name="${id}" id="${id}" >
				<option value=""></option>
				<c:forEach items="${lista}" var="sexo">
					<option value="${sexo}">${sexo.nome}</option>
				</c:forEach>
</select>