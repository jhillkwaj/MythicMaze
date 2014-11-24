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
    
    public JShape(int x, int y)
    {
        super(x,y);
        blockList.add(new Block(x,y-2,false,true,false,true));
        blockList.add(new Block(x,y-1,false,true,false,true));
        blockList.add(new Block(x,y,false,false,true,true));
        blockList.add(new Block(x-1,y,true,false,true,false));        
    }
}
