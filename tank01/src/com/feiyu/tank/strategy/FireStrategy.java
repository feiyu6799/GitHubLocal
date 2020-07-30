package com.feiyu.tank.strategy;

import java.io.Serializable;

import com.feiyu.tank.Tank;

/**
 * 策略模式
 * 发射子弹的接口
 * 所有发射子弹类型，都要继承此接口
 * @author feiyu
 *
 */
public interface FireStrategy extends Serializable{
	void fire(Tank t);
}
