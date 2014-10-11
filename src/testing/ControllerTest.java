package testing;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import game_engine.ChessPosition;

import java.util.ArrayList;

import org.junit.Test;

import pieces.BishopPiece;
import pieces.KingPiece;
import pieces.KnightPiece;
import pieces.PawnPiece;
import pieces.Piece;
import pieces.QueenPiece;
import pieces.RookPiece;
import user_interface.Controller;

public class ControllerTest
{
	@Test
	public void testSetup()
	{
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		Controller b = new Controller();
		pieces = b.getGamePieces();
		int pawnCount = 16;
		int rookCount = 4;
		int knightCount = 4;
		int kingCount = 2;
		int queenCount = 2;
		int bishopCount = 4;
		for (Piece p : pieces)
		{
			if (p instanceof PawnPiece)
			{
				pawnCount--;
			}
			if (p instanceof RookPiece)
			{
				rookCount--;
			}
			if (p instanceof QueenPiece)
			{
				queenCount--;
			}
			if (p instanceof KingPiece)
			{
				kingCount--;
			}
			if (p instanceof KnightPiece)
			{
				knightCount--;
			}
			if (p instanceof BishopPiece)
			{
				bishopCount--;
			}
		}
		boolean isValid = true;
		if ((bishopCount >= 1 || bishopCount < 0)
				|| (pawnCount >= 1 || pawnCount < 0)
				|| (queenCount >= 1 || queenCount < 0)
				|| (kingCount >= 1 || kingCount < 0)
				|| (knightCount >= 1 || knightCount < 0)
				|| (rookCount >= 1 || rookCount < 0))
		{
			isValid = false;
		}
		assertTrue(isValid);

	}
	@Test
	public void testCheckMate()
	{
		Controller b = new Controller(true);
		ChessPosition attackPosition = new ChessPosition(6, 0);
		ChessPosition defendPosition = new ChessPosition(6, 1);
		ChessPosition defendPosition1 = new ChessPosition(5, 2);
		KingPiece pawn = new KingPiece(Piece.BLACK, attackPosition);
		QueenPiece queen = new QueenPiece(Piece.WHITE, defendPosition);
		KingPiece pawn2 = new KingPiece(Piece.WHITE, defendPosition1);
		b.addPiece(pawn);
		b.addPiece(queen);
		b.addPiece(pawn2);
		boolean worked = b.inCheckMate(Piece.BLACK);
		assertTrue(worked);

	}
	public void testInCheck()
	{
		fail("Not yet implemented");
	}

	public void testInCheckMate()
	{
		fail("Not yet implemented");
	}

	public void testInStaleMate()
	{
		fail("Not yet implemented");
	}
}
