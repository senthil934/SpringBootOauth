package com.pack.SpringBootJWT.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pack.SpringBootJWT.helper.JwtUtil;
import com.pack.SpringBootJWT.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		
		//Get JWT Header
		String requestTokenHeader = req.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		//From requestTokenHeader we took username and jwtToken
		String username = null;
		String jwtToken = null;
		
		//Now we check for null value and format
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
					
					//we get the token from 7th position
					jwtToken = requestTokenHeader.substring(7);
					System.out.println("Inside Filter "+jwtToken);
					//we get username from JwtUtil
					try {
						//username=this.jwtUtil.extractUsername(jwtToken);
						username=this.jwtUtil.getUsernameFromToken(jwtToken);
						System.out.println(username);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					
					//Getting userdetails for username
					UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
					
					//Validate the token
					if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
						//Got userdetails
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					
					    //set details to usernamepasswordauthentication
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
						
						//Now set these details in SecurityContext
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
					else {
						System.out.println("Token is not validated.");
					}
					
				}
		
		filterChain.doFilter(req, res);
	}

}
