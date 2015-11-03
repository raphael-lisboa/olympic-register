package com.rpl.olympic.register.repository.user.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rpl.olympic.register.model.User;
import com.rpl.olympic.register.repository.AbstractHibernateDAO;
import com.rpl.olympic.register.repository.user.UserDao;

import java.util.Collection;

@Repository("userDao")
public class UserHibernateDao extends AbstractHibernateDAO<User> implements UserDao {

	@Autowired
	public UserHibernateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User findByEmail(String email) {
		return (User) createEntityCriteria()
											.add(Restrictions.eq("email", email))
											.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> listall() {
		return createEntityCriteria().list();

	}

}
