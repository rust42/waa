package edu.miu.springsecurityinclass.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.springsecurityinclass.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;

@Component
@Data
public class MyUserDetails implements UserDetails {
    private String email;

    @JsonIgnore
    private String password;

//    private List<Role> roles;

    public MyUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
//        this.roles = user.get
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
