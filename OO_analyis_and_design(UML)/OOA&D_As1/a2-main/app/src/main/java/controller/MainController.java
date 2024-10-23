package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.domain.Item;
import model.domain.LendingRegistry;
import model.domain.Member;
import model.domain.exception.InvalidIdException;
import view.View;

/**
 * class Controller.
 */
public class MainController {

  private LendingRegistry registry;
  private View view;

  /**
   * Method to start lending system.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public void start(LendingRegistry registry, View view) throws CloneNotSupportedException {
    this.registry = registry;
    this.view = view;
    int day = 0;
    boolean quit = false;
    while (!quit) {
      day = day + 1;
      view.LendingEvent choice = view.mainMenu();

      try {
        switch (choice) {

          case MemberMenu:
            memberMenu(day);
            break;
          case AdvanceDay: {
            int days = view.askAdvanceDay();
            memberMenu(days);
            break;
          }
          case Quit:
            quit = true;
            break;
          default:
            break;
        }
      } catch (Exception e) {
        view.errorMessage(e.getMessage());
      }
    }
  }


  private void memberMenu(int day) throws CloneNotSupportedException {
    boolean returnToMenu = false;

    view.LendingEvent option = view.memberSelectionMenu();
    try {
      switch (option) {
        case VerboseList:
          view.displayVerboseList(registry.getListOfMembers());
          break;
  
        case CompactList:
          view.displayCompactList(registry.getListOfMembers());
          break;
        case ShowAMemberInfo:
  
          int memPosition = view.showMemberMenu(registry.getListOfMembers(), registry.getListOfMembers().size());
          Member currentMem = registry.getListOfMembers().get(memPosition);
          view.displayMemberFullInfo(currentMem);
  
          view.LendingEvent option2 = view.itemSelectionMenu();
  
          switch (option2) {
  
            case changeAnItemInfo:
              changeItemInfo(memPosition);
              break;
            case DeleteAnItem:
              deleteItem(memPosition);
              break;
  
            case AddNewItem:
              addItem(memPosition, day);
              break;
  
            case LendAnItem:
  
              lendItem(memPosition);
              break;
  
            case ReturnToMenu:
              returnToMenu = true;
              break;
  
            default:
              break;
          }
  
          break;
        case ChangeMemberInfo:
          changeTheMemberInfo();
          break;
  
        case DeleteMem:
          deleteTheMember();
          break;
        case AddMem:
          addTheMember(day);
          break;
        case ReturnToMenu:
          returnToMenu = true;
          break;
        default:
          break;
      }
    } catch (Exception e) {
      view.errorMessage(e.getMessage());
    }

    if (!returnToMenu) {
      memberMenu(day);
    }
  }

  private void changeTheMemberInfo() {

    int memPosition;
    try {
      memPosition = view.showMemberMenu(registry.getListOfMembers(), registry.getListOfMembers().size());
      Member member = view.enterMemInfo();
      Boolean update = registry.changeMemInfo(memPosition, member);
      view.printConfirmationItemChange(update);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

  }

  private void deleteTheMember() {

    int memPosition;
    try {
      memPosition = view.showMemberMenu(registry.getListOfMembers(), registry.getListOfMembers().size());
      // Member member = view.enterMemInfo();
      Boolean update = registry.deleteMem(memPosition);
      view.printConfirmationItemChange(update);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

  }

  private void addTheMember(int day) {
    Member member = view.enterMemInfo();
    Boolean update = registry.addMember(member);
    view.printCounter(update, day);
    view.printConfirmationItemChange(update);

  }

  private void changeItemInfo(int memPos) throws CloneNotSupportedException {

    try {
      Member currentMem = registry.getListOfMembers().get(memPos);
      Integer idItem = view.showItem(currentMem);
      String idOfThisMem = currentMem.getMemberId();

      Item updateItem = view.promtItemInfo();
      Boolean update = registry.updateItemInfo(idOfThisMem, idItem, updateItem);
      view.printConfirmationItemChange(update);
    } catch (InvalidIdException e) {
      view.errorMessage(e.getMessage());
      return;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

  }

  private void deleteItem(int memPos) {

    try {
      Member currentMem = registry.getListOfMembers().get(memPos);
      Integer idItem = view.showItem(currentMem);

      Boolean update = registry.deleteItem(currentMem.getMemberId(), idItem);
      view.printConfirmationItemChange(update);
    } catch (InvalidIdException e) {
      view.errorMessage(e.getMessage());
      return;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
  }

  private void addItem(int memPos, int day) {
    Member currentMem = registry.getListOfMembers().get(memPos);
    Item newItem = view.promtItemInfo();

    Boolean update = registry.appendNewItem(currentMem, newItem);
    view.printCounter(update, day);
    view.printConfirmationItemChange(update);

  }

  private void lendItem(int memPos) {
    try {
      Member owner = registry.getListOfMembers().get(memPos);

      int idItem = view.showItem(owner);

      Integer startDay = view.promptItemStartDay();
      Integer endDay = view.promptItemEndDay();

      int borrowerPos = view.showMemberMenu(registry.getListOfMembers(), registry.getListOfMembers().size());
      Member borrower = registry.getListOfMembers().get(borrowerPos);

      Boolean update = registry.createContract(borrower.getMemberId(), owner.getMemberId(), idItem, startDay, endDay);
      view.printConfirmationItemChange(update);
    } catch (InvalidIdException e) {
      view.errorMessage(e.getMessage());
      return;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

  }

}
