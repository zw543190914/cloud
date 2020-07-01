package com.zw.cloud.activiti.controller.activiti.modle;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.cloud.activiti.dao.*;
import com.zw.cloud.activiti.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/activiti/modle")
public class ActivitiModleController {

    @Autowired
    private ActReDeploymentMapper actReDeploymentMapper;

    @Autowired
    private ActReProcdefMapper actReProcdefMapper;

    @Autowired
    private ActHiProcinstMapper hiProcinstMapper;

    @Autowired
    private ActHiDetailMapper hiDetailMapper;

    @Autowired
    private ActHiTaskinstMapper hiTaskinstMapper;

    @Autowired
    private ActHiActinstMapper hiActinstMapper;

    @Autowired
    private ActRuTaskMapper ruTaskMapper;

    @Autowired
    private ActGeBytearrayMapper geBytearrayMapper;

    @GetMapping("/queryDeployment")
    //http://localhost:9020/activiti/modle/queryDeployment?pageNo=1&pageSize=10&key=
    public Object queryDeployment(Integer pageNo,Integer pageSize,String key){
        ActReDeploymentExample example = new ActReDeploymentExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(key)){
            example.createCriteria().andKeyEqualTo(key);
            return actReDeploymentMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(actReDeploymentMapper.selectByExample(example));
    }

    @GetMapping("/queryProcdef")
    //http://localhost:9020/activiti/modle/queryProcdef?pageNo=1&pageSize=10&key=
    public Object queryProcdef(Integer pageNo,Integer pageSize,String key){
        ActReProcdefExample example = new ActReProcdefExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(key)){
            example.createCriteria().andKeyEqualTo(key);
            return actReProcdefMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(actReProcdefMapper.selectByExample(example));
    }

    @GetMapping("/queryHiProcinst")
    //http://localhost:9020/activiti/modle/queryHiProcinst?pageNo=1&pageSize=10&procInstId=
    public Object queryHiProcinst(Integer pageNo,Integer pageSize,String procInstId){
        ActHiProcinstExample example = new ActHiProcinstExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(procInstId)){
            example.createCriteria().andProcInstIdEqualTo(procInstId);
            return hiProcinstMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(hiProcinstMapper.selectByExample(example));
    }

    @GetMapping("/queryHiDetail")
    //http://localhost:9020/activiti/modle/queryHiDetail?pageNo=1&pageSize=10&procInstId=
    public Object queryHiDetail(Integer pageNo,Integer pageSize,String procInstId){
        ActHiDetailExample example = new ActHiDetailExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(procInstId)){
            example.createCriteria().andProcInstIdEqualTo(procInstId);
            return hiDetailMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(hiDetailMapper.selectByExample(example));
    }

    @GetMapping("/queryHiTaskinst")
    //http://localhost:9020/activiti/modle/queryHiTaskinst?pageNo=1&pageSize=10&procInstId=924e0e4d-ba20-11ea-bed8-a0a4c5f4cb40
    public Object queryHiTaskinst(Integer pageNo,Integer pageSize,String procInstId){
        ActHiTaskinstExample example = new ActHiTaskinstExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(procInstId)){
            example.createCriteria().andProcInstIdEqualTo(procInstId);
            return hiTaskinstMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(hiTaskinstMapper.selectByExample(example));
    }

    @GetMapping("/queryHiActinst")
    //http://localhost:9020/activiti/modle/queryHiActinst?pageNo=1&pageSize=10&procInstId=
    public Object queryHiActinst(Integer pageNo,Integer pageSize,String procInstId){
        ActHiActinstExample example = new ActHiActinstExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(procInstId)){
            example.createCriteria().andProcInstIdEqualTo(procInstId);
            return hiActinstMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(hiActinstMapper.selectByExample(example));
    }

    @GetMapping("/queryRuTask")
    //http://localhost:9020/activiti/modle/queryRuTask?pageNo=1&pageSize=10&procInstId=
    public Object queryRuTask(Integer pageNo,Integer pageSize,String procInstId){
        ActRuTaskExample example = new ActRuTaskExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(procInstId)){
            example.createCriteria().andProcInstIdEqualTo(procInstId);
            return ruTaskMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(ruTaskMapper.selectByExample(example));
    }

    @GetMapping("/queryActGeBytearrays")
    //http://localhost:9020/activiti/modle/queryActGeBytearrays?pageNo=1&pageSize=10&procInstId=
    public Object queryActGeBytearrays(Integer pageNo,Integer pageSize){
        ActGeBytearrayExample example = new ActGeBytearrayExample();
        example.setOrderByClause("ID_ desc");
        PageHelper.startPage(pageNo,pageSize);
        List<ActGeBytearray> actGeBytearrays = geBytearrayMapper.selectByExampleWithBLOBs(example);
        return actGeBytearrays.stream().map(actGeBytearray -> {
            ActGeBytearrayVO bytearrayVO = new ActGeBytearrayVO();
            BeanUtils.copyProperties(actGeBytearray,bytearrayVO);
            try {
                bytearrayVO.setByteStr(new String(actGeBytearray.getBytes(),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            }
            return bytearrayVO;
        }).collect(Collectors.toList());
    }
}
