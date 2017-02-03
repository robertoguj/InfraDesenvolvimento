package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Equipamento;
import com.cspecem.automacao.model.LocalInstalacao;
import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.Equipamentos;
import com.cspecem.automacao.repository.LocaisInstalacao;
import com.cspecem.automacao.repository.Produtos;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EquipamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Equipamentos equipamentos;
	private Equipamento equipamento;
	private Equipamento equipamentoSelecionado;
	private List<SelectItem> statusGarantia;
	private List<Equipamento> equipamentosLista;
	private List<Equipamento> equipamentosSelecionados;
	
	@Inject
	private Produtos produtos;
	private List<Produto> produtosLista;
	
	@Inject
	private LocaisInstalacao locais;
	private List<LocalInstalacao> locaisLista;
	
	public EquipamentoBean() {
		limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			getProdutosLista();
			getLocaisLista();
		}
	}
	
	public void limpar() {
		this.equipamento = new Equipamento();
	}
	
	public void salvar() {
		try {
			this.equipamento = equipamentos.salvar(equipamento);
			limpar();
			FacesUtil.addInfoMessage("Produto salvo com sucesso!");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar salvar. " + e.getMessage());
		}
	}
	
	public void atualizar() {
		try {
			this.equipamento = equipamentos.atualizar(equipamento);
			limpar();
			FacesUtil.addInfoMessage("Atualizado com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar atualizar " + e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			this.equipamentos.deletar(equipamento.getId());
			FacesUtil.addInfoMessage("Equipamento " + this.equipamento.getId() + " removido com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar remover " + e.getMessage());
		}
	}
	
	public List<SelectItem> getStatusGarantia() {
		if (this.statusGarantia == null) {
			this.statusGarantia = new ArrayList<SelectItem>();
			this.statusGarantia.add(new SelectItem("ATIVA","Ativa"));
			this.statusGarantia.add(new SelectItem("EXPIRADA","Expirada"));
		}
		return statusGarantia;
	}
	
	public boolean isEditando() {
		return this.equipamento.getId() != null;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Produto> getProdutosLista() {
		if (this.produtosLista == null) {
			this.produtosLista = produtos.listar("id");
		}
		
		return produtosLista;
	}

	public List<Equipamento> getEquipamentosLista() {
		if (this.equipamentosLista == null) {
			this.equipamentosLista = equipamentos.listar("id");
		}
		
		return equipamentosLista;
	}

	public List<LocalInstalacao> getLocaisLista() {
		if (this.locaisLista == null) {
			this.locaisLista = locais.listar("id");
		}
		return locaisLista;
	}
	
	public Equipamento getEquipamentoSelecionado() {
		return equipamentoSelecionado;
	}
	
	public List<Equipamento> getEquipamentosSelecionados() {
		return equipamentosSelecionados;
	}

	public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}

	public void setEquipamentosSelecionados(List<Equipamento> equipamentosSelecionados) {
		this.equipamentosSelecionados = equipamentosSelecionados;
	}


}
