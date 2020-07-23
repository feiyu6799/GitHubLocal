package com.feiyu.tank.cor;

import com.feiyu.tank.GameObject;
import com.feiyu.tank.Tank;
import com.feiyu.tank.Wall;
/**
 * 坦克于墙之间的碰撞检测
 * @author feiyu
 *
 */
public class TankWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		return false;
	}
	
}
