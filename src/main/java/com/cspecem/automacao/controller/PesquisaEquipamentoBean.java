package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Equipamento;
import com.cspecem.automacao.repository.Equipamentos;
import com.cspecem.automacao.repository.filter.EquipamentoFilter;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEquipamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Equipamentos equipamentos;
	private Equipamento equipamento;
	private EquipamentoFilter filtro;
	private List<Equipamento> equipamentosFiltrados;
	private Equipamento equipamentoSelecionado;
	private List<Equipamento> equipamentosSelecionados;
	
	public PesquisaEquipamentoBean() {
		filtro = new EquipamentoFilter();
	}
	
	public void excluir() {
		try {
			equipamentos.deletar(equipamento.getId());
			equipamentosFiltrados.remove(equipamentoSelecionado);
			
			FacesUtil.addInfoMessage("Equipamento " + equipamentoSelecionado.getId() 
					+ " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar excluir : " + e.getMessage());
		}
	}
	
	public void limpar() {
		equipamento = new Equipamento();
	}
	
	public void pesquisar() {
		equipamentosFiltrados = equipamentos.filtrados(filtro);
	}
	
	public List<Equipamento> getEquipamentosFiltrados() {
		return equipamentosFiltrados;
	}

	public EquipamentoFilter getFiltro() {
		return filtro;
	}

	public Equipamento getEquipamentoSelecionado() {
		return equipamentoSelecionado;
	}

	public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}

	public List<Equipamento> getEquipamentosSelecionados() {
		return equipamentosSelecionados;
	}

	public void setEquipamentosSelecionados(List<Equipamento> equipamentosSelecionados) {
		this.equipamentosSelecionados = equipamentosSelecionados;
	}
	
	

}
