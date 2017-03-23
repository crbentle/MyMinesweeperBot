package minesweeper;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * The colors are unfortunately not distributed uniformly across the Board
 * making this task a lot harder than expected and took a lot of trial and error.
 * Different graphics cards may cause different results, so you may have to tweak
 * some values.
 */
public class ComputerVision {
	/*
	 * The pixels within a cell to check to determine what the cell is showing
	 */
	/** TODO: Need to add '8' */
	private static Pixel onePixel = new Pixel(17, 17); // 66,79,188
	private static Pixel twoPixel = new Pixel(22, 27); // 28,104,4
	private static Pixel threePixel = new Pixel(15, 16); // 173,33,41
	private static Pixel fourPixel = new Pixel(21, 8); // 2,2,136
	private static Pixel fivePixel = new Pixel(13, 7); // 127,1,5
	private static Pixel sixPixel = new Pixel(12, 19); // 6,119,123
	private static Pixel sevenPixel = new Pixel(22, 14);
	private static Pixel flagPixel = new Pixel(16, 12); // 252,2,1
	private static Pixel nonClickedPixel = new Pixel(1, 1); // 110,186,245 - 45,52,180
	

	public static final int NON_CLICKED = 8;
	public static final int FLAG = 9;
	public static final int EMPTY = 0;

	private static Pixel squareOffset;
	
	public static int getNumber(int square){
		squareOffset = getSquareOffset( square );
		
		int numberOnSquare = 0;
//		int numberOnSquare = 8;//TODO

		if( square == 278 )
		{
			System.out.println(square + " = " + numberOnSquare + " - offset = " + squareOffset.x+"/"+squareOffset.y);
		}
			
			if (squareIsFlag()){
				numberOnSquare = 9;
			}
			else if (squareIsNonClicked()){
				numberOnSquare = 8;
			}
			else if (numberIsOne()){
				numberOnSquare = 1;
			}
			else if (numberIsTwo()){
				numberOnSquare = 2;
			}		
			else if (numberIsThree()){
				numberOnSquare = 3;
			}
			else if (numberIsFour()){
				numberOnSquare = 4;
			}
			// 7 must be checked before 5
			else if (numberIsSeven()){
				numberOnSquare = 7;
			}
			else if (numberIsFive()){
				numberOnSquare = 5;
			}
			else if (numberIsSix()){
				numberOnSquare = 6;
			}
//		}
		
		return numberOnSquare;
	}
	
	public static Pixel getSquareOffset( int n )
	{
//		int x = 62 + ( n%30 * 32 ) + ( (n%30)/2) + ( ( n%30 ) / 15 ) + ( ( ( n%30 ) %2 ) * ( ( n%30 ) / 16 ) );
//		int y = 104 + ( n/30 * 32 ) + ( (n/30)/2) + ( (n/30 ) / 9);

		int x = ( n%30 * 32 ) + ( (n%30)/2) + ( ( n%30 ) / 15 ) + ( ( ( n%30 ) %2 ) * ( ( n%30 ) / 16 ) );
		int y = ( n/30 * 32 ) + ( (n/30)/2) + ( (n/30 ) / 9);
		return new Pixel(x,y);
	}

	private static boolean squareIsNonClicked(){
		PixelColors nonClickedPixelColors = new PixelColors(nonClickedPixel);
		
		if (nonClickedPixelColors.blue > 100){
			return true;
		}
		return false;
	}

	private static boolean squareIsFlag(){
		PixelColors flagPixelColors = new PixelColors(flagPixel);
		
		if (flagPixelColors.red > 230
				&& flagPixelColors.green < 20
				&& flagPixelColors.blue < 20){
//			if(flaxPixelColors.blue > 50)
			return true;
		}
		return false;
	}

	private static boolean numberIsOne(){
		PixelColors onePixelColors = new PixelColors(onePixel);
		
		if (onePixelColors.red < 75 && onePixelColors.red > 60 &&
				onePixelColors.green < 100){
			return true;
		}
		return false;
	}
	
	private static boolean numberIsTwo(){
		PixelColors twoPixelColors = new PixelColors(twoPixel);
		
		if (twoPixelColors.blue < 10
				&& twoPixelColors.green > 50){
			return true;
		}
		
//		if (twoPixelColors.blue < 10){
//			return true;
//		}
		return false;
	}
	
	private static boolean numberIsThree(){
		// 173,33,41
		PixelColors threePixelColors = new PixelColors(threePixel);
		
		if (threePixelColors.blue < 100 && threePixelColors.green < 100
				&& threePixelColors.red > 140){
			return true;
		}
//		if (threePixelColors.blue < 20 && threePixelColors.green < 20){
//			return true;
//		}
		return false;
	}
	
	private static boolean numberIsFour(){
		PixelColors fourPixelColors = new PixelColors(fourPixel);
		
		if (fourPixelColors.red < 10
				&& fourPixelColors.green < 10
				&& fourPixelColors.blue > 80){
			return true;
		}
		
//		if (fourPixelColors.red < 5){
//			return true;
//		}	
		return false;
	}
	
	private static boolean numberIsFive(){
		PixelColors fivePixelColors = new PixelColors(fivePixel);
		
		if (fivePixelColors.red < 200 && fivePixelColors.green < 10 &&
				fivePixelColors.blue < 10){
			
			System.out.println(fivePixelColors.red+" / " + fivePixelColors.green + " / " + fivePixelColors.blue);
			return true;
		}
		return false;
	}
	
	private static boolean numberIsSix(){
		PixelColors sixPixelColors = new PixelColors(sixPixel);
		
		if (sixPixelColors.red < 10 && sixPixelColors.green > 100){
			return true;
		}
		return false;
	}
	
	private static boolean numberIsSeven(){
		PixelColors sevenPixelColors = new PixelColors(sevenPixel);
		
		if (sevenPixelColors.green < 30){
			return true;
		}
		return false;
	}
	
	private static class PixelColors{
		public int red;
		public int green;
		public int blue;
		
		public PixelColors(Pixel identityPixel) {
			Pixel realPixel = getRealPixel(identityPixel);			
			BufferedImage boardImage = Board.getBoardImage();
			Color pixelColors = new Color(boardImage.getRGB(realPixel.x, realPixel.y));
			setPixelColors(pixelColors);
		}
		
		private Pixel getRealPixel(Pixel identityPixel){
			Pixel realPixel = new Pixel(identityPixel.x, identityPixel.y);
			realPixel.x += squareOffset.x;
			realPixel.y += squareOffset.y;
			return realPixel;
		}
		
		private void setPixelColors(Color pixelColors){
			this.red = pixelColors.getRed();
			this.green = pixelColors.getGreen();
			this.blue = pixelColors.getBlue();
		}
	}
}