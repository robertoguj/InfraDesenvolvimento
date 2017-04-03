package com.cspecem.automacao.controller;

import java.util.List;

import javax.inject.Inject;

import com.cspecem.automacao.model.LocalInstalacao;
import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.LocaisInstalacao;
import com.cspecem.automacao.repository.Produtos;

public abstract class ExtensaoBean {
	
	@Inject
	private Produtos produtos;
	private List<Produto> produtosLista;
	
	@Inject
	private LocaisInstalacao locais;
	private List<LocalInstalacao> locaisLista;
	
	public List<LocalInstalacao> getLocaisLista() {
		if (this.locaisLista == null) {
			this.locaisLista = locais.listar("local");
		}
		return locaisLista;
	}
	
	public List<Produto> getProdutosLista() {
		if (this.produtosLista == null) {
			this.produtosLista = produtos.listar("descricao");
		}
		
		return produtosLista;
	}
	

}
