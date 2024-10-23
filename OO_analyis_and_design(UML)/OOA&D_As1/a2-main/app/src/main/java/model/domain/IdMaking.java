package model.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class makes ID for a member and an item.
 */
public class IdMaking {
  protected String makeid(ArrayList<Member> listOfMembers) {
    String theCreadtedId;

    Random rnd = new Random();
    String alphaNum = "0123456789abcdefghijklmnopqrstuvwxyz";
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 6; i++) {
      sb.append(alphaNum.charAt(rnd.nextInt(alphaNum.length())));
    }

    theCreadtedId = sb.toString();
    for (Member member : listOfMembers) {

      if (theCreadtedId.equals(member.getMemberId())) {
        break;
      }
    }
    return theCreadtedId;

  }

  protected Integer generateIdItem(ArrayList<Member> listOfMembers) {
    Integer nextId = 0;
    for (Member m : listOfMembers) {
      for (Item i : m.getItemList()) {
        if (nextId <= i.getIdItem()) {
          nextId = i.getIdItem() + 1;
        }
      }
    }

    return nextId;
  }
}
