/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package utils;

import java.util.HashSet;

import javax.swing.JTextField;

public class Validation {
  public boolean validateEnteredNumber(JTextField[] input) throws ChosenNumberException {
    boolean isValid = true;
    HashSet<Integer> values = new HashSet<>();
    for (int i = 0; i < input.length; i++) {
      String text = input[i].getText().trim();
      if (text.isEmpty()) {
        isValid = false;
        throw new ChosenNumberException("The number " + (i + 1) + " can not be empty");

      }

      try {
        int value = Integer.parseInt(text);
        if (value < 1 || value > 20) {
          isValid = false;
          throw new ChosenNumberException("The number " + (i + 1) + " out of range from 1 to 20.");

        }
        if (values.contains(value)) {
          isValid = false;
          throw new ChosenNumberException("The number " + (i + 1) + " is a duplicate value.");

        } else {
          values.add(value);
        }
      } catch (NumberFormatException ex) {
        isValid = false;
        throw new ChosenNumberException("The number " + (i + 1) + " is not a valid integer.");

      }
    }

    return isValid;
  }

  public boolean validateEnteredAmount(JTextField input) throws BetAmountException {
    boolean isValid = true;
    String text = input.getText().trim();
    if (text.isEmpty()) {
      isValid = false;
      throw new BetAmountException("Bet amount can not be empty");
    } else {
      try {
        int value = Integer.parseInt(text);
        if (value <= 0) {
          isValid = false;
          throw new BetAmountException("The bet amount have to be greater than 0");

        }
      } catch (NumberFormatException ex) {
        isValid = false;
        throw new BetAmountException("Bet amount is not a valid integer.");
      }
    }

    return isValid;
  }

  public boolean validateLuckyNumber(JTextField input) throws LuckyNumberException {
    boolean isValid = true;
    String text = input.getText().trim();
    if (text.isEmpty()) {
      isValid = false;
      throw new LuckyNumberException("The lucky number can not be empty.");

    }

    try {
      int value = Integer.parseInt(text);
      if (value < 1 || value > 10) {
        isValid = false;
        throw new LuckyNumberException("The lucky number out of range from 1 to 10.");

      }

    } catch (NumberFormatException ex) {
      isValid = false;
      throw new LuckyNumberException("The lucky number is not a valid integer.");

    }
    return isValid;
  }

}
