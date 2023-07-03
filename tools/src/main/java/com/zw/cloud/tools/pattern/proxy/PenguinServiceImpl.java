package com.zw.cloud.tools.pattern.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PenguinServiceImpl implements PenguinService{

    @Override
    public void beat() {
        log.info("[PenguinServiceImpl][beat]");
    }
}
