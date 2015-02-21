package mythical.maze;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Runs the music for the Mythical Maze game.
 * @author Justin Hill
 */
public class BackgroundMusic
{
    private static Clip clip;
    private static String lastSong = "";

    /**
     * Plays a specific song
     * @param name the song to play.
     */
    public static void play(String name)
    {
        if(clip!=null)
        { 
            clip.stop(); 
        }
        lastSong = name;
        if(Settings.musicOn)
        {
            final String songName = name;
            Thread thread;
            thread = new Thread(new Runnable()
            {
                @Override
                public void run() 
                {
                    AudioInputStream audioIn = null;//initiates audio stream
                    try 
                    {
                        audioIn = AudioSystem.getAudioInputStream(BackgroundMusic.class.getResourceAsStream("Graphics/"+songName+".wav"));//set path
                        clip = AudioSystem.getClip();//gets clip
                        clip.open(audioIn);//opens clip
                        clip.start();//plays song
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } 
                    catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex)
                    {
                        ErrorLogger.logIOError("Music could not load: IOException",ex);
                    }
                    catch(Exception ex)
                    {
                        ErrorLogger.logRuntimeError("Unknown error: sound effects did not load",ex);
                    }
                }
            });
            EventLogger.logEvent("Music successfully loaded and played");
            thread.start();//runs the music playing thread
        }
    }
    
    /**
     * Stops the current song from playing
     */
    public static void stop()
    {
        try
        {
            if(clip!=null)
            {
                clip.stop();
                clip = null;
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not stop music clip",ex);
        }
    }
    
    /**
     * Changes the volume of the current song
     * @param amount the decibels change in the volume
     */
    public static void changeVolume(float amount)
    {
        FloatControl gainControl = 
        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(amount);
    }
    
    /**
     * Resumes playing the last song
     */
    public static void unMute()
    {
        play(lastSong);
    }
}
