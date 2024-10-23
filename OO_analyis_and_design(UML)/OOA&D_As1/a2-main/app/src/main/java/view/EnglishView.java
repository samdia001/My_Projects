package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;
import model.domain.exception.InvalidIdException;

/**
 * This is a class for english view.
 */
public class EnglishView extends OrginalView {
  /**
   * This method shows a menu.
   */
  public LendingEvent mainMenu() {
    System.out.println("\n***** Menu *****\n"
        + "(1) MEMBER'S MENU\n"
        + "(2) ADVANCE DAY\n"
        + "(3) QUIT\n"
        + "\nEnter a number");

    String choice = scanner.nextLine().strip();

    switch (choice) {
      case "1":
        return LendingEvent.MemberMenu;
      case "2":
        return LendingEvent.AdvanceDay;
      case "3":
        return LendingEvent.Quit;
      default:
        System.out.println("Please check your number again");
        break;
    }

    return this.mainMenu();
  }

  /**
   * This method shows a menu.
   */
  public LendingEvent memberSelectionMenu() {
    System.out.println("***** Member Menu *****\n"
        + "(1) Show a verbose list of members\n"
        + "(2) Show a compact list of members\n"
        + "(3) Show information of a selected member\n"
        + "(4) Change a member's information\n"
        + "(5) Remove a member\n"
        + "(6) Add a new member\n"
        + "(7) Go back\n"
        + "\nPlease enter a number");

    int choice = scanner.nextInt();
    // scanner.nextLine();
    System.out.println();

    switch (choice) {
      case 1:
        return LendingEvent.VerboseList;
      case 2:
        return LendingEvent.CompactList;
      case 3:
        return LendingEvent.ShowAMemberInfo;
      case 4:
        return LendingEvent.ChangeMemberInfo;
      case 5:
        return LendingEvent.DeleteMem;
      case 6:
        return LendingEvent.AddMem;
      case 7:
        return LendingEvent.ReturnToMenu;
      default:
        System.out.println("Please check your number again");
        break;

    }
    return null;
  }

  /**
   * This is the item event.
   */
  public LendingEvent itemSelectionMenu() {

    System.out.println("***** Item Menu *****\n"
        + "(1) Change an item's information\n"
        + "(2) Delete an item\n"
        + "(3) Add a new item\n"
        + "(4) Lend an item\n"
        + "(5) Go back\n"
        + "\nPlease enter a number in this item menu");

    int actionChoice = scanner.nextInt();
    if (actionChoice == 1) {
      return LendingEvent.changeAnItemInfo;
    } else if (actionChoice == 2) {
      return LendingEvent.DeleteAnItem;
    } else if (actionChoice == 3) {
      return LendingEvent.AddNewItem;
    } else if (actionChoice == 4) {
      return LendingEvent.LendAnItem;
    } else {
      return LendingEvent.ReturnToMenu;
    }

  }

  /**
   * This method shows a verbose list of members.
   */
  public void displayVerboseList(ArrayList<Member> memberList) throws CloneNotSupportedException {
    Comparator<Member> compareById = (Member o1, Member o2) -> o1.getMemberId().compareTo(o2.getMemberId());

    Collections.sort(memberList, compareById);
    // int count = 0;
    for (Member m : memberList) {
      // System.out.print(count++ + ") ");
      displayMemberFullInfo(m);
    }
  }

  /**
   * This method shows a members full info.
   */
  public void displayMemberFullInfo(Member m) throws CloneNotSupportedException {

    showMemInfoSection(m);
    System.out.println();

    showMemCredSection(m);
    System.out.println();

    for (Item item : m.getItemList()) {
      showItemInfoSection(item);
      System.out.println();

      for (Contract c : item.contractsOfThisItem()) {
        showContractInfoSection(c);
        System.out.println();
      }
    }
    System.out.println("-----------------------------------\n");
  }

  /**
   * This method shows a compact list of members.
   */
  public void displayCompactList(ArrayList<Member> memberList) {
    Comparator<Member> compareById = (Member o1, Member o2) -> o1.getMemberId().compareTo(o2.getMemberId());

    Collections.sort(memberList, compareById);
    for (Member m : memberList) {
      displayMembersInfo(m);
    }
  }

  /**
   * This is the method.
   */
  public void displayMembersInfo(Member m) {

    showMemInfoSection(m);
    System.out.println();

    showMemCredSection(m);
    System.out.println();

    System.out.println("-----------------------------------");

  }

  /**
   * This method takes an input for mems info.
   */
  public Member enterMemInfo() {
    scanner.nextLine(); // consume any remaining input in the buffer
    System.out.println("Enter the member name: ");
    String memName = scanner.nextLine().strip();
    System.out.println("Enter the member email: ");
    String email = scanner.nextLine().strip();
    System.out.println("Enter the member phone: ");
    String phone = scanner.nextLine().strip();
    Member newMem = new Member(memName, email, phone);
    return newMem;
  }

  /**
   * This method takes an input for items info.
   */
  public Item promtItemInfo() {
    System.out.println("Enter the item new name: ");
    scanner.nextLine();
    String itemName = getString();
    System.out.println("Enter the item description: ");
    String description = getString();
    System.out.println("Enter the item category(it can be like tool,"
        + " vehicle, game, toy, sport, other): ");
    String category = getString();
    System.out.println("Enter the item cost: ");
    Integer cost = Integer.parseInt(getString());
    Item newItem = new Item(itemName, description, category, cost);
    return newItem;
  }

  /**
   * This method takes an input for items id.
   */
  public int promptItemid() {
    System.out.print("\nEnter item id: ");
    int itemId = scanner.nextInt();
    return itemId;
  }

  /**
   * This method takes an input for items start day.
   */
  public Integer promptItemStartDay() {
    System.out.print("\nEnter the start day(Only number ex 1,2 osv.): ");
    Integer startDay = scanner.nextInt();
    return startDay;
  }

  /**
   * This method takes an input for items end day.
   */
  public Integer promptItemEndDay() {
    System.out.print("\nEnter the end day(Only number ex 1,2 osv.): ");
    Integer endDay = scanner.nextInt();
    return endDay;
  }

  /**
   * This method prints confirmation of a process.
   */
  public void printConfirmationItemChange(boolean update) {
    if (update) {
      System.out.println("\n***** Accomplished *****\n");
    } else {
      System.out.println("\n***** Failed *****\n");
    }
  }

  /**
   * This method takes an input of a mems index.
   */
  public int showMemberMenu(Iterable<? extends Member> allMembers, int size) throws CloneNotSupportedException {
    System.out.println("\nType the members index (number before name)\n~~~~~~~~~~~~~~");
    int ix = 0;
    System.out.println("");
    for (Member m : allMembers) {
      System.out.print("" + ix + " ");
      System.out.println("NAME: " + m.getName() + "\nTELEFON: " + m.getPhoneNumber() + "\n");
      ix++;
    }

    String input = scanner.nextLine().trim();
    while (!input.matches("^\\d+$")) {
      System.err.println("Index: ");
      input = scanner.nextLine().trim();
    }
    int choice = Integer.parseInt(input);

    if (choice < 0 || choice > size - 1) {
      choice = showMemberMenu(allMembers, size);
    }

    return choice;
  }

  /**
   * This method takes an input of a items index.
   */
  public Integer showItem(Member mem) throws CloneNotSupportedException, InvalidIdException {
    boolean found = false;
    System.out.println("OWNER: " + mem.getName() + ": PHONE: " + mem.getPhoneNumber());
    for (Item i : mem.getItemList()) {
      showItemInfoSection(i);

      for (Contract c : i.contractsOfThisItem()) {
        showContractInfoSection(c);
      }
    }
    System.out.println("\nEnter the id of the Item\n=======================");

    Integer choice = scanner.nextInt();
    for (Item i : mem.getItemList()) {
      if (choice.equals(i.getIdItem())) {
        found = true;
        break;
      }
    }
    if (!found) {
      throw new InvalidIdException("Invalid items id, please try again.");
    }
    return choice;
  }

  private void showMemInfoSection(Member m) {
    System.out.println("NAME: " + "EMAIL: " + "ID: " + "PHONENUMBER");
    displaySelectedMemInfo(m);
  }

  private void showMemCredSection(Member m) {
    System.out.println("CURRENT CREDITS: NUMBER OWNED ITEMS");
    displayCred(m);
  }

  private void showItemInfoSection(Item item) {
    System.out.println("ITEM NAME: DESCRIPTION: CATEGORY: COST: ITEM ID");
    displayItemInfo(item);
  }

  private void showContractInfoSection(Contract c) {
    System.out.println("\t********THIS ONE HAS CONTRACT*********");
    System.out.println("\tLENDING ITEM: BORROWER: START DAY: END DAY");
    displayContractInfo(c);
    System.out.println("\t**************************************");
  }

  public void errorMessage(String message) {
    System.out.println("\n=== Error! ===");
    System.out.println(message + "\n");
  }

  /**
   * This method prints created day.
   */
  public void printCounter(boolean update, int recordedDay) {
    if (update) {
      System.out.println("Created Day: " + recordedDay);
    }
  }

  /**
   * This method takes an input of advance day.
   */
  public int askAdvanceDay() {
    System.out.print("How many days do u want to advance: ");
    int day = scanner.nextInt();
    return day;
  }

}
