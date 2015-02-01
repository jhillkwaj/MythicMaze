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
 * Logs error messages for debugging to a file.
 * @author Richard Dong
 */
public class ErrorLogger {
    
    private static final Logger log = Logger.getLogger(MainMenu.class.getName());
    private static final SimpleFormatter sf = new SimpleFormatter();
    private static Handler fh; 
    
    /**
     * Sets up the error logger with correct formatting and tools to append 
     * existing messages.
     */
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
    

    /**
     * Logs a message into the error logging file.
     * @param message the message to log.
     */
    
    public static void logMessage(String message)
    {
        if(fh == null)//setup the log file if nonexistant
        {
            setup();
        } 
        log.log(Level.INFO, message);//log message as information
    }
    
    /**
     * Logs a Runtime Exception into the log file.
     * @param message an appropriate message describing the exception.
     * @param e the exception itself; includes location and path.
     */
    public static void logRuntimeError(String message, RuntimeException e)
    {
        if(fh == null)//setup log file if nonexistant
        {
            setup();
        }      
        log.log(Level.SEVERE, "Runtime Exception", e);//log exception
        log.warning("Warning Message Displayed:"+message);//log message shown to user
        fh.close();
        JOptionPane.showMessageDialog(null, "A Runtime Exception occurred. Description:" + message,
                "Error", JOptionPane.WARNING_MESSAGE);//display message to user
    }
    

    /**
     * Logs an IOException into the log file and terminates game if unable to continue.
     * @param message an appropriate message describing the exception.
     * @param e the exception itself; includes location and path.
     */
    public static void logIOError(String message, Exception e)
    {
        if(fh == null)//sets up file if non-existant
        {
            setup();
        }      
        log.log(Level.SEVERE, "IO Exception", e);//logs the error

        log.warning("Warning Message Displayed, IO Exception: "+message);//logs message to user
        logMessage("NonRecoverableError. Game Exited and Restarted");//logs resulting action
        fh.close();
        System.exit(0);//exits the game to prevent system from crashing.
    }    
}
