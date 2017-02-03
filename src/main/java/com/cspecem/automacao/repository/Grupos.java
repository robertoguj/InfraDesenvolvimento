package com.cspecem.automacao.repository;

import java.io.Serializable;

import com.cspecem.automacao.dao.GrupoDAO;
import com.cspecem.automacao.model.Grupo;

public class Grupos extends DaoGenerico<Grupo, Long> implements GrupoDAO, Serializable {

	private static final long serialVersionUID = 1L;

}
