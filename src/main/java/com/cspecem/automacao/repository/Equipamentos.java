package com.cspecem.automacao.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cspecem.automacao.dao.EquipamentoDAO;
import com.cspecem.automacao.model.Equipamento;
import com.cspecem.automacao.repository.filter.EquipamentoFilter;

public class Equipamentos extends DaoGenerico<Equipamento, Long> implements EquipamentoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Equipamento pesquisarPorNumeroSerie(String numeroSerie) {
		try {
			return em.createQuery("from Equipamento upper(numeroSerie) = :numeroSerie", Equipamento.class)
					.setParameter("numeroSerie", numeroSerie.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipamento> filtrados(EquipamentoFilter filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Equipamento.class);
		
		if (StringUtils.isNotBlank(filtro.getNumeroSerie())) {
			criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
		}
		
		
		if (StringUtils.isNotBlank(filtro.getNumeroSerie())) {
			criteria.add(Restrictions.ilike("numeroSerie", filtro.getNumeroSerie(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("id")).list();
	}

}
