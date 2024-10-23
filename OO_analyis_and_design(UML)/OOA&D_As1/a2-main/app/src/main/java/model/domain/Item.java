package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * class Item.
 */
public class Item implements Cloneable {
  private String name;
  private String category;
  private Integer costForOneDay;
  private String description;
  private ArrayList<Contract> contractList = new ArrayList<>();
  private boolean available = true;
  private Integer idItem;

  public Item() {
  }

  public Item(String name) {
    this.name = name;
  }

  /**
   * A constructor item, just create an item.
   * 
   */
  public Item(String name, String description, String category, Integer costForOneDay) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.costForOneDay = costForOneDay;

  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public Integer getCostForOneDay() {
    return costForOneDay;
  }

  public String getDescription() {
    return description;
  }

  public boolean getAvailable() {
    return available;
  }

  public Integer getIdItem() {
    return idItem;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "One shared scanner object.")
  public Iterable<Contract> contractsOfThisItem() {
    return this.contractList;
  }

  ArrayList<Contract> getContracts() {
    ArrayList<Contract> copyContracts = new ArrayList<Contract>(contractList);
    return copyContracts;
  }

  // --------------------------setter--------------------------

  protected void changeInformation(String name, String description, String category, Integer costForOneDay) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.costForOneDay = costForOneDay;
  }

  private void setAvailable(boolean available) {
    this.available = available;
  }

  /**
   * This method adds a contract to an item.
   */
  protected void addContract(Contract contract) {
    contractList.add(contract);
    setAvailable(false);
  }

  /**
   * This method calculates cost for an item.
   */
  protected Integer calculateCost(Integer days) {

    Integer totalCost = this.getCostForOneDay() * (days + 1);
    return totalCost;
  }

  /**
   * This method checks avaibility of an item.
   */
  protected boolean isItemAvailable(int startD, int endD) {
    for (Contract c : this.contractsOfThisItem()) {
      if (c.getEndDay() > startD && c.getStartDay() < endD) {
        return false;
      }
    }

    return true;
  }

  protected void setIdItem(Integer idItem) {
    this.idItem = idItem;
  }

}
