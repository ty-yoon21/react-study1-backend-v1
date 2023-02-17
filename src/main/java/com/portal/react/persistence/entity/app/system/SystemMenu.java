package com.portal.react.persistence.entity.app.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.react.persistence.dto.system.SystemMenuDto;
import com.portal.react.persistence.entity.app.system.key.SystemMenuPk;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "T_SYS_MENU"
        , indexes = {
        @Index(name = "T_CM_EENU_IDX_01", unique = false, columnList = "system_cd"),
        @Index(name = "T_CM_EENU_IDX_02", unique = false, columnList = "menu_cd"),
        @Index(name = "T_CM_EENU_IDX_03", unique = false, columnList = "prnt_cd"),

}
)
@Data
@NoArgsConstructor
@IdClass(SystemMenuPk.class)
@DynamicInsert
@DynamicUpdate
//@org.hibernate.annotations.Table(comment= "시스템메뉴코드", appliesTo = "T_SYS_MENU")
public class SystemMenu implements Serializable {

    @Id
    @Comment("시스템코드")
    @Column(name = "system_cd", nullable = false, length = 50)
    private String systemCd;

    @Id
    @Comment("메뉴코드")
    @Column(name = "menu_cd", nullable = false, length = 50)
    private String menuCd;

    @Comment("상위코드")
    @Column(name = "prnt_cd", nullable = false, length = 50)
    private String prntCd;


    @Comment("이름")
    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Comment("이름_영문")
    @Column(name = "name_eng", nullable = true, length = 50)
    private String nameEng;

    @Comment("이름_기타")
    @Column(name = "name_etc", nullable = true, length = 50)
    private String nameEtc;

    @Comment("메뉴타입")
    @Column(name = "menu_type", nullable = true, length = 50)
    private String menuType;

    @Comment("메뉴경로")
    @Column(name = "menu_path", nullable = true, length = 250)
    private String menuPath;


    @Comment("아이콘")
    @Column(name = "icon", nullable = true, length = 50)
    private String icon;

    @Comment("파일경로")
    @Column(name = "file_path", nullable = true, length = 250)
    private String filePath;


    @Comment("PC_적용여부")
    @Column(name = "pc_yn", nullable = true, length = 50)
    private String pcYn;

    @Comment("모바일적용여부")
    @Column(name = "mobile_yn", nullable = true, length = 50)
    private String mobileYn;

    @Comment("정렬순번")
    @Column(name = "sort_sq", nullable = true, length = 50)
    private Integer sortSq;

    @Comment("사용여부")
    @Column(name = "use_yn", nullable = true, length = 50)
    private String useYn;

    @Comment("미사용")
    @Column(name = "lvl", nullable = true, length = 50)
    private int lvl;


    @Comment("등록자_아이디")
    @Column(name = "create_user", nullable = false, length = 50)
    private String createUser;

    @JsonIgnore
    @Comment("등록일")
    @Column(name = "create_time", nullable = true, length = 50)
    private Date createTime;

    @Comment("수정자_아이디")
    @Column(name = "update_user", nullable = true, length = 50)
    private String updateUser;

    @JsonIgnore
    @Comment("수정일")
    @Column(name = "update_time", nullable = true, length = 50)
    private Date updateTime;

    @Builder
    public SystemMenu(String systemCd, String menuCd, String prntCd, String name, String filePath, String pcYn,
                      String mobileYn, String createUser, Date createTime, String updateUser, Date updateTime, int lvl) {
        this.systemCd = systemCd;
        this.menuCd = menuCd;
        this.prntCd = prntCd;
        this.name = name;
        this.filePath = filePath;
        this.pcYn = pcYn;
        this.mobileYn = mobileYn;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.lvl = lvl;

    }

    public SystemMenu(SystemMenuDto menuDto) {
        BeanUtils.copyProperties(menuDto, this);
    }


}
