package gui.components;

import java.awt.Font;

import javax.swing.JLabel;

import utils.Colors;
import utils.Constants;

public class MyLabel extends JLabel {
  public MyLabel(String text) {
    super(text);
    setForeground(Colors.primary5);

  }

  public void setCustomFont(int fontsize, Boolean isBold) {
    setFont(new Font(Constants.FONT_NAME, isBold ? Font.BOLD : Font.PLAIN, fontsize));
  }

}
