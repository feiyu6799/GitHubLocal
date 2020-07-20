package com.feiyu.strategy;
/**
 * 推演方式（此接口与设计模式无关）
 * 自己写的Comparable接口，实际运用中不用自己写此接口
 * <T>泛型，指定实现接口要比较的类型，不用每次传入object强转成某类
 * @author feiyu
 *
 * @param <T>
 */
public interface Comparable<T> {
    int compareTo(T o);
}
