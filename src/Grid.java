public class Grid {
	int width, height;
	int data[];
	
	public Grid(int width, int height)
	{
		this.width = width;
		this.height = height;
		data = new int[width * height];
	}
	
	public int get(int x, int y)
	{
		return data[x + y * width];
	}
	
	public int get(GridPos pos)
	{
		return get(pos.x, pos.y);
	}
	
	public void set(GridPos pos, int value)
	{
		set(pos.x, pos.y, value);
	}
	
	public void set(int x, int y, int value)
	{
		data[x + y * width] = value;
	}
	
	public boolean isValid(GridPos pos)
	{
		return isValid(pos.x, pos.y);
	}
	
	public boolean isValid(int x, int y)
	{
		return x >= 0 && x < width && y >= 0 && y < height;
	}
}