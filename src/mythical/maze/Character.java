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
    *
    * @param x an integer for the x coordinate of the position
    * @param y an integer for the y coordinate of the position
    */
    public Character(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    
    /**
    *
    */
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offSetX, int xBlocks)
    {
        g.setColor(Color.GREEN);
        g.drawImage(ImageManager.getImage(7),(int)(((xPos)*(gridSizeX/((float)xBlocks))))+offSetX, 
                (int)(((yPos)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0)), 
                (int)(gridSizeX/((float)xBlocks)), (int)(gridSizeY/20.0),null);
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
