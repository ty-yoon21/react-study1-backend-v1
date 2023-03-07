package com.portal.react.persistence.entity.app.system;


import com.portal.react.persistence.dto.system.SystemUserJoinDto;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String nickname;


    @OneToMany(mappedBy = "systemUser", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    private Set<SystemAuth> systemAuths = new HashSet<>();

    public static SystemUser ofUser(SystemUserJoinDto systemUserJoinDto) {
        SystemUser systemUser = SystemUser.builder()
                .username(UUID.randomUUID().toString())
                .email(systemUserJoinDto.getEmail())
                .password(systemUserJoinDto.getPassword())
                .nickname(systemUserJoinDto.getNickname())
                .build();
        systemUser.addSystemAuth(SystemAuth.ofUser(systemUser));
        return systemUser;
    }

    public static SystemUser ofAdmin(SystemUserJoinDto systemUserJoinDto) {
        SystemUser systemUser = SystemUser.builder()
                .username(UUID.randomUUID().toString())
                .email(systemUserJoinDto.getEmail())
                .password(systemUserJoinDto.getPassword())
                .nickname(systemUserJoinDto.getNickname())
                .build();
        systemUser.addSystemAuth(SystemAuth.ofAdmin(systemUser));
        return systemUser;
    }

    private void addSystemAuth(SystemAuth systemAuth) {
        systemAuths.add(systemAuth);
    }

//    public List<String> getRoles() {
//        return systemAuths.stream()
//                .map(SystemAuth::getRole)
//                .collect(toList());
//    }
    public List<String> getSystemAuths() {
        return systemAuths.stream()
                .map(SystemAuth::getRole)
                .collect(toList());
    }
}
