/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Utils {
  public void showExitConfirmDialog(Component parent) {
    int action = JOptionPane.showConfirmDialog(parent,
        "Do you confirm to exit the application, your history will be lost?",
        "Confirm exit", JOptionPane.OK_CANCEL_OPTION);

    if (action == JOptionPane.OK_OPTION) {
      System.exit(0);
    }
  }

}
