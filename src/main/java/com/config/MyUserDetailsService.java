package com.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private com.repository.UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User name "+username+" not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),getGrantedAuthorities(user));
	}
	
	private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if(user.getRole().getName().equals("superadmin")) 
			grantedAuthorities.add(new SimpleGrantedAuthority("superadmin"));
		else if(user.getRole().getName().equals("admin")) 
			grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
		else 
			grantedAuthorities.add(new SimpleGrantedAuthority("user"));
		return grantedAuthorities;
	}
}
