package mythical.maze;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Parent class for the shape objects of the game.
 * @author Richard Dong
 */
public class Shape 
{  
    public int xPos,yPos,level;
    private ArrayList<Block>blockList = new ArrayList<>();
    private ArrayList<Block>occupy;
    
    /**
     * Gets three integer values x, y, l and public integers xPos, yPos and level to these values, respectively.
     * @param x the integer value to be represented by xPos.
     * @param y the integer value to be represented by yPos.
     * @param l the integer value to be represented by level.
     */
    public Shape(int x, int y, int l)
    {
        try
        {
            xPos = x;
            yPos = y;
            level = l;
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not initialize shape",ex);
        }   
    }

    /**
     * Draws the shape.
     * @param g the <code>Graphics</code> to paint to
     * @param boardSizeX an integer that represents the horizontal length of the grid.
     * @param boardSizeY an integer that represents the vertical length of the grid.
     * @param offsetX an integer that represents the space between blocks.
     * @param rightBound an integer that represents right boundary in blocks.
     */
    public void drawShape(Graphics g, int boardSizeX, int boardSizeY, int offsetX, int rightBound)
    {
        try
        {
            for(Block b:blockList)
            {
                b.drawBlock(g,level,boardSizeX,boardSizeY,offsetX, rightBound);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not draw shape",ex);
        }   
    }

    /**
     * Moves each block of the shape down and adds one to yPos.
     */
    public void moveDown()
    {
        try
        {
            yPos+=1;
            for(Block b:blockList)
            {
                b.setY(b.getY()+1);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move shape down",ex);
        } 
    }
    
    /**
     * Moves each block of the shape up and subtracts one from yPos.
     */
    public void moveUp()
    {
        try
        {
            yPos-=1;
            for(Block b:blockList)
            {
                b.setY(b.getY()-1);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move shape up",ex);
        }   
    }
    
    /**
     * Moves each block of the shape to the right and adds one to xPos.
     */
    public void moveRight()
    {
        try
        {
            xPos+=1;
            for(Block b:blockList)
            {
                b.setX(b.getX()+1);
            } 
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move shape to the right",ex);
        }     
    }

    /**
     * Moves each block of the shape to the left and subtracts one from xPos.
     */
    public void moveLeft()
    {
        try
        {
            xPos-=1;
            for(Block b:blockList)
            {
                b.setX(b.getX()-1);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not move shape to the left",ex);
        }
        
    }

    /**
     * Rotates each block of the shape clockwise.
     */
    public void rotateClockwise() 
    {
        try
        {
            for(Block b:blockList)
            {
                //each block is translated with respect to the center of the shape.
                int xDistance = b.getX()-xPos;//distance to center
                int yDistance = b.getY()-yPos;//distance to center
                b.setX(xPos-yDistance-1);//-1 offsets due to block size
                b.setY(yPos+xDistance);
                b.rotateClockwise();
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not rotate shape clockwise",ex);
        }  
    }

    /**
     * Rotates each block of the shape counterclockwise.
     */
    public void rotateCounterClockwise() 
    {
        try
        {
            for(Block b:blockList)
            {
                //same logical rules apply as above, only in reverse.
                int xDistance = b.getX()-xPos;
                int yDistance = b.getY()-yPos;
                b.setX(xPos+yDistance);
                b.setY(yPos-xDistance-1);
                b.rotateCounterClockwise();
            } 
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not roates shape counterclockwise",ex);
        }           
    }

    /**
     * Returns the <code>ArrayList</code> of the blocks of the shape rotated counterclockwise
     * but does not actually rotate the blocks counterclockwise.
     * @return occupy an <code>ArrayList</code> of the blocks of the shape if the shape was rotated.
     */
    public ArrayList<Block>getClockwiseOccupied()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create list of occupied blocks after clockwise rotation",ex);
            return null;
        }  
    }

    /**
     * Returns the <code>ArrayList</code> of the blocks of the shape rotated clockwise
     * but does not actually rotate the blocks clockwise
     * @return occupy an <code>ArrayList</code> of the blocks of the shape if the shape was rotated.
     */
    public ArrayList<Block>getCounterClockwiseOccupied()
    {
        try
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
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create list of occupied blocks after counterclockwise rotation",ex);
            return null;
        } 
    }

    /**
     * Returns the <code>ArrayList</code> of the blocks that the shape is composed of.
     * @return blockList an <code>ArrayList</code> of the blocks that shape is composed of.
     */
    public ArrayList<Block> getBlockList()
    {
        return blockList;
    }
}
