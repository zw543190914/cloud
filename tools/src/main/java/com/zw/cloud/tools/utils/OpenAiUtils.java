package com.zw.cloud.tools.utils;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.zw.cloud.tools.entity.OpenAi;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * 调用OpenAi的49中方法
 */
public class OpenAiUtils {

    /**
     * 获取ai
     */
    public static List<CompletionChoice> getAiResult(boolean text,String prompt) {

        OpenAi openAi = new OpenAi();
        if (text) {
            openAi.setModel("text-davinci-003");
        } else {
            openAi.setModel("code-davinci-002");
        }
        openAi.setPrompt(prompt);
        // 0.0 0.1
        openAi.setTemperature(0.0D);
        openAi.setTopP(1D);
        openAi.setPresencePenalty(0D);
        openAi.setFrequencyPenalty(0D);
        //openAi.setStop(Lists.newArrayList("#",";"));
        String OPENAPI_TOKEN = "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";
        Integer TIMEOUT = 60000;
        OpenAiService service = new OpenAiService(OPENAPI_TOKEN, TIMEOUT);
        CompletionRequest.CompletionRequestBuilder builder = CompletionRequest.builder()
                .model(openAi.getModel())
                .prompt(prompt)
                .temperature(openAi.getTemperature())
                .maxTokens(1000)
                .topP(openAi.getTopP())
                .frequencyPenalty(openAi.getFrequencyPenalty())
                .presencePenalty(openAi.getPresencePenalty());
        if (CollectionUtils.isNotEmpty(openAi.getStop())) {
            builder.stop(openAi.getStop());
        }
        CompletionRequest completionRequest = builder.build();
        return service.createCompletion(completionRequest).getChoices();
    }


}

