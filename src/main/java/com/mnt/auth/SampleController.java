package com.mnt.auth;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mnt.entities.auth.AuthUser;
import com.mnt.entities.auth.Role;

@RestController
@RequestMapping("/sample")
public class SampleController {
	
	
	/*@Autowired
	SecurityContextRepository repository;*/

    @RequestMapping(value = "roles", method = RequestMethod.POST)
    @Transactional
    public String login( HttpServletRequest request, HttpServletResponse response)
        throws ServletException {
    	AuthUser user = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getDetails();
    	//user.getAuthorities();
    	//user.getPermissions();
    	List<Role> roles = user.getRoles();
    	String ret="Roles:";
    	for(Role r : roles){
    		ret = ret + "" + r.name;
    		
    	};
        return ret;
    }

    
}