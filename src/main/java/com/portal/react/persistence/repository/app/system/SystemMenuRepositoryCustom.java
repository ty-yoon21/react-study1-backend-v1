package com.portal.react.persistence.repository.app.system;

import com.portal.react.persistence.dto.system.SystemMenuDto;

import java.util.List;
import java.util.Map;

public interface SystemMenuRepositoryCustom {

    public Long findListCnt(String menuCd, String systemcd);

    public List<SystemMenuDto> findByParams(Map<String, Object> param);
}
