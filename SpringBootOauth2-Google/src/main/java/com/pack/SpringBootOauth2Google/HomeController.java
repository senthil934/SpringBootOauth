package com.pack.SpringBootOauth2Google;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "Any user can access this!!";
	}
	
	@GetMapping("/prevent")
	public Principal prevent(Principal principal) {
		return principal; //returns all info abt authenticated user
	}


}
