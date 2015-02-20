package mythical.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Creates a frame in which the user can select a player slot.
 * @author Justin Hill
 */
public class SelectPlayer extends JPanel
{
    public static AutoCloseFrame selectPlayerFrame = new AutoCloseFrame();//initiates variation on frame
    private String[] names, playerNames;
    private Font font;
    private ImageIcon backgroundImage;
    
    /**
     * Creates frame for selecting player slot.
     */
    public SelectPlayer()
    {
        try
        {
            selectPlayerFrame.dispose();
            font = new Font("Arial", Font.PLAIN, 12);//set font
            selectPlayerFrame.setSize(405, 130);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
            selectPlayerFrame.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);//set location to center
            selectPlayerFrame.setTitle("Select User");//title
            selectPlayerFrame.setResizable(false);
            selectPlayerFrame.getContentPane().add(this);
            selectPlayerFrame.repaint();//paints on info
            selectPlayerFrame.setVisible(true);
            this.setLayout(null);
            backgroundImage = new ImageIcon(ImageManager.getImage(66).getScaledInstance(100, 100, 0));//background added
            playerNames = SaveLoad.getProfiles();//retrieves slots from file
            if(playerNames==null)
            { 
                playerNames = new String[4]; //no profiles exist
            }
            if(playerNames.length<4)
            { 
                String[] temp = playerNames;//create clone array, fill empty slots with "New Player" next
                playerNames = new String[4];
                for(int i = 0; i < temp.length; i++)
                {
                    playerNames[i] = temp[i];
                }
            }
            //NOTE: in this game, only 4 profiles can be created as to keep in line 
            //with the nostalgic style of older games, which allowed only 4 slots.
            for(int i = 0; i < playerNames.length; i++)//allows for creation of new profiles
            {
                 if(playerNames[i]==null||playerNames[i].equals(""))
                 {
                     playerNames[i] = "New Player";
                 }
            }
            names = playerNames;
            for(int i=0;i<=3;i++)
            {
                createButton(i);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not select a player from frame",ex);
        }
    }
    
    /**
     * Creates and adds a button for selecting player slot.
     */
    private void createButton(final int slot)
    {
        try
        {
            JButton button = new JButton(backgroundImage);
            //draws out the player name onto buttone
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setText("<html>" + getText(playerNames[slot],slot) + "</html>");
            button.setFont(font);
            button.setForeground(Color.black);
            button.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    selectPlayerFrame.dispose();
                    startGame(names[slot],slot);
                }
            });
            button.setBounds(100*slot, 0, 100, 100);//set location
            this.add(button);//add to frame.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create select player buttons", ex);
        }
    }
    
    /**
     * Returns the name of the player from the profile with added statistics information.
     * @param name the name of the player unmodified.
     * @param slot the key for indicating the player.
     * @return name the modified player name.
     */
    private String getText(String name, int slot)
    {
        try
        {
            if(name.equals("New Player"))
            {
                return name;
            }
            String data = SaveLoad.getProfileData(name, slot);
            String[] dataParts = data.split("%%");//splits the array
            if(dataParts!=null&&dataParts.length>3)
            {
                return "Name: "+name + "<br>"+"Level: "+ dataParts[1]+"<br>" +"Score: "+ dataParts[0];//modifies name with statistics
            }
            return name;
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not return player name",ex);
            return null;
        }
    }
    
    /**
     * Starts the game.
     * @param playerName the name of the player.
     * @param slot the key that identifies the player.
     */
    private void startGame(String playerName, int slot)
    {
        try
        {
            GameRunner g = new GameRunner();//calls to start the game.
            if(!playerName.equals("New Player"))//existing profile
            {
                String[] data = SaveLoad.getProfileData(playerName, slot).split("%%");
                if(data!=null&&data.length>3)//new player
                {
                    g.start(SaveLoad.getProfileData(playerName, slot).split("%%"), playerName, slot);//send information over
                }
                else
                {
                    g.start(playerName, slot);
                }     
            }
            else//new player
            {
                String name = JOptionPane.showInputDialog(null, "What was the name of that boy that is lost?");
                if(name!=null&&name.length()>1&&!name.contains("%"))//proper name passes to game
                {
                    SaveLoad.saveNewProfile(name);
                    g.start(name,slot);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please try again. We hope to see you soon!");
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not start game",ex);
        }
    }   
}
