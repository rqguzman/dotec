<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<div id="erros">
		<ul>
		<c:forEach items="${errors}" var="error">
		<li>${error.category } - ${error.message }</li>
		</c:forEach>
		</ul>
	</div>
	<form <c:choose><c:when test='${tipoDeLogradouro.id == null }'>action="adiciona"</c:when><c:otherwise>action="altera"</c:otherwise></c:choose> method="post" >
		<fieldset>
			<legend>Tipo de Logradouro</legend>
			<input type="hidden" name="tipoDeLogradouro.id" value="${tipoDeLogradouro.id}"  />
			<label for="tipoDeLogradouro.abreviacao">Abreviacao:</label>
			<input id="tipoDeLogradouro.abreviacao" type="text" name="tipoDeLogradouro.abreviacao" value="${tipoDeLogradouro.abreviacao}" /><br /><br />
			<label for="tipoDeLogradouro.nome">Nome:</label>
			<input id="tipoDeLogradouro.nome" type="text" name="tipoDeLogradouro.nome" value="${tipoDeLogradouro.nome}"  /><br /><br />
			<button type="submit">Enviar</button>
		</fieldset>
	</form>
</body>
</html>