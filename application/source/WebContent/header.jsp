<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dotec" %>
<fmt:setLocale value="${locale}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>DOTEC - Empresa Especializada em Guarda de Arquivos e Documentos</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="follow, all" />
		<meta name="Description" content="Guarda de documentos, guarda, guarda de documento, organização, organização de documentos, gestão, gestão de informação, gestão documental, ged, digitalização, digitalização de documentos, dotec, empresa dotec, rio, rio de janeiro, dotec logística de guarda e transporte de documentos faça tudo pela internet" />
		<meta name="keywords" content="Dotec - guarda de arquivos simples,guarda de arquivos técnicos, guarda documentos, digitalização de documentos, gestão de documentos, arquivamento de documentos, organização de documentos, organização de arquivos, guarda de documentos fiscais, organização de arquivo, arquivo de documentos, dicas de segurança, quanto tempo guardar documentos, digitalização,
segurança na internet, guardar documentos, gestão de arquivos, como arquivar documentos, pequenas empresas, grandes empresas." />
		<meta name="author" content="Dotec" />
		<meta name="company" content="Dotec" />
				
		<script type="text/javascript" src="<c:url value="/client/js/jquery.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/client/js/cmxforms.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/client/js/jquery.loading.js"/>"></script>    
		<script type="text/javascript" src="<c:url value="/client/js/jquery.maskedinput.js"/>"></script>    
		<script type="text/javascript" src="<c:url value="/client/js/jquery.validate.js"/>"></script>  
		<script type="text/javascript" src="<c:url value="/client/js/jquery.tablesorter.js"/>"></script>  
		<script type="text/javascript" src="<c:url value="/client/js/jquery.tablesorter.pager.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/client/js/jquery.fixedposition.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/client/js/jquery-ui-1.8.custom.min.js"/>"></script>		
		<script type="text/javascript" src="<c:url value="/client/js/jquery.hotkeys.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/client/js/swfobject.js"/>"></script> 					
		<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/jquery.min.js"/>"  ></script>
		<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/js/jquery-ui.min.js"/>"  ></script>
		<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-validation/jquery.validate.min.js"/>"  ></script>
		<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery.alphanumeric/jquery.alphanumeric.js"/>"  ></script>
		<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask-money/jquery.maskMoney.js"/>"  ></script>
		<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-mask/jquery.maskedinput-1.3.min.js"/>"  ></script>
		<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/jquery-quicksearch/jquery.quicksearch.js"/>"  ></script>	
 		<!--<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/treeview/jquery.treeview.js"/>"  ></script> -->
 		<!-- <script type="text/javascript" src="<c:url value="/client/frameworks/jquery/treeview/jquery.cookie.js"/>"  ></script> -->
 		<script type="text/javascript" src="<c:url value="/client/js/jquery.fixedposition.js"/>"></script>	 	
 		<script type="text/javascript" src="<c:url value="/client/css/media/system/js/core.js"/>"></script>
 		<!--link href="<c:url value="/client/css/default.css"/>" rel="stylesheet" type="text/css" media="screen" /-->
	 	<script type="text/javascript" src="<c:url value="/client/js/jquery.jstree.js"/>" ></script>	
			<!--[if IE 7]>
			<link href="<c:url value="/client/css/bluestork/css/ie7.css"/>" rel="stylesheet" type="text/css" />
			<![endif]-->  
			<!--[if gte IE 8]>
			<link href="/client/css/bluestork/css/ie8.css" rel="stylesheet" type="text/css" />
			<![endif]-->  	
 			<link href="<c:url value="/client/frameworks/jquery/plugins/jquery-ui/css/cupertino/jquery-ui-1.8.12.custom.css"/>" rel="stylesheet" type="text/css" media="screen" />
			<!-- <link rel="stylesheet" href="<c:url value="/client/frameworks/jquery/treeview/jquery.treeview.css" />" /> -->
			<script type="text/javascript" src="<c:url value="/client/js/default.js"/>"  ></script> 
	 		<script type="text/javascript" src="<c:url value="/client/js/dotec.js"/>"  ></script>	 							 
			<script type="text/javascript">
				swfobject.embedSWF("<c:url value='/client/swf/animacao_dotec.swf'/>", "webdoor", "727", "189", "9.0.0", "<c:url value='/client/swf/animacao_dotec.swf'/>");
			</script>				
			
		
		<style type="text/css">
		a:link 
			text-decoration: none;
		}
		a:visited {
			text-decoration: none;
		}
		a:hover {
			text-decoration: none;
		}
		a:active {
			text-decoration: none;
		}

body {
	background-color: #000;
}

		</style>
		
		
		<script type="text/javascript">
			function MM_showHideLayers() { //v9.0
			  var i,p,v,obj,args=MM_showHideLayers.arguments;
			  for (i=0; i<(args.length-2); i+=3) 
			  with (document) if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
				if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
				obj.visibility=v; }
			}
			function MM_swapImgRestore() { //v3.0
			  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
			}
			function MM_preloadImages() { //v3.0
			  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
				var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
				if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			}

			function MM_findObj(n, d) { //v4.01
			  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
				d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
			  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
			  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
			  if(!x && d.getElementById) x=d.getElementById(n); return x;
			}

			function MM_swapImage() { //v3.0
			  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
			   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
			}
			function MM_openBrWindow(theURL,winName,features) { //v2.0
			  window.open(theURL,winName,features);
			}
		</script>
		
		
		<c:forEach items="${contentList}" var="content"	varStatus ="status" end="3">															
			<c:choose>	
				<c:when test="${content eq null}">
					<link href="<c:url value="client/css/quem_somos.css"/>" rel="stylesheet" type="text/css" />
				</c:when>
				<c:otherwise>
					<link href="<c:url value="/client/css/${content.css}.css"/>" rel="stylesheet" type="text/css" />
				</c:otherwise>																			    	
			</c:choose>		
		</c:forEach>
		
		<c:if test="${contentList eq null}">
		
					<link href="<c:url value="/client/css/quem_somos.css"/>" rel="stylesheet" type="text/css" />
		
		
		</c:if>
		
		<script type="text/javascript">
		var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-35946596-1']);
		  _gaq.push(['_trackPageview']);
		 
		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();
			</script>
	</head>
	<body onload="MM_preloadImages('<c:url value="/client/imagens/twitter2.png" />','<c:url value="/client/imagens/facebook2.png" />','<c:url value="/client/imagens/youtube2.png"/>')">	
		
		<input type="hidden" id="siteUrl" value="<c:url value="/"/>" />
		
		<div id="fb-root"></div>
		<script>(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) return;
			js = d.createElement(s); js.id = id;
			js.src = "//connect.facebook.net/pt_BR/all.js#xfbml=1";
			fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));
		</script>
		
		
		
		<!--PRINCIPAL -->
		<div id="principal">
			<div id="topo">     
				<div id="logotipo">
					<a href="<c:url value="/"/>">
						<img src="<c:url value="/client/imagens/logotipo.png"/>" alt="Dotec - Armazenamento de Documentos - Guarda de Documentos" width="151" height="111" border="0" />
					</a>
				</div>          
    			<div id="cadastre-se">
    				<a href="<c:url value="/cadastro/cadastro"/>">
    					<img src="<c:url value="/client/imagens/botao-cadastro.png"/>" alt="" width="194" height="66" border="0" />
    				</a>
    			</div>													               
				<div id="campo-login">
					<img src="<c:url value="/client/imagens/campo-login.png"/>" width="334" height="119" />
				</div>
			</div>
										
    		
    		<!-- START FORUMARIO -->
    		<div class="formulario" id="campo-login">
    			<img src="<c:url value="/client/imagens/campo-login.png"/>" width="334" height="119" />       
    			
    			<div class="formulario" id="cadastro-tabela">
    			
    			<c:choose>
							<c:when test="${empty userInfo.user.nome}">
							
      				<form onsubmit="return validarUsuario();" name="frmLogin" action="<c:url value="/admin/autentica" />" method="post">
      				<div class="login">
        				<table width="295" border="0">
				          <tr>
				            <td width="147"><span class="formulario">E-mail</span></td>
				            <td width="138"><span class="formulario">Senha</span></td>
				          </tr>
				          <tr>
				            <td><span class="formulario"><input type="text" name="credencial.login" type="text" id="email" size="15" /></span></td>
				            <td><span class="formulario"><input type="password" name="credencial.senha" type="text" id="senha" size="15" /></span></td>
				          </tr>
				          <tr>
				            <td><span class="formulario"><a href="javascript:forgotLogin();"  style="color:#000">Esqueceu a senha?</a></span></td>
				            <td><span class="formulario"><input type="image" src="<c:url value="/client/imagens/acessar.png"/>" value="ENVIAR"/></span></td>
				          </tr>
				        </table>
				        </div>
				    </form>
				    
				    
				    <!-- START FORGOT LOGIN -->
									<div class="esqueceu" style="display:none">
										<div class="lineField">
											<div>
												<label>E-mail:</label>
												<input type="text" id="txtEsqueceuSenhaEmail" name="txtEsqueceuSenhaEmail" style="width:270px;" />
											</div>
										</div>
										<div class="clear"></div>
										<div class="lineField" style="margin-top:10px">
											<a href="javascript:backLogin();"  style="color:#000 !important;padding-top:5px;margin-right:10px"><< Voltar</a>
											<img src="<c:url value="/client/image/btEnviar.png"/>" align="middle" alt="enviar" onclick="getNewPass();" />
										</div>
									</div>
									<!-- END FORGOT LOGIN -->
				    
				   </c:when>
							<c:otherwise>
								<div>Seja bem-vindo, <b> ${userInfo.user.nome}</b></div>
								<p>
								<c:choose>
									<c:when test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">
										<a class="icon-16-logout" href="<c:url value="/logout" />" style="color:#000">Sair</a> | <a class="icon-16-logout" href="<c:url value="/movimentacaos/lista" />"  style="color:#000">Área Administrativa</a> 
									</c:when>
									<c:otherwise>
										<a class="icon-16-logout" href="<c:url value="/logout" />"  style="color:#000">Sair</a> | <a class="icon-16-logout" href="<c:url value="/elementos/lista" />"  style="color:#000">Área Administrativa</a>
									</c:otherwise>
								</c:choose>
								
								</p>
							</c:otherwise>
						</c:choose> 
				    
				    
    			</div>       
    		</div>
    		<!-- END FORMULARIO -->
  			
			<div id="menu">
				<div id="links-menu-horizontal">
					<ul>
						<li><a href="<c:url value="/"/>">Home</a></li>
						<li><a href="<c:url value="/quem-somos.html"/>">Quem Somos</a></li>
						<li><a href="<c:url value="/tabela-de-precos.html"/>">Tabela de Preços</a></li>
						<li><a href="<c:url value="/area-de-atendimento.html"/>">Área de Atendimento</a></li>
						<li><a href="<c:url value="/faleconosco/"/>">Contato</a></li>
					</ul>
				</div>
			</div>
			<div id="slide"></div>
			
									
			<div id="conteudo">