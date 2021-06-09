import java.awt.Color;
import java.awt.Graphics;

public class Brick {

	private int myX;
	private int myY;
	private int myWidth;
	private int myHeight;
	private Color myColor;

	public Brick (int x, int y, int width, int height, Color color)
	{
		myX = x;
		myY = y;
		myWidth = width;
		myHeight = height;
		myColor = color;
	}
	
	public boolean wasHit (Ball ball)
	// TODO: checks if ball hits = 
	// top of ball crosses bottom of brick AND
	// center of ball lies between right and left edge AND
	// top of ball not past top of brick
	{ 
		if (ball.topEdge() <= myY + myHeight && // top edge of ball crosses bottom of brick
			ball.getX() >= myX && // center of ball lies within left edge of brick
			ball.getX() <= myX + myWidth) // and within right edge
				return true;	
		else
			return false;
	}

	public void draw (Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(myX, myY, myWidth, myHeight);
		g.setColor(myColor);
		g.fillRect(myX - 1, myY - 1, myWidth - 2, myHeight -2);
	}
	
}
