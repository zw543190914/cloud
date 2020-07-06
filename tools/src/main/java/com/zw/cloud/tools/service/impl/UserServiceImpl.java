/*
package com.zw.cloud.tools.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.zw.cloud.db.dao.UserMapper;
import com.zw.cloud.db.entity.User;
import com.zw.cloud.db.entity.UserExample;
import com.zw.cloud.tools.modle.vo.ParamVO;
import com.zw.cloud.tools.utils.BaiduAiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private BaiduAiUtil baiduAiUtil;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Transactional
    public void insert(User user, MultipartFile file)throws Exception{
        // data url
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        logger.info("[UserServiceImpl][insert] faceImage is {}","data:image/jpg;base64," + faceImage);
        user.setImage(faceImage);
        // 上传至 七牛云
        */
/*MyPutRet myPutRet = fileService.upload(user.getName(), file);
        user.setImage(myPutRet.getKey());*//*

        userMapper.insertSelective(user);
        faceRegisterOrUpdate(String.valueOf(user.getId()),faceImage);

    }

    @Transactional
    public void update(User user, MultipartFile file)throws Exception{
        Preconditions.checkNotNull(user.getId());
        String faceImage = Base64.getEncoder().encodeToString(file.getBytes());
        logger.info("[UserServiceImpl][update] faceImage is {}","data:image/jpg;base64," + faceImage);
        faceRegisterOrUpdate(String.valueOf(user.getId()),faceImage);
        //user.setImage(faceImage);
        // 上传至 七牛云
        */
/*MyPutRet myPutRet = fileService.upload(user.getName(), file);
        user.setImage(myPutRet.getKey());*//*

        userMapper.updateByPrimaryKeySelective(user);
    }

    public PageInfo<User> query(ParamVO paramVO) {
        PageHelper.startPage(paramVO.getPageNo(),paramVO.getPageSize());
        List<User> userList = userMapper.selectByExampleWithBLOBs(new UserExample());
        return new PageInfo<>(userList) ;
    }

    private void faceRegisterOrUpdate(String userId,String faceImage){
        // 检测 人脸图片
        Boolean aBoolean = baiduAiUtil.faceCheck(faceImage);
        if (! aBoolean){
            throw new RuntimeException("人脸图片无效");
        }
        boolean faceExit = baiduAiUtil.faceExit(userId);
        Boolean face;
        if (faceExit){
            face = baiduAiUtil.faceUpdate(userId, faceImage);
        } else {
            // 上传到百度 AI
            face = baiduAiUtil.faceRegister(userId, faceImage);
        }
        if (!face){
            throw new RuntimeException("图片上传失败");
        }
    }
}
*/
