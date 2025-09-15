package flappybird;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bird {
	private FlappyBird flappybird;
	public int x, y, en=35, boy=35;
	private Image bird;
	public int hizY=0;
	public int yerCekimi=1;
	
	public Bird(FlappyBird flappybird) {
		this.flappybird=flappybird;
		x=this.flappybird.en/6;
		y=this.flappybird.boy/2;
		bird=new ImageIcon(getClass().getResource("/bird.png")).getImage();
	}
	
	public void hareket() {
		y+=hizY;
		hizY+=yerCekimi;
		y=Math.max(y, 0);	//ekranın yukardan dışına çıkamasın
	}
	
	public void update() {
		hareket();
	}
	
	public void draw(Graphics g) {
		g.drawImage(bird, x, y, en, boy, null);
	}
}
