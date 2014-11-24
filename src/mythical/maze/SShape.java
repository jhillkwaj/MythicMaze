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
public class SShape extends Shape{
    
    public SShape(int x, int y)
    {
        super(x,y);
        blockList.add(new Block(x,y-1,true,false,false,true));
        blockList.add(new Block(x+1,y-1,true,false,true,false));
        blockList.add(new Block(x-1,y,true,false,true,false));
        blockList.add(new Block(x,y,false,true,true,false));    
    }
    
}
