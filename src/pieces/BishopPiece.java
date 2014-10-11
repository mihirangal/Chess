package pieces;

import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * the bishop
 * @author mihir
 *
 */
public class BishopPiece extends Piece
{
	public BishopPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}


	/**
	 * check all the diagonals surrounding to find all possible clear moves
	 */
	public ArrayList<ChessPosition> getPossibleMoves(String[][] board) 
	{
		// TODO Auto-generated method stub
		ChessPosition current = new ChessPosition(getCurrentPosition());
		int currX= current.getX();
		int currY= current.getY();
		String myColor = getColor();
		ArrayList<ChessPosition> allPossiblePositions = new ArrayList<ChessPosition>();
		allPossiblePositions.addAll(getUpperRight(board, currX, currY, myColor));
		allPossiblePositions.addAll(getLowerRight(board, currX, currY, myColor));
		allPossiblePositions.addAll(getUpperLeft(board, currX, currY, myColor));
		allPossiblePositions.addAll(getLowerLeft(board, currX, currY, myColor));
		return allPossiblePositions;
	}



}
