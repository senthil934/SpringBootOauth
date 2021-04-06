package com.pack.SpringBootJWT.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/welcome")
	public String welcome() {
		String text="This is a private page. ";
		text += "This page is not allowed to unauthenticate users";
		return text;
	}
	
	@RequestMapping("/getUsers")
	public String getUser() {
		return "The user is Senthil";
	}
}
