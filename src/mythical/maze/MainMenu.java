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
    private static JFrame frame = new JFrame();
    private JPanel thisPanel;
    private JButton play, tutorial, credits, highScore, exit;  
    private Color blackStartFilter = new Color(0.0f,0.0f,0.0f,1.0f);
    private double draws = 0;
    private double mythicPos = 0;
    private double mazePos = 0;
    
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
            thisPanel = this;
            this.removeAll();//remove anything from frame, clean slate.
            frame = new JFrame();
            //set correct dimmensions.
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();
            frame.setSize((int)width, (int)height);
            //set title, exit and size buttons.
            frame.setTitle("Mythical Maze");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
            //start painting screen onto frame.
            frame.getContentPane().add(this);
            frame.repaint();
            frame.setVisible(true);
            frame.repaint();
            BackgroundMusic.play("Race_Car_Music");//start music
            frame.addKeyListener(this);//enable controls
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
                    SelectPlayer p = new SelectPlayer();
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
                    JOptionPane.showMessageDialog(null, "In Dev","Credits",JOptionPane.PLAIN_MESSAGE);
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
                    System.exit(1);
                }
            });
            //set positions and size of buttons, which change with screen size; add to screen. 
            play.setBounds(frame.getWidth()/4, frame.getHeight()/4, frame.getWidth()/2, frame.getHeight()/16);
            this.setLayout(null);
            this.add(play);
            tutorial.setBounds(frame.getWidth()/4, frame.getHeight()/4+(3*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
            this.add(tutorial);
            highScore.setBounds(frame.getWidth()/4, frame.getHeight()/4+(5*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
            this.add(highScore);
            credits.setBounds(frame.getWidth()/4, frame.getHeight()/4+(7*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
            this.add(credits);
            exit.setBounds((int)(frame.getWidth()/4), frame.getHeight()/4+(9*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
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
        try 
        {
            //fills a black background with background image
            g.setColor(new Color(0,0,0));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            switch (((int)(draws-200)/300)%6)
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
            }
            if(draws>300&&(int)(draws-200)%300>270)
            {
                blackStartFilter = new Color(0.0f,0.0f,0.0f,(blackStartFilter.getAlpha()/255.0f)+.02f);
            }
            else if(draws>300&&(int)(draws-200)%300<29)
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
            if(draws<100)
            {
                g.setFont(new Font("WLM Carton", Font.PLAIN, 50));
                g.setColor(new Color(255,144,0));
                g.drawImage(ImageManager.getImage(2), this.getWidth()/4, this.getHeight()/6, this.getWidth()/2, this.getWidth()/10, null);
                g.drawImage(ImageManager.getImage(3), this.getWidth()/4, this.getHeight()/2, this.getWidth()/2, this.getWidth()/10, null);

            }
            else if(draws<200)//continuing fade in...
            {
                g.setFont(new Font("WLM Carton", Font.PLAIN, 50));
                g.setColor(new Color(255,144,0));
                g.drawImage(ImageManager.getImage(1), this.getWidth()/4, this.getHeight()/6, this.getWidth()/2, this.getWidth()/10, null);
                g.drawImage(ImageManager.getImage(0), this.getWidth()/3, (int)(this.getHeight()/2.5f), this.getWidth()/3, (int)(((this.getWidth()/3.0f)/800.0f)*600.0f), null);
            }
            if(draws>200&&draws<1000&&(blackStartFilter.getAlpha()/255.0f)-.002f>0)//complete fade in at end
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
                if(draws%400 <= 100)//move around title in pattern back and forth
                {
                    mythicPos+=this.getWidth()/350.0f;
                    mazePos-=this.getWidth()/300.0f;
                }
                if(draws%400 >= 200 && draws%400 < 300)
                {
                    mythicPos-=this.getWidth()/350.0f;
                    mazePos+=this.getWidth()/300.0f;
                }
                g.drawImage(ImageManager.getImage(22), (frame.getWidth()/2)-140, frame.getHeight()/4, 280,97+2* frame.getHeight()/16, this);
                g.drawImage(ImageManager.getImage(22), (frame.getWidth()/2)-140+280, frame.getHeight()/4+(3*(frame.getHeight()/16)), -280,97+2* frame.getHeight()/16, this);
                g.drawImage(ImageManager.getImage(22), (frame.getWidth()/2)-140+280, frame.getHeight()/4+(5*(frame.getHeight()/16)), -280,97+2* frame.getHeight()/16, this);
                g.drawImage(ImageManager.getImage(22), (frame.getWidth()/2)-140, frame.getHeight()/4+(7*(frame.getHeight()/16)), 280,97+2* frame.getHeight()/16, this);
                g.drawImage(ImageManager.getImage(22), (frame.getWidth()/2)-140+280, frame.getHeight()/4+(9*(frame.getHeight()/16)), -280,97+2* frame.getHeight()/16, this);
            }
            
            Thread.sleep(20);//pause after moving
            draws++;//increase to further develop pattern
            repaint();//refresh screen
        } 
        catch (InterruptedException ex) 
        {
            ErrorLogger.logIOError("Interruption in displaying main menu",ex);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Unknown exception in displaying menu",ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        draws = 302;//skip past main menu introduction.
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
            frame.dispose();
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not dispose frame",ex);
        }
    }
}
