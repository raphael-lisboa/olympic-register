package com.rpl.olympic.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rpl.olympic.register.service.CrudService;

import java.util.List;

@RestController
public class UserRestController implements CrudRestCrontroller<UserView> {

	private CrudRestCrontroller<UserView> restCrontroller;

	@Autowired
	public UserRestController(CrudService<UserView> userService) {
		this.restCrontroller = new BaseCrudRestController<>(userService);
	}

	@Override
	@RequestMapping(value = "/user/",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserView>> listAll() {
		return restCrontroller.listAll();
	}

	@Override
	@RequestMapping(value = "/user/{id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserView> get(long id) {
		return restCrontroller.get(id);
	}

	@Override
	@RequestMapping(value = "/user/",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> post(@RequestBody UserView user, UriComponentsBuilder ucBuilder) {
		return restCrontroller.post(user, ucBuilder);
	}

	@Override
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserView> put(@PathVariable("id") long id, @RequestBody UserView user) {
		return restCrontroller.put(id, user);
	}

	@Override
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserView> delete(long id) {
		return restCrontroller.delete(id);
	}

}
