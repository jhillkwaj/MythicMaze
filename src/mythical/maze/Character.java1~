package mythical.maze;

import java.awt.Graphics;

/**
 * Creates a character object that the user can move around through the maze.
 * @author Richard Dong
 */
public class Character 
{
    private int xPos,yPos;
    private int imageNum = 31;
    /**
    * Constructor that takes two integers, x and y, and sets private integers
    * xPos and yPos to represent the location of the character.
    * @param x the integer value to be represented by xPos
    * @param y the integer value to be represented by yPos
    */
    public Character(int x, int y)
    {
        try
        {
            xPos = x;
            yPos = y;
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not initialize character",ex);
        }
    }
    
    /**
    * Draws the character onto the screen.
    * @param g the <code>Graphics</code> parameter to paint onto.
    * @param gridSizeX an integer that represents the horizontal length of the grid.
    * @param gridSizeY an integer that represents the vertical length of the grid.
    * @param offSetX an integer that represents the offset due to screen size.
    * @param xBlocks an integer that represents the size of the grid.
    */
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offSetX, int xBlocks)
    {
        try
        {
            g.drawImage(ImageManager.getImage(imageNum),(int)(((xPos)*(gridSizeX/((float)xBlocks))))+offSetX+(int)(gridSizeX/((float)xBlocks)), 
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0)), 
                -(int)(gridSizeX/((float)xBlocks)), (int)(gridSizeY/20.0),null);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not draw character",ex);
        } 
        //draws the character onto the screen with calculations on size based on 
        //the grid so that character size changes with screen size.
    }
    
    //Instance methods below.
   /**
    * Returns the value of xPos as an integer.
    * @return xPos an integer that represents the character's x coordinate
    */
    public int getX()
    {
        return xPos;
    }
    
   /**
    * Returns the value of yPos as an integer.
    * @return yPos an integer that represents the character's y coordinate
    */
    public int getY()
    {
        return yPos;
    }
    
   /**
    * Gets an integer x and sets private integer xPos to this value.
    * @param x the integer value to be represented by xPos
    */
    public void setX(int x)
    {
        xPos = x;
        imageNum = (int) (31 + Math.random() * 5);//creates animation
    }
    
   /**
    * Gets an integer y and sets private integer yPos to this value.
    * @param y the integer value to be represented by yPos
    */
    public void setY(int y)
    {
        yPos = y;
        imageNum = (int) (31 + Math.random() * 5);//creates animation
    } 
}
