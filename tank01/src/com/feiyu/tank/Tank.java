package com.feiyu.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 坦克类
 * @author feiyu
 *
 */
public class Tank {
	private int x, y; //大小
	private Dir dir = Dir.DOWN; //方向
	private static final int SPEED = 2; //速度
	
	public static int WIDTH = ResourceMgr.tankU.getWidth();
	public static int HEIGHT = ResourceMgr.tankU.getHeight();
	
	private  boolean moving = true; //坦克移动的暂停/启动
	private TankFrame tf = null;//获取调用者的对象
	private boolean living = true;
	private Group group = Group.BAD;//阵容分类
	
	private Random random = new Random();

	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}

	/**
	 * 移动方法
	 * @param g
	 */
	public void paint(Graphics g) {
		
		if(!living) tf.tanks.remove(this);

		
//		利用系统颜色简单绘制坦克模型		
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);
//		g.fillRect(x, y, 50, 50);//坦克初始位置和大小
//		g.setColor(c);
		
		/*导入加载图片绘制坦克模型*/
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		}
	
		move();
	}

	/**
	 * 发射子弹
	 */
	public void fire() {
		//添加子弹，这里注意控制子弹的生命周期，不然会造成内存溢出
		//tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.tf));
		/*子弹从中心位置打出，计算子弹在坦克内部的位置*/
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		//tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
		tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
		
		if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

	}
	
	/**
	 * 移动方向
	 */
	private void move() {
		
		if(!moving) return;
		
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		
		if(this.group == Group.BAD)
		if(random.nextInt(10) > 8) this.fire();
		
	}
	
	/**
	 * 坦克死亡
	 */
	public void die() {
		this.living = false;
	}
	
}
