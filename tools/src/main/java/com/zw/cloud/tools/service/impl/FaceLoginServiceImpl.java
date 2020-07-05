package com.zw.cloud.tools.service.impl;

import com.zw.cloud.db.dao.CodeMapper;
import com.zw.cloud.db.dao.UserMapper;
import com.zw.cloud.db.entity.Code;
import com.zw.cloud.db.entity.User;
import com.zw.cloud.tools.utils.BaiduAiUtil;
import com.zw.cloud.tools.utils.ZXingCodeSimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FaceLoginServiceImpl {

    @Autowired
    private CodeMapper codeMapper;
    @Autowired
    private BaiduAiUtil baiduAiUtil;
    @Autowired
    private UserMapper userMapper;

    @Value("${project.login.url}")
    private String url;

    public String getQRCode() throws Exception {
        // 状态计入 redis -1:默认，未使用，0:失败，1：成功
        //redisTemplate.boundValueOps(getCacheKey(code)).set(result, 30, TimeUnit.MINUTES);
        Code code = new Code();
        code.setStatus((byte) -1);
        codeMapper.insertSelective(code);
        return ZXingCodeSimpleUtils.crateQRCode(url + "?code=" + code.getId());
    }


    public Code checkQRCode(Integer code) {
        // 不断去redis检查登录状态
        //String cacheKey = getCacheKey(code);
        //FaceLoginResult result = (FaceLoginResult) redisTemplate.opsForValue().get(cacheKey);
        Code result = codeMapper.selectByPrimaryKey(code);
        return result;
    }

    public boolean loginByFace(Integer code, MultipartFile attachment) throws Exception {
        String userId = baiduAiUtil.faceSearch(Base64Utils.encodeToString(attachment.getBytes()));
        boolean isSuccess = false;
        byte status = 0;
        if (userId != null) {
            User user = userMapper.selectByPrimaryKey(Long.valueOf(userId));
            if (user != null) {
                // 进行 shiro/Jwt 登录
                isSuccess = true;
                status = 1;
            }
        }
        // 登录 成功或者 失败 都要修改 redis code状态   -1:未使用，0:失败，1：成功
        //redisTemplate.boundValueOps(getCacheKey(code)).set(result, 30, TimeUnit.MINUTES);
        Code code1 = new Code();
        code1.setId(code);
        code1.setStatus(status);
        codeMapper.updateByPrimaryKeySelective(code1);
        return isSuccess;
    }


}
