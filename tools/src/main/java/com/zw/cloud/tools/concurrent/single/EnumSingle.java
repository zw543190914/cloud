package com.zw.cloud.tools.concurrent.single;

import java.lang.reflect.Constructor;

public enum EnumSingle {
    INSTANCE;

}

class Test{
    public static void main(String[] args)throws Exception {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        EnumSingle instance2 = EnumSingle.INSTANCE;
        System.out.println(instance1);
        System.out.println(instance2);
        /**
         * NoSuchMethodException: com.zw.cloud.tools.concurrent.single.EnumSingle.<init>()
         */
        Constructor<EnumSingle> constructor = EnumSingle.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        EnumSingle instance3 = constructor.newInstance();
        System.out.println(instance3);

    }
}
