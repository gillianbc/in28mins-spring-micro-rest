package com.gillianbc.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gillianbc.business.exception.PostNotFoundException;
import com.gillianbc.business.exception.UserNotFoundException;
import com.gillianbc.model.Post;
import com.gillianbc.model.PostDao;
import com.gillianbc.model.User;

@RestController
public class PostService {
	
	@Autowired
	PostDao dao;
	
	// Just for me, not restful
	@GetMapping("/posts")
	public List<Post> findAllPosts(){
		List<Post> posts = dao.findAll();
		if (posts == null) {
			throw new PostNotFoundException("No posts were found");
		}
		return posts;
	}
	
	@GetMapping("/users/{userid}/posts")
	public List<Post> findAllByUser(@PathVariable int userid){
		List<Post> posts = dao.findAllByUser(userid);
		if (posts == null) {
			throw new PostNotFoundException("No posts were found for that user");
		}
		return posts;
	}
	
	@GetMapping("/users/{userid}/posts/{postid}")
	public Post findOne(@PathVariable int userid, @PathVariable int postid) {
		Post post = dao.findOne(postid);
		
		if (post == null) {
			throw new PostNotFoundException("No post was found for id: " + postid );
		}
		if (post.getUserId() != userid) {
			throw new PostNotFoundException("The user of the post is not " + userid);
		}
		System.out.println("POST: " + post.toString());
		return post;
	}
	
	//TODO Post a Post

}
