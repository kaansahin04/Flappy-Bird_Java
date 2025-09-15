package flappybird;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame frame=new JFrame("Flappy Bird");
		frame.setBounds(100,100,500,700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlappyBird flappybird=new FlappyBird();
		frame.add(flappybird);
		frame.pack();
		flappybird.requestFocus();
		frame.setVisible(true);
	}

}
