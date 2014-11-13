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
    
    private int xPos, yPos; 
    public static int width = 50;
    public static int height = 50;
    private boolean north, south, west, east, active;
    public Color c = new Color(250,250,0);
    
    public Block(int x, int y, boolean n, boolean  e, boolean s, boolean w)
    {
        xPos = x;
        yPos = y;
        north = n;
        south = s;
        west = w;
        east = e;
        active = true;
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
    public void setActive(boolean b)
    {
        active = b;
    }
    
    
    
    public int getX()
    {
        return xPos;
    }
    public int getY()
    {
        return yPos;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
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
    public boolean getActive()
    {
        return active;
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
    public void drawBlock(Graphics g)
    {
        g.setColor(c);
        g.fillRect(xPos, yPos, width, height);
        
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
        g.fillRect(xPos + xStart, yPos + yStart, width + xSize, height + ySize);
    }
}
