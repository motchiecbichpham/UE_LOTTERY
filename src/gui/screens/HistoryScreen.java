/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package gui.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.Controller;
import gui.components.MyButton;
import gui.components.MyLabel;
import model.HistoriesTableModel;
import navigation.Screen;
import utils.Colors;
import utils.Constants;

public class HistoryScreen extends Screen {
  private MyLabel titleLabel;
  private MyButton backToHomeButton;
  private MyButton playButton;
  private JTable historiesTable;
  private JScrollPane scrollPane;
  private HistoriesTableModel historiesTableModel;

  private Controller controller;

  public HistoryScreen(Controller controller) {
    this.controller = controller;
    titleLabel = new MyLabel(Constants.HISTORY_TITLE);
    titleLabel.setCustomFont(24, true);
    backToHomeButton = new MyButton(Constants.BACK_HOME_BUTTON);
    playButton = new MyButton(Constants.PLAY2_BUTTON);

    historiesTableModel = new HistoriesTableModel();
    historiesTable = new JTable(historiesTableModel);
    historiesTable.setRowHeight(80);

    JTableHeader headerTable = historiesTable.getTableHeader();
    headerTable.setResizingAllowed(false);
    headerTable.setFont(new Font(Constants.FONT_NAME, Font.BOLD, Constants.FONT_SIZE_SMALL));
    headerTable.setForeground(Colors.primary5);

    historiesTable.setFont(new Font(Constants.FONT_NAME, Font.PLAIN, Constants.FONT_SIZE_SMALL));
    historiesTable.setForeground(Colors.primary5);
    historiesTable.setCellSelectionEnabled(false);

    scrollPane = new JScrollPane(historiesTable);
    scrollPane.setPreferredSize(new Dimension(620, 360));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    backToHomeButton.addActionListener(new ActionListener() {
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
    historiesTableModel.setData(controller.getHistories());
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
    if (controller.getHistories().size() == 0) {
      MyLabel noDataLabel = new MyLabel(Constants.NO_DATA);
      noDataLabel.setCustomFont(16, false);
      add(noDataLabel, gc);
    } else {
      add(scrollPane, gc);

    }
    gc.gridy++;
    gc.anchor = GridBagConstraints.LAST_LINE_START;
    add(backToHomeButton, gc);
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    add(playButton, gc);

    TableColumn column0 = historiesTable.getColumnModel().getColumn(0);
    column0.setPreferredWidth(40);
    TableColumn column1 = historiesTable.getColumnModel().getColumn(1);
    column1.setPreferredWidth(180);
    column1.setCellRenderer(new TextAreaRenderer());
    TableColumn column2 = historiesTable.getColumnModel().getColumn(2);
    column2.setPreferredWidth(150);
    column2.setCellRenderer(new TextAreaRenderer());
    TableColumn column4 = historiesTable.getColumnModel().getColumn(4);
    column4.setPreferredWidth(120);

  }

  static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
    public TextAreaRenderer() {
      setLineWrap(true);
      setWrapStyleWord(true);
      setOpaque(true);
      setEditable(false);
      setBorder(new EmptyBorder(5, 5, 5, 5));
      setForeground(Colors.primary5);
      setFont(new Font(Constants.FONT_NAME, Font.PLAIN, Constants.FONT_SIZE_SMALL));

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
        int row, int column) {
      setText(value != null ? value.toString() : "");
      return this;
    }
  }

}
