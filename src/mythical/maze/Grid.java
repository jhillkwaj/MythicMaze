/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

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
    private Shape fallingShape,nextShape;
    private Character character;
    

    private final int upperBound,bottomBound,rightBound,leftBound,startY,endY, level;
    private boolean hasWon,hasWonLevel,isDead;
    private int scoreToAdd;
    
    /**
    * Gets 5 integers right, bottom, start, end and l and sets private final (constant) integers 
    * rightBound, bottomBound, startY, endY and level to these values, respectively.
    * @param right the integer value to be represented by rightBound
    * @param bottom the integer value to be represented by bottomBound
    * @param start the integer value to be represented by startY
    * @param end the integer value to be represented by endY
    * @param l the integer value to be represented by level
    */
    public Grid(int right, int bottom, int start, int end, int l)
    {
        rightBound = right;
        bottomBound = bottom;
        level = l;

        upperBound = 2;

        leftBound = 0;
        startY = start; //for now

        endY = end;
        hasWon = false;
        hasWonLevel = false;
        isDead = false;
        scoreToAdd=0;
        character  = new Character(-1,start);
    }
    
    /**
    * Prepares and adds shape to the grid after each turn
    */
    public void startLevel()
    {
        nextShape();//generate random shape
        addShape();//add random shape
        nextShape();//prep next shape
    }
    
    /**
    * Returns the object character as a <code>Character</code>
    * @return character a <code>Character</cod> that is the user's character
    */
    public Character getCharacter()
    {
        return character;
    }
    
    /**
    * Returns the <code>ArrayList</code> of the blocks that are dead.
    * @return deadBlocks an <code>ArrayList</code> consisting of all the blocks that are dead
    */
    public ArrayList<Block> getDeadBlocks()
    {
        return deadBlocks;
    }
    
    /**
    * Creates a random shape and prepares to place on grid.
    */
    public void nextShape()
    {
        nextShape = randomShape();
        //move to right for HUD
        for(int i=0;i<4;i++)
        {
            nextShape.moveRight();
            nextShape.moveRight();
            nextShape.moveDown();
        }
        nextShape.moveRight();
        nextShape.moveRight();
    }
    
    /**
    *  Places the shape in the top, left corner of the grid.
    */
    public void addShape()
    {
        fallingShape = nextShape;
        //move back onto grid
        for(int i=0;i<4;i++)
        {
            fallingShape.moveLeft();
            fallingShape.moveLeft();
            fallingShape.moveUp();
        }
        fallingShape.moveLeft();
        fallingShape.moveLeft();
    }
    
    /**
    * Sees if character is "dead" by checking if there is too many dead blocks in one column
    */
    public void checkDead()
    {
        for(Block b:deadBlocks)
        {
            if(b.getY()<=upperBound)
            {
                isDead = true;
            }
        }
    }
    
    /**
    * Generates a random integer and returns a shape corresponding to the random integer
    * @return
    */
    public Shape randomShape()
    {
        int randNum = (int)(Math.random()*16);
        if(randNum==0)
        {
            return new LShape(5,2,level);
        }
        else if(randNum==1)
        {
            return new JShape(5,2,level);
        }
        else if(randNum==2)
        {
            return new SShape(5,2,level);
        }
        else if(randNum==3)
        {
            return new ZShape(5,2,level);
        }
        else if(randNum==4)
        {
            return new IShape(5,2,level);
        }
        else if(randNum<=6)
        {
            if(Math.random()<.7f)
                return new OShape(5,2,level);
            else
                return new OShape(5,2,level,2);
        }
        else if(randNum==7)
        {
            if(Math.random()<.5f)
                return new TShape(5,2,level);
            else
                return new TShape(5,2,level,2);
        }
        else if(randNum<=9)
        {
            return new MiniLShape(5,2,level);
        }
        else if(randNum<=11)
        {
             return new MiniJShape(5,2,level);
        }
        else if(randNum<=13)
        {
            if(Math.random()<.5f)
                return new MiniIShape(5,2,level);
            else if(Math.random()<.5f)
                return new MiniIShape(5,2,level,2);
            else
                return new MiniIShape(5,2,level,3);
        }
        else
        {
            if(Math.random()<.5)
                return new MiniOShape(5,2,level);
            else
                return new MiniOShape(5,2,level,2);
        }
    }
    
    /**
    * Checks to see if the current shape can rotate right (clockwise)
    */
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
    
    /**
    * Checks to see if the current shape can rotate left (counterclockwise)
    */
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
    
    /**
    * Checks to see if the current shape can move right
    */
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
    
    /**
    * Checks to see if the current shape can move left
    */
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
    
    /**
    * Checks to see if the current shape can move down
    * @return canMove a boolean representing if the object can move down
    */
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
            checkDead();
            if(findPath(0, startY, rightBound-1, endY)) //if won
            {
                hasWon = true;
            }
            else //if has not won, has not died
            {
                checkRow();
                addShape();
                nextShape();
            }             
        } 
        return canMove;
    }

    /**
    *
    * @param g the <code>Graphics</code> to paint to
    * @param gridSizeX an integer that represents the horizonatal length of the grid
    * @param gridSizeY an integer that represents the vertical length of the grid
    * @param uiArea an integer that represents 
    */
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int uiArea)
    {
        int offsetX = 200;
        
        //draw the background image
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 2000,2000);

        if(level==2)
        {
             g.drawImage(ImageManager.getImage(14), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level ==3)
        {
             g.drawImage(ImageManager.getImage(15), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level == 1)
        {
             g.drawImage(ImageManager.getImage(17), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level == 4)
        {
             g.drawImage(ImageManager.getImage(21), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level == 5)
        {
             g.drawImage(ImageManager.getImage(19), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level == 6)
        {
             g.drawImage(ImageManager.getImage(20), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level == 7)
        {
             g.drawImage(ImageManager.getImage(18), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level == 8)
        {
             g.drawImage(ImageManager.getImage(16), 0, 0, gridSizeX, gridSizeY, null);
        }
        else if(level==9)
        {
             g.drawImage(ImageManager.getImage(14), 0, 0, gridSizeX, gridSizeY, null);
        }
        else
        {
            g.drawImage(ImageManager.getImage(13), 0, 0, gridSizeX, gridSizeY, null);
        }
       
        
        gridSizeX-=uiArea;
        gridSizeY-=70;
        
        float idealRatio = 1.7f;
        if(gridSizeY/gridSizeX!=idealRatio)
        {
            gridSizeX=(int)(gridSizeY/idealRatio);
        }
        
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
            b.drawBlock(g,level, gridSizeX, gridSizeY, offsetX, rightBound);
        }
        for(Block b : fallingShape.getBlockList())
        {
            b.drawBlock(g,level, gridSizeX, gridSizeY, offsetX, rightBound);
        }
        character.draw(g, gridSizeX, gridSizeY, offsetX, rightBound);
        nextShape.drawShape(g, gridSizeX, gridSizeY, offsetX, rightBound);
        
        
    }
    
    /**
    * Checks to see if a row is complete and removes the row if it is complete
    */
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
    
    /**
    * Gets an integer y and removes the row corresponding to that integer by deleting all the blocks in the row
    * @param y an integer that represents the row number that is to be removed
    */
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
            scoreToAdd+=100;//earn points for removing rows
        }
    }
    
    
    /**
    * Drops the shape
    */
    //Drops the tile
    public void drop()
    {
        while(moveDown())
        {} 
    }
    
    /**
    *
    * @return 
    */
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
    
    /**
    * 
    */
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
                return true; 
            }
        }
        
        return false;
    }
    
    /**
    * Moves the character down 
    * @param x an integer that represents 
    * @param y an integer that represents
    */
    public void moveCharacterDown(int x, int y)
    {
        boolean hasNotMoved = true;
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
                            if(!d.getNorth()&&hasNotMoved)
                            {
                                character.setY(character.getY()+1);
                                hasNotMoved = false;
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
    * Moves the character up 
    * @param x an integer that represents 
    * @param y an integer that represents
    */
    public void moveCharacterUp(int x, int y)
    {
        boolean hasNotMoved = true;
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
                            if(!d.getSouth()&&hasNotMoved)
                            {
                                character.setY(character.getY()-1);
                                hasNotMoved = false;
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
    * Moves the character to the left 
    * @param x an integer that represents 
    * @param y an integer that represents
    */
    public void moveCharacterLeft(int x, int y)
    {
        boolean hasNotMoved = true;
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
                            if(!d.getEast()&&hasNotMoved)
                            {
                                character.setX(character.getX()-1);
                                hasNotMoved =false;
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
    * Moves the character to the right 
    * @param x an integer that represents 
    * @param y an integer that represents
    */
    public void moveCharacterRight(int x, int y)
    {
        boolean hasNotMoved = true;
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
                    if(character.getX()==rightBound-1&&character.getY()==endY&&hasNotMoved)
                    {
                        character.setX(rightBound);
                        hasWonLevel=true;
                        hasNotMoved = false;
                    }
                    else
                    {
                        for(Block d:deadBlocks)
                        {
                            if(d.getX()==x+1&&d.getY()==y)
                            {
                                if(!d.getWest()&&hasNotMoved)
                                {
                                    character.setX(character.getX()+1);
                                    hasNotMoved = false;
                                    
                                }
                            }
                        }
                    }
                    
                }
            }
        }
    }
    
    /**
    * Returns the value of hasWonLevel as a boolean.
    * @return hasWonLevel a boolean that represents whether the user has beat the level or not
    */
    public boolean hasWonLevel()
    {
        return hasWonLevel;
    }
    
    /**
    * Returns the value of hasWon as a boolean.
    * @return hasWon a boolean that represents whether the user has 
    */
    public boolean hasWon()
    {
        return hasWon;
    }
    
    /**
    * Returns the value of isDead as a boolean.
    * @return isDead a boolean that represents
    */
    public boolean isDead()
    {
        return isDead;
    }
    public int getAddedScore()
    {
        return scoreToAdd;
    }
}
