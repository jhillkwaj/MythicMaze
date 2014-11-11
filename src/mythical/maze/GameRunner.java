/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

    
/**
 *
 * @author 100032528
 */
public class GameRunner extends JPanel implements KeyListener {
    JFrame frame;
    private static Timer timer;
    private int timerSpeed = 60;
    private long startTime;
    private Color blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
    private BufferedImage back;
    public LShape test;
    
    public void start()
    {
        this.frame = new JFrame();
        this.removeAll();
        startTime = System.currentTimeMillis();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
         frame.setSize((int)width, (int)height);
        
        frame.setTitle("Mythical Maze");
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
       timer = new Timer(timerSpeed, timerListener);
       timer.start();
       frame.repaint();
       frame.add(this);
       createBlock();//this is purely to demonstrate movement
    }
    public void createBlock()
    {
        test = new LShape(200,100);
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
                    //System.out.println(timeGap + "    " + timer.getDelay()) 
   		}

   	};
    
    
    @Override
    public void paint(Graphics g)
    {
        System.out.println("gah");
        if(back==null)
            {
                back=(BufferedImage)createImage(getWidth(),getHeight());   
            }
            Graphics2D twoDGraph = (Graphics2D)g;
            Graphics graphToBack= back.createGraphics();
            
            test.drawShape(graphToBack);
            //to draw item use: abc.drawImage(graphToBack);
            twoDGraph.drawImage(back,0,0,null); 
       // this.repaint();
       /* //fade to black in then back in on start
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
            
            
        }*/
        
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
