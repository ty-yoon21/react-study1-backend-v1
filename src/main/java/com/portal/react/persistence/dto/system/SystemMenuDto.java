package com.portal.react.persistence.dto.system;


import com.portal.react.persistence.entity.app.system.SystemMenu;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemMenuDto {

    private String systemCd;
    private String menuCd;
    private String prntCd;
    private String name;
    private String nameEng;
    private String nameEtc;
    private String menuPath;
    private String icon;
    private String filePath;
    private String menuType;
    private String pcYn;
    private String mobileYn;
    private int lvl;

    private String useYn;
    private Integer sortSq;
    private String regUserid;
    private String regDttm;
    private String updUserId;
    private String updDttm;

    private String systemName;

    private List<SystemMenu> itemAded;
    private List<SystemMenu> itemEdited;
    private List<SystemMenu> itemRemoved;
    private List<SystemMenu> itemSaved;

    public SystemMenuDto(SystemMenu menu) {
        BeanUtils.copyProperties(menu, this);
    }
}
