// /**
// * @author PHAMThiBich
// * @email thi-bich.pham@ut-capitole.fr
// * @desc [description]
// */
// package events;

// import java.util.EventObject;

// import model.BetModel;

// public class FormEvent extends EventObject {

// private BetModel bet;

// public FormEvent(Object source) {
// super(source);
// }

// public FormEvent(Object source, int[] numbers, int luckyNumber, boolean
// isSuperBet,
// int betAmount) {
// super(source);
// bet = new BetModel(numbers, luckyNumber, isSuperBet, betAmount);

// }

// public FormEvent(Object source, int[] numbers, boolean isSuperBet, int
// betAmount) {
// super(source);
// bet = new BetModel(numbers, isSuperBet, betAmount);

// }

// public BetModel getUserBet() {
// return bet;
// }

// }
