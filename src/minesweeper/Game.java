package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	public static List<Square> squaresWithNumber = new ArrayList<Square>();
	public static List<Square> unclickedSquares = new ArrayList<Square>();
	
	public static void updateSquares()
	{
		for (int index = 0; index < Board.ROWS*Board.COLUMNS; index++){
			
			Square square = Board.getSquare(index);
			
			int number = square.getNumberOnSquare();
			if (number == ComputerVision.NON_CLICKED){
				unclickedSquares.add(square);
			}
			else if (number != ComputerVision.EMPTY && number != ComputerVision.FLAG && square.hasUnClickedNeighbors()){
				squaresWithNumber.add(square);
			}
		}
	}

}
