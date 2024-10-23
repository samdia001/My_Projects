package view;

/**
 * Implements a console view.
 */
public abstract class OriginalView implements View {

  int choice;

  /**
   * Returns pressed characters from the keyboard.

   * @return the pressed character.
   */
  public int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  public void getChoice() {
    choice = getInput();
  }

  public boolean startGameAction() {
    return choice == 'p';
  }

  public boolean hitAction() {
    return choice == 'h';
  }

  public boolean standAction() {
    return choice == 's';
  }

  public boolean quitAction() {
    return choice == 'q';
  }

  /**
   * Display paus after the players turn.
   */
  public void showDelay() {
    System.out.println("\n\t__paus__\n");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
