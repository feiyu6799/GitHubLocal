package com.feiyu.tank.cor;

import com.feiyu.tank.Bullet;
import com.feiyu.tank.GameObject;
import com.feiyu.tank.Wall;
/**
 * 子弹与墙体的碰撞检测
 * @author feiyu
 *
 */
public class BulletWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		return false;
	}
	
}
