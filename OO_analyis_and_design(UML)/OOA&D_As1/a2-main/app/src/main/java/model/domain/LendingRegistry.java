package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.persistence.Persistence;

/**
 * class LendingRegistry.
 */
public class LendingRegistry {

  private ArrayList<Member> listOfMembers;
  private Persistence persistence;
  private IdMaking action = new IdMaking();

  /**
   * Contructor LendingRegistry.
   */
  public LendingRegistry() {
    listOfMembers = new ArrayList<>();
    persistence = new Persistence();
    persistence.hardCodedData(this);

  }


  /**
   * Adds new member to the list.
   */

  public boolean addMember(Member member) {

    if (this.isEmailUnique(member.getEmail()) == true && this.isPhoneNumberUnique(member.getPhoneNumber()) == true) {
      Member newMember = member;
      // newMember.setId(makeid());
      newMember.setId(action.makeid(this.listOfMembers));
      this.listOfMembers.add(newMember);
      return true;
    }
    return false;
  }

  /**
   * Method to change a members info.
   */
  public boolean changeMemInfo(int index, Member mem) {
    if (this.isEmailUnique(mem.getEmail()) == true && this.isPhoneNumberUnique(mem.getPhoneNumber()) == true) {
      this.getListOfMembers().get(index).changeMemInfo(mem.getName(), mem.getEmail(), mem.getPhoneNumber());
      return true;
    }
    return false;
  }

  /**
   * The method delete member.
   */
  public boolean deleteMem(int position) {
    if (this.getListOfMembers().get(position) != null) {
      Member member = this.getListOfMembers().get(position);
      this.listOfMembers.remove(member);
      return true;
    }
    return false;

  }

  /**
   * This medthos selects a specific member by ID.
   *
   * @param id is arguments.
   */
  private Member getaMember(String id) {
    for (Member m : getListOfMembers()) {
      if (id.equals(m.getMemberId())) {
        return m;
      }
    }
    return null;
  }

  // *****Item section*****

  /**
   * This methods returns an item.
   */
  private Item getAnItem2(String ownerId, Integer itemId) {
    Member owner = getaMember(ownerId);
    Iterable<Item> items = owner.getItemList();
    for (Item i : items) {
      if (itemId.equals(i.getIdItem())) {
        return i;
      }
    }

    return null;

  }

  /**
   * This method deletes an item.
   */
  public boolean deleteItem(String memId, Integer itemId) {
    Member currentMem = getaMember(memId);
    for (Item i : currentMem.getItemList()) {
      if (itemId.equals(i.getIdItem()) && i.getAvailable() == true) {
        currentMem.deleteItem(i);
        return true;
      }
    }
    return false;

  }

  /**
   * When created the owning member gets 100 credits.
   * 
   */
  public boolean appendNewItem(Member mem, Item item) {
    if (isItemNameUnique(mem.getMemberId(), item.getName()) == true) {
      // item.setIdItem(generateIdItem());
      item.setIdItem(action.generateIdItem(this.getListOfMembers()));
      mem.addItem(item);
      mem.credit(100);
      return true;
    }
    return false;
  }

  /**
   * Method to update an items info.
   */
  public boolean updateItemInfo(String idOfMem, Integer itemId, Item updateItem) {
    if (isItemNameUnique(idOfMem, updateItem.getName())) {
      Item currentItem = getAnItem2(idOfMem, itemId);
      currentItem.changeInformation(updateItem.getName(), updateItem.getDescription(), updateItem.getCategory(),
          updateItem.getCostForOneDay());
      return true;
    }
    return false;

  }

  // *****Contract section*****
  /**
   * Function used so we can create contract
   * Then have possible to add contract to item.
   */
  public boolean createContract(String borrowerId, String ownerId, Integer itemId, int startDay, int endDay) {

    Item currentItem = getAnItem2(ownerId, itemId);
    int totalLendingDays = endDay - startDay;
    int amount = currentItem.calculateCost(totalLendingDays);

    Member borrower = getaMember(borrowerId);

    if ((currentItem.isItemAvailable(startDay, endDay)) && (borrower.getCredits() >= amount)) {
      Contract contract = new Contract(startDay, endDay, currentItem, borrower);
      currentItem.addContract(contract);

      Member owner = getaMember(ownerId);
      int ownerCredits = owner.getCredits();
      ownerCredits = owner.getCredits() + amount;
      owner.setCredits(ownerCredits);

      int borrowerCredits = borrower.getCredits();
      borrowerCredits = borrower.getCredits() - amount;
      borrower.setCredits(borrowerCredits);

      return true;
    }

    return false;
  }

  /**
   * This is a method to look through members.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "One shared scanner object.")
  public ArrayList<Member> getListOfMembers() {

    ArrayList<Member> copyListOfMembers = new ArrayList<Member>(this.listOfMembers);

    return copyListOfMembers;
  }

  /**
   * The method email unique.
   *
   */
  private boolean isEmailUnique(String email) {
    for (Member member : getListOfMembers()) {
      if (email.equals(member.getEmail())) {
        // System.out.println("This email is used! Please try again");
        return false;
      }
    }
    return true;
  }

  /**
   * The method phone number unique.
   *
   */
  private boolean isPhoneNumberUnique(String phoneNumber) {
    for (Member member : getListOfMembers()) {
      if (phoneNumber.equals(member.getPhoneNumber())) {
        // System.out.println("This phone number is used! Please try again");
        return false;
      }
    }
    return true;
  }

  private boolean isItemNameUnique(String idMem, String itemName) {
    Member currentMem = getaMember(idMem);
    for (Item item : currentMem.getItemList()) {
      if (itemName.equals(item.getName())) {
        return false;
      }
    }
    return true;
  }

}
