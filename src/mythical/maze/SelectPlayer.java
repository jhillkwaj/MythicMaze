/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author justi_000
 */
public class SelectPlayer extends JPanel {
    
    public JFrameExitOnFocussLost frame;
    
    public SelectPlayer()
    {
        Font font = new Font("Arial", Font.PLAIN, 12);
        frame = new JFrameExitOnFocussLost();
        frame.setSize(400, 120);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);
        frame.setTitle("Select User");
        frame.setResizable(false);
        frame.getContentPane().add(this);
        frame.repaint();
        frame.setVisible(true);
        this.setLayout(null);
        ImageIcon image = new ImageIcon(ImageManager.getImage(9).getScaledInstance(100, 100, 0));
        
        String[] playerNames = SaveLoad.getProfiles();
        if(playerNames==null)
        { playerNames = new String[4]; }
        if(playerNames.length<4)
        { 
            String[] temp = playerNames;
            playerNames = new String[4];
            for(int i = 0; i < temp.length; i++)
            {
                playerNames[i] = temp[i];
            }
        }
        for(int i = 0; i < playerNames.length; i++)
        {
             if(playerNames[i]==null||playerNames[i].equals(""))
             {
                 playerNames[i] = "New Player";
             }
        }
        final String[] names = playerNames;
        
        
        JButton one = new JButton(image);
        one.setBorderPainted(false);
        one.setContentAreaFilled(false);
        one.setHorizontalTextPosition(SwingConstants.CENTER);
        one.setText("<html>" + getText(playerNames[0],0) + "</html>");
        one.setFont(font);
        one.setForeground(Color.black);
        one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            startGame(names[0],0);
                            frame.dispose();
                            
			}
		});
        one.setBounds(0, 0, 100, 100);
        this.add(one);
        
        JButton two = new JButton(image);
        two.setBorderPainted(false);
        two.setContentAreaFilled(false);
        two.setHorizontalTextPosition(SwingConstants.CENTER);
        two.setText("<html>" + getText(playerNames[1],1) + "</html>");
        two.setFont(font);
        two.setForeground(Color.black);
        two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            startGame(names[1],1);
                            frame.dispose();
                            
			}
		});
        two.setBounds(100, 0, 100, 100);
        this.add(two);
        
        JButton three = new JButton(image);
        three.setBorderPainted(false);
        three.setContentAreaFilled(false);
        three.setHorizontalTextPosition(SwingConstants.CENTER);
        three.setText("<html>" + getText(playerNames[2],2) + "</html>");
        three.setFont(font);
        three.setForeground(Color.black);
        three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            startGame(names[2],2);
                            frame.dispose();
                            
			}
		});
        three.setBounds(200, 0, 100, 100);
        this.add(three);
        
        JButton four = new JButton(image);
        four.setBorderPainted(false);
        four.setContentAreaFilled(false);
        four.setHorizontalTextPosition(SwingConstants.CENTER);
        four.setText("<html>" + getText(playerNames[3],3) + "</html>");
        four.setFont(font);
        four.setForeground(Color.black);
        four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            startGame(names[3],3);
                            frame.dispose();
                            
			}
		});
        four.setBounds(300, 0, 100, 100);
        this.add(four);
        
        
    }
    
    public String getText(String name, int slot)
    {
        if(name.equals("New Player"))
        {
            return name;
        }
        String data = SaveLoad.getProfileData(name, slot);
        String[] dataParts = data.split("%%");
        if(dataParts!=null&&dataParts.length>3)
        {
            return name + "<br>"+ dataParts[1]+"<br>" + dataParts[2];
        }
        return name;
    }
    
    private void startGame(String playerName, int slot)
    {
        GameRunner g = new GameRunner();
        if(!playerName.equals("New Player"))
        {
            String[] data = SaveLoad.getProfileData(playerName, slot).split("%%");
            if(data!=null&&data.length>3)
            {
                g.start(SaveLoad.getProfileData(playerName, slot).split("%%"), playerName);
            }
            else
                g.start(playerName);
        }
        else
        {
            String name = JOptionPane.showInputDialog(frame, "What was the name of that boy that is lost?");
            while(name==null||name.length()<1||name.contains("%"))
            {
                name = JOptionPane.showInputDialog(frame, "No. That's not it. What was his name?");
            }
            SaveLoad.saveNewProfile(name);
            g.start(playerName);
        }
    }
    
    


    public static class JFrameExitOnFocussLost extends JFrame{
        public void focusLost(FocusEvent fe){
          this.dispose();
        }
    }
    


}
