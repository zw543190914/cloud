package com.zw.cloud.tools.utils;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 百度人脸识别
 * http://ai.baidu.com/ai-doc/FACE/8k37c1rqz#%E4%BA%BA%E8%84%B8%E6%90%9C%E7%B4%A2-mn-%E8%AF%86%E5%88%AB
 */

@Component
@Slf4j
public class BaiduAiUtil {

    @Value("${ai.appId:1}")
    private String APP_ID;
    @Value("${ai.apiKey:1}")
    private String API_KEY;
    @Value("${ai.secretKey:1}")
    private String SECRET_KEY;
    @Value("${ai.imageType}")
    private String IMAGE_TYPE;
    @Value("${ai.groupId}")
    private String groupId;

    private AipFace client;

    private HashMap<String, String> options = new HashMap<>();

    public BaiduAiUtil() {
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
    }


    @PostConstruct
    public void init() {
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        log.info("[BaiduAiUtil][init] client is {}", JSON.toJSONString(client));
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
    public Boolean faceUpdate(String userId, String image) {
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

    // 1：N人脸搜索：也称为1：N识别，在指定人脸集合中，找到最相似的人脸；
    public JSONObject searchByUserId(String usrId,String image) {
        // 传入可选参数调用接口
        options.put("max_face_num", "3");
        options.put("match_threshold", "70");
        options.put("user_id", usrId);
        options.put("max_user_num", "3");
        String groupIdList = "1";
        // 人脸搜索
        return client.search(image, IMAGE_TYPE, groupIdList, options);
    }

    // 两张人脸图片相似度对比
    public JSONObject sample() {
        String image1 = "base64_1";
        String image2 = "base64_2";

        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image1, "BASE64");
        MatchRequest req2 = new MatchRequest(image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);
        return client.match(requests);
    }

    // 删除用户
    public JSONObject delete(String groupId,String userId) {
        return client.deleteUser(groupId, userId, options);
    }
    // 身份验证 推荐阈值0.8，超过即判断为同一人
    public JSONObject checkPersonByIdCard(String image,String idCardNumber,String name) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        String imageType = "BASE64";
        // 身份验证
        JSONObject jsonObject = client.personVerify(image, imageType, idCardNumber, name, options);
        log.info("[BaiduAiUtil][init] checkPersonByIdCard jsonObject is {}", jsonObject.toString());
        return jsonObject;


    }
}
 
