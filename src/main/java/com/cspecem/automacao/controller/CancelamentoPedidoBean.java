package com.cspecem.automacao.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Pedido;
import com.cspecem.automacao.service.CancelamentoPedidoService;
import com.cspecem.automacao.service.NegocioException;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoPedidoService cancelamentoPedidoService;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	/*
	public void cancelarPedido() {
		try {
			this.pedido = this.cancelamentoPedidoService.cancelar(this.pedido);
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
			
			FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	*/
}
