package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.Produtos;
import com.cspecem.automacao.repository.filter.ProdutoFilter;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	private Produto produto;
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	
	private Produto produtoSelecionado;
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	
	public void excluir() {
		try {
			produtos.deletar(produto.getId());
			produtosFiltrados.remove(produtoSelecionado);
			
			FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() 
					+ " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar excluir : " + e.getMessage());
		}
	}
	
	public void limpar() {
		produto = new Produto();
	}
	
	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
}