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
 
 //what do the 4 booleans represent in this class (n,e,s,w)?
 //do they represent if the block is rotated in that direction or if the block is facing that direction?
public class Block {
    
    //x is 10 y is 22 but only 20 are on screen
    private int xPos, yPos; 
    private boolean north, south, west, east;
    public Color c = new Color(250,250,0);
    
    /*
    * @param x an integer that represents the x coordinate of the position
    * @param y an integer that represents the y coordinate of the position
    */
    public Block(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    
    /*
    * @param x an integer that represents the x coordinate of the position
    * @param y an integer that represents the y coordinate of the position
    * @param n a boolean 
    * @param e a boolean
    * @param s a boolean
    * @param w a boolean
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
    
    /*
    * @param x an integer that represents the x coordinate of the position
    */
    public void setX(int x)
    {
        xPos = x;
    }
    
    /*
    * @param y an integer that represents the y coordinate of the position
    */
    public void setY(int y)
    {
        yPos = y;
    }
    
    /*
    * @param b a boolean
    */
    public void setNorth(boolean b)
    {
        north = b;
    }
    
    /*
    * @param b a boolean
    */
    public void setSouth(boolean b)
    {
        south = b;
    }
    
    /*
    * @param b a boolean
    */
    public void setEast(boolean b)
    {
        east = b;
    }
    
    /*
    * @param b a boolean
    */
    public void setWest(boolean b)
    {
        west = b;
    }
    
    
    /*
    * @return xPos an integer that represents the block's x coordinate
    */
    public int getX()
    {
        return xPos;
    }
    
    /*
    * @return yPos an integer that represents the block's y coordinate
    */
    public int getY()
    {
        return yPos;
    }
    
    /*
    * @return north a boolean
    */
    public boolean getNorth()
    {
        return north;
    }
    
    /*
    * @return south a boolean
    */
    public boolean getSouth()
    {
        return south;
    }
    
    /*
    * @return east a boolean
    */
    public boolean getEast()
    {
        return east;
    }
    
    /*
    * @return west a boolean
    */
    public boolean getWest()
    {
        return west;
    }
    
    public void rotateClockwise()
    {
       boolean temp = north;
       north = west;
       west = south;
       south = east;
       east = temp;
    }
    
    public void rotateCounterClockwise()
    {
        boolean temp = north;
        north = east;
        east = south;
        south = west;
        west = temp;
    }
    
    public void drawBlock(Graphics g, int gridSizeX, int gridSizeY, int offSetX, int xBlocks)
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
        g.drawImage(ImageManager.getImage(8),(int)(((xPos)*(gridSizeX/((float)xBlocks))))+xStart+offSetX, 
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+yStart, 
                (int)(gridSizeX/((float)xBlocks))+xSize, (int)(gridSizeY/20.0)+ySize,null);//image needs to change with level
    }
}
