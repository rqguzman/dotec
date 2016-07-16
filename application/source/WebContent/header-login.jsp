<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dotec" %>
<fmt:setLocale value="${locale}"/>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
	<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br"> 
		<head> 
  			<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" /> 
  			<meta name="robots" content="index, follow" /> 
		  	<meta name="generator" content="Joomla! 1.6 - Open Source Content Management" /> 		  			  	<meta name="Description" content="Guarda de documentos, guarda, guarda de documento, organiza��o, organiza��o de documentos, gest�o, gest�o de informa��o, gest�o documental, ged, digitaliza��o, digitaliza��o de documentos, dotec, empresa dotec, rio, rio de janeiro, dotec log�stica de guarda e transporte de documentos fa�a tudo pela internet" />						<meta name="keywords" content="Dotec - guarda de arquivos simples,guarda de arquivos t�cnicos, guarda documentos, digitaliza��o de documentos, gest�o de documentos, arquivamento de documentos, organiza��o de documentos, organiza��o de arquivos, guarda de documentos fiscais, organiza��o de arquivo, arquivo de documentos, dicas de seguran�a, quanto tempo guardar documentos, digitaliza��o,seguran�a na internet, guardar documentos, gest�o de arquivos, como arquivar documentos, pequenas empresas, grandes empresas." />
		  	<title>Sistema DOTEC</title> 
  
 			<link href="<c:url value="/client/image/favicon.ico"/>" rel="shortcut icon" type="image/vnd.microsoft.icon" /> 
			<link rel="stylesheet" href="<c:url value="/client/css/media/system/css/modal.css"/>" type="text/css" /> 
 			<script src="<c:url value="/client/css/media/system/js/core.js"/>" type="text/javascript"></script>  			
 			 
			<link href="<c:url value="/client/css/bluestork/css/template.css"/>" rel="stylesheet" type="text/css" /> 
 			
			<!--[if IE 7]>
			<link href="<c:url value="/client/css/bluestork/css/ie7.css"/>" rel="stylesheet" type="text/css" />
			<![endif]-->  
			<!--[if gte IE 8]>
			<link href="/client/css/bluestork/css/ie8.css" rel="stylesheet" type="text/css" />
			<![endif]--> 
 
			<link rel="stylesheet" type="text/css" href="<c:url value="/client/css/bluestork/css/rounded.css" />" />
			
			<link href="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css"/>" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/jquery.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/js/jquery-ui.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-validation/jquery.validate.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery.alphanumeric/jquery.alphanumeric.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask-money/jquery.maskMoney.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask/jquery.maskedinput-1.3.min.js"/>"  ></script>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-quicksearch/jquery.quicksearch.js"/>"  ></script>
	 
	 
	 
	 
	 <link href="<c:url value="/client/css/default.css"/>" rel="stylesheet" type="text/css" media="screen" />
		</head> 
		<body id="minwidth-body"> 
			<div id="border-top" class="h_blue"> 
				<div> 
					<div> 				
						<span class="logo"><a href="<c:url value="/"></c:url>" target="_blank"><img src="<c:url value="/client/image/logo-admin.gif"/>" alt="Dotec" /></a></span> 
						<span class="title"><a href="index.php">Administra��o</a></span> 
					</div> 
				</div> 
			</div> 	
			