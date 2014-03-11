/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip.GUI.Panels;

import SpaceTrip.*;
import SpaceTrip.Events.*;
import SpaceTrip.GUI.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;

/**
 *
 * @author Lunaretic
 */
public class PlanningScreen extends Panel {
  
  private Event selectedEvent;
  private boolean addMode = false;
  
  private JLabel eventLabel;
  private JLabel timeLabel;
  private JLabel costLabel;
  private JTextArea descriptionLabel;
  private JLabel imageLabel;
  
  private JLabel remFundsLabel;
  
  private JPanel schedulePanel;
  private JPanel eventsPanel;
  
  private JButton addToSchduleBtn;
  private JButton remFrSchduleBtn;
  private JButton cancelBtn;
  private JButton finalizeButton;
  
  public void display()
  {
      //Set up core attributes
      setTitle("Space Trip Planning Screen");
      setSize(800, 400);
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      JPanel rootPanel = new JPanel();
      setContentPane(rootPanel);
      rootPanel.setLayout(null);
      
      //Create the top panel.
      JPanel topPanel = new JPanel();
      topPanel.setLayout(null);
      topPanel.setBounds(0,0,this.getWidth(),22);
      rootPanel.add(topPanel);
      
      //Create the bottom panel.
      JPanel bottomPanel = new JPanel();
      bottomPanel.setLayout(null);
      bottomPanel.setBounds(0,this.getHeight()-56,this.getWidth(),22);
      rootPanel.add(bottomPanel);
      
      //Create the left side events list.
      eventsPanel = new JPanel();
      eventsPanel.setLayout(null);
      eventsPanel.setBounds(0,22,200,this.getHeight()-76);
      rootPanel.add(eventsPanel);
      
      //Create the right side schedule list.
      int schHeight = (Engine.Schedule.schedule.length * 9) * 28;
      JScrollPane scheduleScrollPanel = new JScrollPane();
      scheduleScrollPanel.setBounds(this.getWidth()-200,22,194,this.getHeight()-76);
      scheduleScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scheduleScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      rootPanel.add(scheduleScrollPanel);
      schedulePanel = new JPanel();
      schedulePanel.setLayout(null);
      schedulePanel.setBounds(0,0,scheduleScrollPanel.getWidth(),schHeight);
      schedulePanel.setPreferredSize(new Dimension(scheduleScrollPanel.getWidth(),schHeight));
      
      
      //Create the central description panel
      JPanel descriptionPanel = new JPanel();
      descriptionPanel.setLayout(null);
      descriptionPanel.setBounds(200,22,this.getWidth()-400,this.getHeight()-76);
      rootPanel.add(descriptionPanel);
      
      
      //----------------------------------------------------------
      //                        Events Panel
      //----------------------------------------------------------
      int idx = 0;
      for(SpaceTrip.Events.Event e : Engine.EventList)
      {
        //We use a custom extended version of button here, which can
        //hold onto an event object in its core, thus when we click the button,
        //it can extract its internal event to set for main panel.
        EventButton temp = new EventButton(e.name);
        temp.setFont(new Font("Arial",14, 14));
        temp.setMargin(new Insets(0,0,0,0));
        temp.setBounds(0, (idx*28), 198, 28);
        temp.myEvent = e;
        eventsPanel.add(temp);
        temp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                      setSelectedEvent(((EventButton)event.getSource()).myEvent);
                    }
                });
        
        idx++;
      }
      
      //----------------------------------------------------------
      //                       Schedule Panel
      //----------------------------------------------------------
      
      
      int dayIdx = 0;
      int hourIdx = 0;
      //Loop for each day/hour.
      for(Event daysEvents[] : Engine.Schedule.schedule)
      {
        JLabel dLabel = new JLabel("Day " + (dayIdx +1), null, JLabel.CENTER);
        dLabel.setFont(new Font("Arial",16, 16));
        dLabel.setBounds(2, ((hourIdx + (dayIdx*9))*28), scheduleScrollPanel.getWidth()-22, 28);
        schedulePanel.add(dLabel);
            
        for(Event e : daysEvents)
        {
          
          //Handling for empty entries.
          if(e == null)
          {            
            EventButton temp = new EventButton(hourIdx + "PM: Nothing");
            if(hourIdx == 0)
              temp.setText("12PM: Nothing");
            temp.setFont(new Font("Arial",14, 14));
            temp.setMargin(new Insets(0,0,0,0));
            temp.setBounds(2, ((hourIdx + (dayIdx*9))*28)+28, scheduleScrollPanel.getWidth()-22, 28);
            temp.myEvent = e;
            temp.hourIdx = hourIdx;
            temp.dayIdx = dayIdx;
            schedulePanel.add(temp);
            temp.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                          scheduleClick(((EventButton)event.getSource()));
                        }
                    });
          }
          else
          {
            EventButton temp = new EventButton(hourIdx + "PM: "+e.name);
            if(hourIdx == 0)
              temp.setText("12PM: "+e.name);
            temp.setFont(new Font("Arial",14, 14));
            temp.setMargin(new Insets(0,0,0,0));
            temp.setBounds(2, ((hourIdx + (dayIdx*9))*28)+28, scheduleScrollPanel.getWidth()-22, 28);
            temp.myEvent = e;
            schedulePanel.add(temp);
            temp.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                          //setSelectedEvent(((EventButton)event.getSource()).myEvent);
                        }
                    });
          }
          hourIdx++;
        }
        hourIdx = 0;
        dayIdx++;
      }

      scheduleScrollPanel.setViewportView(schedulePanel);
      scheduleScrollPanel.getViewport().setSize(200, 200);
      
      //----------------------------------------------------------
      //                     Description Panel
      //----------------------------------------------------------
      
      eventLabel = new JLabel("No Event", null, JLabel.CENTER);
      eventLabel.setBounds(20,2,descriptionPanel.getWidth()-40,20);
      eventLabel.setFont(new Font("Arial",18, 18));
      descriptionPanel.add(eventLabel);
      
      descriptionLabel = new JTextArea("No Description");
      descriptionLabel.setBounds(20,184,descriptionPanel.getWidth()-40,80);
      descriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      descriptionLabel.setFont(new Font("Arial",14, 14));
      descriptionLabel.setLineWrap(true);
      descriptionLabel.setWrapStyleWord(true);
      descriptionLabel.setEditable(false);
      descriptionPanel.add(descriptionLabel);
      
      costLabel = new JLabel("Cost: N/A");
      costLabel.setBounds(40,270,180,20);
      costLabel.setFont(new Font("Arial",14, 14));
      descriptionPanel.add(costLabel);
      
      timeLabel = new JLabel("Time Required: N/A");
      timeLabel.setBounds(230,270,180,20);
      timeLabel.setFont(new Font("Arial",14, 14));
      descriptionPanel.add(timeLabel);
      
      ImageIcon img = new ImageIcon("E:\\Pictures\\4Chan\\1DXd3.jpg","Hampster");
      imageLabel = new JLabel(img);
      imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      imageLabel.setBounds(20,24,descriptionPanel.getWidth()-40,150);
      descriptionPanel.add(imageLabel);
      
      addToSchduleBtn = new JButton("Add to Schedule");
      addToSchduleBtn.setMargin(new Insets(0,0,0,0));
      addToSchduleBtn.setBounds(descriptionPanel.getWidth()-142, descriptionPanel.getHeight() - 23, 140, 20);
      descriptionPanel.add(addToSchduleBtn);
      addToSchduleBtn.addActionListener(
              new ActionListener() {
                  public void actionPerformed(ActionEvent event) {
                    activateSchedule();
                  }
              });
      
      cancelBtn = new JButton("Cancel");
      cancelBtn.setMargin(new Insets(0,0,0,0));
      cancelBtn.setBounds(151, descriptionPanel.getHeight() - 23, 100, 20);
      descriptionPanel.add(cancelBtn);
      cancelBtn.addActionListener(
              new ActionListener() {
                  public void actionPerformed(ActionEvent event) {
                    cancelClick();
                  }
              });
      
      
      remFrSchduleBtn = new JButton("Remove from Schedule");
      remFrSchduleBtn.setMargin(new Insets(0,0,0,0));
      remFrSchduleBtn.setBounds(2, descriptionPanel.getHeight() - 23, 140, 20);
      descriptionPanel.add(remFrSchduleBtn);
      remFrSchduleBtn.addActionListener(
              new ActionListener() {
                  public void actionPerformed(ActionEvent event) {
                    removeEvent();
                  }
              });
      
      //----------------------------------------------------------
      //                        Top Panel
      //----------------------------------------------------------
      
      //Top Seperator
      JSeparator topSep = new JSeparator();
      topSep.setBounds(0,20, topPanel.getWidth(), 10);
      topPanel.add(topSep);
      
      
      //----------------------------------------------------------
      //                       Bottom Panel
      //----------------------------------------------------------
      finalizeButton = new JButton("Finalize Trip");
      finalizeButton.setMargin(new Insets(0,0,0,0));
      finalizeButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      finalizeButton.setBounds(bottomPanel.getWidth() - 126, 2, 120, 20);
      bottomPanel.add(finalizeButton);
      finalizeButton.addActionListener(
              new ActionListener() {
                  public void actionPerformed(ActionEvent event) {
                    Engine.GUI.changePanel("FinishScreen");
                  }
              });
      
      //Bottom Seperator
      JSeparator bottomSep = new JSeparator();
      bottomSep.setBounds(0,0, bottomPanel.getWidth(), 10);
      bottomPanel.add(bottomSep);
      
      //Remaining Funds Icon
      remFundsLabel = new JLabel();
      remFundsLabel.setBounds(6, 2, 200, 20);
      
      remFundsLabel.setFont(new Font("Arial",14, 14));
      remFundsLabel.setText("Remaining Funds: $"+Engine.Schedule.currentFunds+".00");
      if(Engine.Schedule.currentFunds < 0)
      {
        remFundsLabel.setForeground(Color.red);
        finalizeButton.setEnabled(false);
        finalizeButton.setText("Over Budget");
      }
      else
      {
        remFundsLabel.setForeground(new Color(0,128,0));
        finalizeButton.setEnabled(true);
        finalizeButton.setText("Finalize Trip");
      }
      bottomPanel.add(remFundsLabel);
      
      
      cancelClick();
      this.setVisible(true);
  }
  
  private void setSelectedEvent(Event e)
  {
    if(e == null)
    {
      selectedEvent = null;
      eventLabel.setText("No Event Selected");
      descriptionLabel.setText("Select an event on the left to begin!");
      ImageIcon img = new ImageIcon("NoEvent.jpg","No Event");
      imageLabel.setIcon(img);
      timeLabel.setText("Time Required: N/A");
      costLabel.setText("Cost: $0.00");
      remFrSchduleBtn.setVisible(false);
      addToSchduleBtn.setVisible(false);
      cancelBtn.setVisible(false);
    }
    else
    {
      selectedEvent = e;
      eventLabel.setText(e.name);
      descriptionLabel.setText(e.description);
      
      ImageIcon img = new ImageIcon(e.image,e.name);
      imageLabel.setIcon(img);
      
      timeLabel.setText("Time Required: " + e.duration + "hrs");
      costLabel.setText("Cost: $" + e.cost + ".00");
      
      //cancelBtn.setVisible(true);
      
      if(e.hour == -1 && e.day == -1)
      {
        remFrSchduleBtn.setVisible(false);
        addToSchduleBtn.setVisible(true);
      }
      else
      {
        remFrSchduleBtn.setVisible(true);
        addToSchduleBtn.setVisible(false);
      }
    }   
  }
  
  public void scheduleClick(EventButton e)
  {
    if(selectedEvent != null && addMode == true && e.myEvent == null)
    {
      Engine.Schedule.schedule[e.dayIdx][e.hourIdx] = new Event(selectedEvent);
      
      e.setText(e.hourIdx + "PM: " + Engine.Schedule.schedule[e.dayIdx][e.hourIdx].name);
      Engine.Schedule.schedule[e.dayIdx][e.hourIdx].hour = e.hourIdx;
      Engine.Schedule.schedule[e.dayIdx][e.hourIdx].day = e.dayIdx;
      
      Engine.Schedule.currentFunds -= Engine.Schedule.schedule[e.dayIdx][e.hourIdx].cost;
      
      remFundsLabel.setText("Remaining Funds: $"+Engine.Schedule.currentFunds + ".00");
      if(Engine.Schedule.currentFunds < 0)
      {
        remFundsLabel.setForeground(Color.red);
        finalizeButton.setEnabled(false);
        finalizeButton.setText("Over Budget");
      }
      else
      {
        remFundsLabel.setForeground(new Color(0,128,0));
        finalizeButton.setEnabled(true);
        finalizeButton.setText("Finalize Trip");
      }
      
      e.myEvent = Engine.Schedule.schedule[e.dayIdx][e.hourIdx];
      
      cancelClick();
    }
    else if(addMode == false)
    {
      setSelectedEvent(e.myEvent);
    }
  }
  
  public void removeEvent()
  {
    //Search our component list for the button in question.
    for(Component c : schedulePanel.getComponents())
    {
      if(c instanceof EventButton)
      {
        EventButton btn = (EventButton)c;
        if(btn.myEvent == selectedEvent)
        {
          //Refund cost of event.
          Engine.Schedule.currentFunds+=btn.myEvent.cost;
          
          //Remove linkages to the event (Java GC should deallocate it after this)
          Engine.Schedule.schedule[btn.dayIdx][btn.hourIdx] = null;
          btn.myEvent = null;
          
          //Update our text, and the text of the funds counter.
          int hour = (btn.hourIdx == 0 ? 12 : btn.hourIdx);
          btn.setText(hour + "PM: Nothing");
          
          remFundsLabel.setText("Remaining Funds: $"+Engine.Schedule.currentFunds + ".00");
          if(Engine.Schedule.currentFunds < 0)
          {
            remFundsLabel.setForeground(Color.red);
            finalizeButton.setEnabled(false);
            finalizeButton.setText("Over Budget");
          }
          else
          {
            remFundsLabel.setForeground(new Color(0,128,0));
            finalizeButton.setEnabled(true);
            finalizeButton.setText("Finalize Trip");
          }

          //Run cancel button code.
          cancelClick();
          break;
        }
      }
    }
  }
  
  public void cancelClick()
  {
    //TODO: Add logic here for which segments should still be disabled.
    addMode = false;
    setSelectedEvent(null);
    for(Component c : eventsPanel.getComponents())
    {
      c.setEnabled(true);
    }
    updateScheduleButtons(false);
  }
  
  
  public void activateSchedule()
  {
    addMode = true;
    descriptionLabel.setText("Select a time slot on the right to add this event to your schedule.");
    cancelBtn.setVisible(true);
    addToSchduleBtn.setVisible(false);
    //TODO: Add logic here for which segments should still be disabled.
    for(Component c : eventsPanel.getComponents())
    {
      c.setEnabled(false);
    }
    updateScheduleButtons(true);
  }
  
  private void updateScheduleButtons(boolean enabled)
  {
    Event cEvent = null;
    int idx = 0;
    for(Component c : schedulePanel.getComponents())
    {
      if(c instanceof EventButton)
      {
        EventButton btn = (EventButton)c;
        if(btn.myEvent == null)
        {
          //For timeslots following an event, check if they should be free or not.
          if(cEvent != null && btn.hourIdx < cEvent.hour + cEvent.duration && cEvent.day == btn.dayIdx)
          {
            btn.setText(btn.hourIdx + "PM:~ " +cEvent.name);
            c.setEnabled(false);
          }
          //If we've had an event. (days without events can be assumed to be free)
          else
          {
            //Free event slot.
            if(addMode)
            {
              boolean eventOK = true;
              //If we're in add mode, we need to pull the time of our event, and cycle through
              for(int idxB = btn.hourIdx; idxB < 8 && idxB < btn.hourIdx + selectedEvent.duration; idxB++)
              {
                  if(Engine.Schedule.schedule[btn.dayIdx][idxB] != null)
                  {
                    eventOK = false;
                    break;
                  }
              }
              
              if(btn.hourIdx + selectedEvent.duration > 8)
                eventOK = false;
        
              
              int hour = (btn.hourIdx == 0 ? 12 : btn.hourIdx);
              if(eventOK)
                btn.setText(hour + "PM: Nothing");
              c.setEnabled(eventOK);
            }
            else
            {
              int hour = (btn.hourIdx == 0 ? 12 : btn.hourIdx);
              btn.setText(hour + "PM: Nothing");
              c.setEnabled(enabled);
            }
          }
        }
        else
        {
          cEvent = btn.myEvent;
          if(addMode)
            c.setEnabled(false);
          else
            c.setEnabled(true);
        }
      }
      idx++;
    }
  }
  
  public void onClose()
  {
  }
}
