package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;
import model.domain.exception.InvalidIdException;

/**
 * This is a class for swedish view.
 */
public class SwedishView extends OrginalView {
  /**
   * This method shows a menu.
   */
  public LendingEvent mainMenu() {
    System.out.println("\n***** Meny *****\n"
        + "(a) FÖRSKJUTEN TID\n"
        + "(b) MEDLEMS MENY\n"
        + "(c) LOGGA UT\n"
        + "\nVälj ett av alternativen");

    switch (getInput()) {
      case 'a':
        return LendingEvent.AdvanceDay;
      case 'b':
        return LendingEvent.MemberMenu;
      case 'c':
        return LendingEvent.Quit;
      default:
        System.out.println("Vänligen kontrollera din inmatning igen");
        break;
    }
    return this.mainMenu();
  }

  /**
   * This method shows a menu.
   */
  public LendingEvent memberSelectionMenu() {
    System.out.println("***** Meny för medlem *****\n"
        + "(a) Visa en översiktslista över alla medlemmar\n"
        + "(b) Visa en detaljerad lista över alla medlemmar\n"
        + "(c) Lägg till ny medlem\n"
        + "(d) Ta bort medlem\n"
        + "(e) Ändra en medlems information\n"
        + "(f) Visa information om en enskild medlem\n"
        + "(g) Gå tillbaka\n"
        + "\nVälj ett av alternativen");

    switch (getInput()) {
      case 'a':
        return LendingEvent.CompactList;
      case 'b':
        return LendingEvent.VerboseList;
      case 'c':
        return LendingEvent.AddMem;
      case 'd':
        return LendingEvent.DeleteMem;
      case 'e':
        return LendingEvent.ChangeMemberInfo;
      case 'f':
        return LendingEvent.ShowAMemberInfo;
      case 'g':
        return LendingEvent.ReturnToMenu;
      default:
        System.out.println("Vänligen kontrollera din inmatning igen");
        break;
    }
    return null;
  }

  /**
   * This is the event.
   */
  public LendingEvent itemSelectionMenu() {

    System.out.println("***** Meny för lånevara *****\n"
        + "(a) Lägg till en ny vara\n"
        + "(b) Låna en vara\n"
        + "(c) Ändra en varas information\n"
        + "(d) Ta bort en vara\n"
        + "(e) Gå tillbaka\n"
        + "\nVälj ett av alternativen");

    switch (getInput()) {
      case 'a':
        return LendingEvent.AddNewItem;
      case 'b':
        return LendingEvent.LendAnItem;
      case 'c':
        return LendingEvent.changeAnItemInfo;
      case 'd':
        return LendingEvent.DeleteAnItem;
      case 'e':
        return LendingEvent.ReturnToMenu;
      default:
        System.out.println("Vänligen kontrollera din inmatning igen");
        break;

    }
    return null;

  }

  /**
   * This method shows a verbose list of members.
   */
  public void displayVerboseList(ArrayList<Member> memberList) throws CloneNotSupportedException {
    Comparator<Member> compareByName = (Member o1, Member o2) -> o1.getName().compareTo(o2.getName());

    Collections.sort(memberList, compareByName);
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
    Comparator<Member> compareByName = (Member o1, Member o2) -> o1.getName().compareTo(o2.getName());

    Collections.sort(memberList, compareByName);
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
    scanner.nextLine();
    System.out.println("Ange medlemmens namn: ");
    String memName = getString();
    System.out.println("Ange medlemmens email: ");
    String email = getString();
    System.out.println("Ange medlemmens telefon: ");
    String phone = getString();
    Member newMem = new Member(memName, email, phone);
    return newMem;
  }

  /**
   * This method takes an input for items info.
   */

  public Item promtItemInfo() {
    System.out.println("Ange varans nya namn: ");
    scanner.nextLine();
    String itemName = getString();
    System.out.println("Ange varans beskrivning: ");
    String description = getString();
    System.out.println("Ange kategori för varan (exempelvis verktyg, fordon, spel, leksak, sport, annat): ");
    String category = getString();
    System.out.println("Ange varans pris: ");
    Integer cost = Integer.parseInt(getString());
    Item newItem = new Item(itemName, description, category, cost);
    return newItem;
  }

  /**
   * This method takes an input for items id.
   */
  public int promptItemid() {
    System.out.print("\nAnge varans id: ");
    int itemId = scanner.nextInt();
    return itemId;
  }

  /**
   * This method takes an input for items start day.
   */
  public Integer promptItemStartDay() {
    System.out.print("\nAnge startdag(Bara siffra ex 1,2 osv.): ");
    Integer startDay = scanner.nextInt();
    return startDay;
  }

  /**
   * This method takes an input for items end day.
   */
  public Integer promptItemEndDay() {
    System.out.print("\nAnge slutdag(Bara siffra ex 1,2 osv.): ");
    Integer endDay = scanner.nextInt();
    return endDay;
  }

  /**
   * This method prints confirmation of a process.
   */
  public void printConfirmationItemChange(boolean update) {
    if (update) {
      System.out.println("\n***** Klart *****\n");
    } else {
      System.out.println("\n***** Ej lyckas *****\n");
    }
  }

  /**
   * This method takes an input of a mems index.
   */
  public int showMemberMenu(Iterable<? extends Member> allMembers, int size) throws CloneNotSupportedException {
    System.out.println("\nAnge medlemmens index (numret före namnet)\n~~~~~~~~~~~~~~");
    int ix = 0;
    System.out.println("");
    for (Member m : allMembers) {
      System.out.print("" + ix + " ");
      System.out.println("NAMN: " + m.getName() + "\nTELEFON: " + m.getPhoneNumber() + "\n");
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
    // System.out.println("\nType The id of the Item\n~~~~~~~~~~~~~~");
    boolean found = false;
    System.out.println("ÄGARE: " + mem.getName() + ": TELEFON: " + mem.getPhoneNumber());
    for (Item i : mem.getItemList()) {
      showItemInfoSection(i);

      for (Contract c : i.contractsOfThisItem()) {
        showContractInfoSection(c);
      }
    }
    System.out.println("\nAnge varans id\n=======================");

    Integer choice = scanner.nextInt();
    for (Item i : mem.getItemList()) {
      if (choice.equals(i.getIdItem())) {
        found = true;
        break;
      }
    }
    if (!found) {
      throw new InvalidIdException("Ogiltigt varans id, vänligen försök igen.");
    }
    return choice;
  }

  private void showMemInfoSection(Member m) {
    System.out.println("NAMN: " + "EMAIL: " + "ID: " + "TELEFON");
    displaySelectedMemInfo(m);
  }

  private void showMemCredSection(Member m) {
    System.out.println("NUVARANDE KREDITER: ANTALET ÄGDA VAROR");
    displayCred(m);
  }

  private void showItemInfoSection(Item item) {
    System.out.println("VARANS NAMN: BESKRIVNING: KATEGORI: PRIS: ITEM ID");
    displayItemInfo(item);
  }

  private void showContractInfoSection(Contract c) {
    System.out.println("\t********DEN HÄR HAR KONTRAKT*********");
    System.out.println("\tLÅNEVARA: LÅNARE: STARTDAG: SLUTDAG");
    displayContractInfo(c);
    System.out.println("\t**************************************");
  }

  public void errorMessage(String message) {
    System.out.println("\n=== Fel! ===");
    System.out.println(message + "\n");
  }

  /**
   * This method prints created day.
   */
  public void printCounter(boolean update, int recordedDay) {
    if (update) {
      System.out.println("Skapad dag: " + recordedDay);
    }
  }

  /**
   * This method takes an input of advance day.
   */
  public int askAdvanceDay() {
    System.out.print("Hur många dagar vill du gå framåt: ");
    int day = scanner.nextInt();
    return day;
  }

}
