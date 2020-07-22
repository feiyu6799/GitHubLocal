package com.feiyu.tank.abstractfactory;

import com.feiyu.tank.Dir;
import com.feiyu.tank.Group;
import com.feiyu.tank.TankFrame;

/**
 * 生产一系列或一组的抽象基类，只有一个
 * 里面定义生产一组的方法
 * @author feiyu
 *
 */
public abstract class GameFactory {
	
	public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
	public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
	public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

}
