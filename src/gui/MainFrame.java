/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.Controller;
import events.BackEventListener;
import events.FormEvent;
import events.FormListener;
import gui.screens.ResultScreen;
import gui.screens.WelcomeScreen;
import gui.screens.GuideScreen;
import gui.screens.HistoryScreen;
import gui.screens.HomeScreen;
import navigation.ScreenManager;

public class MainFrame extends JFrame {

  private WelcomeScreen welcomeScreen;
  private HomeScreen homeScreen;
  private ResultScreen resultScreen;
  private GuideScreen guideScreen;
  private HistoryScreen historyScreen;

  private Controller controller;
  private ScreenManager screenManager = new ScreenManager();

  public MainFrame() {

    welcomeScreen = new WelcomeScreen();
    homeScreen = new HomeScreen();
    resultScreen = new ResultScreen();
    guideScreen = new GuideScreen();
    historyScreen = new HistoryScreen();

    controller = new Controller();
    screenManager.addScreen(welcomeScreen, "WelcomeScreen");
    screenManager.addScreen(homeScreen, "HomeScreen");
    screenManager.addScreen(resultScreen, "ResultScreen");
    screenManager.addScreen(guideScreen, "GuideScreen");
    screenManager.addScreen(historyScreen, "HistoryScreen");

    setLayout(new BorderLayout());
    add(screenManager, BorderLayout.CENTER);
    setMinimumSize(new Dimension(600, 450));
    setMaximumSize(new Dimension(800, 600));
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    homeScreen.setFormListener(new FormListener() {
      @Override
      public void formEventOccurred(FormEvent e) {
        controller.calculateGain(e);
        System.out.println(controller.getCurrentResult());
        resultScreen.setData(controller.getCurrentResult(),
            controller.getCurrentBet());
        homeScreen.clearForm();
        homeScreen.navigateTo("ResultScreen");
      }

    });
    resultScreen.setBackEventListener(new BackEventListener() {
      @Override
      public void backEventListener() {
        homeScreen.clearForm();
        resultScreen.navigateTo("HomeScreen");
      }
    });

    setVisible(true);
  }

}
