package com.zw.cloud.tools.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import com.zw.cloud.tools.modle.vo.AliyunDataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AliyunAiUtils {

    private Logger logger = LoggerFactory.getLogger(AliyunAiUtils.class);

    @Value("${aliyun.appId}")
    private String API_KEY;
    @Value("${aliyun.secretKey}")
    private String SECRET_KEY;
    @Value("${aliyun.area}")
    private String AREA;

    private  String version = "2018-12-03";
    private  String domain = "face.cn-shanghai.aliyuncs.com";
    private  String aliyunUrl = "https://dtplus-cn-shanghai.data.aliyuncs.com/face/attribute";

    private DefaultAcsClient client;

    @PostConstruct
    public void init() {
        DefaultProfile profile = DefaultProfile.getProfile(AREA, API_KEY, SECRET_KEY);
        client = new DefaultAcsClient(profile);
        logger.info("[AliyunAiUtils][init] client is {}", JSON.toJSONString(client));
    }


    public boolean addFace(String id,String name,String image){
        CommonResponse addFace = oparationFace( "AddFace",id,name,image);
        AliyunDataVO aliyunDataVO = JSON.parseObject(addFace.getData(), AliyunDataVO.class);
        return "ok".equals(aliyunDataVO.getData());
    }

    public boolean deleteFace(String id,String name){
        CommonResponse deleteFace = oparationFace("DeleteFace",id,name,null);
        AliyunDataVO aliyunDataVO = JSON.parseObject(deleteFace.getData(), AliyunDataVO.class);
        return "ok".equals(aliyunDataVO.getData());

    }

    // 查找注册库中的人脸
    public CommonResponse recognizeFace(String image)throws Exception{
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("face.cn-shanghai.aliyuncs.com");
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction("RecognizeFace");
        request.putBodyParameter("Group", "default"); //待识别的人脸分组
        //request.putBodyParameter("ImageUrl", "http://xxx.xx.com/xx.jpg"); //检测图片的URL
        request.putBodyParameter("Content", image);  //检测图片的内容，Base64编码
        try {
            CommonResponse recognizeFace = client.getCommonResponse(request);
            //建议用户取判决阈值的最佳值为0.65
            String data = recognizeFace.getData();
            System.out.println(JSON.toJSONString(recognizeFace));
            logger.info("[AliyunAiUtils][recognizeFace] commonResponse is {}", JSON.toJSONString(recognizeFace));
            return recognizeFace;
        } catch (ClientException e) {
            logger.error("[AliyunAiUtils][recognizeFace] error is ", e);
            throw e;
        }
    }

    //人脸属性识别 face_num  int  检测出来的人脸个数
    public String checkFace(String image){
        Map<String,Object> map = new HashMap<>();
        //0: 通过url识别，参数image_url不为空；1: 通过图片content识别，参数content不为空
        map.put("type",1);
        //image = "data:image/jpg;base64," + image;
        logger.info("[AliyunAiUtils][checkFace] image is {}", image);
        map.put("content",image);
        try {
            String doPostJson = HttpClientUtils.doPostJson(aliyunUrl, JSON.toJSONString(map), null);
            logger.info("[AliyunAiUtils][checkFace] doPostJson is {}", doPostJson);
            return doPostJson;
        } catch (Exception e) {
            logger.error("[AliyunAiUtils][checkFace] error is ", e);
            throw e;
        }

    }


    public CommonResponse listFace()throws Exception{
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction("ListFace");
        request.putBodyParameter("Group", "default");
        try {
            return client.getCommonResponse(request);
        } catch (ClientException e) {
            logger.error("[AliyunAiUtils][listFace] error is ", e);
            throw e;
        }
    }



    private CommonResponse oparationFace(String action,String id,String name,String image){
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putBodyParameter("Group", "default"); //添加人脸的分组
        request.putBodyParameter("Person", name); //添加人脸的姓名
        request.putBodyParameter("Image", id);   //添加人脸的编号
        //request.putBodyParameter("ImageUrl", "http://xxx.xx.com/xx.jpg"); //检测图片的URL
        if (null != image){
            request.putBodyParameter("Content", image);  //检测图片的内容，Base64编码
        }
        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
            logger.info("[AliyunAiUtils][oparationFace] commonResponse is {}", JSON.toJSONString(commonResponse));
            return commonResponse;

        } catch (ClientException e) {
            logger.error("[AliyunAiUtils][oparationFace] error is ", e);
            throw new RuntimeException("调用人脸识别服务出错");
        }
    }
}
