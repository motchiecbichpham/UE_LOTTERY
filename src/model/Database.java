/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Constants;

public class Database {

  private ArrayList<ResultModel> results;
  private ArrayList<BetModel> bets;
  private ArrayList<HashMap<String, Object>> histories;

  public Database() {
    results = new ArrayList<ResultModel>();
    bets = new ArrayList<BetModel>();
    histories = new ArrayList<>();

  }

  public void addResult(ResultModel rs) {
    results.add(rs);
  }

  public void addUserBet(BetModel b) {
    bets.add(b);
  }

  public void addHistory(BetModel b, ResultModel rs) {
    HashMap<String, Object> history = new HashMap<>();
    history.put(Constants.TURN, histories.size() + 1);
    history.put(Constants.USER_BET, b);
    history.put(Constants.RESULT, rs);
    histories.add(history);

  }

  public ResultModel getCurrentResult() {
    return results.get(results.size() - 1);
  }

  public BetModel getCurrentBet() {
    return bets.get(bets.size() - 1);
  }

  public ArrayList<ResultModel> getResults() {
    return results;
  }

  public ArrayList<BetModel> getBets() {
    return bets;
  }

  public ArrayList<HashMap<String, Object>> getHistories() {
    return histories;
  }

}
