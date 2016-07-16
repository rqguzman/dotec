<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dotec" %>
<fmt:setLocale value="${locale}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="author" content="
	@author Raphael Cabral - raphaelcabralnet@gmail.com
	@author Jorge Eduardo de Oliveira - jeolcavaco@gmail.com"  />
	<meta name="reply-to" content="raphaelcabralnet@gmail.com"/>
	<meta name="Description" content="Guarda de documentos, guarda, guarda de documento, organização, organização de documentos, gestão, gestão de informação, gestão documental, ged, digitalização, digitalização de documentos, dotec, empresa dotec, rio, rio de janeiro, dotec logística de guarda e transporte de documentos faça tudo pela internet" />
			
			<meta name="keywords" content="Dotec - guarda de arquivos simples,guarda de arquivos técnicos, guarda documentos, digitalização de documentos, gestão de documentos, arquivamento de documentos, organização de documentos, organização de arquivos, guarda de documentos fiscais, organização de arquivo, arquivo de documentos, dicas de segurança, quanto tempo guardar documentos, digitalização,
segurança na internet, guardar documentos, gestão de arquivos, como arquivar documentos, pequenas empresas, grandes empresas." />
	<title>Dotec</title>
	<link rel="shortcut icon" href="<c:url value="/client/image/favicon.ico"/>" />
	<link href="<c:url value="/client/css/default.css"/>" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css"/>" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/jquery.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/js/jquery-ui.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-validation/jquery.validate.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery.alphanumeric/jquery.alphanumeric.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask-money/jquery.maskMoney.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask/jquery.maskedinput-1.3.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-quicksearch/jquery.quicksearch.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/js/default.js"/>"  ></script>
</head>
<body>
		  <div id="cabecalho">
		   	  Cabecalho
	   	  </div>
	   	  <div id="conteudo">
				<%@ include file="/error.jsp" %>