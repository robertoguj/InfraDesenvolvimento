package com.cspecem.automacao.repository;

import java.io.Serializable;

import javax.persistence.NoResultException;

import com.cspecem.automacao.dao.UsuarioDAO;
import com.cspecem.automacao.model.Usuario;
import com.cspecem.automacao.util.jsf.FacesUtil;

public class Usuarios extends DaoGenerico<Usuario, Long> implements UsuarioDAO, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Usuario porEmail(String email) {
		Usuario usuario = null;
		
		try {
			usuario = this.em.createQuery("from Usuario where lower(email) = :email", Usuario.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			FacesUtil.addErrorMessage("Nenhum usuário encontrado com o e-mail " + email + " informado.");
		}
		
		return usuario;
	}
	
}