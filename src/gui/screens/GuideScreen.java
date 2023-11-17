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

public class GuideScreen extends Screen {
  private MyLabel titleLabel;
  private MyButton backButton;
  private MyButton playButton;
  private JTextArea guideText;
  private JScrollPane scrollPane;

  public GuideScreen() {
    titleLabel = new MyLabel("Instruction");
    titleLabel.setCustomFont(24, true);
    backButton = new MyButton("Back");
    playButton = new MyButton("Play");
    guideText = new JTextArea();
    scrollPane = new JScrollPane(guideText);
    scrollPane.setPreferredSize(new Dimension(500, 300));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    instructionArea();
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("WelcomeScreen");
      }
    });

    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        navigateTo("HomeScreen");
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

  private void instructionArea() {
    guideText.setText("Hello player! Welcome to Lottery Game."
        + "\n\nYou will have 2 options of bets, which could help you win more money."
        + "\n\n1. The <<BET>> game: You will choose 4 distinct numbers between 1 and 20 and bet some fee." +
        "\n\n- If you have found 3 correct numbers, you will get 5 times your bet amount." +
        "\n\n- If you have found 4 correct numbers, you will get 50 times." +

        "\n\n2. The <<SUPER BET>> game:  You will choose an additional lucky number between 1 and 10." +
        "\n\n- If you have found the correct lucky number, you will earn 5 times the payout obtained with the simple Bet game."
        +
        "\n\n- If not, you will receive the same payout as simple Bet game");
    guideText.setLineWrap(true);
    guideText.setWrapStyleWord(true);
    guideText.setEditable(false);
    guideText.setBorder(new EmptyBorder(20, 20, 20, 20));
    guideText.setForeground(Colors.primary5);
    guideText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
  }
}
