package com.portal.react.service.auth;

import com.portal.react.persistence.dto.system.CustomUserDetailsDto;
import com.portal.react.persistence.entity.app.system.SystemUser;
import com.portal.react.persistence.repository.app.system.SystemUserRepository;
import com.portal.react.security.cache.CacheKey;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    public final SystemUserRepository systemUserRepository;

    @Override
    @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.findByUsernameWithAuthority(username).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        return CustomUserDetailsDto.of(systemUser);
    }
}