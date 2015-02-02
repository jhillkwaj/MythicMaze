package mythical.maze;

/**
 * Object class for creating "L" shaped pieces.
 * @author Richard Dong
 */
public class LShape extends Shape
{    
    /**
     * Creates an L shaped object
     * @param x an integer that represents the object's x coordinate.
     * @param y an integer that represents the object's y coordinate.
     * @param l an integer that represents the level of the shape.
     * @see Shape
     */
    public LShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x-1,y-2,false,true,false,true));
        this.getBlockList().add(new Block(x-1,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x-1,y,false,false,true,true));
        this.getBlockList().add(new Block(x,y,true,false,true,false));
        
        //shape appears below with coordinate labels.
        //  
        // |(x-1, |
        // | y-2) |
        // |      |
        // |(x-1, |
        // | y-1) |
        // |      |______
        // |(x-1,  (x,y) 
        // |   y)        
        // |_____________
    }  
}