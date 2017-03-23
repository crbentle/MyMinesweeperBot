package minesweeper;

import java.util.ArrayList;

public class Square {
	
	private int boardIndex;
	private int numberOnSquare;
	public ArrayList<Square> surroundingSquares = new ArrayList<Square>();
	public ArrayList<Square> surroundingUnlickedSquares = new ArrayList<Square>();
	public int surroundingFlags;
	
	public Square( int index, int numberOnSquare )
	{
		this.boardIndex = index;
		this.numberOnSquare = numberOnSquare;
	}
	

	public ArrayList<Square> getSurroundingUnclicked(){
		ArrayList<Square> surroundingUnlickedSquares = new ArrayList<Square>();
		
		for (Square surroundingSquare : this.surroundingSquares){
			if (surroundingSquare.getNumberOnSquare() == ComputerVision.NON_CLICKED){
				surroundingUnlickedSquares.add(surroundingSquare);
			}
		}
		return surroundingUnlickedSquares;
	}

	public int getSurroundingFlags(){
		int surroundingFlags = 0;
		
		for (Square surroundingSquare : this.surroundingSquares){
			if (surroundingSquare.getNumberOnSquare() == ComputerVision.FLAG){
				surroundingFlags++;
			}
		}
		return surroundingFlags;
	}
	
	public boolean hasUnClickedNeighbors()
	{
		return getSurroundingUnclicked().size() > 0;
		
	}
	
	public int getEffectiveNumber()
	{
		return numberOnSquare - surroundingFlags;
	}
	
	public void update( int number )
	{
//		if( number < 8 )
//		{
//			System.out.println("Update " + boardIndex + " - " + number);
//		}
		this.numberOnSquare = number;
		updateNeighbors();
		
		for (Square surroundingSquare : surroundingSquares)
		{
			surroundingSquare.updateNeighbors();
		}

		surroundingSquares = Board.getSurroundingSquares(boardIndex);
		surroundingUnlickedSquares = getSurroundingUnclicked();
		surroundingFlags = getSurroundingFlags();
	}
	
	public void updateNeighbors()
	{
		surroundingSquares = Board.getSurroundingSquares(boardIndex);
		surroundingUnlickedSquares = getSurroundingUnclicked();
		surroundingFlags = getSurroundingFlags();
	}
	
	public void printSurrounding()
	{
		int count = 1;
		for( Square surrounding : surroundingSquares )
		{
			System.out.print(" " + surrounding.getNumberOnSquare() +" ");

			if( count == 3 || count == 5 )
			{
				System.out.println("");
			}
			
			if( count == 4 )
			{
				System.out.print(" " + getNumberOnSquare() +" ");
			}
			count++;
		}
		System.out.println("");
	}
	
	public int getBoardIndex() {
		return boardIndex;
	}
	public void setBoardIndex(int boardIndex) {
		this.boardIndex = boardIndex;
	}
	public int getNumberOnSquare() {
		return numberOnSquare;
	}
	public void setNumberOnSquare(int numberOnSquare) {
		this.numberOnSquare = numberOnSquare;
	}


	@Override
	public String toString() {
		return "Square [boardIndex=" + boardIndex + ", numberOnSquare="
				+ numberOnSquare + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardIndex;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (boardIndex != other.boardIndex)
			return false;
		return true;
	}
	

}
