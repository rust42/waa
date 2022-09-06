package com.waa.alumni_portal_service.security;

import com.waa.alumni_portal_service.entity.Faculty;
import com.waa.alumni_portal_service.entity.Role;
import com.waa.alumni_portal_service.entity.Student;
import com.waa.alumni_portal_service.entity.User;
import com.waa.alumni_portal_service.repository.RoleRepository;
import com.waa.alumni_portal_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    private final RoleRepository roleRepository;

    @Override
    public synchronized MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepo.findByEmail(email))
                .map( user -> new MyUserDetails(user))
                .orElse(null);
    }

    public MyUserDetails cloneNewUser(KeycloakAuthenticationToken keycloakAuthToken){
        AccessToken accessToken = ((KeycloakPrincipal)keycloakAuthToken.getPrincipal()).getKeycloakSecurityContext().getToken();
        User newUser = null;
        if(isFaculty(accessToken)){
            newUser = new Faculty();
        }else{
            newUser = new Student();
        }
        newUser.setEmail(accessToken.getEmail());
        newUser.setFirstName(accessToken.getGivenName());
        newUser.setLastName(accessToken.getFamilyName());
        newUser.setExternalId(accessToken.getId());
        newUser.setExternalType("KEY_CLOAK");
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach( role -> {
            if(accessToken.getRealmAccess().getRoles().contains(role.getRole()))
                roles.add(role);
        });

        User savedUser = userRepo.save(newUser);
        return new MyUserDetails(savedUser);
    }

    private int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    private boolean isFaculty(AccessToken accessToken){
        return accessToken.getRealmAccess().getRoles().contains("FACULTY");
    }

}
