package com.cspecem.automacao.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.cspecem.automacao.model.Grupo;

@FacesConverter(value="entityConverter")
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !"".equals(value)) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			Grupo entity = (Grupo) value;

			// adiciona item como atributo do componemte.
			this.addAttribute(component, entity);

			Long codigo = entity.getId();

			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}
		return (String) value;
	}

	protected void addAttribute(UIComponent component, Grupo grupo) {
		// codigo da entidade em geral como chave neste caso.
		String key = grupo.getId().toString();
		this.getAttributesFrom(component).put(key, grupo);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
