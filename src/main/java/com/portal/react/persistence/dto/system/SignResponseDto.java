package com.portal.react.persistence.dto.system;

import com.portal.react.persistence.entity.app.system.SystemAuth;
import com.portal.react.persistence.entity.app.system.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponseDto {

    private Long id;

    private String account;

    private String nickname;

    private String name;

    private String email;

    private List<SystemAuth> roles = new ArrayList<>();

    private String token;

    public SignResponseDto(SystemUser systemUser) {
        this.id = systemUser.getId();
        this.account = systemUser.getAccount();
        this.nickname = systemUser.getNickname();
        this.name = systemUser.getUserName();
        this.email = systemUser.getEmail();
        this.roles = systemUser.getRoles();
    }
}