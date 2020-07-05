package com.zw.cloud.shiro.entity.vo;

import com.zw.cloud.shiro.entity.User;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.Set;

public class UserVO implements Serializable, AuthCachePrincipal {

    private User user;
    private Set<String> roleSet;
    private Set<String> resourceSet;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(Set<String> resourceSet) {
        this.resourceSet = resourceSet;
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
