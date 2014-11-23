/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author justi_000
 */
public class Grid {
    private boolean[][] spotsFull = new boolean[10][22];
    
    public void addBlock(Block b)
    {
        
    }
    
    public void draw(Graphics g,int gridSizeX, int gridSizeY, int offsetX)
    {
        //draw the background image
        g.setColor(Color.red);
        g.fillRect(0, 0, gridSizeX+offsetX, gridSizeY);
        
        //draw the grid
        g.setColor(new Color(0f,0f,0f,.3f));
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 22; j++)
            {
                g.fillRect((int)(((i)*(gridSizeX/10.0)))+5+offsetX,
                (int)(((j)*(gridSizeY/20)))-(2*(int)(gridSizeY/20.0))+5,
                (int)(gridSizeX/10.0) - 10, (int)(gridSizeY/20.0) - 10);
            }
        }
    }
}
