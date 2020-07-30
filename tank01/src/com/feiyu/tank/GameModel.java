package com.feiyu.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.feiyu.tank.cor.ColliderChain;

/**
 * 门面模式
 * 作为Facade，负责与Frame打交道，同时负责内部事务
 * 也就是，之前坦克，子弹，爆炸都是通过Frame中画出的，后期在添加墙体等会很麻烦
 * 现在抽离出来一个GameModel处理这些事
 * @author feiyu
 *
 */
public class GameModel {

	private static final GameModel INSTANCE = new GameModel();
	
	static {
		INSTANCE.init();
	}
	
	Tank myTank;

//	List<Tank> tanks = new ArrayList<>();//敌方坦克集
//	List<Bullet> bullets = new ArrayList<>();//子弹集
//	List<Explode> explodes = new ArrayList<>();//爆炸集
	ColliderChain chain = new ColliderChain();

	private List<GameObject> objects = new ArrayList<>();//调停者模式，处理坦克，子弹，爆炸...


	public GameModel() {}
	
	public static GameModel getInstance() {
		return INSTANCE;
	}

	private void init() {
		// 初始化主战坦克
		myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD);
		}

		// 初始化墙(添加了四堵墙)
		add(new Wall(150, 150, 200, 50));
		add(new Wall(550, 150, 200, 50));
		add(new Wall(300, 300, 50, 200));
		add(new Wall(550, 300, 50, 200));
	}
	

	
	
	public void add(GameObject go) {
		this.objects.add(go);
	}
	
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
	
	public void paint(Graphics g) {
		/*左上角显示子弹的数量*/
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.setColor(c);

		myTank.paint(g);

		for (int i = 0; i < objects.size(); i++) {//调停者遍历内部的坦克，子弹，爆炸...进行绘制
			objects.get(i).paint(g);
		}

		//互相碰撞
		for(int i=0; i<objects.size(); i++) {
			for(int j=i+1; j<objects.size(); j++) { //Comparator.compare(o1,o2)
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				//for
				chain.collide(o1, o2);
			}
		}

	}

	public Tank getMainTank() {
		return myTank;
	}
	
	/**
	 * 保存到硬盘中
	 */
	public void save() {
		File f = new File("c:/feiyu/tank.data");
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(myTank);
			oos.writeObject(objects);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();//关闭流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从硬盘中读取
	 */
	public void load() {
		File f = new File("c:/feiyu/tank.data");
		ObjectInputStream ois= null;
		try {
			ois= new ObjectInputStream(new FileInputStream(f));
			//注意保存时先写什么，这里就先读什么
			myTank = (Tank)ois.readObject();
			objects = (List)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois == null) {
				try {
					ois.close();//关闭流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
