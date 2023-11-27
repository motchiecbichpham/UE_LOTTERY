/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package utils;

import java.util.HashSet;

import javax.swing.JTextField;

import exception.BetAmountException;
import exception.ChosenNumberException;
import exception.LuckyNumberException;

public class Validation {
  public boolean validateEnteredNumber(JTextField[] input) throws ChosenNumberException {
    boolean isValid = true;
    HashSet<Integer> values = new HashSet<>();
    for (int i = 0; i < input.length; i++) {
      String text = input[i].getText().trim();
      if (text.isEmpty()) {
        isValid = false;
        throw new ChosenNumberException("* The number " + (i + 1) + " can not be empty");

      }

      try {
        int value = Integer.parseInt(text);
        if (value < Constants.REGULAR_LOWER_LIMIT || value > Constants.REGULAR_UPPER_LIMIT) {
          isValid = false;
          throw new ChosenNumberException("* The number " + (i + 1) + " out of range from "
              + Constants.REGULAR_LOWER_LIMIT + " to " + Constants.REGULAR_UPPER_LIMIT);
        }
        if (values.contains(value)) {
          isValid = false;
          throw new ChosenNumberException("* The number " + (i + 1) + " is a duplicate value.");

        } else {
          values.add(value);
        }
      } catch (NumberFormatException ex) {
        isValid = false;
        throw new ChosenNumberException("* The number " + (i + 1) + " is not a valid integer.");

      }
    }

    return isValid;
  }

  public boolean validateEnteredAmount(JTextField input) throws BetAmountException {
    boolean isValid = true;
    String text = input.getText().trim();
    if (text.isEmpty()) {
      isValid = false;
      throw new BetAmountException("* Bet amount can not be empty");
    } else {
      try {
        int value = Integer.parseInt(text);
        if (value <= Constants.BET_LOWER_LIMIT || value > Constants.BET_UPPER_LIMIT) {
          isValid = false;
          throw new BetAmountException("* Bet amount must be between 0 - 2 billion €");

        }
      } catch (NumberFormatException ex) {
        isValid = false;
        throw new BetAmountException("* Bet amount is not a valid integer.");
      }
    }

    return isValid;
  }

  public boolean validateLuckyNumber(JTextField input) throws LuckyNumberException {
    boolean isValid = true;
    String text = input.getText().trim();
    if (text.isEmpty()) {
      isValid = false;
      throw new LuckyNumberException("* Lucky number can not be empty.");

    }

    try {
      int value = Integer.parseInt(text);
      if (value < Constants.LUCKY_LOWER_LIMIT || value > Constants.LUCKY_UPPER_LIMIT) {
        isValid = false;
        throw new LuckyNumberException(
            "* Lucky number out of range from " + Constants.LUCKY_LOWER_LIMIT + " to " + Constants.LUCKY_UPPER_LIMIT);

      }

    } catch (NumberFormatException ex) {
      isValid = false;
      throw new LuckyNumberException("* Lucky number is not a valid integer.");
    }
    return isValid;
  }

}
