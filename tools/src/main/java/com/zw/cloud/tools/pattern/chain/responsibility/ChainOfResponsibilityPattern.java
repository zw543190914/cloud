package com.zw.cloud.tools.pattern.chain.responsibility;


public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        OneHandler oneHandler = new OneHandler();
        TwoHandler twoHandler = new TwoHandler();
        ThreeHandler threeHandler = new ThreeHandler();
        oneHandler.setNext(twoHandler);
        twoHandler.setNext(threeHandler);
        oneHandler.handleRequest("ss");
    }
}
