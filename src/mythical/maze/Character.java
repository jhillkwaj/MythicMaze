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
public class Character {
    
    private int xPos,yPos;
    
    /**
    * Gets two integers, x and y, and sets private integers xPos and yPos to these values, respectively.
    * @param x the integer value to be represented by xPos
    * @param y the integer value to be represented by yPos
    */
    public Character(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    
    /**
    *
    * @param g the <code>Graphics</code> to paint to
    * @param gridSizeX an integer that represents the horizonatal length of the grid
    * @param gridSizeY an integer that represents the vertical length of the grid
    * @param offSetX an integer that represents the smallest distance between the character and the y-axis
    * @param xBlocks an integer that represents the length of the x-axis in terms of block size
    */
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offSetX, int xBlocks)
    {
        g.setColor(Color.GREEN);
        g.drawImage(ImageManager.getImage(7),(int)(((xPos)*(gridSizeX/((float)xBlocks))))+offSetX, 
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0)), 
                (int)(gridSizeX/((float)xBlocks)), (int)(gridSizeY/20.0),null);
    }
    
    /**
    * Returns the value of xPos as an integer.
    * @return xPos an integer that represents the characters's x coordinate
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
    
}
