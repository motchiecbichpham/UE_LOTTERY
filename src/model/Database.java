/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

  private ArrayList<ResultModel> results = new ArrayList<ResultModel>();
  private ArrayList<BetModel> bets = new ArrayList<BetModel>();
  private ArrayList<Map<String, Object>> histories = new ArrayList<>();

  public Database() {
    // results = ;
    // bets = ;
    // histories =;
  }

  public void addResult(ResultModel rs) {
    results.add(rs);
  }

  public void addUserBet(BetModel b) {
    bets.add(b);
  }

  public void addHistory(BetModel b, ResultModel rs) {
    Map<String, Object> history = new HashMap<>();
    history.put("userbet", b);
    history.put("result", rs);
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

  public ArrayList<Map<String, Object>> getHistories() {
    return histories;
  }

}
