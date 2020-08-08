package com.feiyu.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.UUID;

/**
 * 坦克类
 * @author feiyu
 *
 */
public class Tank {
	private int x, y; //大小
	private Dir dir = Dir.DOWN; //方向
	private static final int SPEED = 2; //速度
	
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	
	private  boolean moving = true; //坦克移动的暂停/启动
	private TankFrame tf = null;//获取调用者的对象
	private boolean living = true;
	private Group group = Group.BAD;//阵容分类
	
	private Random random = new Random();
	Rectangle rect = new Rectangle();//碰撞检测的类
	
	private UUID id = UUID.randomUUID();

	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
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
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
		
		if(this.group == Group.BAD && random.nextInt(100) > 95)// random.nextInt(100) > 95：一定几率发射子弹
			this.fire();
		
		if(this.group == Group.BAD && random.nextInt(100) > 95)// random.nextInt(100) > 95：一定几率转换方向
			randomDir();
		
		boundsCheck();
		
		//update rect
		rect.x = this.x;
		rect.y = this.y;

	}
	/**
	 * 边界检测，避免坦克抛出界面以外
	 */
	private void boundsCheck() {
		if(this.x < 2) x = 2;
		if (this.y < 28) y = 28;
		if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
		if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
	}

	/**
	 * 随机一个方向行驶
	 */
	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];//Dir.values()获取枚举数组，[random.nextInt(4)]获取随机下标
	}

	/**
	 * 坦克死亡
	 */
	public void die() {
		this.living = false;
	}
	
}
