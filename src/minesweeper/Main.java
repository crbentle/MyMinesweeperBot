package minesweeper;

public class Main {
	
	
	public static void main(String [] args )
	{
		initialize();
		solve();
	}
	

	
	private static void initialize(){
		Window.setMinesweeperSizeAndPosition();
		Window.setMinesweeperToForeground();
		Bot.initiate();
		
//		// The middle square
		int startingSquare = 225;
		Mouse.leftClickSquare(startingSquare);
		
		Board.initializeBoard();
		Board.fillBoard();
		Board.printBoard();
	}
	
	private static void solve()
	{

		int count = 0;
		int lastCount = -1;
		int runs = 0;
		while (Window.gameNotOver()){

			Board.fillBoard();
			count = Logic.SolveBasic();
			count += Logic.solveAdvanced();
			
			if( count == 0 && lastCount == 0 )
			{
				System.exit(0);
//				Logic.guess();
//				count = 1;
			}
			lastCount = count;
			runs++;
			if( runs > 100 )
			{
				break;
			}
		}
	}

}
