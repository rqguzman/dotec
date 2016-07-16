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
            <div class="clr"> </div>           
            <c:if test="${boletoList != null}">
				<dotec:formularioDeBusca></dotec:formularioDeBusca>
			</c:if>
			
			<div class="clr"> </div>
			<form action="<c:url value="/admin/clientes/edita" />" method="post" name="adminForm" id="adminForm">
            <table class="adminlist">
              <thead>
                <tr>
                  <th width="1%"> 
                  &#160;</th>
                  <th width="10%"><a href="javascript:void(0)" title="Id Próprio">Id Próprio</a> </th>                  
                  <th ><a href="javascript:void(0);" title="Ano Referência">Ano Referência</a> </th>
                  <th><a href="javascript:void(0);" title="Mês Referencia">Mês Referencia</a> </th>
                  <th><a href="javascript:void(0);" title="Data Vencimento">Data Vencimento</a> </th>                  
                  
                  <th ><a href="javascript:void(0);" title="Valor">Valor</a> </th>
                  <th ><a href="javascript:void(0);" title="Valor">Status</a> </th>
                  <th ><a href="javascript:void(0);" title="Boleto">Boleto</a> </th>
                  <th width="1%" class="nowrap"> <a href="javascript:void(0);" title="ID">ID</a> </th>
                </tr>
              </thead>
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
					<c:when test="${empty boletoList}">
						<tr class="row0">
							<td colspan="4">Nenhum item encontrado.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${boletoList}" var="boleto" varStatus="rowCounter">
							<tr class="
	                			<c:choose>
	                				<c:when test="${rowCounter.count % 2 == 0}">row1</c:when>
	                				<c:otherwise>row0</c:otherwise>
	                			</c:choose>">
								<td class="center">&#160;</td>
								<td>${boleto.idProprio}</td>
								<td>${boleto.anoReferencia}</td>
								<td>${boleto.mesReferencia}</td>
								<td>${boleto.datavencimento}</td>								
								<td>R$ ${boleto.valor}</td>
								<td>								
									<c:choose>
										<c:when test="${boleto.pago == false}">Aberto</c:when>
										<c:otherwise>Fechado</c:otherwise>
									</c:choose>
								</td>																	
								<td><a href="https://desenvolvedor.moip.com.br/sandbox/Instrucao.do?token=${boleto.token}">Ver Boleto</a></td>
								<td>${boleto.id}</td>
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
