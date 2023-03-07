package com.portal.react.persistence.dto.system;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SystemUserInfoDto {

    private String email;
    private String username;
}
