package mythical.maze;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Imports images for later use when called upon.
 * @author Justin Hill
 */
public class ImageManager 
{
    private static final ArrayList<Image> images = new ArrayList<>();
    
    /**
     * Imports images from a local file.
     */
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
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Alamo.png"))); //16
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Chichen Itza.png"))); //17
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_ChichenItza.png"))); //18
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_EiffelTower.png")));//19
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_SydneyoperaHouse.png")));//20
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Tajmahal.png")));//21
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Wood_Crate_Enlarged_Three.png")));//22
            Thread.sleep(100);    
        } 
        catch (IOException | InterruptedException ex) 
        {
            ErrorLogger.logIOError("Unable to Import Graphics",ex);
            //log an error to fix local file placement, interrupted extraction.
        }
    }
    
    /**
     * Retrieves images for use in graphics creation.
     * @param slot the image key used to determine which image to select.
     * @return Image the correct image object reference.
     */
    public static Image getImage(int slot)
    {
        if(images.isEmpty())
        { 
            importAll(); //import images before use.
            addFont(); //import the font used in the menus of the game
        }
        if(slot<images.size()&&images.get(slot)!=null)
        {
            return images.get(slot);
        }
        else//not in parameters, image doesn't exist
        {
            try 
            {
                //The image you wanted dosn't exist
                Map m = Thread.getAllStackTraces();
                //print the stack trase and log the error
                for(int i = 0; i < m.size(); i++)
                {
                    System.out.print(m.get(i));
                }
                //close image stream (prevents memory leak)
                return null;
            } 
            catch (Exception ex) 
            {
                ErrorLogger.logIOError("Image not found",ex);//log error
            }
        }
        return null;
    } 
    
    public static void addFont()
    {
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, ImageManager.class.getResource("Graphics/wlm_carton.ttf").openStream());
            ge.registerFont(font);
        }catch(Exception e)
        { ErrorLogger.logIOError("Could not import Font", e);}
    }
}
