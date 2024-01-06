package org.innotice.security.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.innotice.security.enums.InternalService;
import org.innotice.security.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InternalAuthentication implements Authentication {

    private InternalService internalService;
    private String secret;
    private boolean isAuthenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(Role.ADMIN.grantedAuthority());
    }

    @Override
    public Object getCredentials() {
        return secret;
    }

    @Override
    public Object getDetails() {
        return internalService;
    }

    @Override
    public Object getPrincipal() {
        return secret;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return internalService.getName();
    }
}
