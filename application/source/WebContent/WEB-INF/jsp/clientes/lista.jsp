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
            <ul>
              <li class="button" id="toolbar-new"> <a href="<c:url value="/admin/clientes/formulario" />"  class="toolbar"> <span class="icon-32-new"> </span> Novo </a> </li>
              <li class="button" id="toolbar-edit"> <a href="#" onclick="javascript:if ($('#boxchecked').val()==0){alert('Por favor, selecione atrav�s da lista');}else{ $('#adminForm').submit()}" class="toolbar"> <span class="icon-32-edit"> </span> Editar </a> </li>
              <li class="divider"></li>              				
              <li class="button" id="toolbar-trash"> <a href="#" onclick="javascript:if(!confirm('Deseja realmente excluir o cliente?')){return false};if ($('#boxchecked').val()==0){alert('Por favor, selecione atrav�s da lista');}else{$('#adminForm').attr('action','<c:url value="/admin/clientes/remove" />'); $('#adminForm').submit()}" class="toolbar"> <span class="icon-32-trash"> </span> Lixeira </a> </li>             
   
            </ul>
            <div class="clr"></div>
          </div>
          <div class="pagetitle icon-48-article">
            <h2>Gerenciador de Clientes</h2>
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
            <li> <a class="active" href="<c:url value="/admin/clientes/lista" />" >clientes</a> </li>
            
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
            <c:if test="${clienteList != null}">
				<dotec:formularioDeBusca></dotec:formularioDeBusca>
			</c:if>
			
			<div class="clr"> </div>
			  <form action="<c:url value="/admin/clientes/edita" />" method="post" name="adminForm" id="adminForm">
            <table class="adminlist">
              <thead>
                <tr>
                  <th width="1%"> 
                  <input type="checkbox" name="checkall-toggle" value="" onclick="checkAll(this)" /></th>
                  <th><a href="javascript:void(0)" title="T�tulo">Nome</a> </th>                  
                  <th width="10%"><a href="javascript:void(0);" title="CPF">CPF</a> </th>
                  <th width="5%"><a href="javascript:void(0);" title="CNPJ">CNPJ</a> </th>                  				  <th width="10%"><a href="javascript:void(0);" title="Guarda T�cnica">Guarda T�cnica</a> </th>	
                  <th width="5%"><a href="javascript:void(0);" title="Habilitado">Habilitado</a> </th>                  <th width="5%"><a href="javascript:void(0);" title="Data de Vencimento">Dt Venc</a> </th>
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
					<c:when test="${empty clienteList}">
						<tr class="row0">
							<td colspan="8">Nenhum item encontrado.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${clienteList}" var="cliente" varStatus="rowCounter">
							<tr class="
	                			<c:choose>
	                				<c:when test="${rowCounter.count % 2 == 0}">row1</c:when>
	                				<c:otherwise>row0</c:otherwise>
	                			</c:choose>">
								<td class="center">
	                  				<input type="checkbox" id="id" name="id" value="${cliente.id}" onclick="$('#boxchecked').val(1)" title="Checkbox para a linha ${rowCounter.count}" />			                  
	                  			</td>
								<td>${cliente.nome}</td>
								<td><c:if test="${cliente.tipoDePessoa=='PF'}">${cliente.documento}</c:if>&nbsp;</td>
								<td><c:if test="${cliente.tipoDePessoa=='PJ'}">${cliente.documento}</c:if>&nbsp;</td>								<td>									<c:choose>										<c:when test="${cliente.guardaTecnica}">Sim</c:when>										<c:otherwise>N�o</c:otherwise>									</c:choose>								</td>
								<td>									<c:choose>										<c:when test="${cliente.habilitado}">Sim</c:when>										<c:otherwise>N�o 																				<c:if test="${userInfo.user.tipoDeUsuario=='ADMINISTRADOR'}">										<a href="#" onclick="enableEmployers(${cliente.id})" id="enableEmployers" title="clique aqui para habiliatdo o usu�rio"> <img alt="clique aqui para habiliatdo o cliente" src="<c:url value="/client/css/media/system/images/checked_out.png" />" /></a>																				</c:if>										</c:otherwise>									</c:choose>																									</td>							<td>${cliente.diaDeVencimento}</td>
								<td>${cliente.id}</td>
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
      Aten��o! JavaScript deve estar habilitado para o bom funcionamento do back-end do administrador.
      </noscript>
      <div class="clr"></div>
    </div>
    <div class="clr"></div>


<%@ include file="/footer-admin.jsp" %>	
