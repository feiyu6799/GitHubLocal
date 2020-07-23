package com.feiyu.tank.cor;

import com.feiyu.tank.GameObject;
/**
 * 比较器-仿照Comparator的用法
 * 所有类型碰撞检测都需要继承的接口
 * @author feiyu
 *
 */
public interface Collider {
	boolean collide(GameObject o1, GameObject o2);
}
