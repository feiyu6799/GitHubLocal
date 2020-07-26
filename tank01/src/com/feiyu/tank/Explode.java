package com.feiyu.tank;

import java.awt.Graphics;
/**
 * 爆炸
 * @author feiyu
 *
 */
public class Explode extends GameObject {

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
//	private int x, y;//位置
	
//	private boolean living = true;//是否存活
//	TankFrame tf = null;//主窗口
//	GameModel gm = null;//与Frame打交道，同时负责内部事务
	
	private int step = 0;//记录1-16张图片加载到哪一张
	
	public Explode(int x, int y) {
		this.x = x;
		this.y = y;
		
//				new Thread(()->new Audio("audio/explode.wav").play()).start();//加载爆炸声音
		GameModel.getInstance().add(this);

	}
	
	

	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step >= ResourceMgr.explodes.length) 
//			step = 0;
			GameModel.getInstance().remove(this);

	}



	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
