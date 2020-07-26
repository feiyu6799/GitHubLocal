package com.feiyu.tank.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.feiyu.tank.GameObject;
/**
 * 子弹加个斜杠
 * @author feiyu
 *
 */
public class TailDecorator extends GODecorator {

	public TailDecorator(GameObject go) {
		
		super(go);
	}

	@Override
	public void paint(Graphics g) {
		this.x = go.x;
		this.y = go.y;
		go.paint(g);
		
		/*加装饰*/
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawLine(go.x, go.y, go.x + getWidth(), go.y + getHeight());
		g.setColor(c);
	}
	
	
	@Override
	public int getWidth() {
		return super.go.getWidth();
	}

	@Override
	public int getHeight() {
		return super.go.getHeight();
	}

}
