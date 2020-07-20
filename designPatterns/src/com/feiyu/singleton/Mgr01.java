package com.feiyu.singleton;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 * Class.forName("")
 * 成立
 */
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();//重点，防止在其它类中new一个对像
    //final必须初始化，如果不初始化，必须跟上静态代码块进行初始化 static{}
    private Mgr01() {};

    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1 == m2);
    }
}

