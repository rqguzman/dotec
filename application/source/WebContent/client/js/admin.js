var urlSite = null;
$(document).ready(function() {
	
	urlSite =  $("#siteUrl").val();
	
	buscaBoletoEmAberto(urlSite);});

function buscaBoletoEmAberto(urlSite){	
	var url = urlSite + "boletos/listaBoletosEmAberto";		
	$.ajax(
        {
            url: url,
            cache: false,
            dataType: "html",
            beforeSend: function () {  },
            success: function (html) {
              $("#boletosEmAberto").html(html);                  
            },
            error: function (request, status, error) {            	
            	$("#errors").append(request.responseText);
            }
        }
    );
}


function enableEmployers(clienteId){	
	
	var url = urlSite + "admin/clientes/enable/?id=" +clienteId ;
		
	$.ajax(
        {
            url: url,
            cache: false,
            dataType: "html",
            beforeSend: function () {  },
            success: function (html) {
             
              if(html=='"success"'){
            	  alert("Cliente habilitado com sucesso");
            	  document.location.reload(true);
              }
            	  
            },
            error: function (request, status, error) {            	
            	$("#errors").append(request.responseText);
            }
        }
    );
}