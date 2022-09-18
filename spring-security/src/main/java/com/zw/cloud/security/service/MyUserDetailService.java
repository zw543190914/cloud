package com.zw.cloud.security.service;

import com.zw.cloud.security.entity.sys.SysRole;
import com.zw.cloud.security.entity.sys.SysUser;
import com.zw.cloud.security.service.api.sys.ISysRoleService;
import com.zw.cloud.security.service.api.sys.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     *   实现自定义的认证流程
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)){
            return null;
        }
        SysUser sysUser = sysUserService.queryByUsername(username);
        if (Objects.isNull(sysUser)) {
            return null;
        }
        List<SysRole> sysRoles = sysRoleService.queryRolesByUserId(sysUser.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sysRoles)) {
            sysRoles.forEach(sysRole -> {
                SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + sysRole.getRoleName());
                authorities.add(auth);
            });
        }

        UserDetails user = new User(username
                ,sysUser.getPassword()
                ,true
                ,true
                ,true
                ,Objects.equals(1,sysUser.getStatus())
                ,authorities);
        return user;
    }
}
