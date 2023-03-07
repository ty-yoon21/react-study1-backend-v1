package com.portal.react.persistence.entity.app.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_CM_AUTHORITY")
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
public class SystemAuth implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private SystemUser systemUser;

    private String role;

    public static SystemAuth ofUser(SystemUser systemUser) {
        return SystemAuth.builder()
                .role("ROLE_USER")
                .systemUser(systemUser)
                .build();
    }

    public static SystemAuth ofAdmin(SystemUser systemUser) {
        return SystemAuth.builder()
                .role("ROLE_ADMIN")
                .systemUser(systemUser)
                .build();
    }


//    public String getRole() {
//        return role;
//    }

    @Override
    public String getAuthority() {
        return role;
    }
}
