package user_interface;

import game_engine.ChessPosition;

import java.util.ArrayList;

import pieces.*;

public class Controller
{
	private String turn;
	private String[][] gameBoard;
	private ArrayList<Piece> gamePieces;
	private final int BOARD_SIZE = 8;
	private boolean testing;

	/**
	 * default controller, created a fresh game board
	 */
	public Controller()
	{
		turn = Piece.WHITE;
		gameBoard = new String[BOARD_SIZE][BOARD_SIZE];
		gamePieces = new ArrayList<Piece>();
		initializeBoard();
	}

	/**
	 * controller with boolean flag creates empty board for test purposes
	 * 
	 * @param b
	 */
	public Controller(boolean b)
	{
		turn = Piece.WHITE;
		gameBoard = new String[BOARD_SIZE][BOARD_SIZE];
		gamePieces = new ArrayList<Piece>();
		testing = true;
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				gameBoard[i][j] = "E";
			}
		}
	}

	/**
	 * for testing purposes add a piece to the board and the pieces array
	 * 
	 * @param p
	 */
	public void addPiece(Piece p)
	{
		if (testing)
		{
			gamePieces.add(p);
			gameBoard[p.getCurrentPosition().getY()][p.getCurrentPosition()
					.getX()] = p.getColor();
		}
	}

	/**
	 * init the gameboard with black and white squares add all the pieces to the
	 * pieces array
	 */
	private void initializeBoard()
	{
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				gameBoard[i][j] = "E";
			}
		}
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			gameBoard[0][i] = Piece.BLACK;
			gameBoard[1][i] = Piece.BLACK;
			gameBoard[6][i] = Piece.WHITE;
			gameBoard[7][i] = Piece.WHITE;
		}
		setUpRooks();
		setUpKnights();
		setUpBishops();
		setUpKings();
		setUpQueens();
		setUpPawns();
	}

	/**
	 * @return the current turn( white/black)
	 */
	public String getTurn()
	{
		return turn;
	}

	/**
	 * return the players whose turn it is not
	 * 
	 * @return
	 */
	public String getNotTurn()
	{
		if (turn.equals(Piece.BLACK))
		{
			return Piece.WHITE;
		}
		else
		{
			return Piece.BLACK;
		}
	}

	/**
	 * find out if the player color is in check
	 * 
	 * @param color
	 * @return true if in check
	 */
	public boolean inCheck(String color)
	{
		ArrayList<ChessPosition> potentialCaptures = new ArrayList<ChessPosition>();
		for (Piece p : gamePieces)
		{
			if (!p.getColor().equals(color))
			{
				for (ChessPosition cp : p.getPossibleMoves(gameBoard))
				{
					if (findPiece(cp) != null)
					{
						potentialCaptures.add(cp);
					}
				}
			}
		}
		for (ChessPosition cp : potentialCaptures)
		{
			if (findPiece(cp) instanceof KingPiece)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * find out if the player color is in checkmate
	 * 
	 * @param color
	 * @return true if in checkmate
	 */
	public boolean inCheckMate(String color)
	{
		// ArrayList<ChessPosition> potentialCaptures = new
		// ArrayList<ChessPosition>();
		for (Piece p : gamePieces)
		{
			ArrayList<ChessPosition> allMoves = p.getPossibleMoves(gameBoard);
			ChessPosition oldPos = p.getCurrentPosition();
			if (p.getColor().equals(color) == true)
			{
				for (ChessPosition newPos : allMoves)
				{
					//System.out.println(newPos);
					changeGameBoard(oldPos, newPos);
					movePiece(oldPos, newPos);
					if (inCheck(color) == false)
					{
						changeGameBoard(newPos, oldPos);
						movePiece(newPos, oldPos);
						return false;
					}
					changeGameBoard(newPos, oldPos);
					movePiece(newPos, oldPos);
				}
			}
		}
		return true;
	}

	/**
	 * @return the gameboard
	 */
	public String[][] getGameboard()
	{
		return gameBoard;
	}

	/**
	 * @param gameboard
	 *            the gameboard to set
	 */
	public void setGameboard(String[][] gameboard)
	{
		this.gameBoard = gameboard;
	}

	/**
	 * @return the gamePieces
	 */
	public ArrayList<Piece> getGamePieces()
	{
		return gamePieces;
	}

	/**
	 * @param gamePieces
	 *            the gamePieces to set
	 */
	public void setGamePieces(ArrayList<Piece> gamePieces)
	{
		this.gamePieces = gamePieces;
	}

	/**
	 * given a chessposition find out which piece is at that point
	 * 
	 * @param pos
	 * @return
	 */
	public Piece findPiece(ChessPosition pos)
	{
		for (Piece p : gamePieces)
		{
			if (p.getCurrentPosition().equals(pos))
			{
				return p;
			}
		}
		return null;
	}

	/**
	 * change the position of the piece at orignalPos to new Pos
	 * 
	 * @param originalPos
	 * @param newPos
	 */
	public void movePiece(ChessPosition originalPos, ChessPosition newPos)
	{
		for( int i=0;i<gamePieces.size();i++)
		{
			if(gamePieces.get(i).getCurrentPosition().equals(newPos))
			{
				gamePieces.remove(i);
				break;
			}
		}
		for (Piece p : gamePieces)
		{
	
			if (p.getCurrentPosition().equals(originalPos))
			{
				p.setCurrentPosition(newPos);
			}
		}
	}

	/**
	 * change the turn
	 */
	public void changeTurn()
	{
		if (turn.equals(Piece.WHITE))
		{
			turn = Piece.BLACK;
		}
		else
		{
			turn = Piece.WHITE;
		}
	}

	/**
	 * add the initial bishops to the pieces array
	 */
	private void setUpBishops()
	{
		BishopPiece black1 = new BishopPiece(Piece.BLACK, new ChessPosition(2,
				0));
		BishopPiece black2 = new BishopPiece(Piece.BLACK, new ChessPosition(5,
				0));
		BishopPiece white1 = new BishopPiece(Piece.WHITE, new ChessPosition(2,
				7));
		BishopPiece white2 = new BishopPiece(Piece.WHITE, new ChessPosition(5,
				7));
		gamePieces.add(black1);
		gamePieces.add(black2);
		gamePieces.add(white1);
		gamePieces.add(white2);
	}

	/**
	 * add the initial knights to the pieces array
	 */
	private void setUpKnights()
	{
		KnightPiece black1 = new KnightPiece(Piece.BLACK, new ChessPosition(1,
				0));
		KnightPiece black2 = new KnightPiece(Piece.BLACK, new ChessPosition(6,
				0));
		KnightPiece white1 = new KnightPiece(Piece.WHITE, new ChessPosition(1,
				7));
		KnightPiece white2 = new KnightPiece(Piece.WHITE, new ChessPosition(6,
				7));
		gamePieces.add(black1);
		gamePieces.add(black2);
		gamePieces.add(white1);
		gamePieces.add(white2);
	}

	/**
	 * add the initial rooks to the pieces array
	 */
	private void setUpRooks()
	{
		RookPiece black1 = new RookPiece(Piece.BLACK, new ChessPosition(0, 0));
		RookPiece black2 = new RookPiece(Piece.BLACK, new ChessPosition(7, 0));
		RookPiece white1 = new RookPiece(Piece.WHITE, new ChessPosition(0, 7));
		RookPiece white2 = new RookPiece(Piece.WHITE, new ChessPosition(7, 7));
		gamePieces.add(black1);
		gamePieces.add(black2);
		gamePieces.add(white1);
		gamePieces.add(white2);
	}

	/**
	 * add the initial queens to the pieces array
	 */
	private void setUpQueens()
	{
		QueenPiece black1 = new QueenPiece(Piece.BLACK, new ChessPosition(3, 0));
		QueenPiece white1 = new QueenPiece(Piece.WHITE, new ChessPosition(3, 7));
		gamePieces.add(black1);
		gamePieces.add(white1);
	}

	/**
	 * add the initial kings to the pieces array
	 */
	private void setUpKings()
	{
		KingPiece black1 = new KingPiece(Piece.BLACK, new ChessPosition(4, 0));
		KingPiece white1 = new KingPiece(Piece.WHITE, new ChessPosition(4, 7));
		gamePieces.add(black1);
		gamePieces.add(white1);
	}

	/**
	 * add the initial pawns to the pieces array
	 */
	private void setUpPawns()
	{
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			gamePieces.add(new PawnPiece(Piece.BLACK, new ChessPosition(i, 1)));
			gamePieces.add(new PawnPiece(Piece.WHITE, new ChessPosition(i, 6)));
		}
	}

	public void changeGameBoard(ChessPosition originalPos, ChessPosition newPos)
	{
		int originalx = originalPos.getY();
		int originaly = originalPos.getX();
		int newx = newPos.getY();
		int newy = newPos.getX();
		gameBoard[newx][newy] = gameBoard[originalx][originaly];
		gameBoard[originalx][originaly] = Piece.EMPTY;
		printArray(gameBoard);
	}

	/**
	 * print any 2d board
	 * 
	 * @param board
	 */
	public static void printArray(String[][] board)
	{
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				System.out.print(board[i][j]);
				if (j < board[i].length - 1)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
