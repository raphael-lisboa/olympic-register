package com.rpl.olympic.register.service;

import java.util.List;

public interface CrudService<T> {

	T add(T user);

	T findByEmail(String email);

	List<T> listAll();

	T findById(long id);

	void update(T currentUser, long id);

	void delete(T user);

}