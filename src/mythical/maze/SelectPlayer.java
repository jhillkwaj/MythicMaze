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
    public static AutoCloseFrame frame = new AutoCloseFrame();//initiates variation on frame
    
    /**
     * Creates frame for selecting player slot.
     */
    public SelectPlayer()
    {
        try
        {
            frame.dispose();
            Font font = new Font("Arial", Font.PLAIN, 12);//set font
            frame.setSize(400, 120);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
            frame.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);//set location to center
            frame.setTitle("Select User");//title
            frame.setResizable(false);
            frame.getContentPane().add(this);
            frame.repaint();//paints on info
            frame.setVisible(true);
            this.setLayout(null);
            ImageIcon image = new ImageIcon(ImageManager.getImage(9).getScaledInstance(100, 100, 0));//background added
            String[] playerNames = SaveLoad.getProfiles();//retrieves slots from file
            if(playerNames==null)
            { 
                playerNames = new String[4]; //no profiles exist
            }
            if(playerNames.length<4)
            { 
                String[] temp = playerNames;//create clone array
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
            final String[] names = playerNames;
            JButton one = new JButton(image);
            //draws out the player name onto buttone
            one.setBorderPainted(false);
            one.setContentAreaFilled(false);
            one.setHorizontalTextPosition(SwingConstants.CENTER);
            one.setText("<html>" + getText(playerNames[0],0) + "</html>");
            one.setFont(font);
            one.setForeground(Color.black);
            one.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    frame.dispose();
                    startGame(names[0],0);
                }
            });
            one.setBounds(0, 0, 100, 100);//set location
            this.add(one);//add to frame.
            //same for second player
            JButton two = new JButton(image);
            two.setBorderPainted(false);
            two.setContentAreaFilled(false);
            two.setHorizontalTextPosition(SwingConstants.CENTER);
            two.setText("<html>" + getText(playerNames[1],1) + "</html>");
            two.setFont(font);
            two.setForeground(Color.black);
            two.addActionListener(new ActionListener() 
            {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        frame.dispose();
                        startGame(names[1],1);
                    }
            });
            two.setBounds(100, 0, 100, 100);
            this.add(two);
            //same for third player
            JButton three = new JButton(image);
            three.setBorderPainted(false);
            three.setContentAreaFilled(false);
            three.setHorizontalTextPosition(SwingConstants.CENTER);
            three.setText("<html>" + getText(playerNames[2],2) + "</html>");
            three.setFont(font);
            three.setForeground(Color.black);
            three.addActionListener(new ActionListener() 
            {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        frame.dispose();
                        startGame(names[2],2);
                    }
            });
            three.setBounds(200, 0, 100, 100);
            this.add(three);
            //same for fourth player
            JButton four = new JButton(image);
            four.setBorderPainted(false);
            four.setContentAreaFilled(false);
            four.setHorizontalTextPosition(SwingConstants.CENTER);
            four.setText("<html>" + getText(playerNames[3],3) + "</html>");
            four.setFont(font);
            four.setForeground(Color.black);
            four.addActionListener(new ActionListener() 
            {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        frame.dispose();
                        startGame(names[3],3);
                    }
            });
            four.setBounds(300, 0, 100, 100);
            this.add(four);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not select a player from frame",ex);
        }
    }
    
    /**
     * Returns the name of the player from the profile with added statistics information.
     * @param name the name of the player unmodified.
     * @param slot the key for indicating the player.
     * @return name the modified player name.
     */
    public String getText(String name, int slot)
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
                return name + "<br>"+ dataParts[1]+"<br>" + dataParts[2];//modifies name with statistics
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
                if(data!=null&&data.length>3)
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
                String name = JOptionPane.showInputDialog(frame, "What was the name of that boy that is lost?");
                while(name==null||name.length()<1||name.contains("%"))//prevents bad names...
                {
                    name = JOptionPane.showInputDialog(frame, "No. That's not it. What was his name?");
                }
                slot = SaveLoad.saveNewProfile(name);//saves in new profile
                g.start(name, slot);//start game
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not start game",ex);
        }
    }

   
}
