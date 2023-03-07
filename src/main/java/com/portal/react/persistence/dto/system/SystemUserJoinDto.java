package com.portal.react.persistence.dto.system;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SystemUserJoinDto {

    private String email;
    private String password;
    private String nickname;
}
