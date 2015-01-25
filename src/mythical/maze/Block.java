/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Richard
 */

public class Block {
    
    //x is 10 y is 22 but only 20 are on screen
    private int xPos, yPos; 
    private boolean north, south, west, east;

    /**
     *
     */
    public Color c = new Color(250,250,0);

    /**
     * Gets two integers, x and y, and sets private integers xPos and yPos to these values, respectively.
     * @param x the integer value to be represented by xPos
     * @param y the integer value to be represented by yPos
     */
    
    public Block(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    
    
    /**
    * Gets two integers x and y, and sets private integers xPos and yPos to these values, respectively. 
    * Also receives four booleans n, e, s, w and sets private booleans north, east, south and west to these values, respectively.
    * @param x the integer value to be represented by xPos
    * @param y the integer value to be represented by yPos
    * @param n a boolean value to be represented by north
    * @param e a boolean value to be represented by east
    * @param s a boolean value to be represented by south
    * @param w a boolean value to be represented by west
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
    * Returns the value of north as a boolean
    * @return north a boolean that represents if the block is oriented north
    */

    public boolean getNorth()
    {
        return north;
    }
    
    /**
    * Returns the value of south as a boolean
    * @return south a boolean that represents if the block is oriented south
    */

    public boolean getSouth()
    {
        return south;
    }
    
    /**
    * Returns the value of east as a boolean
    * @return east a boolean that represents if the block is oriented east
    */

    public boolean getEast()
    {
        return east;
    }
    
    /**
    * Returns the value of west as a boolean
    * @return west a boolean that represents if the block is oriented west
    */

    public boolean getWest()
    {
        return west;
    }
    
    /**
    * Rotates block clockwise by setting north's value to west's value, west's value to south's value, 
    * south's value to east's value and east's value to north's original value.
    */

    public void rotateClockwise()
    {
       boolean temp = north;
       north = west;
       west = south;
       south = east;
       east = temp;
    }
    
    /**
    * Rotates block counterclockwise by setting north's value to east's value, east's value to south's value, 
    * south's value to west's value and west's value to north's original value.
    */

    public void rotateCounterClockwise()
    {
        boolean temp = north;
        north = east;
        east = south;
        south = west;
        west = temp;
    }
    
    /**
    * 
    * @param g the <code>Graphics</code> to paint to
    * @param level an integer that represents the level the user is currently on
    * @param gridSizeX an integer that represents the horizonatal length of the grid
    * @param gridSizeY an integer that represents the vertical length of the grid
    * @param offSetX an integer that represents the smallest distance between the block and the y-axis
    * @param xBlocks an integer that represents the length of the x-axis in terms of block size
    */
    
    public void drawBlock(Graphics g,int level, int gridSizeX, int gridSizeY, int offSetX, int xBlocks)
    {
        g.setColor(c);
        g.fillRect((int)(((xPos)*(gridSizeX/((float)xBlocks))))+offSetX,
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0)),
                (int)(gridSizeX/((float)xBlocks)), (int)(gridSizeY/20.0));
        
        int xStart = 0;
        int yStart = 0;
        int xSize = 0;
        int ySize = 0;
        
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
        
        
        g.drawImage(ImageManager.getImage(level+7),(int)(((xPos)*(gridSizeX/((float)xBlocks))))+xStart+offSetX, 
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+yStart, 
                (int)(gridSizeX/((float)xBlocks))+xSize, (int)(gridSizeY/20.0)+ySize,null);//image needs to change with level
    }
}
