package com.rpl.olympic.register.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rpl.olympic.register.service.UserService;

import java.util.List;

@RestController
public class UserRestController {

	private static final Logger	LOG	= Logger.getLogger(UserRestController.class);

	private UserService			userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;

	}

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<UserView>> listAllUsers() {
		List<UserView> users = userService.listAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserView>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserView> getUser(@PathVariable("id") long id) {
		LOG.debug("Fetching User with id " + id);
		UserView user = userService.findById(id);
		if (user == null) {
			LOG.debug("User with id " + id + " not found");
			return new ResponseEntity<UserView>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserView>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createUser(@RequestBody UserView user, UriComponentsBuilder ucBuilder) {
		LOG.debug("Creating User " + user.getName());
		UserView addedUser = null;
		try {
			addedUser = userService.add(user);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(addedUser.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserView> updateUser(@PathVariable("id") long id, @RequestBody UserView user) {
		LOG.debug("Updating User " + id);

		if (userService.findById(id) == null) {
			LOG.debug("User with id " + id + " not found");
			return new ResponseEntity<UserView>(HttpStatus.NOT_FOUND);
		}
		user.setId(id);
		userService.update(user);
		return new ResponseEntity<UserView>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserView> deleteUser(@PathVariable("id") long id) {
		LOG.debug("Fetching & Deleting User with id " + id);

		UserView user = userService.findById(id);
		if (user == null) {
			LOG.debug("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<UserView>(HttpStatus.NOT_FOUND);
		}

		userService.delete(user);
		return new ResponseEntity<UserView>(HttpStatus.NO_CONTENT);
	}

}