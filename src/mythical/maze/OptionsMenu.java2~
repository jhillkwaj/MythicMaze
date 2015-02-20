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
    
    private final AutoCloseFrame frame = new AutoCloseFrame(); 
    private GameRunner reference = null;//reference to main game frame, used for location, parentComponent
    
    /**
     * Creates an options menu, which contains several settings and options.
     * @param g a reference to the main game running class.
     */
    public OptionsMenu(GameRunner g)
    {
        reference = g;
        frame.setTitle("Options");//title of menu
        //calculate right size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        //size set
        this.setPreferredSize(new Dimension(200, 400));
        
        //location
        frame.setLocation((int)(width/2)-100,(int)(height/2)-200);
        
        //Restart Level
        JButton restart = new JButton();
        restart.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(confirmAction())
                {
                    reference.closeFrame();
                    reference.restartLevel();
                }
            }    
	});
        restart.setBounds(0, 200, 200, 100);
        restart.setIcon(new ImageIcon(ImageManager.getImage(65).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        this.setLayout(null);
        this.add(restart);
        
        //Mute Music
        JButton muteMusic = new JButton();
        muteMusic.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(Settings.musicOn)
                {
                    Settings.turnMusicOff();
                } 
                else//music is off
                {
                    Settings.turnMusicOn();
                } 
                OptionsMenu o = new OptionsMenu(reference);//relaunch options menu with change
            }    
	});
        muteMusic.setBounds(0, 0, 200, 100);
        //draw icons to indicate music status
        if(Settings.musicOn)
        {
             muteMusic.setIcon(new ImageIcon(ImageManager.getImage(60).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        }  
        else
        {
            muteMusic.setIcon(new ImageIcon(ImageManager.getImage(61).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        }
        this.add(muteMusic);
        
        //Must Sounds
        JButton muteSoundEffects = new JButton();
        muteSoundEffects.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(Settings.soundEffectsOn)
                {
                    Settings.turnSoundEffectsOff();
                }   
                else
                {
                    Settings.turnSoundEffectsOn();
                } 
                OptionsMenu o = new OptionsMenu(reference);//relaunch options menu
            }    
	});
        muteSoundEffects.setBounds(0, 100, 200, 100);
        if(Settings.soundEffectsOn)
        {
            muteSoundEffects.setIcon(new ImageIcon(ImageManager.getImage(62).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        }   
        else
        {
            muteSoundEffects.setIcon(new ImageIcon(ImageManager.getImage(63).getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH)));
        }
        this.add(muteSoundEffects);
                
        //Exit Level
        JButton mainMenu = new JButton();
        mainMenu.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(confirmAction())
                {
                    reference.closeFrame();//close game menu
                    MainMenu m = new MainMenu();//create new menu
                    m.start();
                }  
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
    
    /**
     * Creates a confirm dialog, which returns the user's response.
     * @return reply whether the user chose to confirm the preemptive action.
     */
    private boolean confirmAction()
    {
       int reply = JOptionPane.showConfirmDialog(null,"Are you sure?", "Confirm Exit",
               JOptionPane.YES_NO_OPTION);
        
        return reply == JOptionPane.YES_OPTION;//return if confirmed
   }
}
