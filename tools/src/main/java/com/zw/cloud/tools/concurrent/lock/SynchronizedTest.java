package com.zw.cloud.tools.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * 静态方法锁的class,非静态锁调用者对象
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        Phone1 phone = new Phone1();
        Phone1 phone1 = new Phone1();
        Phone2 phone2 = new Phone2();

 /*       new Thread(phone::sendMsg,"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(phone::call,"B").start();*/

        new Thread(()-> {
            Phone2.sendMsg();
        },"B").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()-> {
           Phone2.call();
        },"B").start();
        System.out.println("done");
    }

}

class Phone1 {

    public synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("phone1 发消息");
    }

    public synchronized void call() {
        System.out.println("phone1 打电话");
    }
}

class Phone2 {

   public static synchronized void sendMsg() {
       try {
           TimeUnit.SECONDS.sleep(4);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("phone2 发消息");
   }

   public static synchronized void call() {
       System.out.println("phone2 打电话");
   }
}

class Notify {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

class Data{
    public int num = 0;

    // +1
    public synchronized void increase()throws Exception{
        while (num != 0){
            this.wait();
        }
        num ++;
        System.out.println(Thread.currentThread().getName() + "==" + num);
        this.notifyAll();
    }

    public synchronized void decrease() throws Exception{
        while (num == 0){
            this.wait();
        }
        num --;
        System.out.println(Thread.currentThread().getName() + "==" + num);
        this.notifyAll();
    }
}