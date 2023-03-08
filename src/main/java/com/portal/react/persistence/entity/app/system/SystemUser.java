package com.portal.react.persistence.entity.app.system;


import com.portal.react.persistence.dto.system.SystemUserJoinDto;
import lombok.*;
import javax.persistence.*;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_CM_USER")
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
public class SystemUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String account;

    private String password;

    @Column(name = "user_name")
    private String userName;

    private String nickname;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "systemUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<SystemAuth> roles = new ArrayList<>();

    public void setRoles(List<SystemAuth> role) {
        this.roles = role;
        role.forEach(o -> o.setSystemUser(this));
    }
}
