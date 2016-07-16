<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="formId" required="true" %>

<form></form>
<div id="dialog-form-login" title="Login">
	<p class="validateTips">Todos os campos são obrigatórios</p>
	<form action="<c:url value="/admin/confirmarSenha" />">
		<fieldset>
			<input type="hidden" id="confirmedPassword" value="0"  />
			<label for="nome">Usuário</label> 
			<input type="text" name="credencial.login" id="credencial.login" class="text ui-widget-content ui-corner-all" /> 
			<label for="descricao">Senha</label> 
			<input type="password"	name="credencial.senha" id="credencial.senha" class="text ui-widget-content ui-corner-all" />
		</fieldset>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
		
			$("#${formId}").submit(function() {
				if($("#confirmedPassword").val()=="0"){
					login();
					return false;
				}
			});
		
			function login(){
				$("#dialog-form-login").dialog("open");
			}
});


var	 login = $( "#dialog-form-login  #credencial\\.login" ),
senha = $( "#dialog-form-login #credencial\\.senha" ),
allFieldsLoginModal = $( [] ).add( login ).add( senha ),
tips = $( ".validateTips" );

function checkFill(o,n) {
	if ( o.val().trim().length == 0 ) {
		o.addClass( "ui-state-error" );
		updateTips( "Preencha o campo " + n + "." );
		return false;
	} else {
		return true;
	}
}

function updateTips( t ) {
	tips.text( t ).addClass( "ui-state-highlight" );
	setTimeout(function() {
		tips.removeClass( "ui-state-highlight", 1500 );
	}, 500 );
}


$("#dialog-form-login").dialog({

	autoOpen: false,

	height: 300,

	width: 350,

	modal: true,

	dialogClass: 'div-dialog-form', 
		
		buttons: {

			"Ok": function() {

				var bValid = true;

				allFieldsLoginModal.removeClass( "ui-state-error" );

				bValid = bValid && checkFill( login, "nome");

				bValid = bValid && checkFill( senha, "descrição");
									
				if ( bValid ) {
					
					var form = $("form",this);
					
					var action = form.attr("action");

					var serializedFormData = form.serializeArray();
					
					$.post(action, serializedFormData, "json")

					.success(function(data) {
						if(data=="1"){
							$("#confirmedPassword").val("1");
							$( "#dialog-form-login" ).dialog( "close" );
							$("#${formId}").submit();
						}else{
							updateTips("login ou senha inválida!");
							login.addClass( "ui-state-error" );
							senha.addClass( "ui-state-error" );
						}
																		
					})

					.error(function(data) { 
						updateTips("Erro no acesso ao sistema dotec");
					 });

				}
				

			},

			Cancel: function() {

				$( this ).dialog( "close" );

			}

		},

		close: function() {

			allFieldsLoginModal.val( "" ).removeClass( "ui-state-error" );

		}


});


</script>







