import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class SnakeViewcontroller implements KeyListener, Runnable {
	SnakeModel model;
	GridView view;
	
	public static void main(String argv[])
	{
		new SnakeViewcontroller();
	}
	
	public SnakeViewcontroller()
	{
		JFrame frame = new JFrame("MVCSnake");
		model = new SnakeModel(20, 20);
		view = new GridView(model.grid);
		view.setPreferredSize(new Dimension(600, 600));
		
		frame.addKeyListener(this);
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
		
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		double delay = 150;

		while(true)
		{
			
			if(!model.tick())
			{
				System.out.println("Crashed at length " + model.snake.size());
				break;
			}
			view.repaint();
			
			try {
				Thread.sleep((int)delay);
			} catch (Exception e) { /* ignore */ }
			delay *= 0.999;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: handle cursor
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP: model.move(SnakeModel.UP); break;
		case KeyEvent.VK_DOWN: model.move(SnakeModel.DOWN); break;
		case KeyEvent.VK_LEFT: model.move(SnakeModel.LEFT); break;
		case KeyEvent.VK_RIGHT: model.move(SnakeModel.RIGHT); break;
		}
//		System.out.println(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
