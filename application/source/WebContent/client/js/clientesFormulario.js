$(document).ready(function() {	checkPessoa();	checkTipoDeEndereco();	$('.radioTipoDeCliente').change(function() {		checkPessoa();	});
	function checkPessoa(){		var radioPessoaFisica = $("#radioPessoaFisica");		var radioPessoaJuridica = $("#radioPessoaJuridica");		var action = $("#formulario2").attr("action");				$(".divPessoa").css("display","none");		if(radioPessoaFisica.attr("checked")){			$(".divPessoaFisica").css("display","block");			action = action.replace("~#clientes#~","clientesPessoaFisica");			action = action.replace("clientesPessoaJuridica","clientesPessoaFisica");			$("#formulario2").attr("action",action);		}
		if(radioPessoaJuridica.attr("checked"))		{			$(".divPessoaJuridica").css("display","block");			action = action.replace("~#clientes#~","clientesPessoaJuridica");			action = action.replace("clientesPessoaFisica","clientesPessoaJuridica");			$("#formulario2").attr("action",action);		}
	}	
	function checkTipoDeEndereco()	{
		var indice = $('.divEndereco').length;
		if(indice == 1){
			$(".checkboxTipoDeEndereco").attr("checked","checked");
			$(".checkboxTipoDeEndereco").attr("disabled","disabled");
		}else{
			$(".checkboxTipoDeEndereco").removeAttr("disabled");
		}
	}
	$('#btnIncluiEndereco').click(function() {
		var divTipoDeLogradouroList = $("#divTipoDeLogradouroList").html();
		var divUfList               = $("#divUfList").html();
		var radioPessoaFisica = $("#radioPessoaFisica");
		var tipoDeCliente;
		
		if(radioPessoaFisica.attr("checked")){
			tipoDeCliente = "clientePessoaFisica";
		}else{
			tipoDeCliente = "clientePessoaJuridica";
		}
				
		var indice = $('.divEndereco').length;
		var divEndereco ="";
		divEndereco += '<div class="divEndereco">';
		divEndereco += 	'<div class="divPessoa divPessoaFisica">';
		divEndereco += 		'<ul>';
		divEndereco += 			'<li>';
		divEndereco += 				'<input type="button" class="btnRemoveEndereco" value="-" />';
		divEndereco += 			'</li>';
		divEndereco += 			'<li class="liTipoDeEndereco">';
		divEndereco += 				'<div>';
		divEndereco += 					'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].">Endereço de cobrança</label>';
		divEndereco += 					'<input type="checkbox" name="'+ tipoDeCliente +'.enderecos['+indice+'].enderecoDeCobranca" value="true" class="checkbox checkboxTipoDeEndereco  checkboxEnderecoDeCobranca">';
		divEndereco += 				'</div>';
		divEndereco += 				'<div>';
		divEndereco += 					'<label for="">Endereço de entrega</label>';
		divEndereco += 					'<input type="checkbox" name="'+ tipoDeCliente +'.enderecos['+indice+'].enderecoDeEntrega" value="true" class="checkbox checkboxTipoDeEndereco checkboxEnderecoDeEntrega">';
		divEndereco += 				'</div>';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>';
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].tipoDeLogradouro">Tipo de Logradouro:<span class="star">*</span></label>';
		divEndereco += 				'<select name="'+ tipoDeCliente +'.enderecos['+indice+'].tipoDeLogradouro" id="'+ tipoDeCliente +'.enderecos['+indice+'].tipoDeLogradouro">';
		divEndereco += 					'<option value=""></option>';
		divEndereco +=					divTipoDeLogradouroList;
		divEndereco += 				'</select>';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>';
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].nomeDoLogrado" class="hasTip required">Logradouro: <span class="star">&nbsp;*</span></label>';
		divEndereco += 				'<input type="text" id="'+ tipoDeCliente +'.enderecos['+indice+'].nomeDoLogrado" name="'+ tipoDeCliente +'.enderecos['+indice+'].nomeDoLogrado" value="" class="texto  " size="">';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>'; 
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].numero">Número:<span class="star">*</span> </label>';
		divEndereco += 				'<input type="text" id="'+ tipoDeCliente +'.enderecos['+indice+'].numero" name="'+ tipoDeCliente +'.enderecos['+indice+'].numero" class="numero " value="">';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>';
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].complemento" class="hasTip required">Complemento: </label>';
		divEndereco += 				'<input type="text" id="'+ tipoDeCliente +'.enderecos['+indice+'].complemento" name="'+ tipoDeCliente +'.enderecos['+indice+'].complemento" value="" class="texto" size="">';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>';
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].bairro" class="hasTip required">Bairro: <span class="star">&nbsp;*</span></label> ';
		divEndereco += 				'<input type="text" id="'+ tipoDeCliente +'.enderecos['+indice+'].bairro"	name="'+ tipoDeCliente +'.enderecos['+indice+'].bairro" value="" class="texto" size="">';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>';
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].cep">CEP: <span class="star">*</span> </label>';
		divEndereco += 				'<input type="text" id="'+ tipoDeCliente +'.enderecos['+indice+'].cep" name="'+ tipoDeCliente +'.enderecos['+indice+'].cep" class="cep" value="">';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>';
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].municipio" class="hasTip required">Munic&iacute;pio: <span class="star">&nbsp;*</span></label>';
		divEndereco += 				'<input type="text" id="'+ tipoDeCliente +'.enderecos['+indice+'].municipio" name="'+ tipoDeCliente +'.enderecos['+indice+'].municipio" value="" class="texto " size="">';
		divEndereco += 			'</li>';
		divEndereco += 			'<li>';
		divEndereco += 				'<label for="'+ tipoDeCliente +'.enderecos['+indice+'].uf" class="hasTip required">UF: <span class="star">&nbsp;*</span></label>';
		divEndereco += 				'<select name="'+ tipoDeCliente +'.enderecos['+indice+'].uf" id="'+ tipoDeCliente +'.enderecos['+indice+'].uf">';
		divEndereco += 					'<option value=""></option>';
		divEndereco +=                   divUfList;
		divEndereco +=				'</select>';
		divEndereco += 			'</li>';
		divEndereco += 		'</ul>';
		divEndereco += 	'</div>';
		divEndereco += '</div>';
		$("#divEnderecos").prepend(divEndereco);
		checkTipoDeEndereco();				$(".cep").mask("99999-999");		$(".btnRemoveEndereco").click(function() {
			$(this).parent().parent().parent().parent().remove();
			checkTipoDeEndereco();
		});
		
		$('.checkboxEnderecoDeCobranca').change(function() {
			var attrChecked = $(this).attr('checked');
			$('.checkboxEnderecoDeCobranca').removeAttr("checked");
			if (typeof attrChecked !== 'undefined' && attrChecked !== false) {
				$(this).attr("checked","checked");
			}	
		});
		
		$('.checkboxEnderecoDeEntrega').change(function() {
			var attrChecked = $(this).attr('checked');
			$('.checkboxEnderecoDeEntrega').removeAttr("checked");
			if (typeof attrChecked !== 'undefined' && attrChecked !== false) {
				$(this).attr("checked","checked");
			}	
		});
		
	});
	
	
	$('.checkboxEnderecoDeCobranca').change(function() {
		var attrChecked = $(this).attr('checked');
		$('.checkboxEnderecoDeCobranca').removeAttr("checked");
		if (typeof attrChecked !== 'undefined' && attrChecked !== false) {
			$(this).attr("checked","checked");
		}	
	});
	
	$('.checkboxEnderecoDeEntrega').change(function() {
		var attrChecked = $(this).attr('checked');
		$('.checkboxEnderecoDeEntrega').removeAttr("checked");
		if (typeof attrChecked !== 'undefined' && attrChecked !== false) {
			$(this).attr("checked","checked");
		}	
	});
	
	
	$(".btnRemoveEndereco").click(function() {
		$(this).parent().parent().parent().parent().remove();
	});
	
	
	$("FORM").submit(function() {
		$(".checkboxTipoDeEndereco").removeAttr("disabled");		$("#clientePessoaFisica\\.sexo").removeAttr("disabled");		$("#clientePessoaFisica\\.diaDeVencimento").removeAttr("disabled");		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

});