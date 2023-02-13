package com.portal.react.controller.api.system;


import com.portal.react.persistence.dto.system.SystemMenuDto;
import com.portal.react.service.app.system.SystemMenuService;
import com.portal.react.utils.JsonResponse;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/sys/menu/")
public class SystemMenuController {

    private final SystemMenuService systemMenuService;

    @Autowired
    public SystemMenuController(SystemMenuService systemMenuService) {
        this.systemMenuService = systemMenuService;
    }

    public ResponseEntity<?> findList(@RequestBody Map<String, Object> bodyParam, @PathVariable(required = false) String systemCd) {
        JsonResponse<List<SystemMenuDto>> res = new JsonResponse<List<SystemMenuDto>>("list");

        try {
            res.success(systemMenuService.findByParams(bodyParam));
        }
    }
}
