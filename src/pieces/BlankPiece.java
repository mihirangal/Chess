package pieces;

import game_engine.ChessPosition;

import java.util.ArrayList;
/**
 * a placeholder piece that may be used in a 2d piece based board implementation 
 * @author mihir
 *
 */
public class BlankPiece extends Piece
{



	public ArrayList<ChessPosition> getPossibleMoves(String[][] board) 
	{
		return new ArrayList<ChessPosition>();
	}
}
