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
public class OShape extends Shape{
    public OShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x-1,y-1,false,false,false,true));
        this.getBlockList().add(new Block(x,y-1,false,true,false,false));
        this.getBlockList().add(new Block(x-1,y,false,false,false,true));
        this.getBlockList().add(new Block(x,y,false,true,false,false));    
    }
    
    public OShape(int x, int y,int l, int version)
    {
        super(x,y,l);
        if(version == 2)
        {
            this.getBlockList().add(new Block(x-1,y-1,true,false,false,false));
            this.getBlockList().add(new Block(x,y-1,true,false,false,false));
            this.getBlockList().add(new Block(x-1,y,false,false,false,false));
            this.getBlockList().add(new Block(x,y,false,false,false,false));
        }
        else
        {
            this.getBlockList().add(new Block(x-1,y-1,false,false,false,true));
            this.getBlockList().add(new Block(x,y-1,false,true,false,false));
            this.getBlockList().add(new Block(x-1,y,false,false,false,true));
            this.getBlockList().add(new Block(x,y,false,true,false,false));  
        }
        
    }
}
