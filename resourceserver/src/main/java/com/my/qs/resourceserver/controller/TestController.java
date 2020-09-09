package com.my.qs.resourceserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/test")
	public ResponseEntity<User> test(){
		Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(principal);
		return ResponseEntity.ok(null);
	}
}
