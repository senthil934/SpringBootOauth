package com.pack.SpringBootSSL.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@RequestMapping("/getData")
	public String demo() {
		return "Hello SSL";
	}

}
