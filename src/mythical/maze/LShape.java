/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class LShape extends Shape{
    
    
    
    public LShape(int x, int y)
    {
        super(x,y);
        blockList.add(new Block(x-Block.width,y-Block.height,false,true,false,true));
        blockList.add(new Block(x-Block.width,y,false,true,false,true));
        blockList.add(new Block(x-Block.width,y+Block.height,false,false,true,true));
        blockList.add(new Block(x,y+Block.height,true,false,true,false));        
    }

    public void rotateClockwise() {
        //check for rotation
        //rotate
        for(Block b:blockList)
        {
            b.rotateClockwise();
        }
        
    }

    public void rotateCounterClockwise() {
        for(Block b:blockList)
        {
            b.rotateCounterClockwise();
        }        
    }
    
    
    
    
}
