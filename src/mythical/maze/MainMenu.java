/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author 100032528
 */
public class MainMenu extends JPanel implements KeyListener {
    private static JFrame frame = new JFrame();
    JPanel thisPanel;
    
 
    private JButton play;
    private JButton tutorial;
    private JButton credits;
    private JButton highScore;
    private JButton exit;
    
   
    Color blackStartFilter = new Color(0.0f,0.0f,0.0f,1.0f);
  
    double draws = 0;
    
    double mythicPos = 0;
    double mazePos = 0;
    
    public void start()
    {
        thisPanel = this;
        this.removeAll();
        frame = new JFrame();
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        frame.setSize((int)width, (int)height);
        
        frame.setTitle("Mythical Maze");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
      
        
        //addButtons();
       
        frame.getContentPane().add(this);
        frame.repaint();
        frame.setVisible(true);
         
        frame.repaint();
        
        BackgroundMusic b = new BackgroundMusic(1);
        
        frame.addKeyListener(this);
  
    }
   
    
    
    public void addButtons()
    {
    
        UIManager.put("OptionPane.messageFont", new FontUIResource(
                new Font("Monaco", Font.BOLD, 14)));
        Font font = new Font("Algerian", Font.BOLD, 52);
        Color buttonColor = Color.RED;
        
        play = new JButton("Play");
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setContentAreaFilled(false);
        play.setHorizontalTextPosition(JButton.CENTER);
        play.setVerticalTextPosition(JButton.CENTER);
        play.setFont(font);
        play.setForeground(buttonColor);
        final JPanel p = this;
        play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            GameRunner g = new GameRunner();
                            frame.remove(thisPanel);
                            g.start();
                            frame.dispose();
			}
		});
                
                
        tutorial = new JButton("Tutorial");
        tutorial.setBorder(BorderFactory.createEmptyBorder());
        tutorial.setContentAreaFilled(false);
        tutorial.setHorizontalTextPosition(JButton.CENTER);
        tutorial.setVerticalTextPosition(JButton.CENTER);
        tutorial.setFont(font);
        tutorial.setForeground(buttonColor);
        tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "In Dev","Credits",JOptionPane.PLAIN_MESSAGE);
			}
		});
                
       highScore = new JButton("High Scores");
       highScore.setBorder(BorderFactory.createEmptyBorder());
       highScore.setContentAreaFilled(false);
       highScore.setHorizontalTextPosition(JButton.CENTER);
       highScore.setVerticalTextPosition(JButton.CENTER);
       highScore.setFont(font);
       highScore.setForeground(buttonColor);
       highScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "In Dev","Credits",JOptionPane.PLAIN_MESSAGE);
			}
		});
                
                
       credits = new JButton("Credits");
       credits.setBorder(BorderFactory.createEmptyBorder());
       credits.setContentAreaFilled(false);
       credits.setHorizontalTextPosition(JButton.CENTER);
       credits.setVerticalTextPosition(JButton.CENTER);
       credits.setFont(font);
       credits.setForeground(buttonColor);
       credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                             JOptionPane.showMessageDialog(null, "                                                Credits\n"
                                     + "                                Developed By The Techs\n"
                                     + "Project Manager:                Justin Hill\n"
                                     + "Lead Developer:                 Richard Dong\n"
                                     + "Richard Wu:                         Graphics Manager\n"
                                     + "Abhijeet Venkataraman:     Documentation Manager\n\n"
                                     + "               Contracted By Congative Though Media\n\n"
                                     + "            Developed Using\n"
                                     + "Netbeans:                             Development Enviroment\n"
                                     + "Photoshop:                           Graphics Production\n"
                                     + "Paint Dot Net:                       Graphics Producation\n"
                                     + "BFXR Studios:                      Sound Production\n"
                                     + "BitBucket:                             Group Code Collaberation\n"
                                     + "Google Drive:                       Group Documentation Collaberation\n","Credits",JOptionPane.PLAIN_MESSAGE);
			}
		});
                
                
        exit = new JButton("Exit");
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setContentAreaFilled(false);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);
        exit.setFont(font);
        exit.setForeground(buttonColor);
        exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                               System.exit(1);
			}
		});
                        
                
        
        play.setBounds(frame.getWidth()/4, frame.getHeight()/4, frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(play);
        
        tutorial.setBounds(frame.getWidth()/4, frame.getHeight()/4+(3*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.add(tutorial);
        
        highScore.setBounds(frame.getWidth()/4, frame.getHeight()/4+(4*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.add(highScore);
        
        credits.setBounds(frame.getWidth()/4, frame.getHeight()/4+(5*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.add(credits);
        
        exit.setBounds((int)(frame.getWidth()/4), frame.getHeight()/4+(6*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.add(exit); 
        
  
    }
    

    @Override
    public void paintComponent(Graphics g)
    {
        try {
            g.setColor(new Color(0,0,0));
            
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.drawImage(ImageManager.getImage(10),0,0, this.getWidth(), this.getHeight(), this);
           
 
          
            
            g.setColor(blackStartFilter);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            if(draws<100)
           {
               g.setFont(new Font("Arial", Font.PLAIN, 50));
                g.setColor(new Color(255,144,0));
               
                g.drawImage(ImageManager.getImage(2), this.getWidth()/4, this.getHeight()/6, this.getWidth()/2, this.getWidth()/10, null);
                g.drawImage(ImageManager.getImage(3), this.getWidth()/4, this.getHeight()/2, this.getWidth()/2, this.getWidth()/10, null);
                
           }
            else if(draws<200)
           {
               g.setFont(new Font("Arial", Font.PLAIN, 50));
                g.setColor(new Color(255,144,0));
               
                g.drawImage(ImageManager.getImage(1), this.getWidth()/4, this.getHeight()/6, this.getWidth()/2, this.getWidth()/10, null);
                g.drawImage(ImageManager.getImage(0), this.getWidth()/3, (int)(this.getHeight()/2.5f), this.getWidth()/3, (int)(((this.getWidth()/3.0f)/800.0f)*600.0f), null);
           }
            if(draws>200&&draws<1000&&(blackStartFilter.getAlpha()/255.0f)-.002f>0)
            {
               
                blackStartFilter = new Color(0.0f,0.0f,0.0f,(blackStartFilter.getAlpha()/255.0f)-.002f);
            }
            if((blackStartFilter.getAlpha()/255.0f)-.002f<=.6f)
            {
                g.setFont(new Font("Arial", Font.PLAIN, 50));
                g.setColor(new Color(100,0,0));
               
                g.drawImage(ImageManager.getImage(6) , this.getWidth()/4 + (int)mythicPos, this.getHeight()/20, this.getWidth()/3, this.getWidth()/10, null);
                g.drawImage(ImageManager.getImage(5), this.getWidth()/4 + this.getWidth()/3 + (int)mazePos, this.getHeight()/20, this.getWidth()/4, this.getWidth()/10, null);
                
                
            
                if(Math.random()*(2400-(draws%2400))<7)
                {
                    if(Math.random()<.333f)
                g.drawImage(ImageManager.getImage(7), (int)(this.getWidth()/1.1), (int)(this.getHeight()/1.3), this.getWidth()/20, this.getWidth()/20, null);
                    else if(Math.random() < .5f)
                g.drawImage(ImageManager.getImage(7), (int)(this.getWidth()/3), (int)(this.getHeight()/1.5), this.getWidth()/20, this.getWidth()/20, null);
                    else
                       g.drawImage(ImageManager.getImage(7), (int)(this.getWidth()/8), (int)(this.getHeight()/1.2), this.getWidth()/20, this.getWidth()/20, null); 
                }
                
                if(this.getComponentCount()==0)
                addButtons();
                
                
                
                if(draws%400 <= 100)
                {
                    mythicPos+=this.getWidth()/350.0f;
                    mazePos-=this.getWidth()/300.0f;
                }
                if(draws%400 >= 200 && draws%400 < 300)
                {
                    mythicPos-=this.getWidth()/350.0f;
                    mazePos+=this.getWidth()/300.0f;
                }
                
            }
            
            Thread.sleep(20);
            draws++;
            repaint();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    @Override
    public void keyTyped(KeyEvent e) {
        draws = 302;
        blackStartFilter = new Color(0.0f,0.0f,0.0f,0.4f);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
   
    
  
   
 

}
