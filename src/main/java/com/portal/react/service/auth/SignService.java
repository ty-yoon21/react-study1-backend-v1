package com.portal.react.service.auth;

import com.portal.react.persistence.dto.system.SignRequestDto;
import com.portal.react.persistence.dto.system.SignResponseDto;
import com.portal.react.persistence.entity.app.system.SystemAuth;
import com.portal.react.persistence.entity.app.system.SystemUser;
import com.portal.react.persistence.repository.app.system.SystemUserRepository;
import com.portal.react.security.jwt.JwtCookieUtil;
import com.portal.react.security.jwt.JwtProvider;
import com.portal.react.security.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisUtil redisUtill;

    public SignResponseDto generateAccessJwtByRefreshToken(HttpServletRequest request) throws Exception {

        String jwtRefreshToken = null;
        Cookie jwtRefreshTokenCookie = JwtCookieUtil.getCookie(request,JwtProvider.REFRESH_TOKEN_NAME);
        if(jwtRefreshTokenCookie!=null){
            jwtRefreshToken = jwtRefreshTokenCookie.getValue();
        }else {
            return null;
        }
        System.out.println("############## jwtRefreshToken : "+jwtRefreshToken);

        return SignResponseDto.builder()
                .token(jwtProvider.createAccessTokenByRefreshToken(jwtRefreshToken))
                .build();
    }

    public boolean checkRefreshTokenValid(HttpServletRequest request) throws Exception {
        Cookie jwtRefreshTokenCookie = JwtCookieUtil.getCookie(request,JwtProvider.REFRESH_TOKEN_NAME);
        if(jwtRefreshTokenCookie == null){
            System.out.println("################# cookie null");
            return false;
        }else {
            String jwtRefreshToken = jwtRefreshTokenCookie.getValue();

            if(jwtProvider.validateRefreshToken(jwtRefreshToken)){
                System.out.println("############ refresh true");
                return true;
            }else{
                System.out.println("############ refresh false");
                return false;
            }
        }


    }

    public SignResponseDto login(HttpServletRequest request, SignRequestDto signRequestDto) throws Exception {
        SystemUser systemUser = systemUserRepository.findByAccount(signRequestDto.getAccount()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));
        System.out.println("############ controller api/auth/login : service - systemUser : "+systemUser);
        if (!passwordEncoder.matches(signRequestDto.getPassword(), systemUser.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        // Refresh JWT가 없다면 생성
        String jwtRefreshToken = null;
        Cookie jwtRefreshTokenCookie = JwtCookieUtil.getCookie(request,JwtProvider.REFRESH_TOKEN_NAME);
        boolean isRefreshAvailable = jwtProvider.validateRefreshToken(jwtRefreshTokenCookie.getValue());
        if(jwtRefreshTokenCookie==null || !isRefreshAvailable){
            jwtRefreshToken = jwtProvider.createRefreshToken(systemUser.getAccount(), systemUser.getRoles());
            jwtRefreshTokenCookie = JwtCookieUtil.createCookie(jwtProvider.REFRESH_TOKEN_NAME, jwtRefreshToken);
            redisUtill.setDataExpire(jwtRefreshToken, systemUser.getAccount(), JwtProvider.jwtRefreshExpire);
        }

        return SignResponseDto.builder()
                .id(systemUser.getId())
                .account(systemUser.getAccount())
                .name(systemUser.getUserName())
                .email(systemUser.getEmail())
                .nickname(systemUser.getNickname())
                .roles(systemUser.getRoles())
                .token(jwtProvider.createAccessToken(systemUser.getAccount(), systemUser.getRoles()))
                .refreshToken(jwtRefreshTokenCookie)
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