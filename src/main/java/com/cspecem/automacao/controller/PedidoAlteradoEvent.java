package com.cspecem.automacao.controller;

import com.cspecem.automacao.model.Pedido;

public class PedidoAlteradoEvent {

	private Pedido pedido;
	
	public PedidoAlteradoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
}
