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

/**
 *
 * @author justi_000
 */
public class Grid {
    private ArrayList<Block> deadBlocks = new ArrayList<>();
    private Shape fallingShape;
    private Character character;
    

    private final int upperBound,bottomBound,rightBound,leftBound,startY,endY;
    private boolean isOver;
    private boolean isDead;

    
    public Grid(int right, int bottom, int start, int end)
    {
        rightBound = right;
        bottomBound = bottom;
        addShape();

        upperBound = 2;

        leftBound = 0;
        startY = start; //for now

        endY = end;
        isOver = false;
        isDead = false;
        character  = new Character(-1,startY);
    }
    public Character getCharacter()
    {
        return character;
    }
    public ArrayList<Block>getDeadBlocks()
    {
        return deadBlocks;
    }
    public void addShape()
    {
        fallingShape = randomShape();
    }
    public void levelEnd()
    {
        for(Block b:deadBlocks)
        {
            if(b.getY()<upperBound)
            {
                isDead = true;
            }
        }
    }
    public void setStatus(boolean b)
    {
        isOver = b;
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
            if(b.getX()<leftBound||b.getX()>=rightBound||b.getY()>bottomBound)
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
            if(b.getX()<leftBound||b.getX()>=rightBound||b.getY()>bottomBound)
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
    
    public boolean moveDown()
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
            levelEnd();
            if(isDead)
            {
                   //level is over, do something
                    System.exit(0);
            }
            else //if not dead
            {
                if(findPath(0, startY, rightBound-1, endY)||isOver) //if won
                {
                    isOver = true;
                    //jump to next level?
                }
                else //if not any above, keep going
                {
                    checkRow(); 
                    addShape();
                }
                   
            }
            
        }
        return canMove;
    }

    
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offsetX)
    {
        //draw the background image
        g.setColor(Color.white);
        g.fillRect(0, 0, 2000,2000);
        g.drawImage(ImageManager.getImage(14), 0, 0, gridSizeX+offsetX+offsetX, gridSizeY, null);//draw background, needs to change with level
        
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
        character.draw(g, gridSizeX, gridSizeY, offsetX, rightBound);
        
        
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
            if(count == rightBound - leftBound)
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
    
    //Drops the tile
    public void drop()
    {
        while(moveDown())
        {} 
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
                startBlock = block;
            }
            if(block.getX()==xEnd&&block.getY()==yEnd && !block.getEast())
            {
                endBlock = block;
            }
        }
        if(startBlock==null||endBlock==null)
        { 
            return false; 
        }
        
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
    public void moveCharacterDown(int x, int y)
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
                                character.setY(character.getY()+1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void moveCharacterUp(int x, int y)
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
                                character.setY(character.getY()-1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void moveCharacterLeft(int x, int y)
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
                                character.setX(character.getX()-1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void moveCharacterRight(int x, int y)
    {
        for(Block b:deadBlocks)
        {
            if(x==-1)//initial start outside grid, moves into grid
            {
                character.setX(0);
            }
            else if(b.getX()==x&&b.getY()==y)
            {
                if(!b.getEast())
                {
                    if(character.getX()==rightBound-1)
                    {
                        character.setX(character.getX()+1);
                        //LEVEL WON
                    }
                    else
                    {
                        for(Block d:deadBlocks)
                        {
                            if(d.getX()==x+1&&d.getY()==y)
                            {
                                if(!d.getWest())
                                {
                                    character.setX(character.getX()+1);
                                }
                            }
                        }
                    }
                    
                }
            }
        }
    }
}
