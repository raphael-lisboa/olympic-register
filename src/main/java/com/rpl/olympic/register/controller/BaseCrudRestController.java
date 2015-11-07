package com.rpl.olympic.register.controller;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.rpl.olympic.register.service.CrudService;

import java.util.List;

public class BaseCrudRestController<T> implements CrudRestCrontroller<T> {

	private Logger			LOG;

	private CrudService<T>	crudrService;

	public BaseCrudRestController(CrudService<T> userService) {
		this.crudrService = userService;
		LOG = Logger.getRootLogger();
	}

	@Override
	public ResponseEntity<List<T>> listAll() {
		List<T> users = crudrService.listAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<T>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<T>>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<T> get(@PathVariable("id") long id) {
		LOG.debug("Fetching Entity with id " + id);
		T user = crudrService.findById(id);
		if (user == null) {
			LOG.debug("Entity with id " + id + " not found");
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<T>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> post(T user, UriComponentsBuilder ucBuilder) {
		try {
			crudrService.add(user);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(addedEntity.getId()).toUri());
		// return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<T> put(long id, @RequestBody T user) {
		LOG.debug("Updating Entity " + id);

		if (crudrService.findById(id) == null) {
			LOG.debug("Entity with id " + id + " not found");
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
		crudrService.update(user, id);
		return new ResponseEntity<T>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<T> delete(@PathVariable("id") long id) {
		LOG.debug("Fetching & Deleting  with id " + id);

		T user = crudrService.findById(id);
		if (user == null) {
			LOG.debug("Unable to delete. Entitywith id " + id + " not found");
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}

		crudrService.delete(user);
		return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
	}

}