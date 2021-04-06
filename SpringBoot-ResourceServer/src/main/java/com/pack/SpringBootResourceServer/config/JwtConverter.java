package com.pack.SpringBootResourceServer.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import com.pack.SpringBootResourceServer.model.AccessTokenMapper;

@Component
public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

	@Override
	public void configure(JwtAccessTokenConverter converter) {
		converter.setAccessTokenConverter(this);
	}

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		OAuth2Authentication auth = super.extractAuthentication(map);
		AccessTokenMapper details = new AccessTokenMapper();

		//if (map.get("id") != null)
			//details.setId( );

		if (map.get("userName") != null)
			details.setUserName((String) map.get("userName"));

		if (map.get("name") != null)
			details.setName((String) map.get("name"));

		auth.setDetails(details);
		return auth;
	}

}