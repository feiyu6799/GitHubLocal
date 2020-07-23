package com.feiyu.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * 门面模式
 * 作为Facade，负责与Frame打交道，同时负责内部事务
 * 也就是，之前坦克，子弹，爆炸都是通过Frame中画出的，后期在添加墙体等会很麻烦
 * 现在抽离出来一个GameModel处理这些事
 * @author feiyu
 *
 */
public class GameModel {

	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);

	List<Tank> tanks = new ArrayList<>();//敌方坦克集
	List<Bullet> bullets = new ArrayList<>();//子弹集
	List<Explode> explodes = new ArrayList<>();//爆炸集

	public GameModel() {
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
		}
	}

	public void paint(Graphics g) {
		/*左上角显示子弹的数量*/
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
		g.drawString("敌人的数量:" + tanks.size(), 10, 80);
		g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);
		/*绘制子弹，这里需要注意不能使用超级for循环，因为超级for循环是同步处理的，只能在内部做删除，所以这里使用的是基础循环*/
		for(int i=0; i<bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
		/*绘制敌方坦克*/
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		
		for (int i = 0; i < explodes.size(); i++) {//爆炸绘制
			explodes.get(i).paint(g);
		}
		
		/*每发子弹与每辆敌方坦克做碰撞检测*/
		for(int i=0; i<bullets.size(); i++) {
			for(int j = 0; j<tanks.size(); j++) 
				bullets.get(i).collideWith(tanks.get(j));
		}
	}

	public Tank getMainTank() {
		return myTank;
	}

}
