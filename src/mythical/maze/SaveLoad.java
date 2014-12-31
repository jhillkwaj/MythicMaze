/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 *
 * @author justi_000
 */
public class SaveLoad {
    
    public static String[] getProfiles()
    {
        ArrayList<String> profileNames = new ArrayList<>();
        try {
            File f = new File("profiles.dat");
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    profileNames.add(in.nextLine());
                }
                in.close();
            }
            else{
                return null;
            }
            String[] data = new String[profileNames.size()];
            for(int i = 0; i < profileNames.size(); i++)
            {
                data[i] = profileNames.get(i);
            }
            return data;
        } catch (Exception e) {
            ErrorLogger.logIOError("Can't load profiles", e);
        }
        return null;
    }
    
    public static void saveNewProfile(String name)
    {
        ArrayList<String> profileNames = new ArrayList<>();
        try {
            File f = new File("profiles.dat");
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    profileNames.add(in.nextLine());
                }
                in.close();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("profiles.dat")));
            for(String s : profileNames)
            {
                out.write(s);
                out.newLine();
            }
            out.write(name);
            out.close();
            setProfileData(name, profileNames.size(), "");
        } catch (Exception e) {
            ErrorLogger.logIOError("Can't save new profile", e);
        }
    }
    
    public static String getProfileData(String name, int slot)
    {
        try {
            
                File data = new File(name+slot+".dat");
                if(data.exists())
                {
                    Scanner inData = new Scanner(new FileReader(data));
                
                
                    try{
                        if(inData.hasNext())
                        {
                            return inData.nextLine();
                        }
                        return "";
                    }finally{
                        inData.close();
                    }
                }
                ErrorLogger.logMessage("There dosn't seem to be any saved data for this profile");
                return "";
            
        } catch (Exception e) {
            ErrorLogger.logIOError("Can't load profiles", e);
        }
        return null;
    }
    
    public static void setProfileData(String name, int slot, String data)
    {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(new File(name+slot+".dat")));
            out.write(data);
            out.close();
        } catch (IOException ex) {
            ErrorLogger.logIOError("Can't get saved data", ex);
        }
    }
    
    public static void saveGlobalHighscore(String name, int highscore)
    {
        TreeMap<Integer, String> scores = new TreeMap<>();
        try {
            File f = new File("highScores.dat");
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    String line = in.nextLine();
                    scores.put(Integer.parseInt(line.split("%%")[0]),line.split("%%")[1]);
                }
                in.close();
            }
            scores.put(highscore,name);
            
            
            BufferedWriter out = new BufferedWriter(new FileWriter(f));
            for(int i = 0; (i < 5 && scores.size()>0); i++)
            {
                out.write(scores.lastKey()+"%%"+scores.remove(scores.lastKey()));
                out.newLine();
            }
                
            out.close();
        } catch (Exception e) {
            ErrorLogger.logIOError("Can't save Highscore", e);
        }
    }
    
    public static TreeMap<Integer, String> getHighScores()
    {
        TreeMap<Integer, String> scores = new TreeMap<>();
        try {
            File f = new File("highScores.dat");
            if(f.exists())
            {
                Scanner in = new Scanner(new FileReader(f));
                while(in.hasNext())
                {
                    String line = in.nextLine();
                    scores.put(Integer.parseInt(line.split("%%")[0]),line.split("%%")[1]);
                }
                in.close();
            }
        }catch (Exception e) {
            ErrorLogger.logIOError("Can't load Highscore", e);
        }
        int i = 0;
        while(scores.size()<5)
        {
            switch(i){
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
