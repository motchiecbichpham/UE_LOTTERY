package gui.screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.components.MyButton;
import gui.components.MyLabel;
import navigation.Screen;

public class WelcomeScreen extends Screen {
  private MyLabel welcomeText;
  private MyButton playButton;
  private MyButton guideButton;
  private MyButton historyButton;

  public WelcomeScreen() {
    welcomeText = new MyLabel("WELCOME TO LOTTERY BET");
    welcomeText.setCustomFont(24, true);
    playButton = new MyButton("PLAY GAME NOW", 180);
    guideButton = new MyButton("Read the instruction", 150);
    historyButton = new MyButton("See the history ", 150);

    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("HomeScreen");
      }
    });
    guideButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("GuideScreen");
      }
    });

    historyButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("HistoryScreen");
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
    gc.gridx = 0;
    gc.gridy = 0;
    gc.weightx = 1;
    gc.weighty = 0.1;
    add(welcomeText, gc);
    gc.gridy++;
    add(historyButton, gc);
    gc.insets = new Insets(0, 0, 100, 0);
    add(guideButton, gc);
    gc.insets = new Insets(0, 0, 200, 0);
    add(playButton, gc);
  }

}
