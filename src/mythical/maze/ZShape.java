/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

/**
 * Creates a Z-shaped block to be used in the grid
 * @author Richard
 */
public class ZShape extends Shape {
    
    /**
    * Constructor that gets three integers x, y, l.
    * Uses the integers x and y as the coordinate (x, y) as the beginning point for drawing the shape.
    * Uses l as the integer to represent the level that the user is currently on.
    * @param x an integer that represents the shape's x coordinate
    * @param y an integer that represents the shape's y coordinate
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
    }
    
}
