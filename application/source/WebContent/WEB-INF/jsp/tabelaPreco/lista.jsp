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
               <!-- <li class="button" id="toolbar-new"> <a href="<c:url value="/admin/tabelapreco/formulario" />"  class="toolbar"> <span class="icon-32-new"> </span> Novo </a> </li> -->
              <li class="button" id="toolbar-edit"> <a href="#" onclick="javascript:if ($('#boxchecked').val()==0){alert('Por favor, selecione através da lista');}else{ $('#adminForm').submit()}" class="toolbar"> <span class="icon-32-edit"> </span> Editar </a> </li>
        <!--       <li class="divider"></li>              
              <li class="button" id="toolbar-trash"> <a href="#" onclick="javascript:if ($('#boxchecked').val()==0){alert('Por favor, selecione através da lista');}else{$('#adminForm').attr('action','<c:url value="/admin/tabelapreco/remove" />'); $('#adminForm').submit()}" class="toolbar"> <span class="icon-32-trash"> </span> Lixeira </a> </li>              -->
           
            </ul>
            <div class="clr"></div>
          </div>
          <div class="pagetitle icon-48-article">
            <h2>Gerenciador de Preços</h2>
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
         <form method="post">
        <div class="m">
          <ul id="submenu">
            <li> <a class="active" href="<c:url value="#" />" >Filtro:</a>  </li>
           
            	<input type="text" name="tabelaPreco.title" id="tabelaPreco.title" value="[Digite o título]"/>            	
            	<input type="submit" value="filtrar"  />                                        
          </ul>
          <div class="clr"></div>
        </div>
         </form>
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
          
			<div class="clr"> </div>
			  <form action="<c:url value="/admin/tabelapreco/edita" />" method="post" name="adminForm" id="adminForm">			  			
            <table class="adminlist">
              <thead>
                <tr>
                  <th width="1%"><input type="checkbox" name="checkall-toggle" value="" onclick="checkAll(this)" /></th>
                  <th><a href="javascript:void(0)" title="Título">Titulo</a> </th>                     <th><a href="javascript:void(0)" title="Categoria">Categoria</a> </th>                
                  <th width="10%"><a href="javascript:void(0);" title="Preço">Preço</a> </th>                  
                  <th width="5%"><a href="javascript:void(0);" title="Ordenação">Ordenação</a> </th>
                  <th width="1%" class="nowrap"> <a href="javascript:void(0);" title="ID">ID</a> </th>
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <td colspan="15"><div class="container"><div class="pagination"><div class="limit"></div><div class="limit"></div></div></div></td>
                </tr>
              </tfoot> 
              <tbody>                
               <c:choose>
					<c:when test="${empty tabelaPrecoList}">
						<tr class="row0">
							<td colspan="5">Nenhum item encontrado.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${tabelaPrecoList}" var="tabelaPreco" varStatus="rowCounter">
							<tr class="
	                			<c:choose>
	                				<c:when test="${rowCounter.count % 2 == 0}">row1</c:when>
	                				<c:otherwise>row0</c:otherwise>
	                			</c:choose>">
								<td class="center">
	                  				<input type="checkbox" id="id" name="id" value="${tabelaPreco.id}" onclick="$('#boxchecked').val(1)" title="Checkbox para a linha ${rowCounter.count}" />			                  
	                  			</td>																							<td>${tabelaPreco.title}</td>									<td>${tabelaPreco.category}</td>								<td>${tabelaPreco.price}</td>								
								<td>${tabelaPreco.ordering}</td>						
								<td>${tabelaPreco.id}</td>
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
