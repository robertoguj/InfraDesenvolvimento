package com.cspecem.automacao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Integer matricula = null;
	private String email;
	private String senha;
	private Date cadastro;
	private boolean ativo;
	private List<Grupo> grupos;
	
	@Id
	@GeneratedValue
	@Column(name="usuario_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, length=80)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(nullable = false, length=6)
	public Integer getMatricula() {
		return matricula;
	}
	
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	@Column(nullable = false, unique=true, length=60)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(nullable = false, length=20)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCadastro() {
		return cadastro;
	}
	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usuario_grupo", joinColumns=@JoinColumn(name="usuario_id"),
			inverseJoinColumns=@JoinColumn(name="grupo_id"))
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((cadastro == null) ? 0 : cadastro.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((grupos == null) ? 0 : grupos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (cadastro == null) {
			if (other.cadastro != null)
				return false;
		} else if (!cadastro.equals(other.cadastro))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (grupos == null) {
			if (other.grupos != null)
				return false;
		} else if (!grupos.equals(other.grupos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}


}