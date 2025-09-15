package flappybird;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Pipes {
	FlappyBird flappybird;
	int x=0, y=0, en=55, boy=594;
	Image img, bottom, top;
	int hizX=-3;
	boolean gecti=false;
	
	public Pipes(FlappyBird flappybird, String dosyaYolu) {
		this.flappybird=flappybird;
		x=this.flappybird.en;
		img=new ImageIcon(getClass().getResource(dosyaYolu)).getImage();
	}
	
	public void hareket() {
		x+=hizX;
	}
	
	public void update() {
		hareket();
	}
	
	public void draw (Graphics g) {
		g.drawImage(img, x, y, en, boy, null);
	}
}
