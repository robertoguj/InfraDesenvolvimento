package com.cspecem.automacao.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.cspecem.automacao.model.Equipamento;
import com.cspecem.automacao.repository.Equipamentos;
import com.cspecem.automacao.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Equipamento.class)
public class EquipamentoConverter implements Converter {
	
	@Inject
	private Equipamentos equipamentos;
	
	public EquipamentoConverter() {
		this.equipamentos = CDIServiceLocator.getBean(Equipamentos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {
			if (value != null && !value.isEmpty()) {
				return this.equipamentos.porId(Long.parseLong(value));
			}
			return null;
		} catch (NumberFormatException e) {
			throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um objeto válido."));
		}

	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && (object instanceof Equipamento)) {
			return String.valueOf(((Equipamento) object).getId());
		}
		return null;
	}

}
