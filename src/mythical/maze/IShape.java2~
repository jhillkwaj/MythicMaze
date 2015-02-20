package mythical.maze;

/**
 * Object class for creating "I" shaped pieces.
 * @author Richard Dong
 */
public class IShape extends Shape
{
   /**
    * Creates an "I" shaped piece.
    * @param x an integer that represents the object's x coordinate.
    * @param y an integer that represents the object's y coordinate.
    * @param l an integer that represents the object's level.
    * @see Shape
    */
    public IShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y-2,false,true,false,true));
        this.getBlockList().add(new Block(x,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x,y,false,true,false,true));
        this.getBlockList().add(new Block(x,y+1,false,true,false,true)); 
        //shape appears below with coordinate labels.
        // 
        // |(x,   |
        // | y-2) |
        // |      |
        // |(x,   |
        // | y-1) |
        // |      |
        // |(x,y) |
        // |      |
        // |      |
        // |(x,   |
        // | y+1) |
        // |      |  
    }
}
