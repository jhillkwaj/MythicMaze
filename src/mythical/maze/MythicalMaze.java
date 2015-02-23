package mythical.maze;

import java.io.File;

/**
 * Mythical Maze main class.
 * @author Justin Hill
 */
public class MythicalMaze 
{
    /**
     * Main method for the Mythical Maze game.
     * @param args the command line arguments.
     */
    public static void main(String[] args) 
    {
        //create folder system nessisary to save files
        File profiles = new File("profiles");
        File logs = new File("logs");
        if (!logs.exists())
            logs.mkdir();
        if(!profiles.exists())
            profiles.mkdir();
        MainMenu m = new MainMenu();//initiates main menu
        m.start();//starts menu;
    }
}
