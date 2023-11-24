/**
 * @author PHAMThiBich
 * @email thi-bich.pham@ut-capitole.fr
 * @desc [description]
 */
package utils;

public class Constants {
  // screen name
  public static final String HOME_SCREEN = "HomeScreen";
  public static final String GUIDE_SCREEN = "GuideScreen";
  public static final String RESULT_SCREEN = "ResultScreen";
  public static final String WELCOME_SCREEN = "WelcomeScreen";
  public static final String HISTORY_SCREEN = "HistoryScreen";

  // screen titles
  public static final String WELCOME_TITLE = "WELCOME TO LOTTERY BET";
  public static final String HISTORY_TITLE = "History";
  public static final String RESULT_TITLE = "Result for your last draw";
  public static final String HOME_TITLE = "Lottery game";
  public static final String GUIDE_TITLE = "Instruction";

  // buttons
  public static final String PLAY_BUTTON = "PLAY GAME NOW";
  public static final String PLAY2_BUTTON = "Play";
  public static final String REPLAY_BUTTON = "Re-play";
  public static final String GUIDE_BUTTON = "Instruction";
  public static final String HISTORY_BUTTON = "History";
  public static final String EXIT_BUTTON = "Exit";
  public static final String BACK_HOME_BUTTON = "Back to Home";
  public static final String DRAW_BUTTON = "Draw numbers";
  public static final String GENERATE_BUTTON = "Generate";
  public static final String BACK_BUTTON = "Back";

  // label
  public static final String REGULAR_NUMBERS = "Numbers: ";
  public static final String LUCKY_NUMBER = "Lucky number: ";
  public static final String GAIN = "Gain = ";
  public static final String CURRENCY = " €";
  public static final String TOTAL_GAIN = "Total gain: ";

  public static final String CHOOSE_NUMBERS = "Choose your numbers";
  public static final String CHOOSE_LUCKY_NUMBER = "Lucky number for super bet";
  public static final String SUPER_BET = "Super bet?";
  public static final String LUCKY_CORRECT_TEST = "Test correct lucky number?";
  public static final String NUMBERS_CORRECT_TEST = "Test correct numbers?";
  public static final String BET_AMOUNT = "Bet amount = ";
  public static final String NO_DATA = "You have no records of history. Play game now to have some :D";
  public static final String NO_LUCKY = "No lucky number";

  public static final String GUIDE_TEXT = "Hello DAVID! Welcome to Lottery Game."
      + "\n\nYou will have 2 options of bets, which could help you win more money."
      + "Additionally, you should notice that your bet amount must be between 1 euro and 2 billion euro and it have to be an integer."
      + "\n\n1. The <<BET>> game: You will choose 4 distinct numbers between 1 and 20 and bet some fee." +
      "\n\n- If you have found 3 correct numbers, you will get 5 times your bet amount." +
      "\n\n- If you have found 4 correct numbers, you will get 50 times." +
      "\n\n2. The <<SUPER BET>> game:  You will choose an additional lucky number between 1 and 10." +
      "\n\n- If you have found the correct lucky number, you will earn 5 times the payout obtained with the simple <<BET>> game."
      +
      "\n\n- If not, you will receive the same payout as simple <<BET>> game"
      + "\n\nLast but not least, if you want to test the calculation in the happy case, you can select checkboxes (regular number or lucky number or both) during your bet turn.";

  // font
  public static final String FONT_NAME = "Times New Roman";
  public static final int FONT_SIZE_SMALL = 13;
  public static final int FONT_SIZE_LARGE = 24;
  public static final int FONT_SIZE_MEDIUM = 16;

  // object
  public static final String TURN = "turn";
  public static final String USER_BET = "user_bet";
  public static final String RESULT = "result";

  // table column
  public static final String TURN_COL = "Turn";
  public static final String PLAYER_SUB_COL = "Player submission";
  public static final String RESULT_COL = "Result";
  public static final String CORRECTION_COL = "Correction";
  public static final String GAIN_COL = "Gain";

  // message
  public static final String EXIT_MESSGAE = "Do you confirm to exit the lottery? Your history will be lost.";
  public static final String EXIT_CONFIRM = "Confirm exit";
  public static final String WITHOUT_LUCKY = "without lucky number.";
  public static final String WITH_LUCKY = "with lucky number ";
  public static final String COUNT_CORRECTION = "Count correction: ";

  // number limit
  public static final int REGULAR_UPPER_LIMIT = 20;
  public static final int REGULAR_LOWER_LIMIT = 1;
  public static final int LUCKY_UPPER_LIMIT = 10;
  public static final int LUCKY_LOWER_LIMIT = 1;
  public static final int CORRECT_3_NUMBERS = 5;
  public static final int CORRECT_4_NUMBERS = 50;
  public static final int CORRECT_LUCKY = 5;
  public static final int BET_LOWER_LIMIT = 0;
  public static final int BET_UPPER_LIMIT = 2000000000;

}
