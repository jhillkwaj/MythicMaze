/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.util.ArrayList;

/**
 * Object class for smaller "I" shapes.
 * @author Justin Hill
 */
public class SuperMiniIShape extends Shape {
    /**
    * Creates a small "I" shape.
    * @param x an integer that represents the object's x coordinate.
    * @param y an integer that represents the object's y coordinate.
    * @param l an integer that represents the level the user is currently on.
    * @see Shape
    */
    public SuperMiniIShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y,false,true,false,true));
        this.getBlockList().add(new Block(x,y+1,false,true,false,true));   
        //shape appears below with coordinate labels.
        // 
        // |(x,y) |
        // |      |
        // |      |
        // |(x,   |
        // | y+1) |
        // |      |  
    }
    
   /**
    * Alternative constructor for creating a small "I" shape.
    * @param x an integer that represents the object's x coordinate.
    * @param y an integer that represents the object's y coordinate.
    * @param l an integer that represents the level the user is currently on.
    * @param version an integer that represents the version needed.
    * @see Shape
    */
    public SuperMiniIShape(int x, int y,int l, int version)
    {
        super(x,y,l);
        if(version==2)
        {
            this.getBlockList().add(new Block(x,y,true,false,false,false));
            this.getBlockList().add(new Block(x,y+1,false,false,true,false));    
            //shape appears below with coordinate labels.
            //  ______
            //       
            //  (x,y) 
            //        
            //        
            //  (x,   
            //   y+1) 
            //  ______  
        }
        else
        {
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            this.getBlockList().add(new Block(x,y+1,false,true,false,true));   
            //same as default
        }
    }
    
    
    /**
     * Rotates each block of the shape clockwise.
     */
    public void rotateClockwise() {
        for(Block b:this.getBlockList())
        {
            //each block is translated with respect to the center of the shape.
            int xDistance = b.getX()-xPos;//distance to center
            int yDistance = b.getY()-yPos;//distance to center
            b.setX(xPos-yDistance);// offsets due to block size
            b.setY(yPos+xDistance);
            b.rotateClockwise();
        }
        
    }

    /**
     * Rotates each block of the shape counterclockwise.
     */
    public void rotateCounterClockwise() {
        for(Block b:this.getBlockList())
        {
            //same logical rules apply as above, only in reverse.
            int xDistance = b.getX()-xPos;
            int yDistance = b.getY()-yPos;
            b.setX(xPos+yDistance);
            b.setY(yPos-xDistance);
            b.rotateCounterClockwise();
        }        
    }

    /**
     * Returns the <code>ArrayList</code> of the blocks of the shape rotated counterclockwise
     * but does not actually rotate the blocks counterclockwise.
     * @return occupy an <code>ArrayList</code> of the blocks of the shape if the shape was rotated.
     */
    public ArrayList<Block>getClockwiseOccupied()
    {
        ArrayList<Block> occupy = new ArrayList<>();
        for(Block b:this.getBlockList())
        {
            int xDistance = b.getX()-xPos;
            int yDistance = b.getY()-yPos;
            occupy.add(new Block(xPos-yDistance,yPos+xDistance));
        }
        return occupy;
    }

    /**
     * Returns the <code>ArrayList</code> of the blocks of the shape rotated clockwise
     * but does not actually rotate the blocks clockwise
     * @return occupy an <code>ArrayList</code> of the blocks of the shape if the shape was rotated.
     */
    public ArrayList<Block>getCounterClockwiseOccupied()
    {
        ArrayList<Block> occupy = new ArrayList<>();
        for(Block b:this.getBlockList())
        {
            int xDistance = b.getX()-xPos;
            int yDistance = b.getY()-yPos;
            occupy.add(new Block(xPos+yDistance,yPos+xDistance));
        }
        return occupy;
    }
}
