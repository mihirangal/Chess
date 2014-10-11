package pieces;

import java.util.ArrayList;

import game_engine.*;
/**
 * This class will be the foundation of any pieces implemnted in the future
 * 
 * @author mihir
 *
 */
public abstract class Piece 
{
	private String color;// the color changes the behavior of some pieces relative to the board so we need to keep track of it
	private ChessPosition myPosition;//each piece needs a position
	public static final String BLACK ="B";//keeping the colors constant
	public static final String WHITE ="W";
	public static final String EMPTY ="E";
	public Piece()// create a default ChessPosition
	{
		color=WHITE;
		myPosition = new ChessPosition(0,0);
	}
	public Piece(String newColor, ChessPosition newPos)//Create a Piece with a color and a position
	{
		setColor(newColor);
		myPosition = new ChessPosition(newPos);
	}
	Piece (Piece newPiece)
	{
		
	}//default Copy constructor, children should override


	public void setColor(String newColor)
	{
		color= newColor;
	}
	public String getColor()
	{
		return color;
	}
	/**
	 * @return the current position
	 */
	public ChessPosition getCurrentPosition() {
		return myPosition;
	}
	/**
	 * @param myPosition the myPosition to set
	 */
	public void setCurrentPosition(ChessPosition myPosition) {
		this.myPosition = myPosition;
	}
	/**
	 * 
	 * @param myColor
	 * @param myPos
	 * @param board
	 * @return Checks to make sure that the position given is within the board
	 */
	public static boolean isValidPosition(String myColor, ChessPosition myPos, String[][]board)
	{
		int x = myPos.getX();
		int y = myPos.getY();
		if(x>=8||x<0||y>=8||y<0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	/**
	 * find out if the move is valid for the given piece
	 * @param newPoint
	 * @param board
	 * @return
	 */
	public boolean isValidMove(ChessPosition newPoint, String[][] board) 
	{
		// TODO Auto-generated method stub
		ArrayList<ChessPosition> possiblePositions = getPossibleMoves(board);
		return possiblePositions.contains(newPoint);
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return get all the empty spaces in the diagonal above and the right of you
	 */
	public static ArrayList<ChessPosition> getUpperRight(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX+counter;
		int tempy= currY-counter;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
			tempx=currX+counter;
			tempy=currY-counter;
		}
		return returnList;
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return get all the empty spaces in the diagonal below and the right of you
	 */
	public static ArrayList<ChessPosition> getLowerRight(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX+counter;
		int tempy= currY+counter;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
			tempx=currX+counter;
			tempy=currY+counter;
		}
		return returnList;
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return get all the empty spaces in the diagonal above and the left of you
	 */
	public static ArrayList<ChessPosition> getUpperLeft(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX-counter;
		int tempy= currY-counter;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
			tempx=currX-counter;
			tempy=currY-counter;
		}
		return returnList;
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return get all the empty spaces in the diagonal below and the left of you
	 */
	public static ArrayList<ChessPosition> getLowerLeft(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX-counter;
		int tempy= currY+counter;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
			tempx=currX-counter;
			tempy=currY+counter;
		}
		return returnList;
	}
	/**
	 *
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return  Get all the empty positions to the right of you in your row
	 */
	public static ArrayList<ChessPosition> getRight(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX+counter;
		int tempy= currY;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
			tempx=currX+counter;
		
		}
		return returnList;
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return Get all the empty spaces to the left of me in my row
	 */
	public static ArrayList<ChessPosition> getLeft(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX-counter;
		int tempy= currY;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
			tempx=currX-counter;
			
		}
		return returnList;
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return Find all empty spaces below you in your column
	 */
	public static ArrayList<ChessPosition> getDown(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX;
		int tempy= currY+counter;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
		
			tempy=currY+counter;
		}
		return returnList;
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return Find all empty spaces above you in your column
	 */
	public static ArrayList<ChessPosition> getUp(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> returnList= new ArrayList<ChessPosition>();
		boolean uRightFlag=false;
		int counter =1;
		int tempx= currX;
		int tempy= currY-counter;
		while(uRightFlag==false)
		{
			ChessPosition tempPos= new ChessPosition(tempx,tempy);
			if(!Piece.isValidPosition(myColor, tempPos, board))
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==true )
			{
				uRightFlag=true;
				break;
			}
			if(board[tempy][tempx].equals(Piece.EMPTY)==false&&board[tempy][tempx].equals(myColor)==false )
			{
				uRightFlag=true;
			}
			returnList.add(new ChessPosition(tempx,tempy));
			counter++;
		
			tempy=currY-counter;
		}
		return returnList;
	}
	/**
	 * 
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return all possible moves a knight can make
	 */
	public static ArrayList<ChessPosition> getKnightMoves(String[][]board,int currX, int currY, String myColor)
	{
		
		ArrayList<ChessPosition> allPossiblePositions = new ArrayList<ChessPosition>();
		allPossiblePositions.add(new ChessPosition(currX-1,currY+2));
		allPossiblePositions.add(new ChessPosition(currX-2,currY+1));
		allPossiblePositions.add(new ChessPosition(currX+1,currY+2));
		allPossiblePositions.add(new ChessPosition(currX+2,currY+1));
		allPossiblePositions.add(new ChessPosition(currX-1,currY-2));
		allPossiblePositions.add(new ChessPosition(currX-2,currY-1));
		allPossiblePositions.add(new ChessPosition(currX+1,currY-2));
		allPossiblePositions.add(new ChessPosition(currX+2,currY-1));
		for( int i =0; i<allPossiblePositions.size();i++)
		{
			int currx= allPossiblePositions.get(i).getX();
			int curry= allPossiblePositions.get(i).getY();
			if (!Piece.isValidPosition(myColor, allPossiblePositions.get(i), board)||board[curry][currx].equals(myColor))
			{
				//System.out.println(allPossiblePositions.get(i));
				allPossiblePositions.remove(i);
				i--;
				
			}
		}
		return allPossiblePositions;
	}
	
	/**
	 * 
	 * @param board
	 * @return all possible available moves a piece can make
	 */
	public abstract ArrayList<ChessPosition> getPossibleMoves(String[][]board);
	
}
