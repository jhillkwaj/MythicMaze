package mythical.maze;

/**
 * Object class for creating "T" shaped objects.
 * @author Richard Dong
 */
public class TShape extends Shape 
{
    /**
     * Creates a "T" shape.
     * @param x an integer that represents the x coordinate
     * @param y an integer that represents the y coordinate
     * @param l an integer that represents the level the user is currently on
     * @see Shape
     */
    public TShape(int x, int y, int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
        this.getBlockList().add(new Block(x,y-1,true,false,false,false));
        this.getBlockList().add(new Block(x+1,y-1,true,false,true,false));
        this.getBlockList().add(new Block(x,y,false,true,false,true));        
        //shape appears below with coordinate labels.
        //  ______________________
        //  (x-1,   (x,    (x+1,
        //   y-1)    y-1)   y-1)
        //  _______        _______
        //         |(x,y) |
        //         |      |
        //         |      |
    }  
    
    /**
     * Alternative constructor for creating "T" shapes.
     * @param x an integer that represents the x coordinate
     * @param y an integer that represents the y coordinate
     * @param l an integer that represents the level the user is currently on
     * @param version an integer that represents the version of the <code>TShape</code> to create
     * @see Shape
     */
    public TShape(int x, int y, int l, int version)
    {
        super(x,y,l);
        if(version == 2)
        {
            this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y-1,false,false,false,false));
            this.getBlockList().add(new Block(x+1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            //shape appears below with coordinate labels.
            //  _______       _______
            //  (x-1,   (x,    (x+1,
            //   y-1)    y-1)   y-1)
            //  _______        _______
            //         |(x,y) |
            //         |      |
            //         |      |
        }
        else
        {
            this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y-1,true,false,false,false));
            this.getBlockList().add(new Block(x+1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y,false,true,false,true));     
            //same as default
        }
    } 
}
