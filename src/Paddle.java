import java.awt.Color;
import java.awt.Graphics;


public class Paddle {

	private static final int PADDLE_THICKNESS = 15;
	private static final Color PADDLE_COLOR = Color.WHITE;
	
	private int myX;
	private int myY;
	private int myWidth;
	private int mySpeed;
	private Color myColor;
	
	public Paddle (int x, int y, int width, int speed)
	{
		myX = x;
		myY = y;
		myWidth = width;
		mySpeed = speed;
		myColor = PADDLE_COLOR;
	}
	
	public int topEdge ()
	{
		return myY;
	}
	
	public int rightEdge()
	{
		return myX + myWidth;
	}
	
	public int leftEdge()
	{
		return myX;
	}
	
	public int bottomEdge()
	{
		return myY + PADDLE_THICKNESS;
	}
	
	public void moveRight()
	{
		myX += mySpeed;
	}
	
	public void moveLeft()
	{
		myX -= mySpeed;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(myColor);
		g.fillRect(myX, myY, myWidth, PADDLE_THICKNESS);
	}
}
