
<%@ include file="/header-admin.jsp" %>
 <%@ include file="/menu-admin.jsp" %>
<div id="content-box">
  <div class="border">
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
              <li class="button" id="toolbar-new"> <a href="#"  onclick="document.adminForm.action='<c:url value="/documentos/formulario" />';Joomla.submitbutton('documentos.add')" class="toolbar"> <span class="icon-32-new"> </span> Novo </a> </li>
              <li class="button" id="toolbar-edit"> <a href="#" onclick="javascript:if (document.adminForm.boxchecked.value==0){alert('Por favor, selecione através da lista');}else{ document.adminForm.action='<c:url value="/documentos/edita" />';Joomla.submitbutton('documentos.edit')}" class="toolbar"> <span class="icon-32-edit"> </span> Editar </a> </li>
              <li class="divider"></li>              
              <li class="button" id="toolbar-trash"> <a href="#" onclick="javascript:if (document.adminForm.boxchecked.value==0){alert('Por favor, selecione através da lista');}else{document.adminForm.action='<c:url value="/documentos/remove" />'; Joomla.submitbutton('caixa.trash')}" class="toolbar"> <span class="icon-32-trash"> </span> Lixeira </a> </li>             
              <li class="divider"> </li>
              <li class="button" id="toolbar-help"> <a href="#" onclick="popupWindow('http://help.joomla.org/proxy/index.php?option=com_help&amp;keyref=Help16:Conteúdo_Artigos_Gerenciar', 'Ajuda', 700, 500, 1)" rel="help" class="toolbar"> <span class="icon-32-help"> </span> Ajuda </a> </li>
            </ul>
            <div class="clr"></div>
          </div>
          <div class="pagetitle icon-48-article">
            <h2>Gerenciador de Documentos</h2>
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
            <li> <a  href="<c:url value="/caixas/lista" />" >Caixas</a> </li>
            <li> <a class="active" href="<c:url value="/documentos/lista" />">Documentos</a> </li>
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
        <form action="<c:url value="/caixas/edita" />" method="post" name="adminForm" id="adminForm">
                     
           Nome da Caixa :  <b><span>${caixa.nome}</span></b><br /><br />             
            <input type="hidden" name="caixaId" id="caixaId" value="${caixa.id}" />
            <table class="adminlist">
              <thead>
                <tr>
                  <th width="1%"> 
                  <input type="checkbox" name="checkall-toggle" value="" onclick="checkAll(this)" /></th>
                  <th><a href="javascript:void(0)" title="Título">Título</a> </th>                  
                  <th><a href="javascript:void(0)" title="Descrição">Descrição</a> </th>
                  <th><a href="javascript:void(0)" title="Status">Status</a> </th>
                  <th width="10%"><a href="javascript:void(0);" title="Criado por">Criado por</a> </th>
                  <th width="5%"><a href="javascript:void(0);" title="Data">Data</a> </th>                  
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
              	              
                <c:forEach items="${documentoList}" var="documento" varStatus="rowCounter"> 
	                <tr class="
	                	<c:choose>
	                		<c:when test="${rowCounter.count % 2 == 0}">row1</c:when>
	                		<c:otherwise>row0</c:otherwise>
	                	</c:choose>
	                ">
	                  <td class="center">
	                  <input type="checkbox" id="id" name="id" value="${documento.id}" onclick="isChecked(this.checked);" title="Checkbox para a linha ${rowCounter.count}" />		
	                  
	                  </td>
	                  <td><a href="#" />${documento.nome}</a></td>                                                    
	                  <td><a href="#" />${documento.descricao}</a></td>
	                  <td><a href="#" />${documento.status}</a></td>
	                  <td class="center">Super User</td>
	                  <td class="center nowrap">29.08.11</td>                  
	                  <td class="center"> ${caixa.id}</td>
	                </tr>
                </c:forEach>               
              </tbody>
            </table>
            <div>
              <input type="hidden" name="task" value="" />
              <input type="hidden" name="boxchecked" value="0" />
              
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
  </div>
</div>
<div id="border-bottom">
  <div>
    <div></div>
  </div>
</div>
<div id="footer">
  <p class="copyright"> </p>
</div>
</body>
</html>