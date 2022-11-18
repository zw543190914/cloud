package com.zw.cloud.tools.pattern.decoration;

/**
 * 装饰器模式
 * 装饰类主要是需要定义一个通用的抽象方法或者接口，供实体对象和装饰对象继承，并通过装饰类修饰该对象
 * https://mp.weixin.qq.com/s?__biz=Mzg3OTU5NzQ1Mw==&mid=2247484623&idx=1&sn=358e3ff278dee0f5960f0b44c25de8f9&scene=21#wechat_redirect
 */
public class DuckDecoration extends Duck {

    @Override
    public String getDesc() {
        System.out.println("DuckDecoration");
        return super.getDesc();
    }

    public static void main(String[] args) {
        DuckDecoration duckDecoration = new DuckDecoration();
        System.out.println(duckDecoration.getDesc());
    }

}
