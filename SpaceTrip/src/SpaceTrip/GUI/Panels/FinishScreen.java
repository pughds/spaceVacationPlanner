/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceTrip.GUI.Panels;

import SpaceTrip.Engine;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Erik
 */
public class FinishScreen extends Panel {

    public FinishScreen() {
    }
    private JTextField remainingFundsBox;
    private double remainingFunds;

    public void display() {

        setTitle("Finish Screen");
        setSize(450, 180);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        //Display a "Congratulations" message. Possibly an image or large font text


        JLabel welcomeLabel = new JLabel("You are one step away from the trip of a lifetime!");
        welcomeLabel.setBounds(10, 10, 490, 24);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(welcomeLabel);
        
        JLabel welcomeLabel2 = new JLabel("Press FINISH to blast off, or CANCEL to plan more!");
        welcomeLabel2.setBounds(10, 34, 490, 24);
        welcomeLabel2.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(welcomeLabel2);
        
        JLabel totalCost = new JLabel("Total Vacation Cost: $" + (Engine.Schedule.totalFunds - Engine.Schedule.currentFunds) + ".00 for a " + Engine.Schedule.totalDays + " day trip for " + Engine.Schedule.totalPeople + " people.");
        totalCost.setBounds(10, 74, 490, 24);
        totalCost.setFont(new Font("Serif", Font.PLAIN, 16));
        panel.add(totalCost);
        
        JButton FinishButton = new JButton("FINISH");
        FinishButton.setBounds(360, 110, 75, 24);
        panel.add(FinishButton);
        FinishButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        //Exit program
                        Engine.GUI.changePanel("");
                    }
                });

        JButton CancelButton = new JButton("CANCEL");
        CancelButton.setBounds(10, 110, 80, 24);
        panel.add(CancelButton);
        CancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        Engine.GUI.changePanel("PlanningScreen");
                    }
                });

        this.setVisible(true);

    }
}
