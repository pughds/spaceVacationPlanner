/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip;

import SpaceTrip.*;
import SpaceTrip.GUI.*;
import SpaceTrip.Schedule.*;
import SpaceTrip.Events.*;

/**
 * The core engine.  Note this is a static class.
 * This means it can effectively be accessed from anywhere in the program.
 * This lets even our basic GUI panels access our schedule data effectively.
 * @author Scott
 */
public class Engine {
  
  public static UIManager GUI;
  public static ScheduleManager Schedule;
  public static Event[] EventList;
  
  
  public static void begin()
  {
    Engine.GUI = new UIManager();
    //TODO: Link in actual data to the Schedule at some point here.
    Engine.Schedule = new ScheduleManager(3, 10000);
    Engine.EventList = Event.getAllEvents();
    //Enable the GUI.
    GUI.startGUI();
  }
}
