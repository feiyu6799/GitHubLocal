package com.feiyu.tank.abstractfactory;

import com.feiyu.tank.Bullet;
import com.feiyu.tank.Dir;
import com.feiyu.tank.Explode;
import com.feiyu.tank.Group;
import com.feiyu.tank.Tank;
import com.feiyu.tank.TankFrame;

/**
 * 默认模式下的实现类，表示一组或一系列（坦克，子弹，爆炸）
 * 实现抽象基类，代表一组生产
 * @author feiyu
 *
 */
public class DefaultFactory extends GameFactory{
	
	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Tank(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		return new Explode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Bullet(x, y, dir, group, tf);
	}
}
