package gui.components;

import java.awt.Font;

import javax.swing.JLabel;

import utils.Colors;

public class MyLabel extends JLabel {
  public MyLabel(String text) {
    super(text);

  }

  public void setCustomFont(int fontsize, Boolean isBold) {
    setForeground(Colors.primary5);
    setFont(new Font("Times New Roman", isBold ? Font.BOLD : Font.PLAIN, fontsize));
  }

}
