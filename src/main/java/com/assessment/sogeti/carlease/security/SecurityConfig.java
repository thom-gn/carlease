package com.assessment.sogeti.carlease.security;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.assessment.sogeti.carlease.security.TokenFilter;
import com.assessment.sogeti.carlease.service.TokenService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private TokenService tokenService; // Inject TokenService here

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2AuthorizedClientService authorizedClientService) throws Exception {
        AntPathRequestMatcher apiRequestMatcher = new AntPathRequestMatcher("/api/**");

    	http
        .csrf(csrf -> csrf.disable())

        	.authorizeHttpRequests(authorizeRequests ->
        	authorizeRequests
            .requestMatchers("/api/**").authenticated()  // Authentication required for /api/**
        	.requestMatchers("/cars/**", "/customers/**","/carleases/**").authenticated()
            .anyRequest().authenticated()
        	)
            .oauth2Login(oauth2Login ->
                oauth2Login
                .successHandler(customSuccessHandler(authorizedClientService))
            )	            
            .addFilterBefore(new TokenFilter(tokenService,apiRequestMatcher), UsernamePasswordAuthenticationFilter.class) ;

        return http.build();
    }
    
    
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler(OAuth2AuthorizedClientService authorizedClientService) {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
                String clientRegistrationId = oauth2Token.getAuthorizedClientRegistrationId();

                OAuth2AuthorizedClient authorizedClient = authorizedClientService
                        .loadAuthorizedClient(clientRegistrationId, oauth2Token.getName());

                if (authorizedClient != null) {

                    String accessTokenValue = authorizedClient.getAccessToken().getTokenValue();
                    Instant expiresAt = authorizedClient.getAccessToken().getExpiresAt();                    
                    ZonedDateTime zonedDateTime = expiresAt.atZone(ZoneId.systemDefault());
                   
                    // Store access token in response headers or handle it as needed
                    response.setHeader("X-Access-Token", accessTokenValue);
                    
                    request.getSession().setAttribute("access_token", accessTokenValue);
                    request.getSession().setAttribute("expired_at",  zonedDateTime.toLocalDateTime().plusHours(1).toString());

                    tokenService.createAccessToken(accessTokenValue, zonedDateTime.toLocalDateTime());
                }

                // Redirect to home page after successful login
                getRedirectStrategy().sendRedirect(request, response, "/home");
            }
        };
    }
}
