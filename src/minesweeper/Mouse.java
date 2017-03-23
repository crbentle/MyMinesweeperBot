package minesweeper;

import java.util.Random;

public class Mouse {
	private static Random random = new Random();
	
	public static void leftClickSquare(int square){
		Bot.moveMouse(square);
		Bot.leftClickMouse();
	}
	
	public static void dualClickSquare(int square){
		Bot.moveMouse(square);
		Bot.dualClickMouse();
	}

	public static void flagSquare(int square){
		Bot.moveMouse(square);
		Bot.rightClickMouse();
		Board.placeNumberOnBoard(square, 9);
	}

//	public static void clickSurroundingNonClicked(Square centerSquare){
//		while(centerSquare.hasNextNonClicked()){
//			int nextNonClicked = centerSquare.nextNonClicked();
//			leftClickSquare(nextNonClicked);
//			Square.updateTheSurroundingSquares(nextNonClicked);
//		}
//	}
//
//	public static void flagSurroudingNonClicked(Square centerSquare){
//		while(centerSquare.hasNextNonClicked()){
//			int nextNonClicked = centerSquare.nextNonClicked();
//			flagSquare(nextNonClicked);
//			Square.updateTheSurroundingSquares(nextNonClicked);
//		}
//	}
//
//	public static void flagSquare(int square){
//		Bot.moveMouse(square);
//		Bot.rightClickMouse();
//		Board.placeNumberOnBoard(square, 9);
//	}
//
//	public static boolean clickRandomSurroundingNonClicked(){
//		try{
//			//My change
//			int randomSquare = getRandomSquare(Lists.nonClickedSquares);
//			clickSquareAndUpdateSurrounding(randomSquare);
////			
////			int randomSquare = getRandomSquare(Lists.squaresWithNumbers);
////			Square squareWithNumber = Maps.squaresWithNumbersMap.get(randomSquare);
////			randomSquare = getRandomSquare(squareWithNumber.surroundingNonClickedSquares);
////			clickSquareAndUpdateSurrounding(randomSquare);
//			return true;
//		}
//		catch (Exception e){
//			return false;	
//		}
//	}
//	
//	private static int getRandomSquare(ArrayList<Integer> squaresList) throws Exception{
//		int randomNumber = 0;
//		if (squaresList.size() > 1){
//			randomNumber = random.nextInt(squaresList.size()-1);
//		}
//		return squaresList.get(randomNumber);
//	}
//
//	private static void clickSquareAndUpdateSurrounding(int randomSquare){
//		leftClickSquare(randomSquare);
//		Square.updateTheSurroundingSquares(randomSquare);
//		System.out.println("guessed");
//		Main.guessed++;
//	}
//	
//	public static boolean clickRandomNonClicked(){
//		try{
//			int randomSquare = getRandomSquare(Lists.nonClickedSquares);
//			clickSquareAndUpdateSurrounding(randomSquare);
//			return true;
//		}
//		catch (Exception e){
//			return false;
//		}
//	}
}
