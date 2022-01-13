package com.zw.cloud.tools.jvm;

import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutTest {

    static class A  {
    }
    static class B {
        int a = 1;
        int b = 2;
    }

    static class C {
        int a = 1;
        int b = 2;
        int[] c = {3,4,5};
    }

    static int[] array = {10,11,12};

    public static void main(String[] args) {
        A a = new A();
        /**
         * Mark Word: 32位机->32bit(4byte)  64位机->64bit(8byte)
         * 空对象：
         *  开启指针压缩：16 = 8(mark word) + 4(类型指针) + 0(数组长度) + 0(实例数据) + 4(对齐填充)
         *  关闭指针压缩：16 = 8(mark word) + 8(类型指针) + 0(数组长度) + 0(实例数据) + 0(对齐填充)
         */
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        /**
         * B对象：
         *  开启指针压缩：24 = 8(mark word) + 4(类型指针) + 0(数组长度) + 4+4(实例数据) + 4(对齐填充)
         *  关闭指针压缩：16 = 8(mark word) + 8(类型指针) + 0(数组长度) + 4+4(实例数据) + 0(对齐填充)
         */
        B b = new B();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(b).toPrintable());

        /**
         * C对象：
         *  开启指针压缩：24 = 8(mark word) + 4(类型指针) + 0(数组长度) + 4+4+4(实例数据) + 0(对齐填充)
         *  关闭指针压缩：32 = 8(mark word) + 8(类型指针) + 0(数组长度) + 4+4+8(实例数据) + 0(对齐填充)
         */
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());

        /**
         * array ：
         *  开启指针压缩：32 = 8(mark word) + 4(类型指针) + 4(数组长度) + 12(实例数据) + 4(对齐填充)
         *  关闭指针压缩 数组对象出现两个对齐填充
         *  关闭指针压缩：40 = 8(mark word) + 8(类型指针) + 4(数组长度) + 4(对其填充) +12(实例数据) + 4(对齐填充)
         */
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(array).toPrintable());

        //查看对象占用空间总大小
       /* System.out.println(GraphLayout.parseInstance(a).totalSize());
        //查看对象外部信息：包括引用的对象：
        System.out.println(GraphLayout.parseInstance(a).toPrintable());*/
    }
}
