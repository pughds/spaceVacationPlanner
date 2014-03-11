/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip.GUI.Panels;

import SpaceTrip.Engine;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Darrick
 */
public class EnterInfo extends Panel {

    //Java requires sub-classes create their own copies of constructors.
    public EnterInfo() {
    }
    private JTextField totalDaysBox;
    private JTextField availableFundsBox;
    private JTextField numberTravellersBox;
    private int numberTravellers = 0;
    private int totalDays = 0;
    private double availableFunds;

    public void OnClose()
    {
    }
    
    public void display() {

        //Set up core attributes
        setTitle("Information Entry");
        setSize(400, 245);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Create and set a clean content pane.
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        //Build content pane elements
        JLabel welcomeLabel = new JLabel("Enter your information:");
        welcomeLabel.setBounds(10, 10, 250, 24);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(welcomeLabel);

        JLabel DaysText = new JLabel("Total Days of vacation:");
        DaysText.setFont(new Font("Serif", Font.PLAIN, 20));
        DaysText.setBounds(10, 50, 200, 24);

        totalDaysBox = new JTextField(12);
        totalDaysBox.setBounds(290, 50, 100, 24);

        JLabel FundsText = new JLabel("Available Funds:");
        FundsText.setFont(new Font("Serif", Font.PLAIN, 20));
        FundsText.setBounds(10, 90, 200, 24);

        availableFundsBox = new JTextField(12);
        availableFundsBox.setBounds(290, 90, 100, 24);

        JLabel TravellersText = new JLabel("Total Travellers:");
        TravellersText.setFont(new Font("Serif", Font.PLAIN, 20));
        TravellersText.setBounds(10, 130, 200, 24);

        numberTravellersBox = new JTextField(12);
        numberTravellersBox.setBounds(290, 130, 100, 24);

        JButton OKButton = new JButton("OK");
        OKButton.setBounds(315, 170, 75, 24);
        panel.add(OKButton);
        OKButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (totalDaysBox.getText().compareTo("") == 0 || availableFundsBox.getText().compareTo("") == 0 || numberTravellersBox.getText().compareTo("") == 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Some fields were left blank.\nPlease fill out the form completely.");
                            return;
                        }
                        
                        try
                        {
                          int days = Integer.parseInt(totalDaysBox.getText());
                          int funds = Integer.parseInt(availableFundsBox.getText());
                          int people = Integer.parseInt(numberTravellersBox.getText());
                          
                          if(days <= 0 || funds <= 0)
                            throw new Exception();
                            
                          Engine.Schedule = new SpaceTrip.Schedule.ScheduleManager(days, funds);
                          Engine.Schedule.totalPeople = people;
                          Engine.GUI.changePanel("PlanningScreen");
                        }
                        catch(Exception e)
                        {
                          JOptionPane.showMessageDialog(new JFrame(), "Some fields were filled out incorrectly\nPlease check your entries and try again.");
                          return;
                        }
                    }
                });

        JButton CancelButton = new JButton("CANCEL");
        CancelButton.setBounds(10, 170, 80, 24);
        panel.add(CancelButton);
        CancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        Engine.GUI.changePanel("MainMenu");
                    }
                });



        //Add elements to the panel
        panel.add(DaysText);
        panel.add(totalDaysBox);
        panel.add(FundsText);
        panel.add(availableFundsBox);
        panel.add(TravellersText);
        panel.add(numberTravellersBox);

        this.setVisible(true);
    }
}
