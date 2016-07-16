package br.com.dotec.model;

public class FormaPagamento {
	
	
	
	public static String getFormaPagamento(int forma){		
		switch (forma) {
		case 1:
			return "Saldo moip pela internet";			
		case 3:
			return "Visa cr�dito";
		case 4:
			return "Visa d�bito";
		case 5:
			return "Master cr�dito";
		case 6:
			return "Diners cr�dito";
		case 7:
			return "Amex credito";
		case 8:
			return "BB transfer�ncia";
		case 9:
			return "BB boleto";
		case 10:
			return "BB financiamento";
		case 12:
			return "Itau n�o escolhido";
		case 13:
			return "Ita� transfer�ncia";
		case 14:
			return "Ita� boleto";
		case 21:
			return "Bradesco financiamento";
		case 22:
			return "Bradesco transfer�ncia";
		case 23:
			return "Bradesco boleto";
		case 31:
			return "Real financiamento";
		case 32:
			return "Real transfer�ncia";
		case 33:
			return "Real boleto";
		case 73:
			return "Boleto banc�rio";
		case 75:
			return "Hipercard";
		case 76:
			return "OIPaggo";
		default:
			return "";
		}
		
	}
	
	
}
