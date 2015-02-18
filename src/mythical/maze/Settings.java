package mythical.maze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads and writes game settings information to a settings file.
 * @author Richard Dong
 */
public class Settings 
{
    public static boolean musicOn = true;
    public static boolean soundEffectsOn = true;
    
    /**
     * Scans in the information in a saved settings file and sets game settings.
     */
    public static void scanIn()
    {
        //scan lines of settings into list
        ArrayList<String>inputList = new ArrayList<>();
        String fileName = "Settings.txt";
        String line; //read one line of file
        try
        {
            
            FileReader reader = new FileReader(fileName);
            BufferedReader scanner = new BufferedReader(reader);
            while((line = scanner.readLine())!= null)
            {
                inputList.add(line);
            }
            scanner.close();
        }
        catch(FileNotFoundException ex)
        {
            ErrorLogger.logIOError("Settings file not located", ex);
        }
        catch(IOException ex)
        {
            ErrorLogger.logIOError("Error reading Settings file",ex);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Unknown error with reading Settings file",ex);
        }
        //lines in list will be either Y or N, indicating yes or no
        //music will be line 1, sound effects will be line 2
        switch (inputList.get(0)) 
        {
            case "Y":
                musicOn=true;
                break;
            case "N":
                musicOn=false;
                break;
        //unknown letters in file
            default:
                ErrorLogger.logIOError("Unknown text found in settings file",new IOException());
                break;
        }
        switch(inputList.get(1)) 
        {
            case "Y":
                soundEffectsOn = true;
                break;
            case "N":
                soundEffectsOn = false;
                break;
                //unknown letters in file
            default:
                ErrorLogger.logIOError("Unknown text found in settings file",new IOException());
                break;
        }
    }

    /**
     * Writes and saves game settings to a local file.
     */
    public static void saveSettings()
    {
        try
        {
            String fileName = "Settings.txt";
            FileWriter writeToFile = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(writeToFile);
            if(musicOn)
            {
                writer.write("Y");
            }
            else
            {
                writer.write("N");
            }
            writer.newLine();
            if(soundEffectsOn)
            {
                writer.write("Y");
            }
            else
            {
                writer.write("N");
            }
            writer.close();
        }
        catch(IOException ex)
        {
            ErrorLogger.logIOError("Unable to save settings", ex);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Unknown error with saving settings", ex);
        }
    }

    /**
     * Turns music off and calls to save setting.
     */
    public static void turnMusicOff()
    {
        BackgroundMusic.stop();
        musicOn = false;
        saveSettings();
    }

    /**
     * Turn music on and calls to save setting.
     */
    public static void turnMusicOn()
    {
        BackgroundMusic.unMute();
        musicOn = true;
        saveSettings();
    }

    /**
     * Turns sound effects on and calls to save setting.
     */
    public static void turnSoundEffectsOn()
    {
        soundEffectsOn = true;
        saveSettings();
    }

    /**
     * Turns sound effects off and calls to save setting.
     */
    public static void turnSoundEffectsOff()
    {
        soundEffectsOn = false;
        saveSettings();
    }
}
