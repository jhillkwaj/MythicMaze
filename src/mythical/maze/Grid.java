/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author justi_000
 */
public class Grid {
    private ArrayList<Block> deadBlocks = new ArrayList<>();
    private Shape fallingShape;
    private int rightBound = 12;
    private final int leftBound = 0;
    private int bottomBound = 21;
    private final int upperBound = 3;
    boolean isOver;
    
   
    int startY = 20;
    int endY = 18;
    
    public Grid(int rightBound, int bottomBound)
    {
        this.rightBound = rightBound;
        this.bottomBound = bottomBound;
        addShape();
        rightBound = 9;
        bottomBound = 21;
        startY = 18;
        endY = 18;
        isOver = false;
    }
    public ArrayList<Block>getDeadBlocks()
    {
        return deadBlocks;
    }
    public void addShape()
    {
        fallingShape = randomShape();
    }
    public boolean levelEnd()
    {
        for(Block b:deadBlocks)
        {
            if(b.getY()<upperBound)
            {
                System.out.println("dead");
                return true;
            }
        }
        return false;
    }
    public Shape randomShape()
    {
        int randNum = (int)(Math.random()*16);
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
        else if(randNum<=6)
        {
            if(Math.random()<.7f)
                return new OShape(5,2);
            else
                return new OShape(5,2,2);
        }
        else if(randNum==7)
        {
            if(Math.random()<.5f)
                return new TShape(5,2);
            else
                return new TShape(5,2,2);
        }
        else if(randNum<=9)
        {
            return new MiniLShape(5,2);
        }
        else if(randNum<=11)
        {
             return new MiniJShape(5,2);
        }
        else if(randNum<=13)
        {
            if(Math.random()<.5f)
                return new MiniIShape(5,2);
            else if(Math.random()<.5f)
                return new MiniIShape(5,2,2);
            else
                return new MiniIShape(5,2,3);
        }
        else
        {
            if(Math.random()<.5)
                return new MiniOShape(5,2);
            else
                return new MiniOShape(5,2,2);
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
            if(b.getX()+2>rightBound)
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
            
            if(findPath(0, startY, rightBound-1, endY))
            {
                
            }
            else
            {
                checkRow();
                addShape();
            }
            
        }
        //check for collision with other blocks
    }

    
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offsetX)
    {
        //draw the background image
        g.setColor(Color.white);
        g.fillRect(0, 0, 2000,2000);
        g.drawImage(ImageManager.getImage(10), 0, 0, gridSizeX+offsetX+offsetX, gridSizeY, null);
        
        //draw the grid
        g.setColor(new Color(1f,1f,1f,.3f));
        for(int i = 0; i < rightBound; i++)
        {
            for(int j = 0; j < 22; j++)
            {
                g.fillRect((int)(((i)*(gridSizeX/((float)rightBound))))+5+offsetX,
                (int)(((j)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                (int)(gridSizeX/((float)rightBound)) - 10, (int)(gridSizeY/20.0) - 10);
            }
        }
        
        g.setColor(new Color(0f,1f,1f,.3f));
        
        g.fillRect((int)(((-1)*(gridSizeX/((float)rightBound))))+5+offsetX,
                (int)(((startY)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                (int)(gridSizeX/((float)rightBound)) - 10, (int)(gridSizeY/20.0) - 10);
        
        g.fillRect((int)(((rightBound)*(gridSizeX/((float)rightBound))))+5+offsetX,
                (int)(((endY)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                (int)(gridSizeX/((float)rightBound)) - 10, (int)(gridSizeY/20.0) - 10);
        
        for(Block b : deadBlocks)
        {
            b.drawBlock(g, gridSizeX, gridSizeY, offsetX, rightBound);
        }
        for(Block b : fallingShape.getBlockList())
        {
            b.drawBlock(g, gridSizeX, gridSizeY, offsetX, rightBound);
        }
        
    }
    public void checkRow()
    {
        for(int y=upperBound;y<=bottomBound;y++)
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
    //returns true if a path exists
    public boolean findPath(int xStart, int yStart, int xEnd, int yEnd)
    {
        Block startBlock = null;
        Block endBlock = null;
        //check to see if there are stat and end blocks
        for(Block block : deadBlocks)
        {
            if(block.getX()==xStart&&block.getY()==yStart && !block.getWest())
            {
                System.out.println("Start");
                startBlock = block;
            }
            if(block.getX()==xEnd&&block.getY()==yEnd && !block.getEast())
            {
                System.out.println("end");
                endBlock = block;
            }
        }
        if(startBlock==null||endBlock==null)
        { System.out.println("No Start or end"); return false; }
        
        //set up a map linking blocks to the blocks they are connected to
        
        HashMap<Block,ArrayList<Block>> blocks = new HashMap<>();
        
        for(Block block : deadBlocks)
        {
            ArrayList<Block> linkedBlocks = new ArrayList<>();
            for(Block otherBlock : deadBlocks)
            {
                if(otherBlock.getX()==block.getX())
                {
                    if(otherBlock.getY()==block.getY()-1&&!block.getNorth()&&!otherBlock.getSouth())
                    {
                        linkedBlocks.add(otherBlock);
                    }
                    else if(otherBlock.getY()==block.getY()+1&&!block.getSouth()&&!otherBlock.getNorth())
                    {
                        linkedBlocks.add(otherBlock);
                    }
                }
                else if(otherBlock.getY()==block.getY())
                {
                    if(otherBlock.getX()==block.getX()-1&&!block.getWest()&&!otherBlock.getEast())
                    {
                        linkedBlocks.add(otherBlock);
                    }
                    else if(otherBlock.getX()==block.getX()+1&&!block.getEast()&&!otherBlock.getWest())
                    {
                        linkedBlocks.add(otherBlock);
                    }
                }
            }
            blocks.put(block, linkedBlocks);
        }
        System.out.println(blocks.size());
        //check for a solution
        return findPath(startBlock, endBlock, blocks);
    }
    
    private boolean findPath(Block block, Block endBlock, HashMap<Block,ArrayList<Block>> blocks)
    {
        
        if(block==endBlock)
        { 
            return true; 
        }
        
        ArrayList<Block> links = blocks.get(block);
        blocks.remove(block);
        
        if(links.isEmpty())
        { return false; }
        
        
        for(Block b : links)
        {
            if(blocks.containsKey(b) && findPath(b, endBlock, blocks))
            {
                isOver = true;
                return true;
                
            }
        }
        
        return false;
    }
    public boolean isOver()
    {
        return isOver;
    }
    public void moveCharacterDown(int x, int y,Character c)
    {
        for(Block b:deadBlocks)
        {
            if(b.getX()==x&&b.getY()==y)
            {
                if(!b.getSouth())
                {
                    for(Block d:deadBlocks)
                    {
                        if(d.getX()==x&&d.getY()==y+1)
                        {
                            if(!d.getNorth())
                            {
                                c.setY(c.getY()+1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void moveCharacterUp(int x, int y,Character c)
    {
        for(Block b:deadBlocks)
        {
            if(b.getX()==x&&b.getY()==y)
            {
                if(!b.getNorth())
                {
                    for(Block d:deadBlocks)
                    {
                        if(d.getX()==x&&d.getY()==y-1)
                        {
                            if(!d.getSouth())
                            {
                                c.setY(c.getY()-1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void moveCharacterLeft(int x, int y,Character c)
    {
        for(Block b:deadBlocks)
        {
            if(b.getX()==x&&b.getY()==y)
            {
                if(!b.getWest())
                {
                    for(Block d:deadBlocks)
                    {
                        if(d.getX()==x-1&&d.getY()==y)
                        {
                            if(!d.getEast())
                            {
                                c.setX(c.getX()-1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void moveCharacterRight(int x, int y,Character c)
    {
        for(Block b:deadBlocks)
        {
            if(b.getX()==x&&b.getY()==y)
            {
                if(!b.getEast())
                {
                    for(Block d:deadBlocks)
                    {
                        if(d.getX()==x+1&&d.getY()==y)
                        {
                            if(!d.getWest())
                            {
                                c.setX(c.getX()+1);
                            }
                        }
                    }
                }
            }
        }
    }
}
