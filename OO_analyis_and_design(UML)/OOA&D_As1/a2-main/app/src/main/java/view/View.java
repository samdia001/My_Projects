package view;

import java.util.ArrayList;
import model.domain.Item;
import model.domain.Member;
import model.domain.exception.InvalidIdException;

/**
 * This is just an interface of View.
 */
public interface View {

  int getInput();

  String getString();

  void displaySelectedMemInfo(Member mem);

  LendingEvent mainMenu();

  LendingEvent memberSelectionMenu();

  LendingEvent itemSelectionMenu();

  void displayVerboseList(ArrayList<Member> memberList) throws CloneNotSupportedException;

  void displayMemberFullInfo(Member m) throws CloneNotSupportedException;

  void displayCompactList(ArrayList<Member> memberList);

  void displayMembersInfo(Member m);

  Member enterMemInfo();

  Item promtItemInfo();

  int promptItemid();

  Integer promptItemStartDay();

  Integer promptItemEndDay();

  void printConfirmationItemChange(boolean update);

  int showMemberMenu(Iterable<? extends Member> allMembers, int size) throws CloneNotSupportedException;

  void printCounter(boolean update, int recordedDay);

  int askAdvanceDay();

  Integer showItem(Member mem) throws CloneNotSupportedException, InvalidIdException;

  void errorMessage(String message);

}
