package com.rpl.olympic.register.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDAO<T extends Serializable> implements DAO<T> {

	private SessionFactory	sessionFactory;
	private Class<T>		persistentClass;

	public AbstractHibernateDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

		this.persistentClass = (Class<T>) ((ParameterizedType) this	.getClass()
																	.getGenericSuperclass()).getActualTypeArguments()[0];

	}

	@Override
	public T add(T t) {
		sessionFactory.getCurrentSession().save(t);
		return t;
	}

	@Override
	public T findById(Long id) {
		return sessionFactory.getCurrentSession().load(persistentClass, id);

	}

	@Override
	public T update(T t) {
		sessionFactory.getCurrentSession().update(t);
		return t;
	}

	@Override
	public void remove(T t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public void remove(T t, Long id) {
		sessionFactory.getCurrentSession().delete(persistentClass.getName(), id);
	}

	protected Criteria createEntityCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(persistentClass);
	}
}