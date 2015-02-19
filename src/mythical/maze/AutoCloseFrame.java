package mythical.maze;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * Closes a frame when focus is lost.
 * @author Justin HIll
 */
public class AutoCloseFrame extends JFrame implements WindowFocusListener,  WindowListener {
    
    public AutoCloseFrame()
    {
        super();
        addWindowFocusListener(this);
        addWindowListener(this);
    }
    

    public void windowGainedFocus(WindowEvent e) 
    {}

    public void windowLostFocus(WindowEvent e) {
        this.dispose();
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e){
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
    
}
