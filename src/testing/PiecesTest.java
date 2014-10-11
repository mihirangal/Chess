package testing;

import static org.junit.Assert.*;
import user_interface.*;
import game_engine.ChessPosition;

import org.junit.Test;

import pieces.KnightAndRookPiece;
import pieces.MegaPiece;
import pieces.Piece;
import pieces.BishopPiece;
import pieces.KingPiece;
import pieces.KnightPiece;
import pieces.PawnPiece;
import pieces.QueenPiece;
import pieces.RookPiece;

public class PiecesTest
{

	@Test
	public void testValidMove()
	{
		Controller b = new Controller();
		String color = Piece.WHITE;
		ChessPosition cp = new ChessPosition(3,3);
		boolean cheker=Piece.isValidPosition(color, cp, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testInValidMove()
	{
		Controller b = new Controller();
		String color = Piece.WHITE;
		ChessPosition cp = new ChessPosition(-1,-1);
		boolean cheker=Piece.isValidPosition(color, cp, b.getGameboard());
		assertFalse(cheker);
	}
	@Test
	public void testPawnMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		PawnPiece pawn = new PawnPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,5);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testPawnInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		PawnPiece pawn = new PawnPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,7);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	@Test
	public void testBishopMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		BishopPiece p = new BishopPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(5,5);
		
		boolean cheker= p.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testBishopInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		BishopPiece p = new BishopPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,5);
		
		boolean cheker= p.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	@Test
	public void testRookMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		RookPiece p = new RookPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,3);
		
		boolean cheker= p.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testRookInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		RookPiece p = new RookPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,7);
		
		boolean cheker= p.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	@Test
	public void testQueenMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		QueenPiece p = new QueenPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,3);
		
		boolean cheker= p.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testQueenInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		QueenPiece p = new QueenPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,7);
		
		boolean cheker= p.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	@Test
	public void testKnightMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		KnightPiece p = new KnightPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(5,4);
		
		boolean cheker= p.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testKngihtInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		KnightPiece pawn = new KnightPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,5);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	@Test
	public void testKingMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		KingPiece pawn = new KingPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,5);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testKingInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		KingPiece pawn = new KingPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,7);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	public void testMegaMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		MegaPiece pawn = new MegaPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,5);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testMegaInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		MegaPiece pawn = new MegaPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(-1,7);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	public void testRookaAndKnightMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		KnightAndRookPiece pawn = new KnightAndRookPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(6,5);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertTrue(cheker);
	}
	@Test
	public void testRookAndKnightInvalidMove()
	{
		Controller b = new Controller();
		ChessPosition originalPlace = new ChessPosition(6,6);
		KnightAndRookPiece pawn = new KnightAndRookPiece(Piece.WHITE,originalPlace);
		ChessPosition newPlace = new ChessPosition(-1,7);
		
		boolean cheker= pawn.isValidMove(newPlace, b.getGameboard());
		assertFalse(cheker);
	}
	

}
