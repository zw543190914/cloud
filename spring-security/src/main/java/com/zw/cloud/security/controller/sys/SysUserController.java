package com.zw.cloud.security.controller.sys;


import com.zw.cloud.security.entity.sys.SysUser;
import com.zw.cloud.security.service.api.sys.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/updatePwd")
    public boolean updatePwd(String oldPwd, String newPwd) {
        // 获取当前登录用户信息(注意：没有密码的)
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        // 通过用户名获取到用户信息（获取密码）
        SysUser sysUser = sysUserService.queryByUsername(username);
        if (Objects.isNull(sysUser)) {
            return false;
        }

        // 判断输入的旧密码是正确
        if (passwordEncoder.matches(oldPwd, sysUser.getPassword())) {
            // 不要忘记加密新密码
            sysUser.setPassword(passwordEncoder.encode(newPwd));
            sysUserService.updateById(sysUser);
            return true;
        }
        return false;
    }
}

