package com.tymchak.DAO.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.tymchak.DAO.GenericDAO;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	private Class<T> type;

	@PersistenceContext
	protected EntityManager em;

	protected GenericDAOImpl(Class<T> type) {
		this.type = type;
	}

	public void create(T newInstance) {
		em.persist(newInstance);
	}

	public T read(int id) {
		return em.find(type, id);
	}

	public void update(T transientObject) {
		em.merge(transientObject);
	}

	public void delete(T persistentObject) {
		em.remove(em.merge(persistentObject));
	}

	public List<T> getAll() {
		TypedQuery<T> query = em.createQuery(
				"SELECT s FROM " + type.getSimpleName() + " s", type);
		List<T> list = query.getResultList();
		return list;
	}

	public int count() {
		return getAll().size();
	}

}
