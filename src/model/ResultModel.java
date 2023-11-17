/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package model;

import java.util.ArrayList;
import java.util.Map;

public class ResultModel {
  private Map<String, ArrayList<Integer>> numbers;
  private int gain;

  public ResultModel() {
  }

  public ResultModel(Map<String, ArrayList<Integer>> numbers, int gain) {
    this.numbers = numbers;
    this.gain = gain;
  }

  public Map<String, ArrayList<Integer>> getNumbers() {
    return numbers;
  }

  public Integer getGain() {
    return gain;
  }

}
