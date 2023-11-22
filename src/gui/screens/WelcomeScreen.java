package gui.screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.components.MyButton;
import gui.components.MyLabel;
import navigation.Screen;
import utils.Constants;
import utils.Utils;

public class WelcomeScreen extends Screen {
  private MyLabel welcomeText;
  private MyButton playButton;
  private MyButton guideButton;
  private MyButton historyButton;
  private MyButton exitButton;

  private Utils utils;

  public WelcomeScreen() {
    welcomeText = new MyLabel(Constants.WELCOME_TITLE);
    welcomeText.setCustomFont(24, true);
    playButton = new MyButton(Constants.PLAY_BUTTON, 200);
    guideButton = new MyButton(Constants.GUIDE_BUTTON, 150);
    historyButton = new MyButton(Constants.HISTORY_BUTTON, 150);
    exitButton = new MyButton(Constants.EXIT_BUTTON, 150);

    utils = new Utils();
    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo(Constants.HOME_SCREEN);
      }
    });
    guideButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo(Constants.GUIDE_SCREEN);
      }
    });

    exitButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        utils.showExitConfirmDialog(WelcomeScreen.this);

      }

    });

    historyButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo(Constants.HISTORY_SCREEN);
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
    gc.insets = new Insets(0, 0, 120, 0);
    add(guideButton, gc);
    gc.insets = new Insets(0, 0, 240, 0);
    add(playButton, gc);
    gc.insets = new Insets(0, 0, -120, 0);
    add(exitButton,
        gc);
  }

}
