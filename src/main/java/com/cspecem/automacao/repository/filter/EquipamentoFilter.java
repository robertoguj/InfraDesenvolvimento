package com.cspecem.automacao.repository.filter;

import java.io.Serializable;

public class EquipamentoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Codigo;
	private String numeroSerie;

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie == null ? null : numeroSerie.toUpperCase();
	}
	
	

}
