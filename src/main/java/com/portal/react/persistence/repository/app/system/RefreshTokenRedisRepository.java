package com.portal.react.persistence.repository.app.system;

import com.portal.react.persistence.entity.app.system.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}