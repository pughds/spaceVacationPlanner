/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip.GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SpaceTrip.GUI.Panels.Panel;
import SpaceTrip.Engine;

/**
 *
 * @author Scott
 */
public class UIManager {
  
  //Control members
  private Panel currentPanel;
  
  public UIManager()
  {
  }
    
  //Main panel changing function, kills the current panel and opens a new one.
  public boolean changePanel(String panelName)
  {
      if(currentPanel != null)
      {
        //Close the old panel (Call onClose before smashing the panel though).
        currentPanel.onClose();
        currentPanel.dispose();
      }
      
      //Exit handler.
      if(panelName.equals(""))
      {
        System.exit(0);
      }
      
      //Instantiate new Panel object based on incoming string.
      try
      {
        
        currentPanel = ((Panel) Class.forName("SpaceTrip.GUI.Panels."+panelName).newInstance());
      }
      catch(Exception e)
      {
        //Catch bad classnames.
        JOptionPane.showMessageDialog(currentPanel, "Class does not exist: " + panelName);
        return false;
      }
      
      currentPanel.display();
      return true;
  }
  
  //Main startup function for the GUI.
  public void startGUI()
  {
    SwingUtilities.invokeLater(new Runnable() 
    {
        public void run() {
          changePanel("MainMenu");
        }
    });
  }
  
  //Handler for window closing.
  public WindowAdapter closeHandler = new WindowAdapter() {
    public void windowClosing(WindowEvent we) {
      if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to quit?", "Quit Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
      {
        changePanel("");
      }
    }
  };
}
