package gui.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import utils.Colors;

public class MyButton extends JButton {
  public MyButton(String text) {
    super(text);
    setFont(new Font("Times New Roman", Font.PLAIN, 16));
    setForeground(Colors.primary5);
    setPreferredSize(new Dimension(120, 40));
  }

  public MyButton(String text, int width) {
    super(text);
    setForeground(Colors.primary5);
    setPreferredSize(new Dimension(width, 40));
    setFont(new Font("Times New Roman", Font.PLAIN, 16));

  }

}
