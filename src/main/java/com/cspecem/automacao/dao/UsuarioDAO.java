package com.cspecem.automacao.dao;

import com.cspecem.automacao.model.Usuario;

public interface UsuarioDAO extends Generico<Usuario, Long> {
	
	public Usuario porEmail(String email);

}
