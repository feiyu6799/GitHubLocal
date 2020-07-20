package com.feiyu.strategy;
/**
 * 自己写的Comparator接口，实际运用中不用自己写此接口
 * 针对比较大小的一种策略模式
 * @author feiyu
 *
 * @param <T>
 */
@FunctionalInterface//引入lambda表达式注解，只有一个方法有没有此注解都可以
public interface Comparator<T> {
    int compare(T o1, T o2);

    default void m() {
        System.out.println("m");
    }
}
