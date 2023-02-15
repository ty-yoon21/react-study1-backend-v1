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
        " WITH RECURSIVE T3 (MENU_CD, NAME, NAME_ENG, NAME_ETC, PRNT_CD, MENU_PATH, ICON, MENU_TYPE, MOBILE_YN, PC_YN, SORT_SQ, SYSTEM_CD, CREATE_TIME, UPDATE_TIME, CREATE_USER, CREATE_TIME, USE_YN, LVL) AS " +
                " ( " +
                "   SELECT T1.MENU_CD, T1.NAME, T1.NAME_ENG, T1.NAME_ETC, T1.PRNT_CD, T1.MENU_PATH, T1.FILE_PATH, T1.ICON, T1.MENU_TYPE, T1.MOBILE_YN, T1.PC_YN, T1.SORT_SQ, T1.SYSTEM_CD, T1.CREATE_TIME, T1.UPDATE_TIME, T1.CREATE_USER, T1.UPDATE_USER, T1.USE_YN, 0 AS LVL " +
                "   FROM T_SYS_MENU T1 " +
                "   WHERE T1.PRNT_CD = '' AND T1.SYSTEM_CD = '10322' AND IFNULL(T1.USE_YN, 'Y') = 'Y' " +
                "   UNION ALL " +
                "   SELECT T2.MENU_CD, T2.NAME, T2.NAME_ENG, T2.NAME_ETC, T2.PRNT_CD, T2.MENU_PATH, T2.FILE_PATH, T2.ICON, T2.MENU_TYPE, T2.MOBILE_YN, T2.PC_YN, T2. SORT_SQ, T2.SYSTEM_CD, T2.CREATE_TIME, T2.UPDATE_TIME, T2.CREATE_USER, T2.UPDATE_USER, T2.USE_YN, T3.LVL+1 AS LVL " +
                "   FROM T_SYS_MENU T3 " +
                "   INNER JOIN T3 " +
                "   ON T2.PRNT_CD = T3.MENU_CD " +
                "   AND T2.SYSTEM_CD = T3.SYSTEM_CD AND IFNULL(T2.USE_YN, 'Y') = 'Y' " +
                "   ) " +
                "   SELECT * FROM T3 " +
                "   ORDER BY T3.MENU_CD, T3.SORT_SQ "
            , nativeQuery = true)
    public  List<SystemMenu> selectSystemTreeMenu(String roles);

}
