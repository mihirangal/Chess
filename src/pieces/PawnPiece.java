package pieces;

import java.util.ArrayList;

import game_engine.*;
/**
 * the pawn
 * @author mihir
 *
 */
public class PawnPiece extends Piece
{
	public PawnPiece(String newColor, ChessPosition newPos)
	{
		super(newColor, newPos);
	}
	/**
	 * move forward one if clear, add diagonal pieces if they have opponent on them
	 */
	public ArrayList<ChessPosition> getPossibleMoves(String[][] board) 
	{
		// TODO Auto-generated method stub
		ChessPosition current = new ChessPosition(getCurrentPosition());
		int currX= current.getX();
		int currY= current.getY();
		String myColor = getColor();
		ArrayList<ChessPosition> allPossiblePositions = new ArrayList<ChessPosition>();
		allPossiblePositions.addAll(addPossibleMoves(board, currX, currY, myColor));
		return allPossiblePositions;
	}
	/**
	 * helper to find all possible moves
	 * @param board
	 * @param currX
	 * @param currY
	 * @param myColor
	 * @return
	 */
	private ArrayList<ChessPosition> addPossibleMoves(String[][] board, int currX, int currY,
			String myColor)
	{
		ArrayList<ChessPosition> allPossiblePositions =new ArrayList<ChessPosition>();
		
		if(myColor.equals(Piece.WHITE))
		{
			ChessPosition ahead = new ChessPosition(currX,currY-1);
			ChessPosition rightCapture = new ChessPosition(currX+1,currY-1);
			ChessPosition leftCapture = new ChessPosition(currX-1,currY-1);
			if(Piece.isValidPosition(myColor, ahead, board)&&board[currY-1][currX].equals(Piece.EMPTY))
			{
				allPossiblePositions.add(ahead);
			}
			if(Piece.isValidPosition(myColor, rightCapture, board)&&board[currY-1][currX+1].equals(Piece.BLACK))
			{
				allPossiblePositions.add(rightCapture);
			}
			if(Piece.isValidPosition(myColor, leftCapture, board)&&board[currY-1][currX-1].equals(Piece.BLACK))
			{
				allPossiblePositions.add(leftCapture);
			}
		}
		else
		{
			ChessPosition ahead = new ChessPosition(currX,currY+1);
			ChessPosition rightCapture = new ChessPosition(currX-1,currY+1);
			ChessPosition leftCapture = new ChessPosition(currX+1,currY+1);
			if(Piece.isValidPosition(myColor, ahead, board)&&board[currY+1][currX].equals(Piece.EMPTY))
			{
				allPossiblePositions.add(ahead);
			}
			if(Piece.isValidPosition(myColor, rightCapture, board)&&board[currY+1][currX-1].equals(Piece.WHITE))
			{
				allPossiblePositions.add(rightCapture);
			}
			if(Piece.isValidPosition(myColor, leftCapture, board)&&board[currY+1][currX+1].equals(Piece.WHITE))
			{
				allPossiblePositions.add(leftCapture);
			}
			
		}
		return allPossiblePositions;
	}
}
