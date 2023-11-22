package gui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import utils.Constants;

public class MyErrorLabel extends JLabel {
  public MyErrorLabel(String text) {
    super(text);
    setForeground(Color.RED);
    setFont(new Font(Constants.FONT_NAME, Font.ITALIC, Constants.FONT_SIZE_SMALL));
  }
}
