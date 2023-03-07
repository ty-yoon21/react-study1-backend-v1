package com.portal.react.persistence.dto.system;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SystemUserLoginDto {
    private String email;
    private String password;
}
