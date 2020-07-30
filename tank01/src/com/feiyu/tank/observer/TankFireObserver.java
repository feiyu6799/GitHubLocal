package com.feiyu.tank.observer;

import java.io.Serializable;

/**
 * 观察者接口（Observer）
 * @author feiyu
 *
 */
public interface TankFireObserver extends Serializable{
	void actionOnFire(TankFireEvent e);
}
