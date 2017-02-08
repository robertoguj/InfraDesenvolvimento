package com.cspecem.automacao.repository;

import java.io.Serializable;

import com.cspecem.automacao.dao.MaterialDAO;
import com.cspecem.automacao.model.Material;

public class Materiais extends DaoGenerico<Material, Long> implements MaterialDAO, Serializable {

	private static final long serialVersionUID = 1L;

}
