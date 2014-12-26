/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class MiniOShape extends Shape{
    public MiniOShape(int x, int y,int l)
    {
        super(x,y,l);
        this.getBlockList().add(new Block(x,y,false,true,false,true));
         
    }
    public MiniOShape(int x, int y,int l,int version)
    {
        super(x,y,l);
        if(version == 2)
            this.getBlockList().add(new Block(x,y,false,false,false,false));
        else
            this.getBlockList().add(new Block(x,y,false,true,false,true));
         
    }
    @Override
    public void rotateClockwise()
    {
        for(Block b:getBlockList())
        {
            b.rotateClockwise();
        }
    }
    @Override
    public void rotateCounterClockwise()
    {
        for(Block b:getBlockList())
        {
            b.rotateCounterClockwise();
        }
    }
    @Override
    public ArrayList<Block> getClockwiseOccupied()
    {
        ArrayList<Block>blockList = new ArrayList<>();
        blockList.add(new Block(xPos,yPos));
        return blockList;   
    }
    @Override
    public ArrayList<Block> getCounterClockwiseOccupied()
    {
        ArrayList<Block>blockList = new ArrayList<>();
        blockList.add(new Block(xPos,yPos));
        return blockList;
    }
}
