package com.zw.cloud.tools.controller.ocr;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.zw.cloud.tools.modle.vo.OcrVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * 文字识别
 * https://ai.baidu.com/ai-doc/OCR/Nkibizxlf
 */
@RequestMapping("/ocr")
@RestController
public class OcrController {

    @Autowired
    private AipOcr client;

    /**
     * 高精度识别
     */
    @PostMapping("/highPrecisionImageRecognition")
    //http://localhost:9040/ocr/highPrecisionImageRecognition
    public OcrVO highPrecisionImageRecognition(MultipartFile file) throws Exception{
        // 传入可选参数调用接口
        HashMap<String, String> options = buildOptionsHashMap();
        // 参数为本地图片路径
        /*String image = "test.jpg";
        JSONObject res = client.basicAccurateGeneral(image, options);
        System.out.println(res.toString(2));*/

        // 通用文字识别（含生僻字版）, 图片参数为远程url图片
       /* JSONObject res = client.enhancedGeneralUrl(url, options);
        System.out.println(res.toString(2));*/

        // 参数为本地图片二进制数组
        byte[] bytes = file.getBytes();
        JSONObject res = client.basicAccurateGeneral(bytes, options);
        //System.out.println(res.toString(2));
        return JSON.parseObject(JSON.toJSONString(res), OcrVO.class);
    }

    /**
     * 网络图片文字识别
     */
    @PostMapping("/netImageRecognition")
    //http://localhost:9040/ocr/netImageRecognition
    public OcrVO netImageRecognition(MultipartFile file) throws Exception{
        HashMap<String, String> options = buildOptionsHashMap();
        /* // 参数为本地图片路径
        String image = "test.jpg";
        JSONObject res = client.webImage(image, options);
        System.out.println(res.toString(2));*/

        // 网络图片文字识别, 图片参数为远程url图片
        /*JSONObject res = client.webImageUrl(url, options);
        System.out.println(res.toString(2));*/

        // 参数为本地图片二进制数组
        byte[] bytes = file.getBytes();
        JSONObject res = client.basicAccurateGeneral(bytes, options);
        return JSON.parseObject(JSON.toJSONString(res), OcrVO.class);
    }

    /**
     * 身份证识别
     */
    @PostMapping("/icCardRecognition")
    //http://localhost:9040/ocr/icCardRecognition
    public OcrVO icCardRecognition(MultipartFile file) throws Exception{
        HashMap<String, String> options = buildOptionsHashMap();
        String idCardSide = "back";

        // 参数为本地图片路径
       /* String image = "test.jpg";
        JSONObject res = client.idcard(image, idCardSide, options);
        System.out.println(res.toString(2));*/

        byte[] bytes = file.getBytes();
        JSONObject res = client.idcard(bytes, idCardSide, options);
        return JSON.parseObject(JSON.toJSONString(res), OcrVO.class);
    }

    /**
     * 手写文字识别
     */
    @PostMapping("/handwritingRecognition")
    //http://localhost:9040/ocr/handwritingRecognition
    public OcrVO handwritingRecognition(MultipartFile file) throws Exception{
        // 传入可选参数调用接口
        HashMap<String, String> options = buildOptionsHashMap();
        options.put("recognize_granularity","big");
        // 参数图片url
        /*String url = "http://localhost/test.jpg"
        res = client.handwritingUrl(url, options);
        System.out.println(res.toString(2));*/

        JSONObject res = client.handwriting(file.getBytes(), options);
        return JSON.parseObject(JSON.toJSONString(res), OcrVO.class);
    }

    /**
     * 二维码识别
     */
    @PostMapping("/codeRecognition")
    //http://localhost:9040/ocr/codeRecognition
    public String codeRecognition(MultipartFile file) throws Exception{
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        // 参数图片url
       /* String url = "http://localhost/test.jpg"
        res = client.qrcodeUrl(url, options);
        System.out.println(res.toString(2));
        byte[] bytes = file.getBytes();*/
        JSONObject res = client.qrcode(file.getBytes(), options);
        return res.toString();
    }

    private HashMap<String, String> buildOptionsHashMap() {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>(8);
        options.put("language_type", "CHN_ENG");

        //是否检测图像朝向，
        options.put("detect_direction", "false");
        //是否返回识别结果中每一行的置信度
        options.put("probability", "false");
        return options;
    }

}
