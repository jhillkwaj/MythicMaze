package mythical.maze;

/**
 * Object class for small "I" shapes.
 * @author Richard
 */
public class MiniIShape extends Shape
{    
   /**
    * Creates a small "I" shape.
    * @param x an integer that represents the object's x coordinate.
    * @param y an integer that represents the object's y coordinate.
    * @param l an integer that represents the level the user is currently on.
    * @see Shape
    */
    public MiniIShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x,y,false,true,false,true));
        this.getBlockList().add(new Block(x,y+1,false,true,false,true));   
        //shape appears below with coordinate labels.
        // 
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
    
   /**
    * Alternative constructor for creating a small "I" shape.
    * @param x an integer that represents the object's x coordinate.
    * @param y an integer that represents the object's y coordinate.
    * @param l an integer that represents the level the user is currently on.
    * @param version an integer that represents the version needed.
    * @see Shape
    */
    public MiniIShape(int x, int y,int l, int version)
    {
        super(x,y,l);
        if(version==2)
        {
            this.getBlockList().add(new Block(x,y-1,true,false,false,false));
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            this.getBlockList().add(new Block(x,y+1,false,false,true,false));    
            //shape appears below with coordinate labels.
            //  ______
            //  (x,   
            //   y-1) 
            //       
            // |(x,y) |
            // |      |
            // |      |
            //  (x,   
            //   y+1) 
            //  ______  
        }
        else if(version==3)
        {
            this.getBlockList().add(new Block(x,y-1,false,false,false,false));
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            this.getBlockList().add(new Block(x,y+1,false,false,false,false)); 
            //shape appears below with coordinate labels.
            //  
            //  (x,   
            //   y-1) 
            //       
            // |(x,y) |
            // |      |
            // |      |
            //  (x,   
            //   y+1) 
            //    
        }
        else
        {
            this.getBlockList().add(new Block(x,y-1,false,true,false,true));
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            this.getBlockList().add(new Block(x,y+1,false,true,false,true)); 
            //same as default
        }
    }
}
