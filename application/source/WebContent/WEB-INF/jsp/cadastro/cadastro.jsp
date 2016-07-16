<%@ include file="/header.jsp" %>

<script type="text/javascript" src="<c:url value="/client/js/clientesFormulario.js"/>"  ></script>
 	<style type="text/css">
		@import url("<c:url value="/client/css/clientesFormulario.css"/>");
		@import url("<c:url value="/client/css/cadastro.css"/>");
	</style>
	<script type="text/javascript" src="<c:url value="/client/frameworks/jquery/plugins/fancybox/jquery.fancybox-1.3.4.pack.js" />"></script>
	<link rel="stylesheet" href="<c:url value="/client/frameworks/jquery/plugins/fancybox/jquery.fancybox-1.3.4.css" />" type="text/css" media="screen" />
								
<script>

$(document).ready(function() {	

	/*$('[type=checkbox]').click(function(){
		if(this.id =='clientePessoaJuridica.contrato')
			$('#inlinepj').click();
		else
			$('#inline').click();
	});	*/
	$(".emailJuridico").change(function(){
		$(".emailUsuario").val(this.value);
		
	});
	$('[type=password]').change(function(){
		if(this.id =='clientePessoaJuridica.contrato')
			$('#inlinepj').click();
		else
			$('#inline').click();
	});
	

	$("a#inline").fancybox({
		'hideOnContentClick': true
	});		
	$("a#inlinepj").fancybox({
		'hideOnContentClick': true
	});		

		
});


</script>								
								
								
								
								



<link href="<c:url value="/client/css/cadastro.css"/>" rel="stylesheet" type="text/css" />
<style type="text/css">a:link {
	text-decoration: none;
	color: #CCC;
}
a:visited {
	text-decoration: none;
	color: #FFF;
}
a:hover {
	text-decoration: none;
	color: #666;
}
a:active {
	text-decoration: none;
}
#formulario-dados {
	position:absolute;
	left:289px;
	top:19px;
	width:351px;
	height:890px;
	z-index:100;
	text-align: left;
}
</style>









  
  
  
  
  
  
  
  
  
  <div id="formulario" style="margin-top:40px">
  <p style="color:red;margin:20px 0 20px;text-align:center;">
						<%@ include file="/error.jsp" %>
						</p>
  
  <div class="dados" id="formulario-dados">
    
    <form autocomplete="off"  id="formulario2" name="formulario" class="cmxform"
							action="<c:url value="/cadastro/~#clientes#~/adicionaPublico"/>" method="post"> 
						
      <div>
        <fieldset>
        	<input type="hidden" id="clientePessoaFisica.id" name="clientePessoaFisica.id"  value="${clientePessoaFisica.id}" />
			<input type="hidden"  id="clientePessoaJuridica.id" name="clientePessoaJuridica.id"  value="${clientePessoaJuridica.id}" />
			
			<c:choose>
								<c:when test="${tipoDeCliente=='pf' or tipoDeCliente==null or tipoDeCliente==''}">
									<c:set var="checkPessoaFisica" value="true" ></c:set>
									<c:set var="checkPessoaJuridica" value="false" ></c:set>
								</c:when>	
								<c:when test="${tipoDeCliente=='pj'}">
									<c:set var="checkPessoaFisica" value="false" ></c:set>
									<c:set var="checkPessoaJuridica" value="true" ></c:set>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${tipoDeCliente!=null}">
									<c:set var="readOnlyTipoDePessoa" value="true" ></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="readOnlyTipoDePessoa" value="false" ></c:set>
								</c:otherwise>	
							</c:choose>
          <ul id="ulTipoDeCliente">
            <li>
            	<dotec:campoRadio  classe="radioTipoDeCliente" name="tipoDeCliente" label="Pessoa Física" id="radioPessoaFisica"  value="pf" checked="${checkPessoaFisica}" readOnly="${readOnlyTipoDePessoa}"></dotec:campoRadio>
											<dotec:campoRadio classe="radioTipoDeCliente" name="tipoDeCliente" label="Pessoa Jurídica" id="radioPessoaJuridica" value="pj" checked="${checkPessoaJuridica}" readOnly="${readOnlyTipoDePessoa}"></dotec:campoRadio>
              
            </li>
          </ul>
    <div class="divPessoa divPessoaFisica">					
                 						<table cellpadding="0" cellspacing="0" border="0">
                 							<tr >
                 								<td><label for="clientePessoaFisica.nome" class="hasTip required">Nome: <span class="star">&#160;*</span></label></td>
                 								<td><input type="text" id="clientePessoaFisica.nome" name="clientePessoaFisica.nome" value="${clientePessoaFisica.nome}" class="texto nome" /></td>
                 							</tr>
                 							<tr>
                 								<td><label for="clientePessoaFisica.cpf" class="hasTip required">CPF: <span class="star">&#160;*</span></label></td>
                 								<td><input type="text" id="clientePessoaFisica.cpf" name="clientePessoaFisica.cpf" value="${clientePessoaFisica.cpf}" class="cpf" /></td>
                 							</tr>
                 							<tr>
                 								<td><label for="clientePessoaFisica.rg" class="hasTip required">RG: <span class="star">&#160;*</span></label></td>
                 								<td><input type="text" id="clientePessoaFisica.rg" name="clientePessoaFisica.rg" value="${clientePessoaFisica.rg}" class="texto rg" /></td>
                 							</tr>
                 							
                 							<tr>
                 								<td><label for="clientePessoaFisica.telefone.numero" class="hasTip required">Telefone: <span class="star">&#160;*</span></label></td>
                 								<td>
													<input type="text" id="clientePessoaFisica.telefone.ddd" name="clientePessoaFisica.telefone.ddd" class="ddd" value="${clientePessoaFisica.telefone.ddd}" size="2" maxlength="2" />
													<input type="text" id="clientePessoaFisica.telefone.numero" name="clientePessoaFisica.telefone.numero" class="telefone" value="${clientePessoaFisica.telefone.numero}" size="10" maxlength="8" class="telefone"/>
												</td>
                 							</tr>
                 							<tr>
                 								<td><label for="clientePessoaFisica.dataDeNascimento" class="hasTip required">Data de Nascimento: <span class="star">&#160;*</span></label></td>
                 								<td>
                 									<joda:format value="${clientePessoaFisica.dataDeNascimento}" pattern="dd/MM/yyyy" var="dataDeNascimento"  />
                 									<input type="text" id="clientePessoaFisica.dataDeNascimento" name="clientePessoaFisica.dataDeNascimento" value="${dataDeNascimento}"  class="data"/>
                 								</td>
                 							</tr>
                 							<tr>
                 								<td><label for="clientePessoaFisica.sexo">Sexo:<span class="star">&#160;*</span></label></td>
												<td><select name="clientePessoaFisica.sexo" id="clientePessoaFisica.sexo" class="sexo">
													<option value=""></option>
													<c:forEach items="${sexoList}" var="sexo">
														<option value="${sexo}" <c:if test="${clientePessoaFisica.sexo==sexo}">selected="selected"</c:if> >${sexo.nome}</option>
													</c:forEach>
												</select>
												</td>
                 							</tr>
                 							<tr>
	                 							<td><label for="clientePessoaFisica.diaDeVencimento">Data Vencimento:<span class="star">*</span></label></td>
												<td>
													<select name="clientePessoaFisica.diaDeVencimento" id="clientePessoaFisica.diaDeVencimento" class="diaVencimento">
														<option value=""></option>										 
														<option value="10" <c:if test="${clientePessoaFisica.diaDeVencimento==10}">selected="selected"</c:if>>10</option>
														<option value="15" <c:if test="${clientePessoaFisica.diaDeVencimento==15}">selected="selected"</c:if>>15</option>
														<option value="20" <c:if test="${clientePessoaFisica.diaDeVencimento==20}">selected="selected"</c:if>>20</option>
													</select>
												</td>											
                 							</tr>
                 						</table>                 						                 										
									</div>
									
									
									
									<div class="divPessoa divPessoaJuridica">
										<table cellpadding="0" cellspacing="0" border="0">
                 							<tr >
                 								<td><label for="clientePessoaJuridica.nome" class="hasTip required">Nome: <span class="star">&#160;*</span></label></td>
                 								<td><input type="text" id="clientePessoaJuridica.nome" name="clientePessoaJuridica.nome" value="${clientePessoaJuridica.nome}" class="texto nome" /></td>
                 							</tr>
                 							<tr >
                 								<td><label for="clientePessoaJuridica.cnpj" class="hasTip required">CNPJ: <span class="star">&#160;*</span></label></td>
                 								<td><input type="text" id="clientePessoaJuridica.cnpj" name="clientePessoaJuridica.cnpj" value="${clientePessoaJuridica.cnpj}" class="cnpj" /></td>
                 							</tr>
                 							<tr>
                 								<td><label for="clientePessoaJuridica.email" class="hasTip required">E-mail: <span class="star">&#160;*</span></label></td>
                 								<td><input type="text" id="clientePessoaJuridica.email" name="clientePessoaJuridica.email" value="${clientePessoaJuridica.email}" class="email emailJuridico" /></td>
                 							</tr>
											<tr>
                 								<td><label for="clientePessoaJuridica.telefone.numero" class="hasTip required">Telefone: <span class="star">&#160;*</span></label></td>
                 								<td>
													<input type="text" id="clientePessoaJuridica.telefone.ddd" name="clientePessoaJuridica.telefone.ddd" class="ddd" value="${clientePessoaFisica.telefone.ddd}" size="2" maxlength="2" class="ddd"/>
													<input type="text" id="clientePessoaJuridica.telefone.numero" name="clientePessoaJuridica.telefone.numero" class="telefone" value="${clientePessoaJuridica.telefone.numero}" size="10" maxlength="8" class="telefone"/>
												</td>
                 							</tr>
                 							<tr>
                 								<td><label for="clientePessoaJuridica.diaDeVencimento">Data Vencimento:<span class="star">*</span></label></td>
												<td>
													<select name="clientePessoaJuridica.diaDeVencimento" id="clientePessoaJuridica.diaDeVencimento" class="diaVencimento">
														<option value=""></option>										 
														<option value="10" <c:if test="${clientePessoaJuridica.diaDeVencimento==10}">selected="selected"</c:if>>10</option>
														<option value="15" <c:if test="${clientePessoaJuridica.diaDeVencimento==15}">selected="selected"</c:if>>15</option>
														<option value="20" <c:if test="${clientePessoaJuridica.diaDeVencimento==20}">selected="selected"</c:if>>20</option>
													</select>
												</td>
                 							</tr>
										</table>
									</div>
                        		</fieldset>
                        		
                        		<fieldset class="adminform">
                          			
                          			
	                        			
	                        				<div class="divEndereco">
	                        					<div class="divPessoa divPessoaFisica">
													
													<table>
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].cep">CEP:<span class="star">*</span></label></td>
															<td><input onchange="buscaCEP(this.value)" type="text" id="clientePessoaFisica.enderecos[0].cep" name="clientePessoaFisica.enderecos[0].cep" value="${clientePessoaFisica.enderecos[0].cep}" class="cep" /></td>
														</tr>
														<tr>
															<td>
																<input type="hidden" id="clientePessoaFisica.enderecos[0].enderecoDeCobranca" name="clientePessoaFisica.enderecos[0].enderecoDeCobranca" value="1"/>
																<input type="hidden" id="clientePessoaFisica.enderecos[0].enderecoDeEntrega" name="clientePessoaFisica.enderecos[0].enderecoDeEntrega" value="0"/>
																
																<label for="clientePessoaFisica.enderecos[0].tipoDeLogradouro">Tipo de Logradouro:<span class="star">*</span></label>
															</td>
															<td>
																<select name="clientePessoaFisica.enderecos[0].tipoDeLogradouro" id="clientePessoaFisica.enderecos[0].tipoDeLogradouro" class="tipoLogradouro fisicatipoLogradouro">
										                			<option value=""></option>
										                			<c:forEach items="${tipoDeLogradouroList}" var="tipoDeLogradouro">
										                    			<option value="${tipoDeLogradouro}" <c:if test="${clientePessoaFisica.enderecos[0].tipoDeLogradouro==tipoDeLogradouro}">selected="selected"</c:if> >${tipoDeLogradouro.nome}</option>
										                			</c:forEach>
										            			</select>			
										            													
															</td>
														</tr>
														
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].nomeDoLogrado">Logradouro:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaFisica.enderecos[0].nomeDoLogrado" name="clientePessoaFisica.enderecos[0].nomeDoLogrado" value="${clientePessoaFisica.enderecos[0].nomeDoLogrado}" class="text nomeLogrado fisicanomeLogrado" /></td>
														</tr>
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].numero">Número:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaFisica.enderecos[0].numero" name="clientePessoaFisica.enderecos[0].numero" value="${clientePessoaFisica.enderecos[0].numero}" class="numero fisicanumero" /></td>
														</tr>
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].complemento">Complemento:</label></td>
															<td><input type="text" id="clientePessoaFisica.enderecos[0].complemento" name="clientePessoaFisica.enderecos[0].complemento" value="${clientePessoaFisica.enderecos[0].complemento}" class="texto complemento" /></td>
														</tr>
														
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].referencia">Referência:</label></td>
															<td><textarea  id="clientePessoaFisica.enderecos[0].referencia" name="clientePessoaFisica.enderecos[0].referencia" value="${clientePessoaFisica.enderecos[0].referencia}" class="texto referencia" >
															</textarea>
															</td>
														</tr>
														
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].bairro">Bairro:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaFisica.enderecos[0].bairro" name="clientePessoaFisica.enderecos[0].bairro" value="${clientePessoaFisica.enderecos[0].bairro}" class="texto bairro fisicabairro" /></td>
														</tr>
														
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].municipio">Município:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaFisica.enderecos[0].municipio" name="clientePessoaFisica.enderecos[0].municipio" value="${clientePessoaFisica.enderecos[0].municipio}" class="texto municipio fisicacidade" /></td>
														</tr>
														<tr>
															<td><label for="clientePessoaFisica.enderecos[0].uf">Estado:<span class="star">*</span></label></td>
															<td><select name="clientePessoaFisica.enderecos[0].uf" id="clientePessoaFisica.enderecos[0].uf" class="uf fisicauf">
										            			<option value=""></option>
										            			<c:forEach items="${ufs}" var="uf">  
										                			<option value="${uf}" <c:if test="${clientePessoaFisica.enderecos[0].uf==uf}">selected="selected"</c:if>>${uf}</option>
										            			</c:forEach>
										        			</select>
										        			</td>
														</tr>
													</table>												
												</div>                        		
	                        				</div>  


	                        				<div class="divEndereco">
	                        					<div class="divPessoa divPessoaJuridica">
	                        						<input type="hidden" id="clientePessoaJuridica.enderecos[0].enderecoDeCobranca" name="clientePessoaJuridica.enderecos[0].enderecoDeCobranca" value="1"/>
													<input type="hidden" id="clientePessoaJuridica.enderecos[0].enderecoDeEntrega" name="clientePessoaJuridica.enderecos[0].enderecoDeEntrega" value="0"/>
																
	                        						<table>
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].cep">CEP:<span class="star">*</span></label></td>
															<td><input type="text" onchange="buscaCEP(this.value)" id="clientePessoaJuridica.enderecos[0].cep" name="clientePessoaJuridica.enderecos[0].cep" value="${clientePessoaJuridica.enderecos[0].cep}" class="cep" /></td>
														</tr>
														<tr>
															<td>																
																<label for="clientePessoaJuridica.enderecos[0].tipoDeLogradouro">Tipo de Logradouro:<span class="star">*</span></label>
															</td>
															<td>
																<select name="clientePessoaJuridica.enderecos[0].tipoDeLogradouro" id="clientePessoaJuridica.enderecos[0].tipoDeLogradouro" class="tipoLogradouro juridicatipoLogradouro">
										                			<option value=""></option>
										                			<c:forEach items="${tipoDeLogradouroList}" var="tipoDeLogradouro">
										                    			<option value="${tipoDeLogradouro}" <c:if test="${clientePessoaJuridica.enderecos[0].tipoDeLogradouro==tipoDeLogradouro}">selected="selected"</c:if> >${tipoDeLogradouro.nome}</option>
										                			</c:forEach>
										            			</select>															
															</td>
														</tr>
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].nomeDoLogrado">Logradouro:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaJuridica.enderecos[0].nomeDoLogrado" name="clientePessoaJuridica.enderecos[0].nomeDoLogrado" value="${clientePessoaJuridica.enderecos[0].nomeDoLogrado}" class="text nomeLogrado juridicanomeLogrado" /></td>
														</tr>
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].numero">Número:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaJuridica.enderecos[0].numero" name="clientePessoaJuridica.enderecos[0].numero" value="${clientePessoaJuridica.enderecos[0].numero}" class="numero juridicanumero" /></td>
														</tr>
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].complemento">Complemento:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaJuridica.enderecos[0].complemento" name="clientePessoaJuridica.enderecos[0].complemento" value="${clientePessoaJuridica.enderecos[0].complemento}" class="texto complemento" /></td>
														</tr>
														
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].referencia">Referência:</label></td>
															<td><textarea  id="clientePessoaJuridica.enderecos[0].referencia" name="clientePessoaJuridica.enderecos[0].referencia" class="texto referencia" >${clientePessoaJuridica.enderecos[0].referencia}
															</textarea>
															</td>
														</tr>
														
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].bairro">Bairro:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaJuridica.enderecos[0].bairro" name="clientePessoaJuridica.enderecos[0].bairro" value="${clientePessoaJuridica.enderecos[0].bairro}" class="texto bairro juridicabairro" /></td>
														</tr>
														
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].municipio">Município:<span class="star">*</span></label></td>
															<td><input type="text" id="clientePessoaJuridica.enderecos[0].municipio" name="clientePessoaJuridica.enderecos[0].municipio" value="${clientePessoaJuridica.enderecos[0].municipio}" class="municipio juridicacidade" /></td>
														</tr>
														<tr>
															<td><label for="clientePessoaJuridica.enderecos[0].uf">Estado:<span class="star">*</span></label></td>
															<td><select name="clientePessoaJuridica.enderecos[0].uf" id="clientePessoaJuridica.enderecos[0].uf" class="uf juridicauf">
										            			<option value=""></option>
										            			<c:forEach items="${ufs}" var="uf">  
										                			<option value="${uf}" <c:if test="${clientePessoaJuridica.enderecos[0].uf==uf}">selected="selected"</c:if>>${uf}</option>
										            			</c:forEach>
										        			</select>
										        			</td>
														</tr>
													</table>
	                        					
	                        					
												</div>                        		
	                        				</div>  
	                        			

	                        		
                         		</fieldset>     

		                        <fieldset class="adminform">
                         			<legend>Dados de Usuário:</legend>
                         			<p></p>
									<div class="divPessoa divPessoaFisica">
										<table>
											<tr>
												<td>								
													<input type="hidden" name="clientePessoaFisica.usuarios[0].id" value="${clientePessoaFisica.usuarios[0].id}" />
													<label for="clientePessoaFisica.usuarios[0].email" class="hasTip required">E-mail:<span class="star">&#160;*</span></label>																									
												</td>
												<td><input type="text" id="clientePessoaFisica.usuarios[0].email" name="clientePessoaFisica.usuarios[0].email" value="${clientePessoaFisica.usuarios[0].email}" class="texto" /></td>
											</tr>
											<tr>
												<td>																					
													<label for="clientePessoaFisica.usuarios[0].senha" class="hasTip required">Senha:<span class="star">&#160;*</span></label>																									
												</td>
												<td><input type="password" id="clientePessoaFisica.usuarios[0].senha" name="clientePessoaFisica.usuarios[0].senha" value="${clientePessoaFisica.usuarios[0].senha}" class="password required"   maxlength="8"/></td>
											</tr>
											<tr>
												<td><input name="clientePessoaFisica.contrato" id="clientePessoaFisica.contrato" type="checkbox"/>
												aceitar contrato
												</td>
												<td>
													<div>														 
														<a id="inline" href="#datapf" title="Contrato" style="float:left">Ver contrato</a><div style="float:left"> &#160; | &#160; </div>   
														<a href="<c:url value="/client/contratopf.pdf" />" title="baixar pdf"  style="float:left" ><img  src="<c:url value="/client/image/pdf.png" />"/></a>
														
													</div>
												</td>
											</tr>
										</table>																								
									</div>
										
										
										
										
										
										
										
									<div class="divPessoa divPessoaJuridica">
										<input type="hidden" name="clientePessoaJuridica.usuarios[0].id" value="${clientePessoaJuridica.usuarios[0].id}" />
										<table>
											<tr>
												<td ><label for="clientePessoaJuridica.usuarios[0].nome" class="hasTip required">Nome: <span class="star">&#160;*</span></label></td>
												<td><input type="text" id="clientePessoaJuridica.usuarios[0].nome" name="clientePessoaJuridica.usuarios[0].nome" class="texto nomeUsuario" value=""  /></td>
											</tr>
											<tr>
												<td ><label for="clientePessoaJuridica.usuarios[0].cpf" class="hasTip required">CPF: <span class="star">&#160;*</span></label></td>
												<td><input type="text" id="clientePessoaJuridica.usuarios[0].cpf" name="clientePessoaJuridica.usuarios[0].cpf" class="cpf cpfUsuario" value=""  /></td>
											</tr>
											<tr>
												<td ><label for="clientePessoaJuridica.usuarios[0].rg" class="hasTip required">RG: <span class="star">&#160;*</span></label></td>
												<td><input type="text" id="clientePessoaJuridica.usuarios[0].rg" name="clientePessoaJuridica.usuarios[0].rg" class="texto rgUsuario" value=""  /></td>
											</tr>
											<tr>
	                 							<td><label for="clientePessoaJuridica.usuarios[0].dataDeNascimento">Data de Nascimento:<span class="star">*</span></label></td>
												<td>
													
													<joda:format value="${clientePessoaJuridica.usuarios[0].dataDeNascimento}" pattern="dd/MM/yyyy" var="dataDeNascimento"  />
													<input type="text" id="clientePessoaJuridica.usuarios[0].dataDeNascimento" name="clientePessoaJuridica.usuarios[0].dataDeNascimento"   class="data dataNascimento"/>
												</td>											
                 							</tr>
                 							<tr>
                 								<td><label for="clientePessoaJuridica.usuarios[0].sexo">Sexo:<span class="fieldRequired">*</span></label></td>
												<td><select name="clientePessoaJuridica.usuarios[0].sexo" id="clientePessoaJuridica.usuarios[0].sexo" class="sexoUsuario">
													<option value=""></option>
													<c:forEach items="${sexoList}" var="sexo">
														<option value="${sexo}" <c:if test="${clientePessoaJuridica.usuarios[0].sexo==sexo}">selected="selected"</c:if> >${sexo.nome}</option>
													</c:forEach>
												</select></td>
                 							</tr>
                 							<tr>
												<td ><label for="clientePessoaJuridica.usuarios[0].email" class="hasTip required">E-mail: <span class="star">&#160;*</span></label></td>
												<td><input readonly="readonly" type="text" id="clientePessoaJuridica.usuarios[0].email" name="clientePessoaJuridica.usuarios[0].email" class="email emailUsuario" value=""  /></td>
											</tr>
											
											<tr>
												<td ><label for="clientePessoaJuridica.usuarios[0].senha" class="hasTip required">Senha: <span class="star">&#160;*</span></label></td>
												<td><input type="password" id="clientePessoaJuridica.usuarios[0].senha" name="clientePessoaJuridica.usuarios[0].senha" class="texto senhaUsuario" value=""  maxlength="8" /></td>
											</tr>
											<tr>
												<td>
													<input name="clientePessoaJuridica.contrato" id="clientePessoaJuridica.contrato" type="checkbox"/> aceitar contrato
												</td>
												<td>
													<div>														 
														<a id="inlinepj" href="#datapj" title="Contrato" style="float:left">Ver contrato</a><div style="float:left"> &#160; | &#160; </div>   
														<a href="<c:url value="/client/contratopj.pdf" />" title="baixar pdf"  style="float:left" ><img  src="<c:url value="/client/image/pdf.png" />"/></a>
														
													</div> 
												</td>
											</tr>
										</table>
										
																				
																																								
									</div>
								</fieldset>
													
								<div id="divTipoDeLogradouroList">
									<c:forEach items="${tipoDeLogradouroList}" var="tipoDeLogradouro">
										<option value="${tipoDeLogradouro}">${tipoDeLogradouro.nome}</option>
									</c:forEach>
								</div>
								<div id="divUfList">
									<c:forEach items="${ufs}" var="uf">  
										<option value="${uf}">${uf}</option>
									</c:forEach>
								</div>
								
																																																																																		                                                                                                                                                                                 
			 					<div class="clr"></div>
			 					<p style="padding:10px 0 !important">		<dotec:campoObrigatorio></dotec:campoObrigatorio>
              					</p>
              					</div>
              					
	            				<div class="width-40 fltrt">
	              					<div id="categories-sliders-" class="pane-sliders">
	                					<div style="display:none;">
	                  						<div> 
	                  						</div>
	                					</div>
              						</div>
            					</div>
            					<div class="clr"></div>
           						<input type="submit" />

    </form>
  </div>
  
  
  </div>
  </div>
  
  
  
  <div id="rodape-final">________________________________________________________________________________________________________<br />
  <br />
  Todos os direitos reservados
</div></div>


<!-- ============================== -->																									
<div style="display:none;">
	<div id="datapf" style="color:#333 !important;">
	<p align="center" style="margin-left:22.5pt;">
	<br />
	<strong>CONTRATO DE PRESTA&Ccedil;&Atilde;O DE SERVI&Ccedil;OS DE DEP&Oacute;SITO, TRANSPORTE E CONTROLE DE DOCUMENTOS</strong></p>
<p>
	&nbsp;</p>
<p>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Este Contrato de Presta&ccedil;&atilde;o de Servi&ccedil;os disciplina os termos e condi&ccedil;&otilde;es mediante as quais a <strong>DOTEC Log&iacute;stica de Guarda e Transporte de Documentos EIRELI - ME</strong>, pessoa jur&iacute;dica devidamente registrada na JUCERJA sob o NIRE 33.2.0871303-1 e inscrita no <strong>CNPJ/MF</strong> sob o <strong>N&ordm;. 12.270.628/0001-96</strong>, situada na Rua Bar&atilde;o de Itaipu 303, Andara&iacute;, Rio de Janeiro &ndash; RJ, CEP 20.541-120 (de agora em diante denominada apenas DOTEC) oferece a pessoas f&iacute;sicas maiores de 18 anos e a pessoas jur&iacute;dicas (de agora em diante denominado apenas CONTRATANTE), ap&oacute;s a aceita&ccedil;&atilde;o eletr&ocirc;nica deste contrato, os servi&ccedil;os dep&oacute;sito, transporte e controle de documentos conforme cl&aacute;usulas abaixo:</p>
<p>
	&nbsp;</p>
<p align="center">
	CONTRATANTE</p>
<p>
	&nbsp;</p>
<p align="center">
	PARA PESSOA F&Iacute;SICA</p>
<p>
	NOME DO RESPONS&Aacute;VEL:</p>
<p>
	CPF:</p>
<p>
	E-MAIL CADASTRADO:</p>
<p>
	ENDERE&Ccedil;O DE COBRAN&Ccedil;A:</p>
<p>
	&nbsp;</p>
<p>
	<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Aten&ccedil;&atilde;o: Ao clicar o bot&atilde;o &quot;LI E ACEITO OS TERMOS DO CONTRATO&quot;, o CONTRATANTE estar&aacute; automaticamente aceitando todos os termos e condi&ccedil;&otilde;es contidos neste contrato. </strong></p>
<p>
	&nbsp;</p>
<p>
	<strong>1. OBJETO</strong></p>
<p>
	&nbsp;</p>
<p>
	1.1. O objeto deste contrato &eacute; a presta&ccedil;&atilde;o de servi&ccedil;os de dep&oacute;sito, transporte e controle de documentos.</p>
<p>
	&nbsp;</p>
<p>
	1.2 O CONTRATANTE sujeitar-se-&aacute; &agrave;s normas e condi&ccedil;&otilde;es estabelecidas no presente contrato, bem como &agrave;s disposi&ccedil;&otilde;es legais contidas no C&oacute;digo Civil Brasileiro e demais regras estabelecidas pela legisla&ccedil;&atilde;o pertinente.</p>
<p style="margin-left:22.5pt;">
	&nbsp;</p>
<p>
	<strong>2. FORMA DE EXECU&Ccedil;&Atilde;O</strong></p>
<p>
	&nbsp;</p>
<p>
	2.1. A presta&ccedil;&atilde;o dos servi&ccedil;os ser&aacute; efetivada pelo CONTRATANTE atrav&eacute;s de cadastro no site <a href="http://www.dotec.com.br/">www.dotec.com.br</a> mediante a cria&ccedil;&atilde;o de <em>login</em> e senha com acesso exclusivamente pela internet. O CONTRATANTE poder&aacute; atrav&eacute;s do Sistema criar usu&aacute;rios secund&aacute;rios para a visualiza&ccedil;&atilde;o e movimenta&ccedil;&atilde;o, sendo de sua &uacute;nica responsabilidade a quita&ccedil;&atilde;o dos valores correspondentes.</p>
<p>
	2.2. Ap&oacute;s o cadastro e a montagem de uma &aacute;rvore de estrutura no banco de dados, conjunto de diret&oacute;rios, o CONTRATANTE poder&aacute; solicitar a DOTEC a quantidade que desejar de caixas para acondicionar seus arquivos.</p>
<p>
	2.3. A DOTEC disponibilizar&aacute; caixas padronizadas que comportam at&eacute; 20 quilos em seus 44x22x36 cm. Para arquivos mais pesados ou de dimens&otilde;es maiores a DOTEC dever&aacute; ser previamente consultada.</p>
<p>
	2.4. &Eacute; vedado o arquivo de artigos perigosos, explosivos, produtos qu&iacute;micos, radioativos, perec&iacute;veis, derivados de combust&iacute;veis, l&iacute;quidos inflam&aacute;veis, objetos de valor, tais como objetos feitos de ouro, platina, prata, pedras preciosas e semi-preciosas, valores em esp&eacute;cie, dinheiro, cheques, vale-transportes, cart&otilde;es de cr&eacute;dito, entre outros e demais artigos proibidos por legisla&ccedil;&atilde;o espec&iacute;fica.</p>
<p>
	2.5. Objetos considerados de valor n&atilde;o ser&atilde;o aceitos pela DOTEC.</p>
<p>
	2.6. A DOTEC n&atilde;o &eacute; respons&aacute;vel pela declara&ccedil;&atilde;o de conte&uacute;do indicada pelo CONTRATANTE e presumir&aacute; verdadeiras as declara&ccedil;&otilde;es por ele expressas.</p>
<p>
	2.7. No caso de volumes suspeitos, a DOTEC poder&aacute; recusar a sua coleta e/ou arquivamento, e &nbsp;ainda poder&aacute; realizar &agrave; sua inspe&ccedil;&atilde;o manualmente ou por dispositivos eletr&ocirc;nicos, Raio X e/ou outros tipos de analisadores, substituindo o lacre por outro, sem que isto cause qualquer responsabilidade sobre o conte&uacute;do da mesma.</p>
<p>
	2.8. A DOTEC n&atilde;o aceitar&aacute; a caixa que visualmente apresentar sinais de viola&ccedil;&atilde;o, adultera&ccedil;&atilde;o, avaria, fios expostos ou vazamentos, que possam tornar a caixa insegura para o transporte e arquivamento.</p>
<p>
	2.9. O CONTRATANTE ser&aacute; o respons&aacute;vel pelo controle de movimenta&ccedil;&atilde;o e conte&uacute;do da(s) caixa(s), sendo o &uacute;nico respons&aacute;vel pela identifica&ccedil;&atilde;o e indexa&ccedil;&atilde;o dos documentos na base de dados.</p>
<p>
	2.10. O acesso ao sistema ficar&aacute; condicionado ao pagamento de taxa de ades&atilde;o e, posteriormente, ao pagamento das mensalidades, ficando o acesso do CONTRATANTE ao sistema DOTEC vinculado &agrave; compensa&ccedil;&atilde;o do pagamento.</p>
<p>
	2.11. Os servi&ccedil;os ser&atilde;o prestados de forma n&atilde;o personalizada, inexistindo a obriga&ccedil;&atilde;o da DOTEC em implantar e desenvolver funcionalidades adicionais no SITE/SOFTWARE, a pedido do CONTRATANTE e exce&ccedil;&otilde;es concedidas devem ser consideradas como cortesia.</p>
<p>
	2.12. As d&uacute;vidas sobre utiliza&ccedil;&atilde;o do sistema ser&atilde;o solucionadas por e-mail,&nbsp; atrav&eacute;s de abertura de chamados pelo pr&oacute;prio sistema, no hor&aacute;rio comercial, sem limite de ocorr&ecirc;ncias.</p>
<p>
	2.13. O CONTRATANTE poder&aacute; designar no m&aacute;ximo 10 (dez) usu&aacute;rios para utiliza&ccedil;&atilde;o do sistema.</p>
<p>
	&nbsp;</p>
<p>
	<strong>3. &nbsp;DA MOVIMENTA&Ccedil;&Atilde;O DE ARQUIVOS</strong></p>
<p>
	&nbsp;</p>
<p>
	3.1. O CONTRATANTE poder&aacute; requerer a DOTEC a coleta e devolu&ccedil;&atilde;o de caixa(s) sempre que necess&aacute;rio, atrav&eacute;s de comandos no pr&oacute;prio Sistema</p>
<p>
	3.2. A coleta e a devolu&ccedil;&atilde;o de caixa(s) pela DOTEC ocorrer&aacute; em at&eacute; 48h a partir da solicita&ccedil;&atilde;o, no endere&ccedil;o indicado pelo CONTRATANTE.</p>
<p>
	3.3. A coleta e a devolu&ccedil;&atilde;o ocorrer&aacute; sempre em dias &uacute;teis e em hor&aacute;rio comercial, sem hora determinada. Ser&atilde;o efetuadas at&eacute; 2 (duas) tentativas de entrega no endere&ccedil;o indicado pelo CONTRATANTE.</p>
<p>
	3.4 Caso n&atilde;o haja sucesso, na &uacute;ltima tentativa, o CONTRATANTE ser&aacute; notificado para providenciar a retirada da caixa no local onde ela se encontra, ou pagar novo frete.</p>
<p>
	&nbsp;</p>
<p>
	<strong>4. DO PRE&Ccedil;O&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong></p>
<p>
	&nbsp;</p>
<p>
	4.1. Pelo cadastro no sistema DOTEC o CONTRATANTE pagar&aacute; a taxa de ades&atilde;o estipulada em nosso Portal no campo tabela de pre&ccedil;os.</p>
<p>
	4.2. O CONTRATANTE pagar&aacute; a DOTEC o valor correspondente ao frete por Solicita&ccedil;&atilde;o de Movimenta&ccedil;&atilde;o de Caixa, conforme tabela de pre&ccedil;os de frete contido em nosso Portal.</p>
<p>
	4.3. O CONTRATANTE pagar&aacute; a DOTEC o valor mensal pelo dep&oacute;sito dos arquivos, conforme quantidade e volume.</p>
<p>
	4.4. Os valores pagos ap&oacute;s o respectivo vencimento acarretar&atilde;o ao CONTRATANTE o acr&eacute;scimo de multa de 2% (dois por cento), sem preju&iacute;zo de juros morat&oacute;rios de 1% (um por cento) ao m&ecirc;s ou fra&ccedil;&atilde;o e corre&ccedil;&atilde;o monet&aacute;ria.</p>
<p>
	4.5 O atraso superior a 30 (trinta) dias no pagamento de quaisquer servi&ccedil;os ensejar&aacute; o bloqueio do acesso do CONTRATANTE ao sistema, sem preju&iacute;zo das demais san&ccedil;&otilde;es dispostas neste contrato.</p>
<p>
	&nbsp;</p>
<p>
	<strong>5. RETEN&Ccedil;&Atilde;O DE ARQUIVOS</strong></p>
<p>
	5.1. &Eacute; outorgado a DOTEC o direito de reten&ccedil;&atilde;o, sobre todas as caixas arquivadas, no caso de n&atilde;o pagamento integral dos fretes, taxas, mensalidades ou pre&ccedil;os de qualquer natureza relacionados aos servi&ccedil;os ora contratados, podendo reter todo e qualquer arquivo at&eacute; o pagamento integral das despesas efetuadas, inclusive podendo cobrar taxa de perman&ecirc;ncia at&eacute; que se d&ecirc; a liquida&ccedil;&atilde;o dos valores envolvidos.</p>
<p>
	<strong>6. CONFIDENCIALIDADE</strong></p>
<p>
	6.1. A DOTEC tratar&aacute; tais informa&ccedil;&otilde;es como confidenciais, submetendo-as ao sigilo.</p>
<p>
	6.2. Na ocasi&atilde;o de retirada das caixas de arquivos, as mesmas ser&atilde;o devidamente lacradas com etiquetas de numera&ccedil;&atilde;o &uacute;nica na presen&ccedil;a do CONTRATANTE, de forma que o seu conte&uacute;do ser&aacute; inviol&aacute;vel, exceto no estipulado no item 2.7.</p>
<p>
	6.3. A DOTEC por si e por seus parceiros, prepostos, funcion&aacute;rios e prestadores de servi&ccedil;o, se obriga a manter o mais absoluto sigilo com rela&ccedil;&atilde;o &agrave;s informa&ccedil;&otilde;es e &aacute;rvore de pastas, rela&ccedil;&atilde;o de documentos e dados do CONTRATANTE, a que, diretamente ou atrav&eacute;s de seus funcion&aacute;rios, parceiros, prepostos e prestadores de servi&ccedil;o venha a ter acesso, conhecimento ou que venha a lhe ser confiado durante e em raz&atilde;o do presente instrumento. Compromete-se, outrossim, a n&atilde;o revelar, reproduzir, utilizar ou dar conhecimento, em hip&oacute;tese alguma, a terceiros, bem como a n&atilde;o permitir que nenhum de seus funcion&aacute;rios, parceiros, prepostos e prestadores de servi&ccedil;o fa&ccedil;a uso dessas de forma indevida.</p>
<p>
	6.4. A confidencialidade ora tratada engloba, ainda, o teor do presente instrumento, que dever&aacute; ser mantido em sigilo, podendo ser tornado p&uacute;blico pelas partes somente mediante autoriza&ccedil;&atilde;o da outra parte, ou para uso em processo judicial.</p>
<p>
	6.5. A DOTEC poder&aacute; divulgar as informa&ccedil;&otilde;es ou o conte&uacute;do dos arquivos somente no caso de ordem judicial, desde que antes de realizar a divulga&ccedil;&atilde;o notifique o CONTRATANTE, no m&iacute;nimo 24 (vinte e quatro) horas antes &agrave; referida divulga&ccedil;&atilde;o, para que o CONTRATANTE, querendo, promova as medidas necess&aacute;rias e cab&iacute;veis para a prote&ccedil;&atilde;o dos seus interesses.</p>
<p>
	6.6. A DOTEC dar&aacute; ci&ecirc;ncia imediata ao CONTRATANTE sobre qualquer uso ou divulga&ccedil;&atilde;o n&atilde;o autorizada das informa&ccedil;&otilde;es, ou de qualquer outra viola&ccedil;&atilde;o do conte&uacute;do dos arquivos e colaborar&aacute;, de todas as formas poss&iacute;veis, para recuperar a posse dos mesmos e para impedir que seu uso n&atilde;o autorizado prossiga ou se amplie, tudo sem preju&iacute;zo da indeniza&ccedil;&atilde;o pelos danos causados ao CONTRATANTE.</p>
<p>
	6.7. As informa&ccedil;&otilde;es do CONTRATANTE contidas nas &aacute;rvores de pastas e arquivos s&atilde;o, e continuar&atilde;o sendo, de inteira e exclusiva propriedade do CONTRATANTE o que n&atilde;o confere a DOTEC&nbsp;o direito de uso destas informa&ccedil;&otilde;es.</p>
<p>
	&nbsp;</p>
<p>
	<strong>7. DO SEGURO</strong></p>
<p>
	&nbsp;</p>
<p>
	7.1. A DOTEC concede ao CONTRATANTE um seguro autom&aacute;tico que d&aacute; cobertura de at&eacute; 5 (cinco) vezes o valor cobrado mensalmente para sua guarda, por caixa, em caso de sinistro no dep&oacute;sito ou em caso de grave acidente ocorrido com a(s) caixa(s) durante o translado CONTRATANTE X DOTEC X CONTRATANTE.</p>
<p>
	&nbsp;</p>
<p>
	<strong>8. VIG&Ecirc;NCIA, REAJUSTE E MENOR PRE&Ccedil;O GARANTIDO&nbsp;</strong></p>
<p>
	&nbsp;</p>
<p>
	8.1.&nbsp;O presente contrato entrar&aacute; em vigor na data de seu aceite eletr&ocirc;nico, quando do cadastramento, e ter&aacute; dura&ccedil;&atilde;o de <strong>24 (vinte e quatro)</strong> meses.</p>
<p>
	8.2. Expirando o prazo de dura&ccedil;&atilde;o, o presente instrumento se renovar&aacute; AUTOMATICAMENTE, por igual prazo, caso as partes n&atilde;o manifestem por escrito com anteced&ecirc;ncia m&iacute;nima de 60 (sessenta) dias a inten&ccedil;&atilde;o de n&atilde;o renov&aacute;-lo.</p>
<p>
	8.3.&nbsp;Haver&aacute; reajuste dos valores, a cada 12 (doze) meses de contrato, com base na varia&ccedil;&atilde;o positiva do &iacute;ndice IPC-A acumulado no per&iacute;odo e no caso de extin&ccedil;&atilde;o do referido &iacute;ndice, aplicar-se-&aacute; o IGP-M ou &iacute;ndice de finalidade similar que reflita a desvaloriza&ccedil;&atilde;o da moeda oficial do Brasil.</p>
<p>
	8.4.&nbsp;Se durante a vig&ecirc;ncia deste contrato houver altera&ccedil;&atilde;o dos pre&ccedil;os praticados pela DOTEC para os mesmos servi&ccedil;os e condi&ccedil;&otilde;es comerciais, o CONTRATANTE poder&aacute; escolher entre o pre&ccedil;o garantido neste contrato (ap&oacute;s reajustes previstos no par&aacute;grafo supracitado) ou o novo pre&ccedil;o oferecido publicamente pelo servi&ccedil;o, garantindo assim que o CONTRATANTE pague pelo mesmo servi&ccedil;o o menor pre&ccedil;o.</p>
<p>
	&nbsp;</p>
<p>
	<strong>9. RESCIS&Atilde;O OU CANCELAMENTO DO SERVI&Ccedil;O</strong></p>
<p>
	&nbsp;</p>
<p>
	9.1. O presente contrato poder&aacute; ser rescindido a qualquer tempo, por simples conveni&ecirc;ncia de uma das partes, mediante comunica&ccedil;&atilde;o, por escrito com anteced&ecirc;ncia m&iacute;nima de 60 (sessenta) dias.</p>
<p>
	9.2. Caso o cancelamento unilateral seja feito pelo&nbsp;CONTRATANTE, n&atilde;o haver&aacute;&nbsp;multa contratual, por&eacute;m ainda ser&atilde;o devidas as parcelas em aberto, bem como mensalidades em atraso at&eacute; a data da notifica&ccedil;&atilde;o.</p>
<p>
	9.3. No caso de rescis&atilde;o, a dispensa pelo CONTRATANTE da execu&ccedil;&atilde;o de quaisquer servi&ccedil;os, seja por qual for a raz&atilde;o, durante o prazo do pr&eacute;-aviso, dever&aacute; ser feita por escrito, n&atilde;o desobrigando-o do pagamento do pre&ccedil;o integral at&eacute; o termo final do aviso.</p>
<p>
	9.4.&nbsp;Caso o cancelamento unilateral seja feito pela&nbsp;DOTEC, n&atilde;o incidir&aacute; multa contratual, por&eacute;m as parcelas em aberto e atrasadas dever&atilde;o ser quitadas pelo CONTRATANTE. As parcelas em aberto, e que ainda n&atilde;o tenham vencido, ser&atilde;o canceladas pela DOTEC e o CONTRATANTE estar&aacute; desobrigado de quit&aacute;-las.</p>
<p>
	9.5. &nbsp;O presente contrato tamb&eacute;m poder&aacute; ser rescindido a qualquer tempo independente de aviso, notifica&ccedil;&atilde;o ou interpela&ccedil;&atilde;o judicial ou extrajudicial, nos seguintes casos:</p>
<p style="margin-left:35.4pt;">
	a)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;inexecu&ccedil;&atilde;o, abandono ou paralisa&ccedil;&atilde;o dos servi&ccedil;os por mais de 30 (trinta) dias;</p>
<p style="margin-left:35.4pt;">
	b)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;imper&iacute;cia ou neglig&ecirc;ncia na execu&ccedil;&atilde;o dos servi&ccedil;os;</p>
<p style="margin-left:35.4pt;">
	d)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;o n&atilde;o cumprimento de quaisquer uma das cl&aacute;usulas do contrato;</p>
<p>
	&nbsp;</p>
<p>
	9.6. Em caso de rescis&atilde;o ou encerramento do contrato, por quaisquer raz&otilde;es, o CONTRATANTE dever&aacute; requerer a DOTEC a devolu&ccedil;&atilde;o da(s) caixa(s) at&eacute; a data limite de vig&ecirc;ncia do contrato. &nbsp;Caso o CONTRATANTE n&atilde;o requeira a carga no prazo determinado, a DOTEC dever&aacute; notific&aacute;-lo para que, em um prazo de 15 (quinze) dias, a contar do recebimento do aviso, providencie a retirada da(s) caixa(s), sob pena de ser considerada abandonada e serem adotadas as medidas legais cab&iacute;veis.</p>
<p>
	9.7 Poder&atilde;o ser cobradas taxas adicionais, na hip&oacute;tese de n&atilde;o retirada da(s) caixa(s) nos prazos supra-estabelecidos.</p>
<p>
	&nbsp;</p>
<p>
	<strong>10. RESPONSABILIDADE</strong></p>
<p>
	&nbsp;</p>
<p>
	10.1. A DOTEC responder&aacute; integralmente perante o CONTRATANTE por quaisquer danos causados decorrentes de atos il&iacute;citos praticados por seus empregados e/ou representantes ou por quaisquer descumprimentos das normas aqui contratadas, no limite do montante de 1 (uma) mensalidade paga pelo CONTRATANTE.</p>
<p>
	<strong>11. EXONERA&Ccedil;&Atilde;O DE RESPONSABILIDADE</strong></p>
<p>
	11.1 Apesar da utiliza&ccedil;&atilde;o dos melhores esfor&ccedil;os para efetuar a guarda dos arquivos, a DOTEC n&atilde;o ser&aacute; respons&aacute;vel por eventuais perdas e danos, extravios ou quaisquer outros imprevistos, se estes decorrerem de:</p>
<p style="margin-left:35.4pt;">
	a) Ato de guerra ou conflito armado;</p>
<p style="margin-left:35.4pt;">
	b) Dano el&eacute;trico ou magn&eacute;tico, bem como desaparecimento ou qualquer outro dano semelhante a imagens eletr&ocirc;nicas, fotogr&aacute;ficas ou qualquer outro tipo de grava&ccedil;&atilde;o;</p>
<p style="margin-left:35.4pt;">
	c) Qualquer v&iacute;cio, defeito ou caracter&iacute;sticas inerentes aos arquivos depositados;</p>
<p style="margin-left:35.4pt;">
	d) Quaisquer efeitos decorrentes de caso fortuito ou for&ccedil;a maior que venham a atingir as instala&ccedil;&otilde;es ou ve&iacute;culos da DOTEC.</p>
<p>
	11.2 A DOTEC n&atilde;o se responsabilizar&aacute;, sob qualquer hip&oacute;tese, por indeniza&ccedil;&otilde;es por danos morais, lucros cessantes ou qualquer outra forma de dano ou preju&iacute;zo indireto, incluindo, sem limita&ccedil;&atilde;o, a perda de utilidade, de mercado e outros que possam, por qualquer motivo, vir a ser incorridos pelo CONTRATANTE, independentemente do fato de a DOTEC ter pr&eacute;via ci&ecirc;ncia de que tal preju&iacute;zo poderia vir a ocorrer.</p>
<p>
	&nbsp;</p>
<p>
	<strong>12. DO FORO</strong></p>
<p>
	12.1 Para dirimir quaisquer controv&eacute;rsias referentes ao presente instrumento fica eleito o foro da Comarca da Capital do Rio de Janeiro, com expressa ren&uacute;ncia a qualquer outro, por mais privilegiado que seja, para nele dirimirem toda e qualquer d&uacute;vida ou lit&iacute;gio decorrentes do presente contrato.</p>
<p align="center">
	<br />
	<strong>DOTEC Log&iacute;stica de Guarda e Transporte de Documentos Ltda</strong></p>


	</div>
</div>
<!-- ============================== -->
								
								
								
								
<!-- ============================== -->																									
<div style="display:none">
	<div id="datapj" style="color:#333 !important;">
	
	
	<p align="center" style="margin-left:22.5pt;">
	<br />
	<strong>CONTRATO DE PRESTA&Ccedil;&Atilde;O DE SERVI&Ccedil;OS DE DEP&Oacute;SITO, TRANSPORTE E CONTROLE DE DOCUMENTOS</strong></p>
<p>
	&nbsp;</p>
<p>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Este Contrato de Presta&ccedil;&atilde;o de Servi&ccedil;os disciplina os termos e condi&ccedil;&otilde;es mediante as quais a <strong>DOTEC Log&iacute;stica de Guarda e Transporte de Documentos EIRELI - ME</strong>, pessoa jur&iacute;dica devidamente registrada na JUCERJA sob o NIRE 33.2.0871303-1 e inscrita no <strong>CNPJ/MF</strong> sob o <strong>N&ordm;. 12.270.628/0001-96</strong>, situada na Rua Bar&atilde;o de Itaipu 303, Andara&iacute;, Rio de Janeiro &ndash; RJ, CEP 20.541-120 (de agora em diante denominada apenas DOTEC) oferece a pessoas f&iacute;sicas maiores de 18 anos e a pessoas jur&iacute;dicas (de agora em diante denominado apenas CONTRATANTE), ap&oacute;s a aceita&ccedil;&atilde;o eletr&ocirc;nica deste contrato, os servi&ccedil;os dep&oacute;sito, transporte e controle de documentos conforme cl&aacute;usulas abaixo:</p>
<p>
	&nbsp;</p>
<p align="center">
	CONTRATANTE</p>
<p>
	&nbsp;</p>
<p align="center">
	&nbsp;</p>
<p align="center">
	PARA PESSOA JUR&Iacute;DICA</p>
<p style="text-align: left; ">
	EMPRESA:</p>
<p style="text-align: left; ">
	CNPJ:</p>
<p style="text-align: left; ">
	NOME DO RESPONS&Aacute;VEL:</p>
<p style="text-align: left; ">
	CPF:</p>
<p style="text-align: left; ">
	E-MAIL CADASTRADO:</p>
<p style="text-align: left; ">
	ENDERE&Ccedil;O DE COBRAN&Ccedil;A:</p>
<p>
	&nbsp;</p>
<p>
	<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Aten&ccedil;&atilde;o: Ao clicar o bot&atilde;o &quot;LI E ACEITO OS TERMOS DO CONTRATO&quot;, o CONTRATANTE estar&aacute; automaticamente aceitando todos os termos e condi&ccedil;&otilde;es contidos neste contrato. </strong></p>
<p>
	&nbsp;</p>
<p>
	<strong>1. OBJETO</strong></p>
<p>
	&nbsp;</p>
<p>
	1.1. O objeto deste contrato &eacute; a presta&ccedil;&atilde;o de servi&ccedil;os de dep&oacute;sito, transporte e controle de documentos.</p>
<p>
	&nbsp;</p>
<p>
	1.2 O CONTRATANTE sujeitar-se-&aacute; &agrave;s normas e condi&ccedil;&otilde;es estabelecidas no presente contrato, bem como &agrave;s disposi&ccedil;&otilde;es legais contidas no C&oacute;digo Civil Brasileiro e demais regras estabelecidas pela legisla&ccedil;&atilde;o pertinente.</p>
<p style="margin-left:22.5pt;">
	&nbsp;</p>
<p>
	<strong>2. FORMA DE EXECU&Ccedil;&Atilde;O</strong></p>
<p>
	&nbsp;</p>
<p>
	2.1. A presta&ccedil;&atilde;o dos servi&ccedil;os ser&aacute; efetivada pelo CONTRATANTE atrav&eacute;s de cadastro no site <a href="http://www.dotec.com.br/">www.dotec.com.br</a> mediante a cria&ccedil;&atilde;o de <em>login</em> e senha com acesso exclusivamente pela internet. O CONTRATANTE poder&aacute; atrav&eacute;s do Sistema criar usu&aacute;rios secund&aacute;rios para a visualiza&ccedil;&atilde;o e movimenta&ccedil;&atilde;o, sendo de sua &uacute;nica responsabilidade a quita&ccedil;&atilde;o dos valores correspondentes.</p>
<p>
	2.2. Ap&oacute;s o cadastro e a montagem de uma &aacute;rvore de estrutura no banco de dados, conjunto de diret&oacute;rios, o CONTRATANTE poder&aacute; solicitar a DOTEC a quantidade que desejar de caixas para acondicionar seus arquivos.</p>
<p>
	2.3. A DOTEC disponibilizar&aacute; caixas padronizadas que comportam at&eacute; 20 quilos em seus 44x22x36 cm. Para arquivos mais pesados ou de dimens&otilde;es maiores a DOTEC dever&aacute; ser previamente consultada.</p>
<p>
	2.4. &Eacute; vedado o arquivo de artigos perigosos, explosivos, produtos qu&iacute;micos, radioativos, perec&iacute;veis, derivados de combust&iacute;veis, l&iacute;quidos inflam&aacute;veis, objetos de valor, tais como objetos feitos de ouro, platina, prata, pedras preciosas e semi-preciosas, valores em esp&eacute;cie, dinheiro, cheques, vale-transportes, cart&otilde;es de cr&eacute;dito, entre outros e demais artigos proibidos por legisla&ccedil;&atilde;o espec&iacute;fica.</p>
<p>
	2.5. Objetos considerados de valor n&atilde;o ser&atilde;o aceitos pela DOTEC.</p>
<p>
	2.6. A DOTEC n&atilde;o &eacute; respons&aacute;vel pela declara&ccedil;&atilde;o de conte&uacute;do indicada pelo CONTRATANTE e presumir&aacute; verdadeiras as declara&ccedil;&otilde;es por ele expressas.</p>
<p>
	2.7. No caso de volumes suspeitos, a DOTEC poder&aacute; recusar a sua coleta e/ou arquivamento, e &nbsp;ainda poder&aacute; realizar &agrave; sua inspe&ccedil;&atilde;o manualmente ou por dispositivos eletr&ocirc;nicos, Raio X e/ou outros tipos de analisadores, substituindo o lacre por outro, sem que isto cause qualquer responsabilidade sobre o conte&uacute;do da mesma.</p>
<p>
	2.8. A DOTEC n&atilde;o aceitar&aacute; a caixa que visualmente apresentar sinais de viola&ccedil;&atilde;o, adultera&ccedil;&atilde;o, avaria, fios expostos ou vazamentos, que possam tornar a caixa insegura para o transporte e arquivamento.</p>
<p>
	2.9. O CONTRATANTE ser&aacute; o respons&aacute;vel pelo controle de movimenta&ccedil;&atilde;o e conte&uacute;do da(s) caixa(s), sendo o &uacute;nico respons&aacute;vel pela identifica&ccedil;&atilde;o e indexa&ccedil;&atilde;o dos documentos na base de dados.</p>
<p>
	2.10. O acesso ao sistema ficar&aacute; condicionado ao pagamento de taxa de ades&atilde;o e, posteriormente, ao pagamento das mensalidades, ficando o acesso do CONTRATANTE ao sistema DOTEC vinculado &agrave; compensa&ccedil;&atilde;o do pagamento.</p>
<p>
	2.11. Os servi&ccedil;os ser&atilde;o prestados de forma n&atilde;o personalizada, inexistindo a obriga&ccedil;&atilde;o da DOTEC em implantar e desenvolver funcionalidades adicionais no SITE/SOFTWARE, a pedido do CONTRATANTE e exce&ccedil;&otilde;es concedidas devem ser consideradas como cortesia.</p>
<p>
	2.12. As d&uacute;vidas sobre utiliza&ccedil;&atilde;o do sistema ser&atilde;o solucionadas por e-mail,&nbsp; atrav&eacute;s de abertura de chamados pelo pr&oacute;prio sistema, no hor&aacute;rio comercial, sem limite de ocorr&ecirc;ncias.</p>
<p>
	2.13. O CONTRATANTE poder&aacute; designar no m&aacute;ximo 10 (dez) usu&aacute;rios para utiliza&ccedil;&atilde;o do sistema.</p>
<p>
	&nbsp;</p>
<p>
	<strong>3. &nbsp;DA MOVIMENTA&Ccedil;&Atilde;O DE ARQUIVOS</strong></p>
<p>
	&nbsp;</p>
<p>
	3.1. O CONTRATANTE poder&aacute; requerer a DOTEC a coleta e devolu&ccedil;&atilde;o de caixa(s) sempre que necess&aacute;rio, atrav&eacute;s de comandos no pr&oacute;prio Sistema</p>
<p>
	3.2. A coleta e a devolu&ccedil;&atilde;o de caixa(s) pela DOTEC ocorrer&aacute; em at&eacute; 48h a partir da solicita&ccedil;&atilde;o, no endere&ccedil;o indicado pelo CONTRATANTE.</p>
<p>
	3.3. A coleta e a devolu&ccedil;&atilde;o ocorrer&aacute; sempre em dias &uacute;teis e em hor&aacute;rio comercial, sem hora determinada. Ser&atilde;o efetuadas at&eacute; 2 (duas) tentativas de entrega no endere&ccedil;o indicado pelo CONTRATANTE.</p>
<p>
	3.4 Caso n&atilde;o haja sucesso, na &uacute;ltima tentativa, o CONTRATANTE ser&aacute; notificado para providenciar a retirada da caixa no local onde ela se encontra, ou pagar novo frete.</p>
<p>
	&nbsp;</p>
<p>
	<strong>4. DO PRE&Ccedil;O&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong></p>
<p>
	&nbsp;</p>
<p>
	4.1. Pelo cadastro no sistema DOTEC o CONTRATANTE pagar&aacute; a taxa de ades&atilde;o estipulada em nosso Portal no campo tabela de pre&ccedil;os.</p>
<p>
	4.2. O CONTRATANTE pagar&aacute; a DOTEC o valor correspondente ao frete por Solicita&ccedil;&atilde;o de Movimenta&ccedil;&atilde;o de Caixa, conforme tabela de pre&ccedil;os de frete contido em nosso Portal.</p>
<p>
	4.3. O CONTRATANTE pagar&aacute; a DOTEC o valor mensal pelo dep&oacute;sito dos arquivos, conforme quantidade e volume.</p>
<p>
	4.4. Os valores pagos ap&oacute;s o respectivo vencimento acarretar&atilde;o ao CONTRATANTE o acr&eacute;scimo de multa de 2% (dois por cento), sem preju&iacute;zo de juros morat&oacute;rios de 1% (um por cento) ao m&ecirc;s ou fra&ccedil;&atilde;o e corre&ccedil;&atilde;o monet&aacute;ria.</p>
<p>
	4.5 O atraso superior a 30 (trinta) dias no pagamento de quaisquer servi&ccedil;os ensejar&aacute; o bloqueio do acesso do CONTRATANTE ao sistema, sem preju&iacute;zo das demais san&ccedil;&otilde;es dispostas neste contrato.</p>
<p>
	&nbsp;</p>
<p>
	<strong>5. RETEN&Ccedil;&Atilde;O DE ARQUIVOS</strong></p>
<p>
	5.1. &Eacute; outorgado a DOTEC o direito de reten&ccedil;&atilde;o, sobre todas as caixas arquivadas, no caso de n&atilde;o pagamento integral dos fretes, taxas, mensalidades ou pre&ccedil;os de qualquer natureza relacionados aos servi&ccedil;os ora contratados, podendo reter todo e qualquer arquivo at&eacute; o pagamento integral das despesas efetuadas, inclusive podendo cobrar taxa de perman&ecirc;ncia at&eacute; que se d&ecirc; a liquida&ccedil;&atilde;o dos valores envolvidos.</p>
<p>
	<strong>6. CONFIDENCIALIDADE</strong></p>
<p>
	6.1. A DOTEC tratar&aacute; tais informa&ccedil;&otilde;es como confidenciais, submetendo-as ao sigilo.</p>
<p>
	6.2. Na ocasi&atilde;o de retirada das caixas de arquivos, as mesmas ser&atilde;o devidamente lacradas com etiquetas de numera&ccedil;&atilde;o &uacute;nica na presen&ccedil;a do CONTRATANTE, de forma que o seu conte&uacute;do ser&aacute; inviol&aacute;vel, exceto no estipulado no item 2.7.</p>
<p>
	6.3. A DOTEC por si e por seus parceiros, prepostos, funcion&aacute;rios e prestadores de servi&ccedil;o, se obriga a manter o mais absoluto sigilo com rela&ccedil;&atilde;o &agrave;s informa&ccedil;&otilde;es e &aacute;rvore de pastas, rela&ccedil;&atilde;o de documentos e dados do CONTRATANTE, a que, diretamente ou atrav&eacute;s de seus funcion&aacute;rios, parceiros, prepostos e prestadores de servi&ccedil;o venha a ter acesso, conhecimento ou que venha a lhe ser confiado durante e em raz&atilde;o do presente instrumento. Compromete-se, outrossim, a n&atilde;o revelar, reproduzir, utilizar ou dar conhecimento, em hip&oacute;tese alguma, a terceiros, bem como a n&atilde;o permitir que nenhum de seus funcion&aacute;rios, parceiros, prepostos e prestadores de servi&ccedil;o fa&ccedil;a uso dessas de forma indevida.</p>
<p>
	6.4. A confidencialidade ora tratada engloba, ainda, o teor do presente instrumento, que dever&aacute; ser mantido em sigilo, podendo ser tornado p&uacute;blico pelas partes somente mediante autoriza&ccedil;&atilde;o da outra parte, ou para uso em processo judicial.</p>
<p>
	6.5. A DOTEC poder&aacute; divulgar as informa&ccedil;&otilde;es ou o conte&uacute;do dos arquivos somente no caso de ordem judicial, desde que antes de realizar a divulga&ccedil;&atilde;o notifique o CONTRATANTE, no m&iacute;nimo 24 (vinte e quatro) horas antes &agrave; referida divulga&ccedil;&atilde;o, para que o CONTRATANTE, querendo, promova as medidas necess&aacute;rias e cab&iacute;veis para a prote&ccedil;&atilde;o dos seus interesses.</p>
<p>
	6.6. A DOTEC dar&aacute; ci&ecirc;ncia imediata ao CONTRATANTE sobre qualquer uso ou divulga&ccedil;&atilde;o n&atilde;o autorizada das informa&ccedil;&otilde;es, ou de qualquer outra viola&ccedil;&atilde;o do conte&uacute;do dos arquivos e colaborar&aacute;, de todas as formas poss&iacute;veis, para recuperar a posse dos mesmos e para impedir que seu uso n&atilde;o autorizado prossiga ou se amplie, tudo sem preju&iacute;zo da indeniza&ccedil;&atilde;o pelos danos causados ao CONTRATANTE.</p>
<p>
	6.7. As informa&ccedil;&otilde;es do CONTRATANTE contidas nas &aacute;rvores de pastas e arquivos s&atilde;o, e continuar&atilde;o sendo, de inteira e exclusiva propriedade do CONTRATANTE o que n&atilde;o confere a DOTEC&nbsp;o direito de uso destas informa&ccedil;&otilde;es.</p>
<p>
	&nbsp;</p>
<p>
	<strong>7. DO SEGURO</strong></p>
<p>
	&nbsp;</p>
<p>
	7.1. A DOTEC concede ao CONTRATANTE um seguro autom&aacute;tico que d&aacute; cobertura de at&eacute; 5 (cinco) vezes o valor cobrado mensalmente para sua guarda, por caixa, em caso de sinistro no dep&oacute;sito ou em caso de grave acidente ocorrido com a(s) caixa(s) durante o translado CONTRATANTE X DOTEC X CONTRATANTE.</p>
<p>
	&nbsp;</p>
<p>
	<strong>8. VIG&Ecirc;NCIA, REAJUSTE E MENOR PRE&Ccedil;O GARANTIDO&nbsp;</strong></p>
<p>
	&nbsp;</p>
<p>
	8.1.&nbsp;O presente contrato entrar&aacute; em vigor na data de seu aceite eletr&ocirc;nico, quando do cadastramento, e ter&aacute; dura&ccedil;&atilde;o de <strong>24 (vinte e quatro)</strong> meses.</p>
<p>
	8.2. Expirando o prazo de dura&ccedil;&atilde;o, o presente instrumento se renovar&aacute; AUTOMATICAMENTE, por igual prazo, caso as partes n&atilde;o manifestem por escrito com anteced&ecirc;ncia m&iacute;nima de 60 (sessenta) dias a inten&ccedil;&atilde;o de n&atilde;o renov&aacute;-lo.</p>
<p>
	8.3.&nbsp;Haver&aacute; reajuste dos valores, a cada 12 (doze) meses de contrato, com base na varia&ccedil;&atilde;o positiva do &iacute;ndice IPC-A acumulado no per&iacute;odo e no caso de extin&ccedil;&atilde;o do referido &iacute;ndice, aplicar-se-&aacute; o IGP-M ou &iacute;ndice de finalidade similar que reflita a desvaloriza&ccedil;&atilde;o da moeda oficial do Brasil.</p>
<p>
	8.4.&nbsp;Se durante a vig&ecirc;ncia deste contrato houver altera&ccedil;&atilde;o dos pre&ccedil;os praticados pela DOTEC para os mesmos servi&ccedil;os e condi&ccedil;&otilde;es comerciais, o CONTRATANTE poder&aacute; escolher entre o pre&ccedil;o garantido neste contrato (ap&oacute;s reajustes previstos no par&aacute;grafo supracitado) ou o novo pre&ccedil;o oferecido publicamente pelo servi&ccedil;o, garantindo assim que o CONTRATANTE pague pelo mesmo servi&ccedil;o o menor pre&ccedil;o.</p>
<p>
	&nbsp;</p>
<p>
	<strong>9. RESCIS&Atilde;O OU CANCELAMENTO DO SERVI&Ccedil;O</strong></p>
<p>
	&nbsp;</p>
<p>
	9.1. O presente contrato poder&aacute; ser rescindido a qualquer tempo, por simples conveni&ecirc;ncia de uma das partes, mediante comunica&ccedil;&atilde;o, por escrito com anteced&ecirc;ncia m&iacute;nima de 60 (sessenta) dias.</p>
<p>
	9.2. Caso o cancelamento unilateral seja feito pelo&nbsp;CONTRATANTE, n&atilde;o haver&aacute;&nbsp;multa contratual, por&eacute;m ainda ser&atilde;o devidas as parcelas em aberto, bem como mensalidades em atraso at&eacute; a data da notifica&ccedil;&atilde;o.</p>
<p>
	9.3. No caso de rescis&atilde;o, a dispensa pelo CONTRATANTE da execu&ccedil;&atilde;o de quaisquer servi&ccedil;os, seja por qual for a raz&atilde;o, durante o prazo do pr&eacute;-aviso, dever&aacute; ser feita por escrito, n&atilde;o desobrigando-o do pagamento do pre&ccedil;o integral at&eacute; o termo final do aviso.</p>
<p>
	9.4.&nbsp;Caso o cancelamento unilateral seja feito pela&nbsp;DOTEC, n&atilde;o incidir&aacute; multa contratual, por&eacute;m as parcelas em aberto e atrasadas dever&atilde;o ser quitadas pelo CONTRATANTE. As parcelas em aberto, e que ainda n&atilde;o tenham vencido, ser&atilde;o canceladas pela DOTEC e o CONTRATANTE estar&aacute; desobrigado de quit&aacute;-las.</p>
<p>
	9.5. &nbsp;O presente contrato tamb&eacute;m poder&aacute; ser rescindido a qualquer tempo independente de aviso, notifica&ccedil;&atilde;o ou interpela&ccedil;&atilde;o judicial ou extrajudicial, nos seguintes casos:</p>
<p style="margin-left:35.4pt;">
	a)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;inexecu&ccedil;&atilde;o, abandono ou paralisa&ccedil;&atilde;o dos servi&ccedil;os por mais de 30 (trinta) dias;</p>
<p style="margin-left:35.4pt;">
	b)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;imper&iacute;cia ou neglig&ecirc;ncia na execu&ccedil;&atilde;o dos servi&ccedil;os;</p>
<p style="margin-left:35.4pt;">
	d)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;o n&atilde;o cumprimento de quaisquer uma das cl&aacute;usulas do contrato;</p>
<p>
	&nbsp;</p>
<p>
	9.6. Em caso de rescis&atilde;o ou encerramento do contrato, por quaisquer raz&otilde;es, o CONTRATANTE dever&aacute; requerer a DOTEC a devolu&ccedil;&atilde;o da(s) caixa(s) at&eacute; a data limite de vig&ecirc;ncia do contrato. &nbsp;Caso o CONTRATANTE n&atilde;o requeira a carga no prazo determinado, a DOTEC dever&aacute; notific&aacute;-lo para que, em um prazo de 15 (quinze) dias, a contar do recebimento do aviso, providencie a retirada da(s) caixa(s), sob pena de ser considerada abandonada e serem adotadas as medidas legais cab&iacute;veis.</p>
<p>
	9.7 Poder&atilde;o ser cobradas taxas adicionais, na hip&oacute;tese de n&atilde;o retirada da(s) caixa(s) nos prazos supra-estabelecidos.</p>
<p>
	&nbsp;</p>
<p>
	<strong>10. RESPONSABILIDADE</strong></p>
<p>
	&nbsp;</p>
<p>
	10.1. A DOTEC responder&aacute; integralmente perante o CONTRATANTE por quaisquer danos causados decorrentes de atos il&iacute;citos praticados por seus empregados e/ou representantes ou por quaisquer descumprimentos das normas aqui contratadas, no limite do montante de 1 (uma) mensalidade paga pelo CONTRATANTE.</p>
<p>
	<strong>11. EXONERA&Ccedil;&Atilde;O DE RESPONSABILIDADE</strong></p>
<p>
	11.1 Apesar da utiliza&ccedil;&atilde;o dos melhores esfor&ccedil;os para efetuar a guarda dos arquivos, a DOTEC n&atilde;o ser&aacute; respons&aacute;vel por eventuais perdas e danos, extravios ou quaisquer outros imprevistos, se estes decorrerem de:</p>
<p style="margin-left:35.4pt;">
	a) Ato de guerra ou conflito armado;</p>
<p style="margin-left:35.4pt;">
	b) Dano el&eacute;trico ou magn&eacute;tico, bem como desaparecimento ou qualquer outro dano semelhante a imagens eletr&ocirc;nicas, fotogr&aacute;ficas ou qualquer outro tipo de grava&ccedil;&atilde;o;</p>
<p style="margin-left:35.4pt;">
	c) Qualquer v&iacute;cio, defeito ou caracter&iacute;sticas inerentes aos arquivos depositados;</p>
<p style="margin-left:35.4pt;">
	d) Quaisquer efeitos decorrentes de caso fortuito ou for&ccedil;a maior que venham a atingir as instala&ccedil;&otilde;es ou ve&iacute;culos da DOTEC.</p>
<p>
	11.2 A DOTEC n&atilde;o se responsabilizar&aacute;, sob qualquer hip&oacute;tese, por indeniza&ccedil;&otilde;es por danos morais, lucros cessantes ou qualquer outra forma de dano ou preju&iacute;zo indireto, incluindo, sem limita&ccedil;&atilde;o, a perda de utilidade, de mercado e outros que possam, por qualquer motivo, vir a ser incorridos pelo CONTRATANTE, independentemente do fato de a DOTEC ter pr&eacute;via ci&ecirc;ncia de que tal preju&iacute;zo poderia vir a ocorrer.</p>
<p>
	&nbsp;</p>
<p>
	<strong>12. DO FORO</strong></p>
<p>
	12.1 Para dirimir quaisquer controv&eacute;rsias referentes ao presente instrumento fica eleito o foro da Comarca da Capital do Rio de Janeiro, com expressa ren&uacute;ncia a qualquer outro, por mais privilegiado que seja, para nele dirimirem toda e qualquer d&uacute;vida ou lit&iacute;gio decorrentes do presente contrato.</p>
<p align="center">
	<br />
	<strong>DOTEC Log&iacute;stica de Guarda e Transporte de Documentos Ltda</strong></p>
	
	
	
	
	</div>
</div>
<!-- ============================== -->
								






<p>&nbsp;</p>
</body>
</html>
