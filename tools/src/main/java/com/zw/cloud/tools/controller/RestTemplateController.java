package com.zw.cloud.tools.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.modle.vo.TcResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.*;

@RestController
@RequestMapping("/rest/template")
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    //http://localhost:9040/rest/template
    public void testGet(){
        String url = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=30&isVerify=1&pageNo=1";
        TcResultVO resultVO = restTemplate.getForObject(url, TcResultVO.class);
        System.out.println(JSONUtil.toJsonStr(resultVO));
    }

    @GetMapping("/testParallelStream")
    //http://localhost:9040/rest/template/testParallelStream
    public List<User> testParallelStream(){

        User user1 = new User();
        user1.setName("202203221045131506099649836695554.jpg");
        User user2 = new User();
        user2.setName("202203221045221506099688302657538.jpg");
        User user3 = new User();
        user3.setName("202203221045261506099707722285057.jpg");
        User user4 = new User();
        user4.setName("202203221045311506099728773496834.jpg");

        User user5 = new User();
        user5.setName("202203221045131506099649836695554.jpg");
        User user6 = new User();
        user6.setName("202203221045221506099688302657538.jpg");
        User user7 = new User();
        user7.setName("202203221045261506099707722285057.jpg");
        User user8 = new User();
        user8.setName("202203221045311506099728773496834.jpg");
        List<User> userList = Lists.newArrayList(user1,user2,user3,user4,user5,user6,user7,user8);
        userList.parallelStream().forEach(user -> {
            OssFileDTO ossFileDTO = queryAttachmentUrlById(user.getName());
            user.setDescription(ossFileDTO.getTokenUrl());
        });
       return userList;
    }

    private OssFileDTO queryAttachmentUrlById(String filename) {
        Map<String,Object> param = new HashMap<>();
        param.put("rootContent","private");
        param.put("subContent","dyeing-sc-link");
        param.put("fileName",filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("accessToken", "E26F23FE62494AD4B5CCBA57E59F0748");
        headers.add("clientId", "B4263529337148489E88A215BE562CF8");
        headers.add("orgCode", "devController");
        headers.add("userId", "E5F887E61DED452E9AD28E84746D49E9");
        HttpEntity<Object> httpEntity = new HttpEntity<>(param,headers);

        JSONObject jsonObject = restTemplate.exchange("https://dev-i.szzhijing.com/dyeing-config/oss/getGetTokenUrl",
                HttpMethod.POST, httpEntity, JSONObject.class).getBody();
        if (Objects.isNull(jsonObject)) {
            return new OssFileDTO();
        }
        return jsonObject.getObject("data", OssFileDTO.class);

    }

    public static class OssFileDTO implements Serializable {
        private String uuidFileName;
        private String tokenUrl;

        public String getUuidFileName() {
            return uuidFileName;
        }

        public void setUuidFileName(String uuidFileName) {
            this.uuidFileName = uuidFileName;
        }

        public String getTokenUrl() {
            return tokenUrl;
        }

        public void setTokenUrl(String tokenUrl) {
            this.tokenUrl = tokenUrl;
        }
    }
}
