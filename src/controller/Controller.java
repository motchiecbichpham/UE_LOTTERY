/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import events.FormEvent;
import model.BetModel;
import model.Database;
import model.ResultModel;

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

  public ArrayList<Map<String, Object>> getHistories() {
    return db.getHistories();
  }

  private Map<String, ArrayList<Integer>> drawNumbers() {

    Map<String, ArrayList<Integer>> rs = new HashMap<>();
    ArrayList<Integer> templateResult = new ArrayList<>();

    for (int i = 1; i <= 20; i++)
      templateResult.add(i);
    rs.put("regular", new ArrayList<>());
    rs.put("lucky", new ArrayList<>());

    Random rng = new Random();
    for (int i = 0; i < 4; i++) {
      int rndIndex = rng.nextInt(templateResult.size());
      rs.get("regular").add(templateResult.get(rndIndex));
      templateResult.remove(rndIndex);
    }
    int luckyNumber = new Random().nextInt(9) + 1;
    rs.get("lucky").add(luckyNumber);
    return rs;
  }

  public void calculateGain(FormEvent e) {
    int gain = 0;
    int count = 0;
    Map<String, ArrayList<Integer>> rs = drawNumbers();
    BetModel userBet = e.getUserBet();
    int betAmount = userBet.getBetAmount();
    int[] numbers = userBet.getNumbers();
    ArrayList<Integer> regular = new ArrayList<>(rs.get("regular"));
    boolean superBet = userBet.isSuperBet();
    for (int i = 0; i < numbers.length; i++) {
      int index = regular.indexOf(numbers[i]);
      if (index >= 0) {
        count++;
        regular.remove(index);
      }
    }

    if (count == 3) {
      gain = betAmount * 5;
    } else if (count == 4) {
      gain = betAmount * 50;
    }

    if (superBet) {
      int luckyNumber = userBet.getLuckyNumber();
      if (rs.get("lucky").contains(luckyNumber)) {
        gain = gain * 5;
      }
    }
    ResultModel resultModel = new ResultModel(rs, gain);
    db.addResult(resultModel);
    db.addHistory(userBet, resultModel);
    db.addUserBet(userBet);
  }

  public ArrayList<Integer> generateNumbers(Boolean isGenerateLuckyNumber) {
    ArrayList<Integer> templateNumbers = new ArrayList<>();
    ArrayList<Integer> generatedNumbers = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      templateNumbers.add(i);
    }
    Random rng = new Random();
    for (int i = 0; i < 4; i++) {
      int rndIndex = rng.nextInt(templateNumbers.size());
      generatedNumbers.add(templateNumbers.get(rndIndex));
      templateNumbers.remove(rndIndex);
    }
    if (isGenerateLuckyNumber) {
      generatedNumbers.add(new Random().nextInt(9) + 1);
    }
    return generatedNumbers;
  }
}
