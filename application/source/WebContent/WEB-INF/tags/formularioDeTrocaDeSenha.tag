<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<form></form>
<div id="dialog-form-trocar-senha" title="Trocar Senha" class="dialog-form-trocar-senha" style="height:300px">
	<p class="validateTips">Todos os campos são obrigatórios</p>
	<form action="<c:url value="/admin/usuarios/trocaSenha" />">
		<fieldset>
			<label for="login">Login</label> 
			<input type="text"	name="login" id="login" class="text ui-widget-content ui-corner-all" />		
			<label for="senha">Senha Atual</label> 
			<input type="password"	name="senhaAtual" id="senhaAtual" class="text ui-widget-content ui-corner-all" /> 
			<label for="descricao">Nova Senha</label> 
			<input type="password"	name="novaSenha" id="novaSenha" class="text ui-widget-content ui-corner-all" />
			<label for="descricao">Confirmar Senha</label> 
			<input type="password"	name="confirmaSenha" id="confirmaSenha" class="text ui-widget-content ui-corner-all" />
		</fieldset>
	</form>
</div>
<script type="text/javascript">

function trocarSenha(){
	$("#dialog-form-trocar-senha").dialog("open");
}


var	 login = $( "#dialog-form-trocar-senha  #login" ),
senhaAtual = $( "#dialog-form-trocar-senha #senhaAtual" ),
novaSenha = $( "#dialog-form-trocar-senha #novaSenha" ),
confirmaSenha = $( "#dialog-form-trocar-senha #confirmaSenha" ),
allFieldsTrocarSenhaModal = $( [] ).add( login ).add( senhaAtual ).add( novaSenha ).add( confirmaSenha ),
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


function checkConfirmacaoDeSenha(oNovaSenha,oConfirmacaoDeSenha) {
	if ( oNovaSenha.val().trim() != oConfirmacaoDeSenha.val().trim() ) {
		oNovaSenha.addClass( "ui-state-error" );
		oConfirmacaoDeSenha.addClass( "ui-state-error" );
		updateTips( "Nova senha não confere com a confirmação" );
		return false;
	} else {
		return true;
	}
}

function checkRegexp( o, regexp, n ) {
    if ( !( regexp.test( o.val() ) ) ) {
      o.addClass( "ui-state-error" );
      updateTips( n );
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


$("#dialog-form-trocar-senha").dialog({

	autoOpen: false,

	height: 450,

	width: 350,

	modal: true,

	dialogClass: 'div-dialog-form', 
		
		buttons: {

			"Ok": function() {

				var bValid = true;

				allFieldsTrocarSenhaModal.removeClass( "ui-state-error" );

				bValid = bValid && checkFill( login, "Login");

				bValid = bValid && checkFill( senhaAtual, "Senha Atual");
				
				bValid = bValid && checkFill( novaSenha, "Nova Senha");
								
				bValid = bValid && checkFill( confirmaSenha, "Confirmar Senha");
								
				bValid = bValid && checkConfirmacaoDeSenha( novaSenha, confirmaSenha);
												
				if ( bValid ) {
					
					var form = $("form",this);
					var action = form.attr("action");
					var serializedFormData = form.serializeArray();
				
					$.ajax({
						  type: "POST",
						  url: action,
						  data: serializedFormData,
						  success: function(data) {
										alert(data);
										$( "#dialog-form-trocar-senha" ).dialog( "close" );
						  			}
					
						  ,error:function (jXHR, textStatus, errorThrown){ 
									if(jXHR.responseText!=null && jXHR.responseText!=""){
										alert(jXHR.responseText);
									}else{
									  	alert("Erro no processamento da solicitação.");		
									}
						  		}
						  ,dataType: "json"
					});
				}
			},

			Cancel: function() {

				$( this ).dialog( "close" );

			}

		},

		close: function() {

			allFieldsTrocarSenhaModal.val( "" ).removeClass( "ui-state-error" );

		}


});


</script>







