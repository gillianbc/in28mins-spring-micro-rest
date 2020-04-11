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

import com.gillianbc.business.exception.PostNotFoundException;
import com.gillianbc.model.Post;
import com.gillianbc.model.PostDao;

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
	
	@PostMapping("/users/{userid}/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post, @PathVariable int userid) {
		post.setUserId(userid);
		Post savedPost = dao.save(post);
		// We want to return the uri of the newly created record e.g. /users/56/post/3
		// We can get the first part, /users/56/post, from the request
		// We add a placeholder to the path:  /users/56/post/{postid}
		// We can get the id from the post created and sub it into the placeholder /users/56/post/3
		// We can build that whole thing as a uri
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedPost.getId())
		.toUri();
		// By default, the status is 200 OK, but best practice is Created 201
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userid}/posts/{postid}")
	public void deletePost(@PathVariable int userid, @PathVariable int postid){
		dao.delete(userid, postid);
		
	}

}
