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
    
    private final int rightBound,bottomBound, level, score;
    private double time;
    private String profile;
    private Timer timer;
    private boolean characterPhase;
    
    public HUD(int right,int bottom, int l, int s, String p)
    {
        rightBound = right;
        bottomBound = bottom;
        level = l;
        score = s;
        time = 0.0;
        profile = p;
        characterPhase = false;
    }
    
    public void startTimer()
    {
        int delay = 100;
        ActionListener task = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
               time=time+0.1;
            }
        };
        timer =new Timer(delay, task);
        timer.start();
    }
    public void stopTimer()
    {
        timer.stop();
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
        
        
        Font myFont=new Font("Times New Roman",Font.BOLD, 50);
            g.setColor(Color.BLACK);
            g.setFont(myFont);
            g.drawString("NEXT",(int)(14*(gridSizeX/((float)rightBound)))+200,
                (int)(1.5*gridSizeY/20));
            
            g.drawString("TIME:",(int)(14*(gridSizeX/((float)rightBound)))+200,
                8*gridSizeY/20);
            
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
            g.drawString(displayTime,(int)(17*(gridSizeX/((float)rightBound)))+200,
                8*gridSizeY/20);
            g.drawString("LEVEL:",(int)(((14)*(gridSizeX/((float)rightBound))))+200,
                10*gridSizeY/20);
            g.drawString(""+level,(int)(((18)*(gridSizeX/((float)rightBound))))+200,
                10*gridSizeY/20);
            g.drawString("SCORE:",(int)(((14)*(gridSizeX/((float)rightBound))))+200,
                12*gridSizeY/20);
            g.drawString(""+score,(int)(((18)*(gridSizeX/((float)rightBound))))+200,
                12*gridSizeY/20);    
            
            g.drawString("PROFILE:",(int)(((14)*(gridSizeX/((float)rightBound))))+200,
                14*gridSizeY/20);
            g.drawString(profile,(int)(14*(gridSizeX/((float)rightBound)))+200,
                15*gridSizeY/20);
            if(characterPhase)
            {
                Font newFont=new Font("Times New Roman",Font.BOLD, 100);
                g.setColor(Color.RED);
                g.setFont(newFont);
                g.drawString("GO",(int)(14*(gridSizeX/((float)rightBound)))+200,
                18*gridSizeY/20);
                
            }
    }
    public void setCharacterPhase()
    {
        characterPhase = true;
    }
    public double getElapsedTime()
    {
        return time;
    }
}
