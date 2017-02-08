package com.cspecem.automacao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.cspecem.automacao.model.PedidoCompra;
import com.cspecem.automacao.repository.Pedidos;
import com.cspecem.automacao.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = PedidoCompra.class)
public class PedidoConverter implements Converter {

	//@Inject
	private Pedidos pedidos;
	
	public PedidoConverter() {
		pedidos = CDIServiceLocator.getBean(Pedidos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PedidoCompra retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = pedidos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			PedidoCompra pedido = (PedidoCompra) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

}
