package com.MiniProject.CourseManager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.MiniProject.CourseManager.Repository.UserRepo;

@Service
public class UserDetailsServiecImpl implements UserDetailsService{
	
	@Autowired
	private UserRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.MiniProject.CourseManager.Entity.User userDetails = userrepo.findByUserName(username);
		if(userDetails != null) {
			return org.springframework.security.core.userdetails.User.builder()
					.username(userDetails.getUserName())
					.password(userDetails.getPassWord())
					.build();
		}
		
		throw new UsernameNotFoundException("user not found with username"+username);
	}
 
	
}
