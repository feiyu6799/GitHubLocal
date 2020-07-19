package com.feiyu.tank;
/**
 * 启动类
 * @author feiyu
 *
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
		
		int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));//坦克的数量

		
		//初始化敌方坦克
		for(int i=0; i<initTankCount; i++) {
			tf.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, tf));
		}
		
		new Thread(()->new Audio("audio/war1.wav").loop()).start();//声音

		while(true) {
			Thread.sleep(25);
			tf.repaint();//刷新窗口
		}
	}
		
}
