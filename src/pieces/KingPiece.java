package pieces;


import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * the king
 * @author mihir
 *
 */
public class KingPiece extends Piece
{

	public KingPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}
	/**
	 * find the free squares from the four squares around 
	 */
	public ArrayList<ChessPosition> getPossibleMoves(String[][] board) 
	{
		// TODO Auto-generated method stub
		ChessPosition current = new ChessPosition(getCurrentPosition());
		int currX= current.getX();
		int currY= current.getY();
		String myColor = getColor();
		ArrayList<ChessPosition> allPossiblePositions = new ArrayList<ChessPosition>();
		allPossiblePositions.add(new ChessPosition(currX,currY+1));
		allPossiblePositions.add(new ChessPosition(currX,currY-1));
		allPossiblePositions.add(new ChessPosition(currX+1,currY));
		allPossiblePositions.add(new ChessPosition(currX-1,currY));
		allPossiblePositions.add(new ChessPosition(currX+1,currY+1));
		allPossiblePositions.add(new ChessPosition(currX+1,currY-1));
		allPossiblePositions.add(new ChessPosition(currX-1,currY+1));
		allPossiblePositions.add(new ChessPosition(currX-1,currY-1));
		for( int i =0; i<allPossiblePositions.size();i++)
		{
			int y= allPossiblePositions.get(i).getY();
			int x= allPossiblePositions.get(i).getX();
			if (!Piece.isValidPosition(myColor, allPossiblePositions.get(i), board)||board[y][x].equals(myColor))
			{
				allPossiblePositions.remove(i);
				i--;
			}

		}
		
		//System.out.println(allPossiblePositions);
		return allPossiblePositions;
	}



}
