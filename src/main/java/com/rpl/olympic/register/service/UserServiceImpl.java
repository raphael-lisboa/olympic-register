package com.rpl.olympic.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rpl.olympic.register.controller.UserView;
import com.rpl.olympic.register.converter.UserConverter;
import com.rpl.olympic.register.model.User;
import com.rpl.olympic.register.repository.user.UserDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;

	}

	@Override
	@Transactional
	public UserView add(UserView user) {
		if (userDao.findByEmail(user.getEmail()) != null) {
			throw new DuplicateKeyException("User alredy exist");
		}
		return madeUserView(userDao.add(madeUser(user)));
	}

	@Override
	@Transactional
	public UserView findByEmail(String email) {

		return madeUserView(userDao.findByEmail(email));
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<UserView> listAll() {
		List<UserView> list = new ArrayList();
		userDao.listall().parallelStream().forEach(t -> list.add(madeUserView(t)));
		return list;
	}

	@Override
	@Transactional
	public UserView findById(long id) {
		User user = userDao.findById(id);
		return madeUserView(user);
	}

	@Override
	@Transactional
	public void update(UserView userView) {
		userDao.update(madeUser(userView));

	}

	@Override
	@Transactional
	public void delete(UserView user) {
		userDao.remove(madeUser(user));

	}

	private UserView madeUserView(User user) {
		return UserConverter.convert(user);
	}

	private User madeUser(UserView user) {
		return UserConverter.convert(user);
	}
}
