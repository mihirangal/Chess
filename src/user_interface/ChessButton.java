package user_interface;

import game_engine.ChessPosition;

import java.awt.*;
import java.io.File;
import javax.swing.*;
@SuppressWarnings("serial")
public class ChessButton extends JButton
{
	private ChessPosition myPosition;
	private Color color;
	private File myFile;
	public ChessButton(ChessPosition p,Color newColor)
	{
		setColor(newColor);
		myPosition= new ChessPosition(p);
		myFile=null;
	}
	public ChessButton()
	{
		super();
		myPosition = new ChessPosition(-1,-1);
	}

	/**
	 * @return the myPos
	 */
	public ChessPosition getMyPosition()
	{
		return myPosition;
	}

	/**
	 * @param myPos the myPos to set
	 */
	public void setMyPosition(ChessPosition p)
	{
		myPosition= new ChessPosition(p);
	}
	/**
	 * @return the color
	 */
	public Color getColor()
	{
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	/**
	 * @return the myFile
	 */
	public File getFile()
	{
		return myFile;
	}
	/**
	 * @param myFile the myFile to set
	 */
	public void setFile(File myFile)
	{
		this.myFile = myFile;
	}
	
}
