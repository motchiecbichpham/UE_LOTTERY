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

import gui.components.MyButton;
import gui.components.MyLabel;
import gui.components.MyTextField;

import model.BetModel;
import model.ResultModel;
import navigation.Screen;
import utils.Constants;
import utils.Utils;

public class ResultScreen extends Screen {
  private MyLabel titleLabel;

  private MyLabel numbersLabel;
  private MyLabel luckyLabel;
  private MyLabel gainLabel;
  private MyLabel currencyLabel;
  private MyLabel userBetLabel;
  private MyLabel correctionLabel;

  private MyTextField number;
  private MyTextField lucky;
  private MyTextField gain;

  private MyButton continueButton;
  private MyButton exiButton;
  private MyButton historyButton;

  private ResultModel currentResult;
  private BetModel currentBet;
  private Utils utils;

  private Controller controller;

  public ResultScreen(Controller c) {
    this.controller = c;
    titleLabel = new MyLabel(Constants.RESULT_TITLE);
    titleLabel.setCustomFont(Constants.FONT_SIZE_LARGE, true);
    numbersLabel = new MyLabel(Constants.REGULAR_NUMBERS);
    luckyLabel = new MyLabel(Constants.LUCKY_NUMBER);
    gainLabel = new MyLabel(Constants.GAIN);
    currencyLabel = new MyLabel(Constants.CURRENCY);
    userBetLabel = new MyLabel("");
    correctionLabel = new MyLabel("");

    lucky = new MyTextField();
    lucky.setEditable(false);
    gain = new MyTextField(40, 120);
    gain.setEditable(false);

    continueButton = new MyButton(Constants.REPLAY_BUTTON);
    exiButton = new MyButton(Constants.EXIT_BUTTON);
    historyButton = new MyButton(Constants.HISTORY_BUTTON);

    currentResult = new ResultModel();
    currentBet = new BetModel();

    utils = new Utils();

    continueButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo(Constants.HOME_SCREEN);
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
        navigateTo(Constants.HISTORY_SCREEN);
      }

    });

  }

  @Override
  public void init() {
    super.init();
    this.currentResult = controller.getCurrentResult();
    this.currentBet = controller.getCurrentBet();
    layoutComponents(currentResult, currentBet);
  }

  private void layoutComponents(ResultModel rs, BetModel b) {
    setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.weightx = 1;
    gc.weighty = 0.1;
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
    ArrayList<Integer> regular = rs.getNumbers();
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
    lucky.setText(Integer.toString(rs.getLuckyNumber()));
    add(lucky, gc);

    gc.gridx = 0;
    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    userBetLabel.setText(b.parseToString());
    add(userBetLabel, gc);

    gc.gridy++;
    correctionLabel.setText(Constants.COUNT_CORRECTION + rs.getCountCorrection());
    add(correctionLabel, gc);

    gc.insets = new Insets(0, 0, 0, 0);
    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    add(gainLabel, gc);
    gain.setText(rs.getGain().toString());
    gc.insets = new Insets(0, 48, 0, 0);
    add(gain, gc);
    gc.insets = new Insets(0, 180, 0, 0);
    add(currencyLabel, gc);

    gc.insets = new Insets(0, 0, 0, 0);
    gc.gridy++;
    gc.gridx = 0;
    gc.anchor = GridBagConstraints.LAST_LINE_START;
    add(exiButton, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    add(continueButton, gc);
  }

}
