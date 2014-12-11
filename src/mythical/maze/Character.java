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
public class Character {
    
    private int xPos,yPos;
    
    /**
    *
    * @param x an integer for the x coordinate
    * @param y an integer for the y coordinate
    */
    public void setStart(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    
    /**
    *
    */
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offsetX)
    {
        //draw the character in the grid block
    }
    
    /**
    *
    */
    public int getX()
    {
        return xPos;
    }
    
    /**
    *
    */
    public int getY()
    {
        return yPos;
    }
    
    /**
    * @param x an integer for the x coordinate
    */
    public void setX(int x)
    {
        xPos = x;
    }
    
    /**
    * @param y an integer for the y coordinate
    */
    public void setY(int y)
    {
        yPos = y;
    }
    
}
