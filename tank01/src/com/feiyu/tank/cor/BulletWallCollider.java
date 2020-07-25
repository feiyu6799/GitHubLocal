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
		if(o1 instanceof Bullet && o2 instanceof Wall) {
			Bullet b = (Bullet)o1;
			Wall w = (Wall)o2;
			
			
			if(b.rect.intersects(w.rect)) {
				b.die();
			}
			
		} else if (o1 instanceof Wall && o2 instanceof Bullet) {
			return collide(o2, o1);
		} 
		
		return true;
		
	}

	
}
