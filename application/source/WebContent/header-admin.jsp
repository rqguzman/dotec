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
		  	<meta name="Description" content="Guarda de documentos, guarda, guarda de documento, organização, organização de documentos, gestão, gestão de informação, gestão documental, ged, digitalização, digitalização de documentos, dotec, empresa dotec, rio, rio de janeiro, dotec logística de guarda e transporte de documentos faça tudo pela internet" />						<meta name="keywords" content="Dotec - guarda de arquivos simples,guarda de arquivos técnicos, guarda documentos, digitalização de documentos, gestão de documentos, arquivamento de documentos, organização de documentos, organização de arquivos, guarda de documentos fiscais, organização de arquivo, arquivo de documentos, dicas de segurança, quanto tempo guardar documentos, digitalização,segurança na internet, guardar documentos, gestão de arquivos, como arquivar documentos, pequenas empresas, grandes empresas." />						<meta name="author" content="Dotec" />					<meta name="company" content="Dotec" />		  		  	
		  	<meta name="generator" content="Joomla! 1.6 - Open Source Content Management" />
		  
		  	<meta http-equiv="pragma" content="no-cache" />
		  	<meta http-equiv="cache-control"   content="no-cache" />
			<meta http-equiv="expires" content="0" /> 
			
		  	<title>Sistema DOTEC</title> 
  
 			<link href="<c:url value="/client/image/favicon.ico"/>" rel="shortcut icon" type="image/vnd.microsoft.icon" /> 
			<link rel="stylesheet" href="<c:url value="/client/css/media/system/css/modal.css"/>" type="text/css" />			  		
 			 
			<link href="<c:url value="/client/css/bluestork/css/template.css"/>" rel="stylesheet" type="text/css" /> 
 			<link rel="stylesheet" type="text/css" href="<c:url value="/client/css/bluestork/css/rounded.css" />" />			
			<link href="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css"/>" rel="stylesheet" type="text/css" media="screen" />
			<!-- <link rel="stylesheet" href="<c:url value="/client/frameworks/jquery/treeview/jquery.treeview.css" />" /> -->
	 		<link href="<c:url value="/client/css/default.css"/>" rel="stylesheet" type="text/css" media="screen" />
	 		<link href="<c:url value="/client/frameworks/jquery/plugins/jquery-datatables/media/css/jquery.dataTables.css"/>" rel="stylesheet" type="text/css" media="screen" />
	 		
			<!--[if IE 7]>
			<link href="<c:url value="/client/css/bluestork/css/ie7.css"/>" rel="stylesheet" type="text/css" />
			<![endif]-->  
			<!--[if gte IE 8]>
			<link href="/client/css/bluestork/css/ie8.css" rel="stylesheet" type="text/css" />
			<![endif]--> 
 			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/jquery.min.js"/>"  ></script>
			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/js/jquery-ui.min.js"/>"  ></script>
			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-validation/jquery.validate.min.js"/>"  ></script>
			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery.alphanumeric/jquery.alphanumeric.js"/>"  ></script>
			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask-money/jquery.maskMoney.js"/>"  ></script>
			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask/jquery.maskedinput-1.3.min.js"/>"  ></script>
			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-quicksearch/jquery.quicksearch.js"/>"  ></script>	
			<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-datatables/media/js/jquery.dataTables.js"/>"  ></script>			<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js"/>"  ></script>			<script type="text/javascript" src="<c:url value="/ckeditor/sample.js"/>"  ></script>
			
	 		<!-- <script type="text/javascript" src="<c:url value="/client/frameworks/jquery/treeview/jquery.treeview.js"/>"  ></script> -->
	 		<!-- <script type="text/javascript" src="<c:url value="/client/frameworks/jquery/treeview/jquery.cookie.js"/>"  ></script>-->
	 		<script type="text/javascript" src="<c:url value="/client/js/default.js"/>"  ></script> 
	 		<script type="text/javascript" src="<c:url value="/client/js/admin.js"/>"  ></script>
 			<script type="text/javascript" src="<c:url value="/client/css/media/system/js/core.js"/>"></script> 			
			
	 
		</head> 
		<body id="minwidth-body"> 					<input type="hidden" id="siteUrl" value="<c:url value="/"/>" />
			<div id="border-top" class="h_blue"> 
				<div> 
					<div> 				
						<span class="title"><a href="#">Área Administrativa</a></span> 						<span class="logo"><a href="<c:url value="/"></c:url>" target="_blank"><img src="<c:url value="/client/image/logo-admin.gif"/>" alt="Dotec" /></a></span>							
					</div> 
				</div> 
			</div> 	
			