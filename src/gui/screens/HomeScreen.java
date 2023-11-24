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

import javax.swing.JCheckBox;

import controller.Controller;
import exception.BetAmountException;
import exception.ChosenNumberException;
import exception.LuckyNumberException;
import gui.components.MyButton;
import gui.components.MyErrorLabel;
import gui.components.MyLabel;
import gui.components.MyTextField;
import model.BetModel;
import navigation.Screen;
import utils.Validation;
import utils.Constants;
import utils.Utils;

public class HomeScreen extends Screen {

  private MyLabel titleLabel;
  private MyLabel chooseLabel;
  private MyLabel extraChooseLabel;
  private MyLabel superBetLabel;
  private MyLabel amountBetLabel;
  private MyLabel currencyLabel;
  private MyLabel correctNumbersTestLabel;
  private MyLabel correctLuckyTestLabel;

  private MyErrorLabel errorNumbersLabel;
  private MyErrorLabel errorAmountBetLabel;
  private MyErrorLabel errorLuckyNumberLabel;

  private MyTextField[] numberFields;
  private MyTextField luckyNumberField;
  private MyTextField betAmount;

  private JCheckBox betCheckBox;
  private JCheckBox correctNumberTestBox;
  private JCheckBox correctLuckyTestBox;

  private MyButton drawButton;
  private MyButton exitButton;
  private MyButton backButton;
  private MyButton generateButton;

  private Validation validation;
  private Utils utils;
  private Controller controller;

  public HomeScreen(Controller c) {
    this.controller = c;

    titleLabel = new MyLabel(Constants.HOME_TITLE);
    titleLabel.setCustomFont(24, true);

    chooseLabel = new MyLabel(Constants.CHOOSE_NUMBERS);
    extraChooseLabel = new MyLabel(Constants.CHOOSE_LUCKY_NUMBER);
    superBetLabel = new MyLabel(Constants.SUPER_BET);
    amountBetLabel = new MyLabel(Constants.BET_AMOUNT);
    currencyLabel = new MyLabel(Constants.CURRENCY);
    correctNumbersTestLabel = new MyLabel(Constants.NUMBERS_CORRECT_TEST);
    correctLuckyTestLabel = new MyLabel(Constants.LUCKY_CORRECT_TEST);

    errorNumbersLabel = new MyErrorLabel("");
    errorAmountBetLabel = new MyErrorLabel("");
    errorLuckyNumberLabel = new MyErrorLabel("");

    numberFields = new MyTextField[4];
    luckyNumberField = new MyTextField();
    betAmount = new MyTextField(40, 120);

    betCheckBox = new JCheckBox();
    correctNumberTestBox = new JCheckBox();
    correctLuckyTestBox = new JCheckBox();

    drawButton = new MyButton(Constants.DRAW_BUTTON);
    exitButton = new MyButton(Constants.EXIT_BUTTON, 60);
    backButton = new MyButton(Constants.BACK_HOME_BUTTON);
    generateButton = new MyButton(Constants.GENERATE_BUTTON);

    validation = new Validation();
    utils = new Utils();

    // set up lucky bet
    extraChooseLabel.setEnabled(false);
    luckyNumberField.setEnabled(false);
    correctLuckyTestBox.setEnabled(false);
    correctLuckyTestLabel.setEnabled(false);
    betCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isTicked = betCheckBox.isSelected();
        extraChooseLabel.setEnabled(isTicked);
        luckyNumberField.setEnabled(isTicked);
        correctLuckyTestBox.setEnabled(isTicked);
        correctLuckyTestLabel.setEnabled(isTicked);
      }

    });

    generateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ArrayList<Integer> generatedNumbers = controller.generateNumbers(betCheckBox.isSelected());
        for (int i = 0; i < numberFields.length; i++) {
          numberFields[i].setText(generatedNumbers.get(i).toString());
        }
        if (betCheckBox.isSelected()) {
          luckyNumberField.setText(generatedNumbers.get(generatedNumbers.size() - 1).toString());
        }
      }

    });

    drawButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetErrorLabel();
        int[] numbers = new int[4];
        try {
          if (validation.validateEnteredNumber(numberFields) && validation.validateEnteredAmount(betAmount)) {
            for (int i = 0; i < numberFields.length; i++) {
              numbers[i] = Integer.parseInt(numberFields[i].getText());
            }
            boolean isSuperBet = betCheckBox.isSelected();
            int luckyNumber;
            int bet = Integer.parseInt(betAmount.getText());
            BetModel betModel;

            if (isSuperBet) {
              if (validation.validateLuckyNumber(luckyNumberField)) {
                luckyNumber = Integer.parseInt(luckyNumberField.getText());
                betModel = new BetModel(numbers, luckyNumber, isSuperBet, bet);
                submit(betModel);
              }
            } else {
              betModel = new BetModel(numbers, isSuperBet, bet);
              submit(betModel);
            }
          }
        } catch (ChosenNumberException ex) {
          handleChosenNumberException(ex);
        } catch (BetAmountException ex) {
          handleBetAmountException(ex);
        } catch (LuckyNumberException ex) {
          handleLuckyNumberException(ex);
        }

      }

    });

    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        utils.showExitConfirmDialog(HomeScreen.this);
      }
    });

    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clearForm();
        navigateTo(Constants.WELCOME_SCREEN);
      }

    });

  }

  @Override
  public void init() {
    super.init();
    layoutComponents();
  }

  private void clearForm() {
    for (int i = 0; i < numberFields.length; i++) {
      numberFields[i].setText("");
    }
    luckyNumberField.setText("");
    betAmount.setText("");
    betCheckBox.setSelected(false);
    extraChooseLabel.setEnabled(false);
    luckyNumberField.setEnabled(false);
    correctLuckyTestBox.setSelected(false);
    correctNumberTestBox.setSelected(false);
    correctLuckyTestBox.setEnabled(false);
    correctLuckyTestLabel.setEnabled(false);
    resetErrorLabel();

  }

  private void resetErrorLabel() {
    errorNumbersLabel.setText("");
    errorAmountBetLabel.setText("");
    errorLuckyNumberLabel.setText("");
  }

  private void submit(BetModel betModel) {
    Boolean isCorrectNumbersTest = false;
    Boolean isCorrectLuckyTest = false;
    if (correctLuckyTestBox.isSelected()) {
      isCorrectLuckyTest = true;
    }
    if (correctNumberTestBox.isSelected()) {
      isCorrectNumbersTest = true;
    }
    controller.calculateGain(betModel, isCorrectNumbersTest, isCorrectLuckyTest);
    clearForm();
    navigateTo(Constants.RESULT_SCREEN);
  }

  private void handleChosenNumberException(ChosenNumberException ex) {
    errorNumbersLabel.setText(ex.getMessage());
  }

  private void handleBetAmountException(BetAmountException ex) {
    errorAmountBetLabel.setText(ex.getMessage());
  }

  private void handleLuckyNumberException(LuckyNumberException ex) {
    errorLuckyNumberLabel.setText(ex.getMessage());

  }

  private void layoutComponents() {
    setLayout(new GridBagLayout());

    GridBagConstraints gc = new GridBagConstraints();
    gc.weightx = 1;
    gc.weighty = 0.1;
    // title
    gc.gridy = 0;
    gc.anchor = GridBagConstraints.FIRST_LINE_START;
    add(titleLabel, gc);

    // exit button
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.FIRST_LINE_END;
    add(exitButton, gc);

    // choose numbers
    gc.gridy = 1;
    gc.gridx = 0;
    gc.anchor = GridBagConstraints.LINE_START;
    add(chooseLabel, gc);
    gc.gridy = 2;
    for (int i = 0; i < numberFields.length; i++) {
      numberFields[i] = new MyTextField();
      gc.insets = new Insets(0, 48 * i, 0, 0);
      add(numberFields[i], gc);
    }

    // choose lucky number
    gc.gridx = 1;
    gc.gridy = 1;
    gc.insets = new Insets(0, 60, 0, 0);
    add(extraChooseLabel, gc);
    gc.gridy++;
    add(luckyNumberField, gc);
    gc.gridy++;
    add(errorLuckyNumberLabel, gc);

    gc.gridx = 0;
    gc.insets = new Insets(0, 0, 0, 0);
    add(errorNumbersLabel, gc);

    // super bet box
    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    add(betCheckBox, gc);
    gc.insets = new Insets(0, 32, 0, 0);
    add(superBetLabel, gc);

    // bet amount
    gc.gridx = 0;
    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    add(amountBetLabel, gc);
    gc.insets = new Insets(0, 90, 0, 0);
    add(betAmount, gc);
    gc.insets = new Insets(0, 214, 0, 0);
    add(currencyLabel, gc);

    gc.gridx = 1;
    gc.anchor = GridBagConstraints.LINE_END;
    add(generateButton, gc);
    gc.gridx = 0;
    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    gc.anchor = GridBagConstraints.LINE_START;
    add(errorAmountBetLabel, gc);

    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    add(correctNumberTestBox, gc);
    gc.insets = new Insets(0, 32, 0, 0);
    add(correctNumbersTestLabel, gc);
    gc.gridx++;
    gc.insets = new Insets(0, 60, 0, 0);
    add(correctLuckyTestBox, gc);
    gc.insets = new Insets(0, 92, 0, 0);
    add(correctLuckyTestLabel, gc);
    // bottom
    gc.gridy++;
    gc.gridx = 0;
    gc.insets = new Insets(0, 0, 0, 0);
    gc.anchor = GridBagConstraints.LAST_LINE_START;
    add(backButton, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    add(drawButton, gc);
  }

}
