package com.zw.cloud.security.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.security.entity.sys.SysUser;
import com.zw.cloud.security.dao.sys.SysUserMapper;
import com.zw.cloud.security.service.MyUserDetailService;
import com.zw.cloud.security.service.api.sys.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.security.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-09-18
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public boolean register(SysUser sysUser) {
        final String username = sysUser.getUsername();
        if(Objects.nonNull(queryByUsername(username))) {
            throw new RuntimeException("用户名已存在");
        }
        final String rawPassword = sysUser.getPassword();
        sysUser.setPassword(bCryptPasswordEncoder.encode(rawPassword));
        return Objects.equals(1,baseMapper.insert(sysUser));
    }

    @Override
    public String login(SysUser addedUser) {
        String username = addedUser.getUsername();
        String password = addedUser.getPassword();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Reload password post-security so we can generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtils.generateToken(userDetails);
        log.info("[login]username is {},token is {}",username,token);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtils.getUsernameFromToken(token);
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("获取用户信息失败,请重新登陆");
        }
        if (!jwtTokenUtils.isTokenExpired(token)){
            return jwtTokenUtils.refreshToken(token);
        } else {
            throw new RuntimeException("token失效,请重新登陆");
        }
    }

    @Override
    public SysUser queryByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,username);
        return baseMapper.selectOne(queryWrapper);
    }
}
