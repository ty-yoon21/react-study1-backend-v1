package com.portal.react.persistence.dto.system;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequestDto {

    private Long id;

    private String account;

    private String password;

    private String nickname;

    private String username;

    private String email;

}