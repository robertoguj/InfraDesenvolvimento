package com.cspecem.automacao.repository;

import java.io.Serializable;

import com.cspecem.automacao.dao.EquipamentoDAO;
import com.cspecem.automacao.model.Equipamento;

public class Equipamentos extends DaoGenerico<Equipamento, Long> implements EquipamentoDAO, Serializable {

	private static final long serialVersionUID = 1L;

}
