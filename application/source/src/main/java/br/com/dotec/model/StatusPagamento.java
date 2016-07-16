package br.com.dotec.model;

public class StatusPagamento {	
	public static String getPagamento(int status){

		switch (status) {
			case 1:
				return "Autorizado";
				
			case 2:
				return "Iniciado";
				
			case 3:
				return "Boleto Impresso";
				
			case 4:
				return "Concluído";
				
			case 5:
				return "Cancelado";
				
			case 6:
				return "Em análise";
				
			case 7:
				return "Estornado";
				
			case 8:
				return "Em revisão";
				
			case 9:
				return "Reembolsado";
				
			default:
				return "";			
		}
	}
		
}
