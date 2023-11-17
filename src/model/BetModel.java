/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package model;

public class BetModel {
  private int[] numbers;
  private int luckyNumber;
  private boolean isSuperBet;
  private int betAmount;

  public BetModel() {
  }

  public BetModel(int[] numbers, boolean superBet, int betAmount) {
    this.numbers = numbers;
    this.isSuperBet = superBet;
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

  public int getLuckyNumber() {
    return luckyNumber;
  }

  public boolean isSuperBet() {
    return isSuperBet;
  }

  public int getBetAmount() {
    return betAmount;
  }

  @Override
  public String toString() {
    String st = "Your current bet numbers are: ";

    for (int i : numbers) {
      st += i + ", ";
    }
    if (isSuperBet) {

      st += "with " + luckyNumber + " as lucky number";
    } else {
      st += "without lucky number.";
    }
    st += "\n\n And you bet " + betAmount;

    return st;
  }
}
