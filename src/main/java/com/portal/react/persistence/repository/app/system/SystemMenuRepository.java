package com.portal.react.persistence.repository.app.system;

import com.portal.react.persistence.entity.app.system.SystemMenu;
import com.portal.react.persistence.entity.app.system.key.SystemMenuPk;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemMenuRepository extends JpaRepository<SystemMenu, SystemMenuPk>, SystemMenuRepositoryCustom, JpaSpecificationExecutor<SystemMenu> {

    List<SystemMenu> findBySystemCdAndMenuCd(String systemCd, String menuCd);

    List<SystemMenu> findBySystemCd(String systemCd);

    List<SystemMenu> findByMenuCd(String menuCd);

    List<SystemMenu> findAllBySystemCd(String systemCd, Sort sort);

    @Query(value =
        " SELECT MENU_CD, NAME, NAME_ENG, NAME_ETC, MENU_TYPE, ICON, FILE_PATH, PC_YN, MOBILE_YN, USE_YN, PRNT_CD, MENU_PATH, SORT_SQ, SYSTEM_CD, LVL, CREATE_TIME, CREATE_USER, UPDATE_TIME, UPDATE_USER FROM T_SYS_MENU ORDER BY MENU_CD, SORT_SQ "
            , nativeQuery = true)
    public  List<SystemMenu> selectSystemTreeMenu();

}
