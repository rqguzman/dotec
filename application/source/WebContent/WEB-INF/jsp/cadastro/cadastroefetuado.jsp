<%@ include file="/header.jsp" %>
		<script type="text/javascript" src="<c:url value="/client/js/clientesFormulario.js"/>"  ></script>
 	<style type="text/css">
		@import url("<c:url value="/client/css/clientesFormulario.css"/>");
		@import url("<c:url value="/client/css/cadastro.css"/>");
	</style>
						<div style="padding:40px">
						<%@ include file="/error.jsp" %>
						
						
						Seu Cadastro foi efetuado com sucesso, você receberá o boleto bancário em seu email para dar-mos continuidade na finalização do respectivo cadastro.
																		
							<!-- FIM DE FORMULARIO -->
							<script>
								window.location.href="${userInfo.moipUrl}";							
							</script>
</div>
<%@ include file="/footer.jsp"%>