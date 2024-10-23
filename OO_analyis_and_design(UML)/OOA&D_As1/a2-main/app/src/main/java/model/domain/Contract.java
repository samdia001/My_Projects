package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This is just an class.
 */
public class Contract {
  private int startDay;
  private int endDay;
  private Item item;
  private Member borrower;

  public Contract() {
  }

  /**
   * This is just a contructor.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "One shared scanner object.")
  public Contract(int startDay, int endDay, Item item, Member borrower) {
    this.startDay = startDay;
    this.endDay = endDay;
    this.item = item;
    this.borrower = borrower;

  }

  public int getStartDay() {
    return this.startDay;
  }

  public int getEndDay() {
    return this.endDay;
  }

  /**
   * This is a method.
   */
  public Item getCurrentItem() throws CloneNotSupportedException {
    Item currentItem = this.item;
    Item copyItem = (Item) currentItem.clone();
    return copyItem;
  }

  /**
   * This is a method.
   */
  public Member getCurrentUser() throws CloneNotSupportedException {
    Member currentUser = this.borrower;
    Member copyCurrentUser = (Member) currentUser.clone();
    return copyCurrentUser;
  }

  // --------------------------setter--------------------
  protected void setStartDay(int startDay) {
    this.startDay = startDay;
  }

  protected void setEndDay(int endDay) {
    this.endDay = endDay;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "One shared scanner object.")
  protected void setCurrentItem(Item currentItem) {
    this.item = currentItem;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "One shared scanner object.")
  protected void setBorrower(Member borrower) {
    this.borrower = borrower;
  }

}
