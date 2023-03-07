package com.portal.react.persistence.dto.system;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.react.persistence.entity.app.system.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetailsDto implements UserDetails{
    private String username;
    private String password;
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public static UserDetails of(SystemUser systemUser) {
        return CustomUserDetailsDto.builder()
                .username(systemUser.getUsername())
                .password(systemUser.getPassword())
                .roles(systemUser.getSystemAuths())
                .build();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
