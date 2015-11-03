package com.rpl.olympic.register.repository.user;

import com.rpl.olympic.register.model.User;
import com.rpl.olympic.register.repository.DAO;

import java.util.Collection;

public interface UserDao extends DAO<User> {

	User findByEmail(String email);

	Collection<User> listall();
}
