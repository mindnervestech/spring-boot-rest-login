package com.mnt.auth;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mnt.entities.auth.AuthUser;
import com.mnt.entities.auth.PermissionMatrix;
import com.mnt.entities.auth.Role;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	AuthUser user = null;
    	Session session = null;
    	try {
    	  try {
    		  
    		  session = sessionFactory.getCurrentSession();
    		  user = (AuthUser) session.createQuery("from AuthUser where username = :username").setString("username", username).uniqueResult();
    		  List<PermissionMatrix> permissions = new ArrayList<>();
    		  for(Role role : user.getRoles()) {
    			  permissions.addAll(role.getPermissionMatrix());
    		  } 
    		  user.setPermissions(permissions);
    		 
    	  } catch(org.hibernate.HibernateException ex) {
    		  try {
    			  session = sessionFactory.openSession();
    			  user = (AuthUser) session.createQuery("from AuthUser where username = :username").setString("username", username).uniqueResult();
    		  } finally {
    	    		session.close();
    	    	}
    	  }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        
        return user;
    }

}