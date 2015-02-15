package mythical.maze;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Saves game profiles into a local file database.
 * @author Justin Hill
 */
public class SaveLoad 
{    

    /**
     * Extracts profiles from the local file database.
     * @return data the array with player profiles.
     */
    public static String[] getProfiles()
    {
        ArrayList<String> profileNames = new ArrayList<>();
        try 
        {
            File f = new File("profiles.dat");//set path to file
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    profileNames.add(in.nextLine());//read in file lines
                }
                in.close();
            }
            else//file does not exist
            {
                return null;
            }
            String[] data = new String[profileNames.size()];
            for(int i = 0; i < profileNames.size(); i++)
            {
                data[i] = profileNames.get(i);//new clone array
            }
            return data;//return clone, clone is needed as original will need to be modified.
        } 
        catch (Exception e) 
        {
            ErrorLogger.logIOError("Cannot load profiles", e);
        }
        return null;//if no profiles to return
    }
    
    /**
     * Saves a new profile to the local database.
     * @param name the new profile to save.
     * @return the slot number for the new player
     */
    public static int saveNewProfile(String name)
    {
        int slot = 0;
        ArrayList<String> profileNames = new ArrayList<>();
        try 
        {
            File f = new File("profiles.dat");//set file path
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    profileNames.add(in.nextLine());//extract original lines from file.
                }
                in.close();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("profiles.dat")));//set file path
            for(String s : profileNames)
            {
                out.write(s);//write in original lines
                out.newLine();
                slot++;
            }
            out.write(name);//add in new profile
            out.close();
            setProfileData(name, profileNames.size(), "");
        } 
        catch (Exception e) 
        {
            ErrorLogger.logIOError("Cannot save new profile", e);
        }
        return slot;
    }
    
    /**
     * Extracts player statistics from individual player files.
     * @param name the name of the profile file.
     * @param slot the key for identifying the file.
     * @return profileData a string with the extracted information.
     */
    public static String getProfileData(String name, int slot)
    {
        try 
        {
            String profileData = "";
            File data = new File(name+slot+".dat");
            if(data.exists())
            {
                Scanner inData = new Scanner(new FileReader(data));
                try
                {
                    if(inData.hasNext())//if has lines
                    {
                        profileData = inData.nextLine();//set profileData to that line
                    }
                    return profileData;
                }
                finally
                {
                    inData.close();
                }
            }
            EventLogger.logEvent("There dosn't seem to be any saved data for this profile");//no lines in file
            return profileData;
        }
        catch (Exception e) 
        {
            ErrorLogger.logIOError("Can't load individual profile.", e);
        }
        return null;
    }
    
    /**
     * Enters new profile data into individual file.
     * @param name the profile name.
     * @param slot the key to identify the file.
     * @param data the data to enter.
     */
    public static void setProfileData(String name, int slot, String data)
    {
        BufferedWriter out;
        try 
        {
            out = new BufferedWriter(new FileWriter(new File(name+slot+".dat")));//set file path.
            out.write(data);//writes in data
            out.close();
        }
        catch (IOException ex) 
        {
            ErrorLogger.logIOError("Cannot save profile data to file", ex);
        }
    }
    
    /**
     * Saves high scores of the game.
     * @param name the profile name that achieved the score.
     * @param highscore the score itself.
     */
    public static void saveGlobalHighscore(String name, int highscore)
    {
        TreeMap<Integer, String> scores = new TreeMap<>();//creates a treemap of score and name keys
        try 
        {
            File f = new File("highScores.dat");//sets file path.
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    String line = in.nextLine();//scans in existing high scores
                    scores.put(Integer.parseInt(line.split("%%")[0]),line.split("%%")[1]);//splits and puts into tree
                }
                in.close();
            }
            scores.put(highscore,name);//add into treemap, which automatically sorts into order.
            BufferedWriter out = new BufferedWriter(new FileWriter(f));//set file path
            for(int i = 0; (i < 5 && scores.size()>0); i++)
            {
                out.write(scores.lastKey()+"%%"+scores.remove(scores.lastKey()));//write the scores in order
                out.newLine();
            }
            out.close();
        }
        catch (IOException e) 
        {
            ErrorLogger.logIOError("Cannot save highscore", e);
        }
    }
    
    /**
     * Extracts high scores for displaying to user.
     * @return scores the TreeMap of high scores and player names.
     */
    public static TreeMap<Integer, String> getHighScores()
    {
        TreeMap<Integer, String> scores = new TreeMap<>();
        try 
        {
            File f = new File("highScores.dat");//sets file path.
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    String line = in.nextLine();
                    scores.put(Integer.parseInt(line.split("%%")[0]),line.split("%%")[1]);//splits and puts into tree.
                }
                in.close();
            }
        }
        catch (IOException e) 
        {
            ErrorLogger.logIOError("Can't extract highscores", e);
        }
        int i = 0;//no high scores exist yet.
        while(scores.size()<5)//creates fake profiles
        {
            switch(i)
            {
                case 0:
                    scores.put(50, "Alice");
                    break;
                case 1:
                    scores.put(40, "Bob");
                    break;
                case 2:
                    scores.put(30, "Eve");
                    break;
                case 3:
                    scores.put(20, "Mallet");
                    break; 
                case 4:
                    scores.put(10, "Oscar");
                    break;      
            }
            i++;               
        }
        return scores;
    }
}
