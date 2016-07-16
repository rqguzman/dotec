package br.com.dotec.model;

import javax.persistence.Entity;

@Entity
public class Envelope extends Elemento {

	@Override
	public TipoDeElemento getTipoDeElemento() {
		return TipoDeElemento.ENVELOPE;
	}

}
