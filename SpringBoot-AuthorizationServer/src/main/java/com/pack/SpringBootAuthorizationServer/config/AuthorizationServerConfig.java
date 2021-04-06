package com.pack.SpringBootAuthorizationServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer //act like authorization server
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		//credentials from resource owner to client appl using "password"
		/*.withClient("abc.com").secret("abc123")
		.scopes("read")
		.authorizedGrantTypes("password")*/
		
		.withClient("abc.com").secret("abc123")
		.scopes("read")
		.authorizedGrantTypes("password","refresh_token")
		.and()
		
		/*.withClient("xyz.com").secret("abc12")
		.scopes("read")
		.authorizedGrantTypes("password");*/
		
		//credentials from resource owner to auth server using authorization_code
		.withClient("xyz.com").secret("abc12")
		.scopes("read")
		.authorizedGrantTypes("authorization_code")
		.redirectUris("http://localhost:8083")
		.and()
		.withClient("pqr.com").secret("abcd")
		.scopes("info")
		.authorizedGrantTypes("client_credentials");
	}
	
    @Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

}

