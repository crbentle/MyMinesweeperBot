package minesweeper;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

/*
 * MILLISECONDS_CLICK_DELAY determines the speed of the program,
 * but due to a race condition in the Robot class anything under 35
 * gets increasingly unreliable.
 * The race condition can cause the mouse to move before the click is registered.
 */
public class Bot {
	private static Robot robot;
	private static final int MILLISECONDS_CLICK_DELAY = 35;

	
	public static void initiate(){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calculate the center of a cell
	 * @param n The cell number
	 * @return The center Pixel of the cell
	 */
	public static Pixel getCenterOfSquare( int n )
	{
//		int x = 99 + ( n%30 * 32 ) + ( (n%30)/2) + ( ( n%30 ) / 15 ) + ( ( ( n%30 ) %2 ) * ( ( n%30 ) / 16 ) );
//		int y = 202 + ( n/30 * 32 ) + ( (n/30)/2) + ( (n/30 ) / 9);
//		try
//		{
//			SortedSet<Integer> keys = new TreeSet<Integer>(centerOfSquares.keySet());
//		for( int key : keys )
//		{
//			if( key % 30 == 0 )
//			{
//				System.out.println("");
//			}
//			Pixel pixel = centerOfSquares.get(key);
//			System.out.print(key+"="+pixel.x+"/"+pixel.y+" ");
//		}
//		System.exit(0);
////		System.out.println("centerOfSquares = " + centerOfSquares);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}

		int x = 99 + ( n%30 * 32 ) + ( (n%30)/2) + ( ( n%30 ) / 15 ) + ( ( ( n%30 ) %2 ) * ( ( n%30 ) / 16 ) );
		int y = 202 + ( n/30 * 32 ) + ( (n/30)/2) + ( (n/30 ) / 9);
		return new Pixel(x,y);
	}
	
	public static void moveMouse(int square){
		Pixel pixel = getCenterOfSquare(square);
		robot.mouseMove(pixel.x, pixel.y);
	}
	
	public static void leftClickMouse(){
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(MILLISECONDS_CLICK_DELAY);
		robot.mouseMove(0, 0);
	}
	
	public static void delay(int milliseconds){
		robot.delay(milliseconds);
	}
	
	public static void rightClickMouse(){
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		robot.delay(MILLISECONDS_CLICK_DELAY);
	}
	
	public static void dualClickMouse(){
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
		robot.delay(MILLISECONDS_CLICK_DELAY);
		robot.mouseMove(0, 0);
	}
	
	public static BufferedImage screenShot(Rectangle boardRectangle){
		return robot.createScreenCapture(boardRectangle);
	}
}