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
    
    public void setStart(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offsetX)
    {
        //draw the character in the grid block
    }
    public int getX()
    {
        return xPos;
    }
    public int getY()
    {
        return yPos;
    }
    public void setX(int x)
    {
        xPos = x;
    }
    public void setY(int y)
    {
        yPos = y;
    }
//instace methods for location, width and height, whether or not he can move (block or character phase)
    //draw methods, include different graphical versions
    
    //send over block list, will constitute platforms
        //platforms will be walls and where can't exit block
            //always know which block character is in. if leave block, find if next location is a block, if
            //not, then is a platform.
    
    //have a solution spot, return true is past rightBound.
    
    
    //will be called from grid class, will serve like a block, as an object class
    //movement methods will be called by key commands
}
