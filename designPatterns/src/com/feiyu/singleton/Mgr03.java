package com.feiyu.singleton;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 有bug
 */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03() {//private修饰控制不能在其它地方new 出对象
    }

    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(()->//省略接口中只有一个方法（省略new Runnable中只有一个run方法）
                System.out.println(Mgr03.getInstance().hashCode())//同一个类的不同对象的hashCode是不同的
            ).start();
        }
    }
}
