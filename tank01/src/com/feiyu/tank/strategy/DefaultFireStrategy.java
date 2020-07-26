package com.feiyu.tank.strategy;

import com.feiyu.tank.Audio;
import com.feiyu.tank.Bullet;
import com.feiyu.tank.GameModel;
import com.feiyu.tank.Group;
import com.feiyu.tank.Tank;
import com.feiyu.tank.decorator.RectDecorator;
import com.feiyu.tank.decorator.TailDecorator;

/**
 * 发射子弹的方式2
 * 单发
 * @author feiyu
 *
 */
public class DefaultFireStrategy implements FireStrategy{

	@Override
	public void fire(Tank t) {

		int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
//		new Bullet(bX, bY, t.dir, t.group);
		
		//Bug? new Bullet把自己加了一遍
		GameModel.getInstance().add(
				new RectDecorator(
						new TailDecorator(
						new Bullet(bX, bY, t.dir, t.group))));//调用装饰器，这里注意的是两种装饰同时调用时的写法
		
		if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
		
	}

}
