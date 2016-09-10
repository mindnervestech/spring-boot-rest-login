package com.mnt.auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mnt.entities.auth.AuthUser;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	AuthenticationProvider authenticationProvider;
	
	/*@Autowired
	SecurityContextRepository repository;*/

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @Transactional
    public LoginResponse login(@RequestBody final UserLogin login, HttpServletRequest request, HttpServletResponse response)
        throws ServletException {
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.name, 
        		login.password);
        AuthUser details = new AuthUser();
        details.setUsername(login.name);
        token.setDetails(details);
        Authentication auth = authenticationProvider.authenticate(token);
       // SecurityContextHolder.getContext().setAuthentication(auth);
        
        return new LoginResponse(TokenHandler.createTokenForUser(auth));
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;
       
        public LoginResponse(final String token) {
            this.token = token;
            
        }
    }
}