package com.zw.cloud.shiro.config;

import com.zw.cloud.shiro.entity.User;
import com.zw.cloud.shiro.service.api.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(MyRealm.class);

    public static final String SALT = "salt_secret";

    //用户 登录 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken用于存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        logger.info("[doGetAuthenticationInfo]进入登录认证");
        User currentUser = null;
        try {
            currentUser = userService.queryUserByUserName(username);
            logger.info("[doGetAuthenticationInfo]进入登录认证--数据库查询");

        } catch (Exception e) {
            logger.error("[doGetAuthenticationInfo] error is {}",e );
        }
        if (currentUser != null){
            //盐值 盐值在前
            ByteSource salt = ByteSource.Util.bytes(username);
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            // currentUser.getPassword() 加密后密码
            return new SimpleAuthenticationInfo(currentUser, currentUser.getPassword(),salt,getName());
        }
        return null;
    }

    //角色权限和对应权限 管理
    // 当访问到页面的时候，使用了相应的注解或者shiro标签才会执行此方法否则不会执行
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("[doGetAuthorizationInfo]进入权限匹配");
        User currentUser =(User) super.getAvailablePrincipal(principalCollection);

        if (currentUser != null){
            logger.info("[doGetAuthorizationInfo]进入权限匹配 ==查询数据库");

            String username = currentUser.getUserName();
            // 权限信息对象info，用来存放查出的用户的所有的角色及权限
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户角色集合
            Map<String, Object> permissionMap = userService.queryPermission(username);
            if (null == permissionMap.get("roleSet")){
                return null;
            }
            Set<String> roles = (Set<String>) permissionMap.get("roleSet");
            logger.info("[doGetAuthorizationInfo]roles is {}", roles.toString());
            info.setRoles(roles);

            if (null == permissionMap.get("resourceNameList")){
                return info;
            }
            Set<String> resourceUriSet = (Set<String>) permissionMap.get("resourceNameList");
            //查询具体权限名称
            info.setStringPermissions(resourceUriSet);
            logger.info("[doGetAuthorizationInfo]resourceUriSet is {}", resourceUriSet.toString());

            return info;
        }
        return null;
    }


    /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    public static void main(String[] args) {
        String zy = new Md5Hash("123456", "zw", 3).toString();
        System.out.println(zy);
        System.out.println(DigestUtils.md5Hex("zy123456"));
    }

}
