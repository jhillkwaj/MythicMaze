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
import static java.lang.Character.toUpperCase;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

    
/**
 *
 * @author 100032528
 */
public class GameRunner extends JPanel implements KeyListener {
    JFrame frame;
    private boolean[] keys = new boolean[10];
    private static Timer timer;
    private int timerSpeed = 60;
    private long startTime;
    private long updateTime = 0;
    private int eventTime = 900;
    private Color blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
    private BufferedImage back;
   

    private final int rightBound = 11;
    private final int bottomBound = 21;
    
    private Grid gameGrid;

   
    private Character character;

    
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
        frame.setSize((int)(width/2), (int) height);
        frame.setLocation((int)(width/4), 0);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
       timer = new Timer(timerSpeed, timerListener);
       timer.start();
       frame.repaint();
       frame.add(this);

       
       gameGrid = new Grid(rightBound, bottomBound);
       character = new Character();
       
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
       updateTime += eventTime;
       gameGrid.moveDown();

    }
    
    @Override
    public void paint(Graphics g)
    {
            
            int boardSizeY = this.getHeight();
            int boardSizeX = (int)(boardSizeY/(float)(bottomBound-2))*(rightBound);
            
            float ratio = (float)boardSizeY/(float)boardSizeX;
            
            if(this.getWidth()*boardSizeX*ratio<this.getHeight()*boardSizeY)
            {
                boardSizeX=this.getWidth()-(this.getWidth()/5);
                boardSizeY = (int)(boardSizeX/(float)(rightBound))*(bottomBound-2);
            }
            
           // System.out.println(boardSizeX + "   " + boardSizeY);
            
            
           
           // System.out.println((float)boardSizeY/(float)boardSizeX);
            if(back==null)
            {
                back=(BufferedImage)createImage(1920,1070);
            }
            Graphics2D twoDGraph = (Graphics2D)g;
            Graphics graphToBack= back.createGraphics();
           
            
            

            gameGrid.draw(graphToBack,boardSizeX,boardSizeY,boardSizeX/5);

            
            //check if character is active or not
            //if active, then enable movement and updating graphics
            graphToBack.setColor(Color.YELLOW);
            
            
            twoDGraph.drawImage(back,0,0,getWidth(),getHeight(),null); 
            
            if(System.currentTimeMillis()-updateTime >= eventTime)
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
        
        //put in if statement for character.
        if (ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S)
	{
            updateTime -= (eventTime)/3;
	}
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(!gameGrid.isOver())
        {
            if (ke.getKeyCode() == KeyEvent.VK_UP)
            {
                gameGrid.rotateRight();
            }
            if (ke.getKeyCode() == KeyEvent.VK_SHIFT)
            {
                gameGrid.rotateLeft();
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                gameGrid.moveRight();
            }
            if (ke.getKeyCode() == KeyEvent.VK_LEFT)
            {
                gameGrid.moveLeft();
            }

            switch(toUpperCase(ke.getKeyChar()))
                {
                    case KeyEvent.VK_W : gameGrid.rotateRight(); break; //clockwise
                    case KeyEvent.VK_A : gameGrid.moveLeft(); break; //left
                    case KeyEvent.VK_D : gameGrid.moveRight(); break; //right
                    case KeyEvent.VK_R : gameGrid.rotateLeft(); break; //counterclockwise
                    //case KeyEvent.VK_SPACE : keys[4]=true; break; //down

                }
            }
        else
        {
            int x = character.getX();
            int y = character.getY();
            if (ke.getKeyCode() == KeyEvent.VK_UP)
            {
                gameGrid.moveCharacterUp(x,y,character);
            }
            if (ke.getKeyCode() == KeyEvent.VK_DOWN)
            {
                gameGrid.moveCharacterDown(x,y,character);
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                gameGrid.moveCharacterRight(x,y,character);
            }
            if (ke.getKeyCode() == KeyEvent.VK_LEFT)
            {
                gameGrid.moveCharacterLeft(x,y,character);
            }

            switch(toUpperCase(ke.getKeyChar()))
                {
                    case KeyEvent.VK_W : gameGrid.moveCharacterUp(x,y,character); break; //clockwise
                    case KeyEvent.VK_A : gameGrid.moveCharacterLeft(x,y,character); break; //left
                    case KeyEvent.VK_D : gameGrid.moveCharacterRight(x,y,character); break; //right
                    case KeyEvent.VK_S : gameGrid.moveCharacterDown(x,y,character); break; //counterclockwise
                    //case KeyEvent.VK_SPACE : keys[4]=true; break; //down

                }
            }
        }
    }
