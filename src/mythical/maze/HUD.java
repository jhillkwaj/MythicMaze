/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Richard
 */
public class HUD {
    
    private final int rightBound,bottomBound;
    private double time;
    
    public HUD(int right,int bottom)
    {
        rightBound = right;
        bottomBound = bottom;
        time = 0.0;
    }
    
    public void startTimer()
    {
        int delay = 100;
        ActionListener taskPerformer = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
               time=time+0.1;
            }
        };
        new Timer(delay, taskPerformer).start();
    }
    public void drawHUD(Graphics g,int gridSizeX, int gridSizeY, int uiArea)
    {
        gridSizeX-=uiArea;
        gridSizeY-=90;
        
        
        float idealRatio = 1.7f;
        if(gridSizeY/gridSizeX!=idealRatio)
        {
            gridSizeX=(int)(gridSizeY/idealRatio);
        }
        
        
        Font myFont=new Font("Times New Roman",Font.BOLD, 40);
            g.setColor(Color.BLACK);
            g.setFont(myFont);
            g.drawString("NEXT",(int)(((12.5)*(gridSizeX/((float)rightBound))))+200,
                6*gridSizeY/20);
            
            g.drawString("TIME:",(int)(((25.5)*(gridSizeX/((float)rightBound))))+200,
                6*gridSizeY/20);
            
            String displayTime=time+"";
            if(time<10)
            {
                displayTime=displayTime.substring(0,3);
            }
            else if(time<100)
            {
                displayTime=displayTime.substring(0,4);
            }
            else if(time<1000)
            {
                displayTime=displayTime.substring(0,5);
            }
            else if(time<10000)
            {
                displayTime=displayTime.substring(0,6);
            }
            g.drawString(displayTime,(int)(((28.5)*(gridSizeX/((float)rightBound))))+200,
                6*gridSizeY/20);
    }
}
