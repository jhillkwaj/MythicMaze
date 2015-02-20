package mythical.maze;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Object class for individual game blocks.
 * @author Richard Dong
 */
public class Block 
{
    private int xPosition, yPosition; //variables for location on grid.
    private boolean northWallExists, southWallExists, westWallExists, eastWallExists;//true = wall; false = no wall.
    private Color wallColor;//color of walls.

    /**
     * Takes two parameters, x and y, and sets private integers xPosition and yPosition, 
 respectively.
     * @param x the integer value to be represented by xPosition.
     * @param y the integer value to be represented by yPosition.
     */
    public Block(int x, int y)
    {
        try
        {
            xPosition = x;
            yPosition = y;
            wallColor = new Color(250,250,0);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not initialize default block",ex);
        }
    }
    
    /**
     * Alternative constructor that takes in parameters for location, in the 
     * form x and y, and parameters for wall locations, in the form n, e, s, and w.
     * @param x the integer value to be represented by xPosition.
     * @param y the integer value to be represented by yPosition.
     * @param n a boolean value to be represented by northWallExists wall.
     * @param e a boolean value to be represented by eastWallExists wall.
     * @param s a boolean value to be represented by southWallExists wall.
     * @param w a boolean value to be represented by westWallExists wall.
     */
    public Block(int x, int y, boolean n, boolean  e, boolean s, boolean w)
    {
        try
        {
            xPosition = x;
            yPosition = y;
            northWallExists = n;
            southWallExists = s;
            westWallExists = w;
            eastWallExists = e;
            //code below creates random walls as an extra feature
            if(Math.random()<.017f)
            { northWallExists = true;}
            if(Math.random()<.017f)
            { southWallExists = true;}
            if(Math.random()<.017f)
            { eastWallExists = true;}
            if(Math.random()<.017f)
            { westWallExists = true;}
            if(Math.random()<.15f)
            { northWallExists = false; }
            if(Math.random()<.15f)
            { southWallExists = false; }
            if(Math.random()<.15f)
            { eastWallExists = false; }
            if(Math.random()<.15f)
            { westWallExists = false; }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not initialize block",ex);
        }
    }
    
    /**
     * Rotates the walls on a block by setting individual values to those further 
     * clockwise.
     */
    public void rotateClockwise()
    {
        try
        {
            boolean temp = northWallExists;//create a temporary variable.
            //start setting values to those appearing further clockwise.
            northWallExists = westWallExists;
            westWallExists = southWallExists;
            southWallExists = eastWallExists;
            eastWallExists = temp;//sets final wall to temporary value, finishes swap.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not rotate block clockwise",ex);
        }
       
    }
    
    /**
     * Rotates the walls on a block by setting individual values to those further 
     * counterclockwise.
     */
    public void rotateCounterClockwise()
    {
        try
        {
            boolean temp = northWallExists;//create a temporary variable.
            //start setting values to those appearing further counterclockwise.
            northWallExists = eastWallExists;
            eastWallExists = southWallExists;
            southWallExists = westWallExists;
            westWallExists = temp;//sets final wall to temporary value, finishes swap.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not rotate block counterclockwise",ex);
        }
        
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
        try
        {
            //set custom wall colors to complement the backgrounds to stand out.
            wallColor = new Color(0,230,50);
            int levelImage = 10;//default
            if((level%11) == 2)
            { 
                levelImage = 8;
                wallColor = new Color(250,30,30);
            }
            else if((level%11) == 3)
            { 
                levelImage = 11;
                wallColor = new Color(30,250,30);
            }
            else if((level%11) == 4)
            { 
                levelImage = 12;
                wallColor = new Color(250,30,255);
            }
            else if((level%11) == 5)
            { 
                levelImage = 9;
                wallColor = new Color(30,250,255);
            }
            else if((level%11) == 6)
            { 
                levelImage = 36;
                wallColor = new Color(255,250,100);
            }
            else if((level%11) == 7)
            { 
                levelImage = 12;
                wallColor = new Color(255,150,60);
            }
            else if((level%11) == 8)
            { 
                levelImage = 52;
                wallColor = new Color(250,00,00);
            }
            else if((level%11) == 9)
            { 
                levelImage = 11;
                wallColor = new Color(250,255,255);
            }
            else if(level == -1)
            { 
               levelImage = 11;
               wallColor = new Color(250,180,0);
            }

            g.setColor(wallColor);//set color for the walls.
            g.fillRect((int)(((xPosition)*(gridSizeX/((float)xBlocks))))+offSetX,
                    (int)(((yPosition)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0)),
                    (int)(gridSizeX/((float)xBlocks)), (int)(gridSizeY/20.0));
            //draws a box, larger than the image, in which the block image will rest upon.
            //since the box is larger than the block image in some spots, this will indicate
            //a wall.

            //instantiate values to determine offset due to walls.
            int xStart = 0;
            int yStart = 0;
            int xSize = 0;
            int ySize = 0;

            //if walls exist, offset.
            if(westWallExists)
            { 
                xStart+=5;
                xSize-=5;
            }
            if(eastWallExists)
            { 
                xSize-=5;
            }
            if(northWallExists)
            { 
                yStart+=5;
                ySize-=5;
            }
            if(southWallExists)
            { 
                ySize-=5;
            }
            //finally, draw the image on top with calculated offset values.
            g.drawImage(ImageManager.getImage(levelImage),(int)(((xPosition)*(gridSizeX/((float)xBlocks))))+xStart+offSetX, 
                    (int)(((yPosition)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+yStart, 
                    (int)(gridSizeX/((float)xBlocks))+xSize, (int)(gridSizeY/20.0)+ySize,null);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not draw block graphics",ex);
        }
        //matches the block to the appropriate image.
        int levelImage = 10;//default
        if(level == 2)
        { 
            levelImage = 8;
            wallColor = new Color(250,30,30);
        }
        else if(level == 3)
        { 
            levelImage = 11;
            wallColor = new Color(30,250,30);
        }
        else if(level == 4)
        { 
            levelImage = 12;
            wallColor = new Color(250,30,255);
        }
        else if(level == 5)
        { 
            levelImage = 9;
            wallColor = new Color(30,250,255);
        }
        else if(level == 6)
        { 
            levelImage = 36;
            wallColor = new Color(255,250,100);
        }
        else if(level == 7)
        { 
            levelImage = 12;
            wallColor = new Color(255,150,60);
        }
        else if(level == 8)
        { 
            levelImage = 8;
            wallColor = new Color(250,00,00);
        }
        else if(level == 9)
        { 
            levelImage = 11;
            wallColor = new Color(250,255,255);
        }else if(level == -1)
        { 
            levelImage = 11;
            wallColor = new Color(250,180,0);
        }  
    }

    //Instance methods below
   /**
    * Gets an integer x and sets private integer xPosition to this value.
    * @param x the integer value to be represented by xPosition
    */
    public void setX(int x)
    {
        xPosition = x;
    }
    
   /**
    * Gets an integer y and sets private integer yPosition to this value.
    * @param y the integer value to be represented by yPosition
    */
    public void setY(int y)
    {
        yPosition = y;
    }
    
   /**
    * Gets a boolean b and sets private boolean northWallExists to this value.
    * @param b a boolean value to be represented by northWallExists
    */
    public void setNorthWallExists(boolean b)
    {
        northWallExists = b;
    }
    
   /**
    * Gets a boolean b and sets private boolean southWallExists to this value.
    * @param b a boolean value to be represented by southWallExists
    */
    public void setSouthWallExists(boolean b)
    {
        southWallExists = b;
    }
    
   /**
    * Gets a boolean b and sets private boolean eastWallExists to this value.
    * @param b a boolean value to be represented by eastWallExists
    */
    public void setEastWallExists(boolean b)
    {
        eastWallExists = b;
    }
    
   /**
    * Gets a boolean b and sets private boolean westWallExists to this value.
    * @param b a boolean value to be represented by westWallExists
    */
    public void setWestWallExists(boolean b)
    {
        westWallExists = b;
    }
    
    
   /**
    * Returns the value of xPosition as an integer.
    * @return xPosition an integer that represents the block's x coordinate
    */
    public int getX()
    {
        return xPosition;
    }
    
   /**
    * Returns the value of yPosition as an integer.
    * @return yPosition an integer that represents the block's y coordinate
    */
    public int getY()
    {
        return yPosition;
    }
    
   /**
    * Returns if the northWallExists side of the block contains a wall.
    * @return northWallExists a boolean that represents if the block has a northWallExists wall.
    */
    public boolean getNorth()
    {
        return northWallExists;
    }
    
   /**
    * Returns if the southWallExists side of the block contains a wall.
    * @return northWallExists, a boolean that represents if the block has a southWallExists wall.
    */
    public boolean getSouth()
    {
        return southWallExists;
    }
    
   /**
    * Returns if the eastWallExists side of the block contains a wall.
    * @return eastWallExists, a boolean that represents if the block has a eastWallExists wall.
    */
    public boolean getEast()
    {
        return eastWallExists;
    }
    
   /**
    * Returns if the westWallExists side of the block contains a wall.
    * @return northWallExists a boolean that represents if the block has a westWallExists wall.
    */
    public boolean getWest()
    {
        return westWallExists;
    }
}
