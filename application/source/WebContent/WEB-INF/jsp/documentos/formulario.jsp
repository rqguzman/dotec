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
              <li class="button" id="toolbar-save"> <a href="javascript:document.getElementById('frmadmin').submit()" onClick="" class="toolbar"> <span class="icon-32-save"> </span> Salvar &amp; Fechar </a> </li>              
              <li class="button" id="toolbar-cancel"> <a href="<c:url value="/documentos/?caixaId=${caixa.id}" />" class="toolbar"> <span class="icon-32-cancel"> </span> Cancelar </a> </li>
              <li class="divider"> </li>
              <li class="button" id="toolbar-help"> <a href="#" onClick="popupWindow('http://help.joomla.org/proxy/index.php?option=com_help&amp;keyref=Help16:Components_Weblinks_Categories_Edit', 'Ajuda', 700, 500, 1)" rel="help" class="toolbar"> <span class="icon-32-help"> </span> Ajuda </a> </li>
            </ul>
            <div class="clr"></div>
          </div>
          <div class="pagetitle icon-48-category-add icon-48-weblinks-category-add">
            <h2>Gerenciador de Documentos: Adicionar Documentos</h2>
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
        <div class="m"> 
                             
         <form  id="frmadmin" name="frmadmin" class="cmxform" <c:choose><c:when test='${documento.id == null}'>action="adiciona"</c:when><c:otherwise>action="edita"</c:otherwise></c:choose> 
					  method="post" >
		<input type="hidden" name="documento.id"  value="${documento.id}" />
		<input type="hidden" name="documento.caixaId" id="documento.caixaId"  value="${caixa.id}" />
		            
            <div class="width-60 fltlft">
              <fieldset class="adminform">
                <legend>Detalhes</legend>
                <ul class="adminformlist">
                  <li>
                  	<label id="document.nome-lbl" for="caixa.caixaId" class="hasTip required" title="caixaId:caixa Id">Caixa<span class="star">&#160;*</span></label>
                  	${caixa.nome} 
                  </li>
                  <li> 
                    <dotec:campoTexto label="Nome:" id="documento.nome" value="${documento.nome}" classe="inputbox required" size="40"></dotec:campoTexto>                    
                  </li>
                                             
                </ul>
                <div class="clr"></div>
				<label id="jform_articletext-lbl" for="jform_articletext" class="hasTip" title="Descrição da Caixa::Insira a descriçãoda caixa na área de texto">Descrição da Caixa</label>			<div class="clr"></div>
				<textarea name="documento.descricao" id="documento.descricao" cols="0" rows="0" style="width:100%; height:250px;" class="mce_editable">${documento.descricao}</textarea>
			
			 <div class="clr"></div>
                
              </fieldset>
            </div>
            <div class="width-40 fltrt">
              <div id="categories-sliders-" class="pane-sliders">
                <div style="display:none;">
                  <div> </div>
                </div>
              </div>
            </div>
            <div class="clr"></div>
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
  <p class="copyright"> <a href="http://www.joomla.org">Joomla!&#174;</a> é um software livre liberado sob a <a href="http://www.gnu.org/licenses/gpl-2.0.html">GNU General Public License</a>. <span class="version">Versão 1.6.6</span> </p>
</div>
</body></html>