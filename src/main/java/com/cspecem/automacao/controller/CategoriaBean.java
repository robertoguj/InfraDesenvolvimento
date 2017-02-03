package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Categoria;
import com.cspecem.automacao.repository.Categorias;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;
	private Categoria categoria;
	private List<Categoria> categoriasLista;
	private Categoria categoriaSelecionada;
	private List<Categoria> categoriasSelecionadas;
	
	public CategoriaBean() {
		limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			
		}
		System.out.println("inicializacao");
	}
	
	public void salvar() {
		try {
			this.categorias.salvar(categoria);
			FacesUtil.addInfoMessage("Salvo com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao salvar " + e.getMessage());
		}
	}
	
	public List<Categoria> getCategoriasLista() {
		if (this.categoriasLista == null) {
			this.categoriasLista = categorias.listar("descricao");
		}
		return categoriasLista;
	}
	
	public void limpar() {
		this.categoria = new Categoria();
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public List<Categoria> getCategoriasSelecionadas() {
		return categoriasSelecionadas;
	}

	public void setCategoriasSelecionadas(List<Categoria> categoriasSelecionadas) {
		this.categoriasSelecionadas = categoriasSelecionadas;
	}
	
	
	
}
