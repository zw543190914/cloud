package com.zw.cloud.security.controller;

import com.zw.cloud.security.entity.sys.SysUser;
import com.zw.cloud.security.service.api.sys.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping
public class SecurityController {

    @Autowired
    private ISysUserService sysUserService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @PostMapping(value = "/doLogin")
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(SysUser sysUser) throws AuthenticationException {
        final String token = sysUserService.login(sysUser);
        // Return the token
        return ResponseEntity.ok(token);
    }

    @GetMapping(value = "/refresh")
    @ResponseBody
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = sysUserService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(refreshedToken);
        }
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public boolean register(@RequestBody SysUser addedUser) throws AuthenticationException{
        return sysUserService.register(addedUser);
    }

   /* @GetMapping("/doLogin")
    //http://localhost:9050/doLogin
    public String doLogin(){
        return "success";
    }*/

    @RequestMapping("/mylogin.html")
    public String myLogin() {
        return "mylogin";
    }
}
