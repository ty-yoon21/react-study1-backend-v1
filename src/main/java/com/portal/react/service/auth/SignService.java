package com.portal.react.service.auth;

import com.portal.react.persistence.dto.system.SignRequestDto;
import com.portal.react.persistence.dto.system.SignResponseDto;
import com.portal.react.persistence.entity.app.system.SystemAuth;
import com.portal.react.persistence.entity.app.system.SystemUser;
import com.portal.react.persistence.repository.app.system.SystemUserRepository;
import com.portal.react.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignResponseDto login(SignRequestDto request) throws Exception {
        SystemUser systemUser = systemUserRepository.findByAccount(request.getAccount()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));
        System.out.println("############ controller api/auth/login : service - systemUser : "+systemUser);
        if (!passwordEncoder.matches(request.getPassword(), systemUser.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        return SignResponseDto.builder()
                .id(systemUser.getId())
                .account(systemUser.getAccount())
                .name(systemUser.getUserName())
                .email(systemUser.getEmail())
                .nickname(systemUser.getNickname())
                .roles(systemUser.getRoles())
                .token(jwtProvider.createToken(systemUser.getAccount(), systemUser.getRoles()))
                .build();

    }

    public boolean register(SignRequestDto request) throws Exception {
        try {
            SystemUser systemUser = SystemUser.builder()
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userName(request.getUsername())
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .build();

            systemUser.setRoles(Collections.singletonList(SystemAuth.builder().role("ROLE_USER_GENERAL").build()));

            systemUserRepository.save(systemUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    public SignResponseDto getSystemUser(String account) throws Exception {
        SystemUser systemUser = systemUserRepository.findByAccount(account)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new SignResponseDto(systemUser);
    }

}