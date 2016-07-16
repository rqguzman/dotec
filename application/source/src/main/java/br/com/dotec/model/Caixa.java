package br.com.dotec.model;

import javax.persistence.Entity;

@Entity
public class Caixa extends Elemento {

	@Override
	public TipoDeElemento getTipoDeElemento() {
		return TipoDeElemento.CAIXA;
	}

}
