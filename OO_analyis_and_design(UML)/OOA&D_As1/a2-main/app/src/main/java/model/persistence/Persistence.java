package model.persistence;

import model.domain.Item;
import model.domain.LendingRegistry;
import model.domain.Member;

/**
 * This is the hard coded data.
 */
public class Persistence {

  /**
   * This method is hard coded members data.
   */
  public void hardCodedData(LendingRegistry registry) {

    Member mem1 = new Member("Dale Phillips", "dale1@gmail.com", "123000", 270);
    Item item1 = new Item("table", "bed table", "furniture", 50);
    Item item2 = new Item("pen", "pink color pen", "tool", 10);

    registry.addMember(mem1);

    registry.appendNewItem(mem1, item1);
    registry.appendNewItem(mem1, item2);

    Member mem2 = new Member("Sabrina MCQueen", "mcQueen@gmail.com", "333333", 100);
    registry.addMember(mem2);

    Member mem3 = new Member("Misty Malone", "misty00@gmail.com", "300300", 30);
    registry.addMember(mem3);
    Item item3 = new Item("carpet", "bathrooms carpet", "furniture", 10);

    registry.appendNewItem(mem3, item3);

    registry.createContract(mem3.getMemberId(), mem1.getMemberId(), item2.getIdItem(), 5, 7);
  }
}
