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
public abstract class Shape {
   
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
    public void drawShape(Graphics g)
    {
        for(Block b:blockList)
        {
            b.drawBlock(g);
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
                b.setY(b.getY()+50);
            }
        }
    }
    public abstract void rotateClockwise();
    public abstract void rotateCounterClockwise();
}
