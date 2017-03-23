package minesweeper;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Window {
	private static HWND minesweeperWindow = User32.INSTANCE.FindWindow(null, "Minesweeper");
	
	public static void setMinesweeperSizeAndPosition(){
//		System.out.println("setMinesweeperSizeAndPosition");
//		System.out.println("minesweeperWindow = " + minesweeperWindow );
		if( minesweeperWindow == null )
		{
			try {
				System.out.println("Starting MineSweeper.exe");
				Runtime
						.getRuntime()
						.exec(new String[] { "C:\\Program Files\\Microsoft Games\\Minesweeper\\MineSweeper.exe" });
				Thread.sleep(1000);
			} catch (Exception err) {
				err.printStackTrace();
			}
			minesweeperWindow = User32.INSTANCE.FindWindow(null, "Minesweeper");
			System.out.println("MineSweeper started");
		}
		
		if (minesweeperWindow == null) {
			System.out.println("Minesweeper is not running");
			System.exit(1);
		}
		else{
			User32.INSTANCE.SetWindowPos(minesweeperWindow, null, 20, 80, 1100, 1100, 68);
		}
	}
	
	public static void setMinesweeperToForeground() {
		User32.INSTANCE.SetForegroundWindow(minesweeperWindow);
	}
	
	public static boolean gameNotOver(){
		if (gameWasWon() || gameWasLost()){
			return false;
		}
		return true;
	}
	
	private static boolean gameWasLost(){
		HWND gameLostPopUp = User32.INSTANCE.FindWindow(null, "Game Lost");
		
		if (gameLostPopUp == null){
			return false;
		}
		System.out.println("Game Lost");
		return true;
	}
	
	private static boolean gameWasWon(){
		HWND gameWonPopUp = User32.INSTANCE.FindWindow(null, "Game Won");
		
		if (gameWonPopUp == null){
			return false;
		}
		System.out.println("Game Won");
		return true;
	}
	
	public static void main(String[] args)
	{
		setMinesweeperSizeAndPosition();
	}
}