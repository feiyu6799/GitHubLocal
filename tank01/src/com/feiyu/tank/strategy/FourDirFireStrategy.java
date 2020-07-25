package com.feiyu.tank.strategy;

import com.feiyu.tank.Audio;
import com.feiyu.tank.Bullet;
import com.feiyu.tank.Dir;
import com.feiyu.tank.Group;
import com.feiyu.tank.Tank;

/**
 * 发射子弹方式1
 * 向四周发射子弹的方式
 * @author feiyu
 *
 */
public class FourDirFireStrategy implements FireStrategy{

	@Override
	public void fire(Tank t) {
		int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		Dir[] dirs = Dir.values();
		for(Dir dir : dirs) {
			new Bullet(bX, bY, dir, t.group);
		}
		
		if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
		
	}

}