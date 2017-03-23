package minesweeper;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Logic {
	
	public static int SolveBasic()
	{
		int changes = 0;
		Game.updateSquares();
		
		Set<Square> squaresToFlag = new HashSet<Square>();
		Set<Square> squaresToClick = new HashSet<Square>();
		for( Square square : Game.squaresWithNumber )
		{
			if( square.getNumberOnSquare() == ( square.surroundingUnlickedSquares.size() + square.getSurroundingFlags() ) )
			{
				squaresToFlag.addAll(square.surroundingUnlickedSquares);
			}
			
			if( square.getNumberOnSquare() == square.getSurroundingFlags() )
			{
//				Mouse.dualClickSquare( square.getBoardIndex() );
				squaresToClick.addAll( square.surroundingUnlickedSquares );
			}
		}
		System.out.println("ToFlag = " + squaresToFlag);
		for( Square square : squaresToFlag )
		{
			if( !Window.gameNotOver() )
			{
				break;
			}
			if( square.getNumberOnSquare() != ComputerVision.FLAG)
			Mouse.flagSquare(square.getBoardIndex());
			changes++;
		}
		

		System.out.println("ToClick = " + squaresToClick);
		for( Square square : squaresToClick )
		{
			if( !Window.gameNotOver() )
			{
				break;
			}
			if( square.getNumberOnSquare() == ComputerVision.NON_CLICKED)
			Mouse.leftClickSquare(square.getBoardIndex());
			changes++;
		}
		return changes;
		
	}
	
	public static int solveAdvanced()
	{
		int changes = 0;
		Game.updateSquares();
		
		Set<Square> squaresToClick = new HashSet<Square>();
		Set<Square> squaresToFlag = new HashSet<Square>();
		for( Square square : Game.squaresWithNumber )
		{
			if( ( square.getNumberOnSquare() - square.getSurroundingFlags() ) == 1 )
			{
					oneOne( square, squaresToClick );
					oneTwo( square, squaresToClick, squaresToFlag );
			}
			advancedCombo( square, squaresToClick, squaresToFlag );
		}
		



		System.out.println("AdvancedFlag = " + squaresToFlag);
		for( Square square : squaresToFlag )
		{
			if( !Window.gameNotOver() )
			{
				break;
			}
			if( square.getNumberOnSquare() != ComputerVision.FLAG)
			Mouse.flagSquare(square.getBoardIndex());
			changes++;
		}
		
		System.out.println("AdvancedClick = " + squaresToClick );
		for( Square square : squaresToClick )
		{
			if( !Window.gameNotOver() )
			{
				break;
			}
			if( square.getNumberOnSquare() == ComputerVision.NON_CLICKED)
			Mouse.leftClickSquare(square.getBoardIndex());
			changes++;
		}
		return changes;
	}
	
	private static void oneOne( Square oneSquare, Set<Square> squaresToClick)
	{
//		System.out.println("oneOne - " + oneSquare.getBoardIndex());
		for( Square neighbor : oneSquare.surroundingSquares )
		{
//			System.out.println("\tneighbor = " +  neighbor.getBoardIndex());
			if( ( neighbor.getNumberOnSquare() - neighbor.getSurroundingFlags() ) == 1 )
			{
//				System.out.println("\t"+neighbor.getBoardIndex() + " is 1");
				Set<Square> intersection = new HashSet<Square>();
				intersection.addAll( oneSquare.getSurroundingUnclicked());
				intersection.addAll( neighbor.getSurroundingUnclicked());
//				if( intersection.size() == 3 && oneSquare.getSurroundingUnclicked().containsAll( intersection) )
//				{
////				System.out.println("\tfull intersection = " + intersection);
//					intersection.removeAll(neighbor.getSurroundingUnclicked());
////					System.out.println("\tintersection - neighbor = " + intersection);
//					if (intersection.size() == 1) {
//						squaresToClick.addAll(intersection);
//					}
//				}

				/**
				 * TODO: DO this for 2-2
				 */
				if( oneSquare.getSurroundingUnclicked().containsAll( neighbor.getSurroundingUnclicked()) )
				{
					intersection = new HashSet<Square>();
					intersection.addAll( oneSquare.getSurroundingUnclicked());
					intersection.removeAll( neighbor.getSurroundingUnclicked() );
					squaresToClick.addAll(intersection);
				}
			}
		}
	}
	
	/*
	 * a: square with 1 unclicked
	 * b: square with 2 unclicked
	 * If intersection of (b - a) = 1, click (a - b), r-click (b - a)
	 */
	private static void oneTwo( Square oneSquare, Set<Square> squaresToClick, Set<Square> squaresToFlag )
	{
		for( Square neighbor : oneSquare.surroundingSquares )
		{
			if( ( neighbor.getNumberOnSquare() - neighbor.getSurroundingFlags() ) == 2 )
			{
				Set<Square> neighborIntersection = new HashSet<Square>();
				neighborIntersection.addAll( neighbor.getSurroundingUnclicked() );
				neighborIntersection.removeAll( oneSquare.getSurroundingUnclicked() );
				if( neighborIntersection.size() == 1 )
				{
					squaresToFlag.addAll(neighborIntersection);
					Set<Square> oneSquareIntersection = new HashSet<Square>();
					oneSquareIntersection.addAll( oneSquare.surroundingUnlickedSquares );
					oneSquareIntersection.removeAll( neighbor.surroundingUnlickedSquares );
					squaresToClick.addAll(oneSquareIntersection);
				}
			}
		}
	}
	
	//http://computronium.org/minesweeper/combos.html
	private static void advancedCombo( Square square, Set<Square> squaresToClick, Set<Square> squaresToFlag )
	{
			for( Square neighbor : square.surroundingSquares )
			{
				if( neighbor.getNumberOnSquare() >7 || neighbor.getNumberOnSquare() <1 )
				{
					continue;
				}
				if( ( square.getEffectiveNumber() == neighbor.getEffectiveNumber() )
					&& square.getSurroundingUnclicked().containsAll( neighbor.getSurroundingUnclicked() ) )
				{
					Set<Square> intersection = new HashSet<Square>();
					intersection.addAll( square.getSurroundingUnclicked());
					intersection.removeAll( neighbor.getSurroundingUnclicked() );
					squaresToClick.addAll(intersection);
				}
				
				
				//if count([b] - [a]) = b-a > flag [b]-[a]
				Set<Square> intersection = new HashSet<Square>();
				intersection.addAll( neighbor.getSurroundingUnclicked());
				intersection.removeAll( square.getSurroundingUnclicked() );
				if( !intersection.isEmpty() &&  intersection.size() == ( neighbor.getEffectiveNumber() - square.getEffectiveNumber() ) )
				{
					System.out.println("square = " + square);
					System.out.println("neighbor = " + neighbor);
					System.out.println("Flag: " + intersection);
					squaresToFlag.addAll( intersection );
				}
			}
	}
	
	public static void guess()
	{
		int edges = Game.unclickedSquares.size();
		Random rand = new Random();
		 // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int index = rand.nextInt(edges + 1);
		Square guess = Game.unclickedSquares.get( index );
		System.out.println("Guessing " + guess.getBoardIndex());
		Mouse.leftClickSquare(guess.getBoardIndex());
	}

}
