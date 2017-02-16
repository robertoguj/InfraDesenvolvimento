package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.LocalInstalacao;
import com.cspecem.automacao.repository.LocaisInstalacao;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class LocalInstalacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LocaisInstalacao locais;
	private LocalInstalacao localInstalacao;
	private LocalInstalacao localSelecionado;
	private List<SelectItem> areas;
	private List<LocalInstalacao> locaisLista;
	private List<LocalInstalacao> locaisSelecionados;

	public LocalInstalacaoBean() {
		limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			getAreas();
		}
		System.out.println("inicializacao");
	}
	
	public void excluir() {
		try {
			locais.deletar(this.localInstalacao.getId());
			FacesUtil.addInfoMessage("Removido com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void salvar() {
		try {
			this.locais.salvar(this.localInstalacao);
			limpar();
			FacesUtil.addInfoMessage("Salvo com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar salvar " + e.getMessage());
		}
	}
	
	public void atualizar() {
		try {
			this.locais.atualizar(this.localInstalacao);
			limpar();
			FacesUtil.addInfoMessage("Atualizado com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar atualizar " + e.getMessage());
		}
	}
	
	private void limpar() {
		this.localInstalacao = new LocalInstalacao();
	}
	
	public boolean isEditando() {
		return this.localInstalacao.getId() != null;
	}

	public List<SelectItem> getAreas() {
		if (this.areas == null) {
			this.areas = new ArrayList<SelectItem>();
			this.areas.add(new SelectItem("Alto Forno","Blast Furnace"));
			this.areas.add(new SelectItem("Laboratório Central","Central Laboratory"));
			this.areas.add(new SelectItem("Coqueria","Coke Making"));
			this.areas.add(new SelectItem("Lingotamento Contínuo","Continuous Casting"));
			this.areas.add(new SelectItem("Energy Center","Energy Center"));
			this.areas.add(new SelectItem("Power Plant","Power Plant"));
			this.areas.add(new SelectItem("Tratamento de Água","Raw Water Treatment"));
			this.areas.add(new SelectItem("Pátio de Matéria Prima","Raw Material"));
			this.areas.add(new SelectItem("Sinterização","Sinter Plant"));
			this.areas.add(new SelectItem("Aciaria","Steel Making"));
			this.areas.add(new SelectItem("Centro de Treinamento","Training Center"));
			this.areas.add(new SelectItem("Distribuição e Utilidades","Utility Distribution"));
		}
		return areas;
	}

	public LocalInstalacao getLocalInstalacao() {
		return localInstalacao;
	}

	public void setLocalInstalacao(LocalInstalacao localInstalacao) {
		this.localInstalacao = localInstalacao;
	}
	
	public List<LocalInstalacao> getLocaisLista() {
		if(this.locaisLista == null) {
			this.locaisLista = locais.listar("id");
		}
		return this.locaisLista;
	}

	public LocalInstalacao getLocalSelecionado() {
		return localSelecionado;
	}

	public void setLocalSelecionado(LocalInstalacao localSelecionado) {
		this.localSelecionado = localSelecionado;
	}
	
	public List<LocalInstalacao> getLocaisSelecionados() {
		return locaisSelecionados;
	}
	
	public void setLocaisSelecionados(List<LocalInstalacao> locaisSelecionados) {
		this.locaisSelecionados = locaisSelecionados;
	}

}
