package com.rpl.olympic.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface CrudRestCrontroller<T> {

	ResponseEntity<List<T>> listAll();

	ResponseEntity<T> get(long id);

	ResponseEntity<Void> post(T user, UriComponentsBuilder ucBuilder);

	ResponseEntity<T> put(long id, T user);

	ResponseEntity<T> delete(long id);

}