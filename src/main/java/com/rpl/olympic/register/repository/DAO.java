package com.rpl.olympic.register.repository;

import java.io.Serializable;

public interface DAO<T extends Serializable> {

	T add(T t);

	T findById(Long id);

	T update(T t);

	void remove(T t);

	void remove(T t, Long id);

}