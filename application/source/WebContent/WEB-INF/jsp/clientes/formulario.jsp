<%@ include file="/header-admin.jsp" %>
<%@ include file="/menu-admin.jsp" %>
 	<script type="text/javascript" src="<c:url value="/client/js/clientesFormulario.js"/>"  ></script>
 	<style type="text/css">
		@import url("<c:url value="/client/css/clientesFormulario.css"/>");
	</style>
	
    		<div class="padding">
      			<div id="toolbar-box">
        			<div class="t">
          				<div class="t">
            				<div class="t"></div>
          				</div>
        			</div>
        			<div class="m">        				        				<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">
	          				<div class="toolbar-list" id="toolbar">	
	            				<ul>              	
	              					<li class="button" id="toolbar-save"> <a href="javascript:$('#formulario2').submit()" onClick="" class="toolbar"> <span class="icon-32-save"> </span> Salvar &amp; Fechar </a> </li>              	
	              					<li class="button" id="toolbar-cancel"> <a href="<c:url value="/admin/clientes/lista" />" class="toolbar"> <span class="icon-32-cancel"> </span> Cancelar </a> </li>	
	              					<li class="divider"> </li>	
	            				</ul>	
	            				<div class="clr"></div>	
	          				</div>          				</c:if>
          				<div class="pagetitle icon-48-category-add icon-48-weblinks-category-add">          				          										<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">
	            			<h2>Gerenciador de Clientes: <c:choose>	
							<c:when test='${clientePessoaFisica.id==null and clientePessoaJuridica.id==null}'>Adicionar</c:when>	
							<c:otherwise>Editar</c:otherwise>	
							</c:choose> Cliente</h2>						</c:if>												<c:if test="${userInfo.user.tipoDeUsuario == 'PRIMARIO' || userInfo.user.tipoDeUsuario == 'SECUNDARIO'}">							<h2>Minhas Informações cadastrais</h2>						</c:if>
          			</div>
          			<div class="clr"></div>
        		</div>
        		<div class="b">
          			<div class="b">
            			<div class="b"></div>
          			</div>
        		</div>
      		</div>
      		<div class="clr"></div>
      		<div id="element-box">
        	<div class="t">
          		<div class="t">
            		<div class="t"></div>
          		</div>
        	</div>
        	<div class="m">                               				<dotec:formularioDeLoginModal formId="formulario2"></dotec:formularioDeLoginModal>
         		<form  id="formulario2" name="formulario2" class="cmxform"
					
					<c:choose>
						<c:when test='${clientePessoaFisica.id==null and clientePessoaJuridica.id==null}'>action="<c:url value="/admin/~#clientes#~/adiciona"/>"</c:when>
						<c:otherwise>action="<c:url value="/admin/~#clientes#~/altera"/>"</c:otherwise>
					</c:choose> method="post" > 
					<input type="hidden" name="clientePessoaFisica.id"  value="${clientePessoaFisica.id}" />
					<input type="hidden" name="clientePessoaJuridica.id"  value="${clientePessoaJuridica.id}" />			
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
					</c:choose>										<c:choose>						<c:when test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">							<c:set var="readOnly" value="false" ></c:set>						</c:when>						<c:otherwise>							<c:set var="readOnly" value="true" ></c:set>						</c:otherwise>						</c:choose>								          
            		<div class="width-60 fltlft">
              			<fieldset class="adminform">
                			<legend>Detalhes</legend>
                			<ul class="adminformlist" id="ulTipoDeCliente">
                  				<li>
                  					<dotec:campoRadio  classe="radioTipoDeCliente" name="tipoDeCliente" label="Pessoa Física" id="radioPessoaFisica"  value="pf" checked="${checkPessoaFisica}" readOnly="${readOnlyTipoDePessoa}"></dotec:campoRadio>
									<dotec:campoRadio classe="radioTipoDeCliente" name="tipoDeCliente" label="Pessoa Jurídica" id="radioPessoaJuridica" value="pj" checked="${checkPessoaJuridica}" readOnly="${readOnlyTipoDePessoa}"></dotec:campoRadio>                  
                  				</li>                  
                  			</ul>
                  			
                 			<div class="divPessoa divPessoaFisica">					
                 				<input type="hidden" id="clientePessoaFisica.habilitado" name="clientePessoaFisica.habilitado" value="1" />
                 				<input type="hidden" id="clientePessoaFisica.email" name="clientePessoaFisica.email" value="${clientePessoaFisica.email}" />                 				                 	
                 				<ul>
									<li>
										<dotec:campoTexto label="Nome:" classe="textonovo" id="clientePessoaFisica.nome" value="${clientePessoaFisica.nome}" readOnly="${readOnly}"></dotec:campoTexto>
									</li>
									<li>
										<dotec:campoCPF label="CPF:" id="clientePessoaFisica.cpf"  value="${clientePessoaFisica.cpf}" readOnly="${readOnly}"></dotec:campoCPF>
									</li>
									<li>
										<dotec:campoRG label="RG:" id="clientePessoaFisica.rg"  value="${clientePessoaFisica.rg}" readOnly="${readOnly}"></dotec:campoRG>
									</li>								
									<li>
										<dotec:campoTelefone label="Telefone:" idTelefone="clientePessoaFisica.telefone.numero" idDDD="clientePessoaFisica.telefone.ddd" valueTelefone="${clientePessoaFisica.telefone.numero}" valueDDD="${clientePessoaFisica.telefone.ddd}" readOnly="${readOnly}"></dotec:campoTelefone>
									</li>
									<li>
										<joda:format value="${clientePessoaFisica.dataDeNascimento}" pattern="dd/MM/yyyy" var="dataDeNascimento"  />
										<dotec:campoData id="clientePessoaFisica.dataDeNascimento"  label="Data de Nascimento:" value="${dataDeNascimento}" readOnly="${readOnly}"></dotec:campoData>
									</li>
									<li>
										<label for="clientePessoaFisica.sexo">Sexo:<span class="star">&#160;*</span></label>
										<select name="clientePessoaFisica.sexo" id="clientePessoaFisica.sexo"										<c:if test="${readOnly}">											disabled="disabled"											</c:if>										>
											<option value=""></option>
											<c:forEach items="${sexoList}" var="sexo">
												<option value="${sexo}" <c:if test="${clientePessoaFisica.sexo==sexo}">selected="selected"</c:if> >${sexo.nome}</option>
											</c:forEach>
										</select>
									</li>								</ul>									
							</div>
							<div class="divPessoa divPessoaJuridica">
								<input type="hidden" id="clientePessoaJuridica.habilitado" id="clientePessoaJuridica.habilitado" value="1" />
								<ul>
									<li>
										<dotec:campoTexto label="Nome:" classe="textonovo" id="clientePessoaJuridica.nome" value="${clientePessoaJuridica.nome}" readOnly="${readOnly}" ></dotec:campoTexto>
									</li>
									<li>
										<dotec:campoCNPJ label="CNPJ:" id="clientePessoaJuridica.cnpj"  value="${clientePessoaJuridica.cnpj}" readOnly="${readOnly}" ></dotec:campoCNPJ>
									</li>
									
									<li>
										<dotec:campoTelefone label="Telefone:" idTelefone="clientePessoaJuridica.telefone.numero" idDDD="clientePessoaJuridica.telefone.ddd" valueTelefone="${clientePessoaJuridica.telefone.numero}" valueDDD="${clientePessoaJuridica.telefone.ddd}" readOnly="${readOnly}" ></dotec:campoTelefone>
									</li>
								</ul>
							</div>
                        </fieldset>                                                <!-- GUARDA TÉCNICA -->												<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">	                        <fieldset class="adminform">		                         	<legend>Guarda Técnica:</legend>			                         <div class="divPessoa divPessoaFisica">				                         <ul>       					                         <li>																					<dotec:campoRadio classe="radioGuardaTecnica" name="clientePessoaFisica.guardaTecnica" label="Não" id="radioSemGuardaTecnica" value="false" checked="${clientePessoaFisica.guardaTecnica==false}"></dotec:campoRadio>		                  						<dotec:campoRadio  classe="radioGuardaTecnica" name="clientePessoaFisica.guardaTecnica" label="Sim" id="radioComGuardaTecnica"  value="true" checked="${clientePessoaFisica.guardaTecnica}" ></dotec:campoRadio>	              	                  					</li>										</ul>									</div>									<div class="divPessoa divPessoaJuridica">			                        <ul>       				                        <li>																				<dotec:campoRadio classe="radioGuardaTecnica" name="clientePessoaJuridica.guardaTecnica" label="Não" id="radioSemGuardaTecnica" value="false" checked="${clientePessoaJuridica.guardaTecnica==false}"></dotec:campoRadio>		                  					<dotec:campoRadio  classe="radioGuardaTecnica" name="clientePessoaJuridica.guardaTecnica" label="Sim" id="radioComGuardaTecnica"  value="true" checked="${clientePessoaJuridica.guardaTecnica}" ></dotec:campoRadio>	              	                  				</li>								</ul>									</div>								</fieldset>						</c:if>                                                                        <c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">	                        <fieldset class="adminform">		                         	<legend>Inativar:</legend>			                         <div class="divPessoa divPessoaFisica">				                         <ul>       					                         <li>																					<dotec:campoRadio classe="radioInativo" name="clientePessoaFisica.inativo" label="Não" id="radioAtivo" value="false" checked="${clientePessoaFisica.inativo==false}"></dotec:campoRadio>		                  						<dotec:campoRadio  classe="radioInativo" name="clientePessoaFisica.inativo" label="Sim" id="radioInativo"  value="true" checked="${clientePessoaFisica.inativo}" ></dotec:campoRadio>	              	                  					</li>										</ul>									</div>									<div class="divPessoa divPessoaJuridica">			                        <ul>       				                        <li>																					<dotec:campoRadio classe="radioInativo" name="clientePessoaJuridica.inativo" label="Não" id="radioAtivo" value="false" checked="${clientePessoaJuridica.inativo==false}"></dotec:campoRadio>		                  						<dotec:campoRadio  classe="radioInativo" name="clientePessoaJuridica.inativo" label="Sim" id="radioInativo"  value="true" checked="${clientePessoaJuridica.inativo}" ></dotec:campoRadio>	              	                  					</li>									</ul>									</div>								</fieldset>						</c:if>																			<!-- HABILITAR -->												<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">	                        <fieldset class="adminform">		                         	<legend>Habilitado:</legend>			                         <div class="divPessoa divPessoaFisica">				                         <ul>       					                         <li>																					<dotec:campoRadio classe="radioInativo" name="clientePessoaFisica.habilitado" label="Não" id="radioHabilitado" value="false" checked="${clientePessoaFisica.habilitado==false}"></dotec:campoRadio>		                  						<dotec:campoRadio  classe="radioInativo" name="clientePessoaFisica.habilitado" label="Sim" id="radioHabilitado"  value="true" checked="${clientePessoaFisica.habilitado}" ></dotec:campoRadio>	              	                  					</li>										</ul>									</div>									<div class="divPessoa divPessoaJuridica">			                        <ul>       				                        <li>																				<dotec:campoRadio classe="radioInativo" name="clientePessoaJuridica.habilitado" label="Não" id="radioHabilitado" value="false" checked="${clientePessoaJuridica.habilitado==false}"></dotec:campoRadio>		                  					<dotec:campoRadio  classe="radioInativo" name="clientePessoaJuridica.habilitado" label="Sim" id="radioHabilitado"  value="true" checked="${clientePessoaJuridica.habilitado}" ></dotec:campoRadio>	              	                  				</li>								</ul>									</div>								</fieldset>						</c:if>	                        
                        <fieldset class="adminform">
                         	<legend>Dia de cobrança:</legend>
	                         <div class="divPessoa divPessoaFisica">
		                         <ul>       
			                         <li>
										<label for="clientePessoaFisica.diaDeVencimento">Data Vencimento:<span class="star">*</span></label>
										<select name="clientePessoaFisica.diaDeVencimento" id="clientePessoaFisica.diaDeVencimento"										<c:if test="${readOnly}">											disabled="disabled"											</c:if>																				>
											<option value=""></option>										 
											<option value="10" <c:if test="${clientePessoaFisica.diaDeVencimento==10}">selected="selected"</c:if>>10</option>
											<option value="15" <c:if test="${clientePessoaFisica.diaDeVencimento==15}">selected="selected"</c:if>>15</option>
											<option value="20" <c:if test="${clientePessoaFisica.diaDeVencimento==20}">selected="selected"</c:if>>20</option>
										</select>
									</li>
								</ul>
							</div>
							<div class="divPessoa divPessoaJuridica">
	                        <ul>       
		                         <li>
									<label for="clientePessoaJuridica.diaDeVencimento">Data Vencimento:<span class="star">*</span></label>
									<select name="clientePessoaJuridica.diaDeVencimento" id="clientePessoaJuridica.diaDeVencimento">
										<option value=""></option>										 
										<option value="10" <c:if test="${clientePessoaJuridica.diaDeVencimento==10}">selected="selected"</c:if>>10</option>
										<option value="15" <c:if test="${clientePessoaJuridica.diaDeVencimento==15}">selected="selected"</c:if>>15</option>
										<option value="20" <c:if test="${clientePessoaJuridica.diaDeVencimento==20}">selected="selected"</c:if>>15</option>
									</select>
								</li>
							</ul>
							</div>
						</fieldset>
                        <fieldset class="adminform">
                          	<legend>Endereços <input type="button" id="btnIncluiEndereco" value="+" /></legend>
                          	<div id="divEnderecos">
	                        	<c:forEach items="${clientePessoaFisica.enderecos}" var="endereco" varStatus="s">
	                        		<div class="divEndereco">
	                        			<div class="divPessoa divPessoaFisica">
											<input type="hidden" name="clientePessoaFisica.enderecos[${s.index}].id" value="${endereco.id}" />
										    <ul>
										    	<li>
										    		<input type="button" class="btnRemoveEndereco" value="-" />
										    	</li>
										    	<li class="liTipoDeEndereco">
										    		<div>
											        	<dotec:campoCheckBox  id="clientePessoaFisica.enderecos[${s.index}].enderecoDeCobranca" label="Endereço de cobrança:" value="${endereco.enderecoDeCobranca}" classe="checkboxTipoDeEndereco  checkboxEnderecoDeCobranca"></dotec:campoCheckBox>
											        </div>
											        <div>
											        	<dotec:campoCheckBox  id="clientePessoaFisica.enderecos[${s.index}].enderecoDeEntrega" label="Endereço de entrega:" value="${endereco.enderecoDeEntrega}" classe="checkboxTipoDeEndereco checkboxEnderecoDeEntrega"></dotec:campoCheckBox>
											        </div>
										        </li>
										        <li>
										            <label for="clientePessoaFisica.enderecos[${s.index}].tipoDeLogradouro">Tipo de Logradouro:<span class="star">*</span></label>
										            <select name="clientePessoaFisica.enderecos[${s.index}].tipoDeLogradouro" id="clientePessoaFisica.enderecos[${s.index}].tipoDeLogradouro">
										                <option value=""></option>
										                <c:forEach items="${tipoDeLogradouroList}" var="tipoDeLogradouro">
										                    <option value="${tipoDeLogradouro}" <c:if test="${endereco.tipoDeLogradouro==tipoDeLogradouro}">selected="selected"</c:if> >${tipoDeLogradouro.nome}</option>
										                </c:forEach>
										            </select>
										        </li>
										        <li>
										            <dotec:campoTexto label="Logradouro:" id="clientePessoaFisica.enderecos[${s.index}].nomeDoLogrado" value="${endereco.nomeDoLogrado}"></dotec:campoTexto>
										        </li>
										        <li>
										            <dotec:campoNumeroInteiro label="Número:" id="clientePessoaFisica.enderecos[${s.index}].numero" value="${endereco.numero}"></dotec:campoNumeroInteiro>
										        </li>
										        <li>
										            <dotec:campoTexto required="false" label="Complemento:" id="clientePessoaFisica.enderecos[${s.index}].complemento" value="${endereco.complemento}"></dotec:campoTexto>
										        </li>										        										        <li>										            <dotec:campoTextArea required="false" label="Referência:" id="clientePessoaFisica.enderecos[${s.index}].referencia" value="${endereco.referencia}"></dotec:campoTextArea>										        </li>										        
										        <li>
										            <dotec:campoTexto label="Bairro:" id="clientePessoaFisica.enderecos[${s.index}].bairro" value="${endereco.bairro}"></dotec:campoTexto>
										        </li>
										        <li>
										            <dotec:campoCEP label="CEP:"  id="clientePessoaFisica.enderecos[${s.index}].cep" name="clientePessoaFisica.enderecos[${s.index}].cep" value="${endereco.cep}"></dotec:campoCEP>
										        </li>
										        <li>
										        <dotec:campoTexto label="Município:" id="clientePessoaFisica.enderecos[${s.index}].municipio" value="${endereco.municipio}"></dotec:campoTexto>
										        </li>
										        <li>
										        	<label for="clientePessoaFisica.enderecos[${s.index}].uf" class="hasTip required">UF: <span class="star">&nbsp;*</span></label>
											        <select name="clientePessoaFisica.enderecos[${s.index}].uf" id="clientePessoaFisica.enderecos[${s.index}].uf">
											            <option value=""></option>
											            <c:forEach items="${ufs}" var="uf">  
											                <option value="${uf}" <c:if test="${endereco.uf==uf}">selected="selected"</c:if>>${uf}</option>
											            </c:forEach>
											        </select>
										        </li>
										    </ul>
										</div>                        		
	                        		</div>  
	                        	</c:forEach>
	                        	<c:forEach items="${clientePessoaJuridica.enderecos}" var="endereco" varStatus="s">
	                        		<div class="divEndereco">
	                        			<div class="divPessoa divPessoaJuridica">
											<input type="hidden" name="clientePessoaJuridica.enderecos[${s.index}].id" value="${endereco.id}" />
										    <ul>
										    	<li>
										    		<input type="button" class="btnRemoveEndereco" value="-" />
										    	</li>
										    	<li class="liTipoDeEndereco">
										    		<div>
											        	<dotec:campoCheckBox  id="clientePessoaJuridica.enderecos[${s.index}].enderecoDeCobranca" label="Endereço de cobrança:" value="${endereco.enderecoDeCobranca}" classe="checkboxTipoDeEndereco  checkboxEnderecoDeCobranca"></dotec:campoCheckBox>
											        </div>
											        <div>
											        	<dotec:campoCheckBox  id="clientePessoaJuridica.enderecos[${s.index}].enderecoDeEntrega" label="Endereço de entrega:" value="${endereco.enderecoDeEntrega}" classe="checkboxTipoDeEndereco checkboxEnderecoDeEntrega"></dotec:campoCheckBox>
											        </div>
										        </li>
										        <li>
										            <label for="clientePessoaJuridica.enderecos[${s.index}].tipoDeLogradouro">Tipo de Logradouro:<span class="star">*</span></label>
										            <select name="clientePessoaJuridica.enderecos[${s.index}].tipoDeLogradouro" id="clientePessoaJuridica.enderecos[${s.index}].tipoDeLogradouro">
										                <option value=""></option>
										                <c:forEach items="${tipoDeLogradouroList}" var="tipoDeLogradouro">
										                    <option value="${tipoDeLogradouro}" <c:if test="${endereco.tipoDeLogradouro==tipoDeLogradouro}">selected="selected"</c:if> >${tipoDeLogradouro.nome}</option>
										                </c:forEach>
										            </select>
										        </li>
										        <li>
										            <dotec:campoTexto label="Logradouro:" id="clientePessoaJuridica.enderecos[${s.index}].nomeDoLogrado" value="${endereco.nomeDoLogrado}"></dotec:campoTexto>
										        </li>
										        <li>
										            <dotec:campoNumeroInteiro label="NÃºmero:" id="clientePessoaJuridica.enderecos[${s.index}].numero" value="${endereco.numero}"></dotec:campoNumeroInteiro>
										        </li>
										        <li>
										            <dotec:campoTexto required="false" label="Complemento:" id="clientePessoaJuridica.enderecos[${s.index}].complemento" value="${endereco.complemento}"></dotec:campoTexto>
										        </li>																								<li>										            <dotec:campoTextArea required="false" label="ReferÃªncia:" id="clientePessoaFisica.enderecos[${s.index}].referencia" value="${endereco.referencia}"></dotec:campoTextArea>										        </li>																																				
										        <li>
										            <dotec:campoTexto label="Bairro:" id="clientePessoaJuridica.enderecos[${s.index}].bairro" value="${endereco.bairro}"></dotec:campoTexto>
										        </li>
										        <li>
										            <dotec:campoCEP label="CEP:"  id="clientePessoaJuridica.enderecos[${s.index}].cep" name="clientePessoaJuridica.enderecos[${s.index}].cep" value="${endereco.cep}"></dotec:campoCEP>
										        </li>
										        <li>
										        <dotec:campoTexto label="MunicÃ­pio:" id="clientePessoaJuridica.enderecos[${s.index}].municipio" value="${endereco.municipio}"></dotec:campoTexto>
										        </li>
										        <li>
										        	<label for="clientePessoaJuridica.enderecos[${s.index}].uf" class="hasTip required">UF: <span class="star">&nbsp;*</span></label>
											        <select name="clientePessoaJuridica.enderecos[${s.index}].uf" id="clientePessoaJuridica.enderecos[${s.index}].uf">
											            <option value=""></option>
											            <c:forEach items="${ufs}" var="uf">  
											                <option value="${uf}" <c:if test="${endereco.uf==uf}">selected="selected"</c:if>>${uf}</option>
											            </c:forEach>
											        </select>
										        </li>
										    </ul>
										</div>                        		
	                        		</div>  
	                        	</c:forEach>
	                        </div>
                         </fieldset>     

                         <fieldset class="adminform">
                         <legend>Usuário:</legend>
							<div class="divPessoa divPessoaFisica">
								<ul>
									<li>								
										<input type="hidden" name="clientePessoaFisica.usuarios[0].id" value="${clientePessoaFisica.usuarios[0].id}" />
										<dotec:campoEmail label="Login:" id="clientePessoaFisica.usuarios[0].email" value="${clientePessoaFisica.usuarios[0].email}" readOnly="${readOnly}"></dotec:campoEmail>
									</li>
									<li>
										<dotec:campoSenha label="Senha:" id="clientePessoaFisica.usuarios[0].senha" name="clientePessoaFisica.usuarios[0].senha"></dotec:campoSenha>
									</li>								
								</ul>							
							</div>
								
							<div class="divPessoa divPessoaJuridica">
								<input type="hidden" id="clientePessoaJuridica.email" name="clientePessoaJuridica.email" value="${clientePessoaJuridica.email}" />
								<ul>
									<li>
										<input type="hidden" name="clientePessoaJuridica.usuarios[0].id" value="${clientePessoaJuridica.usuarios[0].id}" />
										<dotec:campoTexto label="Nome:" id="clientePessoaJuridica.usuarios[0].nome" value="${clientePessoaJuridica.usuarios[0].nome}"></dotec:campoTexto>
									</li>
									<li>
										<dotec:campoCPF label="CPF:" id="clientePessoaJuridica.usuarios[0].cpf" name="clientePessoaJuridica.usuarios[0].cpf" value="${clientePessoaJuridica.usuarios[0].cpf}"></dotec:campoCPF>
									</li>
									<li>
										<dotec:campoRG label="RG:" id="clientePessoaJuridica.usuarios[0].rg" value="${clientePessoaJuridica.usuarios[0].rg}"></dotec:campoRG>
									</li>
									<li>
										<joda:format value="${clientePessoaJuridica.usuarios[0].dataDeNascimento}" pattern="dd/MM/yyyy" var="dataDeNascimento"  />
										<dotec:campoData id="clientePessoaJuridica.usuarios[0].dataDeNascimento"  label="Data de Nascimento:" value="${dataDeNascimento}"></dotec:campoData>
									</li>
									<li>
										<label for="clientePessoaJuridica.usuarios[0].sexo">Sexo:<span class="fieldRequired">*</span></label>
										<select name="clientePessoaJuridica.usuarios[0].sexo" id="clientePessoaJuridica.usuarios[0].sexo">
											<option value=""></option>
											<c:forEach items="${sexoList}" var="sexo">
												<option value="${sexo}" <c:if test="${clientePessoaJuridica.usuarios[0].sexo==sexo}">selected="selected"</c:if> >${sexo.nome}</option>
											</c:forEach>
										</select>
									</li>
									<li>
										<dotec:campoEmail label="E-mail:" id="clientePessoaJuridica.usuarios[0].email" value="${clientePessoaJuridica.usuarios[0].email}"></dotec:campoEmail>
									</li>
									
									<li>
										<dotec:campoSenha label="Senha:" id="clientePessoaJuridica.usuarios[0].senha" name="clientePessoaJuridica.usuarios[0].senha" value="${clientePessoaJuridica.usuarios[0].senha}"></dotec:campoSenha>
									</li>
								</ul>
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
			 			<dotec:campoObrigatorio></dotec:campoObrigatorio>
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
        <div class="clr"></div>
   	</div>
	<div class="b">
	  <div class="b">
	    <div class="b"></div>
	  </div>
    </div>
   </div>
      <noscript>
      Atenção! JavaScript deve estar habilitado para o bom funcionamento do back-end do administrador.
      </noscript>
      <div class="clr"></div>
    </div>
    <div class="clr"></div>


<%@ include file="/footer-admin.jsp" %>