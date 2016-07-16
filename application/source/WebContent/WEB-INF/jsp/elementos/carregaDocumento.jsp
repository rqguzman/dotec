<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dotec" %>
<fmt:setLocale value="${locale}"/>
<p style="padding:0"><b>Nome: </b> ${elemento.nome}</p>
<p style="padding:0"><b>Descri��o: </b> ${elemento.descricao}</p>
<c:if test="${!empty documentoList}">
<div id="demo">
			<th>dfxfg</th>			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${documentoList}" var="documento" varStatus="rowCounter">
			<tr>				
				<td>${documento.id}</td>
				<td>${documento.descricao}</td>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>		
			<th>ID</th>
			<th>Descri��o</th>
			<th></th>
		</tr>
</table>
			</div>
			
<script type="text/javascript">		
	
	var oTable = $('#example').dataTable( {
		"bPaginate": true,					
		"bLengthChange": false,
		"bFilter": true,
		"bSort": true,
		"bInfo": false,
		"bAutoWidth": false } );
	
			
		function solicitarDocumento(){
			$(".quantidade").css("display","none");	
		}
	
		function carregaDocumentos(id){
	
		console.log(id);
			$.ajax({
		        url : "carregaDocumento",
		        type: "post",
		        dataType: "html",
		        data: "id=" + id,
		        success: function (data) {
		           $('#boxDocumentos').html(data);			          
		        },
		        error: function (jXHR, textStatus, errorThrown) {
		            if(jXHR.responseText!=null && jXHR.responseText!=""){
		            	alert(jXHR.responseText);
		            }else{
		            	alert("Erro ao carregar os documentos!");
		            }
		        }
		    });
		}
	
		function removerDocumento(id, paiId, obj){
			$.ajax({
		        url : "removerDocumento",
		        type: "post",
		        dataType: "json",
		        data: "id=" + id,
		        success: function (data) {		            
		        	alert(data);			        			        
		        	carregaDocumentos(paiId);
		        },
		        error: function (jXHR, textStatus, errorThrown) {
		            if(jXHR.responseText!=null && jXHR.responseText!=""){
		            	alert(jXHR.responseText);
		            }else{
		            	alert("Erro no processo de remo��o do elemento!");
		            }
		        }
		    });
		}
		
		function addDoc(id){
			$("#elementoPaiId").val(id);
			solicitarDocumento();	
		}
	
		function deleteDoc(id, paiId, obj){			
			removerDocumento(id, paiId, this);
		}

		
</script>

</c:if>



