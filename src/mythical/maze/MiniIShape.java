/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

/**
 *
 * @author Richard
 */
public class MiniIShape extends Shape{
    
    
    /*
    * Gets three integers x, y, l.
    * Uses the integers x and y as the coordinate (x, y) as the beginning point for drawing the shape.
    * Uses l as the integer to represent the level that the user is currently on.
    * @param x an integer that represents the shape's x coordinate
    * @param y an integer that represents the shape's y coordinate
    * @param l an integer that represents the level the user is currently on
    * @see Shape
    */
    public MiniIShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x,y,false,true,false,true));
        this.getBlockList().add(new Block(x,y+1,false,true,false,true));   
    }
    
    
    /*
    * Gets four integers x, y, l and v.
    * Uses the integers x and y as the coordinate (x, y) as the beginning point for drawing the shape.
    * Uses l as the integer to represent the level that the user is currently on.
    * Uses v to determine which version of the <code>MiniIShape</code> to create.
    * @param x an integer that represents the shape's x coordinate
    * @param y an integer that represents the shape's y coordinate
    * @param l an integer that represents the level the user is currently on
    * @param v an integer that represents the version of the <code>MiniIShape</code> to create
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
        }
        else if(version==3)
        {
            this.getBlockList().add(new Block(x,y-1,false,false,false,false));
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            this.getBlockList().add(new Block(x,y+1,false,false,false,false));    
        }
        else
        {
            this.getBlockList().add(new Block(x,y-1,false,true,false,true));
            this.getBlockList().add(new Block(x,y,false,true,false,true));
            this.getBlockList().add(new Block(x,y+1,false,true,false,true));  
        }
    
    }
}
