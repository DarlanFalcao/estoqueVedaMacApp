package br.com.vedamac.model;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.omnifaces.converter.SelectItemsConverter;

public class Unidade extends SelectItemsConverter  {
	private String idUnidade;
	private String unidade;
	
	
	public Unidade(){
		
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	
	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	    
	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        String id = (value instanceof Unidade) ? ((Unidade) value).getUnidade() : null;
	        return (id != null) ? String.valueOf(id) : null;
	    }
	


}
