/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package gui.screens;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.Controller;
import events.BackEventListener;

import gui.components.MyButton;
import gui.components.MyLabel;
import gui.components.MyTextField;

import model.BetModel;
import model.ResultModel;
import navigation.Screen;
import utils.Utils;

public class ResultScreen extends Screen {
  private MyLabel titleLabel;

  private MyLabel numbersLabel;
  private MyLabel luckyLabel;
  private MyLabel gainLabel;
  private MyLabel currencyLabel;

  private MyTextField number;
  private MyTextField lucky;
  private MyTextField gain;

  private MyButton continueButton;
  private MyButton exiButton;
  private MyButton historyButton;

  private ResultModel currentResult;
  private BetModel currentBet;
  private BackEventListener backEventListener;
  private Utils utils;

  private Controller controller;

  public ResultScreen() {
    titleLabel = new MyLabel("Result for your last draw.");
    titleLabel.setCustomFont(24, true);
    numbersLabel = new MyLabel("Normal numbers");
    luckyLabel = new MyLabel("Lucky number");
    gainLabel = new MyLabel("Gain = ");
    currencyLabel = new MyLabel("€");

    lucky = new MyTextField();
    lucky.setEditable(false);
    gain = new MyTextField(40, 120);
    gain.setEditable(false);

    continueButton = new MyButton("Re-play");
    exiButton = new MyButton("Exit");
    historyButton = new MyButton("History");

    currentResult = new ResultModel();
    currentBet = new BetModel();

    utils = new Utils();

    controller = new Controller();

    continueButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (backEventListener != null) {
          backEventListener.backEventListener();
        }
      }

    });

    exiButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        utils.showExitConfirmDialog(ResultScreen.this);
      }

    });

    historyButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("HistoryScreen");
      }

    });

  }

  public void setBackEventListener(BackEventListener listener) {
    this.backEventListener = listener;
  }

  public void setData(ResultModel rs, BetModel b) {
    this.currentResult = rs;
    this.currentBet = b;
  }

  @Override
  public void init() {
    super.init();
    System.out.println(controller.getCurrentResult());
    layoutComponents(currentResult, currentBet);
  }

  private void layoutComponents(ResultModel rs, BetModel b) {
    setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.weightx = 1;
    gc.weighty = 1;
    gc.gridy = 0;
    gc.gridx = 0;
    gc.anchor = GridBagConstraints.FIRST_LINE_START;
    add(titleLabel, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.FIRST_LINE_END;
    add(historyButton, gc);

    gc.gridy++;
    gc.gridx = 0;
    gc.anchor = GridBagConstraints.LINE_START;
    add(numbersLabel, gc);
    gc.gridy++;
    ArrayList<Integer> regular = rs.getNumbers().get("regular");
    for (int i = 0; i < regular.size(); i++) {
      number = new MyTextField();
      number.setText(regular.get(i).toString());
      number.setEditable(false);
      gc.insets = new Insets(0, 48 * i, 0, 0);
      add(number, gc);
    }

    gc.gridy--;
    gc.gridx = 1;
    gc.insets = new Insets(0, 48, 0, 0);
    add(luckyLabel, gc);
    gc.gridy++;
    lucky.setText(rs.getNumbers().get("lucky").get(0).toString());
    add(lucky, gc);

    gc.gridx = 0;
    gc.gridy++;
    gc.anchor = GridBagConstraints.LINE_START;
    gc.insets = new Insets(0, 0, 0, 0);
    add(gainLabel, gc);
    gain.setText(rs.getGain().toString());
    gc.insets = new Insets(0, 48, 0, 0);
    add(gain, gc);
    gc.insets = new Insets(0, 180, 0, 0);
    add(currencyLabel, gc);

    gc.insets = new Insets(0, 0, 0, 0);
    gc.gridy++;
    gc.anchor = GridBagConstraints.LAST_LINE_START;
    add(exiButton, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    add(continueButton, gc);
  }

}
