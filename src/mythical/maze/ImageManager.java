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
import javax.imageio.ImageIO;

/**
 *
 * @author 100032528
 */
public class ImageManager {
    private static final ArrayList<Image> images = new ArrayList<>();
    public static void importAll()
    {
        try {
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/CTM.png"))); //0
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/ContractedBy.png"))); //1
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/DevelopedBy.png"))); //2
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/TECHs.png"))); //3
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/MM.png"))); //4
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Maze.png"))); //5
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Mythical.png"))); //6
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/teddy.png"))); //7
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Brick.png"))); //8
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Ice_Stacked.png"))); //9
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Jungle_Rock.png"))); //10
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Rock.png"))); //11
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Wood_Crate_Enlarged.png"))); //12
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Evil_Forest_Demo.png"))); //13
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Pyramids.png")));//14
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Stonehenge.png")));//15
            images.add(null);
            Thread.sleep(100);    
        } catch (Exception ex) {
            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Image getImage(int slot)
    {
        if(images.size()==0)
        { 
            importAll(); 
        }
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

                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, "Image Not Founded");
               
                return null;
            } catch (Exception ex) {
                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return null;
        }
        
    
}
