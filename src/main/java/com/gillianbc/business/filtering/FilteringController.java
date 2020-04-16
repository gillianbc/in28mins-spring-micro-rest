package com.gillianbc.business.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public HaricotBean retrieveHaricotBean() {
		return new HaricotBean("apple", "pear", "banana");
	}
	
	@GetMapping("/filtering/all")
	public List<HaricotBean> retrieveHaricotBeans() {
		return Arrays.asList(new HaricotBean("apple", "pear", "banana"), 
				new HaricotBean("peach", "plum", "arse"));
	}
	
}
