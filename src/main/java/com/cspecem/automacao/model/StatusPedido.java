package com.cspecem.automacao.model;

public enum StatusPedido {

	//ORCAMENTO("Orçamento"),
	ANALISE("Em análise"),
	EMITIDO("Emitido"), 
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
