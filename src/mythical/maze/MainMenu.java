package mythical.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Initiates a main menu in which the user can navigate around to find the 
 * game, log in, create new profiles, play tutorials, or find statistics.
 * @author Justin Hill
 */
public class MainMenu extends JPanel implements KeyListener 
{
    private static JFrame menuFrame = new JFrame();
    private JButton play, tutorial, credits, highScore, exit;  
    private Color blackStartFilter = new Color(0.0f,0.0f,0.0f,1.0f);
    private double draws = 0;
    private double mythicPos = 0;
    private double mazePos = 0;
    private long startTime;
    private int lastDraw = 0;
    
    private ArrayList<Shape> shapes = new ArrayList<>();
    
    /**
     * Creates the frame with correct dimensions, starts music and button controls.
     */
    public void start()
    {
        try
        {
            EventLogger.setupEvent();//set up logs
            ErrorLogger.setupError();
            CrashLogger.setupCrash();
            ImageManager.importAll();//import all images
            this.removeAll();//remove anything from frame, clean slate.
            menuFrame = new JFrame();
            //set correct dimmensions.
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double menuWidth = screenSize.getWidth();
            double menuHeight = screenSize.getHeight();
            menuFrame.setSize((int)menuWidth, (int)menuHeight);
            //set title, exit and size buttons.
            menuFrame.setTitle("Mythical Maze");
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
            //start painting screen onto frame.
            menuFrame.getContentPane().add(this);
            startTime = System.currentTimeMillis();//have default start time for comparison
            menuFrame.setVisible(true);
            BackgroundMusic.play("Race_Car_Music");//start music
            menuFrame.addKeyListener(this);//enable controls
            EventLogger.logEvent("Game menu load successful");//log menu load
            menuFrame.repaint();//start updatin graphics
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not start game menu",ex);
        }
    }
   
    /**
     * Adds user navigation buttons, such as play, tutorial, high scores, exit, etc.
     */
    public void addButtons()
    {
        try
        {
            //set fonts, size, color
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Monaco", Font.BOLD, 14)));
            Font font = new Font("WLM Carton", Font.PLAIN, 52);
            Font fontSmall = new Font("WLM Carton", Font.PLAIN, 42);
            Color buttonColor = Color.black;
            //create first play button
            play = new JButton("Play");
            play.setBorder(BorderFactory.createEmptyBorder());
            play.setContentAreaFilled(false);
            play.setHorizontalTextPosition(JButton.CENTER);
            play.setVerticalTextPosition(JButton.CENTER);
            play.setFont(font);
            play.setForeground(buttonColor);
            final JPanel p = this;
            //what happens when button is clicked.
            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    SoundFX.playFX("Select");
                    SelectPlayer p = new SelectPlayer();//opens select player menu
                }
            });
            //add tutorial button        
            tutorial = new JButton("Tutorial");
            tutorial.setBorder(BorderFactory.createEmptyBorder());
            tutorial.setContentAreaFilled(false);
            tutorial.setHorizontalTextPosition(JButton.CENTER);
            tutorial.setVerticalTextPosition(JButton.CENTER);
            tutorial.setFont(font);
            tutorial.setForeground(buttonColor);
            tutorial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    SoundFX.playFX("Select");
                    GameRunner g = new GameRunner();
                    g.start("Tutorial", -1);
                }
            });
            //add high scores button        
            highScore = new JButton("High Scores");
            highScore.setBorder(BorderFactory.createEmptyBorder());
            highScore.setContentAreaFilled(false);
            highScore.setHorizontalTextPosition(JButton.CENTER);
            highScore.setVerticalTextPosition(JButton.CENTER);
            highScore.setFont(fontSmall);
            highScore.setForeground(buttonColor);
            highScore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    SoundFX.playFX("Select");
                    TreeMap<Integer, String> t = SaveLoad.getHighScores();//calls to get high scores to display.
                    String scores = "";
                    for(int i = 0; i < 4; i++)
                    {
                        scores += t.lastEntry().getValue();
                        scores += " " + t.lastKey() + "\n";
                        t.pollLastEntry();
                    }
                    JOptionPane.showMessageDialog(null,scores, "Highscores" ,JOptionPane.PLAIN_MESSAGE);
                }
            });

           //create credits button.         
           credits = new JButton("Credits");
           credits.setBorder(BorderFactory.createEmptyBorder());
           credits.setContentAreaFilled(false);
           credits.setHorizontalTextPosition(JButton.CENTER);
           credits.setVerticalTextPosition(JButton.CENTER);
           credits.setFont(font);
           credits.setForeground(buttonColor);
           credits.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                SoundFX.playFX("Select");
                                 JOptionPane.showMessageDialog(null, "                                                Credits\n"
                                         + "                                Developed By The Techs\n"
                                         + "Project Manager:                Justin Hill\n"
                                         + "Lead Developer:                 Richard Dong\n"
                                         + "Graphics Manager:             Richard Wu\n"
                                         + "Documentation Manager:  Abhijeet Venkataraman:\n\n"
                                         + "               Contracted By Congative Though Media\n\n"
                                         + "                                       Developed Using\n"
                                         + "Netbeans:                             Development Enviroment\n"
                                         + "Photoshop:                           Graphics Production\n"
                                         + "Paint Dot Net:                       Graphics Producation\n"
                                         + "BFXR Studios:                      Sound Production\n"
                                         + "BitBucket:                             Group Code Collaberation\n"
                                         + "Google Drive:                       Group Documentation Collaberation\n","Credits",JOptionPane.PLAIN_MESSAGE);
                            }
                    });

            //create exit button.       
            exit = new JButton("Exit");
            exit.setBorder(BorderFactory.createEmptyBorder());
            exit.setContentAreaFilled(false);
            exit.setHorizontalTextPosition(JButton.CENTER);
            exit.setVerticalTextPosition(JButton.CENTER);
            exit.setFont(font);
            exit.setForeground(buttonColor);
            exit.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    SoundFX.playFX("Select");
                    System.exit(1);
                }
            });
            //set positions and size of buttons, which change with screen size; add to screen. 
            play.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4, menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.setLayout(null);
            this.add(play);
            tutorial.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4+(3*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(tutorial);
            highScore.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4+(5*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(highScore);
            credits.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4+(7*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(credits);
            exit.setBounds((int)(menuFrame.getWidth()/4), menuFrame.getHeight()/4+(9*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(exit);   
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create menu buttons",ex);
        }   
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        draws = (int)(40*(System.currentTimeMillis()-startTime)/1000.0);
        while(lastDraw<draws)
        {
            lastDraw++;
            try 
            {
                //fills a black background with background image
                g.setColor(new Color(0,0,0));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                switch (((int)(lastDraw-200)/300)%7)
                {
                    case 0:
                        g.drawImage(ImageManager.getImage(16),0,0, this.getWidth(), this.getHeight(), this);
                    break;

                    case 1:
                        g.drawImage(ImageManager.getImage(21),0,0, this.getWidth(), this.getHeight(), this);
                    break;

                    case 2:
                       g.drawImage(ImageManager.getImage(20),0,0, this.getWidth(), this.getHeight(), this);
                    break;

                    case 3:
                        g.drawImage(ImageManager.getImage(15),0,0, this.getWidth(), this.getHeight(), this);
                    break;

                    case 4:
                        g.drawImage(ImageManager.getImage(14),0,0, this.getWidth(), this.getHeight(), this);
                    break;

                    case 5:
                        g.drawImage(ImageManager.getImage(19),0,0, this.getWidth(), this.getHeight(), this);
                        break;  
                    case 6:
                        g.drawImage(ImageManager.getImage(52),0,0, this.getWidth(), this.getHeight(), this);
                    break;   
                }
                if(lastDraw>300&&(int)(lastDraw-200)%300>270)
                {
                    blackStartFilter = new Color(0.0f,0.0f,0.0f,(blackStartFilter.getAlpha()/255.0f)+.02f);
                }
                else if(lastDraw>300&&(int)(lastDraw-200)%300<29)
                {
                    if((blackStartFilter.getAlpha()/255.0f)-.02f>0)
                    {
                        blackStartFilter = new Color(0.0f,0.0f,0.0f,(blackStartFilter.getAlpha()/255.0f)-.02f);
                    }

                    else
                    {
                        blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
                    }            
                }                   
                //fade in main menu elements
                g.setColor(blackStartFilter);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                if(lastDraw<100)
                {
                    g.setFont(new Font("WLM Carton", Font.PLAIN, 50));
                    g.setColor(new Color(255,144,0));
                    g.drawImage(ImageManager.getImage(2), this.getWidth()/4, this.getHeight()/6, this.getWidth()/2, this.getWidth()/10, null);
                    g.drawImage(ImageManager.getImage(3), this.getWidth()/4, this.getHeight()/2, this.getWidth()/2, this.getWidth()/10, null);

                }
                else if(lastDraw<200)//continuing fade in...
                {
                    g.setFont(new Font("WLM Carton", Font.PLAIN, 50));
                    g.setColor(new Color(255,144,0));
                    g.drawImage(ImageManager.getImage(1), this.getWidth()/4, this.getHeight()/6, this.getWidth()/2, this.getWidth()/10, null);
                    g.drawImage(ImageManager.getImage(0), this.getWidth()/3, (int)(this.getHeight()/2.5f), this.getWidth()/3, (int)(((this.getWidth()/3.0f)/800.0f)*600.0f), null);
                }
                if(lastDraw>200&&lastDraw<1000&&(blackStartFilter.getAlpha()/255.0f)-.002f>0)//complete fade in at end
                {
                    blackStartFilter = new Color(0.0f,0.0f,0.0f,(blackStartFilter.getAlpha()/255.0f)-.002f);
                }
                //draw some pieces
                for(Shape s : shapes)
                {
                    s.drawShape(g, this.getWidth(), this.getHeight(), 0, 0);
                }
                if((blackStartFilter.getAlpha()/255.0f)-.002f<=.6f)//animation for title
                {
                    //set font and color, get images
                    g.setFont(new Font("WLM Carton", Font.PLAIN, 50));
                    g.setColor(new Color(100,0,0));
                    g.drawImage(ImageManager.getImage(6) , this.getWidth()/4 + (int)mythicPos, this.getHeight()/20, this.getWidth()/3, this.getWidth()/10, null);
                    g.drawImage(ImageManager.getImage(5), this.getWidth()/4 + this.getWidth()/3 + (int)mazePos, this.getHeight()/20, this.getWidth()/4, this.getWidth()/10, null);
                    if(this.getComponentCount()==0)//add to blank menu
                    {
                        addButtons();
                    }
                    if(lastDraw%400 < 100)//move around title in pattern back and forth
                    {
                        mythicPos+=this.getWidth()/350.0f;
                        mazePos-=this.getWidth()/300.0f;
                    }
                    else if(lastDraw%400 >= 200 && lastDraw%400 < 300)
                    {
                        mythicPos-=this.getWidth()/350.0f;
                        mazePos+=this.getWidth()/300.0f;
                    }
                    g.drawImage(ImageManager.getImage(22), (menuFrame.getWidth()/2)-140, menuFrame.getHeight()/4, 280,97+2* menuFrame.getHeight()/16, this);
                    g.drawImage(ImageManager.getImage(22), (menuFrame.getWidth()/2)-140+280, menuFrame.getHeight()/4+(3*(menuFrame.getHeight()/16)), -280,97+2* menuFrame.getHeight()/16, this);
                    g.drawImage(ImageManager.getImage(22), (menuFrame.getWidth()/2)-140+280, menuFrame.getHeight()/4+(5*(menuFrame.getHeight()/16)), -280,97+2* menuFrame.getHeight()/16, this);
                    g.drawImage(ImageManager.getImage(22), (menuFrame.getWidth()/2)-140, menuFrame.getHeight()/4+(7*(menuFrame.getHeight()/16)), 280,97+2* menuFrame.getHeight()/16, this);
                    g.drawImage(ImageManager.getImage(22), (menuFrame.getWidth()/2)-140+280, menuFrame.getHeight()/4+(9*(menuFrame.getHeight()/16)), -280,97+2* menuFrame.getHeight()/16, this);
                }
            } 
            catch(Exception ex)
            {
                ErrorLogger.logRuntimeError("Unknown exception in displaying menu",ex);
            }
        }
        repaint();//refresh screen
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        draws = 300;//skip past main menu introduction.
        lastDraw = 300;
        blackStartFilter = new Color(0.0f,0.0f,0.0f,0.4f);//fade right into menu
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
    /**
     * Closes the menu.
     */
    public static void closeMenu()
    {
        try
        {   
            menuFrame.dispose();
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not dispose frame",ex);
        }
    }
}
