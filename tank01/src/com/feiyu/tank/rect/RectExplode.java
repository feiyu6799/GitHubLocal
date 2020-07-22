package com.feiyu.tank.rect;

import java.awt.Color;
import java.awt.Graphics;

import com.feiyu.tank.Audio;
import com.feiyu.tank.ResourceMgr;
import com.feiyu.tank.TankFrame;
import com.feiyu.tank.abstractfactory.BaseExplode;

/**
 * 方形爆炸
 * @author feiyu
 *
 */
public class RectExplode extends BaseExplode {


	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	private int x, y;
	
	//private boolean living = true;
	TankFrame tf = null;
	
	private int step = 0;
	
	public RectExplode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		//g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, 10*step, 10*step);
		step++;
		
		if(step >= 15) 
			tf.explodes.remove(this);
		
		g.setColor(c);
		
		
	}

}
