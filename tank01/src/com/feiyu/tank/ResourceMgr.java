package com.feiyu.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 加载图片资源
 * @author feiyu
 *
 */
public class ResourceMgr {
//	public static BufferedImage tankL, tankU, tankR, tankD;//坦克图片
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD; //坦克图片
	public static BufferedImage badTankL, badTankU, badTankR, badTankD; //坦克图片
	public static BufferedImage bulletL, bulletU, bulletR, bulletD; //子弹图片
	public static BufferedImage[] explodes = new BufferedImage[16];//爆炸图片

	static {
		try {
			/*坦克图片*/
//			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
//			tankL = ImageUtil.rotateImage(tankU, -90);
//			tankR = ImageUtil.rotateImage(tankU, 90);
//			tankD = ImageUtil.rotateImage(tankU, 180); 
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			
			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);

			/*子弹*/
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
		
			/*爆炸*/
			for(int i=0; i<16; i++) 
				explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
