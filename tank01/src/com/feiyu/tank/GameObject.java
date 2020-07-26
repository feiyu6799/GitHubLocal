package com.feiyu.tank;

import java.awt.Graphics;
/**
 * 调停者 Mediator
 * 管理坦克，子弹，爆炸，以及后面添加的其他物体，
 * 坦克，子弹，爆炸之间交流都是通过GameObject进行交流的
 * 坦克，子弹，爆炸需要继承此抽象类
 * @author feiyu
 *
 */
public abstract class GameObject {
	public int x, y;

	public abstract void paint(Graphics g);
	
	
	
	public abstract int getWidth();
	
	public abstract int getHeight();
}
