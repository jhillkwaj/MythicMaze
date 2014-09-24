/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author 100032528
 */
public class MainMenu extends JPanel {
    private static JFrame frame = new JFrame();
    private Image back;
    private Image playBack;
 
    private JButton play;
    private JButton tutorial;
    private JButton credits;
    private JButton highScore;
    private JButton exit;
    
   

  
    public void start()
    {
        this.removeAll();
        frame = new JFrame();
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        frame.setSize((int)width, (int)height);
        
        frame.setTitle("Mythic Maze");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        
        addButtons();
       
        frame.add(this);
        frame.setVisible(true);
         
        frame.repaint();
        
        
  
    }
   
    
    
    public void addButtons()
    {
    
        
        Font font = new Font("Algerian", Font.BOLD, 52);
        
        play = new JButton("Play");
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setContentAreaFilled(false);
        play.setHorizontalTextPosition(JButton.CENTER);
        play.setVerticalTextPosition(JButton.CENTER);
        play.setFont(font);
        play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            
			}
		});
                
                
        tutorial = new JButton("Tutorial");
        tutorial.setBorder(BorderFactory.createEmptyBorder());
        tutorial.setContentAreaFilled(false);
        tutorial.setHorizontalTextPosition(JButton.CENTER);
        tutorial.setVerticalTextPosition(JButton.CENTER);
        tutorial.setFont(font);
        tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
                
       highScore = new JButton("High Scores");
       highScore.setBorder(BorderFactory.createEmptyBorder());
       highScore.setContentAreaFilled(false);
       highScore.setHorizontalTextPosition(JButton.CENTER);
       highScore.setVerticalTextPosition(JButton.CENTER);
       highScore.setFont(font);
       highScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                                
			}
		});
                
                
       credits = new JButton("Credits");
       credits.setBorder(BorderFactory.createEmptyBorder());
       credits.setContentAreaFilled(false);
       credits.setHorizontalTextPosition(JButton.CENTER);
       credits.setVerticalTextPosition(JButton.CENTER);
       credits.setFont(font);
       credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
                
                
        exit = new JButton("Exit");
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setContentAreaFilled(false);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);
        exit.setFont(font);
        exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                               System.exit(1);
			}
		});
                        
                
        
        play.setBounds(frame.getWidth()/4, frame.getHeight()/4, frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(play);
        
        tutorial.setBounds(frame.getWidth()/4, frame.getHeight()/4+(3*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(tutorial);
        
        highScore.setBounds(frame.getWidth()/4, frame.getHeight()/4+(4*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(highScore);
        
        credits.setBounds(frame.getWidth()/4, frame.getHeight()/4+(5*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(credits);
        
        exit.setBounds((int)(frame.getWidth()/4), frame.getHeight()/4+(6*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(exit); 
        
  
    }
    

    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D)g; 
        
       
        try {
            Thread.sleep(30);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.repaint();
    }
   
    int[] xPoints = new int[50];
    int[] yPoints = new int[50];
    
  
   
 

}
