package mythical.maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Creates a heads up display that displays user statistics, names and buttons.
 * @author Richard Dong
 */
public class HUD {
    
    private final int rightBound,bottomBound, level, score;//boundary indicators, statistics to display.
    private double time;//elapsed time.
    private final String profile;//name of user.
    private Timer timer;
    private boolean characterPhase;
    
    /**
     * Constructor that sets up the heads up display.
     * @param right the right bound for the size of the display.
     * @param bottom the bottom bound for the size of the display.
     * @param l the level of the player.
     * @param s the score of the player.
     * @param p the name of the player.
     */
    public HUD(int right,int bottom, int l, int s, String p)
    {
        rightBound = right;
        bottomBound = bottom;
        level = l;
        score = s;
        time = 0.0;
        profile = p;
        characterPhase = false; //timer is running during block phase.
    }
    
    /**
     * Creates and starts the timer for scoring purposes.
     */
    public void startTimer()
    {
        int delay = 100;
        ActionListener task = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
               time=time+0.1;//counts in tenthes of a second.
            }
        };
        timer =new Timer(delay, task);
        timer.start();
    }

    /**
     * Stops timer, used when block phase is over.
     */
    public void stopTimer()
    {
        timer.stop();
    }
   
    /**
     * Draws the items of the heads up display to the screen.
     * @param g the <code>Graphics</code> to paint onto.
     * @param gridSizeX an integer that represents the horizontal length of the grid.
     * @param gridSizeY an integer that represents the vertical length of the grid.
     * @param uiArea an integer that represents the area of the grid to offset.
     */
    public void drawHUD(Graphics g,int gridSizeX, int gridSizeY, int uiArea)
    {
        //calculate the correct dimmensions of the display
        gridSizeX-=uiArea;
        gridSizeY-=90;
        float idealRatio = 1.7f;
        if(gridSizeY/gridSizeX!=idealRatio)
        {
            gridSizeX=(int)(gridSizeY/idealRatio);
        }
        
        //start to write headings
        Font myFont=new Font("Times New Roman",Font.BOLD, 50);
            g.setColor(Color.BLACK);
            g.setFont(myFont);
            g.drawString("NEXT",(int)(14*(gridSizeX/((float)rightBound)))+200,
                (int)(1.5*gridSizeY/20));//next shape label
            
            g.drawString("TIME:",(int)(14*(gridSizeX/((float)rightBound)))+200,
                8*gridSizeY/20);//time label
            
            //format time correctly to one decimal place.
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
                8*gridSizeY/20);//draw time value.
            g.drawString("LEVEL:",(int)(((14)*(gridSizeX/((float)rightBound))))+200,
                10*gridSizeY/20);//draw level label.
            g.drawString(""+level,(int)(((18)*(gridSizeX/((float)rightBound))))+200,
                10*gridSizeY/20);//draw level value.
            g.drawString("SCORE:",(int)(((14)*(gridSizeX/((float)rightBound))))+200,
                12*gridSizeY/20);//draw score label.
            g.drawString(""+score,(int)(((18)*(gridSizeX/((float)rightBound))))+200,
                12*gridSizeY/20);//draw score value.
            g.drawString("PROFILE:",(int)(((14)*(gridSizeX/((float)rightBound))))+200,
                14*gridSizeY/20);//draw profile label.
            g.drawString(profile,(int)(14*(gridSizeX/((float)rightBound)))+200,
                15*gridSizeY/20);//draw profile name.
            //tells user if block phase has transitioned into character phase.
            if(characterPhase)
            {
                Font newFont=new Font("Times New Roman",Font.BOLD, 100);
                g.setColor(Color.RED);
                g.setFont(newFont);
                g.drawString("GO",(int)(14*(gridSizeX/((float)rightBound)))+200,
                18*gridSizeY/20);
            }
    }

    /**
     * Indicates a transition from block to character phase.
     */
    public void setCharacterPhase()
    {
        characterPhase = true;
    }

    /**
     * Returns the elapsed time for score calculation.
     * @return time the elapsed time.
     */
    public double getElapsedTime()
    {
        return time;
    }
}