package com.zw.cloud.tools.pattern.chain.responsibility;

abstract class Handler {

    private Handler next;

    //处理请求的方法
    public abstract void handleRequest(String request);

    public void setNext(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }
}
