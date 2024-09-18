package com.assessment.sogeti.carlease.security;

import com.assessment.sogeti.carlease.service.TokenService;
import com.assessment.sogeti.carlease.data.AccessToken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class TokenFilter extends OncePerRequestFilter  {

	   private final TokenService tokenService;
	   private final RequestMatcher apiRequestMatcher;


	    public TokenFilter(TokenService tokenService, RequestMatcher apiRequestMatcher) {
	        this.tokenService = tokenService;
	        this.apiRequestMatcher = apiRequestMatcher;
	    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
        if (!apiRequestMatcher.matches(request)) {
            String token = (String) request.getSession().getAttribute("access_token");
            AccessToken storedToken = tokenService.getTokenFromStore(token);

            if (storedToken != null && tokenService.isTokenValid(storedToken)) {
                chain.doFilter(request, response); 
                return;
            } else {
            	//invalidate session so dat user retrieves new access token due to re-authenticate
                request.getSession().invalidate();
            }
        }

        String accessToken = request.getHeader("Authorization");            
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7); // Remove "Bearer " prefix

            AccessToken storedToken = tokenService.getTokenFromStore(accessToken);
            if (storedToken != null && tokenService.isTokenValid(storedToken)) {
                // Token is valid, proceed with the request
                // Set the authentication in the Spring Security context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        storedToken, null, Collections.emptyList()); // No authorities here, you can add them if needed
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
                return;
            } else {
                // Token is invalid or expired, deny access
            	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            }

        } else {
            // Token is not present in the request
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing");
        }
    }
    
   
}
