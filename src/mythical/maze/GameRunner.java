/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

    
/**
 *
 * @author 100032528
 */
public class GameRunner extends JPanel implements KeyListener {
    
    private static Timer timer;
    private int timerSpeed = 60;
    
    long startTime;
    
    
    Color blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
    
    public void start(JFrame frame)
    {
        frame.removeAll();
        startTime = System.currentTimeMillis();
        frame.setVisible(false);
        frame.setTitle("Mythical Maze");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        frame.isFocused();
        frame.add(this);
        frame.setVisible(true); 
        frame.repaint();
        
       timer = new Timer(timerSpeed, timerListener);
       timer.start();
        
      

        frame.addKeyListener(this);
        
   
    }
    
    ActionListener timerListener = new ActionListener() 
   	{
                   @Override
   		public void actionPerformed(ActionEvent e)
   		{
                    //fade to black in then back in
                    long time = System.currentTimeMillis();
                    
          
                    
                    repaint();
                    
                    
                    //check how long code is takeing to run
                    //int timeGap = (int)(System.currentTimeMillis() - time);
                    //System.out.println(timeGap + "    " + timer.getDelay());
                    
                    
                  
   		}

   	};
    
    @Override
    public void paintComponent(Graphics g)
    {
        //fade to black in then back in on start
        System.out.println((System.currentTimeMillis()-startTime)/60);
        //fade out
        if((System.currentTimeMillis()-startTime)/60<100)
        {
            g.setColor(blackStartFilter);
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            blackStartFilter = new Color(0.0f,0.0f,0.0f,(System.currentTimeMillis()-startTime)/60000);
            
        }
        
        //draw game stuff
        if((System.currentTimeMillis()-startTime)/60>100)
        {
            
            
        }
    }
   

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    
    }

        
    
}
