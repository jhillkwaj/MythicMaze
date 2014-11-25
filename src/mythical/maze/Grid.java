/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author justi_000
 */
public class Grid {
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private Shape fallingShape;
    
    public Grid()
    {
        addShape();
    }
    
    public void addShape()
    {
        fallingShape = randomShape();
        
        for(Block b : fallingShape.getBlockList())
        {
            blocks.add(b);
        }
    }
    
    public Shape randomShape()
    {
        return new LShape(5,2);
    }
    
    public void rotateRight()
    {
        fallingShape.rotateClockwise();
    }
    
    public void rotateLeft()
    {
        fallingShape.rotateCounterClockwise();
    }
    
    public void moveRight()
    {
        fallingShape.moveRight();
    }
    
    public void moveLeft()
    {
        fallingShape.moveLeft();
    }
    
    public void moveDown()
    {
        fallingShape.moveDown();
    }
    
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offsetX)
    {
        //draw the background image
        g.setColor(Color.red);
        g.fillRect(0, 0, gridSizeX+offsetX, gridSizeY);
        
        //draw the grid
        g.setColor(new Color(0f,0f,0f,.3f));
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 22; j++)
            {
                g.fillRect((int)(((i)*(gridSizeX/10.0)))+5+offsetX,
                (int)(((j)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                (int)(gridSizeX/10.0) - 10, (int)(gridSizeY/20.0) - 10);
            }
        }
        
        
        
        for(Block b : blocks)
        {
            b.drawBlock(g, gridSizeX, gridSizeY, offsetX);
        }
    }
}
