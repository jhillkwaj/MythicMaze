package mythical.maze;

import java.io.IOException;
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
    public static void playFX(String name)
    {
        if(Settings.soundEffectsOn)
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
                        EventLogger.logEvent("Sound effect successful load");
                    } 
                    catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex)
                    {
                        ErrorLogger.logIOError("Cannot play sound effect",ex);
                    }
                    catch(Exception ex)
                    {
                        ErrorLogger.logRuntimeError("Unknown error in playing sound effect",ex);
                    }
                }
            });
            thread.start();//runs the sound creating thread
        }
    }
}
