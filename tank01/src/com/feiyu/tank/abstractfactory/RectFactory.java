package com.feiyu.tank.abstractfactory;

import com.feiyu.tank.Dir;
import com.feiyu.tank.Group;
import com.feiyu.tank.TankFrame;
import com.feiyu.tank.rect.RectBullet;
import com.feiyu.tank.rect.RectExplode;
import com.feiyu.tank.rect.RectTank;

/**
 * 方形组系列  实现类
 * @author feiyu
 *
 */
public class RectFactory extends GameFactory{


	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		// TODO Auto-generated method stub
		return new RectTank(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		// TODO Auto-generated method stub
		return new RectExplode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new RectBullet(x, y, dir, group, tf);
	}

}
