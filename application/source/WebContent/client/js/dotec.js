//Produção
var urlSite ="http://www.dotec.com.br";
//Qualidade
//var urlSite ="http://www.dotec.com.br/qa";
//Qualidade
//var urlSite ="http://www.dotec.com.br/demo";
//-- Desenvolvimento
//var urlSite ="http://localhost:8080/Dotec";



$.fn.clearForm = function () {
    return this.each(function () {
        var type = this.type, tag = this.tagName.toLowerCase();
        if (tag == 'form')
            return $(':input', this).clearForm();
        if (type == 'text' || type == 'password' || tag == 'textarea')
            this.value = '';
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
};

$.ajaxSetup({
    global: false,
    type: "POST",
    contentType: "application/x-www-form-urlencoded;charset=ISO-8859-1"
});

function closeAllFormFilters() {
    
    $(".showFormHide").each(
            function () {
                divHide = "#form" + $(this).attr("id");
                $(divHide).slideUp();
            }
    );

}



function isChecked(){
	

}


function forgotLogin(){
	if (!$(".login").is(":hidden") && $(".esqueceu").is(":hidden")) {
		$(".login").hide();
		$(".esqueceu").fadeIn(500);
	}else{
		$(".esqueceu").hide();
		$(".login").fadeIn(500);
	}	
}

function backLogin(){
	
		if (!$(".login").is(":hidden") && $(".esqueceu").is(":hidden")) {
			$(".login").hide();
			$(".esqueceu").fadeIn(500);
		}else{
			$(".esqueceu").hide();
			$(".login").fadeIn(500);
		}
	}	
	



$().ready(function () {

	
	$("#cepSearch").click( function(){
		txtEmail = $(".email").val();
        txtNome = $(".nome").val();
        txtTelefone = $(".telefone").val();
        txtMensagem = $(".mensagem").val();

        var url = urlSite + "/faleconosco/?";        
        url = url + "faleconosco.nome=" + txtNome;
        url = url + "&faleconosco.email=" + txtEmail;
        url = url + "&faleconosco.telefone=" + txtTelefone;
        url = url + "&faleconosco.mensagem=" + txtMensagem;

        $.ajax(
        		{
        			url: url,
	                  cache: false,
	                  dataType: "html",
	                  type: 'POST',
	                  beforeSend: function() { setLoading(); },
	                  success: function(html) {
	                       
	                        setFormSucessMessage("Mensagem enviada com sucesso!");
	                        $(".email").val("");
	                        $(".nome").val("");
	                        $(".telefone").val("");
	                        $(".mensagem").val("");
	                        
	                        setLoadingFalse(); 
	                  },
	                  error: function() {
	                        setLoadingFalse(); 
	                        setFormErrorMessage("Ocorreu algum erro na aplicação.");
	                  }
	              }
	          );

    });
    
	
	});
	
	if($("#radioPessoaFisica:checked").val()=='pf')
	{
		
		$(".divPessoaFisica").show();
		$(".divPessoaJuridica").hide();
	}else{
		$(".divPessoaFisica").hide();
		$(".divPessoaJuridica").show();
	}
	
	$("#forgot-login").click (
			
		function () {
			
			if (!$(".login").is(":hidden") && $(".esqueceu").is(":hidden")) {
				$(".login").hide();
				$(".esqueceu").fadeIn(500);
			}else{
				$(".esqueceu").hide();
				$(".login").fadeIn(500);
			}
		}
	);
	
	
	
	
	$("#back-login").click (
		function () {
			if (!$(".login").is(":hidden") && $(".esqueceu").is(":hidden")) {
				$(".login").hide();
				$(".esqueceu").fadeIn(500);
			}else{
				$(".esqueceu").hide();
				$(".login").fadeIn(500);
			}
		}
	);
	
	$("#loadingPanel").fixedPosition();

    $("#dialog").dialog({
        resizable: false,
        height: 160,
        modal: true,
        autoOpen: false,
        buttons: {
            "Alterar": function () {
                alterarSenha();
            },
            "Cancelar": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#inpSearch").focus(
        function () {
            if ($(this).val() == "Buscar documentos")
                $(this).val("");
        }
    );

    $("#inpSearch").blur(
        function () {
            if ($(this).val() == "")
                $(this).val("Buscar documentos");
        }
    );


    $("#modalDocumentos").dialog({
        resizable: false,
        height: 400,
        modal: true,
        autoOpen: false,
        width: 805,
        closeOnEscape: true
    });


    $("#modalFaturas").dialog({
        resizable: false,
        height: 400,
        modal: true,
        autoOpen: false,
        width: 805,
        closeOnEscape: true
    });

    $(".linkFatura").click(
        function () {
            $("#modalFaturas").dialog("open");
        }
    );

    $(".alterPass").click(
        function () {
            $("#dialog").dialog("open");
        }
    );

    $(".trCaixaOpen").click(
        function () {
            var idCaixa = $(this.parentNode.parentNode).attr("id");
            openModalDocumentos(idCaixa);
        }
    );


    $(".showFormHide").click(
        function () {

            closeAllFormFilters();

            divHide = "#form" + $(this).attr("id");

            if ($(divHide).is(":hidden")) {
                $(divHide).slideDown();
            }
        }
    );

    $(".slide h2").click(
        function () {

            $(".info").slideUp();

            var parent = $(this).parent();
            var div = $(parent).attr("id");
            var obj = $("." + div);

            if ($(obj).is(":hidden"))
                $(obj).slideDown();
            else
                $(obj).slideUp();
        }
    );



    setValidacaoEndereco();



    if ($("#frmFaleConosco").length > 0) {
        $("#frmFaleConosco").validate({
            rules: {
        		'faleconosco.email': { required: true, email: true },
                'faleconosco.nome': "required",
                'faleconosco.telefone': "required",
                'faleconosco.mensagem': "required"
            },
            messages: {
            	'faleconosco.email': "*",
            	'faleconosco.nome': "*",
            	'faleconosco.telefone': "*",
            	'faleconosco.mensagem': "*"
            }
        });
    }

    if ($("#frmNovaSenha").length > 0) {
        $("#frmNovaSenha").validate({
            rules: {
                txtSenhaAtual: "required",
                txtNovaSenha: "required"
            },
            messages: {
                txtSenhaAtual: "*",
                txtNovaSenha: "*"
            }
        });
    }


    if ($("#frmCadastro").length > 0) {

        $(".rd").click(function () {
            if ($(this).val() == "PJ") {
                $("#lblNome").html("Razão Social:");
                $("#lblCPF").html("CNPJ:");
                $("#txtCpfCnpj").val("");
            }
            else {
                $("#lblNome").html("Nome:");
                $("#lblCPF").html("CPF:");
                $("#txtCpfCnpj").val("");
            }

        });

        $("#endMov").change(
        function () {
            if ($(this).val() == 0) {
                $("#txtlogradouroMov").val("");
                $("#txtNumeroMov").val("");
                $("#txtComplementoMov").val("");
                $("#txtBairroMov").val("");
                $("#txtCidadeMov").val("");
                $("#txtCepMov").val("");
                $("#txtUFMov").val("");
                $("#pnlEndMovimentacao").fadeIn(400);
            } else {
                $("#pnlEndMovimentacao").hide();
                $("#txtlogradouroMov").val($("#txtlogradouro").val());
                $("#txtNumeroMov").val($("#txtNumero").val());
                $("#txtComplementoMov").val($("#txtComplemento").val());
                $("#txtBairroMov").val($("#txtBairro").val());
                $("#txtCidadeMov").val($("#txtCidade").val());
                $("#txtCepMov").val($("#txtCep").val());
                $("#txtUFMov").val($("#txtUF").val());
            }
        }
        );


        $("#txtTelefone").setMask();
        $("#txtCelular").setMask();
        $("#txtCep").setMask();
        $("#txtNumero").setMask();
        $("#txtNumeroMov").setMask();

        $("#frmCadastro").validate({
            rules: {
                txtNomeRazaoSocial: "required",
                txtCpfCnpj: { required: true },
                txtEmail: { required: true, email: true },
                txtTelefone: "required",
                txtCelular: "required",
                txtSenha: "required",
                txtlogradouro: "required",
                txtNumero: "required",
                txtBairro: "required",
                txtCidade: "required",
                txtCep: "required",
                txtUF: "required",
                endMov: "required",
                txtSenha: "required"
            },
            messages: {
                txtNomeRazaoSocial: "*",
                txtCpfCnpj: "*",
                txtEmail: { required: "*", email: "*" },
                txtTelefone: "*",
                txtCelular: "*",
                txtSenha: "*",
                txtlogradouro: "*",
                txtNumero: "*",
                txtBairro: "*",
                txtCidade: "*",
                txtCep: "*",
                txtUF: "*",
                endMov: "*",
                txtSenha: "*"
            }
        });
    }


    $("#root").jstree({
        "plugins": ["themes", "xml_data", "crrm", "dnd", "sort", "types", "hotkeys", "contextmenu", "ui"],
        "xml_data": {
            "ajax": {
                "url": "/application/actions/act_Caixas.asp?task=mountTree&sid=" + Math.random(),
                "data": function (n) {
                    return {
                        id: n.attr ? n.attr("id") : 0,
                        rand: new Date().getTime()
                    };
                }
            }
        }
        ,
        "themes": {
            "theme": "classic",
            "url": "/shared/js/themes/classic/sty   le.css",
            "dots": true,
            "icons": true
        },
        "dnd": {
            "drop_finish": function () {
                
            },
            "drag_check": function (data) {
                if (data.r.attr("id") == "1") {
                    return false;
                }
                return {
                    after: false,
                    before: false,
                    inside: true
                };
            },
            "drag_finish": function (data) {

                var element = $(data.o);
                var idFolder = data.r.attr("id");
                var idBox = $(element).attr("id").replace('drag_', '');

                $.ajax({
                    async: false,
                    type: 'POST',
                    url: "/application/actions/act_Caixas.asp?task=moveToFolder&sid=" + Math.random(),
                    data: {
                        "operation": "moveToFolder",
                        "idFolder": idFolder,
                        "idBox": idBox
                    },
                    success: function (r) {
                       
                    }
                });

            }
        },
        "types": {
            "max_depth": 50,
            "max_children": 50,
            "valid_children": "all",
            "select_node": true,
            "open_node": true,
            "close_node": false,
            "create_node": true,
            "delete_node": true
        }
    })
		.bind("create.jstree", function (e, data) {


		    var url = "/application/actions/act_Caixas.asp?task=createFolder";
		    url = url + "&sid=" + Math.random();
		    url = url + "&id=" + data.rslt.parent.attr("id");
		    url = url + "&title=" + $.URLEncode(data.rslt.name);


		    $.ajax(
		                          {
		                              url: url,
		                              cache: false,
		                              dataType: "json",
		                              success: function (r) {
		                                  if (r.status) {
		                                      $(data.rslt.obj).attr("id", r.id);
		                                  }
		                                  else {
		                                      $.jstree.rollback(data.rlbk);
		                                  }
		                              }
		                          }
		                      );



		})
		.bind("remove.jstree", function (e, data) {
		    data.rslt.obj.each(function () {
		        $.ajax({
		            async: false,
		            type: 'POST',
		            url: "/application/actions/act_Caixas.asp?task=deleteFolder&sid=" + Math.random(),
		            data: {
		                "operation": "remove_node",
		                "id": this.id
		            },
		            success: function (r) {
		                if (!r.status) {
		                    data.inst.refresh();
		                }
		            }
		        });
		    });
		})
		.bind("rename.jstree", function (e, data) {

		    var url = "/application/actions/act_Caixas.asp?task=renameFolder";
		    url = url + "&sid=" + Math.random();
		    url = url + "&id=" + data.rslt.obj.attr("id");
		    url = url + "&title=" + $.URLEncode(data.rslt.new_name);


		    $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "json",
                          success: function (r) {
                              if (r.status != 1) {
                                  $.jstree.rollback(data.rlbk);
                              }
                          }
                      }
                  );



		})
        .bind("click.jstree", function (e, data) {
            var node = $(e.target).closest("li");
            GetCaixasByTree($(node).attr("id"));
        })
		.bind("move_node.jstree", function (e, data) {
		    data.rslt.o.each(function (i) {
		        $.ajax({
		            async: false,
		            type: 'POST',
		            url: "/application/actions/act_Caixas.asp?task=moveFolder&sid=" + Math.random(),
		            data: {
		                "operation": "move_node",
		                "id": $(this).attr("id"),
		                "ref": data.rslt.np.attr("id"),
		                "position": data.rslt.cp + i,
		                "title": data.rslt.name,
		                "copy": data.rslt.cy ? 1 : 0
		            },
		            success: function (r) {
		                if (!r.status) {
		                    $.jstree.rollback(data.rlbk);
		                }
		                else {
		                    $(data.rslt.oc).attr("id", r.id);
		                    if (data.rslt.cy && $(data.rslt.oc).children("UL").length) {
		                        data.inst.refresh(data.inst._get_parent(data.rslt.oc));
		                    }
		                }
		            }
		        });
		    });
		});

function setLoading() {
    messagesVerify();
    $("#loadingPanel").show();
}

function setLoadingFalse() {
    $("#loadingPanel").hide();
}

function messagesVerify() {
    if (!$("#msgErro").is(":hidden")) $("#msgErro").hide();
    if (!$("#msgSucess").is(":hidden")) $("#msgSucess").hide();
}

function setErrorMessage(msg) {
    $("#msgSystem").removeClass("sucess");
    $("#msgSystem").removeClass("error");
    $("#msgSystem").addClass("error");
    $("#msgSystem").html(msg);
    $("#msgSystem").fadeIn(500, function() { setTimeout(function() { $("#msgSystem").fadeOut("slow"); }, 5000); });
    $("html").scrollTop(0);
}

function setSucessMessage(msg) {
    $("#msgSystem").removeClass("sucess");
    $("#msgSystem").removeClass("error");
    $("#msgSystem").addClass("sucess");
    $("#msgSystem").html(msg);
    $("#msgSystem").fadeIn(500, function() { setTimeout(function() { $("#msgSystem").fadeOut("slow"); }, 5000); });
    $("html").scrollTop(0);
}

function setFormErrorMessage(msg) {
setErrorMessage(msg);
//    $("#formMsg").removeClass("sucess");
//    $("#formMsg").removeClass("error");
//    $("#formMsg").addClass("error");
//    $("#formMsg").html(msg);
//    $("#formMsg").fadeIn(500, function () { setTimeout(function () { $("#formMsg").fadeOut("slow"); }, 5000); });
//    $("html").scrollTop(100);

}

function setFormSucessMessage(msg) {
  setSucessMessage(msg);
    
//    $("#formMsg").removeClass("sucess");
//    $("#formMsg").removeClass("error");
//    $("#formMsg").addClass("sucess");
//    $("#formMsg").html(msg);
//    $("#formMsg").fadeIn(500, function () { setTimeout(function () { $("#formMsg").fadeOut("slow"); }, 5000); });
//    $("html").scrollTop(100);
  //
}

function getFormPage() {
    return $('form');
}

function selectAll() {
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";

    jQuery.each($(frmChildElements), function () {
        this.checked = $(frmChildElements)[0].checked;
    });

}

function uncheckAll() {
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";

    jQuery.each($(frmChildElements), function () {
        this.checked = false;
    });
}

function countCheckSelected() {
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";

    count = 0;

    jQuery.each($(frmChildElements), function () {
        if (this.checked) count++;
    });

    return count;
}

function valueCheckSelected() {
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";
    var value = "";
    jQuery.each($(frmChildElements), function () {
        if (this.checked) {
            value = this.value;
        }
    });
    return value;
}

function getAllValuesSelected() {

    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";
    var value = "";

    jQuery.each($(frmChildElements), function () {
        if (this.checked && (this.value != $(frmChildElements)[0].value)) {
            value += this.value + "|";
        }
    });

    return value;

}


function showFormModal(obj) {

    $("#txtDocAssunto").val("");
    $("#txtDocTitle").val("");
    $("#txtDocDesc").val("");
    $("#idDoc").val("");

    $(".formModal").slideUp(500);

    divShow = $("#" + obj);

    if ($(divShow).is(":hidden"))
        $(divShow).slideDown(500);
    else
        $(divShow).slideUp(500);
}


function showFormTab(obj) {

    if (obj == "frmEnderecos")
        $("#frmCadEndereco").clearForm();

    divShow = $("#" + obj);

    if ($(divShow).is(":hidden"))
        $(divShow).slideDown(500);
    else
        $(divShow).slideUp(500);


}



/* CADASTRO */

function alterarSenha() 
{

    if ($("#frmNovaSenha").valid()) {

        txtSenhaAtual = $("#txtSenhaAtual").val();
        txtNovaSenha = $("#txtNovaSenha").val();

        var url = "application/actions/act_Usuario.asp?task=AlterarSenha";
        url = url + "&sid=" + Math.random();
        url = url + "&txtSenhaAtual=" + txtSenhaAtual;
        url = url + "&txtNovaSenha=" + txtNovaSenha;

        $.ajax(
                {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function () { $("#dialog").dialog("close"); setLoading(); },
                    success: function (html) {
                        setSucessMessage(html);
                        $("#txtSenhaAtual").val("");
                        $("#txtNovaSenha").val("");
                        setLoadingFalse();
                    },
                    error: function (request, status, error) {
                        setErrorMessage(request.responseText);
                        setLoadingFalse();
                    }
                }
            );
    
    }

}

function sendCadastro() {
   
    if ($("#frmCadastro").valid()) {

        txtNomeRazaoSocial = $(".nome").val();
        txtCpfCnpj = $("#txtCpfCnpj").val();
        txtLogradouro = $("#txtlogradouro").val();

        txtNumero = $("#txtNumero").val();
        txtComplemento = $("#txtComplemento").val();
        txtBairro = $("#txtBairro").val();
        txtCidade = $("#txtCidade").val();
        txtCep = $("#txtCep").val();
        txtUF = $("#txtUF").val();
        txtEmail = $("#txtEmail").val();
        txtTelefone = $("#txtTelefone").val();
        txtCelular = $("#txtCelular").val();
        txtSenha = $("#txtSenha").val();

        if (isCadastro) {
            txtLogradouroMov = $("#txtlogradouroMov").val();
            txtNumeroMov = $("#txtNumeroMov").val();
            txtComplementoMov = $("#txtComplementoMov").val();
            txtBairroMov = $("#txtBairroMov").val();
            txtCidadeMov = $("#txtCidadeMov").val();
            txtCepMov = $("#txtCepMov").val();
            txtUFMov = $("#txtUFMov").val();
        }

        var url = "application/actions/act_Usuario.asp?task=Cadastro";
        url = url + "&sid=" + Math.random();
        url = url + "&txtNomeRazaoSocial=" + txtNomeRazaoSocial;
        url = url + "&txtCpfCnpj=" + txtCpfCnpj;
        url = url + "&txtLogradouro=" + $.URLEncode(txtLogradouro);
        url = url + "&txtNumero=" + txtNumero;
        url = url + "&txtComplemento=" + $.URLEncode(txtComplemento);
        url = url + "&txtBairro=" + $.URLEncode(txtBairro);
        url = url + "&txtCidade=" + $.URLEncode(txtCidade);
        url = url + "&txtCep=" + txtCep;
        url = url + "&txtUF=" + txtUF;
        url = url + "&txtEmail=" + txtEmail;
        url = url + "&txtTelefone=" + txtTelefone;
        url = url + "&txtCelular=" + txtCelular;
        url = url + "&txtSenha=" + txtSenha;

        if (isCadastro) {
            url = url + "&txtLogradouroMov=" + $.URLEncode(txtLogradouroMov);
            url = url + "&txtNumeroMov=" + $.URLEncode(txtNumeroMov);
            url = url + "&txtComplementoMov=" + $.URLEncode(txtComplementoMov);
            url = url + "&txtBairroMov=" + $.URLEncode(txtBairroMov);
            url = url + "&txtCidadeMov=" + $.URLEncode(txtCidadeMov);
            url = url + "&txtCepMov=" + txtCepMov;
            url = url + "&txtUFMov=" + txtUFMov;
        }
        
        $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "xml",
                          beforeSend: function () { setLoading(); },
                          success: function (xml) {

                              if (isCadastro) {

                                  var idtransacao = "";
                                  var descricao = "";
                                  var valor = "";
                                  var nome = "";
                                  var logradouro = "";
                                  var numero = "";
                                  var complemento = "";
                                  var bairro = "";
                                  var cidade = "";
                                  var cep = "";
                                  var uf = "";
                                  var cpf = "";
                                  var telefone = "";
                                  var email = "";

                                  $(xml).find('idtransacao').each(function () { idtransacao += $(this).text(); });

                                  $(xml).find('descricao').each(function () { descricao += $(this).text(); });
                                  $(xml).find('valor').each(function () { valor += $(this).text(); });
                                  $(xml).find('nome').each(function () { nome += $(this).text(); });

                                  $(xml).find('logradouro').each(function () { logradouro += $(this).text(); });
                                  $(xml).find('numero').each(function () { numero += $(this).text(); });
                                  $(xml).find('complemento').each(function () { complemento += $(this).text(); });

                                  $(xml).find('bairro').each(function () { bairro += $(this).text(); });
                                  $(xml).find('cidade').each(function () { cidade += $(this).text(); });
                                  $(xml).find('cep').each(function () { cep += $(this).text(); });

                                  $(xml).find('uf').each(function () { uf += $(this).text(); });
                                  $(xml).find('cpf').each(function () { cpf += $(this).text(); });
                                  $(xml).find('telefone').each(function () { telefone += $(this).text(); });
                                  $(xml).find('email').each(function () { email += $(this).text(); });

                                  $(".txtStatus").html("Suas informações foram armazenadas com sucesso!<br /> Aguarde, você será redirecionado para efetuar o pagamento.");

                                  SetPagValues(idtransacao, descricao, valor, nome, logradouro, numero, complemento, bairro, cidade, cep, uf, cpf, telefone, email);

                              }
                              else 
                              {
                                  setFormSucessMessage("Dados cadastrais atualizados com sucesso!");
                                  setLoadingFalse();
                              }

                          },
                          error: function (request, status, error) {


                                
                              
                              
                              setFormErrorMessage(request.responseText);
                              setLoadingFalse();


                          }
                      }
                  );

    }
}

function SetPagValues(idtrasacao, descricao, valor, nome, logradouro, numero, complemento, bairro, cidade, cep, uf, cpf, telefone, email) 
{
    $("#id_transacao").val(idtrasacao);
    $("#pag_descricao").val(descricao);
    $("#pag_valor").val(valor);
    $("#pag_nome").val(nome);
    $("#pag_logradouro").val(logradouro);
    $("#pag_numero").val(numero);
    $("#pag_complemento").val(complemento);
    $("#pag_bairro").val(bairro);
    $("#pag_cidade").val(cidade);
    $("#pag_cep").val(cep);
    $("#pag_uf").val(uf);
    $("#pag_cpf").val(cpf);
    $("#pag_telefone").val(telefone);
    $("#pag_email").val(email);

    setTimeout($("#pagseguro").submit(), 1900000);

}


/* LOGIN */

function validarUsuario() {

        txtMail = $("#txtLoginEmail").val();
        txtPass = $("#txtLoginPass").val();


        if (txtMail.length > 0 && txtPass.length > 0) {
            return true;
        }
        else {
            setErrorMessage("Você não tem permissão para acessar a área restrita do site.");
            return false;
        }
//        {

////            var url = "application/actions/act_Usuario.asp?task=Login";
////            url = url + "&sid=" + Math.random();
////            url = url + "&txtLoginEmail=" + txtMail;
////            url = url + "&txtLoginPass=" + txtPass;


////            alert(url);

////            $.ajax(
////                      {
////                          url: url,
////                          cache: false,
////                          dataType: "html",
////                          beforeSend: function () { setLoading(); },
////                          success: function (html) {
////                              setLoadingFalse();
////                              location.reload();
////                          },
////                          error: function (request, status, error) {
////                              setErrorMessage(request.responseText);
////                              setLoadingFalse();
////                          }
////                      }
////                  );

//        }
    }

    function Logoff() 
    {
        var url = "application/actions/act_Usuario.asp?task=Logoff";
        url = url + "&sid=" + Math.random();

        $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "html",
                          beforeSend: function() { setLoading(); },
                          success: function(html) {
                              setLoadingFalse();
                              location.reload();
                          },
                          error: function(request, status, error) {
                              setErrorMessage(request.responseText);
                              setLoadingFalse();
                          }
                      }
         );
    
    }


    function getGridEnderecos() {
        var url = "application/actions/act_Usuario.asp?task=getGridEnderecos";
        url = url + "&sid=" + Math.random();

        $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "html",
                          beforeSend: function () { setLoading(); },
                          success: function (html) {
                              $("#tbEnderecosUsuario").html(html);
                              bindActionsOnButtonsEnderecos();
                              setLoadingFalse();
                          },
                          error: function (request, status, error) {
                              setErrorMessage(request.responseText);
                              setLoadingFalse();
                          }
                      }
         );

    }

    function getGridAcessos() {
        var url = "application/actions/act_Usuario.asp?task=getGridAcessos";
        url = url + "&sid=" + Math.random();

        $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "html",
                          beforeSend: function () { setLoading(); },
                          success: function (html) {
                              $("#tbAcessoSecundario").html(html);
                              bindActionsOnButtonsAcessos();
                              setLoadingFalse();
                          },
                          error: function (request, status, error) {
                              setErrorMessage(request.responseText);
                              setLoadingFalse();
                          }
                      }
         );

    }



    function editEndereco(idEndereco) {

        var url = "application/actions/act_Usuario.asp?task=editEndereco";
        url = url + "&sid=" + Math.random();
        url = url + "&id=" + idEndereco;

        $.ajax(
            {
                url: url,
                cache: false,
                dataType: "xml",
                beforeSend: function () { $("#frmEnderecos").slideUp(500); setLoading(); },
                success: function (xml) {

                    $(xml).find('id').each(function () { $("#idEndereco").val($(this).text()); });
                    $(xml).find('logradouro').each(function () { $("#tabEndLogradouro").val($(this).text()); });
                    $(xml).find('numero').each(function () { $("#tabEndNumero").val($(this).text()); });
                    $(xml).find('complemento').each(function () { $("#tabEndComplemento").val($(this).text()); });
                    $(xml).find('bairro').each(function () { $("#tabEndBairro").val($(this).text()); });
                    $(xml).find('cidade').each(function () { $("#tabEndCidade").val($(this).text()); });
                    $(xml).find('cep').each(function () { $("#tabEndCep").val($(this).text()); });
                    $(xml).find('uf').each(function () { $("#tabEndUf").val($(this).text()); });

                    $("#frmEnderecos").slideDown(500);

                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );


        }

        function editAcessos(id) {

            var url = "application/actions/act_Usuario.asp?task=editAcessos";
            url = url + "&sid=" + Math.random();
            url = url + "&id=" + id;

            $.ajax(
            {
                url: url,
                cache: false,
                dataType: "xml",
                beforeSend: function () { $("#frmSenha").slideUp(500); setLoading(); },
                success: function (xml) {

                    $(xml).find('id').each(function () { $("#idSenha").val($(this).text()); });
                    $(xml).find('email').each(function () { $("#tabAcessoEmail").val($(this).text()); });
                    $(xml).find('senha').each(function () { $("#tabAcessoSenha").val($(this).text()); });

                    $("#frmSenha").slideDown(500);

                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );


        }

    function bindActionsOnButtonsEnderecos() {

        $(".editEndereco").click(
        function () {
            var idEnd = $(this.parentNode.parentNode).attr("id");
            editEndereco(idEnd);

        }
    );

        $(".delEndereco").click(
        function () {
            var idEnd = $(this.parentNode.parentNode).attr("id");
            $("#inpIdEndDelete").val(idEnd);
            $("#modalDelEndConfirma").dialog("open");
        }
    );

        $("#modalDelEndConfirma").dialog({
            resizable: false,
            height: 100,
            modal: true,
            autoOpen: false,
            title: "Excluir endereço",
            buttons: {
                "Sim": function () {
                    delThisEndereco();
                },
                "Não": function () {
                    $(this).dialog("close");
                }
            }
        });


    }

function bindActionsOnButtonsAcessos() {

        $(".editAcesso").click(
        function () {
            var id = $(this.parentNode.parentNode).attr("id");
            editAcessos(id);

        }
        );

        $(".delAcesso").click(
        function () {
            var idEnd = $(this.parentNode.parentNode).attr("id");
            $("#inpIdAcessoDelete").val(idEnd);
            $("#modalDelAcessoSecundario").dialog("open");
        }
        );

        $("#modalDelAcessoSecundario").dialog({
            resizable: false,
            height: 100,
            modal: true,
            autoOpen: false,
            title: "Excluir acesso",
            buttons: {
                "Sim": function () {
                    delThisAcessos();
                },
                "Não": function () {
                    $(this).dialog("close");
                }
            }
        });

}


function delThisAcessos() {


    var url = "application/actions/act_Usuario.asp?task=delAcessos";
    url = url + "&sid=" + Math.random();
    url = url + "&id=" + $("#inpIdAcessoDelete").val();


    $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "html",
                          beforeSend: function () { setLoading(); },
                          success: function (html) {
                              setSucessMessage("Acesso excluído com sucesso.");
                              getGridAcessos();
                              $("#modalDelAcessoSecundario").dialog("close");
                              setLoadingFalse();
                          },
                          error: function (request, status, error) {
                              setErrorMessage(request.responseText);
                              $("#modalDelAcessoSecundario").dialog("close");
                              setLoadingFalse();
                          }
                      }
         );

}


    function delThisEndereco() {


        var url = "application/actions/act_Usuario.asp?task=delEndereco";
        url = url + "&sid=" + Math.random();
        url = url + "&id=" + $("#inpIdEndDelete").val();


        $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "html",
                          beforeSend: function () { setLoading(); },
                          success: function (html) {
                              setSucessMessage("Endereço excluído com sucesso.");
                              getGridEnderecos();
                              $("#modalDelEndConfirma").dialog("close");
                              setLoadingFalse();
                          },
                          error: function (request, status, error) {
                              setErrorMessage(request.responseText);
                              $("#modalDelEndConfirma").dialog("close");
                              setLoadingFalse();
                          }
                      }
         );
    
    }


    function GetCaixasByTree(idCategoria) {
 
        var url = "application/actions/act_Caixas.asp?task=GetGridCaixa";
        url = url + "&sid=" + Math.random();
        url = url + "&idCategoria=" + idCategoria;


        $.ajax(
                      {
                          url: url,
                          cache: false,
                          dataType: "html",
                          success: function (html) {

                              $("#boxList").html(html);

                              $(".trCaixa").click(
                                    function () {
                                        idCaixa = $(this).attr("id");
                                        openModalDocumentos(idCaixa);
                                    }
                               );
                          },
                          error: function (request, status, error) {
                              setErrorMessage(request.responseText);
                          }
                      }
         );

    }
   
    function getNewPass() {
        email = $("#txtEsqueceuSenhaEmail").val();
       
        if (email != "") {
            var url = urlSite + "/forgot?";         
            url = url + "emailUser=" + $("#txtEsqueceuSenhaEmail").val();
            $.ajax(
	              {
	                  url: url,
	                  cache: false,
	                  dataType: "html",
	                  beforeSend: function() { setLoading(); },
	                  success: function(html) 
	                  {	     	                	 
	                	 if(html == "E-mail não encontrado em nosso banco de dados.")
	                    	 alert(html);
	                     else
	                     {
	                    	 alert(html);
	                    	 backLogin();
	                     }	                      
	                  },
	                  error: function(request, status, error) 
	                  {
	                      alert(request.responseText);
	                      	                  
	                  }
	              }
            );
        
        }
    
    }


function openModalDocumentos(idCaixa) 
{

    var url = "application/actions/act_Caixas.asp?task=GetMeusDocumentos";
        url = url + "&sid=" + Math.random();
        url = url + "&idCaixa=" + idCaixa;

        $.ajax(
        {
            url: url,
            cache: false,
            dataType: "html",
            beforeSend: function () { setLoading(); },
            success: function (html) {
                $("#contentModalDocumentos").html(html);
                setActionButtonDoc();
                setLoadingFalse();
                $("#modalDocumentos").dialog("open");
            },
            error: function (request, status, error) {
                setErrorMessage(request.responseText);
                setLoadingFalse();
            }
        }
    );

}



function cadDocumentoCaixa() {

    if ($("#frmCadastroDocumento").valid()) {

        idCaixa = $("#idCaixa").val();

        if (idCaixa != "") {

            var txtDocAssunto = $("#txtDocAssunto").val();
            var txtDocTitle = $("#txtDocTitle").val();
            var txtDocDesc = $("#txtDocDesc").val();
            var idDoc = $("#idDoc").val();

            var url = "application/actions/act_Caixas.asp?task=CadDocumentos";
            url = url + "&sid=" + Math.random();
            url = url + "&idCaixa=" + idCaixa;
            url = url + "&txtDocAssunto=" + $.URLEncode(txtDocAssunto);
            url = url + "&txtDocTitle=" + $.URLEncode(txtDocTitle);
            url = url + "&txtDocDesc=" + $.URLEncode(txtDocDesc);
            url = url + "&idDocumento=" + idDoc;


            $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { setLoading(); },
                success: function (html) {
                    setLoadingFalse();
                    atualizaGridDocumentos();
                    $(".formModal").slideUp(500);
                    $("#txtDocAssunto").val("");
                    $("#txtDocTitle").val("");
                    $("#txtDocDesc").val("");
                    $("#idDoc").val("");
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

        }

    }

}

function atualizaGridDocumentos() {

    idCaixa = $("#idCaixa").val();

    var url = "application/actions/act_Caixas.asp?task=AtualizaGridDocumentos";
    url = url + "&sid=" + Math.random();
    url = url + "&idCaixa=" + idCaixa;

    $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { setLoading(); },
                success: function (html) {
                    $("#gridDocumentos").html(html);
                    setActionButtonDoc();
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

}

function cadDescricaoCaixa() {

    if ($("#frmAlteraDescricaoCaixa").valid()) {

        idCaixa = $("#idCaixa").val();

        if (idCaixa != "") {

            var txtDescricaoCaixa = $("#txtDescricaoCaixa").val();

            var url = "application/actions/act_Caixas.asp?task=CadDescricao";
            url = url + "&sid=" + Math.random();
            url = url + "&idCaixa=" + idCaixa;
            url = url + "&txtDescricaoCaixa=" + $.URLEncode(txtDescricaoCaixa);

            $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { setLoading(); },
                success: function (html) {
                    setLoadingFalse();
                    $("#contentModalDocumentos  h4").html(html);
                    $(".formModal").slideUp(500);
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

        }
    }

}


function setActionButtonDoc() {

    $(".btEditDoc").click(
        function () {
            editDocumento($(this));
        }
    );

    $(".btDelDoc").click(
        function () {
            delDocumento($(this));
        }
    );


        if ($("#frmCadastroDocumento").length > 0) {
            $("#frmCadastroDocumento").validate({
                rules: {
                    txtDocAssunto: "required",
                    txtDocTitle: "required",
                    txtDocDesc: "required"
                },
                messages: {
                    txtDocAssunto: "*",
                    txtDocTitle: "*",
                    txtDocDesc: "*"
                }
            });
        }


        if ($("#frmAlteraDescricaoCaixa").length > 0) {
            $("#frmAlteraDescricaoCaixa").validate({
                rules: {
                    txtDescricaoCaixa: "required"
                },
                messages: {
                    txtDescricaoCaixa: "*"
                }
            });
        }


}

function getIdDocumento(obj) 
{
    
   if(obj != null || obj != "undefined")
   {
       trelement = $($(obj).parent()).parent();
       var splitEl = $(trelement).attr("id").split("_");
       docId = splitEl[1];
   }
   else
   {
       docId = $("#idDoc").val();
   }
   return docId;


}


function saveAcessos() {

    if ($("#frmCadSenha").valid()) {

        var idend = ($("#idSenha").val() != "") ? $("#idSenha").val() : 0;

        var url = "application/actions/act_Usuario.asp?task=saveAcessos";
        url = url + "&sid=" + Math.random();
        url = url + "&idSenha=" + idend;
        url = url + "&tabAcessoNome=" + $("#tabAcessoNome").val();
        url = url + "&tabAcessoEmail=" + $("#tabAcessoEmail").val();
        url = url + "&tabAcessoSenha=" + $("#tabAcessoSenha").val();
        
        $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { },
                success: function (html) {
                    $("#tabAcessoEmail").val("");
                    $("#tabAcessoSenha").val("");
                    $("#tabAcessoNome").val("");
                    $("#idSenha").val("");
                    $("#frmSenha").slideUp(500);
                    getGridAcessos();
                    setSucessMessage(html);
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

    }
}

jQuery.validator.addMethod("passValidation", function (value, element) {

    var nFound = false;

    for (var i = 0; i < value.length; i++) {
        if (!isNaN(value[i]))
            nFound = true;        
    }

    return nFound && (value.length >= 8);

}, "Senha inválida. A senha deve conter 8 caracteres ou mais, sendo 1 caracter numérico.");

function setValidacaoEndereco() {

    if ($("#frmCadSenha").length > 0) 
    {

        $("#frmCadSenha").validate({
            rules:
        {
            tabAcessoEmail: { required: true, email: true },
            tabAcessoSenha: {required:true, passValidation: true },
            tabAcessoNome: "required"
        },
            messages: {
                tabAcessoEmail: { required: "*", email: "*" },
                tabAcessoSenha: { required: "*" },
                tabAcessoNome: "*"
            }
        });
    }

    if ($("#frmCadEndereco").length > 0) {

        $("#tabEndNumero").setMask();
        $("#tabEndCep").setMask();
        
        $("#frmCadEndereco").validate({
            rules:
        {
            tabEndLogradouro: "required",
            tabEndNumero: "required",
            tabEndComplemento: "required",
            tabEndBairro: "required",
            tabEndCidade: "required",
            tabEndCep: "required",
            tabEndUf: "required"
        },
            messages: {
                tabEndLogradouro: "*",
                tabEndNumero: "*",
                tabEndComplemento: "*",
                tabEndBairro: "*",
                tabEndCidade: "*",
                tabEndCep: "*",
                tabEndUf: "*"
            }
        });

    }
}

function saveEndereco() {

    if ($("#frmCadEndereco").valid()) {

        var idend = ($("#idEndereco").val() != "") ? $("#idEndereco").val() : 0;

        var url = "application/actions/act_Usuario.asp?task=saveEndereco";
        url = url + "&sid=" + Math.random();
        url = url + "&idEndereco=" + idend;
        url = url + "&tabEndLogradouro=" + $("#tabEndLogradouro").val();
        url = url + "&tabEndNumero=" + $("#tabEndNumero").val();
        url = url + "&tabEndComplemento=" + $("#tabEndComplemento").val();
        url = url + "&tabEndBairro=" + $("#tabEndBairro").val();
        url = url + "&tabEndCidade=" + $("#tabEndCidade").val();
        url = url + "&tabEndCep=" + $("#tabEndCep").val();
        url = url + "&tabEndUf=" + $("#tabEndUf").val();

        $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { },
                success: function (html) {
                    $("#tabEndLogradouro").val("");
                    $("#tabEndNumero").val("");
                    $("#tabEndComplemento").val("");
                    $("#tabEndBairro").val("");
                    $("#tabEndCidade").val("");
                    $("#tabEndCep").val("");
                    $("#tabEndUf").val("");
                    $("#frmEnderecos").slideUp(500);
                    getGridEnderecos();
                    setSucessMessage(html);
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

    }
}


function editDocumento(obj) {

    idDocumento = getIdDocumento(obj);

    var url = "application/actions/act_Caixas.asp?task=editDocumento";
    url = url + "&sid=" + Math.random();
    url = url + "&idDocumento=" + idDocumento;

    $.ajax(
            {
                url: url,
                cache: false,
                dataType: "xml",
                beforeSend: function () { $(".formModal").slideUp(500); setLoading(); },
                success: function (xml) {

                    $(xml).find('idDocumento').each(function () { $("#idDoc").val($(this).text()); });
                    $(xml).find('assunto').each(function () { $("#txtDocAssunto").val($(this).text()); });
                    $(xml).find('titulo').each(function () { $("#txtDocTitle").val($(this).text()); });
                    $(xml).find('descricao').each(function () { $("#txtDocDesc").val($(this).text()); });
                        
                    $("#frmCadDocumento").slideDown(500);
                
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

}

function solicitarCaixa() {

    var url = "application/actions/act_Caixas.asp?task=SolicitarCaixa";
    url = url + "&sid=" + Math.random();

    $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { setLoading(); },
                success: function (html) {
                    setSucessMessage(html);
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );
    


}

function solicitarRetirada() {

    idCaixa = $("#idCaixa").val();

    var url = "application/actions/act_Caixas.asp?task=SolicitaRetiradaCaixa";
    url = url + "&sid=" + Math.random();
    url = url + "&idCaixa=" + idCaixa;

    $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { setLoading(); },
                success: function (html) {
                    $("#modalDocumentos").dialog("close");
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

}

function solicitarMovimentacao() {

    idCaixa = $("#idCaixa").val();

    var url = "application/actions/act_Caixas.asp?task=SolicitaMovimentacaoCaixa";
    url = url + "&sid=" + Math.random();
    url = url + "&idCaixa=" + idCaixa;

    $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { setLoading(); },
                success: function (html) {
                    $("#modalDocumentos").dialog("close");
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );

}

function delDocumento(obj) {

    idDocumento = getIdDocumento(obj);

    var url = "application/actions/act_Caixas.asp?task=delDocumento";
        url = url + "&sid=" + Math.random();
        url = url + "&idDocumento=" + idDocumento;

        $.ajax(
            {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function () { setLoading(); },
                success: function (html) {
                    trRemove = $($(obj).parent()).parent();
                    $(trRemove).remove();
                    setLoadingFalse();
                },
                error: function (request, status, error) {
                    setErrorMessage(request.responseText);
                    setLoadingFalse();
                }
            }
        );
}

function searchDocs() {

    var txtSearch = $("#inpSearch").val();

    if (txtSearch.length > 0) {

        var url = "busca-documentos.asp";
        url = url + "?search=" + txtSearch;
        url = url + "&sid=" + Math.random();

        location.href = url;

    }

}

function limpaForm(obj) {

    var elemet = "#" + obj;

    $(elemet).find(':input').each(function () {
        switch (this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });
}


function buscaCEP(strCep)
{		
	var url = urlSite+ "/cep/get/?cep=" + strCep;
	$.getJSON(url, function (json){ 					
		
		if(json != null){
			if($("#radioPessoaFisica:checked").val()=='pf'){
				$(".fisicabairro").val(json.cep.bairro);
				$(".fisicanomeLogrado").val(json.cep.logradouro);
				$(".fisicabairro").val(json.cep.bairro);
				$(".fisicacidade").val(json.cep.cidade);
				$(".fisicauf").val("RJ");		
				
				var tipo = json.cep.tp_logradouro;
				$(".fisicatipoLogradouro option[value='" + tipo + "']").attr('selected', 'selected');
				
				$('.fisicanumero').focus();
			}else{
				$(".juridicabairro").val(json.cep.bairro);
				$(".juridicanomeLogrado").val(json.cep.logradouro);
				$(".juridicabairro").val(json.cep.bairro);
				$(".juridicacidade").val(json.cep.cidade);
				$(".juridicauf").val("RJ");		
				
				var tipo = json.cep.tp_logradouro;
				$(".juridicatipoLogradouro option[value='" + tipo + "']").attr('selected', 'selected');
				
				$('.juridicanumero').focus();
				
			}
		}
	});
}

function validaBuscaCEP(strCep)
{			
	var url = urlSite+ "/entrega/validaPossibilidadeEntrega/?cep=" + strCep;
	$("#mensagemValidaCep").html("");
	$.getJSON(url, function (json){		
		if(json != null){
			var data = ""; 
													
			if(json.cep.logradouro!= undefined)
			{
				data = "<p><b style=\"color:green\">" +json.cep.error + "</b> "; 
				if(json.cep.logradouro != "0")
					data +=" : " + json.cep.tp_logradouro + " " + json.cep.logradouro + " - " +	json.cep.bairro + " - " + json.cep.cidade+"</p>";
			}
			else
				data ="<b style=\"color:red\">"+json.cep.error+"</b>";
			
			$("#mensagemValidaCep").html(data)
		}
			
			
	});
}



/*FALE CONOSCO*/

function sendFaleConosco() {

    if ($("#frmFaleConosco").valid()) {
        txtEmail = $(".email").val();
        txtNome = $(".nome").val();
        txtTelefone = $(".telefone").val();
        txtMensagem = $(".mensagem").val();

        var url = urlSite + "/faleconosco/?";        
        url = url + "faleconosco.nome=" + txtNome;
        url = url + "&faleconosco.email=" + txtEmail;
        url = url + "&faleconosco.telefone=" + txtTelefone;
        url = url + "&faleconosco.mensagem=" + txtMensagem;
        if($(".email").val()==""){
        	alert("Campo email obrigatório");
        	return false;
        }
        $.ajax(
   		{
   			url: url,
            cache: false,
            dataType: "html",
            type: 'POST',		                  
            success: function(html) {		                       
                alert("Mensagem enviada com sucesso!");
                $(".email").val("");
                $(".nome").val("");
                $(".telefone").val("");
                $(".mensagem").val("");		                        
              },
              error: function() {		                     
                 alert("Ocorreu algum erro na aplicação.");
               }
           }
       );

    }
    else {
        alert("Verifique o preechimento dos campos.");
    }
}

