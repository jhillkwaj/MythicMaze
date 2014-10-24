/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author 100032528
 */
public class MainMenu extends JPanel {
    private static JFrame frame = new JFrame();
    JPanel thisPanel;
    
 
    private JButton play;
    private JButton tutorial;
    private JButton credits;
    private JButton highScore;
    private JButton exit;
    
   
    Color blackStartFilter = new Color(0.0f,0.0f,0.0f,1.0f);
  
    double draws = 0;
    
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
  
    }
   
    
    
    public void addButtons()
    {
    
        
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
                            g.start(frame);
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
                             JOptionPane.showMessageDialog(null, "In Dev","Credits",JOptionPane.PLAIN_MESSAGE);
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
            
           // System.out.println(this.getComponentCount());
            g.setColor(new Color(0,0,0));
            
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.drawImage(ImageManager.getImage(10),0,0, this.getWidth(), this.getHeight(), this);
           
 
          
            
            g.setColor(blackStartFilter);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
           
            if(draws>200&&draws<1000&&(blackStartFilter.getAlpha()/255.0f)-.002f>0)
            {
               
                blackStartFilter = new Color(0.0f,0.0f,0.0f,(blackStartFilter.getAlpha()/255.0f)-.002f);
            }
            if((blackStartFilter.getAlpha()/255.0f)-.002f<=.6f&&this.getComponentCount()==0)
            {
                addButtons();
            }
            
            Thread.sleep(20);
            draws++;
            repaint();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }
   
    
  
   
 

}
