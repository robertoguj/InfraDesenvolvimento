package com.cspecem.automacao.repository;

import java.io.Serializable;
import java.util.List;

import com.cspecem.automacao.dao.CategoriaDAO;
import com.cspecem.automacao.model.Categoria;

public class Categorias extends DaoGenerico<Categoria, Long> implements CategoriaDAO, Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<Categoria> raizes() {
		return em.createQuery("from Categoria where categoria is null", 
				Categoria.class).getResultList();
	}
	
	public List<Categoria> subcategorias(Categoria categoria) {
		return em.createQuery("from Categoria where categoria = :raiz", 
				Categoria.class).setParameter("raiz", categoria).getResultList();
	}

}
