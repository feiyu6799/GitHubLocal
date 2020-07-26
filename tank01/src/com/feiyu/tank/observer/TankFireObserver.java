package com.feiyu.tank.observer;
/**
 * 观察者接口（Observer）
 * @author feiyu
 *
 */
public interface TankFireObserver {
	void actionOnFire(TankFireEvent e);
}
