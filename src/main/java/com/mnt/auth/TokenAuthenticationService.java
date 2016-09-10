package com.mnt.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final TokenHandler tokenHandler;

    @Autowired
    public TokenAuthenticationService(UserService userService) {
        tokenHandler = new TokenHandler(userService);
    }

   /* public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final UserDetails user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }*/

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
        	String permission = TokenHandler.getPermissions(token);
        	String requestedObject = request.getMethod() + ":" + request.getRequestURI();
        	
        	if(permission.contains(requestedObject + ":" + 0)) {
        		return null;
        	} else {
        		final UserDetails user = tokenHandler.parseUserFromToken(token);
        		if (user != null) {
        			return new UserAuthentication(user);
        		}
        	}
        }
        return null;
    }
}

