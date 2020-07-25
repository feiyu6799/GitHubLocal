package com.feiyu.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * 窗口类
 * @author feiyu
 *
 */
public class TankFrame extends Frame {
	
	GameModel gm = GameModel.getInstance();


	
	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

	
	public TankFrame() {
		setSize(GAME_WIDTH,GAME_HEIGHT);//设置窗口大小
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		
		this.addKeyListener(new MyKeyListener());//按键监听
		
		addWindowListener(new WindowAdapter() {//窗口监听
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		


	}
	
	/*处理双缓冲，解决闪烁问题*/
	Image offScreenImage = null;
	/**
	 * 方法调用顺序：
	 * repaint>update>paint
	 * 而我们截获update进行内部处理，不在单独按照顺序执行paint方法了
	 * 
	 * 双缓冲：先在内存上绘制坦克和子弹图形，
	 * 然后在把在内存上绘制好的图形绘制到窗口上，
	 * 解决闪烁问题
	 */
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		/*内存中绘制坦克和子弹*/
		Graphics gOffScreen = offScreenImage.getGraphics();//获取内存中的画笔
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		/*窗口中绘制坦克和子弹*/
		g.drawImage(offScreenImage, 0, 0, null);//窗口中的画笔
	}
	
	
	@Override
	public void paint(Graphics g) {//窗口被遮盖再次呈现时发生的事件，repaint刷新窗口也可以触发
		gm.paint(g);
		
	}
	/**
	 * 按键监听内部类
	 * @author feiyu
	 *
	 */
	class MyKeyListener extends KeyAdapter {
		
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
		
		@Override
		public void keyPressed(KeyEvent e) {//按键时发生事件
			
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;

			default:
				break;
			}
			setMainTankDir();
			
			new Thread(()->new Audio("audio/tank_move.wav").play()).start();//声音

		}

		@Override
		public void keyReleased(KeyEvent e) {//抬起按键时发生事件
			
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
				
			case KeyEvent.VK_CONTROL:
//				myTank.fire();
				gm.getMainTank().fire();
				break;

			default:
				break;
			}
			setMainTankDir();
		}
		
		private void setMainTankDir() {
			Tank myTank = gm.getMainTank();

			if(!bL && !bU && !bR && !bD) myTank.setMoving(false);
			else {
				myTank.setMoving(true);
				
				if(bL) myTank.setDir(Dir.LEFT);
				if(bU) myTank.setDir(Dir.UP);
				if(bR) myTank.setDir(Dir.RIGHT);
				if(bD) myTank.setDir(Dir.DOWN);
			}
		}

	}
	
	

}
