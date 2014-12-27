/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Richard
 */
public class ErrorLogger {
    
    private static Logger log = Logger.getLogger(MainMenu.class.getName());
    private static SimpleFormatter sf = new SimpleFormatter();
    private static Handler fh; 
    
    private static void setup()
    {
        try 
        {
            fh = new FileHandler("Error.log",999999,1,true);
            fh.setFormatter(sf);
            log.addHandler(fh);
        } 
        catch (Exception e)
        {
            logIOError("Can't start log \""+
                    e.toString() + "\"",e);
        }
        logMessage("Log started\n\n");
    }
    
    public static void logMessage(String message)
    {
        if(fh == null)
        {
            setup();
        } 
        log.log(Level.INFO, message);
    }
    
    public static void logRuntimeError(String message,RuntimeException e)
    {
        if(fh == null)
        {
            setup();
        }      
        log.log(Level.SEVERE, "Runtime Exception", e);
        log.warning("Warning Message Displayed:"+message);
        fh.close();
        JOptionPane.showMessageDialog(null, "A Runtime Exception occurred. Description:" + message,
                "Error", JOptionPane.WARNING_MESSAGE);
    }
     public static void logIOError(String message,Exception e)
    {
        if(fh == null)
        {
            setup();
        }      
        log.log(Level.SEVERE, "IO Exception", e);
        
        log.warning("Warning Message Displayed, IO Exception: "+message);
        logMessage("NonRecoverableError. Game Exited and Restarted");
        fh.close();
        System.exit(0);
    }    
}
