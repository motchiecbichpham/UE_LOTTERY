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
import gui.screens.ResultScreen;
import gui.screens.WelcomeScreen;
import gui.screens.GuideScreen;
import gui.screens.HistoryScreen;
import gui.screens.HomeScreen;
import navigation.ScreenManager;
import utils.Constants;

public class MainFrame extends JFrame {

  private WelcomeScreen welcomeScreen;
  private HomeScreen homeScreen;
  private ResultScreen resultScreen;
  private GuideScreen guideScreen;
  private HistoryScreen historyScreen;

  private Controller controller;
  private ScreenManager screenManager = new ScreenManager();

  public MainFrame() {

    controller = new Controller();
    welcomeScreen = new WelcomeScreen();
    homeScreen = new HomeScreen(controller);
    resultScreen = new ResultScreen(controller);
    guideScreen = new GuideScreen();
    historyScreen = new HistoryScreen(controller);

    screenManager.addScreen(welcomeScreen, Constants.WELCOME_SCREEN);
    screenManager.addScreen(homeScreen, Constants.HOME_SCREEN);
    screenManager.addScreen(resultScreen, Constants.RESULT_SCREEN);
    screenManager.addScreen(guideScreen, Constants.GUIDE_SCREEN);
    screenManager.addScreen(historyScreen, Constants.HISTORY_SCREEN);

    setLayout(new BorderLayout());
    add(screenManager, BorderLayout.CENTER);
    setSize(getPreferredSize());
    setMinimumSize(new Dimension(720, 540));
    setMaximumSize(new Dimension(720, 540));
    setSize(720, 540);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

}
