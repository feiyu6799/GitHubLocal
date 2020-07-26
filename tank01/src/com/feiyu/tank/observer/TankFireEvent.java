package com.feiyu.tank.observer;

import com.feiyu.tank.Tank;
/**
 * 事件类
 * @author feiyu
 *
 */
public class TankFireEvent {
	
	Tank tank;

	public Tank getSource() {//获取事件源
		return tank;
	}

	public TankFireEvent(Tank tank) {
		this.tank = tank;
	}
}
