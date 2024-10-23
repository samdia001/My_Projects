package cookbook.model;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import cookbook.Cookbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * {@summary} Controller class manages the editing functionality for recipes.
 */
public class RecipeEditor {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button addButton;

  @FXML
  private AnchorPane ap;

  @FXML
  private Button deleteButton;

  @FXML
  private TextArea descriptionArea;

  @FXML
  private TextArea instructionsArea;

  @FXML
  private TextField editCookTimeField;

  @FXML
  private TextField editNameField;

  @FXML
  private TextField editPrepTimeField;

  @FXML
  private TextField editServingsField;

  @FXML
  private TableView<Ingredient> ingredientsTable;

  @FXML
  private TableColumn<Ingredient, String> measurementCol;

  @FXML
  private TableColumn<Ingredient, String> nameCol;

  @FXML
  private TableColumn<Ingredient, Integer> qtyCol;

  @FXML
  private TextField inputMeasurement;

  @FXML
  private TextField inputName;

  @FXML
  private TextField inputQty;

  @FXML
  private Button updateButton;

  @FXML
  private Button saveButton;

  @FXML
  private ImageView returnButton;

  ObservableList<Ingredient> ingredientList =  FXCollections.observableArrayList();
  Recipe recipe;
  int recipeId;

  /**
   * {@summary} Initializes ui and functionality of scene by loading data, refreshing tables
   * and scene transition.
   *
   * @param recipe Recipe object passed from display recipe scene
   */
  @FXML
  public void initialize(Recipe recipe) {
    this.recipe = recipe;
    this.recipeId = recipe.getId();
    loadData();
    refreshTable(recipeId);
    addRecipeObject(recipe);
    transitionPreviousScene();
  }

  /**
   * {@summary} Sets up the cell value for ingredient name, quantity, and measurement columns
   *  allowing the data to be displayed as a table component.
   */
  public void loadData() {
    nameCol.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
    qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
    measurementCol.setCellValueFactory(new PropertyValueFactory<>("measurement"));
  }

  /**
   * {@summary} Refreshes the ingredients table to retrieve the latest associated ones from recipe.
   *
   * @param recipeId Recipe id passed from previous scene
   */
  public void refreshTable(int recipeId) {
    ingredientList.clear();

    try {
      QueryMaker qm = new QueryMaker();
      ObservableList<Ingredient> databaseList = qm.retrieveIngredients(recipeId);
        
      for (Ingredient i : databaseList) {
        ingredientList.add(new Ingredient(i.getIngredientName(), i.getQty(), i.getMeasurement()));
      }

    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    ingredientsTable.setItems(ingredientList);
  }

  /**
   * Inserts the ingredient properties into user input fields when selected by mouse in table.
   */
  @FXML
  private void ingredientClicked(MouseEvent event) {
    Ingredient selectedIngredient = ingredientsTable.getSelectionModel().getSelectedItem();
    inputName.setText(selectedIngredient.getIngredientName());
    inputQty.setText(String.valueOf(selectedIngredient.getQty()));
    inputMeasurement.setText(selectedIngredient.getMeasurement());
  }

  /**
   * {@summary} Updates ingredient based on user input for name, qty, measurement when clicked.
   */
  @FXML
  private void update(ActionEvent event) {
    Ingredient editedIngredient = ingredientsTable.getSelectionModel().getSelectedItem();
    String originalName = editedIngredient.getIngredientName();
    editedIngredient.setIngredientName(inputName.getText());
    editedIngredient.setQty(Integer.parseInt(inputQty.getText()));
    editedIngredient.setMeasurement(inputMeasurement.getText());

    try {
      QueryMaker qm = new QueryMaker();
      qm.updateIngredient(editedIngredient, originalName, recipeId);
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    ingredientsTable.refresh();
    inputName.clear();
    inputQty.clear();
    inputMeasurement.clear();
  }

  /**
   * {@summary} Deletes a selected ingredient from the recipe.
   */
  @FXML
  private void deleteIngredient(ActionEvent event) {
    Ingredient selectedIngredient = ingredientsTable.getSelectionModel().getSelectedItem();
    ingredientsTable.getItems().remove(selectedIngredient);

    try {
      QueryMaker qm = new QueryMaker();
      qm.deleteIngredient(selectedIngredient, recipeId);
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    ingredientsTable.refresh();
    inputName.clear();
    inputQty.clear();
    inputMeasurement.clear();
  }

  /**
   * {@summary} Adds a new ingredient to the recipe.
   */
  @FXML
  private void addIngredient(ActionEvent event) {
    String ingredientName = inputName.getText();
    int id = recipeId;
    int qty = Integer.parseInt(inputQty.getText());
    String measurement = inputMeasurement.getText();

    Ingredient newIngredient = new Ingredient(ingredientName, id, qty, measurement);

    try {
      QueryMaker qm = new QueryMaker();
      qm.addIngredient(newIngredient);
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    ingredientList.add(newIngredient);
    ingredientsTable.refresh();
    inputName.clear();
    inputQty.clear();
    inputMeasurement.clear();
  }


  /**
   * {@summary} Adds the recipe object selected and sets its properties into java fx components such
   * as labels and text areas.
   *
   * @param recipe Recipe object passed as parameter from previous scene when user selects to edit
   */
  public void addRecipeObject(Recipe recipe){
    editNameField.setText(recipe.getName());
    descriptionArea.setText(recipe.getDescription());
    instructionsArea.setText(recipe.getInstructions());
    editServingsField.setText(String.valueOf(recipe.getServings()));
    editPrepTimeField.setText(String.valueOf(recipe.getPrepTime()));
    editCookTimeField.setText(String.valueOf(recipe.getCookTime()));
  }

  @FXML
  void saveRecipe(ActionEvent event) {
    System.out.println(recipe.getName());
    String savedName = editNameField.getText();
    String savedDescription = descriptionArea.getText();
    String savedInstructions = instructionsArea.getText();
    int savedServings = Integer.parseInt(editServingsField.getText());
    int savedPrepTime = Integer.parseInt(editPrepTimeField.getText());
    int savedCookTime = Integer.parseInt(editCookTimeField.getText());
    recipe.setServings(savedServings);
    recipe.setPrepTime(savedPrepTime);
    recipe.setCookTime(savedCookTime);
    recipe.setName(savedName);
    recipe.setDescription(savedDescription);
    recipe.setInstructions(savedInstructions);

    try {
      QueryMaker qm = new QueryMaker();
      qm.updateRecipe(recipe);
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  @FXML
  void transitionPreviousScene() {
    returnButton.setOnMouseClicked(event -> {
      FXMLLoader fxmlLoader = new FXMLLoader(Cookbook.class.getResource("RecipesScene.fxml"));
      Node n;   

      try {
        n = fxmlLoader.load();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      AnchorPane.setTopAnchor(n, 0.0);
      AnchorPane.setRightAnchor(n, 0.0);
      AnchorPane.setBottomAnchor(n, 0.0);
      AnchorPane.setLeftAnchor(n, 0.0);

      ap.getChildren().clear();
      ap.getChildren().add(n);
    });
  }
}
    
