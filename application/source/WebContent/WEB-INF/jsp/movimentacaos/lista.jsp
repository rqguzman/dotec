<%@ include file="/header-admin.jsp" %> 
<%@ include file="/menu-admin.jsp" %>
<script type="text/javascript" src="<c:url value="/client/js/movimentacoesLista.js"/>"  ></script>
	 <div class="padding">
      <div id="toolbar-box">
        <div class="t">
          <div class="t">
            <div class="t"></div>
          </div>
        </div>
        <div class="m">
          <div class="toolbar-list" id="toolbar">
            <ul>				<li></li>
            </ul>
            <div class="clr"></div>
          </div>
          <div class="pagetitle icon-48-article">			<h2>Lista de Movimentações</h2>          </div>
          <div class="clr"></div>
        </div>
        <div class="b">
          <div class="b">
            <div class="b"></div>
          </div>
        </div>
      </div>
      <div class="clr"></div>
      <div id="submenu-box">
        <div class="t">
          <div class="t">
            <div class="t"></div>
          </div>
        </div>
        <div class="m">
          <ul id="submenu">
            <li> <a class="active" href="<c:url value="#" />" >Filtro:</a> </li>
            <li>
	            <form method="post">
	               	<select name="movimentacao.dataDeCriacao" id="movimentacao.dataDeCriacao">
	              		<joda:format value="${movimentacao.dataDeCriacao}" pattern="dd/MM/yyyy" var='dataDeCriacao'  />
	              		<joda:format value="${las30Days}" pattern="dd/MM/yyyy" var='las30Days'  />
	              		<joda:format value="${las60Days}" pattern="dd/MM/yyyy" var='las60Days'  />
	              		<joda:format value="${las90Days}" pattern="dd/MM/yyyy" var='las90Days'  />
	              		
	            		<option value="${las30Days}" <c:if test="${dataDeCriacao==las30Days}">selected="selected"</c:if>>Últimos 30 dias</option>
						<option value="${las60Days}" <c:if test="${dataDeCriacao==las60Days}">selected="selected"</c:if>>Últimos 60 dias</option>
						<option value="${las90Days}" <c:if test="${dataDeCriacao==las90Days}">selected="selected"</c:if>>Últimos 90 dias</option>
						<option value="01/01/1900" <c:if test="${dataDeCriacao=='01/01/1900'}">selected="selected"</c:if>>Todas</option>
					</select>
	            	
					<select name="movimentacao.tipoDeMovimentacao" id="movimentacao.tipoDeMovimentacao">
						<option value="">Tipo de Movimentação</option>
						<c:forEach items="${tipoDeMovimentacaoList}" var="tipoDeMovimentacao">
							<option value="${tipoDeMovimentacao}" <c:if test="${movimentacao.tipoDeMovimentacao==tipoDeMovimentacao}">selected="selected"</c:if> >${tipoDeMovimentacao.nome}</option>
						</c:forEach>
					</select>
					<select name="movimentacao.statusDaMovimentacao" id="movimentacao.statusDaMovimentacao">
						<option value="">Status da movimentação</option>
						<c:forEach items="${statusDaMovimentacaoList}" var="statusDaMovimentacao">
							<option value="${statusDaMovimentacao}" <c:if test="${movimentacao.statusDaMovimentacao==statusDaMovimentacao}">selected="selected"</c:if> >${statusDaMovimentacao.nome}</option>
						</c:forEach>
					</select>
					<input type="submit" value="filtrar"  />
	            </form>
            </li>
            
          </ul>
          <div class="clr"></div>
        </div>
        <div class="b">
          <div class="b">
            <div class="b"></div>
          </div>
        </div>
      </div>
      <div id="element-box">
        <div class="t">
          <div class="t">
            <div class="t"></div>
          </div>
        </div>
        <div class="m">                           
            <div class="clr"> </div>           
            <c:if test="${movimentacaoList != null}">
				<dotec:formularioDeBusca></dotec:formularioDeBusca>
			</c:if>
			
			<div class="clr"> </div>
			  
            <table class="adminlist">
              <thead>                      <tr>		                  <th width="1%">		
		                  <input type="checkbox" name="checkall-toggle" value="" onclick="checkAll(this)" /></th>		
		                  <th width="5%" class="nowrap"> <a href="javascript:void(0);" title="ID">ID</a></th>		
		                  <th width="10%"><a href="javascript:void(0)" title="Tipo de Movimentação">Tipo De Movimentação</a> </th> 		                  		                  <c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">                 									  <th width="10%"><a href="javascript:void(0);" title="ID do Cliente">ID do Cliente</a> </th>				
			                  <th width="10%"><a href="javascript:void(0);" title="Nome do Cliente">Nome do Cliente</a> </th>		                  		                   </c:if> 		                  			              <th width="10%"><a href="javascript:void(0);" title="ID da Caixa">ID da Caixa</a> </th>							              <th width="10%"><a href="javascript:void(0);" title="Nome da caixa">Nome da caixa</a> </th>		                  		                  <th width="10%"><a href="javascript:void(0);" title="CriadaPor por">CriadaPor por</a> </th>		
		                  <th width="10%"><a href="javascript:void(0);" title="Data de Criação">Data de Criação</a> </th>		                  		                  <th width="20%"><a href="javascript:void(0);" title="Status da Movimentação">Status da Movimentação</a> </th>		
		                </tr>		       </thead>
               <tfoot>
                <tr>
                  <td colspan="15"><div class="container">
                      <div class="pagination">
                        <div class="limit">
                        </div>
                        <div class="limit"></div>                       
                      </div> 
                    </div></td>
                </tr>
              </tfoot> 
              <tbody>                
               <c:choose>
					<c:when test="${empty movimentacaoList}">
						<tr class="row0">
							<td colspan="10">Nenhum item encontrado.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${movimentacaoList}" var="movimentacao" varStatus="rowCounter">
							<tr class="
	                			<c:choose>
	                				<c:when test="${rowCounter.count % 2 == 0}">row1</c:when>
	                				<c:otherwise>row0</c:otherwise>
	                			</c:choose>">
								<td class="center">
	                  				<input type="checkbox" name="id" value="${movimentacao.id}" onclick="$('#boxchecked').val(1)" title="Checkbox para a linha ${rowCounter.count}" />			                  
	                  			</td>
	                  			<td>${movimentacao.id}</td>
	                  			<td>${movimentacao.tipoDeMovimentacao.nome}</td>	                  				                  			<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">																	<td>${clientes.get(rowCounter.index).id}</td>											
		                  			<td>${clientes.get(rowCounter.index).nome}</td>		                  				                  			</c:if>	 			                  			        					                  		<td>${caixas.get(rowCounter.index).id}</td>													                  		<td>${caixas.get(rowCounter.index).nome}</td>	                  								
								<td>${movimentacao.criadaPor.nome}</td>	
								<td>
									<joda:format value="${movimentacao.dataDeCriacao}" pattern="dd/MM/yyyy"  />
								</td>																<c:choose>	                  				<c:when test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">	                  				<td>	                  					<form  class="formUpdateStatus">		                  					<input type="hidden" name="movimentacao.id" value="${movimentacao.id}" />			                  				<select name="movimentacao.statusDaMovimentacao">												<c:forEach items="${statusDaMovimentacaoList}" var="statusDaMovimentacao">													<option value="${statusDaMovimentacao}" <c:if test="${movimentacao.statusDaMovimentacao==statusDaMovimentacao}">selected="selected"</c:if> >${statusDaMovimentacao.nome}</option>												</c:forEach>											</select>											<input type="submit" value="Alterar"  />										</form>		                  			</td>		                  			</c:when>	                  				<c:otherwise>	                  					<td>${movimentacao.statusDaMovimentacao.nome}</td>	                  				</c:otherwise>	                  			</c:choose>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
               
                              
              </tbody>
            </table>
            <div>
              <input type="hidden" name="task" value="" />
              <input type="hidden" name="boxchecked" id="boxchecked" value="0" />
              
            </div>
          
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
