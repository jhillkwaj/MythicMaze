package mythical.maze;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logs system crash to a dedicated log file.
 * @author Richard Dong
 */
public class CrashLogger 
{
    private static final Logger logCrash = Logger.getLogger("CrashLogger");
    private static final SimpleFormatter sf = new SimpleFormatter();
    private static Handler fhCrash; 
        
    /**
     * Sets up crash handler log with correct formatting and tools to append 
     * existing messages.
     */
    public static void setupCrash()
    {
        try 
        {  
            fhCrash = new FileHandler("CrashHandler.txt",999999,1,true);
            fhCrash.setFormatter(sf);
            logCrash.addHandler(fhCrash);   
        } 
        catch (IOException | SecurityException e)
        {
            ErrorLogger.logIOError("Can't start Crash log \""+
                    e.toString() + "\"",e);
        }
    }
    
    /**
     * Logs system crash into the crash handling log.
     * @param message an appropriate message describing the exception.
     * @param e the exception itself; includes location and path.
     */
    public static void logCrash(String message, Exception e)
    {
        if(fhCrash == null)//sets up file if non-existant
        {
            setupCrash();
        }
        logCrash.log(Level.SEVERE, "Game crashed: "+message, e);//logs the error
        EventLogger.logEvent("Game Crashed");
        fhCrash.close();
    }
}
