/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
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
    private long updateTime = 0;
    private Color blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
    private BufferedImage back;
    public Shape test;
    private Grid gameGrid = new Grid();
    
    public void start()
    {
        this.frame = new JFrame();
        this.removeAll();
        startTime = System.currentTimeMillis();
        updateTime = startTime;
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
        test = new JShape(5,5);
    }
    ActionListener timerListener = new ActionListener() 
   	{
                   @Override
   		public void actionPerformed(ActionEvent e)
   		{
                    //fade to black in then back in
                    
                    //check how long code is takeing to run
                    //int timeGap = (int)(System.currentTimeMillis() - time);
                    //System.out.println(timeGap + "    " + timer.getDelay()) 
   		}

   	};
    
    public void update()
    {
       updateTime += 1000;
       test.moveDown();
       //test.rotateClockwise();
       test.rotateCounterClockwise();
    }
    
    
    @Override
    public void paint(Graphics g)
    {
            if(back==null)
            {
                back=(BufferedImage)createImage(1920,1070);
            }
            Graphics2D twoDGraph = (Graphics2D)g;
            Graphics graphToBack= back.createGraphics();
           
            
            
            gameGrid.draw(graphToBack,1200,1070,200);
            
            graphToBack.setColor(Color.YELLOW);
            test.drawShape(graphToBack,1200,1070,200);
            
            twoDGraph.drawImage(back,0,0,getWidth(),getHeight(),null); 
            
            if(System.currentTimeMillis()-updateTime >= 1000)
            {
                update();
            }
            
            repaint();
      
        
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
