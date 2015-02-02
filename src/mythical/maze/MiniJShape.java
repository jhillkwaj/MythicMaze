package mythical.maze;

/**
 * Object class for creating small "J" shapes.
 * @author Richard
 */
public class MiniJShape extends Shape
{    
   /**
    * Creates a small "J" shape.
    * @param x an integer that represents the object's x coordinate
    * @param y an integer that represents the object's y coordinate
    * @param l an integer that represents the level the user is currently on
    * @see Shape
    */
    public MiniJShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x-1,y,true,false,true,false));
        this.getBlockList().add(new Block(x,y,false,true,true,false));  
        //shape appears below with coordinate labels.
        //
        //        |(x,   |
        //        | y-1) |
        //  ______|      |
        //  (x-1,  (x,y) |
        //     y)        |
        //  _____________|
    }
}
