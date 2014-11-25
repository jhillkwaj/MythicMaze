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
public class ZShape extends Shape {
    
    public ZShape(int x, int y)
    {
        super(x,y);
        this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
        this.getBlockList().add(new Block(x,y-1,true,true,false,false));
        this.getBlockList().add(new Block(x,y,false,false,true,true));
        this.getBlockList().add(new Block(x+1,y,true,false,true,false));    
    }
    
}
