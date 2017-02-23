package com.cspecem.automacao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.cspecem.automacao.model.LocalInstalacao;
import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.LocaisInstalacao;
import com.cspecem.automacao.repository.Produtos;

public abstract class AbstractController {
	
	@Inject
	private Produtos produtos;
	private List<Produto> produtosLista;
	
	@Inject
	private LocaisInstalacao locais;
	private List<SelectItem> locaisLista;
	
	public List<SelectItem> getLocaisLista() {
		if (this.locaisLista == null) {
			this.locaisLista = new ArrayList<SelectItem>();
			for (LocalInstalacao li : locais.listar("local")) {
				this.locaisLista.add(new SelectItem(li.getArea(), li.getLocal()));
			}
		}
		return locaisLista;
	}
	
	public List<Produto> getProdutosLista() {
		if (this.produtosLista == null) {
			this.produtosLista = produtos.listar("id");
		}
		
		return produtosLista;
	}
	

}
