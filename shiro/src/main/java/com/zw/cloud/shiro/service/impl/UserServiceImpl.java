package com.zw.cloud.shiro.service.impl;

import com.google.common.base.Preconditions;
import com.zw.cloud.shiro.dao.*;
import com.zw.cloud.shiro.entity.*;
import com.zw.cloud.shiro.service.api.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public User queryUserByUserName(String username){
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username).andStatusEqualTo((byte)0);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public User insert(User user){
        Preconditions.checkNotNull(user.getUserName());
        Preconditions.checkNotNull(user.getPassword());
        user.setPassword(DigestUtils.md5Hex(user.getUserName() + user.getPassword()));
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public User update(User user){
        Preconditions.checkNotNull(user.getUserName());
        user.setPassword(DigestUtils.md5Hex(user.getUserName() + user.getPassword()));
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName());
        userMapper.updateByExample(user,example);
        return user;
    }

    @Override
    public boolean duplicationCheck(String userName){
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        long count = userMapper.countByExample(example);
        return count < 1 ;
    }

    @Override
    public Map<String,Object> queryPermission(String userName){
        Map<String,Object> result = new HashMap<>();
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        User user = userMapper.selectOneByExample(example);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (CollectionUtils.isEmpty(userRoles)){
            return result;
        }
        List<Integer> roleIds = userRoles.stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList());
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(roleIds);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (CollectionUtils.isEmpty(roles)){
            return result;
        }
        Set<String> roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
        result.put("roleSet",roleNames);
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        roleResourceExample.createCriteria().andRoleIdIn(roleIds);
        List<RoleResource> roleResources = roleResourceMapper.selectByExample(roleResourceExample);
        if (CollectionUtils.isEmpty(roleResources)){
            return result;
        }
        List<Integer> resourceIds = roleResources.stream().map(RoleResource::getResourceId).distinct().collect(Collectors.toList());
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andIdIn(resourceIds);
        List<Resource> resources = resourceMapper.selectByExample(resourceExample);
        if (CollectionUtils.isEmpty(resources)){
            return result;
        }
        Set<String> resourceNameList = resources.stream().map(Resource::getName).collect(Collectors.toSet());
        result.put("resourceNameList",resourceNameList);
        return result;
    }

}
