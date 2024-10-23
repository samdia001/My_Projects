package view;

import java.util.Scanner;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * This is the view.
 */
public abstract class OrginalView implements View {
  Scanner scanner = new Scanner(System.in, "utf-8");

  /**
   * This is a msthod to get characters input.
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

  /**
   * This is the view.
   */
  public String getString() {
    String input = scanner.nextLine().strip();
    return input;

  }

  // ------------------------------------------
  /**
   * This is a method to shows a mems info.
   */
  public void displaySelectedMemInfo(Member mem) {
    System.out.println(mem.getName() + ": " + mem.getEmail()
        + ": " + mem.getMemberId() + ": " + mem.getPhoneNumber());
  }

  public void displayCred(Member m) {
    System.out.println(m.getCredits() + ": " + m.numberOwnedItems());
  }

  public void displayItemInfo(Item item) {
    System.out.println(item.getName() + ": " + item.getDescription() + ": " + item.getCategory() + ": "
        + item.getCostForOneDay() + ": " + item.getIdItem());
  }

  /**
   * This is a method to shows a contracts info.
   */
  public void displayContractInfo(Contract c) {
    try {
      System.out.println("\t" + c.getCurrentItem().getName() + ": " + c.getCurrentUser().getName() + ": "
          + c.getStartDay() + ": " + c.getEndDay());
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
  }

}
