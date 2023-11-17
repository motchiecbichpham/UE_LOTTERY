/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package navigation;

import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class Screen extends JPanel {
  public Screen() {
    Border innerBorder = BorderFactory.createTitledBorder("");
    Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    setBorder(new EmptyBorder(20, 20, 20, 20));

    addAncestorListener(new AncestorListener() {

      @Override
      public void ancestorAdded(AncestorEvent event) {
        init();
      }

      @Override
      public void ancestorRemoved(AncestorEvent event) {
        System.out.println("out");
      }

      @Override
      public void ancestorMoved(AncestorEvent event) {
        System.out.println("move");

      }

    });
  }

  public void navigateTo(String screenName) {
    CardLayout cl = (CardLayout) this.getParent().getLayout();
    cl.show(this.getParent(), screenName);
  }

  public void init() {
    removeAll();
    revalidate();
    repaint();
  }

}