/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythical.maze;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author 100032528
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
