package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Equipamento;
import com.cspecem.automacao.repository.Equipamentos;
import com.cspecem.automacao.repository.filter.EquipamentoFilter;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EquipamentoBean extends ExtensaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Equipamentos equipamentos;
	private Equipamento equipamento;
	private Equipamento equipamentoSelecionado;
	private List<Equipamento> equipamentosLista;
	private List<Equipamento> equipamentosSelecionados;
	
	private EquipamentoFilter filtro;
	private List<Equipamento> equipamentosFiltrados;
	
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
	
	public void pesquisar() {
		equipamentosFiltrados = equipamentos.filtrados(filtro);
	}
	
	public void limpar() {
		equipamento = new Equipamento();
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
	
	public boolean isEditando() {
		return this.equipamento.getId() != null;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getEquipamentosLista() {
		if (this.equipamentosLista == null) {
			this.equipamentosLista = equipamentos.listar("id");
		}
		
		return equipamentosLista;
	}

	public Equipamento getEquipamentoSelecionado() {
		return equipamentoSelecionado;
	}
	
	public List<Equipamento> getEquipamentosSelecionados() {
		return equipamentosSelecionados;
	}
	
	public List<Equipamento> getEquipamentosFiltrados() {
		return equipamentosFiltrados;
	}

	public EquipamentoFilter getFiltro() {
		return filtro;
	}

	public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}

	public void setEquipamentosSelecionados(List<Equipamento> equipamentosSelecionados) {
		this.equipamentosSelecionados = equipamentosSelecionados;
	}


}
