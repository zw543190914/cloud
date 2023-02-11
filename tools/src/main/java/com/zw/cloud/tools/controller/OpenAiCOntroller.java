package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSON;
import com.theokanning.openai.completion.CompletionChoice;
import com.zw.cloud.common.annotation.NotNeedResponseAutoWrapper;
import com.zw.cloud.common.entity.dto.DingTalkRequestDTO;
import com.zw.cloud.common.utils.DingTalkUtils;
import com.zw.cloud.tools.utils.OpenAiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatGPT")
@Slf4j
public class OpenAiCOntroller {

    @NotNeedResponseAutoWrapper
    //http://localhost:9040/chatGPT?text=true&prompt=java 冒泡排序
    public List<CompletionChoice> getAiResult(@RequestParam boolean text,@RequestParam String prompt) {
        return OpenAiUtils.getAiResult(text,prompt);
    }

    @GetMapping("/chat")
    //http://localhost:9040/chat/sendByDingTalk?msg=java 冒泡排序
    public String chat(@RequestParam String msg){
        List<CompletionChoice> aiResultList = OpenAiUtils.getAiResult(true, msg);
        log.info("[OpenAiCOntroller][sendByDingTalk]aiResultList is {}", JSON.toJSON(aiResultList));
        String result = aiResultList.stream().map(CompletionChoice::getText).collect(Collectors.joining("<\br>"));
        result = result.replaceAll("\\n\n", "<br>");
        return result.replaceAll("\\n","<br>");
    }
}
