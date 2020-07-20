package com.feiyu.strategy;
/**
 * cat的一种策略2
 * 根据重量比较
 * @author feiyu
 *
 */
public class CatWeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.weight < o2.weight) return -1;
        else if (o1.weight > o2.weight) return 1;
        else return 0;
    }
}
