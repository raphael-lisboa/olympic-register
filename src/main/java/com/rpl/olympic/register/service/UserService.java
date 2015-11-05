package com.rpl.olympic.register.service;

import com.rpl.olympic.register.controller.UserView;

import java.util.List;

public interface UserService {

	UserView add(UserView user);

	UserView findByEmail(String email);

	List<UserView> listAll();

	UserView findById(long id);

	void update(UserView currentUser);

	void delete(UserView user);

}