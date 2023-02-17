package com.portal.react.service.app.system;

import com.portal.react.exceptions.AlreadyExistException;
import com.portal.react.exceptions.NotFoundException;
import com.portal.react.persistence.dto.system.SystemMenuDto;
import com.portal.react.persistence.entity.app.system.SystemMenu;
import com.portal.react.persistence.entity.app.system.key.SystemMenuPk;
import com.portal.react.persistence.repository.app.system.SystemMenuRepository;
import com.portal.react.persistence.repository.app.system.SystemMenuRepositoryImpl;
import com.portal.react.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SystemMenuServiceImpl implements SystemMenuService{

    private final SystemMenuRepository systemMenuRepository;


    public SystemMenuServiceImpl(SystemMenuRepository systemMenuRepository) {
        this.systemMenuRepository = systemMenuRepository;
    }

    @Override
    public List<SystemMenuDto> findAll() {
        List<SystemMenu> menus = systemMenuRepository.findAll();

        return menus.stream().map(x -> new SystemMenuDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<SystemMenuDto> findBySystemCd(String systemCd) {
        List<SystemMenu> menus = systemMenuRepository.findBySystemCd(systemCd);

        return menus.stream().map(x -> new SystemMenuDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<SystemMenuDto> findBySystemCdAndMenuCd(String systemCd, String menuCd) {
        List<SystemMenu> menus = systemMenuRepository.findBySystemCdAndMenuCd(systemCd, menuCd);

        return menus.stream().map(x -> new SystemMenuDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<SystemMenuDto> findByParams(Map<String, Object> param) {
        System.out.println("##########################SysetmMenuServiceImpl - findByParams() param"+param);
        List<SystemMenuDto> auths = systemMenuRepository.findByParams(param);

        System.out.println("##########################SysetmMenuServiceImpl - findByParams() auth"+auths);
        return auths;
    }

    @Override
    public List<SystemMenu> add(List<SystemMenu> systemMenus) {
        List<SystemMenu> result = new ArrayList<>();

        for(SystemMenu systemMenu : systemMenus) {
            try {
                result.add(add(systemMenu));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public SystemMenu add(SystemMenu systemMenu) throws NotFoundException {
        SystemMenuPk key = SystemMenuPk.builder().menuCd(systemMenu.getMenuCd()).systemCd(systemMenu.getSystemCd()).build();

        Optional<SystemMenu> exists =systemMenuRepository.findById(key);

        if(exists.isPresent())
            try {
                throw new AlreadyExistException();
            } catch (AlreadyExistException e) {
                e.printStackTrace();
            }

        systemMenu.setCreateTime(DateUtils.now());
        systemMenu.setUpdateTime(DateUtils.now());

        return systemMenuRepository.save(systemMenu);
    }

    @Override
    public List<SystemMenu> edit(List<SystemMenu> systemMenus) {
        List<SystemMenu> result = new ArrayList<>();

        for(SystemMenu systemMenu : systemMenus) {
            try {
                result.add(edit(systemMenu));
            } catch (NotFoundException e) {
                log.info("Not Found");
            } catch (Exception e) {
                throw e;
            }
        }

        return result;
    }

    @Override
    public SystemMenu edit(SystemMenu systemMenu) throws NotFoundException {
        SystemMenuPk key = SystemMenuPk.builder().menuCd(systemMenu.getMenuCd()).systemCd(systemMenu.getSystemCd()).build();

        Optional<SystemMenu> exists = systemMenuRepository.findById(key);

        if(!exists.isPresent())
            throw new NotFoundException();

        systemMenu.setUpdateTime(DateUtils.now());

        return systemMenuRepository.save(systemMenu);

    }

    @Override
    public int remove(List<SystemMenu> systemMenus) {

        int resultcnt = 0;

        for (SystemMenu systemMenu : systemMenus) {
            try {
                remove(systemMenu);
                resultcnt++;
            } catch (NotFoundException e) {
                log.info("Not Found");
            } catch (Exception e) {
                throw e;
            }
        }

        return resultcnt;
    }

    @Override
    public void remove(SystemMenu systemMenu) throws NotFoundException {
        SystemMenuPk key = SystemMenuPk.builder().menuCd((systemMenu.getMenuCd())).systemCd(systemMenu.getSystemCd()).build();
        Optional<SystemMenu> exists = systemMenuRepository.findById(key);

        if(!exists.isPresent())
            throw new NotFoundException();

        systemMenuRepository.delete(systemMenu);
    }

    @Override
    public List<SystemMenuDto> getSystemMenu(String roles) throws Exception {
        List<SystemMenu> menus = systemMenuRepository.selectSystemTreeMenu(roles);

        return menus.stream().map(SystemMenuDto::new).collect(Collectors.toList());
    }

    @Override
    public List<SystemMenu> save(List<SystemMenu> systemMenus) {
        List<SystemMenu> result = new ArrayList<>();

        for (SystemMenu systemMenu : systemMenus) {
            try {
                result.add(edit(systemMenu));
            } catch (NotFoundException e) {
                log.info("Not Found");
            } catch (Exception e) {
                throw e;
            }

        }

        return result;
    }

    @Override
    public SystemMenu save(SystemMenu systemMenu) throws NotFoundException {

        SystemMenuPk key = SystemMenuPk.builder().menuCd(systemMenu.getMenuCd()).systemCd(systemMenu.getSystemCd()).build();

        Optional<SystemMenu> exists = systemMenuRepository.findById(key);

        if(!exists.isPresent()) {
            systemMenu.setCreateTime(DateUtils.now());
            systemMenu.setUpdateTime(DateUtils.now());
        }else {
            systemMenu.setUpdateTime(DateUtils.now());
        }
        return systemMenuRepository.save(systemMenu);
    }
}
