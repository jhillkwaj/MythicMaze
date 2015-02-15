package mythical.maze;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 * Logs system errors to a dedicated log file.
 * @author Richard Dong
 */
public class ErrorLogger 
{
    private static final Logger logError = Logger.getLogger("ErrorLogger");
    private static final SimpleFormatter sf = new SimpleFormatter();
    private static Handler fhError; 
   
    /**
     * Sets up error log with correct formatting and tools to append 
     * existing messages.
     */
    public static void setupError()
    {
        try 
        {
            fhError = new FileHandler("ErrorLogger.txt",999999,1,true);
            fhError.setFormatter(sf);
            logError.addHandler(fhError);   
        } 
        catch (IOException | SecurityException e)
        {
            logIOError("Can't start Error log \""+
                    e.toString() + "\"",e);
        }
    }
       
    /**
     * Logs a Runtime Exception into the error log file.
     * @param message an appropriate message describing the exception.
     * @param e the exception itself; includes location and path.
     */
    public static void logRuntimeError(String message, RuntimeException e)
    {
        if(fhError == null)//setup log file if nonexistant
        {
            setupError();
        }
        logError.log(Level.SEVERE, "Runtime Exception", e);//log exception
        logError.log(Level.INFO,"Warning Message Displayed:"+message);//log message shown to user
        EventLogger.logEvent("Runtime Error occurred, warning message displayed: "+message);
        fhError.close();
        JOptionPane.showMessageDialog(null, "A Runtime Exception occurred. Description:" + message,
                "Error", JOptionPane.WARNING_MESSAGE);//display message to user
    }
    
    /**
     * Logs an IOException into the error log file and terminates game if unable to continue.
     * @param message an appropriate message describing the exception.
     * @param e the exception itself; includes location and path.
     */
    public static void logIOError(String message, Exception e)
    {
        if(fhError == null)//sets up file if non-existant
        {
            setupError();
        }      
        logError.log(Level.SEVERE, "IO Exception: "+ message, e);//logs the error
        logError.log(Level.INFO, "NonRecoverableError. Prompted user to exit.");//logs resulting action
        EventLogger.logEvent("IO Exception occurred, prompted user to exit. Description: " + message);
        fhError.close();
        Object[] options = { "OK", "CANCEL" };
        int num = JOptionPane.showOptionDialog(null, "A non-recoverable error has occurred. Press OK to exit.", "Warning",
        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if(num == JOptionPane.YES_OPTION)
        {
            CrashLogger.logCrash(message, e);
            System.exit(0);//exits the game
        }
    }    
}
