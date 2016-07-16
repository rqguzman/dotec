$(document).ready(function() {
	
	$(".btnEdit").click(function(){
		if($('input[@name=id]:checked').size() == 0){
			   alert('Por favor, selecione através da lista');
			   return false;
		}else if($('input[@name=fieldNames]:checked').size() > 1){
			   alert('Por favor, selecione apenas um elemento na lista');
			   return false;
		}
		 $('#adminForm').submit();
	});
	
	$('.formUpdateStatus').submit(function() {
		var serializedFormData = $(this).serializeArray();
		$.post("updateStatus",serializedFormData, "json")
		.success(function() {
			alert("Status da movimentação alterada com sucesso!");
		})
		.error(function(data) { 
			alert("Erro na alteração do Status da movimentação!");				 
		});
		return false;
	});

});