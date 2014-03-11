/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip.Schedule;
import SpaceTrip.Events.Event;

/**
 *
 * @author Scott
 */
public class ScheduleManager {
  
  public int totalDays;
  public int totalFunds;
  public int currentFunds;
  public int totalPeople;
  
  public Event[][] schedule;
  
    public ScheduleManager(int days, int funds)
    {
        this.totalDays = days;
        this.totalFunds = funds;
        this.currentFunds = funds;
        
        //Assume 8 hours a day, starting at Noon until 8PM?
        this.schedule = new Event[totalDays][8];
    }
}
