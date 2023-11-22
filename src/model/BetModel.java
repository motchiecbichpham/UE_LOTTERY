/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package model;

import utils.Constants;

public class BetModel {
  private int[] numbers;
  private int luckyNumber;
  private boolean isSuperBet;
  private int betAmount;

  public BetModel() {
  }

  public BetModel(int[] numbers, boolean isSuperBet, int betAmount) {
    this.numbers = numbers;
    this.isSuperBet = isSuperBet;
    this.betAmount = betAmount;
  }

  public BetModel(int[] numbers, int luckyNumber, boolean isSuperBet, int betAmount) {
    this.numbers = numbers;
    this.luckyNumber = luckyNumber;
    this.isSuperBet = isSuperBet;
    this.betAmount = betAmount;
  }

  public int[] getNumbers() {
    return numbers;
  }

  public void setNumbers(int[] numbers) {
    this.numbers = numbers;
  }

  public int getLuckyNumber() {
    return luckyNumber;
  }

  public void setLuckyNumber(int luckyNumber) {
    this.luckyNumber = luckyNumber;
  }

  public boolean isSuperBet() {
    return isSuperBet;
  }

  public void setSuperBet(boolean isSuperBet) {
    this.isSuperBet = isSuperBet;
  }

  public int getBetAmount() {
    return betAmount;
  }

  @Override
  public String toString() {
    String st = Constants.NORMAL_NUMBERS;
    for (int i : numbers) {
      st += i + "  ";
    }
    if (isSuperBet) {
      st += "\n" + Constants.LUCKY_NUMBER + luckyNumber;
    } else {
      st += "\n" + Constants.NO_LUCKY;
    }
    st += "\n" + Constants.BET_AMOUNT + betAmount + Constants.CURRENCY;
    return st;
  }

  public String parseToString() {
    String st = "You bet " + betAmount + Constants.CURRENCY + " on ";
    for (int i : numbers) {
      st += i + ",  ";
    }
    if (isSuperBet) {
      st += Constants.WITH_LUCKY + luckyNumber;
    } else {
      st += Constants.WITHOUT_LUCKY;
    }
    return st;
  }
}
