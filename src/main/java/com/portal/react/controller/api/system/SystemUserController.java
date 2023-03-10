package com.portal.react.controller.api.system;

import com.portal.react.persistence.dto.system.*;
import com.portal.react.persistence.repository.app.system.SystemUserRepository;
import com.portal.react.persistence.vo.ItemsModifiedResponseVO;
import com.portal.react.service.auth.SignService;
import com.portal.react.utils.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class SystemUserController {

    private final SystemUserRepository systemUserRepository;
    private final SignService signService;

    public SystemUserController(SystemUserRepository systemUserRepository, SignService signService) {
        this.systemUserRepository = systemUserRepository;
        this.signService = signService;

    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> signin(HttpServletRequest request, @RequestBody SignRequestDto signRequestDto) throws Exception {
        System.out.println("############ controller api/auth/login : request : "+request);
        System.out.println("############ controller api/auth/login : request : "+signRequestDto);

        return new ResponseEntity<>(signService.login(request, signRequestDto), HttpStatus.OK);

        /*        JsonResponse<SignResponseDto> res = new JsonResponse<SignResponseDto>("userLogin");
        try{
            signService.login(signRequestDto);
        } catch(JSONException e) {
            log.error("Json Exception : ", e);
            res.error("Something Wrong");
        } catch (Exception e) {
            log.error("Exception e : ", e);
            res.error("Something Wrong");
        }
        return res.send();*/
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> generateAccessJwtByRefreshToken(HttpServletRequest request) throws Exception {
        System.out.println("############ controller api/auth/refresh : request : "+request);

        return new ResponseEntity<>(signService.generateAccessJwtByRefreshToken(request), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> signup(HttpServletRequest request, @RequestBody SignRequestDto signRequestDto) throws Exception {
        System.out.println("############ controller api/auth/register : request : "+request);
        System.out.println("############ controller api/auth/register : signRequestDto : "+signRequestDto);
        JsonResponse<SignRequestDto> res = new JsonResponse<SignRequestDto>("userRegister");
        try{
            signService.register(signRequestDto);
        } catch(JSONException e) {
            log.error("Json Exception : ", e);
            res.error("Something Wrong");
        } catch (Exception e) {
            log.error("Exception e : ", e);
            res.error("Something Wrong");
        }
        return res.send();
        //return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }

    @PostMapping(value = "/isAuth")
    public ResponseEntity<?> isAuth(@RequestBody Map<String, String> bodyParam, HttpServletRequest request, Principal principal) throws Exception {

        JsonResponse<Map<Object, Object>> res = new JsonResponse<>("checkAuth");
        Map<Object, Object> map = new HashMap<>();
        System.out.println("###################### isAuth - Principal : "+principal);
        try {
            if (principal != null) {
                map.put("isAuth", true);
            } else {
                if(signService.checkRefreshTokenValid(request)){
                    map.put("isAuth", true);
                }else{
                    map.put("isAuth", false);
                }

            }
            res.success(map);
        } catch (Exception e) {
            log.error("Exception e : ", e);
            throw new Exception(e);
        }

        return res.send();
    }

    @GetMapping("/user/get")
    public ResponseEntity<SignResponseDto> getUser(@RequestParam String account) throws Exception {
        return new ResponseEntity<>( signService.getSystemUser(account), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponseDto> getUserForAdmin(@RequestParam String account) throws Exception {
        return new ResponseEntity<>( signService.getSystemUser(account), HttpStatus.OK);
    }
}
