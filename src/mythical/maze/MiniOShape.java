package mythical.maze;

import java.util.ArrayList;

/**
 * Creates a small "O" shaped piece.
 * @author Richard Dong
 */
public class MiniOShape extends Shape{
    /**
     * Creates a small "O" shaped piece
     * @param x an integer that represents the object's x coordinate.
     * @param y an integer that represents the object's y coordinate.
     * @param l an integer that represents the object's level.
     * @see Shape
     */
    public MiniOShape(int x, int y, int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y,false,true,false,true));
        //shape appears below with coordinate labels.
        //
        //        |      |
        //        |(x,y) |
        //        |      |        
    }
    
    /**
     * Alternative constructor for creating a small "O" shaped piece
     * @param x an integer that represents the object's x coordinate
     * @param y an integer that represents the object's y coordinate
     * @param l an integer that represents the level the user is currently on
     * @param version an integer that represents the version of the <code>MiniOShape</code> to create
     * @see Shape
     */
    public MiniOShape(int x, int y, int l, int version)
    {
        super(x,y,l);
        if(version == 2)
        {
            this.getBlockList().add(new Block(x,y,false,false,false,false));
            //shape appears below with coordinate labels.
            // *no walls at all
            //                
            //         (x,y)  
            //                       
        } 
        else
        {
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            //creates first default version, 
        }
    }
    
    /**
     * Rotates shape clockwise around center, in other terms, without changing its location.
     */
    @Override
    public void rotateClockwise()
    {
        for(Block b:getBlockList())
        {
            b.rotateClockwise();
        }
    }
    
    /**
     * Rotates shape counterclockwise around center, in other terms, without changing its location.
     */
    @Override
    public void rotateCounterClockwise()
    {
        for(Block b:getBlockList())
        {
            b.rotateCounterClockwise();
        }
    }
    
    /**
     * Returns a list of new blocks created after rotation clockwise.
     * @return blockList the list of blocks in the shape.
     */
    @Override
    public ArrayList<Block> getClockwiseOccupied()
    {
        ArrayList<Block>blockList = new ArrayList<>();
        blockList.add(new Block(xPos,yPos));
        return blockList;   
    }
    
    /**
     * Returns a list of new blocks created after rotation counterclockwise.
     * @return blockList the list of blocks in the shape.
     */
    @Override
    public ArrayList<Block> getCounterClockwiseOccupied()
    {
        ArrayList<Block>blockList = new ArrayList<>();
        blockList.add(new Block(xPos,yPos));
        return blockList;
    }
}
