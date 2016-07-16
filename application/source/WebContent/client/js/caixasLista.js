$(document).ready(function() {
	
	
	
	// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
	$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
	var	 nome = $( "#dialog-form-nova-caixa  #caixa\\.nome" ),
		descricao = $( "#dialog-form-nova-caixa #caixa\\.descricao" ),
		allFields = $( [] ).add( nome ).add( descricao ),
		tips = $( ".validateTips" );

	function updateTips( t ) {
		tips
			.text( t )
			.addClass( "ui-state-highlight" );
		setTimeout(function() {
			tips.removeClass( "ui-state-highlight", 1500 );
		}, 500 );
	}

	function checkLength( o, n, min, max ) {
		if ( o.val().length > max || o.val().length < min ) {
			o.addClass( "ui-state-error" );
			updateTips( "Tamanho do campo " + n + " deve ser entre " +
				min + " e " + max + " caracteres." );
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
	
	$( "#dialog-form-nova-caixa" ).dialog({
		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		dialogClass: 'div-nova-caixa' ,
		buttons: {
			"Ok": function() {
				var bValid = true;
				allFields.removeClass( "ui-state-error" );

				bValid = bValid && checkLength( nome, "nome", 3, 20 );
				//bValid = bValid && checkLength( descricao, "descrição", 3, 100 );
				
				if ( bValid ) {
					var serializedFormData = $("form",this).serializeArray();
					$.post("adicionaAjax", serializedFormData, "json")
					.success(function() { 
						$( "#dialog-form-nova-caixa" ).dialog( "close" );
						alert("Caixa solicitada com sucesso! Aguarde o envio da DOTEC para inserir documentos.");
					})
					.error(function(data) { 
						alert("Erro: " + "Erro na criação da caixa");				 
					 });
				}
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});

	$( ".btnSolicitarCaixas" ).click(function() {
			$( "#dialog-form-nova-caixa" ).dialog( "open" );
	});
	
	
	
	
	
});