package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import utils.Colors;

public class MyTextField extends JTextField {
  public MyTextField(int height, int width) {
    super();
    setPreferredSize(new Dimension(width, height));
    layoutComponent();

  }

  public MyTextField() {
    super();
    setPreferredSize(new Dimension(40, 36));
    layoutComponent();

  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    if (enabled) {
      setBorder(new LineBorder(Colors.primary5));
    } else {
      setBorder(new LineBorder(Color.LIGHT_GRAY));
    }
  }

  private void layoutComponent() {
    LineBorder lineBorder = new LineBorder(Colors.primary5);
    setHorizontalAlignment(JTextField.CENTER);
    setBorder(lineBorder);
    setFont(new Font("Times New Roman", Font.PLAIN, 16));
    setForeground(Colors.primary5);
  }

}
