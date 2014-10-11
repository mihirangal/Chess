package pieces;

import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * the knight 
 * @author mihir
 *
 */
public class KnightPiece extends Piece
{
	public KnightPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}
	/**
	 * the knight moves are standard so just code them all
	 */
	public ArrayList<ChessPosition> getPossibleMoves(String[][] board) 
	{
		// TODO Auto-generated method stub
		ChessPosition current = new ChessPosition(getCurrentPosition());
		int currX= current.getX();
		int currY= current.getY();
		String myColor = getColor();
		ArrayList<ChessPosition> allPossiblePositions;
		allPossiblePositions = Piece.getKnightMoves(board, currX, currY, myColor);
		return allPossiblePositions;
	}
}
