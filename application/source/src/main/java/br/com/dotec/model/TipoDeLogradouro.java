package br.com.dotec.model;

public enum TipoDeLogradouro {
		ACS("ACESSO")
	   ,AER("AEROPORTO")     
	   ,AL("ALAMEDA") 	    
	   ,AP("APARTAMENTO")   
	   ,AV("AVENIDA") 	
       ,AT("ATALHO")	   
	   ,BC("BECO") 	        
	   ,BL("BLOCO") 
       ,BOU("BOULEVARD")	   
	   ,CAM("CAMINHO") 	  
	   ,CMP("CAMPO")
	   ,COND("CONDOMINIO")
	   ,CONJ("CONJUNTO")
	   ,ESC("ESCADA")
	   ,ESCD("ESCADINHA") 	  
	   ,EST("ESTA��O") 	    
	   ,ETR("ESTRADA") 	 
	   ,ETV("ESTRADA VELHA")
	   ,FAZ("FAZENDA")
       ,FAV("FAVELA")	   
	   ,FORT("FORTALEZA") 	  
	   ,FRTE("FORTE") 
	   ,GL("GALERIA") 	    
	   ,ILH("ILHA")
	   ,JAR("JARDIM")
	   ,LD("LADEIRA") 	    
	   ,LGO("LARGO") 
	   ,LOT("LOTEAMENTO")
	   ,MOR("MORRO")
	   ,PAT("P�TIO")
	   ,PAR("PARALELA")
	   ,PRQ("PARQUE") 
       ,PON("PONTA")	   
	   ,P�A("PRA�A")
	   ,PR("PRAIA") 	      
	   ,QD("QUADRA") 	      
	   ,KM("QUIL�METRO") 	  
	   ,QTA("QUINTA") 	
       ,RET("RETA")	   
	   ,ROD("RODOVIA") 	    
	   ,R("RUA") 	     
       ,SER("SERVID�O")	 
       ,SET("SETOR")	   
	   ,SQD("SUPER QUADRA") 	
	   ,TRV("TRAVESSA") 
       ,VAL("VALE")	   
	   ,VIA("VIA") 	    
	   ,VD("VIADUTO") 	    
	   ,VL("VILA")
	   ,VIE("VIELA"); 

	private final String nome;

	TipoDeLogradouro(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}

