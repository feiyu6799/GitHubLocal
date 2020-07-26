package com.feiyu.tank.observer;

import com.feiyu.tank.Tank;
/**
 * 观察者（监听类）监听事件触发：这里时CTRL按下事件触发子弹
 * @author feiyu
 *
 */
public class TankFireHandler implements TankFireObserver {

	@Override
	public void actionOnFire(TankFireEvent e) {
		Tank t = e.getSource();
		t.fire();
	}

}
