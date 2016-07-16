package br.com.dotec.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Documento extends Elemento {
	
	@OneToOne(cascade = { CascadeType.ALL })
	private File file;
	
	@Override
	public TipoDeElemento getTipoDeElemento() {
		// TODO Auto-generated method stub
		return TipoDeElemento.DOCUMENTO;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
