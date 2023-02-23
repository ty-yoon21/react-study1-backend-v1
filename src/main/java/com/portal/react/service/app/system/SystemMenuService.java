package com.portal.react.service.app.system;

import com.portal.react.persistence.dto.system.SystemMenuDto;
import com.portal.react.persistence.entity.app.system.SystemMenu;

import com.portal.react.exceptions.AlreadyExistException;
import com.portal.react.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

public interface SystemMenuService {


    public List<SystemMenuDto> findAll();

    public List<SystemMenuDto> findBySystemCd(String systemCd);

    public List<SystemMenuDto> findBySystemCdAndMenuCd(String systemCd, String menuCd);

    List<SystemMenuDto> findByParams(Map<String, Object> param);

    public List<SystemMenu> add(List<SystemMenu> systemMenus);

    public SystemMenu add(SystemMenu systemMenu) throws NotFoundException;

    public List<SystemMenu> edit(List<SystemMenu> systemMenu);

    public SystemMenu edit(SystemMenu systemMenu) throws NotFoundException;

    public int remove(List<SystemMenu> systemMenu);

    public void remove(SystemMenu systemMenu) throws NotFoundException;

    List<SystemMenuDto> getSystemMenu() throws Exception;

    public List<SystemMenu> save(List<SystemMenu> systemMenu);

    public SystemMenu save(SystemMenu systemMenu) throws NotFoundException;
}
