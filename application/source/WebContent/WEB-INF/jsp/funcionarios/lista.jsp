<%@ include file="/header.jsp" %>
	<form>
		<fieldset>
			<legend>&nbsp;Lista de Funcionários:<a class="linkMais" href="<c:url value="/funcionarios/formulario"/>"><img class="btnMais" alt="mais" title="mais" src="<c:url value="/images/mais.png"/>" /></a></legend>
			<dotec:formularioDeBusca></dotec:formularioDeBusca>
			<table>
				<thead>
					<th>Nome</th>
					<th>CPF</th>
					<th>RG</th>
					<th>Data de Nascimento</th>
					<th>Sexo</th>
					<th>Matrícula</th>
				</thead>
				<tbody>
					<c:forEach items="${funcionarioList}" var="funcionario">
						<tr>
							<td>${funcionario.nome}</td>
							<td>${funcionario.cpf}</td>
							<td>${funcionario.rg}</td>
							<td><fmt:formatDate value="${funcionario.dataDeNascimento.time}" pattern="dd/MM/yyyy"/></td>
							<td>${funcionario.sexo}</td>
							<td>${funcionario.matricula}</td>
							<td><a href="edita?id=${funcionario.id}"><img class="btnEditarItem" alt="Editar" title="Editar" src="<c:url value="/images/btnEditar.png"/>" /></a></td>
							<td><a href="remove?id=${funcionario.id}"><img class="btnExcluirItem" alt="Excluir" title="Excluir" src="<c:url value="/images/btnExcluir.png"/>" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</form>
<%@ include file="/footer.jsp" %> 
</html>