package mythical.maze;

/**
 * Object class for creating "J" shaped pieces.
 * @author Richard Dong
 */
public class JShape extends Shape 
{  
    /**
     * Creates a J shaped object.
     * @param x an integer that represents the shape x coordinate.
     * @param y an integer that represents the shape y coordinate.
     * @param l an integer that is used for version control.
     * @see Shape
     */
    public JShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y-2,false,true,false,true));
        this.getBlockList().add(new Block(x,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x,y,false,true,true,false));
        this.getBlockList().add(new Block(x-1,y,true,false,true,false));     
        //shape appears below with coordinate labels.
        //         ______
        //        |(x,   |
        //        | y-2) |
        //        |______|
        //        |(x,   |
        //        | y-1) |
        //  ______|______|
        // |(x-1, |(x,y) |
        // |   y) |      |
        // |______|______|
    }
}
