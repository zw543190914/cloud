package com.zw.cloud.tools.pattern.chain.responsibility;

import java.util.Objects;

public class OneHandler extends Handler {

    @Override
    public void handleRequest(String request) {
        System.out.println("handle one...");
        if (Objects.nonNull(getNext())) {
            getNext().handleRequest(request);
        }
    }
}
