package com.zw.cloud.tools.utils;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.zw.cloud.tools.service.impl.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BaiduAiUtil {

    @Value("${ai.appId}")
    private String APP_ID;
    @Value("${ai.apiKey}")
    private String API_KEY;
    @Value("${ai.secretKey}")
    private String SECRET_KEY;
    @Value("${ai.imageType}")
    private String IMAGE_TYPE;
    @Value("${ai.groupId}")
    private String groupId;

    private AipFace client;

    private HashMap<String, String> options = new HashMap<>();

    public BaiduAiUtil() {
//        options.put("quality_control", "NORMAL");
//        options.put("liveness_control", "LOW");
    }
    private Logger logger = LoggerFactory.getLogger(BaiduAiUtil.class);


    @PostConstruct
    public void init() {
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        logger.info("[BaiduAiUtil][init] client is {}", JSON.toJSONString(client));
        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(5000);
//        client.setSocketTimeoutInMillis(60000);
    }

    // 人脸注册 ：将用户照片存入人脸库中
    public Boolean faceRegister(String userId, String image) {
        // 人脸注册
        JSONObject res = client.addUser(image, IMAGE_TYPE, groupId, userId, options);
        int errorCode = res.getInt("error_code");
        return errorCode == 0;
    }

    // 人脸更新 ：更新人脸库中的用户照片 
    public Boolean faceUpdate(String userId, String image) {    // 人脸更新
        JSONObject res = client.updateUser(image, IMAGE_TYPE, groupId, userId, options);
        int errorCode = res.getInt("error_code");
        return errorCode == 0;
    }

    //人脸检测：判断上传图片中是否具有面部头像 
    public Boolean faceCheck(String image) {
        JSONObject res = client.detect(image, IMAGE_TYPE, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject resultObject = res.getJSONObject("result");
            int faceNum = resultObject.getInt("face_num");
            return faceNum == 1;
        } else {
            return false;
        }
    }

    //人脸查找：查找人脸库中最相似的人脸并返回数据
    // 处理：用户的匹配得分（score）大于80分，即可认为是同一个用户
    public String faceSearch(String image) {
        JSONObject res = client.search(image, IMAGE_TYPE, groupId, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject result = res.getJSONObject("result");
            JSONArray userList = result.getJSONArray("user_list");
            if (userList.length() > 0) {
                JSONObject user = userList.getJSONObject(0);
                double score = user.getDouble("score");
                if (score > 80) {
                    return user.getString("user_id");
                }
            }
        }
        return null;
    }

    //人脸 是否存在 0存在
    public boolean faceExit(String userId) {
        JSONObject res = client.getUser(userId, groupId, null);
        return 0 == res.getInt("error_code");
    }

}
 
