package gui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyErrorLabel extends JLabel {
  public MyErrorLabel(String text) {
    super(text);
    setForeground(Color.RED);
    setFont(new Font("Times New Roman", Font.ITALIC, 14));
  }
}
