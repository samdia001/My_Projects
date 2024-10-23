package cookbook.view;

import cookbook.Cookbook;
import cookbook.controller.AddRecipeController;
import cookbook.controller.ItemController;
import cookbook.model.*;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
//import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;



public class RecipesScene implements Initializable {
    @FXML
    private Button AddRecipeButton;
    @FXML
    private TextField RecipeSearchField;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane ap;

    AddRecipeController addrecipe;

    User user = Session.getCurrentUser();
    int user_id = user.getUserId();


    public static Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Cookbook.class.getResource("RecipesScene.fxml"));
        Scene recipesScene = new Scene(fxmlLoader.load(), 1280, 720);

        return recipesScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //tODO: In the first use of the QueryMaker, make it so it retrieves only the USER's recipes.

        specificControls();
        try {
            QueryMaker qm = new QueryMaker();
            ObservableList<Recipe> recipes = qm.getAllRecipes();
            FilteredList<Recipe> filteredRecipes = new FilteredList<>(recipes, b -> true);
        
            loadRecipes(recipes);
        
            RecipeSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredRecipes.setPredicate(recipe -> {
                    // if no search value, display everything.
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String[] searchKeywords = newValue.toLowerCase().split("[,\\s]+");
        
                    // Check if any of the search keywords match the recipe
                    for (String searchKeyword : searchKeywords) {
                        if (recipe.getName().toLowerCase().contains(searchKeyword) ||
                                recipe.getDescription().toLowerCase().contains(searchKeyword) ||
                                recipe.getInstructions().toLowerCase().contains(searchKeyword)) {
                            return true; // found a match
                        }
        
                        try {
                            List<String> customTags = qm.getCustomTagsForRecipe(recipe.getId(), user.getUserId());
                            for (String tag : customTags) {
                                if (tag.toLowerCase().contains(searchKeyword)) {
                                    return true; // found a match
                                }
                            }
                        } catch (SQLException e) {
                          throw new RuntimeException("error: " + e.getMessage());
                        }
                    }
                    return false;
                });
        
                SortedList<Recipe> sortedRecipes = new SortedList<>(filteredRecipes);
                grid.getChildren().clear();
                loadRecipes(sortedRecipes);
            });
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
    }

    private void specificControls() {
        AddRecipeButton.setOnMouseClicked(e2 -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Cookbook.class.getResource("AddRecipeScene.fxml"));
            Node n;    
    
            // load first
            try {
                n = fxmlLoader.load();
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
            // then get controller
            addrecipe = fxmlLoader.getController();
    
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
    
            ap.getChildren().clear();
            ap.getChildren().add(n);
        });
    }


    private void loadRecipes(ObservableList<Recipe> allRecipes){
        int row = 1, col = 0;

        for(int i=0; i<allRecipes.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/cookbook/RecipeItem.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ItemController itemController = fxmlLoader.getController();
            itemController.setData(allRecipes.get(i), ap);

            if(col == 4){
                col = 0;
                row++;
            }

            grid.add(anchorPane, col++, row);
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            //GridPane.setMargin(anchorPane, new Insets(0,10,0,10));
        }
    }
}
