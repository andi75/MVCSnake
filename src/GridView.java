import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridView extends JPanel {
	Grid grid;
	
	static final Color colors[] = {
			Color.blue,
			Color.yellow,
			Color.red
	};
	
	public GridView(Grid grid)
	{
		this.grid = grid;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int squareOffset = 1;
		int squareWidth = getWidth() / grid.width - squareOffset;
		int squareHeight = getHeight() / grid.height - squareOffset;
		
		squareWidth = Math.min(squareWidth, squareHeight);
		squareHeight = squareWidth;
		
		for(int sy = 0; sy < grid.height; sy++)
		{
			for(int sx = 0; sx < grid.width; sx++)
			{
				int x = squareOffset + sx * (squareWidth + squareOffset);
				int y = squareOffset + sy * (squareHeight + squareOffset);
				g.setColor( colors[ grid.get(sx, sy) ] );
				g.fillRect(x, getHeight() - y - squareHeight, squareWidth, squareHeight);
				if(grid.get(sx, sy) == 1)
				{
					g.setColor( Color.black );
					g.drawRect(x - 1, getHeight() - y - squareHeight - 1, squareWidth + 1, squareHeight + 1);
				}
			}
		}
	}
}
