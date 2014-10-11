package pieces;

import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * moves like a knight and a rook
 * @author mihir
 *
 */
public class KnightAndRookPiece extends Piece
{
	public KnightAndRookPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}


	/**
	 * add all knight moves and rook moves
	 */
	public ArrayList<ChessPosition> getPossibleMoves(String[][] board) 
	{
		ChessPosition current = new ChessPosition(getCurrentPosition());
		int currX= current.getX();
		int currY= current.getY();
		String myColor = getColor();
		ArrayList<ChessPosition> allPossiblePositions = new ArrayList<ChessPosition>();
		allPossiblePositions.addAll(getRight(board, currX, currY, myColor));
		allPossiblePositions.addAll(getLeft(board, currX, currY, myColor));
		allPossiblePositions.addAll(getUp(board, currX, currY, myColor));
		allPossiblePositions.addAll(getDown(board, currX, currY, myColor));
		allPossiblePositions.addAll(getKnightMoves(board, currX, currY, myColor));
		return allPossiblePositions;
	}
}
