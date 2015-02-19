/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Pulls up a menu for user to change settings.
 * @author Justin Hill
 */
public class OptionsMenu extends JPanel {
    
    private AutoCloseFrame frame = new AutoCloseFrame(); 
    private GameRunner reference = null;
    
    public OptionsMenu(GameRunner g)
    {
        reference = g;
        //name
        frame.setTitle("Options");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        //size
        this.setPreferredSize(new Dimension(200, 400));
        
        //location
        frame.setLocation((int)(width/2)-100,(int)(height/2)-200);
        
        //Restart Level
        JButton restrt = new JButton();
        restrt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    reference.closeFrame();
			reference.restartLevel();
		}    
	});
        restrt.setBounds(0, 0, 200, 100);
        restrt.setIcon(new ImageIcon(ImageManager.getImage(65).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        this.setLayout(null);
        this.add(restrt);
        
        //Must Music
        JButton muteMusic = new JButton();
        muteMusic.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    if(Settings.musicOn)
			Settings.turnMusicOff();
                    else
                        Settings.turnMusicOn();
                    OptionsMenu o = new OptionsMenu(reference);
		}    
	});
        muteMusic.setBounds(0, 100, 200, 100);
        if(Settings.musicOn)
            muteMusic.setIcon(new ImageIcon(ImageManager.getImage(60).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        else
            muteMusic.setIcon(new ImageIcon(ImageManager.getImage(61).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        this.add(muteMusic);
        
        //Must Sounds
        JButton muteSounds = new JButton();
        muteSounds.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    if(Settings.soundEffectsOn)
			Settings.turnSoundEffectsOff();
                    else
                        Settings.turnSoundEffectsOn();
                    OptionsMenu o = new OptionsMenu(reference);
		}    
	});
        muteSounds.setBounds(0, 200, 200, 100);
        if(Settings.soundEffectsOn)
            muteSounds.setIcon(new ImageIcon(ImageManager.getImage(62).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        else
            muteSounds.setIcon(new ImageIcon(ImageManager.getImage(63).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        this.add(muteSounds);
        
        
        
        //Exit Level
        JButton mainMenu = new JButton();
        mainMenu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			reference.closeFrame();
                        MainMenu m = new MainMenu();
                        m.start();
		}    
	});
        mainMenu.setBounds(0, 300, 200, 100);
        mainMenu.setIcon(new ImageIcon(ImageManager.getImage(64).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        this.add(mainMenu);
        
        
        
       frame.setAlwaysOnTop(true); 
        frame.add(this);
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
    }
    
    private boolean confirm(String message)
    {
       int reply = JOptionPane.showConfirmDialog(null, message, "Confirm Exit",
               JOptionPane.YES_NO_OPTION);
        
       if (reply == JOptionPane.YES_OPTION) {
          return true;
        }
        else {
           return false;
        }
   }
    
}
