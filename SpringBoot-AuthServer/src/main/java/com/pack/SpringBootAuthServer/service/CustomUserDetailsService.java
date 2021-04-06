package com.pack.SpringBootAuthServer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.pack.SpringBootAuthServer.dao.UserEntityRepository;
import com.pack.SpringBootAuthServer.model.CustomUser;
import com.pack.SpringBootAuthServer.model.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserEntityRepository userEntityRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = null;
		CustomUser customUser = null;

		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		
		List<UserEntity> list=userEntityRepo.getListUserEntities(username);
		
        if(!list.isEmpty()) {
			
			userEntity = list.get(0);
			
			List<String> permissionList = userEntityRepo.getListOfUserPermission(username);
			
			List<String> perList=new ArrayList<>();
			for(String plist:permissionList) {
				String permlist = "ROLE_" + plist;
				perList.add(permlist);
			}
			
			if (perList != null && !perList.isEmpty()) {
				for (String permission : perList) {
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
					grantedAuthoritiesList.add(grantedAuthority);
				}
				userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
			}
			
			
		}
        
        try {
			

			if (userEntity != null && userEntity.getId() != null) {
				 customUser = new CustomUser(userEntity);
				
			} else {
				throw new UsernameNotFoundException("User " + username + " was not found in the database");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
        return customUser;
	}

}