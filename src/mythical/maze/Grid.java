package mythical.maze;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a Grid that the game will be played on; includes blocks and characters.
 * @author Justin Hill and Richard Dong
 */
public class Grid 
{
    private ArrayList<Block> deadBlocks = new ArrayList<>();//list of non-moving blocks
    private Shape fallingShape,nextShape;//shape that can be moved, next shape in queue
    private Character character;//character that user moves
    private int upperBound,bottomBound,rightBound,leftBound,startY,endY, level;//constants for grid
    private boolean hasWon,hasWonLevel,isDead;//variables for whether game has been won, lost, etc.
    private int scoreToAdd;//scores gained from winning level
    
    //NOTE:: due to the length of the class, methods have been organized into the 
    //following categories: creating new shapes, moving shapes, checking if game has
    //been won, character movement, graphics, and instance methods.
    
   /**
    * Constructor that takes 5 integers right, bottom, start, end and l and sets 
    * private final (constant) integers rightBound, bottomBound, startY, endY 
    * and level to these values, respectively.
    * @param right the integer value indicating grid size (right boundary).
    * @param bottom the integer value indicating grid size (bottom boundary).
    * @param start the integer value representing the starting space.
    * @param end the integer value representing the end space.
    * @param l the integer value representing level.
    */
    public Grid(int right, int bottom, int start, int end, int l)
    {
        try
        {
            rightBound = right;
            bottomBound = bottom;
            level = l;
            startY = start;
            endY = end;
            upperBound = 2;//leaves 2 spaces for shape to be created above screen
            leftBound = 0;//left most position

            hasWon = false;//obviously, when game starts, the user has not won
            hasWonLevel = false;//same
            isDead = false;//same
            scoreToAdd=0;
            character  = new Character(-1,start);//character is in space of starting position
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not initialize grid", ex);
        }  
    }
    
   /**
    * Prepares and adds 2 shapes for the beginning of the level.
    */
    public void startLevel()
    {
        try
        {
            nextShape();//generate random shape
            addShape();//add random shape to grid
            nextShape();//prep next shape to side to display
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not start grid level", ex);
        } 
    }

    //Methods below create random new shapes to be added onto the grid.
    
   /**
    * Creates a random shape and prepares to place on Heads up Display.
    */
    public void nextShape()
    {
        try
        {
            nextShape = randomShape();
            //add a new shape for displaying on the HUD
            for(int i=0;i<4;i++)//move to the right 6 spaces, down 4.
            {
                nextShape.moveRight();
                nextShape.moveRight();
                nextShape.moveDown();
            }
            nextShape.moveRight();
            nextShape.moveRight();
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create and move shape into next slot", ex);
        }
    }
    
   /**
    * Places the shape from the heads up display onto top, left corner of the grid.
    */
    public void addShape()
    {
        try
        {
            fallingShape = nextShape;
            //move back onto grid
            for(int i=0;i<4;i++)//up 4 spaces, left 6.
            {
                fallingShape.moveLeft();
                fallingShape.moveLeft();
                fallingShape.moveUp();
            }
            fallingShape.moveLeft();
            fallingShape.moveLeft();
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not next shape onto grid", ex);
        } 
    }
    
    /**
    * Generates a random integer and returns corresponding shape.
    * @return Shape the shape that is returned for presentation.
    */
    public Shape randomShape()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create random shape", ex);
            return null;//no shape returned
        }   
    }
   
    //Methods below rotate and move shapes located on the grid.
   
   /**
    * Rotates the current shape clockwise (right) if possible.
    */
    public void rotateRight()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not rotate shape to the right", ex);
        }
    }
    
   /**
    * Rotates the current shape counterclockwise (left) if possible.
    */
    public void rotateLeft()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not rotate shape to the left", ex);
        }     
    }
    
   /**
    * Moves the current block to the right if possible.
    */
    public void moveRight()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move shape to the right", ex);
        }     
    }
    
   /**
    * Moves the current block to the left if possible.
    */
    public void moveLeft()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move shape to the left", ex);
        }
    }
    
   /**
    * Checks to see if the current shape can move down if possible; if block cannot
    * move down, this indicates that a new block must be created or the game has ended.
    * @return canMove a boolean representing if the object can move down
    */
    public boolean moveDown()
    {
        try
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
            else//if the block cannot move down
            {
                for(Block b : fallingShape.getBlockList())
                {
                    deadBlocks.add(b);//add the shape to the list of nonmoving shapes
                }
                checkDead();//see if the game has ended
                if(findPath(0, startY, rightBound-1, endY)) //see if a correct path has been created
                {
                    hasWon = true;
                }
                else //has not won nor lost, but new shape must be created
                {
                    checkRow();
                    addShape();
                    nextShape();
                }             
            } 
            return canMove;//purpose of returning is for a method that moves blocks down to bottom.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move block down due to unknown error",ex);
            return false; 
        }      
    }

   /**
    * Checks if the level is lost by checking if blocks have overflowed the top.
    */
    public void checkDead()
    {
        try
        {
            for(Block b:deadBlocks)
            {
                if(b.getY()<=upperBound)//over the top of the screen
                {
                    isDead = true;
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not check if blocks were dead",ex);
        }   
    }
  
    /**
    * Checks to see if a row is complete and removes the row if it is complete
    */
    public void checkRow()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not check and remove full rows",ex);
        }
    }
    
    /**
    * Gets an integer y and removes the row corresponding to that integer by deleting all the blocks in the row
    * @param y an integer that represents the row number that is to be removed
    */
    public void removeRow(int y)
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not remove a full row",ex);
        }  
    }
    
    
    /**
     * Drops the shape as far down as possible.
     */
    public void drop()
    {
        while(moveDown()){}//repeatedly move down if can move down. 
    }
    
    /**
     * Algorithm that finds whether a correct path has been built using the blocks
     * from start to finish.
     * @param xStart the starting x coordinate value.
     * @param yEnd the end y coordinate value.
     * @param yStart the starting y coordinate value.
     * @param xEnd the end x coordinate value.
     * @return true or false based on if there exists a solution.
     */
    public boolean findPath(int xStart, int yStart, int xEnd, int yEnd)
    {
        try
        {
            Block startBlock = null;
            Block endBlock = null;
            //check to see if there are start and end blocks
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
            if(startBlock==null||endBlock==null)//there doesn't seem to even be first correct step.
            { 
                return false; 
            }

            //set up a map linking blocks to the blocks they are connected to

            HashMap<Block,ArrayList<Block>> blocks = new HashMap<>();

            for(Block block : deadBlocks)
            {
                ArrayList<Block> linkedBlocks = new ArrayList<>();
                for(Block otherBlock : deadBlocks)//traverse the blocks to form linked paths
                {
                    if(otherBlock.getX()==block.getX())
                    {
                        if(otherBlock.getY()==block.getY()-1&&!block.getNorth()&&!otherBlock.getSouth())//see if blocks are adjacent
                        {
                            linkedBlocks.add(otherBlock);//if linked, add block to linked block list
                        }
                        else if(otherBlock.getY()==block.getY()+1&&!block.getSouth()&&!otherBlock.getNorth())
                        {
                            linkedBlocks.add(otherBlock);
                        }
                    }
                    else if(otherBlock.getY()==block.getY())//same checking algorithm for y coordinates
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Error with finding solution path",ex);
            return false;
        }  
    }
    
    /**
     * Recursive algorithm that checks linked blocks to see if it is possible or 
     * not to traverse from one specific start to an end location.
     */
    private boolean findPath(Block block, Block endBlock, HashMap<Block,ArrayList<Block>> blocks)
    {
        try
        {
            if(block==endBlock)//base case, end reached
            { 
                return true; 
            }
            ArrayList<Block> links = blocks.get(block);
            blocks.remove(block);//move to next step
            if(links.isEmpty())//nothing is linked
            {
                return false; 
            }
            for(Block b : links)
            {
                if(blocks.containsKey(b) && findPath(b, endBlock, blocks))//recursive loop until end reached or no end
                {
                    return true; 
                }
            }
            return false;//at end, if no solution found, then return no solution.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not recursively find path",ex);
            return false;
        }
    }
    
    //Methods below are for character movement.
    
   /**
    * Moves the character down if possible
    * @param x an integer that represents the character's x coordinate.
    * @param y an integer that represents the character's y coordinate.
    */
    public void moveCharacterDown(int x, int y)
    {
        try
        {
            boolean hasNotMoved = true;
            for(Block b:deadBlocks)
            {
                if(b.getX()==x&&b.getY()==y)//find the current block the character is in.
                {
                    if(!b.getSouth())//check if current block has a wall to the south.
                    {
                        for(Block d:deadBlocks)
                        {
                            if(d.getX()==x&&d.getY()==y+1)//find the block the character is moving into.
                            {
                                if(!d.getNorth()&&hasNotMoved)//if that block doesn't have a wall.
                                {
                                    character.setY(character.getY()+1);//move into it.
                                    hasNotMoved = false;//limits movement to one space.
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move character down",ex);
        }   
    }
    
   /**
    * Moves the character up if possible.
    * @param x an integer that represents the character's x coordinate.
    * @param y an integer that represents the character's y coordinate.
    */
    public void moveCharacterUp(int x, int y)
    {
        try
        {
            boolean hasNotMoved = true;
            for(Block b:deadBlocks)
            {
                if(b.getX()==x&&b.getY()==y)//find block character is currently in.
                {
                    if(!b.getNorth())//if the block doesn't have a wall to the north.
                    {
                        for(Block d:deadBlocks)
                        {
                            if(d.getX()==x&&d.getY()==y-1)//find the block character is moving into.
                            {
                                if(!d.getSouth()&&hasNotMoved)//if that block doesn't have a wall.
                                {
                                    character.setY(character.getY()-1);//move into the block.
                                    hasNotMoved = false;//limits movement to one space.
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not character up",ex);
        }  
    }
    
   /**
    * Moves the character to the left if possible.
    * @param x an integer that represents the character's x coordinate.
    * @param y an integer that represents the character's y coordinate.
    */
    public void moveCharacterLeft(int x, int y)
    {
        try
        {
            boolean hasNotMoved = true;
            for(Block b:deadBlocks)
            {
                if(b.getX()==x&&b.getY()==y)//find block character is currently in.
                {
                    if(!b.getWest())//if block has a wall to the west.
                    {
                        for(Block d:deadBlocks)
                        {
                            if(d.getX()==x-1&&d.getY()==y)//find block character is moving into.
                            {
                                if(!d.getEast()&&hasNotMoved)//if block doesn't have a wall.
                                {
                                    character.setX(character.getX()-1);//move into block.
                                    hasNotMoved =false;//limit movement to one space.
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move character to the left",ex);
        }
    }
    
   /**
    * Moves the character to the right if possible.
    * @param x an integer that represents the character's x coordinate.
    * @param y an integer that represents the character's y coordinate.
    */
    public void moveCharacterRight(int x, int y)
    {
        try
        {
            boolean hasNotMoved = true;
            for(Block b:deadBlocks)
            {
                if(x==-1)//initial start outside grid, moves into grid
                {
                    character.setX(0);
                }
                else if(b.getX()==x&&b.getY()==y)//find block character is in.
                {
                    if(!b.getEast())
                    {
                        if(character.getX()==rightBound-1&&character.getY()==endY
                           &&hasNotMoved)//checks if one to the left of the finish.
                        {
                            character.setX(rightBound);//move into finish.
                            hasWonLevel=true;//level won!!
                            hasNotMoved = false;
                        }
                        else
                        {
                            for(Block d:deadBlocks)
                            {
                                if(d.getX()==x+1&&d.getY()==y)//check block moving into.
                                {
                                    if(!d.getWest()&&hasNotMoved)//check for walls.
                                    {
                                        character.setX(character.getX()+1);//move.
                                        hasNotMoved = false;//limit movement.

                                    }
                                }
                            }
                        }  
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move character to the left",ex);
        }   
    }
    
    //Method below is for graphics.
    
   /**
    * Draws the grid along with all its components after calculations.
    * @param g the <code>Graphics</code> to paint onto.
    * @param gridSizeX an integer that represents the horizontal length of the grid.
    * @param gridSizeY an integer that represents the vertical length of the grid.
    * @param uiArea an integer that represents the area of the heads up display.
    */
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int uiArea)
    {
        try
        {
            int offsetX = 200;
            //draw the background image
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 2000,2000);//draw over the previous with a black screen.
            //find the correct background image to draw.
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
                 g.drawImage(ImageManager.getImage(16), 0, 0, gridSizeX, gridSizeY, null);
            }
            else if(level == 8)
            {
                 g.drawImage(ImageManager.getImage(14), 0, 0, gridSizeX, gridSizeY, null);
            }
            else if(level==9)
            {
                 g.drawImage(ImageManager.getImage(13), 0, 0, gridSizeX, gridSizeY, null);
            }
            else
            {
                g.drawImage(ImageManager.getImage(18), 0, 0, gridSizeX, gridSizeY, null);
            }

            //calculations to make sure the grid is resizable with the screen size,
            //also makes sure grid spaces stay as squares.
            gridSizeX-=uiArea;
            gridSizeY-=70;

            float idealRatio = 1.7f;
            if(gridSizeY/gridSizeX!=idealRatio)
            {
                gridSizeX=(int)(gridSizeY/idealRatio);//sets to ideal square size.
            }
            //draw the HUD background
            g.setColor(new Color(0f,0f,0f,.5f));
            g.fillRect((int)(14*(gridSizeX/((float)rightBound)))+150, gridSizeY/40, (int)(10*(gridSizeX/((float)rightBound))), gridSizeY - (gridSizeY/4));
            //draw the grid
            g.setColor(new Color(1f,1f,1f,.3f));//slight shading to indicate grid spaces.
            for(int i = 0; i < rightBound; i++)//draws individual grid spaces
            {
                for(int j = 0; j < 22; j++)
                {
                    g.fillRect((int)(((i)*(gridSizeX/((float)rightBound))))+5+offsetX,
                    (int)(((j)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                    (int)(gridSizeX/((float)rightBound)) - 10, (int)(gridSizeY/20.0) - 10);
                }
            }

            g.setColor(new Color(0f,1f,1f,.3f));

            //draws outline for entire grid, is offset by screen size, maintains square shape of spots.
            g.fillRect((int)(((-1)*(gridSizeX/((float)rightBound))))+5+offsetX,
                    (int)(((startY)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                    (int)(gridSizeX/((float)rightBound)) - 10, (int)(gridSizeY/20.0) - 10);

            g.fillRect((int)(((rightBound)*(gridSizeX/((float)rightBound))))+5+offsetX,
                    (int)(((endY)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                    (int)(gridSizeX/((float)rightBound)) - 10, (int)(gridSizeY/20.0) - 10);

            //draws individual blocks onto grid.
            for(Block b : deadBlocks)
            {
                b.drawBlock(g,level, gridSizeX, gridSizeY, offsetX, rightBound);
            }

            //draws the falling block the user controls.
            for(Block b : fallingShape.getBlockList())
            {
                b.drawBlock(g,level, gridSizeX, gridSizeY, offsetX, rightBound);
            }
            character.draw(g, gridSizeX, gridSizeY, offsetX, rightBound);//draws the character
            nextShape.drawShape(g, gridSizeX, gridSizeY, offsetX, rightBound);//draws the next shape
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not draw grid with components",ex);
        }  
    }
    
    
    //Instance methods below
    
   /**
    * Returns character reference.
    * @return character the character reference.
    */
    public Character getCharacter()
    {
        return character;
    }
    
   /**
    * Returns the <code>ArrayList</code> of the blocks that are non-moving.
    * @return deadBlocks an <code>ArrayList</code> consisting of all the blocks that are dead
    */
    public ArrayList<Block> getDeadBlocks()
    {
        return deadBlocks;
    }
    
   /**
    * Returns the value of hasWonLevel as a boolean.
    * @return hasWonLevel a boolean that represents whether the user has beat the level or not.
    */
    public boolean hasWonLevel()
    {
        return hasWonLevel;
    }
    
   /**
    * Returns the value of hasWon as a boolean.
    * @return hasWon a boolean that represents whether the user has won the block phase.
    */
    public boolean hasWon()
    {
        return hasWon;
    }
    
   /**
    * Returns the value of isDead as a boolean.
    * @return isDead a boolean that represents whether the user has lost the level.
    */
    public boolean isDead()
    {
        return isDead;
    }

    /**
     * Returns the score that was gained during the level.
     * @return scoreToAdd the score to append to the user's overall score.
     */
    public int getAddedScore()
    {
        return scoreToAdd;
    }
}