package com.cspecem.automacao.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.cspecem.automacao.model.Material;
import com.cspecem.automacao.repository.Materiais;
import com.cspecem.automacao.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Material.class)
public class MaterialConverter implements Converter {
	
	@Inject
	private Materiais materiais;
	
	public MaterialConverter() {
		this.materiais = CDIServiceLocator.getBean(Materiais.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {
			if (value != null && !value.isEmpty()) {
				return this.materiais.porId(Long.parseLong(value));
			}
			return null;
		} catch (NumberFormatException e) {
			throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um objeto válido."));
		}

	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && (object instanceof Material)) {
			return String.valueOf(((Material) object).getId());
		}
		return null;
	}

}
