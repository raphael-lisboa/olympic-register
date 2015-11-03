package com.rpl.olympic.register.service;

import com.rpl.olympic.register.controller.UserView;
import com.rpl.olympic.register.model.User;

import java.util.List;

public interface UserService {

	UserView add(User user);

	UserView findByEmail(String email);

	List<UserView> listAll();

	UserView findById(long id);

	void update(User currentUser);

	void delete(User user);

}