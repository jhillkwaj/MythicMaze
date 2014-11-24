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
public class MiniOShape extends Shape{
     public MiniOShape(int x, int y)
    {
        super(x,y);
        blockList.add(new Block(x,y,false,true,false,true));
         
    }
    
}
