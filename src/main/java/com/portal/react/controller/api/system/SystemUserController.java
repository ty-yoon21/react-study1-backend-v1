package com.portal.react.controller.api.system;

import com.portal.react.persistence.dto.system.SystemUserInfoDto;
import com.portal.react.persistence.dto.system.SystemUserJoinDto;
import com.portal.react.persistence.dto.system.SystemUserLoginDto;
import com.portal.react.persistence.dto.system.SystemUserTokenDto;
import com.portal.react.service.auth.AuthService;
import com.portal.react.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class SystemUserController {

    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public SystemUserController(@Lazy AuthService authService, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/join")
    public String join(@RequestBody SystemUserJoinDto systemUserJoinDto) {
        authService.join(systemUserJoinDto);
        return "회원가입 완료";
    }

    @PostMapping("/join/admin")
    public String joinAdmin(@RequestBody SystemUserJoinDto systemUserJoinDto) {
        authService.joinAdmin(systemUserJoinDto);
        return "어드민 회원 가입 완료";
    }

    @PostMapping("/login")
    public ResponseEntity<SystemUserTokenDto> login(@RequestBody SystemUserLoginDto systemUserLoginDto) {
        return ResponseEntity.ok(authService.login(systemUserLoginDto));
    }

    @GetMapping("/members/{email}")
    public SystemUserInfoDto getSystemUserInfoDto(@PathVariable String email) {
        return authService.getSystemUserInfoDto(email);
    }

    @PostMapping("/reissue")
    public ResponseEntity<SystemUserTokenDto> reissue(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok(authService.reissue(refreshToken));
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken,
                       @RequestHeader("RefreshToken") String refreshToken) {
        String username = jwtTokenUtil.getUsername(resolveToken(accessToken));
        authService.logout(SystemUserTokenDto.of(accessToken, refreshToken), username);
    }

    private String resolveToken(String accessToken) {
        return accessToken.substring(7);
    }
}
