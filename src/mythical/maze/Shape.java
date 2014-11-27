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
    private ArrayList<Block>blockList = new ArrayList<>();
    private ArrayList<Block>occupy;
    
    public Shape(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    public void drawShape(Graphics g, int boardSizeX, int boardSizeY, int offsetX, int rightBound)
    {
        for(Block b:blockList)
        {
            b.drawBlock(g,boardSizeX,boardSizeY,offsetX, rightBound);
        }
    }
    public void moveDown()
    {
        yPos+=1;
        for(Block b:blockList)
        {
            b.setY(b.getY()+1);
        }
    }
    public void moveRight()
    {
        xPos+=1;
        for(Block b:blockList)
        {
            b.setX(b.getX()+1);
        }     
    }
    public void moveLeft()
    {
        xPos-=1;
        for(Block b:blockList)
        {
            b.setX(b.getX()-1);
        }
    }
    public void rotateClockwise() {
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
    public ArrayList<Block>getClockwiseOccupied()
    {
        occupy = new ArrayList<>();
        for(Block b:blockList)
        {
            int xDistance = b.getX()-xPos;
            int yDistance = b.getY()-yPos;
            occupy.add(new Block(xPos-yDistance-1,yPos+xDistance));
        }
        return occupy;
    }
    public ArrayList<Block>getCounterClockwiseOccupied()
    {
        occupy = new ArrayList<>();
        for(Block b:blockList)
        {
            int xDistance = b.getX()-xPos;
            int yDistance = b.getY()-yPos;
            occupy.add(new Block(xPos+yDistance,yPos-xDistance-1));
        }
        return occupy;
    }
    public ArrayList<Block> getBlockList()
    {
        return blockList;
    }
}
