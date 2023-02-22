package com.portal.react.controller.api.system;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.portal.react.persistence.dto.system.SystemMenuDto;
import com.portal.react.persistence.entity.app.system.SystemMenu;
import com.portal.react.persistence.vo.ItemsModifiedResponseVO;
import com.portal.react.service.app.system.SystemMenuService;
import com.portal.react.utils.JsonResponse;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;*/
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
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



    @ApiOperation(value = "목록조회", notes = "목록을 조회합니다")
    @PostMapping("list")
    @ResponseBody
    public ResponseEntity<?> findList(@RequestBody Map<String, Object> bodyParam, @PathVariable(required = false) String systemCd) {
        JsonResponse<List<SystemMenuDto>> res = new JsonResponse<List<SystemMenuDto>>("list");
        System.out.println("########################## bodyParam : "+bodyParam);
        try {
            res.success(systemMenuService.findByParams(bodyParam));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            res.error("Exception...");
        } catch (Exception e) {
            log.error(e.getMessage());
            res.error("Exception...");
        }
        System.out.println("########################## res : "+res);
        System.out.println("########################## res.body() : "+res.getBody());
        return res.send();
    }


    @PostMapping("save")
    @ResponseBody
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody SystemMenuDto systemMenuDto){
        System.out.println("########################## menu - save");
        System.out.println("############## request.getParameterMap().toString() : "+request.getParameterMap().toString());
        System.out.println("############## systemMenuDto : " + systemMenuDto.toString());
        JsonResponse<ItemsModifiedResponseVO> res = new JsonResponse<ItemsModifiedResponseVO>("postSite");
        System.out.println("########################## menu - save - res : "+res);
        List<SystemMenu> itemsAdded = systemMenuDto.getItemAdded();
        List<SystemMenu> itemsEdited = systemMenuDto.getItemEdited();
        List<SystemMenu> itemsRemoved = systemMenuDto.getItemRemoved();
        System.out.println("########################## menu - save - itemsAdded : "+itemsAdded);
        System.out.println("########################## menu - save - itemsEdited : "+itemsEdited);
        System.out.println("########################## menu - save - itemsRemoved : "+itemsRemoved);
        try {
            int addedCnt = systemMenuService.add(itemsAdded).size();
            int updatedCnt = systemMenuService.edit(itemsEdited).size();
            int deletedCnt = systemMenuService.remove(itemsRemoved);

            res.success(ItemsModifiedResponseVO.builder()
                    .addedItemsRequestedCount(itemsAdded.size())
                    .addedItmesResultCount(addedCnt)
                    .editedItemsRequestCount(itemsEdited.size())
                    .editedItmesResultCount(updatedCnt)
                    .deletedItemsRequestedCount(itemsRemoved.size())
                    .deletedItemsResultCount(deletedCnt).build());


        } catch(JSONException e) {
            log.error("Exception", e);
            res.error("Something Wrong");
        } catch (Exception e) {
            log.error("Exception", e);
            res.error("Something Wrong");
        }

        return res.send();
    }

    @PostMapping("getSystemMenuList")
    @ResponseBody
    public ResponseEntity<?> getSystemMenuList() throws  Exception {

        JsonResponse<List<SystemMenuDto>> res = new JsonResponse<>("menuList");
        try {
/*            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String roles = StringUtils.collectionToCommaDelimitedString(authorities);
            res.success((systemMenuService.getSystemMenu(roles)));*/
        } catch (Exception e) {
            throw new Exception(e);
        }
        return res.send();
    }

    @PostMapping("send")
    public ResponseEntity<?> send(@RequestBody SystemMenuDto dto) throws JsonProcessingException {

        return ResponseEntity.ok("Success");
    }


}
