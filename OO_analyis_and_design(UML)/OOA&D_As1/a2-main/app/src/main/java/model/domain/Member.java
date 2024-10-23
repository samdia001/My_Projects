package model.domain;

import java.util.ArrayList;

/**
 * This is just an class.
 */
public class Member implements Cloneable {

  private String name;
  private String email;
  private String phoneNumber;
  private String memberId;
  private int credits = 0;
  private ArrayList<Item> items;

  /**
   * constructor 2.
   */
  public Member(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.items = new ArrayList<>();
  }

  /**
   * Contructor for Member.
   */
  public Member(String name, String email, String phoneNumber, int credits) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.credits = credits;
    this.items = new ArrayList<>();

  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getMemberId() {
    return memberId;
  }

  public int getCredits() {
    return credits;
  }

  // numbers of items own by member
  public int numberOwnedItems() {
    return this.items.size();
  }

  public ArrayList<Item> getItemList() {
    ArrayList<Item> copyItems = new ArrayList<Item>(items);
    return copyItems;
  }

  /**
   * This method gets contract list of an item.
   */
  public Iterable<Contract> getContractList(Item i) {
    ArrayList<Contract> copyContractList = new ArrayList<Contract>(i.getContracts());
    return copyContractList;
  }

  // -------------------------ATTENTION: setter here-------

  protected void addItem(Item i) {
    this.items.add(i);
  }

  protected void setCredits(int credits) {
    this.credits = credits;
  }

  protected void setId(String id) {
    this.memberId = id;
  }

  protected void credit(int amount) {
    this.credits += amount;
  }

  /**
   * Delete an item.
   */
  protected void deleteItem(Item i) {
    this.items.remove(i);
  }

  protected void changeMemInfo(String memName, String newEmail, String newPhone) {
    this.name = memName;
    this.email = newEmail;
    this.phoneNumber = newPhone;
  }

}
