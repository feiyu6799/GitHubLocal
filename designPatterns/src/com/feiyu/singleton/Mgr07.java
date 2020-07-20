package com.feiyu.singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * 完美写法（不能避免反序列化）
 */
public class Mgr07 {

    private Mgr07() {
    }

    private static class Mgr07Holder {//加载外部类时静态内部类不会加载，只有调用静态内部类时才会加载
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        return Mgr07Holder.INSTANCE;//这里才加载静态内部类
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(()->{
                System.out.println(Mgr07.getInstance().hashCode());
            }).start();
        }
    }


}
