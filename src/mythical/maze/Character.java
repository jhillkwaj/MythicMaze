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
    * @param x an integer for the x coordinate of the position
    * @param y an integer for the y coordinate of the position
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
    * @return xPos an integer for the x coordinate of the character's position
    */
    public int getX()
    {
        return xPos;
    }
    
    /**
    *
    * @return yPos an integer for the y coordinate of the character's position
    */
    public int getY()
    {
        return yPos;
    }
    
    /**
    * @param x an integer for the x coordinate of the position
    */
    public void setX(int x)
    {
        xPos = x;
    }
    
    /**
    * @param y an integer for the y coordinate of the position
    */
    public void setY(int y)
    {
        yPos = y;
    }
    
}
