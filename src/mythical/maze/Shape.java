/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class Shape {
   
    public int xPos, yPos;
    public boolean active;
    public ArrayList<Block>blockList = new ArrayList<>();
    
    public Shape(int x, int y)
    {
        xPos = x;
        yPos = y;
        active = true;
    }
    public boolean getActive()
    {
        return active;
    }
    public void setInActive()
    {
        active = false;
    }
    public void drawShape(Graphics g, int boardSizeX, int boardSizeY, int offsetX)
    {
        for(Block b:blockList)
        {
            b.drawBlock(g,boardSizeX,boardSizeY,offsetX);
        }
    }
    public void moveDown()
    {
        if(active)
        {
            //check for collision, else, move down
            yPos+=1;
            for(Block b:blockList)
            {
                b.setY(b.getY()+1);
            }
        }
    }
    public void rotateClockwise() {
        //check for rotation
        //rotate
        
        for(Block b:blockList)
        {
            int xDistance = b.getX()-xPos;
            int yDistance = b.getY()-yPos;
            b.setX(xPos-yDistance-1);
            b.setY(yPos+xDistance);
            b.rotateClockwise();
        }
        
    }

    public void rotateCounterClockwise() {
        for(Block b:blockList)
        {
            int xDistance = b.getX()-xPos;
            int yDistance = b.getY()-yPos;
            b.setX(xPos+yDistance);
            b.setY(yPos-xDistance-1);
            b.rotateCounterClockwise();
        }        
    }
}
