/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

/**
 *
 * @author Richard
 */
public class Block {
    
    private int xPos, yPos;
    private boolean north, south, west, east;
    
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
    
    
}
