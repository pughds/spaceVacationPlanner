/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip.GUI;

import javax.swing.*;

/**
 *
 * @author Lunaretic
 */
public class EventButton extends JButton {
  
  public SpaceTrip.Events.Event myEvent;
  
  public int dayIdx;
  public int hourIdx;
  
  public EventButton(String s)
  {
      super(s);
  }
  
}
