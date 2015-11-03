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

import com.rpl.olympic.register.model.User;
import com.rpl.olympic.register.service.UserService;

import java.util.List;

@RestController
public class UserRestController {

	private static final Logger	LOG	= Logger.getLogger(UserRestController.class);

	@Autowired
	private UserService			userService;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<UserView>> listAllUsers() {
		List<UserView> users = userService.listAll();
		if (users.isEmpty()) { return new ResponseEntity<List<UserView>>(HttpStatus.NO_CONTENT); }
		return new ResponseEntity<List<UserView>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserView> getUser(@PathVariable("id") long id) {
		LOG.debug("Fetching User with id " + id);
		UserView user = userService.findById(id);
		if (user == null) {
			LOG.debug("User with id " + id + " not found");
			return new ResponseEntity<UserView>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserView>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/str", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" },
					produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Void> add(@RequestBody String str, UriComponentsBuilder ucBuilder) {
		LOG.debug(str);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createUser(@RequestBody UserView user, UriComponentsBuilder ucBuilder) {
		LOG.debug("Creating User " + user.getName());
		UserView addedUser = null;
		try {
			addedUser = userService.add(new User(null, user.getName(), user.getEmail(), user.getNickname()));
		} catch (DuplicateKeyException e) {
			new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(addedUser.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody UserView user) {
		LOG.debug("Updating User " + id);

		if (userService.findById(id) == null) {
			LOG.debug("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		User updatedUser = new User(id, user.getName(), user.getEmail(), user.getNickname());

		userService.update(updatedUser);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	// ------------------- Delete a User
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		LOG.debug("Fetching & Deleting User with id " + id);

		UserView user = userService.findById(id);
		if (user == null) {
			LOG.debug("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.delete(new User());
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users
	// --------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		LOG.debug("Deleting All Users");

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}