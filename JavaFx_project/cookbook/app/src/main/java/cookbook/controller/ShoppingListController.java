package cookbook.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import cookbook.Cookbook;
import cookbook.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShoppingListController {

  @FXML
  private TextArea copyArea;

  @FXML
  private TextField quantityField;

  @FXML
  private Label quantityLabel;

  @FXML
  private Button deleteButton;

  @FXML
  private Button editButton;

  @FXML
  private TableView<ShoppingListItem> itemTable;

  @FXML
  private TableColumn<ShoppingListItem, String> Item;

  @FXML
  private TableColumn<ShoppingListItem, Integer> Quantity;

  @FXML
  private TableColumn<ShoppingListItem, String> Measurement;

  @FXML
  private Label listHeader;

  @FXML
  private AnchorPane ap;

  @FXML
  private Label InstructionLabel;

  @FXML
  private Button NewListButton;

  @FXML
  private Button ClearListButton;

  private ShoppingListController controller;

  private WeeklyDinnerList plan;

  private User user;
  private ShoppingList shoppingList;
  private ObservableList<ShoppingListItem> shoppingListItems;
  
  @FXML
  public void initialize() {
    this.user = Session.getCurrentUser();
    
    NewListButton.setOnMouseClicked(event -> {
      openListChoiceWindow();
    });
  }

  public void loadTable() {
    Item.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
    Quantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
    Measurement.setCellValueFactory(new PropertyValueFactory<>("measurement"));
  }

  public void setTable() {
    setShoppingList();
    loadTable();
    itemTable.setItems(shoppingListItems);
    setCopyArea();
  }

  private void openListChoiceWindow(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(Cookbook.class.getResource("SelectWeeklyPlanWindow.fxml"));

      Parent window = fxmlLoader.load();
      SelectWeeklyPlanWindowController windowController = fxmlLoader.getController();

      windowController.setCallerController(controller);

      Stage stage = new Stage();
      stage.setTitle("Select a Weekly Plan");
      stage.setScene(new Scene(window, 339, 508));
      stage.showAndWait();
      setTable();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void setController(ShoppingListController controller){
    this.controller = controller;
  }

  public void setSelectedPlan(WeeklyDinnerList plan){
    this.plan = plan;
  }

  /**
   * Set the text area conent for the shopping list same as the table view.
   */
  public void setCopyArea() {
    StringBuilder textAreaContent = new StringBuilder();

    for (ShoppingListItem item : shoppingListItems) {
      String itemName = item.getIngredientName();
      String quantity = String.valueOf(item.getQty());
      String measurement = item.getMeasurement();

      String row = quantity.toString() + "\t\t" + measurement + "\t\t\t" + itemName + "\n";
      textAreaContent.append(row);
    }
    copyArea.setText(textAreaContent.toString());
  }

  /**
   * Check first for shopping list already existing in database for weeklyplan.
   * If no shopping list, generate shopping list and insert items.
   */
  private void setShoppingList() {

    try {
      QueryMaker qm = new QueryMaker();
      ShoppingList shoppingList = qm.retrieveShoppingList(plan.getWeekId(), user);
      // check if shopping list already exists
      if (shoppingList != null) {
        this.shoppingList = shoppingList;

        ObservableList<ShoppingListItem> shoppingListItems = qm.retrieveShoppingListItems(shoppingList.getListId());
        this.shoppingListItems = shoppingListItems;
      // create new list and insert items if list doesn't exist
      } else {
        LocalDate dateCreated = LocalDate.now();
        qm.createShoppingList(user.getUserId(), plan.getWeekId(), plan.getWeekName(), dateCreated);

        ShoppingList newShoppingList = qm.retrieveShoppingList(plan.getWeekId(), user);
        this.shoppingList = newShoppingList;

        qm.insertShoppingListItems(plan, newShoppingList.getListId());

        ObservableList<ShoppingListItem> shoppingListItems = qm.retrieveShoppingListItems(newShoppingList.getListId());
        this.shoppingListItems = shoppingListItems;
      }

    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Delete shopping list item from shopping list. 
   *
   * @param event on item selecton and button click
   */
  @FXML
  void delete(ActionEvent event) {
    ShoppingListItem selectedItem = itemTable.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      int itemId = selectedItem.getItemId();
        
      try {
        QueryMaker queryMaker = new QueryMaker();
        queryMaker.deleteListItem(itemId);
      } catch (SQLException e) {
        e.printStackTrace();
      } 
      itemTable.getItems().remove(selectedItem);
      itemTable.refresh();
      setCopyArea();
    }
  }

  /**
   * Edit the quantity of a shopping list item.
   *
   * @param event on text field not null and a number and button click
   */
  @FXML
  void edit(ActionEvent event) {
    ShoppingListItem selectedItem = itemTable.getSelectionModel().getSelectedItem();
    String quantityText = quantityField.getText();

    if (quantityText != null && quantityText.matches("\\d+")) {
      int quantity = Integer.parseInt(quantityText);
      int itemId = selectedItem.getItemId();
      try {
        QueryMaker queryMaker = new QueryMaker();
        queryMaker.updateListItem(itemId, quantity);
      } catch (SQLException e) {
        e.printStackTrace();
      } 
      setTable();
      setCopyArea();
    }
  }
}
