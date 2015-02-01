package mythical.maze;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Object class for individual game blocks.
 * @author Richard Dong
 */
public class Block 
{
    private int xPos, yPos; //variables for location on grid.
    private boolean north, south, west, east;//true = wall; false = no wall.
    private Color c;//color of walls.

    /**
     * Takes two parameters, x and y, and sets private integers xPos and yPos, 
     * respectively.
     * @param x the integer value to be represented by xPos.
     * @param y the integer value to be represented by yPos.
     */
    public Block(int x, int y)
    {
        xPos = x;
        yPos = y;
        c = new Color(250,250,0);
    }
    
    /**
     * Alternative constructor that takes in parameters for location, in the 
     * form x and y, and parameters for wall locations, in the form n, e, s, and w.
     * @param x the integer value to be represented by xPos.
     * @param y the integer value to be represented by yPos.
     * @param n a boolean value to be represented by north wall.
     * @param e a boolean value to be represented by east wall.
     * @param s a boolean value to be represented by south wall.
     * @param w a boolean value to be represented by west wall.
     */
    public Block(int x, int y, boolean n, boolean  e, boolean s, boolean w)
    {
        xPos = x;
        yPos = y;
        north = n;
        south = s;
        west = w;
        east = e;
    }
    
    /**
     * Rotates the walls on a block by setting individual values to those further 
     * clockwise.
     */
    public void rotateClockwise()
    {
       boolean temp = north;//create a temporary variable.
       //start setting values to those appearing further clockwise.
       north = west;
       west = south;
       south = east;
       east = temp;//sets final wall to temporary value, finishes swap.
    }
    
    /**
     * Rotates the walls on a block by setting individual values to those further 
     * counterclockwise.
     */
    public void rotateCounterClockwise()
    {
        boolean temp = north;//create a temporary variable.
        //start setting values to those appearing further counterclockwise.
        north = east;
        east = south;
        south = west;
        west = temp;//sets final wall to temporary value, finishes swap.
    }
    
    /**
    * Draws the block onto the screen.
    * @param g the <code>Graphics</code> parameter to paint onto.
    * @param level an integer that represents the level the user is currently on.
    * @param gridSizeX an integer that represents the horizontal length of the grid.
    * @param gridSizeY an integer that represents the vertical length of the grid.
    * @param offSetX an integer that represents the offset due to screen size.
    * @param xBlocks an integer that represents the size of the grid.
    */
    public void drawBlock(Graphics g,int level, int gridSizeX, int gridSizeY, int offSetX, int xBlocks)
    {
        //matches the block to the appropriate image.
        int levelImage = 10;//default
        if(level == 2)
        { 
            levelImage = 8; 
        }
        else if(level == 3)
        { 
            levelImage = 11; 
        }
        else if(level == 4)
        { 
            levelImage = 12; 
        }
        else if(level == 5)
        { 
            levelImage = 9; 
        }
        
        g.setColor(c);//set color for the walls.
        g.fillRect((int)(((xPos)*(gridSizeX/((float)xBlocks))))+offSetX,
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0)),
                (int)(gridSizeX/((float)xBlocks)), (int)(gridSizeY/20.0));
        //draws a box, larger than the image, in which the image will rest upon.
        //since the box is larger than the image in some spots, this will indicate
        //a wall.
        
        //instantiate values to determine offset due to walls.
        int xStart = 0;
        int yStart = 0;
        int xSize = 0;
        int ySize = 0;
        
        //if walls exist, offset.
        if(west)
        { 
            xStart+=3;
            xSize-=3;
        }
        if(east)
        { 
            xSize-=3;
        }
        if(north)
        { 
            yStart+=3;
            ySize-=3;
        }
        if(south)
        { 
            ySize-=3;
        }
        //finally, draw the image on top with calculated offset values.
        g.drawImage(ImageManager.getImage(levelImage),(int)(((xPos)*(gridSizeX/((float)xBlocks))))+xStart+offSetX, 
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+yStart, 
                (int)(gridSizeX/((float)xBlocks))+xSize, (int)(gridSizeY/20.0)+ySize,null);
    }

    //Instance methods below
   /**
    * Gets an integer x and sets private integer xPos to this value.
    * @param x the integer value to be represented by xPos
    */
    public void setX(int x)
    {
        xPos = x;
    }
    
   /**
    * Gets an integer y and sets private integer yPos to this value.
    * @param y the integer value to be represented by yPos
    */
    public void setY(int y)
    {
        yPos = y;
    }
    
   /**
    * Gets a boolean b and sets private boolean north to this value.
    * @param b a boolean value to be represented by north
    */
    public void setNorth(boolean b)
    {
        north = b;
    }
    
   /**
    * Gets a boolean b and sets private boolean south to this value.
    * @param b a boolean value to be represented by south
    */
    public void setSouth(boolean b)
    {
        south = b;
    }
    
   /**
    * Gets a boolean b and sets private boolean east to this value.
    * @param b a boolean value to be represented by east
    */
    public void setEast(boolean b)
    {
        east = b;
    }
    
   /**
    * Gets a boolean b and sets private boolean west to this value.
    * @param b a boolean value to be represented by west
    */
    public void setWest(boolean b)
    {
        west = b;
    }
    
    
   /**
    * Returns the value of xPos as an integer.
    * @return xPos an integer that represents the block's x coordinate
    */
    public int getX()
    {
        return xPos;
    }
    
   /**
    * Returns the value of yPos as an integer.
    * @return yPos an integer that represents the block's y coordinate
    */
    public int getY()
    {
        return yPos;
    }
    
   /**
    * Returns if the north side of the block contains a wall.
    * @return north a boolean that represents if the block has a north wall.
    */
    public boolean getNorth()
    {
        return north;
    }
    
   /**
    * Returns if the south side of the block contains a wall.
    * @return north, a boolean that represents if the block has a south wall.
    */
    public boolean getSouth()
    {
        return south;
    }
    
   /**
    * Returns if the east side of the block contains a wall.
    * @return east, a boolean that represents if the block has a east wall.
    */
    public boolean getEast()
    {
        return east;
    }
    
   /**
    * Returns if the west side of the block contains a wall.
    * @return north a boolean that represents if the block has a west wall.
    */
    public boolean getWest()
    {
        return west;
    }
}
