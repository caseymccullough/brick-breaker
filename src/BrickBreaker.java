import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class BrickBreaker extends JFrame 
implements ActionListener, KeyListener, MouseListener {

	private static final int MAX_WIDTH = 700;		// Window size
	private static final int MAX_HEIGHT = 800;		// Window size
	private static final int TOP_OF_WINDOW = 22;	// Top of the visible window

	private static final int DELAY_IN_MILLISEC = 25;  // Time delay between ball updates
	
	private static final Color[] brickColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
	private static final int BRICK_WIDTH = 50;
	private static final int BRICK_HEIGHT = 20;
	
	private static final int PADDLE_WIDTH = 120;
	private static final int PADDLE_SPEED = 25;
	
	private static final int BALL_RADIUS = 7;

	private Ball theBall;
	private Paddle thePaddle;
	private ArrayList <Brick> theBricks;
	//private String audioFilePath ; 
	//AudioPlayer player;  


	public BrickBreaker(int level)
	{
		theBall = new Ball (50, 250, 5, 5, BALL_RADIUS, Color.red);
		thePaddle = new Paddle (MAX_WIDTH / 2, MAX_HEIGHT - 100, PADDLE_WIDTH, PADDLE_SPEED);
		theBricks = new ArrayList <Brick> ();
		//audioFilePath =  "ping.wav";
		//player = new AudioPlayer();
		int brickColorCount = 0;
		// creates band of bricks between yPos 100 and 200
		for (int yPos = 100; yPos <=200; yPos += BRICK_HEIGHT)
		{
			// generates a single row with uniform color
			for (int xPos = 0; xPos < MAX_WIDTH; xPos += BRICK_WIDTH)
				theBricks.add(new Brick (xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT, brickColors[brickColorCount]));
			brickColorCount++;
		}	
		// Show the window with the ball in its initial position.
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Timer clock= new Timer(DELAY_IN_MILLISEC, this);			

		clock.start();	// Now actually start the timer.

	}

	public static void main(String[] args) {

		BrickBreaker game = new BrickBreaker(1); // these int parameters could be used to represent levels
		game.addKeyListener(game);
		BrickBreaker game2 = new BrickBreaker(2);
		game2.addKeyListener(game2);
		playClip("doublebass.wav");

	}

	@Override
	public void actionPerformed(ActionEvent e) { 

		// Move the ball.
		theBall.move();
		theBall.bounce(0, MAX_WIDTH, TOP_OF_WINDOW, MAX_HEIGHT);
		if (theBall.bounceWithPaddle(thePaddle))
				playClip ("bounce.wav");

		for (int i = 0; i < theBricks.size(); i++)
			if (theBricks.get(i).wasHit(theBall))
			{
				theBall.reverseDy();
				theBricks.remove(i);
				playClip ("ping.wav");

			}
		// Update the window.
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();


		if (keyCode == KeyEvent.VK_LEFT)
		{
			thePaddle.moveLeft();
		}
		else if (keyCode == KeyEvent.VK_RIGHT)
		{
			thePaddle.moveRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g)
	{
		// Clear the window.
		g.setColor(Color.black);
		g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);

		// Tell the ball to draw itself.
		theBall.draw(g);
		thePaddle.draw(g);
		for (int i = 0; i < theBricks.size(); i++)
			theBricks.get(i).draw(g);
	}

	/**
	 * Method to play the audio file
	 * 
	 * @param filename the name of the WAV file being played
	 */
	public static void playClip(String filename) 
	{
		try
		{
			File audioFile = new File(filename);
			final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

			clip.addLineListener(new LineListener()
			{
				@Override
				public void update(LineEvent event)
				{
					if (event.getType() == LineEvent.Type.STOP)
						clip.close();
				}
			});

			clip.open(AudioSystem.getAudioInputStream(audioFile));
			clip.start();
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		theBall.relocate (e.getX(), e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
