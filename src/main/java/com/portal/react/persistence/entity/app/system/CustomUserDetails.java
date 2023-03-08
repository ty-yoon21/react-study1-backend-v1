package com.portal.react.persistence.entity.app.system;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final SystemUser systemUser;

    public CustomUserDetails(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public final SystemUser getSystemUser() {
        return systemUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return systemUser.getRoles().stream().map(o -> new SimpleGrantedAuthority(
                o.getRole()
        )).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUserName();
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
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}