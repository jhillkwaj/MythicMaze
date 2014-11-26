/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author justi_000
 */
public class Grid {
    private ArrayList<Block> deadBlocks = new ArrayList<>();
    private Shape fallingShape;
    private int rightBound = 9;
    private int leftBound = 0;
    private int bottomBound = 21;
    
    public Grid()
    {
        addShape();
    }
    public ArrayList<Block>getDeadBlocks()
    {
        return deadBlocks;
    }
    public void addShape()
    {
        fallingShape = randomShape();
    }
    
    public Shape randomShape()
    {
        int randNum = (int)(Math.random()*11);
        if(randNum==0)
        {
            return new LShape(5,2);
        }
        else if(randNum==1)
        {
            return new JShape(5,2);
        }
        else if(randNum==2)
        {
            return new SShape(5,2);
        }
        else if(randNum==3)
        {
            return new ZShape(5,2);
        }
        else if(randNum==4)
        {
            return new IShape(5,2);
        }
        else if(randNum==5)
        {
            return new OShape(5,2);
        }
        else if(randNum==6)
        {
            return new TShape(5,2);
        }
        else if(randNum==7)
        {
            return new MiniLShape(5,2);
        }
        else if(randNum==8)
        {
            return new MiniJShape(5,2);
        }
        else if(randNum==9)
        {
            return new MiniIShape(5,2);
        }
        else
        {
            return new MiniOShape(5,2);
        }
    }
    
    public void rotateRight()
    {
        //method in Shape class returns rotation locations
        boolean canMove = true;
        for(Block b:fallingShape.getClockwiseOccupied())
        {
            for(Block d:deadBlocks)
            {
                if(b.getX()==d.getX()&&b.getY()==d.getY())
                {
                    canMove = false;
                }
            }
            if(b.getX()<leftBound||b.getX()>rightBound||b.getY()>bottomBound)
            {
                canMove = false;
            }
        }
        if(canMove)
        {
            fallingShape.rotateClockwise();
        }  
    }
    
    public void rotateLeft()
    {
        //method in Shape class returns rotation locations
        boolean canMove = true;
        for(Block b:fallingShape.getCounterClockwiseOccupied())
        {
            for(Block d:deadBlocks)
            {
                if(b.getX()==d.getX()&&b.getY()==d.getY())
                {
                    canMove = false;
                }
            }
            if(b.getX()<leftBound||b.getX()>rightBound||b.getY()>bottomBound)
            {
                canMove = false;
            }
        }
        if(canMove)
        {
            fallingShape.rotateCounterClockwise();
        } 
    }
    
    public void moveRight()
    {
        boolean canMove = true;
        for(Block b:fallingShape.getBlockList())
        {
            if(b.getX()+1>rightBound)
            {
                canMove = false;
            }
            for(Block d :deadBlocks)
            {
                if(b.getX()+1==d.getX()&&b.getY()==d.getY())
                {
                    canMove = false;
                }
            }
        }
        if(canMove)
        {
            fallingShape.moveRight();
        }
    }
    
    public void moveLeft()
    {
        boolean canMove = true;
        for(Block b:fallingShape.getBlockList())
        {
            if(b.getX()-1<leftBound)
            {
                canMove = false;
            }
            for(Block d :deadBlocks)
            {
                if(b.getX()-1==d.getX()&&b.getY()==d.getY())
                {
                    canMove = false;
                }
            }
        }
        if(canMove)
        {
            fallingShape.moveLeft();
        }
    }
    
    public void moveDown()
    {
        //check for collision with walls
        boolean canMove = true;
        for(Block b:fallingShape.getBlockList())
        {
            if(b.getY()+1>bottomBound)
            {
                canMove = false;
            }
            for(Block d :deadBlocks)
            {
                if(b.getX()==d.getX()&&b.getY()+1==d.getY())
                {
                    canMove = false;
                }
            }
            
        }
        if(canMove)
        {
            fallingShape.moveDown();
        }
        else
        {
            for(Block b : fallingShape.getBlockList())
            {
                deadBlocks.add(b);
            }
            checkRow();
            addShape();
        }
        //check for collision with other blocks
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
        
        
        
        for(Block b : deadBlocks)
        {
            b.drawBlock(g, gridSizeX, gridSizeY, offsetX);
        }
        for(Block b : fallingShape.getBlockList())
        {
            b.drawBlock(g, gridSizeX, gridSizeY, offsetX);
        }
    }
    public void checkRow()
    {
        for(int y=0;y<=bottomBound;y++)
        {
            int count = 0;
            for(Block b:deadBlocks)
            {
                if(b.getY()==y)
                {
                    count++;
                }
            }
            if(count == rightBound - leftBound + 1)
            {
                removeRow(y);
            }
        }
    }
    public void removeRow(int y)
    {
        Block toRemove = null;
        for(Block b:deadBlocks)
        {
            if(b.getY() == y)
            {
                toRemove = b;
            }
        }
        if(toRemove != null)
        {
            System.out.println(toRemove.getX()+","+toRemove.getY());
            deadBlocks.remove(toRemove);
            removeRow(y);
        }
        else
        {
            for(Block b:deadBlocks)
            {
                if(b.getY()<y)
                {
                    b.setY(b.getY()+1);
                }
            }
        }
    }
}
