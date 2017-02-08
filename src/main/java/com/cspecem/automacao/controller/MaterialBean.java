package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Material;
import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.Materiais;
import com.cspecem.automacao.repository.Produtos;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MaterialBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Materiais materiais;
	private Material material;
	private List<SelectItem> statusCadastroSAP;
	private List<Material> materiaisLista;
	private Material materialSelecionado;
	private List<Material> materiaisSelecionados;
	
	@Inject
	private Produtos produtos;
	private List<Produto> produtosLista;
	
	public MaterialBean() {
		limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		System.out.println("inicializacao");
	}
	
	public void limpar() {
		material = new Material();
	}
	
	public void salvar() {
		try {
			this.material = materiais.salvar(material);
			limpar();
			FacesUtil.addInfoMessage("Salvo com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar salvar. " + e.getMessage());
		}
	}
	
	public void atualizar() {
		try {
			this.material = materiais.atualizar(material);
			limpar();
			FacesUtil.addInfoMessage("Atualizado com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar atualizar " + e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			this.materiais.deletar(material.getId());
			FacesUtil.addInfoMessage("Equipamento " + this.material.getId() + " removido com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar remover " + e.getMessage());
		}
	}
	
	public boolean isEditando() {
		return this.material.getId() != null;
	}
	
	public List<SelectItem> getStatusCadastroSAP() {
		if (this.statusCadastroSAP == null) {
			this.statusCadastroSAP = new ArrayList<SelectItem>();
			this.statusCadastroSAP.add(new SelectItem("Pendente","Pendente"));
			this.statusCadastroSAP.add(new SelectItem("Cadastrado","Cadastrado"));
			this.statusCadastroSAP.add(new SelectItem("Não cadastrado","Não cadastrado"));
		}
		return statusCadastroSAP;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public List<Produto> getProdutosLista() {
		if (this.produtosLista == null) {
			this.produtosLista = produtos.listar("id");
		}
		return produtosLista;
	}
	
	public List<Material> getMateriaisLista() {
		if (this.materiaisLista == null) {
			this.materiaisLista = materiais.listar("id");
		}
		return materiaisLista;
	}
	
	public Material getMaterialSelecionado() {
		return materialSelecionado;
	}

	public void setMaterialSelecionado(Material materialSelecionado) {
		this.materialSelecionado = materialSelecionado;
	}

	public List<Material> getMateriaisSelecionados() {
		return materiaisSelecionados;
	}

	public void setMateriaisSelecionados(List<Material> materiaisSelecionados) {
		this.materiaisSelecionados = materiaisSelecionados;
	}


}
