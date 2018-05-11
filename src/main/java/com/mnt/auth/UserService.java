package com.mnt.auth;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mnt.auth.repository.UserRepository;
import com.mnt.entities.auth.AuthUser;
import com.mnt.entities.auth.PermissionMatrix;
import com.mnt.entities.auth.Role;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UserRepository userRepository;
    
    @Override
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	AuthUser user = null;
    	try {
    		  user = userRepository.findByUsername(username).orElse(null);
    		  List<PermissionMatrix> permissions = new ArrayList<>();
    		  for(Role role : user.getRoles()) {
    			  permissions.addAll(role.getPermissionMatrix());
    		  } 
    		  user.setPermissions(permissions);
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        
        return user;
    }

}