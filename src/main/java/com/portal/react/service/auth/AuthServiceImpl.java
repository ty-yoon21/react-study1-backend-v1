package com.portal.react.service.auth;

import com.portal.react.persistence.dto.system.*;
import com.portal.react.persistence.entity.app.system.LogoutAccessToken;
import com.portal.react.persistence.entity.app.system.RefreshToken;
import com.portal.react.persistence.entity.app.system.SystemUser;
import com.portal.react.persistence.repository.app.system.LogoutAccessTokenRedisRepository;
import com.portal.react.persistence.repository.app.system.RefreshTokenRedisRepository;
import com.portal.react.persistence.repository.app.system.SystemUserRepository;
import com.portal.react.security.cache.CacheKey;
import com.portal.react.security.jwt.JwtExpirationEnums;
import com.portal.react.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.portal.react.security.jwt.JwtExpirationEnums.REFRESH_TOKEN_EXPIRATION_TIME;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {


    public final SystemUserRepository systemUserRepository;
    public final PasswordEncoder passwordEncoder;
    public final RefreshTokenRedisRepository refreshTokenRedisRepository;
    public final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
    public final JwtTokenUtil jwtTokenUtil;

    public void join(SystemUserJoinDto systemUserJoinDto) {
        systemUserJoinDto.setPassword(passwordEncoder.encode(systemUserJoinDto.getPassword()));
        systemUserRepository.save(SystemUser.ofUser(systemUserJoinDto));
    }

    public void joinAdmin(SystemUserJoinDto systemUserJoinDto) {
        systemUserJoinDto.setPassword(passwordEncoder.encode(systemUserJoinDto.getPassword()));
        systemUserRepository.save(SystemUser.ofAdmin(systemUserJoinDto));
    }

    // 1
    public SystemUserTokenDto login(SystemUserLoginDto systemUserLoginDto) {
        SystemUser systemUser = systemUserRepository.findByEmail(systemUserLoginDto.getEmail()).orElseThrow(() -> new NoSuchElementException("회원이 없습니다."));
        checkPassword(systemUserLoginDto.getPassword(), systemUser.getPassword());
        System.out.println("################# check password 통과");
        String username = systemUser.getUsername();
        System.out.println("################# username"+username);
        String accessToken = jwtTokenUtil.generateAccessToken(username);
        System.out.println("################# accessToken"+accessToken);
        RefreshToken refreshToken = saveRefreshToken(username);
        return SystemUserTokenDto.of(accessToken, refreshToken.getRefreshToken());
    }

    public void checkPassword(String rawPassword, String findSystemUserPassword) {
        if (!passwordEncoder.matches(rawPassword, findSystemUserPassword)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }

    public RefreshToken saveRefreshToken(String username) {
        return refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(username,
                jwtTokenUtil.generateRefreshToken(username), REFRESH_TOKEN_EXPIRATION_TIME.getValue()));
    }

    // 2
    public SystemUserInfoDto getSystemUserInfoDto(String email) {
        SystemUser systemUser = systemUserRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("회원이 없습니다."));
        if (!systemUser.getUsername().equals(getCurrentUsername())) {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다.");
        }
        return SystemUserInfoDto.builder()
                .username(systemUser.getUsername())
                .email(systemUser.getEmail())
                .build();
    }

    // 4
    @CacheEvict(value = CacheKey.USER, key = "#username")
    public void logout(SystemUserTokenDto tokenDto, String username) {
        String accessToken = resolveToken(tokenDto.getAccessToken());
        long remainMilliSeconds = jwtTokenUtil.getRemainMilliSeconds(accessToken);
        refreshTokenRedisRepository.deleteById(username);
        logoutAccessTokenRedisRepository.save(LogoutAccessToken.of(accessToken, username, remainMilliSeconds));
    }

    public String resolveToken(String token) {
        return token.substring(7);
    }

    // 3
    public SystemUserTokenDto reissue(String refreshToken) {
        refreshToken = resolveToken(refreshToken);
        String username = getCurrentUsername();
        RefreshToken redisRefreshToken = refreshTokenRedisRepository.findById(username).orElseThrow(NoSuchElementException::new);

        if (refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            return reissueRefreshToken(refreshToken, username);
        }
        throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

    public SystemUserTokenDto reissueRefreshToken(String refreshToken, String username) {
        if (lessThanReissueExpirationTimesLeft(refreshToken)) {
            String accessToken = jwtTokenUtil.generateAccessToken(username);
            return SystemUserTokenDto.of(accessToken, saveRefreshToken(username).getRefreshToken());
        }
        return SystemUserTokenDto.of(jwtTokenUtil.generateAccessToken(username), refreshToken);
    }

    public boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
        return jwtTokenUtil.getRemainMilliSeconds(refreshToken) < JwtExpirationEnums.REISSUE_EXPIRATION_TIME.getValue();
    }
}
