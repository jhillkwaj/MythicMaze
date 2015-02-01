package mythical.maze;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Runs the music for the Mythical Maze game.
 * @author Justin Hill
 */
public class BackgroundMusic implements Runnable 
{
    private Thread thread;
    private final int choice;
    private Clip clip;
    
    /**
     * Takes an input choice plays a specific song according to the parameter.
     * @param choice an integer that represents the choice of which song to play.
     */
    public BackgroundMusic(int choice)
    {
        this.choice = choice;//sets the local variable to the parameter.
        start();//starts the music.
    }
    
    /**    
     * Starts a music stream from a selected music file to play during the game.
     */
    @Override
    public void run()
    {
        AudioInputStream audioIn = null;
        try
        {
            //gets audio, plays, and loops
            audioIn = AudioSystem.getAudioInputStream(BackgroundMusic.class.getResourceAsStream("Graphics/Race_Car_Music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) 
        {
            ErrorLogger.logIOError("Music file could not be played" ,ex);//logs potential IOException
        }
    }
   
    /**
     * Starts a thread to play game music.
     */
    private void start()
    {
       if (thread == null)
       {
          thread = new Thread (this, "Background Music");
          thread.start();
       }
    }  
}
