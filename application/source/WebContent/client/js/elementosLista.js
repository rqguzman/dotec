$(document).ready(function() {

	var isGuardaTecnica = $("#isGuardaTecnica").val() == "true";

	renderTree();

	setInterval('$("#tree").jstree("refresh")',300000);

	


	// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!

	$( "#dialog:ui-dialog" ).dialog( "destroy" );	



	$(".search").keyup(function () {

		$('#boxDocumentos').html("");

		var val = $(this).val();

		$("#tree").jstree('close_all');

		$("#tree").jstree("search",val);

	});


	$(".search").puts("Digite para Buscar ...");
	

	var	 nome = $( "#dialog-form-adiciona  #caixa\\.nome" ),
		descricao = $( "#dialog-form-adiciona #caixa\\.descricao" ),

		quantidade = $( "#dialog-form-adiciona #quantidade" ),
		allFields = $( [] ).add( nome ).add( descricao ),
		tips = $( ".validateTips" );	

	

	function updateTips( t ) {

		tips.text( t ).addClass( "ui-state-highlight" );

		setTimeout(function() {

			tips.removeClass( "ui-state-highlight", 1500 );

		}, 500 );

	}

	

	var	 nomeElemento = $( "#dialog-form-rename  #elementoNome" ),

		descricaoElemento = $( "#dialog-form-rename #elementoDescricao" ),

		allFieldsRename = $( [] ).add( nomeElemento ).add( descricaoElemento ),

		tips = $( ".validateTips" );


	function checkLength( o, n, min, max ) {

		if ( o.val().length > max || o.val().length < min ) {

			o.addClass( "ui-state-error" );

			updateTips( "Tamanho do campo " + n + " deve ser entre " + min + " e " + max + " caracteres." );

			

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

	

	function checkInteger(o,min,max,msg){

		   // EXPRESSAO REGULAR PARA ACEITAR APENAS NUMEROS INTEIROS

		   var reDigits = /^\d+$/;

		   var val = o.val();

		   var result = true;

		 

		   if (reDigits.test(o.val())){

			   if(val < min){

				   result = false;

			   }

			   if(val > max){

				   result = false;

			   }

		   }else{

			   result = false;

		   }

		   

		   if(!result){

			   updateTips( msg );

		   }

		   

		   return result;

	}


	


	$( "#dialog-form-adiciona" ).dialog({


		autoOpen: false,


		height: 350,


		width: 350,


		modal: true,


		dialogClass: 'div-dialog-form' ,


		buttons: {


			"Ok": function() {


				var bValid = true;


				allFields.removeClass( "ui-state-error" );


				bValid = bValid && checkLength( nome, "nome", 1, 150 );


				bValid = bValid && checkLength( descricao, "descrição", 1, 255 );

				

				if(addType=="caixa"){

				

					bValid = bValid && checkInteger( quantidade,1,99,"O número de caixas solicitadas deve estar entre 1 e 99");
									

				}

				if ( bValid ) {


					var camposInput = $("form input",this);


					var addType = $("#addType").val();

										

					if(addType=="caixa"){


						$("form input:eq(0)",this).attr("name","caixa.nome");


						$("form input:eq(1)",this).attr("name","caixa.descricao");


					}else if (addType=="pasta") {


						$("form input:eq(0)",this).attr("name","pasta.nome");


						$("form input:eq(1)",this).attr("name","pasta.descricao");


					}else if (addType=="documento"){


						$("form input:eq(0)",this).attr("name","documento.nome");
						$("form input:eq(1)",this).attr("name","documento.descricao");

												

						

					}else if (addType=="envelope"){



						$("form input:eq(0)",this).attr("name","envelope.nome");

						$("form input:eq(1)",this).attr("name","envelope.descricao");

												

						

					}

					

					var form = this;

					

					if(addType=="documento" && $("#uploadedFile").val()!=""){

						

						var uploadControllerURL  = $("#siteUrl").val() + "upload/upload";

						

						var bar = $('.bar');

						var percent = $('.percent');

						var uploadOptions = { 

							    url:  uploadControllerURL, 

							    

							    success: function(data) { 

							    						    	

							    	if(data){

							    								    	

								    	var options = { 

											    url:    $("#addController").val(), 

											    success: function(data) { 

											    									    	

											    	$( "#dialog-form-adiciona" ).dialog( "close" );

											    	$( "#dialog-form-adiciona" ).clearForm();

											    								

													if(addType=="caixa"){

														alert(data);

													}

										

													if(addType == "documento")

														carregaDocumentos($("#elementoPaiId").val());

													

													$("#tree").jstree('refresh');

											    } ,

											    error:(function (jXHR, textStatus, errorThrown) { 

													

													 if(jXHR.responseText!=null && jXHR.responseText!=""){

														

														if(addType=="caixa" || addType=="documento"){

															alert(jXHR.responseText);

														}else{

															alert("Erro no processamento da solicitação.");		

														}

													}else{

							

										            	alert("Erro no processamento da solicitação.");		

							

										            }

												}),

												beforeSubmit: function() {

																					

													

													

													

													

											    },dataType:'json'

										}; 

															

										$("form",form).ajaxSubmit(options);

									

							    	}else{

							    		$(".progress").css("visibility","hidden");

							    		alert("O arquivo pdf deve ter no máximo 2 megas!");

							    	}

							    						    		

									

							    } ,

							    error:(function (jXHR, textStatus, errorThrown) {

							    	$(".progress").css("visibility","hidden");

							    	

							    	if(jXHR.responseText!=null && jXHR.responseText!=""){

										alert(jXHR.responseText);

									}else{

										alert("Erro no processamento da solicitação.");		

									}

							    }),

								beforeSend: function() {

									var percentVal = '0%';

							        bar.width(percentVal);

							        percent.html(percentVal);

							        $(".progress").css("visibility","visible");

							    },

							    uploadProgress: function(event, position, total, percentComplete) {

							        var percentVal = percentComplete + '%';

							        bar.width(percentVal);

							        percent.html(percentVal);

							    },dataType:'json'

						};

						$("form",form).ajaxSubmit(uploadOptions);

						

						

					}else{

						

						var options = { 

							    url:    $("#addController").val(), 

							    success: function(data) { 

							    	$( "#dialog-form-adiciona" ).dialog( "close" );

							    	$( "#dialog-form-adiciona" ).clearForm();

							    								

									if(addType=="caixa"){

										alert(data);

									}

						

									if(addType == "documento")

										carregaDocumentos($("#elementoPaiId").val());

									

									$("#tree").jstree('refresh');

							    } ,

							    error:(function (jXHR, textStatus, errorThrown) { 

									

									 if(jXHR.responseText!=null && jXHR.responseText!=""){

										

										if(addType=="caixa" || addType=="documento"){

											alert(jXHR.responseText);

										}else{

											alert("Erro no processamento da solicitação.");		

										}

									}else{

			

						            	alert("Erro no processamento da solicitação.");		

			

						            }

								}),

								beforeSubmit: function() {

																	

									

									

									

									

							    },dataType:'json'

						}; 

											

						$("form",form).ajaxSubmit(options);

						

					}

							

				}

				


			},


			Cancel: function() {
				$( this ).dialog( "close" );

				$( this ).clearForm();


			}


		},


		close: function() {


			allFields.val( "" ).removeClass( "ui-state-error" );
			$( this ).clearForm();

		}


	});

	

	$( "#dialog-form-rename" ).dialog({



		autoOpen: false,



		height: 300,



		width: 350,



		modal: true,



		dialogClass: 'div-dialog-form' ,



		buttons: {



			"Ok": function() {



				var bValid = true;



				allFieldsRename.removeClass( "ui-state-error" );



				bValid = bValid && checkLength( nomeElemento, "nome", 1, 150 );



				bValid = bValid && checkLength( descricaoElemento, "descrição", 1, 255 );

			

				if ( bValid ) {



					var camposInput = $("form input",this);



					var serializedFormData = $("form",this).serializeArray();



					$.post("renomear", serializedFormData, "json")



					.success(function() {



						$( "#dialog-form-rename" ).dialog( "close" );

						

						alert("Renomeado com sucesso.");

								

						$("#tree").jstree('refresh');

					

					})



					.error(function(data) { 

						alert("Erro ao renomear elemento");				 



					 });



				}



			},



			Cancel: function() {



				$( this ).dialog( "close" );



			}



		},



		close: function() {



			allFieldsRename.val( "" ).removeClass( "ui-state-error" );



		}



	});

	


	$( ".btnRefresh" ).click(function() {

		$("#tree").jstree("refresh");

	});




	$( ".btnCriarCaixa" ).click(function() {


		$("#elementoPaiId").val("");


		criarCaixa();


	});


	


	$( ".btnCriarPasta" ).click(function() {


		$("#elementoPaiId").val("");


		criarPasta();


	});


	


	function criarCaixa(){

		

		$(".quantidade").css("display","block");

		

		$(".uploadedFile").css("display","none");

		

		$(".progress").css("visibility","hidden");

				

		$("#quantidade").val(1);


		$("#addType").val("caixa");


		$("#addController").val("adicionaCaixa");


		$( "#dialog-form-adiciona").dialog({ title: 'Solicitar Caixa' });


		$( "#dialog-form-adiciona" ).dialog( "open" );


	}


	


	function devolverElemento(id){


		$.ajax({


	        url : "devolverElemento",

	        type: "post",


	        dataType: "json",


	        data: "id=" + id,


	        success: function (data) {
	        	alert(data);

	            $("#tree").jstree('refresh');
	        },


	        error: function (jXHR, textStatus, errorThrown) {


	            if(jXHR.responseText!=null && jXHR.responseText!=""){


	            	alert(jXHR.responseText);


	            }else{


	            	alert("Erro no processo de devolução da caixa!");


	            }


	        }


	    });


	}

	

	

	function solicitarElemento(id){



		$.ajax({



	        url : "solicitarElemento",



	        type: "post",



	        dataType: "json",



	        data: "id=" + id,



	        success: function (data) {

	        	alert(data);

	            $("#tree").jstree('refresh');

	        },



	        error: function (jXHR, textStatus, errorThrown) {



	            if(jXHR.responseText!=null && jXHR.responseText!=""){



	            	alert(jXHR.responseText);



	            }else{



	            	alert("Erro no processo de solicitação da caixa!");



	            }



	        }



	    });



	}


	


	function removerElemento(id,element,obj){


		$.ajax({


	        url : "removerElemento",


	        type: "post",


	        dataType: "json",


	        data: "id=" + id,


	        success: function (data) {


	            element.delete_node(obj);


	        	alert(data);


	        },


	        error: function (jXHR, textStatus, errorThrown) {


	            if(jXHR.responseText!=null && jXHR.responseText!=""){


	            	alert(jXHR.responseText);


	            }else{


	            	alert("Erro ao remover elemento!");


	            }


	        }


	    });


	}


	


	function removerDocumento(id,element,obj){


		$.ajax({


	        url : "removerDocumento",


	        type: "post",


	        dataType: "json",


	        data: "id=" + id,


	        success: function (data) {


	            element.delete_node(obj);


	        	alert(data);


	        },


	        error: function (jXHR, textStatus, errorThrown) {


	            if(jXHR.responseText!=null && jXHR.responseText!=""){


	            	alert(jXHR.responseText);


	            }else{


	            	alert("Erro ao remover elemento!");


	            }


	        }


	    });


	}

	

	function cancelarMovimentacaoPendente(id,element,obj){



		$.ajax({



	        url : "cancelarMovimentacaoPendente",



	        type: "post",



	        dataType: "json",



	        data: "id=" + id,



	        success: function (data) {



	            alert(data);

	            

	            $("#tree").jstree('refresh');



	        },



	        error: function (jXHR, textStatus, errorThrown) {



	            if(jXHR.responseText!=null && jXHR.responseText!=""){



	            	alert(jXHR.responseText);



	            }else{



	            	alert("Erro ao remover elemento!");



	            }



	        }



	    });
	}

	


	function removerPasta(id,element,obj){


		$.ajax({


	        url : "removerPasta",


	        type: "post",


	        dataType: "json",


	        data: "id=" + id,


	        success: function (data) {


	            element.delete_node(obj);


	        	alert(data);


	        },


	        error: function (jXHR, textStatus, errorThrown) {


	            if(jXHR.responseText!=null && jXHR.responseText!=""){


	            	alert(jXHR.responseText);


	            }else{


	            	alert("Erro ao remover elemento!");


	            }


	        }


	    });


	}

	

	function removerEnvelope(id,element,obj){



		$.ajax({



	        url : "removerEnvelope",



	        type: "post",



	        dataType: "json",



	        data: "id=" + id,



	        success: function (data) {



	            element.delete_node(obj);



	        	alert(data);



	        },



	        error: function (jXHR, textStatus, errorThrown) {



	            if(jXHR.responseText!=null && jXHR.responseText!=""){



	            	alert(jXHR.responseText);



	            }else{



	            	alert("Erro ao remover envelope!");



	            }



	        }



	    });



	}

	

	

	function renomearElemento(id,element,obj,rel){

		var tipo = "";

		

		if(rel == "caixa"){

			tipo = "Caixa";

		}

		

		if(rel == "pasta"){

			tipo = "Pasta";

		}

		

		if(rel == "documento"){

			tipo = "Documento";

		}

		

		if(rel == "envelope"){

			tipo = "Envelope";

		}

		

		$("#elementoId").val(id);

		

		$("#elementoNome").val("");

		

		$("#elementoDescricao").val("");

		

		$("#dialog-form-rename").dialog({ title: 'Renomear ' + tipo });



		$("#dialog-form-rename" ).dialog( "open" );

	}


	function criarPasta(){

		

		$(".quantidade").css("display","none");

		

		$(".uploadedFile").css("display","none");

		

		$(".progress").css("visibility","hidden");


		$("#addType").val("pasta");


		$("#addController").val("adicionaPasta");


		$( "#dialog-form-adiciona").dialog({ title: 'Criar Pasta' });


		$( "#dialog-form-adiciona" ).dialog( "open" );


	}

	

	function criarEnvelope(){

		

		$(".quantidade").css("display","none");

		

		$(".uploadedFile").css("display","none");

		

		$(".progress").css("visibility","hidden");



		$("#addType").val("envelope");



		$("#addController").val("adicionaEnvelope");



		$( "#dialog-form-adiciona").dialog({ title: 'Criar Envelope' });



		$( "#dialog-form-adiciona" ).dialog( "open" );



	}




	


	function criarDocumento(){

		

		$(".quantidade").css("display","none");	

		

		$(".uploadedFile").css("display","block");

		

		$(".progress").css("visibility","hidden");


		$("#addType").val("documento");


		$("#addController").val("adicionaDocumento");


		$( "#dialog-form-adiciona").dialog({ title: 'Adicionar Documento' });


		$( "#dialog-form-adiciona" ).dialog( "open" );


	}


	


	function carregaDocumentos(id){


		


		$.ajax({


	        url : "carregaDocumento",


	        type: "post",


	        dataType: "html",


	        data: "id=" + id,


	        success: function (data) {


	          			


			$('#boxDocumentos').html(data);


	        },


	        error: function (jXHR, textStatus, errorThrown) {


	            if(jXHR.responseText!=null && jXHR.responseText!=""){


	            	alert(jXHR.responseText);


	            }else{


	            	alert("Erro ao carregar os documentos!");


	            }


	        }


	    });


	}


	


	


	function customMenu(node) {	    	  


	    var id = $(node).attr("id");

	    var rel= $(node).attr("rel");


	  


	    var items = {


	    	addBox: { // The "rename" menu item



	        	label: "Nova caixa",



	        	action: function () {



	        		$("#elementoPaiId").val(id);



	        		criarCaixa();



	        	}



	        },	

	        

	        addPath: { // The "rename" menu item



	        	label: "Criar Pasta",



	        	action: function () {



	        		$("#elementoPaiId").val(id);



	        		criarPasta();



	        	}



	        },

	        

	        addDocument: { // The "rename" menu item



	    		label: "Adicionar documento",



	    	    action: function () {



	    	    	$("#elementoPaiId").val(id);



	    	    	criarDocumento();



	    	    }



	    	},

	    	

	    	addEnvelope: { // The "rename" menu item



	    		label: "Adicionar envelope",



	    	    action: function () {



	    	    	$("#elementoPaiId").val(id);



	    	    	criarEnvelope();



	    	    }



	    	},


	    	requestBox: { // The "rename" menu item


	    		label: "Solicitar Caixa",


	    		 action: function () {



	    			 solicitarElemento(id);



		    	 }


	    	},

	    	

	    	requestEnvelope: { // The "rename" menu item



	    		label: "Solicitar Envelope",



	    		 action: function () {



	    			 solicitarElemento(id);



		    	 }



	    	},

	    	

	    	requestDocument: { // The "rename" menu item



	    		label: "Solicitar Documento",



	    		 action: function () {



	    			 solicitarElemento(id);



		    	 }



	    	},



	    	

	    	backBox: { // The "rename" menu item


	    		label: "Devolver Caixa",


	    	    action: function () {


	    	    	devolverElemento(id);


	    	    }


	    	},

	    	

	    	backEnvelope: { // The "rename" menu item



	    		label: "Devolver Envelope",



	    	    action: function () {



	    	    	devolverElemento(id);



	    	    }



	    	},

	    	

	    	backDocument: { // The "rename" menu item



	    		label: "Devolver Documento",



	    	    action: function () {



	    	    	devolverElemento(id);



	    	    }



	    	},

	    	


	    	deleteElement: { // The "delete" menu item


	            label: "Deletar",


	            action: function (obj) { 


	            	var element = this;


	            	removerElemento(id,element,obj);


	            }


	        },


	    	deletePath: { // The "delete" menu item


	            label: "Deletar",


	            action: function (obj) { 


	            	var element = this;


	            	removerPasta(id,element,obj);


	            }


	        },

	        

	        cancelMovePending: { // The "delete" menu item



	            label: "Cancelar movimentação pendente",



	            action: function (obj) { 



	            	var element = this;



	            	cancelarMovimentacaoPendente(id,element,obj);



	            }

	        }

	        ,



	    	renameElement: { 



	            label: "Renomear",



	            action: function (obj) { 



	            	var element = this;



	            	renomearElemento(id,element,obj,rel);



	            }

	        }


	    };


	   

	    if ($(node).attr("rel")=="pasta") {
	    	delete items.addDocument;
	    	delete items.backBox;
	    	delete items.deleteElement;

	    	delete items.addEnvelope;

	       	delete items.cancelMovePending;

	       	delete items.requestBox;

	    	delete items.requestEnvelope;

	    	delete items.backEnvelope;

	    	delete items.requestDocument;

	    	delete items.backDocument;
	    }

	    

	    if ($(node).attr("rel")=="documento") {

	    	delete items.addPath;

	    	delete items.requestBox;

	    	delete items.addDocument;

	    	delete items.backBox;

	    	delete items.deletePath;

	    	delete items.addBox;

	    	delete items.addEnvelope;

	    	delete items.requestEnvelope;

	    	delete items.backEnvelope;

	    	

	    	if ($(node).attr("statusDaMovimentacao")=="EM_ANDAMENTO" || $(node).attr("statusDaMovimentacao")=="PENDENTE") {

	    		delete items.requestDocument;

		    	delete items.backDocument;

		    	delete items.deleteElement;

		    	if ($(node).attr("statusDaMovimentacao")=="EM_ANDAMENTO") {

		    		delete items.cancelMovePending;

		    	}

		    }else{

	    		delete items.cancelMovePending;

	    		

	    		if ($(node).attr("statusDaCaixa")=="CLIENTE") {

			    	delete items.requestDocument;

		    	}	

		    	if ($(node).attr("statusDaCaixa")=="DOTEC") {

			    	delete items.backDocument;

		    	}

		    }

	    	

	    	

	    }


	    if ($(node).attr("rel")=="caixa") {

	    	delete items.addBox;

	    	delete items.addPath;

	    	delete items.deleteElement;

	    	delete items.deletePath;

	    	delete items.requestEnvelope;

	    	delete items.backEnvelope;

	    	delete items.requestDocument;

	    	delete items.backDocument;

	    	
	      	if ($(node).attr("statusDaMovimentacao")=="EM_ANDAMENTO" || $(node).attr("statusDaMovimentacao")=="PENDENTE") {

	    		delete items.requestBox;

		    	delete items.backBox;

		    	if ($(node).attr("statusDaMovimentacao")=="EM_ANDAMENTO") {

		    		delete items.cancelMovePending;

		    	}

		    }else{

	    		delete items.cancelMovePending;

	    		

	    		if ($(node).attr("statusDaCaixa")=="CLIENTE") {

			    	delete items.requestBox;

		    	}	

		    	if ($(node).attr("statusDaCaixa")=="DOTEC") {

			    	delete items.backBox;

		    	}

		    }

	    	

	    }
    

	    if ($(node).attr("rel")=="envelope") {

	    	delete items.addBox;

	    	delete items.addPath;

	    	delete items.addEnvelope;

	    	delete items.deletePath;

	    	delete items.requestBox;

	    	delete items.backBox;

	    	delete items.requestDocument;

	    	delete items.backDocument;

	    	

	      	if ($(node).attr("statusDaMovimentacao")=="EM_ANDAMENTO" || $(node).attr("statusDaMovimentacao")=="PENDENTE") {

	    		delete items.requestEnvelope;

		    	delete items.backEnvelope;

		    	delete items.deleteElement;

		    	if ($(node).attr("statusDaMovimentacao")=="EM_ANDAMENTO") {

		    		delete items.cancelMovePending;

		    	}

		    }else{

	    		delete items.cancelMovePending;

	    		if ($(node).attr("statusDaCaixa")=="CLIENTE") {

			    	delete items.requestEnvelope;

		    	}	

		    	if ($(node).attr("statusDaCaixa")=="DOTEC") {

			    	delete items.backEnvelope;

		    	}

		    }

	    }

	    

	    if(!isGuardaTecnica){

	    	delete items.requestEnvelope;

	    	delete items.backEnvelope;

	    	delete items.requestDocument;

	    	delete items.backDocument;

	    }

	    

	    return items;
	}



	function renderTree(){


		


		$("#tree").jstree({ 


			"json_data" : {


				"ajax" : {


					"url" : "nodesJson?"+Math.floor(Math.random()*11),					


					"data" : function (n) {	return { id : n.attr ? n.attr("id") : 0 };					}


				}


			},


			 "search" : {		


				 "show_only_matches" : true,

				 "search_method":"jstree_contains_multi"


		    },





			"types" : {


				"valid_children" : [ "caixa","pasta" ],


				"select_node": true,


				"types" : {


					"pasta" : {


						"valid_children" : [ "caixa","pasta" ],


						"icon" : {


							"image" : $("#pathImageURL").val()


						},


						"select_node": true


					},


					// The `folder` type


					"caixa" : {


							"valid_children" : [ "documento","envelope" ],


							"icon" : {


							"image" : $("#boxImageURL").val()


						},


						"select_node": true


					},



					// The `folder` type



					"documento" : {



							"valid_children" : [ "none" ],



							"icon" : {



							"image" : $("#documentImageURL").val()



						},



						"select_node": true



					},

					

					"envelope" : {



							"valid_children" : [ "documento" ],

	

							"icon" : {

	

							"image" : $("#envelopeImageURL").val()

	

						},

	

						"select_node": true

	

					}

					
					


				}


			},


			"contextmenu":


			{


				items: customMenu


			},


			// "plugins": ["themes", "json_data", "types", "dnd", "sort",  "hotkeys", "contextmenu", "ui"]


			"plugins" :   ["themes", "json_data", "types", "dnd", "search", "sort", "contextmenu", "ui"]


		}).bind("move_node.jstree", function(e, data) {


			var elementoId = $(data.rslt.o).attr("id");


			var elementoPaiId = $(data.rslt.r).attr("id");


			


			if(data.rslt.p != "inside"){


				elementoPaiId = null;


			}


			


			$.post("updateElementoPai", { "elementoId": elementoId, "elementoPaiId": elementoPaiId } , "json")


			.error(function(data) { 


				alert("Erro: " + "Erro ao movimentar objeto no servidor.");				 


			});


							


		}).bind("select_node.jstree", function (event, data) {            	        	
			

			carregaDocumentos($(data.rslt.obj).attr("id"));    

			

        	if((data.inst._get_parent(data.rslt.obj)).length) { 


                data.inst.open_node(data.rslt.obj, true,true); 


             } 


      })


        // 2) if not using the UI plugin - the Anchor tags work as expected





       //    so if the anchor has a HREF attirbute - the page will be changed





        //    you can actually prevent the default, etc (normal jquery usage)





      //  .delegate("a", "click", function (event, data) { event.preventDefault();});





	}





	


	


	


	
	// my function starts here

    $.expr[':'].jstree_contains_multi = function(a,i,m){



	    var word, words = [];

	    var searchFor = m[3].toLowerCase();

	    if(searchFor.indexOf(' ') >= 0) {

	        words = searchFor.split(' ');

	    }

	    else {

	        words = [searchFor];

	    }

	       

	    for (var i=0; i < words.length; i++) {

	        word = words[i];

	        if((a.textContent || a.innerText || "").toLowerCase().indexOf(word) >= 0) {

	            return true;

	        }

	        if((a.title).toLowerCase().indexOf(word) >= 0) {

	            return true;

	        }

	    }

	    return false;

	

	};



	// my function ends here

	$.expr[':'].jstree_contains = function(a,i,m){

	

	    return (a.textContent || a.innerText || "").toLowerCase().indexOf(m[3].toLowerCase())>=0;

	

	};

	


	


	


	


	


	


	


});














