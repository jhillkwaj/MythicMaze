package mythical.maze;

/**
 * Object class that creates an "O" shape to be used in the grid
 * @author Richard Dong
 */
public class OShape extends Shape
{
    /**
     * Creates an "O" shape.
     * @param x an integer that represents the x coordinate.
     * @param y an integer that represents the y coordinate.
     * @param l an integer that represents the level the user is currently on.
     * @see Shape
     */
    public OShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x-1,y-1,false,false,false,true));
        this.getBlockList().add(new Block(x,y-1,false,true,false,false));
        this.getBlockList().add(new Block(x-1,y,false,false,false,true));
        this.getBlockList().add(new Block(x,y,false,true,false,false));  
        //shape appears below with coordinate labels.
        //         
        //        |(x-1, (x,   |
        //        | y-1)  y-1) |
        //        |            |
        //        |(x-1, (x,   |
        //        | y)    y    |
    }
    
    /**
     * Alternative constructor for creating an "O" shape.
     * @param x an integer that represents the x coordinate.
     * @param y an integer that represents the y coordinate.
     * @param l an integer that represents the level the user is currently on.
     * @param version an integer that represents the version to create.
     * @see Shape
     */
    public OShape(int x, int y,int l, int version)
    {
        super(x,y,l);
        if(version == 2)
        {
            this.getBlockList().add(new Block(x-1,y-1,true,false,false,false));
            this.getBlockList().add(new Block(x,y-1,true,false,false,false));
            this.getBlockList().add(new Block(x-1,y,false,false,false,false));
            this.getBlockList().add(new Block(x,y,false,false,false,false));
        }
        //shape appears below with coordinate labels.
        //        ____________ 
        //        (x-1, (x,   
        //         y-1)  y-1) 
        //                    
        //        (x-1, (x,y)   
        //         y)        
        else
        {
            this.getBlockList().add(new Block(x-1,y-1,false,false,false,true));
            this.getBlockList().add(new Block(x,y-1,false,true,false,false));
            this.getBlockList().add(new Block(x-1,y,false,false,false,true));
            this.getBlockList().add(new Block(x,y,false,true,false,false));  
        } 
        //same as default
    }
}
