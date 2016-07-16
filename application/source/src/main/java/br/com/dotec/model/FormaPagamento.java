package br.com.dotec.model;

public class FormaPagamento {
	
	
	
	public static String getFormaPagamento(int forma){		
		switch (forma) {
		case 1:
			return "Saldo moip pela internet";			
		case 3:
			return "Visa crédito";
		case 4:
			return "Visa débito";
		case 5:
			return "Master crédito";
		case 6:
			return "Diners crédito";
		case 7:
			return "Amex credito";
		case 8:
			return "BB transferência";
		case 9:
			return "BB boleto";
		case 10:
			return "BB financiamento";
		case 12:
			return "Itau não escolhido";
		case 13:
			return "Itaú transferência";
		case 14:
			return "Itaú boleto";
		case 21:
			return "Bradesco financiamento";
		case 22:
			return "Bradesco transferência";
		case 23:
			return "Bradesco boleto";
		case 31:
			return "Real financiamento";
		case 32:
			return "Real transferência";
		case 33:
			return "Real boleto";
		case 73:
			return "Boleto bancário";
		case 75:
			return "Hipercard";
		case 76:
			return "OIPaggo";
		default:
			return "";
		}
		
	}
	
	
}
