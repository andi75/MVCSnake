import java.util.LinkedList;




public class SnakeModel {
	Grid grid;
	
	static final int EMPTY = 0;
	static final int SNAKE = 1;
	static final int MOUSE = 2;
	
	static final int RIGHT = 0;
	static final int UP = 1;
	static final int LEFT = 2;
	static final int DOWN = 3;
	
	
	LinkedList<GridPos> snake;
	int snakeDirection;
	GridPos mouse;
	
	int hunger = 0;
	// how much does the snake grow?
	int wellFed = 4;
	
	boolean hasMoved = false;
	
	void spawnMouse()
	{
		int mouse_x, mouse_y;
		
		// stick this in a loop so the mouse doesn't accidently spawn on top of the snake
		do
		{
			mouse_x = (int) ( (0.0 + Math.random() * 1.0) * grid.width);
			mouse_y = (int) ( (0.0 + Math.random() * 1.0) * grid.height);
		} while(grid.get(mouse_x, mouse_y) != EMPTY);
		// TODO: in the very unlikely event that the snake really completely covers the
		// inner game area, this loop will repeat indefinitely
		
		mouse = new GridPos(mouse_x, mouse_y);
		grid.set(mouse, MOUSE);
	}
	
	public SnakeModel(int width, int height)
	{
		grid = new Grid(width, height);
		snake = new LinkedList<GridPos>();
		
		// don't put the snake too close to the border
		int snake_x = (int) ( (0.3 + Math.random() * 0.2) * width);
		int snake_y = (int) ( (0.3 + Math.random() * 0.2) * height);

		
		GridPos head = new GridPos( snake_x, snake_y );
		GridPos tail = new GridPos( snake_x, snake_y );
		snake.add(head);
		snake.add(tail);
		
		grid.set(head, SNAKE);
		
		snakeDirection = RIGHT;
		hunger = 0;
		
		spawnMouse();
	}
	
	// returns true if the snake moved successfully
	// returns false if the snake hit a wall
	public boolean tick()
	{
		hasMoved = false;
		hunger += 1;
		int offsets[][] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		
		GridPos head = snake.getFirst();
		GridPos newHead = new GridPos(
				head.x + offsets[snakeDirection][0],
				head.y + offsets[snakeDirection][1]);
		if( !grid.isValid(newHead) || grid.get(newHead) == SNAKE)
		{
			return false;
		}
		if( grid.get(newHead) == MOUSE)
		{
			hunger = 0;
			spawnMouse();
		}
		grid.set(newHead, SNAKE);
		snake.addFirst(newHead);
		
		if(hunger >= wellFed)
		{
			GridPos tail = snake.removeLast();
			grid.set(tail, EMPTY);
		}
		
		return true;
	}

	public void move(int direction) {
		// prevent 180 degree turns
		// prevent two turns within a single tick
		if(!hasMoved && snakeDirection != (direction + 2) % 4)
		{
			snakeDirection = direction;
			hasMoved = true;
		}
	}
}
