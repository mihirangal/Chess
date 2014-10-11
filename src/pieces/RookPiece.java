package pieces;


import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * the rook
 * @author mihir
 *
 */
public class RookPiece extends Piece
{
	public RookPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}

	/**
	 * find all the empty squares above me, below me, to the right of me, to the left of me
	 */
	public ArrayList<ChessPosition> getPossibleMoves(String[][] board) 
	{
		// TODO Auto-generated method stub
		ChessPosition current = new ChessPosition(getCurrentPosition());
		int currX= current.getX();
		int currY= current.getY();
		String myColor = getColor();
		ArrayList<ChessPosition> allPossiblePositions = new ArrayList<ChessPosition>();
		allPossiblePositions.addAll(getRight(board, currX, currY, myColor));
		allPossiblePositions.addAll(getLeft(board, currX, currY, myColor));
		allPossiblePositions.addAll(getUp(board, currX, currY, myColor));
		allPossiblePositions.addAll(getDown(board, currX, currY, myColor));
		return allPossiblePositions;
	}



}
