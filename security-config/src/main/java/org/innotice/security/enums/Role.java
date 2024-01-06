package org.innotice.security.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    USER, ADMIN;

    public GrantedAuthority grantedAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }

}
