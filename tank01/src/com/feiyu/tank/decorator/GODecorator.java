package com.feiyu.tank.decorator;

import java.awt.Graphics;

import com.feiyu.tank.GameObject;
/**
 * 装饰器模式
 * @author feiyu
 *
 */
public abstract class GODecorator extends GameObject{
	
	GameObject go;
	
	public GODecorator(GameObject go) {
		
		this.go = go;
	}

	/**
	 * 在绘制时进行装饰
	 */
	@Override
	public abstract void paint(Graphics g);
}
