package com.feiyu.tank.strategy;

import com.feiyu.tank.Tank;

/**
 * 策略模式
 * 发射子弹的接口
 * 所有发射子弹类型，都要继承此接口
 * @author feiyu
 *
 */
public interface FireStrategy {
	void fire(Tank t);
}
