/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package gui.screens;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;

import controller.Controller;
import events.FormEvent;
import events.FormListener;
import gui.components.MyButton;
import gui.components.MyErrorLabel;
import gui.components.MyLabel;
import gui.components.MyTextField;
import navigation.Screen;
import utils.Validation;
import utils.BetAmountException;
import utils.ChosenNumberException;
import utils.LuckyNumberException;
import utils.Utils;

public class HomeScreen extends Screen {

  private MyLabel titleLabel;
  private MyLabel chooseLabel;
  private MyLabel extraChooseLabel;
  private MyLabel superBetLabel;
  private MyLabel amountBetLabel;
  private MyLabel currencyLabel;

  private MyErrorLabel errorNumbersLabel;
  private MyErrorLabel errorAmountBetLabel;
  private MyErrorLabel errorLuckyNumberLabel;

  private MyTextField[] numberFields;
  private MyTextField luckyNumberField;
  private MyTextField betAmount;

  private JCheckBox betCheckBox;

  private MyButton drawButton;
  private MyButton exitButton;
  private MyButton backButton;
  private MyButton generateButton;

  private FormListener formListener;
  private Validation validation;
  private Utils utils;
  private Controller controller;

  public HomeScreen() {
    Dimension dim = getPreferredSize();
    dim.width = 270;
    setPreferredSize(dim);
    setMinimumSize(dim);

    titleLabel = new MyLabel("Lottery game");
    titleLabel.setCustomFont(24, true);

    chooseLabel = new MyLabel("Choose your numbers");
    extraChooseLabel = new MyLabel("Extra number for super bet");
    superBetLabel = new MyLabel("Super bet ?");
    amountBetLabel = new MyLabel("Bet = ");
    currencyLabel = new MyLabel("€");

    errorNumbersLabel = new MyErrorLabel("");
    errorAmountBetLabel = new MyErrorLabel("");
    errorLuckyNumberLabel = new MyErrorLabel("");

    numberFields = new MyTextField[4];
    luckyNumberField = new MyTextField();
    betAmount = new MyTextField(40, 120);

    betCheckBox = new JCheckBox();

    drawButton = new MyButton("Draw numbers");
    exitButton = new MyButton("Exit", 60);
    backButton = new MyButton("Back to Home");
    generateButton = new MyButton("Generate");

    validation = new Validation();
    utils = new Utils();

    // set up lucky bet
    extraChooseLabel.setEnabled(false);
    luckyNumberField.setEnabled(false);
    betCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isTicked = betCheckBox.isSelected();
        extraChooseLabel.setEnabled(isTicked);
        luckyNumberField.setEnabled(isTicked);
      }

    });

    generateButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        controller = new Controller();
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
            boolean superBet = betCheckBox.isSelected();
            int luckyNumber;
            int bet = Integer.parseInt(betAmount.getText());
            FormEvent ev;

            if (superBet) {
              if (validation.validateLuckyNumber(luckyNumberField)) {
                luckyNumber = Integer.parseInt(luckyNumberField.getText());
                ev = new FormEvent(this, numbers, luckyNumber,
                    superBet, bet);
                if (formListener != null) {
                  formListener.formEventOccurred(ev);
                }
              }
            } else {
              ev = new FormEvent(this, numbers, superBet, bet);
              if (formListener != null) {
                formListener.formEventOccurred(ev);
              }
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
        navigateTo("WelcomeScreen");
      }

    });

  }

  @Override
  public void init() {
    super.init();
    layoutComponents();
  }

  private void handleChosenNumberException(ChosenNumberException ex) {
    errorNumbersLabel.setText("*" + ex.getMessage());
  }

  private void handleBetAmountException(BetAmountException ex) {
    errorAmountBetLabel.setText("*" + ex.getMessage());
  }

  private void handleLuckyNumberException(LuckyNumberException ex) {
    errorLuckyNumberLabel.setText("*" + ex.getMessage());

  }

  private void layoutComponents() {
    setLayout(new GridBagLayout());

    GridBagConstraints gc = new GridBagConstraints();
    gc.weightx = 1;
    gc.weighty = 0.1;
    gc.gridy = 0;
    gc.anchor = GridBagConstraints.FIRST_LINE_START;
    add(titleLabel, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.FIRST_LINE_END;
    add(exitButton, gc);
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

    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    add(betCheckBox, gc);
    gc.insets = new Insets(0, 32, 0, 0);
    add(superBetLabel, gc);

    gc.gridx = 0;
    gc.gridy++;
    gc.insets = new Insets(0, 0, 0, 0);
    add(amountBetLabel, gc);
    gc.insets = new Insets(0, 48, 0, 0);
    add(betAmount, gc);
    gc.insets = new Insets(0, 180, 0, 0);
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
    gc.anchor = GridBagConstraints.LAST_LINE_START;
    add(backButton, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    add(drawButton, gc);
  }

  public void setFormListener(FormListener listener) {
    this.formListener = listener;
  }

  public void clearForm() {
    for (int i = 0; i < numberFields.length; i++) {
      numberFields[i].setText("");
    }
    luckyNumberField.setText("");
    betAmount.setText("");
    betCheckBox.setSelected(false);
    resetErrorLabel();
    extraChooseLabel.setEnabled(false);
    luckyNumberField.setEnabled(false);

  }

  private void resetErrorLabel() {
    errorNumbersLabel.setText("");
    errorAmountBetLabel.setText("");
    errorLuckyNumberLabel.setText("");
  }
}
