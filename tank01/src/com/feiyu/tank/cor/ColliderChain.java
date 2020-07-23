package com.feiyu.tank.cor;

import java.util.LinkedList;
import java.util.List;

import com.feiyu.tank.GameObject;
/**
 * 责任链模式
 * 把所有的检测类串到一起，
 * 也就是放到一个list中去循环比较
 * @author feiyu
 *
 */
public class ColliderChain implements Collider {
	private List<Collider> colliders = new LinkedList<>();//检测类放到list中
	
	/**
	 * 创建检测类
	 */
	public ColliderChain() {
		add(new BulletTankCollider());
		add(new TankTankCollider());
	}
	
	/**
	 * 添加碰撞检测类
	 * @param c
	 */
	public void add(Collider c) {
		colliders.add(c);
	}

	/**
	 * 碰撞检测
	 */
	public boolean collide(GameObject o1, GameObject o2) {
		for(int i=0; i<colliders.size(); i++) {
			if(!colliders.get(i).collide(o1, o2)) {
				return false;
			}
		}
		
		return true;
	}
	
	}
