package com.cspecem.automacao.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.cspecem.automacao.model.LocalInstalacao;
import com.cspecem.automacao.repository.LocaisInstalacao;
import com.cspecem.automacao.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=LocalInstalacao.class)
public class LocalInstalacaoConverter implements Converter {

	@Inject
	private LocaisInstalacao locais;
	
	public LocalInstalacaoConverter() {
		this.locais = (LocaisInstalacao) CDIServiceLocator.getBean(LocaisInstalacao.class);
	}
	
	@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
		try {
			if (value != null && !value.isEmpty()) {
	            return this.locais.porId(Long.parseLong(value));
	        }
	        return null;
	        
		} catch (NumberFormatException e) {
			throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um objeto válido."));
		}

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null && (object instanceof LocalInstalacao)) {
            return String.valueOf(((LocalInstalacao) object).getId());
        }

        return null;
    }

}
