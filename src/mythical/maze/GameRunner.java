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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

    
/**
 * Class that runs the game, including starting and ending the game
 * @author Justin Hill and Richard Dong
 */
public class GameRunner extends JPanel implements KeyListener {
    
    private JFrame frame;//frame for game
    private boolean[] keys = new boolean[10];//represents which keys are pressed
    private static Timer timer;//times the refresh rate
    private final int timerSpeed = 60;//refresh rate
    private long startTime;
    private long updateTime = 0;
    private int eventTime = 900;
    
    private Color blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
    private BufferedImage back;//backdrop where images are drawn
   

    private final int rightBound = 11;//boundaries of grid
    private final int bottomBound = 21;
    
    private int startY, endY,level,score;//variables that change with level
    
    //items in the screen
    private Grid gameGrid;//grid
    private HUD hud;//heads up display for statistics and buttons

    private String playerName;
    private int slot;
    private int highscore;
   
    //Due to the length of the class, methods in this class are organized
    //into the following categories from top to bottom: graphics, starting the game,
    //the game itself, ending the game, and listener controls.
    
    //Methods below are for graphics
        
   /**
    * Creates the canvas; calls out to object classes to paint grid, blocks, 
    * heads up display, character, and much more.
    * @param g the <code>Graphics</code> to paint onto
    * @see Graphics.
    */
    @Override
    public void paint(Graphics g)
    {
        if(back==null)//if canvas has not been created, create canvsas
        {
            back=(BufferedImage)createImage(1920,1070);
        }
        Graphics2D twoDGraph = (Graphics2D)g;
        Graphics graphToBack= back.createGraphics(); //prepares drawing onto bufferedimage graphics
        gameGrid.draw(graphToBack,1920,1070,700);//draws gamegrid, includes blocks
        hud.drawHUD(graphToBack,1920,1070,700);//draws heads up display
        twoDGraph.drawImage(back,0,0,frame.getWidth(),frame.getHeight(),null);//draws bufferedimage to frame
        if(System.currentTimeMillis()-updateTime >= eventTime)//updates based on refresh rate
        {
            update();
        }
        repaint();//redo again in loop
    }
   
    //Methods below deal with starting the game and levels
    
    /**
     * For returning players, the method sets variables to saved statistics and 
     * then moves to start creating a game frame.
     * @param data an array containing saved statistics
     * @param name the name of the player
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
    
    /**
     * Creates a frame for the game to be played in; calculates frame size, refresh rate
     * and adds listeners and starts the appropriate level.
     * @param name the player name
     * @see JFrame
     */
    public void start(String name)
    {
        this.frame = new JFrame();
        this.removeAll();//clears frame from menu or previous level
        //calculates and sets a refresh rate.
        startTime = System.currentTimeMillis();
        updateTime = startTime;
        
        //sets the screen size to full screen, resizable.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        frame.setSize((int)width, (int)height);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        //adds other micellaneous items, such as frame name, exit button, and listeners.
        frame.setTitle("Mythical Maze");
        frame.setLocation((int)(width/4), 0);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);

        //sets player name, starts level
        playerName = name;
        if(level==0||level==1)//if new player
        {
            level = 1;
            newLevel();
        }
        startLevel();//start level
    }
    
    /**
     * Starts a new level by creating the appropriate grid and heads up display.
     */
    public void startLevel()
    {
        //new timer for refresh rate
        ActionListener timerListener = new ActionListener() 
   	{
                @Override
   		public void actionPerformed(ActionEvent e){}
   	};
        timer = new Timer(timerSpeed, timerListener);
        
        timer.start();
        frame.repaint();
        frame.add(this); 
                
        //system for determining start/end Y value based on difficulty, higher 
        //values indicate harder levels as users have less space to create paths
        gameGrid = new Grid(rightBound, bottomBound, startY, endY,level);//new grid
        gameGrid.startLevel();
        hud = new HUD(rightBound,bottomBound,level,score,playerName);//new heads up display
        hud.startTimer();//start level timer, which determines end score.
    }
    
    /**
     * Creates a new level based on player level.
     */
    public void newLevel()
    {
        if(level % 2 == 1)
        {
            eventTime = (int)(900f / ((1+(level))/3.0f));
            startY = level+1;//add difficulty
            endY = level+1;
            score+=500*level;//scores are increased based on level beaten
            
        }
        else if(level == 2)
        {
            eventTime = (int)(900f / ((1+(level))/3.0f));
            startY = 3;
            endY = 2;
        }
        else if(level == 4)
        {
            eventTime = (int)(900f / ((1+3.5)/3.0f));
            startY = 4;
            endY = 3;
        }
        else if(level == 6)
        {
            eventTime = (int)(900f / ((1+3.5)/3.0f));
            startY = 1;
            endY = 5;
        }
        else if(level == 8)
        {
            eventTime = (int)(900f / ((1+4.5)/3.0f));
            startY = 6;
            endY = 8;
        }
        else if(level == 10)
        {
            eventTime = (int)(900f / ((1+5)/3.0f));
            startY = 1;
            endY = 11;
        }
        
        startY = 21 - startY;
        endY = 21 - endY;
            
    }
    
    //Methods below are for the logic during the gameplay itself.
    
    /**
     * Checks during the game whether the game has ended, either due to a 
     * loss or victory.
     */
    public void update()
    {
       if(gameGrid.hasWon())//user has created a successful path, can move character
       {
           hud.stopTimer();
           hud.setCharacterPhase();
       }
       updateTime += eventTime;
       if(gameGrid.isDead()||gameGrid.hasWonLevel())//level lost, calls to end level
       {
           endLevel();
       }
       else
       {
           gameGrid.moveDown();//game has not ended, blocks continue falling
       }
    }
    
    //Methods below are for ending levels and the game.
    
   /**
    * Checks to see if user has won the level and allows user to continue to next level.
    */
    public void endLevel()
    {
        if(gameGrid.hasWonLevel())//level won
        {
            level++;//increase level
            newLevel();//next level
            score+=gameGrid.getAddedScore();//add points for removing rows
            score-=((int)(hud.getElapsedTime()*10));//deduction for time consumed
            if(score<0)
            {
                score = 0;//if user took too much time, their score goes to 0.
            }
            SaveLoad.setProfileData(playerName, slot, score + "%%" + level + "%%" + highscore + "%%" + startY + "%%" + endY);
            startLevel();//level and score need to change with level,change background, calls for new level.
        }
        else//level lost
        {
            //prompt save, etc.
            SaveLoad.saveGlobalHighscore(playerName, score);
            level = 1;
            if(score>highscore)
            { 
                highscore = score+1-1; 
            }
            score = 0;
            eventTime = 900 / ((1+level)/2);
            startY = 18;
            endY = 18;
            SaveLoad.setProfileData(playerName, slot, score + "%%" + level + "%%" + highscore + "%%" + startY + "%%" + endY);//save data
            SoundFX.payFX("f");//play sound effect for losing
            try 
            {
                Thread.sleep(300);
            } 
            catch (InterruptedException ex) 
            {
                ErrorLogger.logIOError("Thread could not sleep after game lost.",ex);
            }
            startLevel();//restart
        }
    }
    
    
    //Methods below are for setting up listeners for gaming controls.
    
   /**
    * Unused keyListener method.
    * @param ke typed parameter
    */
    @Override
    public void keyTyped(KeyEvent ke){}
    
   /**
    * Updates the screen in the event that a key is pressed to move a block down.
    * @param ke typed parameter.
    */
    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S)
        {
            updateTime -= (eventTime)/3;
        }
    }

    //note that objects only move when released, as holding a key down causes 
    //multiple firings, while a key release can only occur once.
    
   /**
    * Causes an movement to blocks or the character when a key has been released.
    * @param ke the key that was released.
    */
    @Override
    public void keyReleased(KeyEvent ke)
    {
        if(!gameGrid.hasWon())//block phase
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
                gameGrid.drop();//move all the way down
            }
            //alternative WASD controls
            switch(toUpperCase(ke.getKeyChar()))
            {
                case KeyEvent.VK_W : gameGrid.rotateRight(); break; //clockwise
                case KeyEvent.VK_A : gameGrid.moveLeft(); break; //left
                case KeyEvent.VK_D : gameGrid.moveRight(); break; //right
                case KeyEvent.VK_R : gameGrid.rotateLeft(); break; //counterclockwise
            }
        }
        else//character phase
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
            //alternative WASD controls.
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
