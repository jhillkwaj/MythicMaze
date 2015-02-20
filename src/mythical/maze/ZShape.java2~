package mythical.maze;

/**
 * Object class for creating "Z" shapes.
 * @author Richard Dong
 */
public class ZShape extends Shape 
{
    /**
     * Creates a "Z" shape.
     * @param x an integer that represents the x coordinate
     * @param y an integer that represents the y coordinate
     * @param l an integer that represents the level the user is currently on
     * @see Shape
     */
    public ZShape(int x, int y, int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
        this.getBlockList().add(new Block(x,y-1,true,true,false,false));
        this.getBlockList().add(new Block(x,y,false,false,true,true));
        this.getBlockList().add(new Block(x+1,y,true,false,true,false)); 
        //shape appears below with coordinate labels.
        //  ______________
        //  (x-1,   (x,   |
        //   y-1)    y-1) |
        //  _______       |_______
        //         |(x,y)  (x+1,
        //         |        y)
        //         |______________
    }  
}
