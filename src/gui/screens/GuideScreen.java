/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package gui.screens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gui.components.MyButton;
import gui.components.MyLabel;
import navigation.Screen;
import utils.Colors;
import utils.Constants;

public class GuideScreen extends Screen {
  private MyLabel titleLabel;
  private MyButton backButton;
  private MyButton playButton;
  private JTextArea guideText;
  private JScrollPane scrollPane;

  public GuideScreen() {
    titleLabel = new MyLabel(Constants.GUIDE_TITLE);
    titleLabel.setCustomFont(24, true);
    backButton = new MyButton(Constants.BACK_BUTTON);
    playButton = new MyButton(Constants.PLAY2_BUTTON);
    guideText = new JTextArea();

    scrollPane = new JScrollPane(guideText);
    scrollPane.setPreferredSize(new Dimension(620, 360));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    guideText.setText(Constants.GUIDE_TEXT);
    guideText.setLineWrap(true);
    guideText.setWrapStyleWord(true);
    guideText.setEditable(false);
    guideText.setBorder(new EmptyBorder(12, 12, 12, 12));
    guideText.setForeground(Colors.primary5);
    guideText.setFont(new Font(Constants.FONT_NAME, Font.PLAIN, Constants.FONT_SIZE_MEDIUM));
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo(Constants.WELCOME_SCREEN);
      }
    });

    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo(Constants.HOME_SCREEN);
      }
    });

  }

  @Override
  public void init() {
    super.init();
    layoutComponent();
  }

  private void layoutComponent() {
    setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.weightx = 1;
    gc.weighty = 0.2;
    gc.gridy = 0;
    gc.anchor = GridBagConstraints.FIRST_LINE_START;
    add(titleLabel, gc);
    gc.gridx = 0;
    gc.gridy = 3;
    gc.anchor = GridBagConstraints.CENTER;
    add(scrollPane, gc);
    gc.gridy++;
    gc.anchor = GridBagConstraints.LAST_LINE_START;
    add(backButton, gc);
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    add(playButton, gc);
  }

}
