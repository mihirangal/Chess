package pieces;

import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * A super piece that can move like every other piece
 * @author mihir
 *
 */
public class MegaPiece extends Piece
{
	public MegaPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}


	/**
		Add rook, knight, and bishop movements to make a super piece
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
		allPossiblePositions.addAll(getKnightMoves(board, currX, currY, myColor));
		return allPossiblePositions;
	}
}
