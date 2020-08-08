package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {

	
/*	public void test() {
		
		BufferedImage image2;
		try {
			image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));//加载图片的相对路径到内存上
			assertNotNull(image2);//验证image2是否为null

		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	@Test
	public void test02(){
        
        try {
        	Socket s = new Socket("127.0.0.1", 8888);
			s.getOutputStream().write("HelloServer".getBytes());
			s.getOutputStream().flush();
	        //s.getOutputStream().close();
	        System.out.println("write over, waiting for msg back...");
	        byte[] bytes = new byte[1024];
	        int len = s.getInputStream().read(bytes);
	        System.out.println(new String(bytes, 0, len));
	        s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
