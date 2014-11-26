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
public class TShape extends Shape {
    
    public TShape(int x, int y)
    {
        super(x,y);
        this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
        this.getBlockList().add(new Block(x,y-1,true,false,false,false));
        this.getBlockList().add(new Block(x+1,y-1,true,false,true,false));
        this.getBlockList().add(new Block(x,y,false,true,false,true));        
    }
    
    public TShape(int x, int y, int version)
    {
        super(x,y);
        if(version == 2)
        {
            this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y-1,false,false,false,false));
            this.getBlockList().add(new Block(x+1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y,false,true,false,true));
        }
        else
        {
            this.getBlockList().add(new Block(x-1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y-1,true,false,false,false));
            this.getBlockList().add(new Block(x+1,y-1,true,false,true,false));
            this.getBlockList().add(new Block(x,y,false,true,false,true));     
        }
    }
    
}
