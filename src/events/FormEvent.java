/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package events;

import java.util.EventObject;

import model.BetModel;

public class FormEvent extends EventObject {

  // private int[] numbers;

  // private int luckyNumber;
  // private boolean superBet;
  // private int betAmount;

  private BetModel bet;

  public FormEvent(Object source) {
    super(source);
  }

  public FormEvent(Object source, int[] numbers, int luckyNumber, boolean superBet,
      int betAmount) {
    super(source);
    bet = new BetModel(numbers, luckyNumber, superBet, betAmount);
    // this.numbers = numbers;
    // this.luckyNumber = luckyNumber;
    // this.superBet = superBet;
    // this.betAmount = betAmount;
  }

  public FormEvent(Object source, int[] numbers, boolean superBet, int betAmount) {
    super(source);
    bet = new BetModel(numbers, superBet, betAmount);

  }

  public BetModel getUserBet() {
    return bet;
  }

}
