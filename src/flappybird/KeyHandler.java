package flappybird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	FlappyBird flappybird;
	
	public KeyHandler(FlappyBird flappybird) {
		this.flappybird=flappybird;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode()==KeyEvent.VK_SPACE) {
			if(!flappybird.gameOver) {
				flappybird.bird.hizY=-9;
			}else {
				flappybird.yenidenOyna();
				flappybird.repaint();
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {}

	@Override
	public void keyReleased(KeyEvent ke) {}

}
