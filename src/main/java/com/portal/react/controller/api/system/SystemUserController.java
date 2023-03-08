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
        return new ResponseEntity<>(signService.login(signRequestDto), HttpStatus.OK);
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

    @GetMapping("/user/get")
    public ResponseEntity<SignResponseDto> getUser(@RequestParam String account) throws Exception {
        return new ResponseEntity<>( signService.getSystemUser(account), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponseDto> getUserForAdmin(@RequestParam String account) throws Exception {
        return new ResponseEntity<>( signService.getSystemUser(account), HttpStatus.OK);
    }
}
