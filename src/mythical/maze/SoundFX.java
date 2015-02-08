package mythical.maze;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Plays sound effects.
 * @author Justin Hill
 */
public class SoundFX 
{
    private static Clip clip;

    /**
     * Plays a specific sound effect.
     * @param name the sound effect to play.
     */
    public static void payFX(String name)
    {
        final String clipName = name;
        Thread thread;
        thread = new Thread(new Runnable()
        {
            @Override
            public void run() 
            {
                AudioInputStream audioIn = null;//initiates audio stream
                try 
                {
                    audioIn = AudioSystem.getAudioInputStream(BackgroundMusic.class.getResourceAsStream("Graphics/"+clipName+".wav"));//set path
                    clip = AudioSystem.getClip();//gets clip
                    clip.open(audioIn);//opens clip
                    clip.start();//plays sound
                } 
                catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex)
                {
                    ErrorLogger.logIOError("Cannot play sound effect",ex);
                }
            }
        });
        thread.start();//runs the sound creating thread
    }
}
