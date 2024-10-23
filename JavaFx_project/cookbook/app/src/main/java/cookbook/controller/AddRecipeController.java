package cookbook.controller;

// AddRecipe Controller made by Eldaras

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cookbook.Cookbook;
import cookbook.model.IngredientsAddRecipe;
import cookbook.model.User;
import cookbook.model.Session;
import cookbook.model.Tags;


public class AddRecipeController implements Initializable {

    @FXML private TextField nameField;

    @FXML private Label nameLabel;

    @FXML private Label createLabel;

    @FXML private TextField portionSize;

    @FXML private Label portionLabel;

    @FXML private Label descLabel;

    @FXML private TextField prepField;

    @FXML private TextField cookField;

    @FXML private Label prepLabel;

    @FXML private Label cookLabel;

    @FXML private TextField servingsField;

    @FXML private Label servingsLabel;

    @FXML private TextArea descField;

    @FXML private Button addRecipeButton;

    @FXML private Label insLabel;

    @FXML private Label tagsLabel;

    @FXML private TextArea insField;

    @FXML private TextField tagsField;

    @FXML private Button insButton;

    @FXML private Button tagsButton;

    @FXML private Label ingLabel;

    @FXML private TextField ingField;

    @FXML private Label measurementLabel;
    
    @FXML private ChoiceBox<String> measurementField;
    
    @FXML private Label quantityLabel;

    @FXML private TextField quantityField;

    @FXML private TableView<IngredientsAddRecipe> tableView;

    @FXML private TableColumn<IngredientsAddRecipe, String> ingColumn;

    @FXML private TableColumn<IngredientsAddRecipe, Integer> quantityColumn;

    @FXML private TableColumn<IngredientsAddRecipe, String> measurementColumn;

    @FXML private Button addTagButton;

    @FXML private Button submitButton;

    @FXML private Button removeButton;
    
    @FXML private ImageView returnButton;

    @FXML private TableView<Tags> tagsView;

    @FXML private TableColumn<Tags, String> tagNameColumn;

    @FXML private AnchorPane ap;
    @FXML private GridPane grid;

    @FXML private CheckBox checkbox1;
    @FXML private CheckBox checkbox2;
    @FXML private CheckBox checkbox3;
    @FXML private CheckBox checkbox4;
    @FXML private CheckBox checkbox5;
    @FXML private CheckBox checkbox6;
    @FXML private CheckBox checkbox7;
    @FXML private CheckBox checkbox8;

    private String[] measurements = {"kg", "g", "l", "ml", "tbsp", "tsp", "cup", "cups", "cloves", "large", "head"};

    /**
    * This function initialize has addButton.
    * All the information written in the TextFields and given to the TableView,
    * are being stored inside the database.
    */ 

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      loadData();
      measurementField.getItems().addAll(measurements);

      addRecipeButton.setOnAction(event -> {
        String recipeName = nameField.getText();
        String recipeDesc = descField.getText();
        String recipeInstructions = insField.getText();
        int servings = Integer.parseInt(servingsField.getText());
        int prepTime = Integer.parseInt(prepField.getText());
        int cookTime = Integer.parseInt(cookField.getText());
        User user = Session.getCurrentUser();
        int user_id = user.getUserId();

        // The if statement could have been dealt better but could not find a better solution.
        // Source: https://youtu.be/5YX18em7vBw 
        List<String> selectedCheckboxValues = new ArrayList<>();
        if (checkbox1.isSelected()) {
          selectedCheckboxValues.add(checkbox1.getText());
        }
  
        if (checkbox2.isSelected()) {
          selectedCheckboxValues.add(checkbox2.getText());
        }
  
        if (checkbox3.isSelected()) {
          selectedCheckboxValues.add(checkbox3.getText());
        }
  
        if (checkbox4.isSelected()) {
          selectedCheckboxValues.add(checkbox4.getText());
        }
  
        if (checkbox5.isSelected()) {
          selectedCheckboxValues.add(checkbox5.getText());
        }
  
        if (checkbox6.isSelected()) {
          selectedCheckboxValues.add(checkbox6.getText());
        }
  
        if (checkbox7.isSelected()) {
          selectedCheckboxValues.add(checkbox7.getText());
        }
  
        if (checkbox8.isSelected()) {
          selectedCheckboxValues.add(checkbox8.getText());
        }
    
        for (String checkboxValue : selectedCheckboxValues) {
          Tags tag = new Tags(checkboxValue); 
          tagsView.getItems().add(tag);
        }
        
        try {
          Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost/cookbook?user=root&password=123456&useSSL=false");
          Statement stmt = conn2.createStatement();
                
          String query = "INSERT INTO recipes (recipe_name, recipe_description, recipe_instructions, servings, prep_time_minutes, cook_time_minutes, user_id) " +
                         "VALUES ('" + recipeName + "', '" + recipeDesc + "', '" + recipeInstructions + "', " +
                          servings + ", " + prepTime + ", " + cookTime + ", " + user_id + ")";
          stmt.executeUpdate(query);
                
          ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
          rs.next();
          int recipeId = rs.getInt(1);
          ObservableList<Tags> tags = tagsView.getItems();

          for (Tags tag : tags) {
            String tagName = tag.getName();
            tagName = tagName.trim();
            if (tagName.length() > 0) {
              ResultSet rsTag = stmt.executeQuery("SELECT tag_id FROM tags WHERE tag_name = '" + tagName + "'");
              if (rsTag.next()) {
                int tagId = rsTag.getInt(1);
                stmt.executeUpdate("INSERT INTO recipe_tags (recipe_id, tag_id) " +
                                   "VALUES ('" + recipeId + "', " + tagId + ")");
              } else {
                stmt.executeUpdate("INSERT INTO custom_tags (user_id, ctag_name) " +
                                   "VALUES (" + user_id + ", '" + tagName + "')");
                ResultSet rsCtag = stmt.executeQuery("SELECT LAST_INSERT_ID()");
                rsCtag.next();
                int ctagId = rsCtag.getInt(1);
                stmt.executeUpdate("INSERT INTO recipe_ctags (recipe_id, ctag_id) " +
                                   "VALUES ('" + recipeId + "', " + ctagId + ")");
              }
            }
          }
          
          ObservableList<IngredientsAddRecipe> ingredients = tableView.getItems();
          for (IngredientsAddRecipe ingredient : ingredients) {
            String ingName = ingredient.getName();
            int quantity = ingredient.getQuantity();
            String measurement = ingredient.getMeasurement();
            String ingredientQuery = "INSERT INTO ingredients (i_name, recipe_id, qty, measurement) " +
                                     "VALUES ('" + ingName + "', " + recipeId + ", " + quantity + ", '" + measurement + "')";
            stmt.executeUpdate(ingredientQuery);
          }
          tableView.getItems().clear();
          tagsView.getItems().clear();

        } catch (SQLException e) {
          e.printStackTrace();
        }
        clearFields();
      });

    /**
    * The addTagButton puts the written tag inside the TableView.
    */ 

    addTagButton.setOnAction(event -> {
      Tags tag = new Tags(tagsField.getText());
      ObservableList<Tags> tags = tagsView.getItems();
      tags.add(tag);
      tagsView.setItems(tags);
      tagsField.clear();
    });

    /**
    * Similar to addTagButton but with ingredients.
    */ 

    submitButton.setOnAction(event -> {
      IngredientsAddRecipe ingredient = new IngredientsAddRecipe(ingField.getText(),
                                            Integer.parseInt(quantityField.getText()),
                                            measurementField.getValue());
      ObservableList<IngredientsAddRecipe> ingredients = tableView.getItems();
      ingredients.add(ingredient);
      tableView.setItems(ingredients);
      ingField.clear();
      quantityField.clear();
    });

    removeButton.setOnAction(event -> {
      int selectedID = tableView.getSelectionModel().getSelectedIndex();
      tableView.getItems().remove(selectedID);
    });

    returnButton.setOnMouseClicked(event -> {
      transitionPreviousScene();
    });
  }

  public void loadData() {
    ingColumn.setCellValueFactory(new PropertyValueFactory<IngredientsAddRecipe, String>("name"));
    quantityColumn.setCellValueFactory(new PropertyValueFactory<IngredientsAddRecipe, Integer>("quantity"));
    measurementColumn.setCellValueFactory(new PropertyValueFactory<IngredientsAddRecipe, String>("measurement"));
    tagNameColumn.setCellValueFactory(new PropertyValueFactory<Tags, String>("Name"));
  }

  public void clearFields() {
    nameField.clear();
    descField.clear();
    insField.clear();
    ingField.clear();
    servingsField.clear();
    prepField.clear();
    cookField.clear();
    quantityField.clear();
  }

  @FXML
  public void transitionPreviousScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(Cookbook.class.getResource("RecipesScene.fxml"));
    Node nd;   

    try {
      nd = fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    AnchorPane.setTopAnchor(nd, 0.0);
    AnchorPane.setRightAnchor(nd, 0.0);
    AnchorPane.setBottomAnchor(nd, 0.0);
    AnchorPane.setLeftAnchor(nd, 0.0);

    ap.getChildren().clear();
    ap.getChildren().add(nd);
  }
}
