package com.portal.react.persistence.repository.app.system;

import com.portal.react.persistence.entity.app.system.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long>{
    Optional<SystemUser> findByAccount(String account);
}
