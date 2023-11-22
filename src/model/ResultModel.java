/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package model;

import java.math.BigInteger;
import java.util.ArrayList;

import utils.Constants;

public class ResultModel {
  private ArrayList<Integer> numbers;
  private int luckyNumber;
  private BigInteger gain;
  private int countCorrection;

  public ResultModel() {

  }

  public ResultModel(ArrayList<Integer> numbers, int luckyNumber, BigInteger gain, int countCorrection) {
    this.numbers = numbers;
    this.luckyNumber = luckyNumber;
    this.gain = gain;
    this.countCorrection = countCorrection;
  }

  public ArrayList<Integer> getNumbers() {
    return numbers;
  }

  public void setNumbers(ArrayList<Integer> numbers) {
    this.numbers = numbers;
  }

  public int getLuckyNumber() {
    return luckyNumber;
  }

  public void setLuckyNumber(int luckyNumber) {
    this.luckyNumber = luckyNumber;
  }

  public BigInteger getGain() {
    return gain;
  }

  public void setGain(BigInteger gain) {
    this.gain = gain;
  }

  public int getCountCorrection() {
    return countCorrection;
  }

  public void setCountCorrection(int countCorrection) {
    this.countCorrection = countCorrection;
  }

  @Override
  public String toString() {
    String st = Constants.NORMAL_NUMBERS;
    for (int i : numbers) {
      st += i + "  ";
    }
    st += "\n" + Constants.LUCKY_NUMBER + luckyNumber;
    return st;
  }
}
