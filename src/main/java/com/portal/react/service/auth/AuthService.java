package com.portal.react.service.auth;

import com.portal.react.persistence.dto.system.SystemUserInfoDto;
import com.portal.react.persistence.dto.system.SystemUserJoinDto;
import com.portal.react.persistence.dto.system.SystemUserLoginDto;
import com.portal.react.persistence.dto.system.SystemUserTokenDto;
import com.portal.react.persistence.entity.app.system.RefreshToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {

    public void join(SystemUserJoinDto systemUserJoinDto);
    public void joinAdmin(SystemUserJoinDto systemUserJoinDto);
    public SystemUserTokenDto login(SystemUserLoginDto systemUserLoginDto);
    public void checkPassword(String rawPassword, String findSystemUserPassword);
    public RefreshToken saveRefreshToken(String username);
    public SystemUserInfoDto getSystemUserInfoDto(String email);
    public void logout(SystemUserTokenDto tokenDto, String username);
    public String resolveToken(String token);
    public SystemUserTokenDto reissue(String refreshToken);
    public String getCurrentUsername();
    public SystemUserTokenDto reissueRefreshToken(String refreshToken, String username);
    public boolean lessThanReissueExpirationTimesLeft(String refreshToken);


}
