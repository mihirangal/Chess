package pieces;

import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * the queen
 * @author mihir
 *
 */
public class QueenPiece extends Piece
{
	public QueenPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}


	/**
	 * The queen can move like both a rook and a bishop so add all possible
	 * positions that rooks and bishops have
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
		allPossiblePositions.addAll(getUpperRight(board, currX, currY, myColor));
		allPossiblePositions.addAll(getLowerRight(board, currX, currY, myColor));
		allPossiblePositions.addAll(getUpperLeft(board, currX, currY, myColor));
		allPossiblePositions.addAll(getLowerLeft(board, currX, currY, myColor));
		return allPossiblePositions;
	}
}
