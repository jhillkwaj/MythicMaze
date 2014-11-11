/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

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
        g.fillRect(xPos, yPos, width, height);//temporary drawing method
    }
}
