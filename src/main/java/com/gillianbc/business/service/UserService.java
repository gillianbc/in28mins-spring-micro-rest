package com.gillianbc.business.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gillianbc.business.exception.UserNotFoundException;
import com.gillianbc.model.User;
import com.gillianbc.model.UserDao;

@RestController
public class UserService{
	
	
	@Autowired
	UserDao dao;
	
	@GetMapping("/users")
	public List<User> findAll(){
		List<User> users = dao.findAll();
		if (users == null) {
			throw new UserNotFoundException("No users were found");
		}
		return users;
	}
	
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable int id) {
		User user = dao.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("No user was found for id: " + id );
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createuser(@RequestBody User user) {
		User savedUser = dao.save(user);
		// We want to return the uri of the newly created record e.g. /users/56
		// We can get the first part, /users, from the request
		// We add a placeholder to the path:  /users/{id}
		// We can get the id from the user created and sub it into the placeholder /users/56
		// We can build that whole thing as a uri
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		// By default, the status is 200 OK, but best practice is Created 201
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userid}")
	public void deleteUser(@PathVariable int userid){
		dao.delete(userid);
		
	}
	
}
