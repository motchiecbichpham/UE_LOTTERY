/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package gui.screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import controller.Controller;
import gui.components.MyButton;
import gui.components.MyLabel;
import navigation.Screen;

public class HistoryScreen extends Screen {
  private MyLabel titleLabel;
  private MyButton backToHomeButton;
  private MyButton playButton;
  private ArrayList<Map<String, Object>> histories;
  private Controller controller;

  public HistoryScreen() {
    titleLabel = new MyLabel("History");
    titleLabel.setCustomFont(24, true);

    backToHomeButton = new MyButton("Back to Home");
    playButton = new MyButton("Play");
    histories = new ArrayList<>();

    controller = new Controller();

    backToHomeButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("WelcomeScreen");
      }

    });
    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("HomeScreen");
      }

    });

  }

  @Override
  public void init() {
    super.init();
    layoutComponent();

  }

  private void layoutComponent() {
    setLayout(new GridBagLayout());

    GridBagConstraints gc = new GridBagConstraints();
    gc.weightx = 1;
    gc.weighty = 0.2;
    gc.gridy = 0;
    gc.anchor = GridBagConstraints.FIRST_LINE_START;
    add(titleLabel, gc);
    gc.gridy++;
    gc.anchor = GridBagConstraints.LAST_LINE_START;
    add(backToHomeButton, gc);
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    add(playButton, gc);
  }

  public void setData(ArrayList<Map<String, Object>> histories) {
    this.histories = histories;
  }
}
