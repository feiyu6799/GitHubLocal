package com.feiyu.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * 子弹类
 * @author feiyu
 *
 */
public class Bullet extends GameObject {
	private static final int SPEED = 6; //速度
	//private static int WIDTH = 30, HEIGHT = 30;//宽高
	public static int WIDTH = ResourceMgr.bulletD.getWidth();//宽
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();//高
	
	public Rectangle rect = new Rectangle();//碰撞检测的类


//	private int x, y;//大小
	private Dir dir;//方向

	private boolean living = true;//子弹是否存活解决内存泄漏问题 
//	TankFrame tf = null; //主窗口
//	GameModel gm = null;//与Frame打交道，同时负责内部事务
	
	public Group group = Group.BAD;//阵容分类

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	
	public Bullet(int x, int y, Dir dir, Group group) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
		
		GameModel.getInstance().add(this);//每创建一个新的对象，就往list中添加一个子弹，目的：简化写法
	}

	/**
	 * 绘制移动子弹
	 * @param g
	 */
	public void paint(Graphics g) {
		if(!living) {
			GameModel.getInstance().remove(this);
		}
//		利用系统颜色简单绘制子弹模型	
//		Color c = g.getColor();
//		g.setColor(Color.RED);
//		g.fillOval(x, y, WIDTH, HEIGHT);
//		g.setColor(c);
		
		/*导入加载图片绘制子弹模型*/
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		}

		move();
	}
	
	/**
	 * 子弹移动
	 */
	private void move() {

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
		
		//update rect
		rect.x = this.x;
		rect.y = this.y;

		
		/*子弹移出主窗口范围，死亡*/
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
	
		
	}

	/**
	 * 碰撞分析
	 * @param tank
	 * @return 
	 */
	public boolean collideWith(Tank tank) {
		
		if(this.group == tank.getGroup()) return false;//想同属相的坦克和炮弹之间没有伤害

		//TODO: 用一个rect来记录子弹的位置
//		Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//		Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
//		if(rect1.intersects(rect2)) {
		if(rect.intersects(tank.rect)) {//位置比对
			tank.die();//死亡
			this.die();//死亡
			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			new Explode(eX, eY);//爆炸
			return true;
		}
		return false;
	}
	/**
	 * 子弹死亡
	 */
	public void die() {
		this.living = false;
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
