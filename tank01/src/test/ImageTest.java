package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {

	@Test
	public void test() {
		
		BufferedImage image2;
		try {
			image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));//加载图片的相对路径到内存上
			assertNotNull(image2);//验证image2是否为null

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
