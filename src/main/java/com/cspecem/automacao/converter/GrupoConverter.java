package com.cspecem.automacao.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

import com.cspecem.automacao.model.Grupo;
import com.cspecem.automacao.repository.Grupos;
import com.cspecem.automacao.util.cdi.CDIServiceLocator;

public class GrupoConverter implements Converter {
	
	@Inject
	private Grupos grupos;
	
	public GrupoConverter() {
		this.grupos = CDIServiceLocator.getBean(Grupos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if (value != null && !value.isEmpty()) {
				Long id = Long.parseLong(value);
				Grupo retorno = grupos.porId(id);
	            return retorno;
	        }
	        return null;
	        
		} catch (NumberFormatException e) {
			throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um objeto válido."));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && (object instanceof Grupo)) {
            return String.valueOf(((Grupo) object).getId());
        }
        return null;
	}
	
}
