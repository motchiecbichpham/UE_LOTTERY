/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.BetModel;
import model.Database;
import model.ResultModel;
import utils.Constants;

public class Controller {
  Database db = new Database();

  public ArrayList<ResultModel> getResultModels() {
    return db.getResults();
  }

  public ResultModel getCurrentResult() {
    int len = db.getResults().size();
    if (len > 0) {
      return db.getResults().get(len - 1);
    }
    return null;
  }

  public BetModel getCurrentBet() {
    int len = db.getBets().size();
    if (len > 0) {
      return db.getBets().get(len - 1);
    }
    return null;
  }

  public ArrayList<HashMap<String, Object>> getHistories() {
    return db.getHistories();
  }

  private ArrayList<Integer> drawNumbers() {
    ArrayList<Integer> templateRegular = new ArrayList<>();
    ArrayList<Integer> regular = new ArrayList<>();
    for (int i = Constants.REGULAR_LOWER_LIMIT; i <= Constants.REGULAR_UPPER_LIMIT; i++)
      templateRegular.add(i);

    Random rng = new Random();
    for (int i = 0; i < 4; i++) {
      int rndIndex = rng.nextInt(templateRegular.size());
      regular.add(templateRegular.get(rndIndex));
      templateRegular.remove(rndIndex);
    }

    return regular;
  }

  private int drawLucky() {
    int luckyNumber = new Random().nextInt(Constants.LUCKY_UPPER_LIMIT) + Constants.LUCKY_LOWER_LIMIT;
    return luckyNumber;
  }

  private ArrayList<Integer> correctDrawNumber(BetModel b) {
    Random random = new Random();
    int randomNumber = random.nextInt(2) + 3;
    ArrayList<Integer> regular = new ArrayList<>();

    switch (randomNumber) {
      case 3:
        ArrayList<Integer> templateRegular = new ArrayList<>();
        for (int i = Constants.REGULAR_LOWER_LIMIT; i <= Constants.REGULAR_UPPER_LIMIT; i++)
          templateRegular.add(i);
        for (int i = 0; i < randomNumber; i++) {
          regular.add(b.getNumbers()[i]);
          templateRegular.remove(Integer.valueOf(b.getNumbers()[i]));
        }
        regular.add(templateRegular.get(new Random().nextInt(Constants.REGULAR_UPPER_LIMIT - randomNumber)));
        break;
      case 4:
        for (int i = 0; i < randomNumber; i++) {
          regular.add(b.getNumbers()[i]);
        }
        break;
      default:
        break;
    }
    return regular;
  }

  private int correctDrawLucky(BetModel b) {
    int luckyNumber = b.getLuckyNumber();
    if (luckyNumber == 0) {
      luckyNumber = new Random().nextInt(Constants.LUCKY_UPPER_LIMIT) + Constants.LUCKY_LOWER_LIMIT;
    }
    return luckyNumber;
  }

  public void calculateGain(BetModel betModel, Boolean isCorrectNumbers, Boolean isCorrectLucky) {
    BigInteger gain = new BigInteger("0");
    int count = 0;
    ArrayList<Integer> regular = isCorrectNumbers ? correctDrawNumber(betModel) : drawNumbers();
    ArrayList<Integer> temp_regular = new ArrayList<>(regular);
    BigInteger betAmount = new BigInteger(String.valueOf(betModel.getBetAmount()));
    int[] numbers = betModel.getNumbers();
    int resultLuckyNumber = isCorrectLucky ? correctDrawLucky(betModel) : drawLucky();

    boolean isSuperBet = betModel.isSuperBet();
    for (int i = 0; i < numbers.length; i++) {
      int index = temp_regular.indexOf(numbers[i]);
      if (index >= 0) {
        count++;
        temp_regular.remove(index);
      }
    }

    if (count == 3) {
      gain = new BigInteger(String.valueOf(Constants.CORRECT_3_NUMBERS)).multiply(betAmount);
    } else if (count == 4) {
      gain = new BigInteger(String.valueOf(Constants.CORRECT_4_NUMBERS)).multiply(betAmount);
    }

    if (isSuperBet) {
      int luckyNumber = betModel.getLuckyNumber();
      if (resultLuckyNumber == luckyNumber) {
        gain = gain.multiply(new BigInteger(String.valueOf(Constants.CORRECT_LUCKY)));
      }
    }
    ResultModel resultModel = new ResultModel(regular, resultLuckyNumber, gain, count);

    db.addResult(resultModel);
    db.addHistory(betModel, resultModel);
    db.addUserBet(betModel);

  }

  public ArrayList<Integer> generateNumbers(Boolean isGenerateLuckyNumber) {
    ArrayList<Integer> templateNumbers = new ArrayList<>();
    ArrayList<Integer> generatedNumbers = new ArrayList<>();
    for (int i = Constants.REGULAR_LOWER_LIMIT; i <= Constants.REGULAR_UPPER_LIMIT; i++) {
      templateNumbers.add(i);
    }
    Random rng = new Random();
    for (int i = 0; i < 4; i++) {
      int rndIndex = rng.nextInt(templateNumbers.size());
      generatedNumbers.add(templateNumbers.get(rndIndex));
      templateNumbers.remove(rndIndex);
    }
    if (isGenerateLuckyNumber) {
      generatedNumbers.add(new Random().nextInt(Constants.LUCKY_UPPER_LIMIT) + Constants.LUCKY_LOWER_LIMIT);
    }
    return generatedNumbers;
  }

}
