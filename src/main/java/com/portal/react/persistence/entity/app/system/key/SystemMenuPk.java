package com.portal.react.persistence.entity.app.system.key;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemMenuPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String menuCd;
    private String systemCd;
}
