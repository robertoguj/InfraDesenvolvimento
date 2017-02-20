package com.cspecem.automacao.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.cspecem.automacao.dao.Generico;

public abstract class DaoGenerico<T, ID extends Serializable> implements Generico<T, ID> {

	@Inject
	protected EntityManager em;

	private final Class<T> classePersistence;

	@SuppressWarnings("unchecked")
	public DaoGenerico() {
		this.classePersistence = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T salvar(T object) {
		EntityTransaction transacao = em.getTransaction();
		transacao.begin();
		em.persist(object);
		transacao.commit();
		em.close();
		return null;
	}

	@Override
	public T atualizar(T object) {
		EntityTransaction transacao = em.getTransaction();
		transacao.begin();
		em.merge(object);
		transacao.commit();
		em.close();
		return null;
	}

	@Override
	public void deletar(ID id) {
		EntityTransaction transacao = em.getTransaction();
		transacao.begin();
		T object = em.find(getClassePersistence(), id);
		em.remove(object);
		transacao.commit();
		em.close();
	}
	
	@Override
	public T porId(ID id) {
		return em.find(getClassePersistence(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> todos() {
		Query q = em.createQuery("FROM " + getClassePersistence().getName());
	    return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar(String value) {
		StringBuffer queryS = new StringBuffer("SELECT obj FROM " + classePersistence.getSimpleName() + " obj ");
		if (value != null) {
			queryS.append("order by " + value + " asc");
		}

		Query query = em.createQuery(queryS.toString());
		return query.getResultList();
	}
	
	protected Session getSession() {
		return em.unwrap(Session.class);
	}
	
	public Class<T> getClassePersistence() {
		return classePersistence;
	}
	
	protected final Criteria criarCriteria() {
		Session session = (Session) em.getDelegate();
		return session.createCriteria(getClassePersistence());
	}


}
