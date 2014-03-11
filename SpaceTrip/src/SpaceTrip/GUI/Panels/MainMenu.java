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
 * @author Scott
 */
public class MainMenu extends Panel
{ 
  
  //Java requires sub-classes create their own copies of constructors.
  public MainMenu() { }
  
  public void display()
  {
      //Set up core attributes
      setTitle("Space Trip Planner");
      setSize(325, 220);
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      //Create and set a clean content pane.
      JPanel panel = new JPanel();
      setContentPane(panel);
      panel.setLayout(null);
      

      //Build content pane elements
      JButton newButton = new JButton("Begin Planning");
      newButton.setBounds(119, 160, 184, 24);
      panel.add(newButton);
      newButton.addActionListener(
              new ActionListener() {
                  public void actionPerformed(ActionEvent event) {
                      Engine.GUI.changePanel("EnterInfo");
                  }
              });
      
      ImageIcon img = new ImageIcon("intro.jpg");
      JLabel imageLabel = new JLabel(img);
      imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      imageLabel.setBounds(4,4,this.getWidth() - 14, 150);
      panel.add(imageLabel);
      
      JButton quitButton = new JButton("Quit");
      quitButton.setBounds(10, 160, 80, 24);
      panel.add(quitButton);
      quitButton.addActionListener(
              new ActionListener() {
                  public void actionPerformed(ActionEvent event) {
                      Engine.GUI.changePanel("");
                  }
              });

      quitButton.getActionMap().put("Quit", new AbstractAction("NewCount") {  
          public void actionPerformed(ActionEvent e) {
            //TODO: This throws an error at the moment.  Need to add a generalized exit. command.
            Engine.GUI.changePanel("");
          }
      });
      
      //Perform the action under the key 'NewCount' when the key is pressed.
      newButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "NewCount"); 
      quitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Quit");  
      
      this.setVisible(true);
  }
}
