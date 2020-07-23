package com.feiyu.tank;
/**
 * 启动类
 * @author feiyu
 *
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
		
		new Thread(()->new Audio("audio/war1.wav").loop()).start();//声音

		while(true) {
			Thread.sleep(25);
			tf.repaint();//刷新窗口
		}
	}
		
}
