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
public class MiniLShape extends Shape{
    public MiniLShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x-1,y-1,false,true,false,true));
        this.getBlockList().add(new Block(x-1,y,false,false,true,true));
        this.getBlockList().add(new Block(x,y,true,false,true,false));
    }
}
