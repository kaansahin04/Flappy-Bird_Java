package flappybird;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener {
	//DEĞİŞKENLER
	int en=500, boy=700;
	Image bg;
	Bird bird;
	KeyHandler keyH;
	ArrayList<Pipes> pipes;
	Random random;
	Timer dongu;
	Timer borular;
	boolean gameOver=false;
	double skor=0;
	
	public FlappyBird(){
		keyH=new KeyHandler(this);
		setPreferredSize(new Dimension(en,boy));
		setBackground(Color.blue);
		addKeyListener(keyH);
		setFocusable(true);
		
		bg=new ImageIcon(getClass().getResource("/background.jpg")).getImage();
		bird=new Bird(this);
		pipes=new ArrayList<Pipes>();
		random=new Random();
		//ekran 1000/60 ms'de 1 güncellenecek
		dongu=new Timer(1000/60, this);
		dongu.start();
		//borular 2 sn'de 1 gelecek
		borular=new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				boruYerlestir();
			}
		});
		borular.start();
	}
	
	public void boruYerlestir() {
		int bosluk=120;
		Pipes ust=new Pipes(this, "/top pipe.png");		//üst boru
		ust.y=random.nextInt(ust.boy-85)*-1;
		int altY=ust.y+ust.boy+bosluk;
		Pipes alt=new Pipes(this, "/bottom pipe.png");	//alt boru
		alt.y=altY;
		pipes.add(ust);
		pipes.add(alt);
	}
	
	public boolean carpisma(Bird bird, Pipes boru) {
		return bird.x<+boru.x+boru.en && bird.x+bird.en>boru.x && bird.y<boru.y+boru.boy-10 && bird.y+bird.boy>boru.y+10;	//kuş boruya değerse
	}
	
	public void yenidenOyna() {
		gameOver=false;
		pipes.clear();
		bird.hizY=0;
		bird.y=boy/2;
		skor=0;
		dongu.start();
		borular.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void update() {
		//kuş güncelleme
		bird.update();
		//borular güncelleme
		for(int i=0; i<pipes.size(); i++) {
			Pipes boru=pipes.get(i);
			boru.update();
		}
		//oyun bitiş kontrolleri
		for(int i=0; i<pipes.size(); i++) {
			Pipes boru=pipes.get(i);
			if(carpisma(bird, boru)) {
				gameOver=true;
			}
		}
		if(bird.y>boy) {
			gameOver=true;
		}
		//borulardan geçiş
		for(int i=0; i<pipes.size(); i++) {
			Pipes boru=pipes.get(i);
			if(bird.x>boru.x+boru.en && !boru.gecti) {
				boru.gecti=true;
				skor+=0.5;
			}
		}
	}
	
	public void draw(Graphics g) {
		//arkaplanı çizme
		g.drawImage(bg, 0, 0, en, boy, null);
		//boruları çizme
		for(int i=0; i<pipes.size(); i++) {
			Pipes boru=pipes.get(i);
			boru.draw(g);
		}
		//kuşu çizme
		bird.draw(g);
		//yazılar
		if(!gameOver) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 32));
			g.drawString(String.valueOf((int) skor), 24, 55);
		}else {
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 32));
			g.drawString(String.valueOf((int) skor), 24, 55);
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 22));
			g.drawString("Game Over! Press Space to Restart!", 55, 315);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
		if(gameOver) {
			borular.stop();
			dongu.stop();
		}
	}
}
