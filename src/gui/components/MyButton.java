package gui.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import utils.Colors;
import utils.Constants;

public class MyButton extends JButton {
  public MyButton(String text) {
    super(text);
    setFont(new Font(Constants.FONT_NAME, Font.PLAIN, Constants.FONT_SIZE_MEDIUM));
    setForeground(Colors.primary5);
    setPreferredSize(new Dimension(136, 48));
  }

  public MyButton(String text, int width) {
    super(text);
    setForeground(Colors.primary5);
    setPreferredSize(new Dimension(width, 48));
    setFont(new Font(Constants.FONT_NAME, Font.PLAIN, Constants.FONT_SIZE_MEDIUM));
  }
}
