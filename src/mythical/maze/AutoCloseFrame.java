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
        setResizable(false);
        addWindowFocusListener(this);
        addWindowListener(this);
    }
    

    @Override
    public void windowGainedFocus(WindowEvent e) 
    {}

    @Override
    public void windowLostFocus(WindowEvent e) {
        this.dispose();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e){
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }  
}
