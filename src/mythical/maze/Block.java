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
    public Color c = new Color(250,250,0);
    
    public Block(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    public Block(int x, int y, boolean n, boolean  e, boolean s, boolean w)
    {
        xPos = x;
        yPos = y;
        north = n;
        south = s;
        west = w;
        east = e;
    }
    public void setX(int x)
    {
        xPos = x;
    }
    public void setY(int y)
    {
        yPos = y;
    }
    public void setNorth(boolean b)
    {
        north = b;
    }
    public void setSouth(boolean b)
    {
        south = b;
    }
    public void setEast(boolean b)
    {
        east = b;
    }
    public void setWest(boolean b)
    {
        west = b;
    }
    
    
    
    public int getX()
    {
        return xPos;
    }
    public int getY()
    {
        return yPos;
    }
    public boolean getNorth()
    {
        return north;
    }
    public boolean getSouth()
    {
        return south;
    }
    public boolean getEast()
    {
        return east;
    }
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
        g.setColor(new Color(40,40,40));
        g.fillRect((int)(((xPos)*(gridSizeX/((float)xBlocks))))+xStart+offSetX, 
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+yStart, 
                (int)(gridSizeX/((float)xBlocks))+xSize, (int)(gridSizeY/20.0)+ySize);
    }
}
