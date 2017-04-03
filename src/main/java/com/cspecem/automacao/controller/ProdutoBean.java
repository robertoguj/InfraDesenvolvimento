package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.Produtos;
import com.cspecem.automacao.service.NegocioException;
import com.cspecem.automacao.service.ProdutoService;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private Produtos produtos;
	
	private Produto produto;
	private List<Produto> produtosLista;
	private String selecionaItem;
	private List<SelectItem> categorias;
	private List<SelectItem> fabricantes;
	
	private Produto produtoSelecionado;
	private List<Produto> produtosSelecionados;
	
	public ProdutoBean() {
		limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		System.out.println("inicializacao");
	}
	
	private void limpar() {
		produto = new Produto();
		
	}
	
	public void salvar() {
		try {
			this.produto = produtoService.salvar(this.produto);
			limpar();
			
			FacesUtil.addInfoMessage("Produto salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void excluir() {
		try {
			this.produtos.deletar(produto.getId());
			FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getModelo() + " removido com sucesso.");

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Ocorreu um erro ao excluir : " + e.getMessage());
		}
	}
	
	public void atualizar() {
		try {
			this.produtos.atualizar(this.produto);
			limpar();
			FacesUtil.addInfoMessage("Atualizado com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar atualizar " + e.getMessage());
		}
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

	public String getSelecionaItem() {
		return selecionaItem;
	}

	public List<SelectItem> getCategorias() {
		if (this.categorias == null) {
			this.categorias = new ArrayList<SelectItem>();
			this.categorias.add(new SelectItem("Acessórios", "Acessórios"));
			this.categorias.add(new SelectItem("Áudio e vídeo", "Áudio e vídeo"));
			this.categorias.add(new SelectItem("Eletro eletrônico", "Eletro eletrônico"));
			this.categorias.add(new SelectItem("Impressora", "Impressora"));
			this.categorias.add(new SelectItem("Hardware", "Hardware"));
			this.categorias.add(new SelectItem("Periféricos", "Periféricos"));
			this.categorias.add(new SelectItem("Redes", "Redes"));
			this.categorias.add(new SelectItem("Servidor", "Servidor"));
			this.categorias.add(new SelectItem("Software", "Software"));
			this.categorias.add(new SelectItem("Storage", "Storage"));
			this.categorias.add(new SelectItem("Suprimentos", "Suprimentos"));
			this.categorias.add(new SelectItem("Switch", "Switch"));
			this.categorias.add(new SelectItem("Workstation", "Workstation"));
		}
		return categorias;
	}
	
	public List<SelectItem> getFabricantes() {
		if (this.fabricantes == null) {
			this.fabricantes = new ArrayList<SelectItem>();
			this.fabricantes.add(new SelectItem("ABB", "ABB"));
			this.fabricantes.add(new SelectItem("Advantech", "Advantech"));
			this.fabricantes.add(new SelectItem("Allied Telesis", "Allied Telesis"));
			this.fabricantes.add(new SelectItem("Cisco", "Cisco"));
			this.fabricantes.add(new SelectItem("Dell", "Dell"));
			this.fabricantes.add(new SelectItem("Furukawa", "Furukawa"));
			this.fabricantes.add(new SelectItem("Hirschmann", "Hirschmann"));
			this.fabricantes.add(new SelectItem("HP", "HP"));
			this.fabricantes.add(new SelectItem("Yokogawa", "Yokogawa"));
			this.fabricantes.add(new SelectItem("iEi", "iEi"));
			this.fabricantes.add(new SelectItem("Ibyte", "Ibyte"));
			this.fabricantes.add(new SelectItem("Intel", "Intel"));
			this.fabricantes.add(new SelectItem("Microsoft", "Microsoft"));
			this.fabricantes.add(new SelectItem("Oracle", "Oracle"));
			this.fabricantes.add(new SelectItem("Ruggedcom", "Ruggedcom"));
			this.fabricantes.add(new SelectItem("Samsung", "Samsung"));
			this.fabricantes.add(new SelectItem("Siemens", "Siemens"));
			this.fabricantes.add(new SelectItem("Seagate", "Seagate"));

		}
		return fabricantes;
	}
	
	public List<Produto> getProdutosLista() {
		if (this.produtosLista == null) {
			this.produtosLista = produtos.listar("descricao");
		}
		return this.produtosLista;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}
	
	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<Produto> getProdutosSelecionados() {
		return produtosSelecionados;
	}

	public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}

	
}