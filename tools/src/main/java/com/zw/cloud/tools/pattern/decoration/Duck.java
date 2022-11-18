package com.zw.cloud.tools.pattern.decoration;

public class Duck extends Food {
    public Duck() {
        this.desc = "鸭肉";
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
