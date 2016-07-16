<%@ include file="/header-admin.jsp" %> 
<%@ include file="/menu-admin.jsp" %>
    <div class="padding">
      <div id="toolbar-box">
        <div class="t">
          <div class="t">
            <div class="t"></div>
          </div>
        </div>
        <div class="m">
          <div class="toolbar-list" id="toolbar">           
            <div class="clr"></div>
          </div>
          <div class="pagetitle icon-48-article">
            <h2>Gerenciador de Boletos</h2>
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
      <div id="submenu-box">
        <div class="t">
          <div class="t">
            <div class="t"></div>
          </div>
        </div>
        <div class="m">
          <ul id="submenu">
            <li> <a class="active" href="<c:url value="/admin/boletos/lista" />" >Boletos</a> </li>           
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
          	
        	<ul id="submenu">
	        	<li> <a class="active" href="<c:url value="#" />" >Filtro:</a> </li>
	
	            <li>
		            <form method="post">	               		
						<select name="pago" id="pago">
							<option value="">Status</option>
							<option value="0" <c:if test="${pago == 0}">selected="selected"</c:if> >Aberto</option>
							<option value="1" <c:if test="${pago == 1}">selected="selected"</c:if> >Pago</option>				
						</select>
						
						&#160;
						<select name="boleto.mesReferencia" id="boleto.mesReferencia">
							<option value="">Mês referência</option>
							<option value="1" <c:if test="${boleto.mesReferencia == 1}">selected="selected"</c:if> >Janeiro</option>
							<option value="2" <c:if test="${boleto.mesReferencia == 2}">selected="selected"</c:if> >Fevereiro</option>				
							<option value="3" <c:if test="${boleto.mesReferencia == 3}">selected="selected"</c:if> >Março</option>
							<option value="4" <c:if test="${boleto.mesReferencia == 4}">selected="selected"</c:if> >Abril</option>
							<option value="5" <c:if test="${boleto.mesReferencia == 5}">selected="selected"</c:if> >Maio</option>
							<option value="6" <c:if test="${boleto.mesReferencia == 6}">selected="selected"</c:if> >Junho</option>
							<option value="7" <c:if test="${boleto.mesReferencia == 7}">selected="selected"</c:if> >Julho</option>
							<option value="8" <c:if test="${boleto.mesReferencia == 8}">selected="selected"</c:if> >Agosto</option>
							<option value="9" <c:if test="${boleto.mesReferencia == 9}">selected="selected"</c:if> >Setembro</option>
							<option value="10" <c:if test="${boleto.mesReferencia == 10}">selected="selected"</c:if> >Outubro</option>
							<option value="11" <c:if test="${boleto.mesReferencia == 11}">selected="selected"</c:if> >Novembro</option>
							<option value="12" <c:if test="${boleto.mesReferencia == 12}">selected="selected"</c:if> >Dezembro</option>
						</select>
						
						&#160;
						
						<select name="boleto.anoReferencia" id="boleto.anoReferencia">
							<option value="">Ano referência</option>
							<option value="2011" <c:if test="${boleto.anoReferencia == 2011}">selected="selected"</c:if>>2011</option>
							<option value="2012" <c:if test="${boleto.anoReferencia == 2012}">selected="selected"</c:if>>2012</option>				
							<option value="2013" <c:if test="${boleto.anoReferencia == 2013}">selected="selected"</c:if>>2013</option>
							<option value="2014" <c:if test="${boleto.anoReferencia == 2014}">selected="selected"</c:if>>2014</option>
							<option value="2015" <c:if test="${boleto.anoReferencia == 2015}">selected="selected"</c:if>>2015</option>
							
						</select>
								
						<input type="submit" value="filtrar"  />
		            </form>
	            </li>
	        	            
	        </ul>
	        	                               			<div class="clr"> </div>
			<dotec:formularioDeBusca></dotec:formularioDeBusca>
			<form action="<c:url value="/admin/clientes/edita" />" method="post" name="adminForm" id="adminForm">
				<table class="adminlist">
              		<thead>
                		<tr>
                			<th width="1%"> &#160;</th>
                			<th width="1%" class="nowrap"> <a href="javascript:void(0);" title="ID">ID</a> </th>
                			<th width="1%" class="nowrap"> <a href="javascript:void(0);" title="ID do Cliente">ID do Cliente</a> </th>
                  			<th ><a href="javascript:void(0)" title="Nome do Cliente">Nome do Cliente</a> </th>
                  			<th ><a href="javascript:void(0)" title="Nosso número">Nosso número</a> </th>                  		
                 			<th ><a href="javascript:void(0);" title="Ano Referência">Ano de Referência</a> </th>
                  			<th><a href="javascript:void(0);" title="Mês Referencia">Mês de Referencia</a> </th>
                  			<th><a href="javascript:void(0);" title="Data Vencimento">Data de Vencimento</a> </th>                                    
                  			<th ><a href="javascript:void(0);" title="Valor">Valor</a> </th>
                  			<th ><a href="javascript:void(0);" title="Status">Status</a> </th>                  			
                  			<th ><a href="javascript:void(0);" title="Boleto">Boleto</a> </th>                  			
                  	   </tr>
              		</thead>
               		<tfoot>
                		<tr>
                  			<td colspan="15">
                  				<div class="container">
	                      			<div class="pagination">
                        				<div class="limit">	</div>
                        				<div class="limit"></div>                       		
                     				</div> 
                    			</div>
                    		</td>
                		</tr>
              		</tfoot> 
              		<tbody>                
               			<c:choose>
							<c:when test="${empty boletoList}">
								<tr class="row0">
									<td colspan="9">Nenhum item encontrado.</td>
								</tr>
							</c:when>							<c:otherwise>
								<c:forEach items="${boletoList}" var="boleto" varStatus="rowCounter">
									<tr class="
	                					<c:choose>
	                						<c:when test="${rowCounter.count % 2 == 0}">row1</c:when>
	                						<c:otherwise>row0</c:otherwise>		
	                					</c:choose>   ">
	                					<td class="center <c:if test="${boleto.pago == false}">aberto</c:if>">&#160;</td>
	                					<td>${boleto.id}</td>	
										<td>${boleto.cliente.id}</td>
										<td>${boleto.cliente.nome}</td>
										<td>${boleto.idProprio}</td>
										<td>${boleto.anoReferencia}</td>
										<td>${boleto.mesReferencia}</td>
										<td><joda:format value="${boleto.datavencimento}" pattern="dd/MM/yyyy"  /></td>								
										<td><fmt:formatNumber value="${boleto.valor}" minFractionDigits="2" type="currency"/></td>									
										<td><c:choose><c:when test="${boleto.pago}">Pago</c:when><c:otherwise>Aberto</c:otherwise></c:choose></td>
										<td>									
											<c:if test="${!boleto.pago}"><a target="blank" href="https://www.moip.com.br/Instrucao.do?token=${boleto.token}">Ver Boleto</a></c:if>
											&#160;
										</td>		
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