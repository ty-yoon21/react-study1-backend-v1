package com.portal.react.persistence.repository.app.system;

import com.portal.react.persistence.entity.app.system.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;

public interface LogoutAccessTokenRedisRepository extends CrudRepository<LogoutAccessToken, String> {
}