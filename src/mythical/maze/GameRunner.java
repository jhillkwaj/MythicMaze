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
    
    private JFrame frame;
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
    private int startY, endY,level,score;
    
    private Grid gameGrid;
    private HUD hud;

    private String playerName;
    private int slot;
    private int highscore;
    


    /*
    * Creates a frame for the game to be played in
    * @see JFrame
    */

    public void start(String name)
    {
        this.frame = new JFrame();
        this.removeAll();
        startTime = System.currentTimeMillis();
        updateTime = startTime;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
         frame.setSize((int)width, (int)height);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setTitle("Mythical Maze");
        
        frame.setLocation((int)(width/4), 0);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);

        
        playerName = name;
        if(level==0)
        {
            startY = 18; //basic level
            endY = 18; //basic level
            level = 1;//extract from file later
            score = 0;//extract from file later
            eventTime = 900 / ((1+level)/2);
        }


       

        startLevel();
       
    }
    


    /*
    * Creates a timer and adjusts the game grid based on user's current level
    */
    public void start(String[] data, String name)
    {
        
        startY = Integer.parseInt(data[3]);
        endY = Integer.parseInt(data[4]);
        highscore = Integer.parseInt(data[2]);
        level = Integer.parseInt(data[1]);
        score = Integer.parseInt(data[0]);
        eventTime = 900 / ((1+level)/2);
        start(name);
    }
    


    

    public void startLevel()
    {
        timer = new Timer(timerSpeed, timerListener);
        timer.start();
        frame.repaint();
        frame.add(this); 
        
        //system for determining start/end Y value based on difficulty
        gameGrid = new Grid(rightBound, bottomBound, startY, endY,level);
        gameGrid.startLevel();
        hud = new HUD(rightBound,bottomBound,level,score,playerName);//level and score need to change with level
        hud.startTimer();
         
    }
    
    /*
    * Checks to see if user has won the game and allows user to continue to next level.
    * Otherwise, it allows the user to save and restart.
    */
    public void endLevel()
    {
        
        //prompt save
        
        if(gameGrid.hasWonLevel())//level won
        {
            level++;//increase level
            eventTime = 900 / ((1+level)/2);
            //increase score
            startY--;//needs to be changed
            endY--;//needs to be changed
            score+=500*level;//needs to be changed
            score+=gameGrid.getAddedScore();//add points for removing rows
            score-=((int)(hud.getElapsedTime()*10));
            if(score<0)
            {
                score = 0;
            }

            SaveLoad.setProfileData(playerName, slot, score + "%%" + level + "%%" + highscore + "%%" + startY + "%%" + endY);
            startLevel();//level and score need to change with level,change background, calls for new level
            
        }
        else//level lost
        {
            //prompt save, etc.
            SaveLoad.saveGlobalHighscore(playerName, score);
            level = 1;
            if(score>highscore)
            { highscore = score+1-1; }
            score = 0;
            eventTime = 900 / ((1+level)/2);
            startY = 18;
            endY = 18;
            SaveLoad.setProfileData(playerName, slot, score + "%%" + level + "%%" + highscore + "%%" + startY + "%%" + endY);
            startLevel();
        }
        
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
    
    /*
    * Updates the time and checks to see if the game is over (either won or lost).
    * If the game is over, it ends the level. Otherwise, it moves the grid down and the game continues.
    */
    public void update()
    {
       if(gameGrid.hasWon())
       {
           hud.stopTimer();
           hud.setCharacterPhase();
       }
       updateTime += eventTime;
       if(gameGrid.isDead()||gameGrid.hasWonLevel())
       {
           endLevel();
       }
       else
       {
           gameGrid.moveDown();
       }
       
    }
    
    /*
    * @param g the <code>Graphics</code> to paint to
    */
    @Override
    public void paint(Graphics g)
    {
            
            //int boardSizeY = this.getHeight();
            //int boardSizeX = (int)(boardSizeY/(float)(bottomBound-2))*(rightBound);
            
            //float ratio = (float)boardSizeY/(float)boardSizeX;
            
            
            
            
           
            //System.out.println((float)boardSizeY/(float)boardSizeX);
            if(back==null)
            {
                back=(BufferedImage)createImage(1920,1070);
            }
            Graphics2D twoDGraph = (Graphics2D)g;
            Graphics graphToBack= back.createGraphics();
           
            
            

            gameGrid.draw(graphToBack,1920,1070,700);
            hud.drawHUD(graphToBack,1920,1070,700);
            
            //check if character is active or not
            //if active, then enable movement and updating graphics
            graphToBack.setColor(Color.YELLOW);
            
            
            twoDGraph.drawImage(back,0,0,frame.getWidth(),frame.getHeight(),null); 
            
            if(System.currentTimeMillis()-updateTime >= eventTime)
            {
                update();
            }
            
            repaint();
      
        
    }
   
    /*
    * @param ke
    */
    @Override
    public void keyTyped(KeyEvent ke)
    {
       
    }
    
    /*
    * @param ke
    */
    @Override
    public void keyPressed(KeyEvent ke)
    {
        
        //put in if statement for character.
        if (ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S)
	    {
            updateTime -= (eventTime)/3;
	    }
    }

    /*
    * @param ke
    */
    @Override
    public void keyReleased(KeyEvent ke)
    {
        if(!gameGrid.hasWon())
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
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
            {
                gameGrid.drop();
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
            int x = gameGrid.getCharacter().getX();
            int y = gameGrid.getCharacter().getY();
            if (ke.getKeyCode() == KeyEvent.VK_UP)
            {
                gameGrid.moveCharacterUp(x,y);
            }
            if (ke.getKeyCode() == KeyEvent.VK_DOWN)
            {
                gameGrid.moveCharacterDown(x,y);
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                gameGrid.moveCharacterRight(x,y);
            }
            if (ke.getKeyCode() == KeyEvent.VK_LEFT)
            {
                gameGrid.moveCharacterLeft(x,y);
            }

            switch(toUpperCase(ke.getKeyChar()))
                {
                    case KeyEvent.VK_W : gameGrid.moveCharacterUp(x,y); break;
                    case KeyEvent.VK_A : gameGrid.moveCharacterLeft(x,y); break;
                    case KeyEvent.VK_D : gameGrid.moveCharacterRight(x,y); break;
                    case KeyEvent.VK_S : gameGrid.moveCharacterDown(x,y); break; 
            

                }
            }
        }
    }
