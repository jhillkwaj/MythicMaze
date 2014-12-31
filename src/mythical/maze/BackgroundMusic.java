 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author justi_000
 */
public class BackgroundMusic implements Runnable {
    private Thread thread;
    private int song;
    Clip clip;
    
    /*
    * Gets an integer song and plays a specific song according to this integer
    * @param song an integer that represents the choice of which song to play
    */
    public BackgroundMusic(int song)
    {
        this.song = song;
        start();
    }
    
    /*
    * @throws 
    */
    @Override
    public void run()
    {
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(BackgroundMusic.class.getResourceAsStream("Graphics/Race_Car_Music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            Logger.getLogger(BackgroundMusic.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
   
   /*
   * 
   */
   private void start ()
   {
      if (thread == null)
      {
         thread = new Thread (this, "Background Music");
         thread.start ();
      }
   }
    
}
