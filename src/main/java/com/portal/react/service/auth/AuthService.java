package com.portal.react.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
}
