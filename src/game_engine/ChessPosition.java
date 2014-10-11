package game_engine;

public class ChessPosition 
{
	private int x;
	private int y;
	public ChessPosition()
	{
		x = 0;
		y = 0;
	}
	public ChessPosition(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	public ChessPosition(ChessPosition newPos)
	{
		x = newPos.x;
		y = newPos.y;
	}
	/**
	 * @return the x
	 */
	public int getX() 
	{
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() 
	{
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) 
	{
		this.y = y;
	}

    public boolean equals(Object other) {
        return (other != null && other instanceof ChessPosition && ((ChessPosition) other).x == x&&((ChessPosition) other).y == y);
      }
    public String toString()
    {
    	return "X = "+ x +" Y = "+ y;
    }
}
