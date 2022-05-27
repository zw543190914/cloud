package com.zw.cloud.tools.mqtt.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.tools.mqtt.config.MqttConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengwei
 * @date 2022/5/25 13:36
 */
@RestController
@RequestMapping("/mqtt")
public class MqttSendController {

    @Autowired
    private MqttConfig mqttClient;

    @RequestMapping("/sendMessage")
    //http://localhost:9040/mqtt/sendMessage
    public WebResult<Boolean> sendMessage(){
        String topic = "d/qa_test_device_stenter_02/report";
        String message = "{\n" +
                "\"stenterStatus\":{\n" +
                "    \"rtime\": 1653460539,     \n" +
                "    \"ctime\": 1653460539,    \n" +
                "    \"mt\": 1,\n" +
                "    \"ec\": 1,\n" +
                "\t\"d01\":\t23,\n" +
                "\t\"d02\":\t23,\n" +
                "\t\"d03\":\t23,\n" +
                "\t\"d04\":\t23,\n" +
                "\t\"d05\":\t23,\n" +
                "\t\"d06\":\t23,\n" +
                "\t\"d07\":\t23,\n" +
                "\t\"d08\":\t23,\n" +
                "\t\"d09\":\t23,\n" +
                "\t\"d10\":\t23,\n" +
                "\t\"d11\":\t23,\n" +
                "\t\"d12\":\t23,\n" +
                "\t\"d13\":\t23,\n" +
                "\t\"d14\":\t23,\n" +
                "\t\"d15\":\t23,\n" +
                "\t\"d16\":\t23,\n" +
                "\t\"d17\":\t23,\n" +
                "\t\"d18\":\t23,\n" +
                "\t\"d19\":\t23,\n" +
                "\t\"d20\":\t23,\n" +
                "\t\"d21\":\t23,\n" +
                "\t\"d22\":\t23,\n" +
                "\t\"d23\":\t23,\n" +
                "\t\"d24\":\t23,\n" +
                "\t\"d25\":\t23,\n" +
                "\t\"d26\":\t23,\n" +
                "\t\"d27\":\t23,\n" +
                "\t\"d28\":\t23,\n" +
                "\t\"d29\":\t23,\n" +
                "\t\"d30\":\t23,\n" +
                "\t\"d31\":\t23,\n" +
                "\t\"d32\":\t23,\n" +
                "\t\"d33\":\t23,\n" +
                "\t\"d34\":\t23,\n" +
                "\t\"d35\":\t23,\n" +
                "\t\"d36\":\t23,\n" +
                "\t\"d37\":\t23,\n" +
                "\t\"d38\":\t23,\n" +
                "\t\"d39\":\t23,\n" +
                "\t\"d40\":\t23,\n" +
                "\t\"d41\":\t23,\n" +
                "\t\"d42\":\t23,\n" +
                "\t\"d43\":\t23,\n" +
                "\t\"d44\":\t23,\n" +
                "\t\"d45\":\t23,\n" +
                "\t\"d46\":\t23,\n" +
                "\t\"d47\":\t23,\n" +
                "\t\"d48\":\t23,\n" +
                "\t\"d49\":\t23,\n" +
                "\t\"d50\":\t23,\n" +
                "\t\"d51\":\t23,\n" +
                "\t\"d52\":\t23,\n" +
                "\t\"d53\":\t23,\n" +
                "\t\"d54\":\t23,\n" +
                "\t\"d55\":\t23,\n" +
                "\t\"d56\":\t23,\n" +
                "\t\"d57\":\t23,\n" +
                "\t\"d58\":\t23,\n" +
                "\t\"d59\":\t23,\n" +
                "\t\"d60\":\t23,\n" +
                "\t\"d61\":\t23,\n" +
                "\t\"d62\":\t23,\n" +
                "\t\"d63\":\t23,\n" +
                "\t\"d64\":\t23,\n" +
                "\t\"d65\":\t23,\n" +
                "\t\"d66\":\t23,\n" +
                "\t\"d67\":\t23,\n" +
                "\t\"d68\":\t23,\n" +
                "\t\"d69\":\t23,\n" +
                "\t\"d70\":\t23,\n" +
                "\t\"d71\":\t23,\n" +
                "\t\"d72\":\t23\n" +
                "  }\n" +
                "}";
        try {
            mqttClient.publish(topic, message);
            return WebResult.build(true);
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.build(false);
        }
    }
}
