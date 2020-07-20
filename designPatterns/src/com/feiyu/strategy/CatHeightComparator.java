package com.feiyu.strategy;
/**
 * cat的一种策略1
 * 根据高度比较
 * @author feiyu
 *
 */
public class CatHeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.height > o2.height) return -1;
        else if (o1.height < o2.height) return 1;
        else return 0;
    }
}
