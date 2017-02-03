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
	private LocalInstalacao local;
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
			locais.deletar(this.local.getId());
			FacesUtil.addInfoMessage("Removido com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void salvar() {
		try {
			this.locais.salvar(this.local);
			limpar();
			FacesUtil.addInfoMessage("Salvo com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar salvar " + e.getMessage());
		}
	}
	
	public void atualizar() {
		try {
			this.locais.atualizar(this.local);
			limpar();
			FacesUtil.addInfoMessage("Atualizado com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar atualizar " + e.getMessage());
		}
	}
	
	private void limpar() {
		this.local = new LocalInstalacao();
	}
	
	public boolean isEditando() {
		return this.local.getId() != null;
	}

	public List<SelectItem> getAreas() {
		if (this.areas == null) {
			this.areas = new ArrayList<SelectItem>();
			this.areas.add(new SelectItem("BF","Blast Furnace"));
			this.areas.add(new SelectItem("Coke","Coke Making Plant"));
			this.areas.add(new SelectItem("EC","Energy Center"));
			this.areas.add(new SelectItem("LAB","Laboratório Central"));
			this.areas.add(new SelectItem("RM","Raw Material"));
			this.areas.add(new SelectItem("Sinter","Sinterização"));
			this.areas.add(new SelectItem("SMP","Steel Making"));
		}
		return areas;
	}

	public LocalInstalacao getLocal() {
		return local;
	}

	public void setLocal(LocalInstalacao local) {
		this.local = local;
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
