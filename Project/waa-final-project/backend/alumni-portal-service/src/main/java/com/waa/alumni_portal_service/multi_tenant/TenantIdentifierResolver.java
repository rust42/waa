package com.waa.alumni_portal_service.multi_tenant;

import com.waa.alumni_portal_service.security.MyUserDetails;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Value("${multi-tenant.default_tenant}")
    private String DEFAULT_TENANT;
    @Autowired
    private TenantConfig tenantConfig;

    public String getDefaultTenant(){
        return DEFAULT_TENANT;
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map( context -> {
                    String tenant = DEFAULT_TENANT;
                    if(context.getAuthentication() instanceof KeycloakAuthenticationToken keycloakAuthToken){
                        KeycloakPrincipal keycloakPrincipal = ((KeycloakPrincipal)keycloakAuthToken.getPrincipal());
                        String email = keycloakPrincipal.getKeycloakSecurityContext().getToken().getEmail();
                        String domain = email.split("@")[1];
                        if(tenantConfig.map.containsKey(domain)){
                            tenant = tenantConfig.map.get(domain);
                        }
                    }
                    return tenant;
                })
                .orElse(DEFAULT_TENANT);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
