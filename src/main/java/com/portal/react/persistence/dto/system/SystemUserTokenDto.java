package com.portal.react.persistence.dto.system;

import com.portal.react.security.jwt.JwtHeaderUtilEnums;
import lombok.*;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SystemUserTokenDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    public static SystemUserTokenDto of(String accessToken, String refreshToken) {
        return SystemUserTokenDto.builder()
                .grantType(JwtHeaderUtilEnums.GRANT_TYPE.getValue())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
