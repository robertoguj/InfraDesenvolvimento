package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Grupo;
import com.cspecem.automacao.model.Usuario;
import com.cspecem.automacao.repository.Grupos;
import com.cspecem.automacao.repository.Usuarios;
import com.cspecem.automacao.service.UsuarioService;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private Usuario usuario;

	@Inject
	private Usuarios usuarios;
	private String confirmarSenha;
	private List<Usuario> usuariosLista;
	private Usuario usuarioSelecionado;
	private List<Usuario> usuariosSelecionados;

	@Inject
	private Grupos grupos;
	private List<Grupo> selectedGrupos;
	private List<SelectItem> selectGrupos;
	
	@Inject
	private UsuarioService usuarioService;

	public UsuarioBean() {
		limpar();
	}

	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			carregarGrupos();
		}
		System.out.println("inicializacao");
	}

	public List<Usuario> getUsuariosLista() {
		if (this.usuariosLista == null) {
			this.usuariosLista = usuarios.listar("id");
		}
		return usuariosLista;
	}

	public String salvar() {

		String senha = this.usuario.getSenha();
		try {
			if (!senha.equals(this.confirmarSenha)) {
				FacesUtil.addErrorMessage("A senha não foi confirmada corretamente.");
				return null;
			}
			this.usuario.setAtivo(true);

			Date dataAtual = new Date(System.currentTimeMillis());
			this.usuario.setCadastro(dataAtual);

			this.usuarioService.salvarUsuario(usuario);
			FacesUtil.addInfoMessage("Usuário salvo com sucesso!");

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Ocorreu um erro ao salvar usuário. " + e.getMessage());
		}
		return "UsuarioSucesso";
	}
	
	public void excluir() {
		try {
			this.usuarios.deletar(usuario.getId());
			FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome() + " removido com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Ocorreu um erro ao tentar excluir : " + e.getMessage());
		}
	}

	public void atualizar() {
		try {
			this.usuario = usuarios.atualizar(usuario);
			FacesUtil.addInfoMessage("Salvo com sucesso! ");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar salvar usuário " + e.getMessage());
		}
	}

	public void carregarGrupos() {
		try {
			selectGrupos = new ArrayList<SelectItem>();
			List<Grupo> list = grupos.listar("id");

			if (list != null && !list.isEmpty()) {
				//selectGrupos.add(new SelectItem(null, "Selecione o perfil"));
				for (Grupo grupo : list) {
					selectGrupos.add(new SelectItem(grupo, grupo.getDescricao()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Ocorreu um erro ao carregar lista " + e.getMessage());

		}
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public void limpar() {
		this.usuario = new Usuario();
	}

	public void desabilitar() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public List<SelectItem> getSelectGrupos() {
		return selectGrupos;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

	public List<Grupo> getSelectedGrupos() {
		return selectedGrupos;
	}

	public void setSelectedGrupos(List<Grupo> selectedGrupos) {
		this.selectedGrupos = selectedGrupos;
	}

}
