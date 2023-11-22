/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package navigation;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ScreenManager extends JPanel {

  public ScreenManager() {
    setLayout(new CardLayout());
  }

  public void addScreen(Screen screen, String screenName) {
    add(screen, screenName);
  }

}
