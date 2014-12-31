/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mythical.maze;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author justi_000
 */
public class SoundFX {
    
    private static Clip clip;
    
    
    public static void payFX(String name)
    {
        final String clipName = name;
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(BackgroundMusic.class.getResourceAsStream("Graphics/"+clipName+".wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception ex) {
            Logger.getLogger(BackgroundMusic.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        thread.run();
    }
              
       
    
}
