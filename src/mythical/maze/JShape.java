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
public class JShape extends Shape {  
    /*
     * Creates a J shaped piece
     * @param x an integer that represents the shape's x coordinate
     * @param y an integer that represents the shape's y coordinate
     * @param l an integer that represents the level the user is currently on
     * @see Shape
     */

    /**
     * Creates a J shaped piece
     * @param x an integer that represents the shape x coordinate
     * @param y an integer that represents the shape y coordinate
     * @param l an integer that represents the level the user is currently on
     * @see Shape
     */
    
    public JShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y-2,false,true,false,true));
        this.getBlockList().add(new Block(x,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x,y,false,true,true,false));
        this.getBlockList().add(new Block(x-1,y,true,false,true,false));        
    }
}
