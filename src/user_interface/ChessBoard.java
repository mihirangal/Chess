package user_interface;

import game_engine.ChessPosition;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pieces.Piece;

public class ChessBoard
{
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private ChessButton[][] chessSquares = new ChessButton[8][8];
	private JPanel chessBoard;
	private File black = new File("src/resources/gray_square.jpeg");
	private File white = new File("src/resources/white_square.jpeg");
	private File blackPawn = new File("src/resources/black_pawn.png");
	private File whitePawn = new File("src/resources/white_pawn.png");
	private File blackRook = new File("src/resources/black_rook.png");
	private File whiteRook = new File("src/resources/white_rook.png");
	private File blackKnight = new File("src/resources/black_knight.png");
	private File whiteKnight = new File("src/resources/white_knight.png");
	private File blackBishop = new File("src/resources/black_bishop.png");
	private File whiteBishop = new File("src/resources/white_bishop.png");
	private File blackKing = new File("src/resources/black_king.png");
	private File whiteKing = new File("src/resources/white_king.png");
	private File blackQueen = new File("src/resources/black_queen.png");
	private File whiteQueen = new File("src/resources/white_queen.png");
	private BufferedImage grayIm;
	private BufferedImage whiteIm;
	private ArrayList<String> history;
	private boolean secondMove;
	private Controller myController;
	private ChessPosition firstMovePos;
	private ChessPosition secondMovePos;
	private JLabel message;
	private JToolBar tools;
	private JButton undo;
	private JButton forefit;
	private JButton restart;
	private JFrame frame;
	private boolean moveComplete;
	private String whiteName;
	private String blackName;
	private static String DRAW = "draw";

	ChessBoard()
	{
		reInitBoard();
	}

	public void reInitBoard()
	{
		secondMove = false;
		initializeGui();
		myController = new Controller();
		firstMovePos = new ChessPosition();
		secondMovePos = new ChessPosition();
		moveComplete = false;
		history = new ArrayList<String>();
	}
	public void setUpImages()
	{
		grayIm = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
		whiteIm = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
		for(int i =0;i<64;i++)
		{
			for(int j=0;j<64;j++)
			{
				grayIm.setRGB(i, j, Color.lightGray.getRGB());
				whiteIm.setRGB(i, j, Color.white.getRGB());
			}
		}
	}

	/**
	 * given a square  in the board find out what color it should be
	 * @param i
	 * @param j
	 * @return
	 */
	public Color getSquareColor(int i, int j)
	{
		if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
		{
			return Color.white;
		}
		else
		{
			return Color.lightGray;
		}
	}

	public void changeButtonImageAndFile(int x, int y, File f)
	{
		try
		{
			Image img = ImageIO.read(f);
			chessSquares[x][y].setFile(f);
			if(!f.equals(black)&&!f.equals(white))
			{
				chessSquares[x][y].setIcon(new ImageIcon(img));
			}
			else if(f.equals(black))
			{
				chessSquares[x][y].setIcon(new ImageIcon(grayIm));
			}
			else
			{
				chessSquares[x][y].setIcon(new ImageIcon(whiteIm));
			}
			
		}
		catch (IOException ex)
		{
		}
	}
	/**
	 * change image without changing file of the button
	 * @param x
	 * @param y
	 * @param f
	 */
	public void changeButtonImage(int x, int y, File f)
	{
		try
		{
			Image img = ImageIO.read(f);
			chessSquares[x][y].setIcon(new ImageIcon(img));
			// chessSquares[x][y].setFile(f);
		}
		catch (IOException ex)
		{
		}
	}

	public void changeButtonImageBack(int x, int y)
	{
		try
		{
			File f= chessSquares[x][y].getFile();
			Image img = ImageIO.read(f);
			if(!f.equals(white)&&!f.equals(black))
			{
				chessSquares[x][y].setIcon(new ImageIcon(img));
			}
			else if(f.equals(white))
			{
				chessSquares[x][y].setIcon(new ImageIcon(whiteIm));
			}
			else
			{
				chessSquares[x][y].setIcon(new ImageIcon(grayIm));
			}
		}
		catch (IOException ex)
		{
		}
	}

	/**
	 * reset all buttons to have the picture of the file they contain
	 */
	public void changeAllButtonsBack()
	{
		for (int i = 0; i < chessSquares.length; i++)
		{
			for (int j = 0; j < chessSquares[0].length; j++)
			{
				changeButtonImageBack(i, j);
			}
		}
	}

	/**
	 * given 2 tiles switch their corresponding image elements 
	 * assumed that originalPos is a valid piece
	 * @param originalPos
	 * @param newPos
	 */
	public void switchImages(ChessPosition originalPos, ChessPosition newPos)
	{
		int originalx = originalPos.getX();
		int originaly = originalPos.getY();
		int newx = newPos.getX();
		int newy = newPos.getY();
		File oldFile = chessSquares[originalx][originaly].getFile();
		changeButtonImage(newx, newy, oldFile);
		chessSquares[newx][newy].setFile(oldFile);
		Color oldColor = getSquareColor(originalx, originaly);
		if (oldColor.equals(Color.white))
		{
			//changeButtonImage(originalx, originaly, white);
			chessSquares[originalx][originaly].setFile(white);
			chessSquares[originalx][originaly].setIcon(new ImageIcon(whiteIm));
		}
		else
		{
			//changeButtonImage(originalx, originaly, black);
			chessSquares[originalx][originaly].setFile(black);
			chessSquares[originalx][originaly].setIcon(new ImageIcon(grayIm));
		}
	}

	/**
	 * given a color return the name of the player
	 * @param color
	 * @return
	 */
	private String getName(String color)
	{
		if (color.equals(Piece.WHITE))
		{
			return whiteName;
		}
		else
		{
			return blackName;
		}
	}

	/**
	 * set up the interface loosely based on code from
	 * http://stackoverflow.com/questions
	 * /21077322/create-a-chess-board-with-jpanel
	 */
	public final void initializeGui()
	{
		frame = new JFrame();
		askNames();
		setUpImages();
		initToolBar();
		addToolBarListeners();
		initButtonGrid();
		addButtonGridListeners();
		changeAllBackgrounds();
		setupRooks();
		setupKnights();
		setupBishops();
		setupKings();
		setupQueens();
		setupPawns();
		setupRest();
		
		frame.add(getGui());
	}

	/**
	 * add a dialog to ask for the user names
	 */
	public void askNames()
	{
		whiteName = JOptionPane.showInputDialog(null,
				"White Player: What is your name?", "Enter your name",
				JOptionPane.QUESTION_MESSAGE);
		blackName = JOptionPane.showInputDialog(null,
				"Black Player: What is your name?", "Enter your name",
				JOptionPane.QUESTION_MESSAGE);
	}

	public final JComponent getChessBoard()
	{
		return chessBoard;
	}

	public final JComponent getGui()
	{
		return gui;
	}

	/**
	 * if a move is valid then complete it by changing the controller state,
	 * switching images, and determining if checkmate occured
	 * 
	 * @param firstPos
	 * @param newPos
	 */
	public void doMove(ChessPosition firstPos, ChessPosition newPos)
	{
		myController.changeGameBoard(firstPos, newPos);
		myController.movePiece(firstPos, newPos);
		switchImages(firstPos, newPos);
		if (myController.inCheckMate(myController.getNotTurn()))
		{
			JOptionPane.showMessageDialog(chessBoard, "Checkmate "
					+ getName(myController.getNotTurn()) + " loses");
			history.add(getName(myController.getTurn()));
			displayScore();
			resetGui();
		}
		myController.changeTurn();
		// inCheck = myController.checkIfInCheck();
		message.setText(getName(myController.getTurn()) + "'s turn");
	}

	/**
	 * show a dialog box with the score
	 */
	public void displayScore()
	{
		int whiteCount = 0;
		int blackCount = 0;
		int drawCount = 0;
		for (String s : history)
		{
			if (s.equals(whiteName))
			{
				whiteCount++;
			}
			if (s.equals(blackName))
			{
				blackCount++;
			}
			if (s.equals(ChessBoard.DRAW))
			{
				drawCount++;
			}
		}
		JOptionPane.showMessageDialog(chessBoard, whiteName + " won "
				+ whiteCount + " times " + blackName + " won " + blackCount
				+ " times and there were " + drawCount + " draws");
	}

	/**
	 * If a move is valid then doMove else return false and dont change game
	 * state
	 * 
	 * @param firstPos
	 * @param newPos
	 * @return did the move execute
	 */
	public boolean executeMove(ChessPosition firstPos, ChessPosition newPos)
	{
		// will implement in next assingmnet
		String[][] currBoard = myController.getGameboard();
		Piece p = myController.findPiece(firstPos);
		ArrayList<ChessPosition> possibleMoves;
		if (p == null)// if no piece was selected to start
		{
			JOptionPane.showMessageDialog(chessBoard, "No Piece Selected");
			return false;
		}
		else
		// a piece was selected
		{
			possibleMoves = p.getPossibleMoves(currBoard);
		}
		if (p.getColor().equals(myController.getTurn()) == false)// if the move
																	// was made
																	// out of
																	// turn
		{
			JOptionPane.showMessageDialog(chessBoard, "Not Your Turn");
			return false;
		}
		if (myController.inCheck(myController.getTurn()))// if the current
															// player is in
															// check
		{
			if (possibleMoves.contains(newPos))
			{
				// try the move
				myController.movePiece(firstPos, newPos);
				myController.changeGameBoard(firstPos, newPos);
				if (myController.inCheck(myController.getTurn()))// if still in
																	// check
				{
					// reset move
					myController.movePiece(newPos, firstPos);
					myController.changeGameBoard(newPos, firstPos);
					JOptionPane.showMessageDialog(chessBoard, "Still In Check");
					return false;
				}
				else
				{
					// do the rest of the move
					switchImages(firstPos, newPos);
					myController.changeTurn();
					// inCheck = myController.checkIfInCheck();
					message.setText(myController.getTurn() + "'s turn");
					moveComplete = true;
					return true;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(chessBoard, "Not A Valid Move");
				return false;
			}
		}
		else
		{
			if (possibleMoves.contains(newPos))
			{
				doMove(firstPos, newPos);
				moveComplete = true;
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(chessBoard, "Not A Valid Move");
				return false;
			}
		}
	}

	public static void main(String[] args)
	{
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				ChessBoard cb = new ChessBoard();
				// ChessBoard bb = new ChessBoard();
				JFrame f = cb.getFrame();
				// f.add(cb.getGui());
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setLocationByPlatform(true);
				f.pack();
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(r);
	}

	/**
	 * set up the toolBar in the beginning
	 */
	public void initToolBar()
	{
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		undo = new JButton("Undo");
		forefit = new JButton("Forefit");
		restart = new JButton("Restart");
		tools.add(undo);
		tools.addSeparator();
		tools.add(forefit);
		tools.addSeparator();
		tools.add(restart);
		tools.addSeparator();
		message = new JLabel("");
		message.setText("Welcom to Chess " + whiteName + " starts");
		tools.add(message);
	}

	/**
	 * add action listeners for the undo restart and forefit commands
	 */
	public void addToolBarListeners()
	{
		chessBoard = new JPanel(new GridLayout(0, 8));
		gui.add(chessBoard);
		undo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (secondMove == false && moveComplete == true)
				{
					System.out.println("Second move= " + secondMovePos
							+ " FirstMovePos= " + firstMovePos);
					myController.changeGameBoard(secondMovePos, firstMovePos);
					myController.movePiece(secondMovePos, firstMovePos);
					switchImages(secondMovePos, firstMovePos);
					myController.changeTurn();
					message.setText(getName(myController.getTurn()) + "'s turn");
					moveComplete = false;
				}
			}
		});
		forefit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(chessBoard,
						getName(myController.getTurn()) + " loses");
				history.add(getName(myController.getNotTurn()));
				displayScore();
				resetGui();
				// reInitBoard();
			}
		});
		restart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(chessBoard, "restarting");
				history.add(ChessBoard.DRAW);
				displayScore();
				resetGui();
				// reInitBoard();
			}
		});
	}

	/**
	 * create button grid
	 */
	public void initButtonGrid()
	{
		for (int i = 0; i < chessSquares.length; i++)
		{
			for (int j = 0; j < chessSquares[i].length; j++)
			{
				ChessButton b = new ChessButton(new ChessPosition(j, i),
						getSquareColor(i, j));
				b.setSize(64, 64);
				b.setOpaque(true);
				chessSquares[j][i] = b;
				b.setBorderPainted(false);
				
			}
		}
	}

	/**
	 * change the background of the button at point i,j
	 * 
	 * @param i
	 * @param j
	 * @param c
	 */
	public void changeButtonBackground(int i, int j, Color c)
	{
		chessSquares[i][j].setBackground(c);
	}

	/**
	 * change the background of the button at point i,j to white
	 * 
	 * @param i
	 * @param j
	 */
	public void removeButtonBackground(int i, int j)
	{
		chessSquares[i][j].setBackground(Color.white);
	}

	/**
	 * reset controller and gameboard in case of loss forefit or restart, do not
	 * reset score counter;
	 */
	public void resetGui()
	{
		myController = new Controller();
		firstMovePos = new ChessPosition();
		secondMovePos = new ChessPosition();
		secondMove = false;
		moveComplete = false;
		resetAllButtons();
		setupRooks();
		setupKnights();
		setupBishops();
		setupKings();
		setupQueens();
		setupPawns();
		setupRest();
		changeAllButtonsBack();
		changeAllBackgrounds();
	}

	public void resetAllBackgrounds()
	{
		for (int i = 0; i < chessSquares.length; i++)
		{
			for (int j = 0; j < chessSquares[0].length; j++)
			{
				removeButtonBackground(i, j);
			}
		}
	}

	/**
	 * remove the files from all buttons so resetGui can reset them
	 */
	public void resetAllButtons()
	{
		for (int i = 0; i < chessSquares.length; i++)
		{
			for (int j = 0; j < chessSquares[0].length; j++)
			{
				chessSquares[i][j].setFile(null);
			}
		}
	}

	/**
	 * for all the buttons in the grid add a listener that will find out x,y
	 * coordinate of button
	 */
	public void addButtonGridListeners()
	{
		for (int ii = 0; ii < 8; ii++)
		{
			for (int jj = 0; jj < 8; jj++)
			{
				chessBoard.add(chessSquares[jj][ii]);
				chessSquares[jj][ii].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						ChessButton button = (ChessButton) e.getSource();
						if (secondMove == false)// it is the first move
						{
							// highlight the possible positions
							firstMovePos = new ChessPosition(button
									.getMyPosition().getX(), button
									.getMyPosition().getY());
							String[][] currBoard = myController.getGameboard();
							Piece p = myController.findPiece(button
									.getMyPosition());
							ArrayList<ChessPosition> possibleMoves = new ArrayList<ChessPosition>();
							if (p != null)
							{
								possibleMoves = p.getPossibleMoves(currBoard);
							}
							for (ChessPosition pm : possibleMoves)
							{
								changeButtonBackground(pm.getX(), pm.getY(),
										Color.GREEN);
							}
							// make secondMove true
							secondMove = true;
							moveComplete = false;
						}
						else
						{
							// now it is second move so run execute move to see
							// if the last move was valid
							secondMovePos = new ChessPosition(button
									.getMyPosition().getX(), button
									.getMyPosition().getY());
							// get rid of the highlighted
													// squares
							changeAllBackgrounds();
							secondMove = false;
							executeMove(firstMovePos, secondMovePos);
						}
						// Do something with x and y
					}
				});
			}
		}
	}

	/**
	 * add rooks to the board
	 */
	public void setupRooks()
	{
		changeButtonImageAndFile(0, 7, whiteRook);
		changeButtonImageAndFile(7, 7, whiteRook);
		changeButtonImageAndFile(0, 0, blackRook);
		changeButtonImageAndFile(7, 0, blackRook);
	}

	/**
	 * add knights to the board
	 */
	public void setupKnights()
	{
		changeButtonImageAndFile(1, 7, whiteKnight);
		changeButtonImageAndFile(6, 7, whiteKnight);
		changeButtonImageAndFile(1, 0, blackKnight);
		changeButtonImageAndFile(6, 0, blackKnight);
	}

	/**
	 * add bishops to the board
	 */
	public void setupBishops()
	{
		changeButtonImageAndFile(2, 7, whiteBishop);
		changeButtonImageAndFile(5, 7, whiteBishop);
		changeButtonImageAndFile(2, 0, blackBishop);
		changeButtonImageAndFile(5, 0, blackBishop);
	}

	/**
	 * add queens to the board
	 */
	public void setupQueens()
	{
		changeButtonImageAndFile(3, 7, whiteQueen);
		changeButtonImageAndFile(3, 0, blackQueen);
	}

	/**
	 * add kings to the board
	 */
	public void setupKings()
	{
		changeButtonImageAndFile(4, 7, whiteKing);
		changeButtonImageAndFile(4, 0, blackKing);
	}

	/**
	 * add all the pawns to the board
	 */
	public void setupPawns()
	{
		for (int i = 0; i < chessSquares[1].length; i++)
		{
			changeButtonImageAndFile(i, 1, blackPawn);
			changeButtonImageAndFile(i, 6, whitePawn);
		}
	}

	public void changeAllBackgrounds()
	{
		for (int i = 0; i < chessSquares.length; i++)
		{
			for (int j = 0; j < chessSquares[0].length; j++)
			{
				changeButtonBackground(i, j, this.getSquareColor(i, j));
			}
		}
	}
	/**
	 * make sure that buttons that don't have pieces on them are all either
	 * black or white
	 */
	public void setupRest()
	{
		for (int i = 0; i < chessSquares.length; i++)
		{
			for (int j = 0; j < chessSquares[i].length; j++)
			{
				if (j > 1 && j < 6)
				{
					// JButton b = chessSquares[i][j];
					Color c = getSquareColor(i, j);
					if (c.equals(Color.white))
					{
						changeButtonImageAndFile(i, j, white);
					}
					else
					{
						changeButtonImageAndFile(i, j, black);
					}
				}
			}
		}
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame()
	{
		return frame;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}
}
