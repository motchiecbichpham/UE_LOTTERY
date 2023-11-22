/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import utils.Constants;

public class HistoriesTableModel extends AbstractTableModel {

  private ArrayList<HashMap<String, Object>> histories;

  private String[] colNames = { Constants.TURN_COL, Constants.PLAYER_SUB_COL, Constants.RESULT_COL,
      Constants.CORRECTION_COL, Constants.GAIN_COL };

  public HistoriesTableModel() {
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public String getColumnName(int column) {
    return colNames[column];
  }

  public void setData(ArrayList<HashMap<String, Object>> histories) {
    this.histories = histories;
  }

  @Override
  public int getColumnCount() {
    return 5;
  }

  @Override
  public int getRowCount() {
    return histories.size();
  }

  @Override
  public Object getValueAt(int row, int col) {
    HashMap<String, Object> history = histories.get(row);
    BetModel b = (BetModel) history.get(Constants.USER_BET);
    ResultModel rs = (ResultModel) history.get(Constants.RESULT);
    switch (col) {
      case 0:
        return history.get(Constants.TURN);
      case 1:
        return b.toString();
      case 2:
        return rs.toString();
      case 3:
        return rs.getCountCorrection();
      case 4:
        return rs.getGain() + Constants.CURRENCY;
    }

    return null;
  }

}
