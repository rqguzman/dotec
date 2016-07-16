$.ajaxSetup({
  global: false,
  type: "GET"
});

$().ready(function () {
    
    $("#loadingPanel").fixedPosition(); 
    
    setWinHeight();
    
    $('#tdTitle').html("<h1>" + window.document.title + "</h1>");
    
    if($("#frmLogin").length > 0)
    {
        $("#frmLogin").validate({
		    rules: {
			    login: "required",
			    senha: "required"
		    },
		    messages: {
			    login: "Preencha corretamente o campo Login.",
			    senha: "Preencha corretamente o campo Senha."
		    },
		    
		    errorLabelContainer: $("#msgInner")
	    });
    }
    
    if($("#frmUsuario").length > 0)
    {
        $("#frmUsuario").validate({
		    rules: {
			    txtUserLogin: "required",
			    txtUserSenha: "required"
		    },
		    messages: {
			    txtUserLogin: "Preencha corretamente o campo Login.",
			    txtUserSenha: "Preencha corretamente o campo Senha."
		    },
		    errorLabelContainer: $("#msgInner")
	    });
    }
    
    if($("#frmInteresses").length > 0)
    {
        $("#frmInteresses").validate({
		    rules: {
			    txtInteresse: "required"
		    },
		    messages: {
			    txtInteresse: "Preencha corretamente o campo Interesse."
		    },
		    errorLabelContainer: $("#msgInner")
	    });
    }
    
    
    if($("#cadFotos").length > 0)
    {
        
        $("#cadFotos").validate({
		    rules: {
			    fileInput: {required: true, accept: "png|jpe?g|gif" }
		    },
		    messages: {
			    fileInput: "Arquivo não suportado pelo sistema."
		    },
		    errorLabelContainer: $("#msgInner")
	    });    
        
    }
    
    
    if($("#frmAssociados").length > 0)
    {
        //sorterTableAssociado();
    }
    
    if($("#cadAssociados").length > 0)
    {
      
      $("#cadAssociados").validate({
		    rules: {
			    txtNome: "required",
			    txtMatricula: "required",
			    txtCEPDadosPessoais: "required",
			    txtEnderecoDadosPessoais: "required",
			    txtBairroDadosPessoais: "required",
			    txtCidadeDadosPessoais: "required",
			    txtUFDadosPessoais: "required",
			    txtIdentidade: "required",
			    txtCPF:{required:true, cpf:true},
			    txtNaturalidade: "required",
			    txtNacionalidade: "required",
			    txtPai: "required",
			    txtMae: "required",
			    txtAniversario:{required:true, dateBR:true},
			    txtSexo: "required",
			    txtEstadoCivil: "required",
			    txtCargo: "required",
			    txtMatriculaFuncional: "required",
			    txtConta: "required"
		    },
		    messages: {
			    txtNome: "Nome não preenchido",
			    txtMatricula: "Matricula não preenchido",
			    txtCEPDadosPessoais: "CEP não preenchido",
			    txtEnderecoDadosPessoais: "Endereço não preenchido",
			    txtBairroDadosPessoais: "Bairro não preenchido",
			    txtCidadeDadosPessoais: "Cidade não preenchido",
			    txtUFDadosPessoais: "UF não preenchido",
			    txtIdentidade: "Identidade não preenchido",
			    txtCPF: {required: "CPF não preenchido", cpf: "CPF inválido" },
			    txtNaturalidade: "Naturalidade não preenchido",
			    txtNacionalidade: "Nacionalidade não preenchido",
			    txtPai: "Pai não preenchido",
			    txtMae: "Mãe não preenchido",
			    txtAniversario: {required:"Aniversário não preenchido", dateBR: "Data de Aniversário inválida"},
			    txtSexo: "Sexo não selecionado",
			    txtEstadoCivil:  "Estado Civil não selecionado",
			    txtCargo: "Cargo não selecionado",
			    txtMatriculaFuncional: "Matrícula Funcional não preenchido",
			    txtConta: "Conta não preenchido"
		    },
		    errorLabelContainer: $("#msgInner2")
	    });      
    }
});

function sorterTableAssociado()
{
    $.tablesorter.defaults.widgets = ['zebra'];
        
    $("#gridView").tablesorter(
        {widthFixed: true, 
         headers:{0:{sorter: false},
                  5:{sorter: false},
                  6:{sorter: false}
         }
        }
    );
}

function setWinHeight()
{
    var hDefault = 167 + 46;
    var mHeight = getHeight();
    var newHeight = mHeight - hDefault;
    $('#content').height(newHeight);  // Set both to equal heights
}

function getHeight() {
  var myHeight = 0;
  if( typeof( window.innerWidth ) == 'number' ) {
    myHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    myHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    myHeight = document.body.clientHeight;
  }
  return myHeight;
}

function getWidth() {
  var myWidth = 0;
  if( typeof( window.innerWidth ) == 'number' ) {
    myWidth = window.innerWidth;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    myWidth = document.documentElement.clientWidth;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    myWidth = document.body.clientWidth;
  }
  return myWidth;
}


function setErrorMessage(msg)
{
    alert(msg);
}

function getFormPage()
{
    return $('form');
}

function selectAll()
{
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";
   
    jQuery.each($(frmChildElements), function() {
        this.checked =   $(frmChildElements)[0].checked;  
    });

}

function uncheckAll()
{
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";
   
    jQuery.each($(frmChildElements), function() {
        this.checked =   false;  
    });
}

function countCheckSelected()
{
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";
    
    count=0;
    
    jQuery.each($(frmChildElements), function() {
        if(this.checked) count++;
    });

    return count;   
}

function valueCheckSelected()
{
    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";
    var value = "";
    jQuery.each($(frmChildElements), function() {
            if(this.checked){
             value = this.value;
            }
    });
    return value;
}

function getAllValuesSelected()
{

    var formObj = getFormPage();
    var frmChildElements = "#gridView :checkbox";
    var value = "";
 
    jQuery.each($(frmChildElements), function() {
         if(this.checked && (this.value != $(frmChildElements)[0].value)){
            value += this.value + "|";
         }
    });
    
    return value;

}

function setLoading()
{
    $("#loadingPanel").show();
}

function setLoadingFalse()
{
    $("#loadingPanel").hide(); 
}

function overThis(obj)
{
    obj.className = obj.className + " trHover";
}

function outThis(obj)
{
    obj.className = obj.className.replace(" trHover","");
}

/* ------------------------------------------------ */

/* PAGINA DE USUÁRIOS*/
function getFormCadastroUsuario(codRegistro)
{
      var formObj = getFormPage();
      var url = "actions/act_Usuario.asp?task=GetFormulario";
      url = url+ "&sid=" + Math.random();
      if(codRegistro!=null) url = url + "&cod=" + codRegistro;
      
      $.ajax(
          {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function(){setLoading();},
                success: function(html){setLoadingFalse();formObj.html(html);},
                error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                }
          }
      ); 
}

function cadastroUsuario()
{
    if($('#tbUsuarios').length > 0) $('#tbUsuarios').remove();
    getFormCadastroUsuario(null);
}

function editarUsuario()
{
     if($('#tbUsuarios') != null) $('#tbUsuarios').remove();
    
        var numeros_registros = countCheckSelected();
        
        if(numeros_registros == 0) alert('Nenhum registro selecionado')
        else 
            if(numeros_registros>1) alert('Para editar selecione apenas um registro')
         else
           getFormCadastroUsuario(valueCheckSelected());

}

function saveUsuario()
{
    txtLogin = $('#txtUserLogin').val();
    txtSenha = $('#txtUserSenha').val();
    txtAdministra = $('#txtAdministra').val();
    hdCodUser = $('#txtCodUser').val();
    
    if(txtLogin.length > 0 && txtSenha.length > 0)
    {
        var url = "actions/act_Usuario.asp?task=SaveDadosUsuario";
            url = url + "&sid=" + Math.random();
            url = url + "&login=" + txtLogin;
            url = url + "&senha=" + txtSenha;
            url = url + "&administra=" + txtAdministra;
            if(hdCodUser.length > 0) url = url + "&cod=" + hdCodUser;
        
        $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){setLoadingFalse(); alert('Usuário Cadastrado'); atualizaGridUsuario();},
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          ); 
      } 
}

function deleteUsuario()
{

    if($('#tbUsuarios') != null) $('#tbUsuarios').remove();
    var numeros_registros = countCheckSelected();
    
    if(numeros_registros == 0) alert('Nenhum registro selecionado')
    else
    {
        
        var valuesForm = getAllValuesSelected();
        
        var url = "actions/act_Usuario.asp?task=DeleteUsuarios";
            url = url + "&sid=" + Math.random();
            url = url + "&usuarios=" + valuesForm;

	    $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){setLoadingFalse(); alert('Usuários excluídos com sucesso!');atualizaGridUsuario();uncheckAll();},
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          ); 
    
    }
}

function atualizaGridUsuario()
{
    var formObj = getFormPage();
    
    var url = "actions/act_Usuario.asp?task=AtualizaGridUsuario";
        url = url + "&sid=" + Math.random();
            
        $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){
                        setLoadingFalse(); 
                        $('#gridView').remove();
                        formObj.after(html);
                    },
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          );          
                    
}
/* FIM - PAGINA DE USUÁRIOS*/

function cadastroAssociado()
{
    location.href = 'associadosActions.asp';
}

function editarAssociado()
{
    var numeros_registros = countCheckSelected();
    
    if(numeros_registros == 0) alert('Nenhum registro selecionado')
    else 
        if(numeros_registros>1) alert('Para editar selecione apenas um registro')
     else
       location.href = 'associadosActions.asp?id=' + valueCheckSelected();
}

function acessarDados(idAssociado)
{
    location.href = 'associadosActions.asp?id=' + (idAssociado);
}

function deleteAssociado()
{
    var formObj = getFormPage();

    var numeros_registros = countCheckSelected();
    
    if(numeros_registros == 0) alert('Nenhum registro selecionado')
    else
    {
        var valuesForm = getAllValuesSelected();
        
        var url = "actions/act_Associados.asp?task=DeleteAssociado";
            url = url + "&sid=" + Math.random();
            url = url + "&usuarios=" + valuesForm;

            $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){
                        setLoadingFalse(); 
                        alert("Associados excluídos com sucesso!");
                        atualizaGridAssociado();
                        uncheckAll();
                    },
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          );               
    
    }
}


function alterStatus(idUsuario, obj)
{

      var url = "actions/act_Associados.asp?task=AlterStatus";
            url = url + "&sid=" + Math.random();
            url = url + "&userid=" + idUsuario;

            $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){
                        setLoadingFalse(); 
                        alterSrcImgStatus(obj);
                    },
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          );         
}


function alterSrcImgStatus(obj)
{
    if($(obj).attr("src").indexOf("On") > 0)
        $(obj).attr("src", $(obj).attr("src").replace("On","Off"))
    else
       $(obj).attr("src", $(obj).attr("src").replace("Off","On"));
}


function atualizaGridAssociado()
{
    var formObj = getFormPage();
    
    var url = "actions/act_Associados.asp?task=AtualizaGridAssociado";
        url = url + "&sid=" + Math.random();
            
        $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){
                        setLoadingFalse(); 
                        $('#recordCount').remove();
                        $('#gridView').remove();
                        formObj.after(html);
                        sorterTableAssociado();
                    },
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          );          
                    
}

function getEndereco(fields)
{
    var frm = getFormPage();
    var txtGetValue = "#txtCEP" + fields;

    if($(txtGetValue).val().match("_") == null)
    {
        var url = "actions/act_CEP.asp?cep=" + $(txtGetValue).val();
            url = url + "&sid=" + Math.random();
            
         $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "xml",
                    beforeSend: function(){setLoading();},
                    success: function(xml){
                        setLoadingFalse(); 
                        
                        var endereco = "";
                        var bairro = "";
                        var cidade = "";
                        var uf = "";
                        
                        $(xml).find('endereco').each(function(){endereco+=$(this).text();});
                        $(xml).find('bairro').each(function(){bairro+=$(this).text();});
                        $(xml).find('cidade').each(function(){cidade+=$(this).text();});
                        $(xml).find('uf').each(function(){uf+=$(this).text();});
                        
                        SetFormValues(fields, endereco, bairro, cidade, uf);
                    },
                    error: function(){setLoadingFalse();alert('CEP não encontrado.');
                    }
              }
          );          
    }
    
}

function SetFormValues(nomecampos, v_endereco, v_bairro, v_cidade, v_uf)
{
    
    var txtEndereco = "#txtEndereco" + nomecampos;
    var txtBairro = "#txtBairro" + nomecampos;
    var txtCidade = "#txtCidade" + nomecampos;
    var txtUF = "#txtUF" + nomecampos;
    
    $(txtEndereco).val(v_endereco);
    $(txtBairro).val(v_bairro);
    $(txtCidade).val(v_cidade);
    $(txtUF).val(v_uf);
}

function setPecualista(obj)
{
      if(obj.checked)
      {
        $('#pnlBeneficiariosPeculio').show();
        $('#pnlIncricaoPecualista').show();
      }
      else
      {
        $('#pnlBeneficiariosPeculio').hide();
        $('#pnlIncricaoPecualista').hide();
      }
}

function verificaMatricula()
{
    
    var formObj = getFormPage();

    var url = "actions/act_Associados.asp?task=VerificaMatricula";
        url = url+ "&sid=" + Math.random();
        url = url+ "&matricula=" + $("#txtMatricula").val();
        
     $.ajax(
          {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function(){setLoading();},
                success: function(html){
                    if(html.search("erro") == 0)
                    {
                        alert('Matrícula Inválida.');
                        $("#txtMatricula").val("");
                    }
                    setLoadingFalse();
                },
                error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                }
          }
      );    
    
    
}

function getFormCadastroAssociados(codRegistro)
{
   
    var formObj = getFormPage();

    var url = "actions/act_Associados.asp?task=GetFormulario";
        url = url+ "&sid=" + Math.random();
        if(codRegistro!=null) url = url + "&cod=" + codRegistro;

     $.ajax(
          {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function(){setLoading();},
                success: function(html){
                    setLoadingFalse();
                    formObj.html(html);
                    setMaskFormulario();
                    preencheListaInterresse(codRegistro);
                    
                    if($("#txtMatricula").val())
                    { 
                        $("#txtMatricula").attr("readonly", true); 
                        $("#txtMatricula").removeAttr("onblur"); 
                    }

                    if($("#rdPecualista:checked").length == 1)
                    {
                     $('#pnlBeneficiariosPeculio').show();
                     $('#pnlIncricaoPecualista').show();
                    }
                },
                error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                }
          }
      );    
}

function setMaskFormulario()
{
     if(!$('#rdPecualista').checked)
     {
        $('#pnlBeneficiariosPeculio').hide();
        $('#pnlIncricaoPecualista').hide();
     }
        
        limpaBeneficiarioPeculio();
    
        jQuery(
            function($){
                $("#txtCPF").mask("###########",{placeholder:" "});
                $("#txtCEPDadosPessoais").mask("#####-###",{placeholder:" "});
                $("#txtAniversario").mask("##/##/####",{placeholder:" "});
                $("#txtTelefone").mask("(##) ####-####",{placeholder:" "});
                $("#txtTelefone2").mask("(##) ####-####",{placeholder:" "});
                $("#txtCelular").mask("(##) ####-####",{placeholder:" "});
                $("#txtConta").mask("####.#####-#",{placeholder:" "});
                $("#txtDataInscricao").mask("##/##/####",{placeholder:" "});
                $("#txtCEP_1BeneficiarioPeculio").mask("#####-###",{placeholder:" "});
                $("#txtCEP_2BeneficiarioPeculio").mask("#####-###",{placeholder:" "});
                $("#txtCEP_3BeneficiarioPeculio").mask("#####-###",{placeholder:" "});
                $("#txtCEP_4BeneficiarioPeculio").mask("#####-###",{placeholder:" "});

                $("#txtTelefone_1BeneficiarioPeculio").mask("(##) ####-####",{placeholder:" "});
                $("#txtTelefone_2BeneficiarioPeculio").mask("(##) ####-####",{placeholder:" "});
                $("#txtTelefone_3BeneficiarioPeculio").mask("(##) ####-####",{placeholder:" "});
                $("#txtTelefone_4BeneficiarioPeculio").mask("(##) ####-####",{placeholder:" "});
           }
        );
}
function loadFoto()
{
    
    var url = "associadosFotos.asp?cod=" + $("#hdIdAssociado").val();
    window.open(url,"FotosAssociados","height=50; width=400");
    
}

function preencheListaInterresse(codRegistro)
{
    objLista = $('#pnlListaInteresse');
    
     var url = "actions/act_Associados.asp?task=SetListaInteresse";
         url = url+ "&sid=" + Math.random();
         if(codRegistro!="") url = url + "&cod=" + codRegistro;

    $.ajax(
          {
                url: url,
                cache: false,
                dataType: "html",
                success: function(html){
                    objLista.html(html);
                },
                error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                }
          }
      ); 
}

function saveInteresse()
{
    var txtInteresse = $('#txtInteresseCad').val();

    if(txtInteresse.length > 0)
    {
            
       codRegistro = $('#hdIdAssociado').val();    

        var url = "actions/act_Associados.asp?task=SaveInteresse";
            url = url+ "&sid=" + Math.random();
            url = url+ "&interesse=" + txtInteresse;

        $.ajax(
          {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function(){setLoading();},
                success: function(html){
                    setLoadingFalse(); 
                    preencheListaInterresse(codRegistro);
                    cadInteresse();
                },
                error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                }
          }
      ); 
    }
    else
        alert('Preencha corretamente o campo Interesse.')
}

function cadInteresse()
{
    if($('#fieldInteresse').css("display") == "none")
            $('#fieldInteresse').slideDown();
    else
        if($('#fieldInteresse').css("display") == "" || $('#fieldInteresse').css("display") == "block")
        {
                var frmChildElements = "#fieldInteresse :input";
                jQuery.each($(frmChildElements), function() {
                    this.value = "";  
                });
            $('#fieldInteresse').slideUp();
        }
}

function insertInteresseAssociado(obj)
{
    codRegistro = $('#hdIdAssociado').val();    
    
    var url = "actions/act_Associados.asp?task=SetInteresse";
    if(obj.checked)
       url=url+"&acao=Add";
    else
       url=url+"&acao=Del";
       url=url+"&codigo="+obj.value;
       url=url+"&idAssociado="+codRegistro;
       url=url+"&sid="+Math.random();
    
        $.ajax(
          {     url: url,
                cache: false,
                dataType: "html",
                success: function(html){},
                error: function(){alert('Ocorreu algum erro na aplicação.');
                }
          }
      ); 
}


function haveBeneficiarioPeculio()
{
    var isExists = false
    
    for(i=1; i<=4; i++)
    {   
        var n = "BeneficiarioPeculio";
        var txtNome_ = "txtNome_"+ i + n;
        var txtTelefone_ = "txtTelefone_"+ i + n;
        var txtCEP_ = "txtCEP_"+ i + n;
        var txtEndereco_ = "txtEndereco_"+ i + n;
        var txtBairro_ = "txtBairro_"+ i + n;
        var txtCidade_ = "txtCidade_"+ i + n;
        var txtUF_ = "txtUF_"+ i + n;
        var hasNome = true;
        var hasTelefone = true;
        var hasCEP = true;
        var hasEndereco = true;
        var hasBairro = true;
        var hasCidade = true;
        
        if($F(txtNome_).length <=0)
            var hasNome = false;

        if($F(txtTelefone_).match("_") != null)
            var hasTelefone = false;

        if($F(txtCEP_).match("_") != null)
            var hasCEP = false;
        
        if($F(txtEndereco_).length <=0)
            var hasEndereco = false;
            
        if($F(txtBairro_).length <=0)
            var hasBairro = false;
        
        if($F(txtCidade_).length <=0)
            var hasCidade = false;

        if($F(txtUF_).length <=0)
            var hasUF = false;

        if(hasNome && hasTelefone && hasCEP && hasEndereco && hasBairro && hasCidade)
            isExists = true;

    }
    
    return isExists;
}

function limpaBeneficiarioPeculio()
{
   
    for(i=1; i<=4; i++)
    {   
        var n = "BeneficiarioPeculio";
        var txtNome_ = "#txtNome_"+ i + n;
        var txtTelefone_ = "#txtTelefone_"+ i + n;
        var txtCEP_ = "#txtCEP_"+ i + n;
        var txtEndereco_ = "#txtEndereco_"+ i + n;
        var txtBairro_ = "#txtBairro_"+ i + n;
        var txtCidade_ = "#txtCidade_"+ i + n;
        var txtUF_ = "#txtUF_"+ i + n;
        var txtComplemento_ = "#txtComplemento_"+ i + n;
                  
        if($(txtNome_).val().match("]") != null)
            $(txtNome_).val("");

        if($(txtTelefone_).val().match("]") != null)
             $(txtTelefone_).val("");

        if($(txtCEP_).val().match("]") != null)
             $(txtCEP_).val("");
        
        if($(txtEndereco_).val().match("]") != null)
             $(txtEndereco_).val("");
            
        if($(txtBairro_).val().match("]") != null)
             $(txtBairro_).val("");
        
        if($(txtCidade_).val().match("]") != null)
             $(txtCidade_).val("");

        if($(txtUF_).val().match("]") != null)
             $(txtUF_).val("");

        if($(txtComplemento_).val().match("]") != null)
             $(txtComplemento_).val("");
    }
}
/* END - PÁGINAS DE USUARIOS */



/* PAGINA DE INTERESSES*/
function getFormCadastroInteresse(codRegistro)
{
    var formObj = getFormPage();
    
    var url = "actions/act_Interesse.asp?task=GetFormulario";
        url = url+ "&sid=" + Math.random();
        if(codRegistro!=null) url = url + "&cod=" + codRegistro;

         $.ajax(
          {
                url: url,
                cache: false,
                dataType: "html",
                beforeSend: function(){setLoading();},
                success: function(html){setLoadingFalse();formObj.html(html);},
                error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                }
          }
         ); 

}

function cadastroInteresse()
{
    if($('#tbUsuarios').length > 0) $('#tbUsuarios').remove();
        getFormCadastroInteresse(null);
}

function editarInteresse()
{
    if($('#tbUsuarios').length > 0) $('#tbUsuarios').remove();
    
        var numeros_registros = countCheckSelected();
        
        if(numeros_registros == 0) alert('Nenhum registro selecionado')
        else 
            if(numeros_registros>1) alert('Para editar selecione apenas um registro')
         else
           getFormCadastroInteresse(valueCheckSelected());

}

function salvarInteresse()
{
    var frm = getFormPage();
    
    var txtInteresse = $('#txtInteresse').val();
    var codInteresse = $('#txtCodInteresse').val();
    
    var url = "actions/act_Interesse.asp?task=SaveDadosInteresse";
        url = url + "&sid=" + Math.random();
        url = url + "&interesse=" + txtInteresse;
    if(codInteresse.length > 0) url = url + "&cod=" + codInteresse;

        $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){setLoadingFalse(); alert('Interesse cadastrado com sucesso'); atualizaGridInteresse();},
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          ); 
}

function deleteInteresse()
{

    var formObj = getFormPage();
    
    if($('#tbUsuarios').length > 0) $('#tbUsuarios').remove();
    
    var numeros_registros = countCheckSelected();
    
    if(numeros_registros == 0) alert('Nenhum registro selecionado')
    else
    {
        
        var valuesForm = getAllValuesSelected();
    
        var url = "actions/act_Interesse.asp?task=DeleteInteresses";
            url = url + "&sid=" + Math.random();
            url = url + "&interesses=" + valuesForm;

        $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){
                        setLoadingFalse(); 
                        alert("Interesses excluídos com sucesso!");
                        atualizaGridInteresse();
                        uncheckAll();
                    },
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          );                 
    }
}

function atualizaGridInteresse()
{
    var formObj = getFormPage();
    var url = "actions/act_Interesse.asp?task=AtualizaGridInteresse";
        url = url + "&sid=" + Math.random();
     
        $.ajax(
              {
                    url: url,
                    cache: false,
                    dataType: "html",
                    beforeSend: function(){setLoading();},
                    success: function(html){
                        setLoadingFalse(); 
                        $('#gridView').remove();
                        formObj.after(html);
                    },
                    error: function(){setLoadingFalse();alert('Ocorreu algum erro na aplicação.');
                    }
              }
          );                  
}

function FastSearch()
{
    value = $('#txtSearchFast').val();
     if(value.length > 0)
     {
         var url = "relatoriosResult.asp?search=" + value;
             url = url + "&sid=" + Math.random();
    
            location.href = url;
     }

}

/* RELATORIOS */
function CamposNaoPreenchidos()
{
    vCargo = false;
    vSexo = false;
    vMes = false;
    vEstadoCivil = false;
    vPecualista = false;
    vInteresses = false;

    if($F('txtCargo') == "*") vCargo = true;
    if($F('txtSexo') == "*") vSexo = true;
    if($F('txtMesAniversario') == "*") vMes = true;
    if($F('txtEstadoCivil') == "*") vEstadoCivil = true;
    if(!($('rdPecualista').checked)) vPecualista = true;
    if($F('txtInteresses').length <= 0) vInteresses= true;
    
    if(!vCargo || !vSexo || !vMes || !vEstadoCivil || !vPecualista || !vInteresses)
        return true;
    else
        return false;
    
}

function RelatorioPequisa()
{
    var invalidFields = new Array();
    var frm = getFormPage();
    
    if(!CamposNaoPreenchidos())
    {
       invalidFields.push({obj: null, txt: "É necessário informar ao menos 1 critério para pesquisa.", typeValidation:"required"});
       setErrorMessage(frm, invalidFields);
    }
    else    
        frm.submit();
}   