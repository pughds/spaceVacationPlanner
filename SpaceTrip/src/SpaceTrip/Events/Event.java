/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip.Events;

/**
 *
 * @author Scott
 */
public class Event {
  
  // Static function, returns an array of all possible events.
  public static Event[] getAllEvents()
  {
    Event[] events;
    
    int totalEvents = 10;
    
    events = new Event[totalEvents];
    
    events[0] = new Event("Concert", 1000, 3, "violin2.jpg", "Allow yourself to be serenaded by the finest orchestra in the system, all while viewing Jupiter's stunning moon Io.");
    events[1] = new Event("Wine Tasting", 350, 2, "grapes.jpg", "Venus vintners invites you to stop by and share a romantic wine tasting for two. Allow our fine vintages to warm your heart; or, is that the atmosphere?");
    events[2] = new Event("Climb Olympus Mons", 500, 5, "OlympusMons.jpg", "Try your hand at a little Martian mountaineering! The view is incredible from the top.");
    events[3] = new Event("Solar Safari", 1500, 6, "safari.jpg", "Take a trip around the system and see some of the engineered life transplanted there last decade.");
    events[4] = new Event("Mars mini-golf", 125, 1, "minigolf.jpg", "Play a round in Cydonia!");
    events[5] = new Event("Asteroid Prospecting", 200, 4, "pickaxe.jpg", "Take a tour of the Asteroid Belt and try your hand at someOld Tyme pospecting. Who knows, you might strike it rich!");
    events[6] = new Event("Oort Cloud Tour", 500, 3, "comet.jpg", "See some space snowballs. The ice machine of the solar system!");
    events[7] = new Event("Sunbathing on Mercury", 125, 2, "mercury.jpg", "Enjoy some of the best suntanning in the system at our retreat on Mercury. Don't forget your sunblock! (SPF 10,000,000 recommended)");
    events[8] = new Event("Historical sites tour", 635, 6, "tranquility.jpg", "Visit some of the sites chronicling Humanity's spread into space. Some sites included are: Tranquility Base and Restaurant, Bowie Base 1 on Mars, and the Neptune's Massif Mall!");
    events[9] = new Event("Space Race!", 245, 1, "saturn.jpg", "Try your hand at some real racing action on our course around Saturn's rings!");
    
    return events;
  }
  
  // Construtor
  public Event(String n, int c, int d, String img, String description)
  {
      this.name = n;
      this.cost = c;
      this.duration = d;
      this.image = img;
      this.description = description;
  }
  
  //Basic info
  public String name;
  public int cost;
  public String image;
  public String description;
  
  //Time of event starting.
  public int day = -1;
  public int hour = -1;
  
  //Duration in hours
  public int duration;
  
  public Event(Event e)
  {
      this.name = e.name;
      this.cost = e.cost;
      this.image = e.image;
      this.description = e.description;
      this.day = e.day;
      this.hour = e.hour;
      this.duration = e.duration;
  }
}
