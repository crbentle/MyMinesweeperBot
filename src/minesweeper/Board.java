package minesweeper;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board {
	private static int[] offset = {-1, 0, 1};
	public static final int ROWS = 16;
	public static final int COLUMNS = 30;
	private static Square[][] board = new Square[ROWS][COLUMNS];
	private static BufferedImage boardImage;
	private static Rectangle boardRectangle = new Rectangle(82, 184, 976, 521);
	
	public static void initializeBoard()
	{
		for (int square = 0; square < board.length * board[0].length; square++){
			int row = getRow(square);
			int column = getColumn(square);
			board[row][column] = new Square( square, ComputerVision.NON_CLICKED );
		}
	}

	public static void fillBoard(){
		updateBoardImage();
		
		for (int index = 0; index < board.length * board[0].length; index++){
			Square square = Board.getSquare(index);
			if( square.getNumberOnSquare() == ComputerVision.NON_CLICKED )
			{
			int number = ComputerVision.getNumber(index);
			
//			System.out.println(" = "+number+" ");
//			
//			if(square > 29 )
//			{
//				System.exit(0);
//			}
			placeNumberOnBoard(index, number);
			}
		}
	}
	
	public static void updateBoardImage(){
		boardImage = Bot.screenShot(boardRectangle);
//		try{
//		File outputfile = new File("boardImage.jpg");
//		ImageIO.write(boardImage, "jpg", outputfile);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		System.exit(0);
	}

	public static BufferedImage getBoardImage(){
		return boardImage;
	}

	public static void placeNumberOnBoard(int square, int number){
		int row = getRow(square);
		int column = getColumn(square);
		board[row][column].update( number );
	}
	
	private static int getRow(int square){
		int row = square / board[0].length;
		return row;
	}
	
	private static int getColumn(int square){
		int column = square % board[0].length;
		return column;
	}

	public static Square getSquare(int index){
		int row = getRow(index);
		int column = getColumn(index);
		Square square = board[row][column];
		return square;
	}
	
	private static Square getSquare(int row, int column){
		int squareIndex;
		
		if (row == 0){
			squareIndex = column;
		}
		else if (column == 0){
			squareIndex = row * board[0].length;
		}
		else{
			squareIndex = row * board[0].length + column;
		}
		return Board.getSquare(squareIndex);
	}
	
//	public static int getNumberOnSquare(int square){
//		int row = getRow(square);
//		int column = getColumn(square);
//		int number = board[row][column];
//		return number;
//	}

	public static ArrayList<Square> getSurroundingSquares(int centerSquare){
		ArrayList<Square> surroundingSquaresList = new ArrayList<Square>();
		int centerRow = getRow(centerSquare);
		int centerColumn = getColumn(centerSquare);
		
		for (int rowOffset : offset){
			for (int columnOffset : offset){
				
				// 0,0 is the center square. We only want neighbors
				if( rowOffset == 0 && columnOffset == 0 )
				{
					continue;
				}
				
				int surroundingRow = centerRow + rowOffset;
				int surroundingColumn = centerColumn + columnOffset;
				
				if (validRow(surroundingRow) && validColumn(surroundingColumn)){
					Square surroundingSquare = Board.getSquare(surroundingRow, surroundingColumn);
					surroundingSquaresList.add(surroundingSquare);
				}
			}
		}
//		removeCenterSquareFromList(centerSquare, surroundingSquaresList);
		return surroundingSquaresList;
	}
	
	private static boolean validRow(int row){
		if (row < board.length && row > -1){
			return true;
		}
		return false;
	}

	private static boolean validColumn(int column){
		if (column < board[0].length && column > -1){
			return true;
		}
		return false;
	}
	
//	private static void removeCenterSquareFromList(int centerSquare, ArrayList<Square> surroundingSquaresList){
//		int index = surroundingSquaresList.indexOf(centerSquare);
//		surroundingSquaresList.remove(index);
//	}

	public static void printBoard(){
		for (int row = 0; row < board.length; row++){
			for (int column = 0; column < board[0].length; column++){
				System.out.print(board[row][column].getNumberOnSquare() + " ");
			}
			System.out.println();
		}
	}
}