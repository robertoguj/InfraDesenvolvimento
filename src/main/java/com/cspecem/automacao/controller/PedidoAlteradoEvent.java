package com.cspecem.automacao.controller;

import com.cspecem.automacao.model.PedidoCompra;

public class PedidoAlteradoEvent {

	private PedidoCompra pedido;
	
	public PedidoAlteradoEvent(PedidoCompra pedido) {
		this.pedido = pedido;
	}

	public PedidoCompra getPedido() {
		return pedido;
	}
	
}
