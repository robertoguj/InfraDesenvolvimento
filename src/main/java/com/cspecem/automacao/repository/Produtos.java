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

import com.cspecem.automacao.dao.ProdutoDAO;
import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.filter.ProdutoFilter;

public class Produtos extends DaoGenerico<Produto, Long> implements ProdutoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Produto porSku(String sku) {
		try {
			return em.createQuery("from Produto where upper(sku) = :sku", Produto.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if (StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}
		
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("descricao")).list();
	}

	@Override
	public List<Produto> porNome(String descricao) {
		return this.em.createQuery("from Produto where upper(descricao) like :descricao", Produto.class)
				.setParameter("descricao", descricao.toUpperCase() + "%").getResultList();
	}

	
	
}
