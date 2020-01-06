package com.gillianbc.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gillianbc.model.User;
import com.gillianbc.model.UserDao;

@RestController
public class UserService{
	
	@Autowired
	UserDao dao;
	
	@GetMapping("/users")
	public List<User> findAll(){
		return dao.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable int id) {
		return dao.findOne(id);
	}
	
	@PostMapping("/users")
	public void createuser(@RequestBody User user) {
		dao.save(user);
	}
	
}
