package com.waa.alumni_portal_service.security;

import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final MyUserDetailsService userDetailsService;

    public synchronized MyUserDetails getCurrentUser(){
       return Optional.ofNullable(SecurityContextHolder.getContext())
                .map( context -> {
                    if(context.getAuthentication() instanceof UsernamePasswordAuthenticationToken authToken){
                        return (MyUserDetails) authToken.getPrincipal();
                    }else if(context.getAuthentication() instanceof KeycloakAuthenticationToken keycloakAuthToken){
                        KeycloakPrincipal keycloakPrincipal = ((KeycloakPrincipal)keycloakAuthToken.getPrincipal());
                        String email = keycloakPrincipal.getKeycloakSecurityContext().getToken().getEmail();

                        MyUserDetails userDetails = userDetailsService.loadUserByUsername(email);
                        if(userDetails == null)
                            userDetails = userDetailsService.cloneNewUser(keycloakAuthToken);
                        return userDetails;
                    }else{
                        return null;
                    }
                })
                .orElse(null);
    }
}
