package com.gillianbc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gillianbc.business.exception.InvalidUserException;

@Component
public class UserDao {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		users.add(new User(1,"Adam", new Date()));
		users.add(new User(2,"Eve", new Date()));
		users.add(new User(3,"Jacob", new Date()));
	}
	
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user) {
		int id = 0;
		
		try {
			 id = user.getId();
			
		} catch (Exception e) {
			user.setId(userCount + 1);
		}
		
		if (duplicateUser(id)) {
			throw new InvalidUserException("There is already a user with this id");
		}
		
		++userCount;
		
		users.add(user);
		return user;
	}
	
	private boolean duplicateUser(int id) {
		System.out.println("THE ID IS "+ id);
		long count = users.stream()
		.filter(user -> user.getId() == id)
		.count();
		System.out.println("THE COUNT IS " + count);
		return count > 0;
	}
}
