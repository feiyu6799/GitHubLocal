package com.feiyu.tank.abstractfactory;

import java.awt.Graphics;
import java.awt.Graphics;


/**
 * 所有子弹类型的抽象父类
 * @author feiyu
 *
 */
public abstract class BaseBullet {
	public abstract void paint(Graphics g);

	public abstract void collideWith(BaseTank tank);
}
