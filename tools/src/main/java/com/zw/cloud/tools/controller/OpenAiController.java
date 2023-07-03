package com.zw.cloud.tools.controller;

import com.alibaba.fastjson2.JSON;
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
public class OpenAiController {

    @NotNeedResponseAutoWrapper
    //http://localhost:9040/chatGPT?text=true&prompt=
    public List<CompletionChoice> getAiResult(@RequestParam boolean text,@RequestParam String prompt) {
        return OpenAiUtils.getAiResult(text,prompt);
    }

    @GetMapping("/chat")
    //http://localhost:9040/chatGPT/chat?msg=java
    public String chat(@RequestParam String msg){
        List<CompletionChoice> aiResultList = OpenAiUtils.getAiResult(true, msg);
        log.info("[OpenAiController][sendByDingTalk]aiResultList is {}", JSON.toJSON(aiResultList));
        String result = aiResultList.stream().map(CompletionChoice::getText).collect(Collectors.joining("<\br>"));
        result = result.replaceAll("\\n\n", "<br>");
        return result.replaceAll("\\n","<br>");
    }
}
