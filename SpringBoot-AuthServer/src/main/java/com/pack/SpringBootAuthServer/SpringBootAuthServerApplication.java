package com.pack.SpringBootAuthServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@ComponentScan(basePackages="com.pack")
public class SpringBootAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAuthServerApplication.class, args);
	}

}
