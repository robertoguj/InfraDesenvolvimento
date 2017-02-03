package com.cspecem.automacao.repository;

import java.io.Serializable;

import com.cspecem.automacao.dao.LocalInstalacaoDAO;
import com.cspecem.automacao.model.LocalInstalacao;

public class LocaisInstalacao extends DaoGenerico<LocalInstalacao, Long> implements LocalInstalacaoDAO, Serializable {

	private static final long serialVersionUID = 1L;

}
